package com.github.jasonnming.results.result.support;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.core.ResolvableType;

import com.github.jasonnming.results.exception.BusinessException;
import com.github.jasonnming.results.result.generic.CommonResult;
import com.github.jasonnming.results.result.basic.ResultCode;

/**
 * 方法返回数据/异常包装器，根据方法的声明将返回的数据或抛出的异常统一包装为{@link com.github.jasonnming.results.result.basic.CommonResult}或其更具体的子类。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-09-29)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public abstract class MethodReturnWrapper
{
    private static final ConcurrentHashMap<Method, MethodReturnWrapper> RETURN_WRAPPER_CACHE = new ConcurrentHashMap<>();

    private MethodReturnWrapper() { /* Empty */ }

    public static MethodReturnWrapper forMethod(final Method method)
    {
        return forMethod(method, null);
    }

    public static MethodReturnWrapper forMethod(final Method method, @Nullable final ResultCodeResolver resultCodeResolver)
    {
        return RETURN_WRAPPER_CACHE.computeIfAbsent(method, x -> createProcessor(x, resultCodeResolver));
    }

    private static MethodReturnWrapper createProcessor(final Method method, @Nullable final ResultCodeResolver resultCodeResolver)
    {
        final Class<?> returnType = method.getReturnType();
        if (returnType != Object.class && !com.github.jasonnming.results.result.basic.CommonResult.class.isAssignableFrom(returnType))
        {
            throw new IllegalArgumentException("Method return type must be Object or descendant of CommonResult");
        }

        final Class<?> methodReturnType = method.getReturnType();
        if (CommonResult.class.isAssignableFrom(methodReturnType))
        {
            // TODO: Potential bug: when the processor is cached, the resolver will also be cached.
            return new GenericResultWrapper(method, resultCodeResolver != null ? resultCodeResolver : Extensions.RESULT_CODE_RESOLVER);
        } else if (com.github.jasonnming.results.result.basic.CommonResult.class.isAssignableFrom(methodReturnType))
        {
            return new BasicResultWrapper(methodReturnType);
        } else
        {
            return new ObjectWrapper();
        }
    }

    abstract ResultCode resolveResultCode(String code);

    abstract com.github.jasonnming.results.result.basic.CommonResult resolveResult(ResultCode resultCode, Object data);

    abstract com.github.jasonnming.results.result.basic.CommonResult resolveException(ResultCode resultCode, String message, String debugMessage);

    /**
     * 包装方法的返回数据/异常，并以{@link com.github.jasonnming.results.result.basic.CommonResult}或其更具体的子类返回。
     * <p>
     * 包装遵循以下规则：
     * <table border="1" cellpadding="5px">
     *     <tr>
     *         <th style="padding-right:5px">返回值声明 \ 执行结果</th>
     *         <td>返回</td>
     *         <td>异常</td>
     *     </tr>
     *     <tr>
     *         <td align="right" style="padding-right:5px">{@code CommonResult}+</td>
     *         <td>与方法声明一致</td>
     *         <td>与方法声明一致</td>
     *     </tr>
     *     <tr>
     *         <td align="right" style="padding-right:5px">{@code Object}</td>
     *         <td>根据返回对象类型判断</td>
     *         <td>{@code CommonResult}</td>
     *     </tr>
     * </table>
     *
     * @param returnValue 方法返回的数据。
     * @param exception   方法抛出的异常，为{@code null}时表示无异常。
     *
     * @return 包装过的数据/异常。
     */
    @NotNull
    public com.github.jasonnming.results.result.basic.CommonResult wrap(final Object returnValue, @Nullable final Throwable exception)
    {
        // 1. When returning CommonResult+, return directly.
        if (returnValue instanceof com.github.jasonnming.results.result.basic.CommonResult)
        {
            return (com.github.jasonnming.results.result.basic.CommonResult)returnValue;
        }

        // 2. When exception, resolve exception to [resultCode, message, debugMessage], then return a CommonResult.
        if (exception != null)
        {
            if (exception instanceof BusinessException)
            {
                final BusinessException businessException = (BusinessException)exception;
                final ResultCode resultCode = this.resolveResultCode(businessException.getCode());
                return this.resolveException(resultCode, businessException.getAlertMessage(), businessException.getDebugMessage());
            } else
            {
                final ResultCode resultCode = this.resolveResultCode(ResultCodes.SYSTEM_ERROR.getCode());
                return this.resolveException(resultCode, null, exception.toString());
            }
        }

        // 3. When success, set result code to SUCCESS and wrap the return value into corresponding wrapper.
        final ResultCode resultCode = this.resolveResultCode(ResultCodes.SUCCESS.getCode());

        // NOTE: The type of return value should only determine by the static declaration of method's return type.
        return this.resolveResult(resultCode, returnValue);
    }

    private static class ObjectWrapper extends MethodReturnWrapper
    {
        @Override
        public ResultCode resolveResultCode(final String code)
        {
            return ResultCodes.of(code);
        }

        @Override
        public com.github.jasonnming.results.result.basic.CommonResult resolveResult(final ResultCode resultCode, final Object data)
        {
            return Results.of(resultCode, null, null, data);
        }

        @Override
        public com.github.jasonnming.results.result.basic.CommonResult resolveException(final ResultCode resultCode, final String message, final String debugMessage)
        {
            return Results.commonResult(resultCode, message, debugMessage);
        }
    }

    private static class BasicResultWrapper extends ObjectWrapper
    {
        private final Class<? extends com.github.jasonnming.results.result.basic.CommonResult> resultType;

        @SuppressWarnings("unchecked")
        private BasicResultWrapper(final Class<?> resultType)
        {
            // It's ok to do this cast, the `resultType` will be ensured conforms to `CommonResult`.
            this.resultType = (Class<? extends com.github.jasonnming.results.result.basic.CommonResult>)resultType;
        }

        @Override
        public com.github.jasonnming.results.result.basic.CommonResult resolveResult(final ResultCode resultCode, final Object data)
        {
            return Results.of(this.resultType, resultCode, null, null, data);
        }

        @Override
        public com.github.jasonnming.results.result.basic.CommonResult resolveException(final ResultCode resultCode, final String message, final String debugMessage)
        {
            return Results.of(this.resultType, resultCode, message, debugMessage);
        }
    }

    private static class GenericResultWrapper extends BasicResultWrapper
    {
        private final Class<?> resultCodeType;

        private final ResultCodeResolver resultCodeResolver;

        private GenericResultWrapper(final Method method, final ResultCodeResolver resultCodeResolver)
        {
            super(method.getReturnType());

            if (resultCodeResolver == null)
            {
                throw new IllegalArgumentException(
                        "To use generic version of common-results, resultCodeResolver must be specified. Otherwise, a string literal code will not be able to convert to a strong-typed result code.");
            }
            final Class<?> resultCodeType = ResolvableType.forMethodReturnType(method).resolveGeneric(0);
            if (resultCodeType == null)
            {
                throw new IllegalArgumentException("Cannot resolve resultCodeType from method ["
                        + method.getDeclaringClass().getName() + "." + method.getName()
                        + "]. To use generic version of common-results, put the result code type on the first type argument directly and explicitly.");
            }

            this.resultCodeType = resultCodeType;
            this.resultCodeResolver = resultCodeResolver;
        }

        @Override
        public ResultCode resolveResultCode(final String code)
        {
            return this.resultCodeResolver.resolve(this.resultCodeType, code);
        }
    }
}
