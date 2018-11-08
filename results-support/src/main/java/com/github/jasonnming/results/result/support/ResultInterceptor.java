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
        return MethodReturnWrapper
                .forMethod(invocation.getMethod())
                .wrapInvocation(invocation::proceed);
    }
}
