package com.github.jasonnming.results.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apiguardian.api.API;

/**
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-10-25)
 */
@API(status = API.Status.INTERNAL)
public final class InternalUtils
{
    private InternalUtils() { /* Empty */ }

    public static <T> List<T> toList(final T[] array)
    {
        return Arrays.asList(array);
    }

    public static <T> List<T> toList(final Collection<T> collection)
    {
        return new ArrayList<>(collection);
    }

    public static <T> List<T> toList(final Iterable<T> iterable)
    {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    public static <T> List<T> toList(final Iterator<T> iterator)
    {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.IMMUTABLE | Spliterator.ORDERED), false)
                .collect(Collectors.toList());
    }
}
