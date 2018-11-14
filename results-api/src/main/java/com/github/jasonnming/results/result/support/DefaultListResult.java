package com.github.jasonnming.results.result.support;

import java.util.List;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.basic.WithMessage;
import com.github.jasonnming.results.result.generic.ListResult;

/**
 * 列表结果。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
class DefaultListResult<TResultCode extends ResultCode, TElement>
        extends DefaultCommonResult<TResultCode>
        implements ListResult<TResultCode, TElement>
{
    private static final long serialVersionUID = 0xac5be3f3dfd5af1dL;

    private final List<TElement> data;

    /**
     * 仅为反序列化使用。
     */
    DefaultListResult()
    {
        this.data = null;
    }

    /**
     * 使用{@link ResultCode}构建结果对象。
     *
     * @param resultCode 结果编码。
     */
    DefaultListResult(final TResultCode resultCode)
    {
        super(resultCode);
        this.data = null;
    }

    /**
     * 使用{@link ResultCode}和自定义的消息构建结果对象
     *
     * @param resultCode   结果编码。
     * @param message      自定义用户可读消息，为{@code null}时如果{@link TResultCode}实现了{@link WithMessage}接口，则使用{@code resultCode.getMessage()}作为用户消息。
     * @param debugMessage 自定义调试诊断消息，为{@code null}时如果{@link TResultCode}实现了{@link WithMessage}接口，则使用{@code resultCode.getDebugMessage()}作为调试消息。
     */
    DefaultListResult(final TResultCode resultCode, final String message, final String debugMessage)
    {
        super(resultCode, message, debugMessage);
        this.data = null;
    }

    /**
     * 使用{@link ResultCode}和自定义的消息和{@code data}构建结果对象。
     *
     * @param resultCode   结果编码。
     * @param message      自定义用户可读消息，为{@code null}时如果{@link TResultCode}实现了{@link WithMessage}接口，则使用{@code resultCode.getMessage()}作为用户消息。
     * @param debugMessage 自定义调试诊断消息，为{@code null}时如果{@link TResultCode}实现了{@link WithMessage}接口，则使用{@code resultCode.getDebugMessage()}作为调试消息。
     * @param data         结果返回的列表。
     */
    DefaultListResult(final TResultCode resultCode, final String message, final String debugMessage, final List<TElement> data)
    {
        super(resultCode, message, debugMessage);
        this.data = data;
    }

    @Override
    public List<TElement> getData()
    {
        return this.data;
    }
}
