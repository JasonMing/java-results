package com.github.jasonnming.results.result.support;

import com.github.jasonnming.results.result.basic.ResultCode;

/**
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-09-29)
 */
@FunctionalInterface
public interface ResultCodeResolver<E extends Exception>
{
    ResultCode resolveEx(Class<?> type, String code) throws E;

    @SuppressWarnings({"RedundantCast", "unchecked"})
    default ResultCode resolve(final Class<?> type, final String code)
    {
        return ((ResultCodeResolver<RuntimeException>)this).resolveEx(type, code);
    }
}
