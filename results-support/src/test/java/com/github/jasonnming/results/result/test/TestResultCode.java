package com.github.jasonnming.results.result.test;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.basic.WithMessage;

/**
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-11-14)
 */
public enum TestResultCode implements ResultCode, WithMessage
{
    SUCCESS("成功"),

    ILLEGAL_ARGUMENT("参数错误"),

    UNKNOWN_ERROR("未知错误"),

    /* End Enum */;

    private final String message;

    private final String debugMessage;

    TestResultCode(final String message)
    {
        this(message, message);
    }

    TestResultCode(final String message, final String debugMessage)
    {
        this.message = message;
        this.debugMessage = debugMessage;
    }

    @Override
    public String getCode()
    {
        return this.name();
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }

    @Override
    public String getDebugMessage()
    {
        return this.debugMessage;
    }
}
