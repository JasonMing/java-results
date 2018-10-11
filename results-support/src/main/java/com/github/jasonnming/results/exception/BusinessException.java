package com.github.jasonnming.results.exception;

import org.apiguardian.api.API;
import org.jetbrains.annotations.Nullable;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.basic.WithMessage;
import com.github.jasonnming.results.result.support.Results;

/**
 * 包含错误代码及错误消息的业务异常。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-09-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public class BusinessException extends RuntimeException
{
    private static final long serialVersionUID = 0x55e59d806dec3766L;

    /**
     * 以字符串表现的结果代码。
     */
    private final String code;

    /**
     * 代码所对应的用户可读的提示消息。
     */
    private final String alertMessage;

    /**
     * 代码所对应的调试诊断消息，此消息不可展现给用户。
     */
    private final String debugMessage;

    public BusinessException(final String code, final String alertMessage)
    {
        this(code, alertMessage, alertMessage);
    }

    public BusinessException(final String code, final String alertMessage, @Nullable final String debugMessage)
    {
        super(formatExceptionMessage(code, alertMessage, debugMessage));

        this.code = code;
        this.alertMessage = alertMessage;
        this.debugMessage = debugMessage != null ? debugMessage : alertMessage;
    }

    public BusinessException(final String code, final String alertMessage, @Nullable final String debugMessage, final Throwable cause)
    {
        super(formatExceptionMessage(code, alertMessage, debugMessage), cause);
        this.code = code;
        this.alertMessage = alertMessage;
        this.debugMessage = debugMessage != null ? debugMessage : alertMessage;
    }

    public BusinessException(final ResultCode resultCode)
    {
        this(resultCode, null, (String)null);
    }

    public BusinessException(final ResultCode resultCode, final String alertMessage)
    {
        this(resultCode, alertMessage, alertMessage);
    }

    public BusinessException(final ResultCode resultCode, final String alertMessage, final String debugMessage)
    {
        super(formatExceptionMessage(resultCode.getCode(), alertMessage, debugMessage));

        final WithMessage message = (resultCode instanceof WithMessage) ? (WithMessage)resultCode : Results.EMPTY_MESSAGE;

        this.code = resultCode.getCode();
        this.alertMessage = (alertMessage != null) ? alertMessage : message.getMessage();
        this.debugMessage = (debugMessage != null) ? debugMessage : message.getDebugMessage();
    }

    public BusinessException(final ResultCode resultCode, final Throwable cause)
    {
        this(resultCode, null, null, cause);
    }

    public BusinessException(final ResultCode resultCode, final String alertMessage, final Throwable cause)
    {
        this(resultCode, alertMessage, alertMessage);
    }

    public BusinessException(final ResultCode resultCode, final String alertMessage, final String debugMessage, final Throwable cause)
    {
        super(formatExceptionMessage(resultCode.getCode(), alertMessage, debugMessage), cause);

        final WithMessage message = (resultCode instanceof WithMessage) ? (WithMessage)resultCode : Results.EMPTY_MESSAGE;

        this.code = resultCode.getCode();
        this.alertMessage = (alertMessage != null) ? alertMessage : message.getMessage();
        this.debugMessage = (debugMessage != null) ? debugMessage : message.getDebugMessage();
    }

    private static String formatExceptionMessage(final String code, final String alertMessage, @Nullable final String debugMessage)
    {
        return code + ":\"" + (debugMessage != null ? debugMessage : alertMessage) + "\"";
    }

    public String getCode()
    {
        return this.code;
    }

    public String getAlertMessage()
    {
        return this.alertMessage;
    }

    public String getDebugMessage()
    {
        return this.debugMessage;
    }
}
