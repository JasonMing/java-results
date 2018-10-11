package com.github.jasonnming.results.result.support;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.generic.PagedListResult;

/**
 * 内容为{@link List}且带有分页信息结果。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
class DefaultPagedListResult<TResultCode extends ResultCode, TElement>
        extends DefaultListResult<TResultCode, TElement>
        implements PagedListResult<TResultCode, TElement>
{
    private static final long serialVersionUID = 0xf84bf86aa2bde32L;

    private final Long page;

    private final Long pageSize;

    private final Boolean hasNextPage;

    private final Long totalPage;

    private final Long totalSize;

    /**
     * 仅为反序列化使用。
     */
    DefaultPagedListResult()
    {
        this.page = null;
        this.pageSize = null;
        this.hasNextPage = null;
        this.totalPage = null;
        this.totalSize = null;
    }
    /**
     * 使用{@link ResultCode}构建结果对象
     *
     * @param resultCode 结果编码
     */
    DefaultPagedListResult(final TResultCode resultCode)
    {
        super(resultCode);
        this.page = null;
        this.pageSize = null;
        this.hasNextPage = null;
        this.totalPage = null;
        this.totalSize = null;
    }

    /**
     * 使用{@link ResultCode}和自定义的消息构建结果对象
     *
     * @param resultCode 结果编码
     * @param message    自定义消息，为{@code null}时使用{@link ResultCode#getMessage()}作为消息
     */
    DefaultPagedListResult(final TResultCode resultCode, final String message, final String debugMessage)
    {
        super(resultCode, message, debugMessage);
        this.page = null;
        this.pageSize = null;
        this.hasNextPage = null;
        this.totalPage = null;
        this.totalSize = null;
    }

    /**
     * 使用{@link ResultCode}和自定义的消息和{@code data}构建结果对象
     *
     * @param resultCode  结果编码
     * @param message     自定义消息，为{@code null}时使用{@link ResultCode#getMessage()}作为消息
     * @param data        结果返回的列表
     * @param page        当前页数，从1开始，非负
     * @param pageSize    每页记录条数
     * @param hasNextPage 是否存在下一页
     * @param totalPage   总页数，从1开始，非负
     * @param totalSize   总记录数
     */
    DefaultPagedListResult(final TResultCode resultCode, final String message, final String debugMessage, final List<TElement> data,
                           final Long page, final Long pageSize, final Boolean hasNextPage, final Long totalPage, final Long totalSize)
    {
        super(resultCode, message, debugMessage, data);
        this.page = page;
        this.pageSize = pageSize;
        this.hasNextPage = hasNextPage;
        this.totalPage = totalPage;
        this.totalSize = totalSize;
    }

    @Override
    public boolean hasPage()
    {
        return this.page != null;
    }

    @Override
    public long getPage()
    {
        if (!this.hasPage())
        {
            throw new IllegalStateException("page not exist.");
        }
        return this.page;
    }

    @Nullable
    @Override
    public Long getPageOrNull()
    {
        return this.page;
    }

    @Override
    public boolean hasPageSize()
    {
        return this.pageSize != null;
    }

    @Override
    public long getPageSize()
    {
        if (!this.hasPageSize())
        {
            throw new IllegalStateException("pageSize not exist.");
        }
        return this.pageSize;
    }

    @Nullable
    @Override
    public Long getPageSizeOrNull()
    {
        return this.pageSize;
    }

    @Override
    public boolean isPredictable()
    {
        return this.hasNextPage != null;
    }

    @Override
    public boolean hasNextPage()
    {
        if (!this.isPredictable())
        {
            throw new IllegalStateException("nextPage is unpredictable.");
        }
        return this.hasNextPage;
    }

    @Override
    public boolean hasTotalPage()
    {
        return this.totalPage != null;
    }

    @Override
    public long getTotalPage()
    {
        if (!this.hasPageSize())
        {
            throw new IllegalStateException("totalPage not exist.");
        }
        return this.totalPage;
    }

    @Nullable
    @Override
    public Long getTotalPageOrNull()
    {
        return this.totalPage;
    }

    @Override
    public boolean hasTotalSize()
    {
        return this.totalSize != null;
    }

    @Override
    public long getTotalSize()
    {
        if (!this.hasTotalSize())
        {
            throw new IllegalStateException("totalSize not exist.");
        }
        return this.totalSize;
    }

    @Nullable
    @Override
    public Long getTotalSizeOrNull()
    {
        return this.totalSize;
    }
}
