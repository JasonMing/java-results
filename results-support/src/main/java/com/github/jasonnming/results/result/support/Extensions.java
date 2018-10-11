package com.github.jasonnming.results.result.support;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.jetbrains.annotations.Nullable;

/**
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-09-29)
 */
final class Extensions
{
    @Nullable
    static final ResultCodeResolver RESULT_CODE_RESOLVER;

    static
    {
        RESULT_CODE_RESOLVER = loadResultCodeResolver();
    }

    private Extensions() { }

    private static ResultCodeResolver loadResultCodeResolver()
    {
        final ServiceLoader<ResultCodeResolver> load = ServiceLoader.load(ResultCodeResolver.class);

        final Iterator<ResultCodeResolver> iterator = load.iterator();
        if (iterator.hasNext())
        {
            final ResultCodeResolver next = iterator.next();
            if (iterator.hasNext())
            {
                // Warn: multiple ResultCodeResolver found.
            }
            return next;
        }
        return null;
    }
}
