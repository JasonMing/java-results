package com.github.jasonnming.results.result.support;

import java.lang.reflect.Method;

import com.github.jasonnming.results.result.basic.ResultCode;

/**
 * 结果码解释器。<br>
 * 在使用{@link com.github.jasonnming.results.result.generic}下的{@code CommonResult<TResultCode>}（及其子类）时，
 * 如果指定了非{@link DefaultResultCode}类型的结果码类型，则需要实现此类。
 * <p>
 * 如果classpath中存在spring-core组件，在使用时会自动扫描并且加载第一个{@link ResultCodeResolver}的实现类。<br>
 * 如果没有，则会使用JDK的{@link java.util.ServiceLoader}进行加载。<br>
 * <p>
 * 另外，可以在使用处{@link MethodReturnWrapper#forMethod(Method, ResultCodeResolver)}指定具体的{@link ResultCodeResolver}来覆盖某次调用中的全局配置。
 *
 * @see MethodReturnWrapper#forMethod(Method, ResultCodeResolver)
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-09-29)
 */
@FunctionalInterface
public interface ResultCodeResolver
{
    /**
     * 将字符串格式的代码转为{@code resultCodeType}对应的{@link ResultCode}实例。
     *
     * @param resultCodeType 结果码具体类型。
     * @param code           结果码字符串。
     *
     * @return {@code resultCodeType}对应的{@link ResultCode}实例。
     */
    ResultCode resolve(Class<?> resultCodeType, String code);
}
