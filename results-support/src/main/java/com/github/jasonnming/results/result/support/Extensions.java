package com.github.jasonnming.results.result.support;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

/**
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-09-29)
 */
final class Extensions
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Extensions.class);

    @Nullable
    static final ResultCodeResolver RESULT_CODE_RESOLVER;

    static
    {
        if (isClassPresent("org.springframework.core.type.classreading.MetadataReaderFactory"))
        {
            RESULT_CODE_RESOLVER = loadResultCodeResolverBySpring();
        } else
        {
            RESULT_CODE_RESOLVER = loadResultCodeResolverByJdk();
        }
    }

    private Extensions() { }

    private static boolean isClassPresent(final String className)
    {
        try
        {
            Class.forName(className, false, Extensions.class.getClassLoader());
            return true;
        } catch (final ClassNotFoundException e)
        {
            return false;
        }
    }

    private static ResultCodeResolver loadResultCodeResolverByJdk()
    {
        final ServiceLoader<ResultCodeResolver> load = ServiceLoader.load(ResultCodeResolver.class);

        final Iterator<ResultCodeResolver> iterator = load.iterator();
        if (iterator.hasNext())
        {
            final ResultCodeResolver next = iterator.next();
            if (iterator.hasNext())
            {
                LOGGER.warn("Multi ResultCodeResolver found, selected: [{}]", next.getClass().getName());
            }
            return next;
        }
        return null;
    }

    private static ResultCodeResolver loadResultCodeResolverBySpring()
    {
        try
        {
            final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            final MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory(resourcePatternResolver);

            final String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "**/*.class";
            final Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);

            final List<String> resultCodeResolvers = Arrays.stream(resources)
                    .filter(x -> x.isReadable())
                    .map(x -> {
                        try
                        {
                            return metadataReaderFactory.getMetadataReader(x);
                        } catch (IOException e)
                        {
                            throw new IllegalStateException("Cannot read metadata", e);
                        }
                    })
                    .filter(x -> ArrayUtils.contains(x.getClassMetadata().getInterfaceNames(), ResultCodeResolver.class.getName()))
                    .map(x -> x.getClassMetadata().getClassName())
                    .collect(Collectors.toList());

            if (resultCodeResolvers.size() == 0)
            {
                LOGGER.warn("No ResultCodeResolver was found in classpath, some generic-result related feature might disabled.");
                return null;
            }

            final String resultCodeResolver = resultCodeResolvers.get(0);
            if (resultCodeResolvers.size() > 1)
            {
                LOGGER.warn("Multi ResultCodeResolver found: [{}], selection will be random, selected: [{}]",
                        String.join(", ", resultCodeResolvers), resultCodeResolver);
            }

            return (ResultCodeResolver)Class.forName(resultCodeResolver).newInstance();
        } catch (final Exception e)
        {
            LOGGER.error("Error occurs while loading ResultCodeResolver.", e);
            return null;
        }
    }
}
