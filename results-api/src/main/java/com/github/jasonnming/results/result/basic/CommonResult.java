package com.github.jasonnming.results.result.basic;

import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 不含数据、仅包含{@link #getResultCode() 结果码}及{@link #getMessage() 结果消息}的结果。
 *
 * @author MiNG
 * @version 1.0.0
 * @apiNote 通过此接口获取结果码（{@link #getResultCode()}）所得到的结果码类型是{@link ResultCode}，
 * 如需指定具体结果码类型的请使用{@link com.github.jasonnming.results.result.generic.CommonResult}接口。
 * @since 1.0.0 (2017-01-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface CommonResult
        extends Result, WithMessage
{
    /**
     * 获取结果码。
     *
     * @return 结果码
     */
    @JsonIgnore
    @NotNull
    ResultCode getResultCode();

    /**
     * 获取{@link #getResultCode()}中返回的代码，等价于{@code getResultCode().getCode()}并且是{@code null}安全的。
     *
     * @return 结果码的对应代码
     */
    @NotNull
    default String getCode()
    {
        return this.getResultCode().getCode();
    }

    /**
     * 获取结果对应消息。
     *
     * @return 自定义的消息或对应结果码的错误信息
     */
    @Nullable
    String getMessage();

    /**
     * 代码所对应的调试诊断消息，此消息不可展现给用户。
     *
     * @return 自定义的消息或对应结果码的调试诊断消息
     */
    @JsonIgnore
    @Nullable
    String getDebugMessage();
}
