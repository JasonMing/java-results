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

    private final Long number;

    private final Long size;

    private final Boolean hasNextPage;

    private final Long totalPage;

    private final Long totalSize;

    /**
     * 仅为反序列化使用。
     */
    DefaultPage()
    {
        this.number = null;
        this.size = null;
        this.hasNextPage = null;
        this.totalPage = null;
        this.totalSize = null;
    }

    DefaultPage(final Long number, final Long size, final Boolean hasNextPage, final Long totalPage, final Long totalSize)
    {
        this.number = number;
        this.size = size;
        this.hasNextPage = hasNextPage;
        this.totalPage = totalPage;
        this.totalSize = totalSize;
    }

    @Override
    public Long getNumber()
    {
        return this.number;
    }

    @Override
    public Long getSize()
    {
        return this.size;
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
        return Objects.equals(this.number, that.number)
                && Objects.equals(this.size, that.size)
                && Objects.equals(this.hasNextPage, that.hasNextPage)
                && Objects.equals(this.totalPage, that.totalPage)
                && Objects.equals(this.totalSize, that.totalSize);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.number, this.size, this.hasNextPage, this.totalPage, this.totalSize);
    }
}
