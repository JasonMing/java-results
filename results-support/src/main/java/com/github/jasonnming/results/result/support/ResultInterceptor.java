package com.github.jasonnming.results.result.support;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apiguardian.api.API;

/**
 * 返回值及异常包装拦截器。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-04-02)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public class ResultInterceptor implements MethodInterceptor
{
    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable
    {
        Object value = null;
        Exception exception = null;
        try
        {
            value = invocation.proceed();
        } catch (final Exception e) // Let the exceptions not derived class from Exception throw directly.
        {
            exception = e;
        }

        final Object result = MethodReturnWrapper.forMethod(invocation.getMethod()).wrap(value, exception);
        return result;
    }
}
