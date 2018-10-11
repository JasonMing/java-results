package com.github.jasonnming.results.result.support;

import java.io.Serializable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.basic.WithMessage;
import com.github.jasonnming.results.result.generic.CommonResult;

/**
 * 只有代码和消息的结果对象。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
class DefaultCommonResult<TResultCode extends ResultCode>
        implements CommonResult<TResultCode>, Serializable
{
    private static final long serialVersionUID = 0xf83b913d008a251fL;

    /**
     * 描述本结果的代码。
     */
    private final TResultCode code;

    /**
     * 用户可读消息。
     **/
    private final String message;

    /**
     * 调试诊断消息。
     */
    private final String debugMessage;

    /**
     * 仅供反序列化使用。
     */
    DefaultCommonResult()
    {
        this.code = null;
        this.message = null;
        this.debugMessage = null;
    }

    /**
     * 使用{@link ResultCode}中的{@link ResultCode#getCode() code}构建结果对象，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，将会设置相应的消息。
     *
     * @param resultCode 结果编码
     */
    DefaultCommonResult(final TResultCode resultCode)
    {
        this(resultCode, null, null);
    }

    /**
     * 使用{@link ResultCode}中的{@link ResultCode#getCode() code}和自定义的消息构建结果对象。
     *
     * @param resultCode   结果编码。
     * @param message      自定义用户可读消息，为{@code null}时如果{@link TResultCode}实现了{@link WithMessage}接口，则使用{@code resultCode.getMessage()}作为用户消息。
     * @param debugMessage 自定义调试诊断消息，为{@code null}时如果{@link TResultCode}实现了{@link WithMessage}接口，则使用{@code resultCode.getDebugMessage()}作为调试消息。
     */
    DefaultCommonResult(final TResultCode resultCode, @Nullable final String message, @Nullable final String debugMessage)
    {
        if (resultCode == null)
        {
            throw new IllegalArgumentException("\"resultCode\" cannot be null.");
        }

        final WithMessage messageInResultCode = resultCode instanceof WithMessage ? ((WithMessage)resultCode) : null;

        this.code = resultCode;
        this.message = message != null ? message : messageInResultCode != null ? messageInResultCode.getMessage() : null;
        this.debugMessage = debugMessage != null ? debugMessage : messageInResultCode != null ? messageInResultCode.getDebugMessage() : null;
    }

    @Override
    public @NotNull TResultCode getResultCode()
    {
        return this.code;
    }

    @Override
    public @NotNull String getMessage()
    {
        return this.message;
    }

    @Override
    public @NotNull String getDebugMessage()
    {
        return this.debugMessage;
    }
}
