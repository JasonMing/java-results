package com.github.jasonnming.results.result.support;

import java.io.Serializable;
import java.util.Objects;

import org.jetbrains.annotations.Nullable;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.basic.WithMessage;

/**
 * 通用错误码。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-04-19)
 */
class DefaultResultCode implements ResultCode, WithMessage, Serializable
{
    private static final long serialVersionUID = 0xfd84f5dd50c5fa50L;

    private final String code;

    private final String message;

    private final String debugMessage;

    DefaultResultCode(final String code)
    {
        this(code, null, null);
    }

    DefaultResultCode(final String code, final String message)
    {
        this(code, message, message);
    }

    DefaultResultCode(final String code, final String message, final String debugMessage)
    {
        this.code = code;
        this.message = message;
        this.debugMessage = debugMessage;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (o instanceof ResultCode)
        {
            return Objects.equals(this.code, ((ResultCode)o).getCode());
        } else if (o instanceof String)
        {
            return Objects.equals(this.code, o);
        } else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return this.code == null ? 0 : this.code.hashCode();
    }

    @Override
    public String getCode()
    {
        return this.code;
    }

    @Override
    public @Nullable String getMessage()
    {
        return this.message;
    }

    @Override
    public @Nullable String getDebugMessage()
    {
        return this.debugMessage;
    }
}
