package com.github.jasonnming.results.result.support;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.generic.SingleResult;

/**
 * 普通带有单一对象结果。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
class DefaultSingleResult<TResultCode extends ResultCode, TData>
        extends DefaultCommonResult<TResultCode>
        implements SingleResult<TResultCode, TData>
{
    private static final long serialVersionUID = 0xef16301ba43e7db5L;

    private final TData data;

    /**
     * 仅为反序列化使用。
     */
    private DefaultSingleResult()
    {
        this.data = null;
    }

    /**
     * 使用{@link ResultCode}构建结果对象
     *
     * @param resultCode 结果编码
     */
    DefaultSingleResult(final TResultCode resultCode)
    {
        super(resultCode);
        this.data = null;
    }

    /**
     * 使用{@link ResultCode}和自定义的消息和{@code data}构建结果对象
     *
     * @param resultCode 结果编码
     * @param message    自定义消息，为{@code null}时使用{@link ResultCode#getMessage()}作为消息
     */
    DefaultSingleResult(final TResultCode resultCode, final String message, final String debugMessage)
    {
        super(resultCode, message, debugMessage);
        this.data = null;
    }

    /**
     * 使用{@link ResultCode}和自定义的消息和{@code data}构建结果对象
     *
     * @param resultCode 结果编码
     * @param message    自定义消息，为{@code null}时使用{@link ResultCode#getMessage()}作为消息
     */
    DefaultSingleResult(final TResultCode resultCode, final String message, final String debugMessage, final TData data)
    {
        super(resultCode, message, debugMessage);
        this.data = data;
    }

    @Override
    public TData getData()
    {
        return this.data;
    }
}
