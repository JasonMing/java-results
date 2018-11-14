package com.github.jasonnming.results.page.support;

import java.io.Serializable;
import java.util.Objects;

import com.github.jasonnming.results.page.FixedPage;
import com.github.jasonnming.results.page.Page;
import com.github.jasonnming.results.page.RollingPage;

/**
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-10-24)
 */
class DefaultPage implements Page, RollingPage, FixedPage, Serializable
{
    private static final long serialVersionUID = 0x2c29589ac7ddc6c4L;

    private final Long currentPage;

    private final Long pageSize;

    private final Boolean hasNextPage;

    private final Long totalPage;

    private final Long totalSize;

    /**
     * 仅为反序列化使用。
     */
    DefaultPage()
    {
        this.currentPage = null;
        this.pageSize = null;
        this.hasNextPage = null;
        this.totalPage = null;
        this.totalSize = null;
    }

    DefaultPage(final Long currentPage, final Long pageSize, final Boolean hasNextPage, final Long totalPage, final Long totalSize)
    {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.hasNextPage = hasNextPage;
        this.totalPage = totalPage;
        this.totalSize = totalSize;
    }

    @Override
    public Long getCurrentPage()
    {
        return this.currentPage;
    }

    @Override
    public Long getPageSize()
    {
        return this.pageSize;
    }

    @Override
    public Boolean hasNextPage()
    {
        return this.hasNextPage;
    }

    @Override
    public Long getTotalPage()
    {
        return this.totalPage;
    }

    @Override
    public Long getTotalSize()
    {
        return this.totalSize;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }
        final DefaultPage that = (DefaultPage)o;
        return Objects.equals(this.currentPage, that.currentPage)
                && Objects.equals(this.pageSize, that.pageSize)
                && Objects.equals(this.hasNextPage, that.hasNextPage)
                && Objects.equals(this.totalPage, that.totalPage)
                && Objects.equals(this.totalSize, that.totalSize);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.currentPage, this.pageSize, this.hasNextPage, this.totalPage, this.totalSize);
    }
}
