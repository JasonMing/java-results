package com.github.jasonnming.results.result.support;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.github.jasonnming.results.page.Page;
import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.basic.WithMessage;
import com.github.jasonnming.results.result.generic.PagedListResult;

/**
 * 内容为{@link List}且带有分页信息结果。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
class DefaultPagedListResult<TResultCode extends ResultCode, TPage extends Page, TElement>
        extends DefaultListResult<TResultCode, TElement>
        implements PagedListResult<TResultCode, TPage, TElement>
{
    private static final long serialVersionUID = 0xf84bf86aa2bde32L;

    private final TPage page;

    /**
     * 仅为反序列化使用。
     */
    DefaultPagedListResult()
    {
        this.page = null;
    }

    /**
     * 使用{@link ResultCode}构建结果对象。
     *
     * @param resultCode 结果编码。
     */
    DefaultPagedListResult(final TResultCode resultCode)
    {
        super(resultCode);
        this.page = null;
    }

    /**
     * 使用{@link ResultCode}和自定义的消息构建结果对象。
     *
     * @param resultCode   结果编码。
     * @param message      自定义用户可读消息，为{@code null}时如果{@link TResultCode}实现了{@link WithMessage}接口，则使用{@code resultCode.getMessage()}作为用户消息。
     * @param debugMessage 自定义调试诊断消息，为{@code null}时如果{@link TResultCode}实现了{@link WithMessage}接口，则使用{@code resultCode.getDebugMessage()}作为调试消息。
     */
    DefaultPagedListResult(final TResultCode resultCode, final String message, final String debugMessage)
    {
        super(resultCode, message, debugMessage);
        this.page = null;
    }

    /**
     * 使用{@link ResultCode}和自定义的消息和{@code data}构建结果对象。
     *
     * @param resultCode   结果编码。
     * @param message      自定义用户可读消息，为{@code null}时如果{@link TResultCode}实现了{@link WithMessage}接口，则使用{@code resultCode.getMessage()}作为用户消息。
     * @param debugMessage 自定义调试诊断消息，为{@code null}时如果{@link TResultCode}实现了{@link WithMessage}接口，则使用{@code resultCode.getDebugMessage()}作为调试消息。
     * @param data         结果返回的列表。
     * @param page         分页信息。
     */
    DefaultPagedListResult(final TResultCode resultCode, final String message, final String debugMessage, final List<TElement> data, final TPage page)
    {
        super(resultCode, message, debugMessage, data);
        this.page = page;
    }

    @Override
    public TPage getPage()
    {
        return this.page;
    }
}
