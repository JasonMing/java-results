package com.github.jasonnming.results.result.generic;

import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

import com.github.jasonnming.results.result.basic.ResultCode;

/**
 * 不含数据、仅包含{@link #getResultCode() 结果码}及{@link #getMessage() 结果消息}的结果。
 *
 * @param <TResultCode> 返回结果码的具体类型
 *
 * @apiNote 此类型相对于 {@link com.github.jasonnming.results.result.basic.CommonResult} 更进一步细化了 {@link #getResultCode()} 的返回类型到 {@link TResultCode}。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface CommonResult<TResultCode extends ResultCode>
        extends com.github.jasonnming.results.result.basic.CommonResult
{
    /**
     * 获取结果码。编译时具体类型为{@link TResultCode}范型参数上所声明的类型。
     *
     * @return 结果码
     *
     * @apiNote 此处细化（refined）了返回值的类型，从任意的{@link ResultCode}细化为{@link TResultCode}以供编译器使用。
     */
    @Override
    @NotNull TResultCode getResultCode();
}
