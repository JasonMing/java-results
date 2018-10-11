package com.github.jasonnming.results.page;

import java.io.Serializable;
import java.util.Objects;

/**
 * 分页请求，包含{@link #page 期望获取页码}以及{@link #pageSize 分页每页大小}。
 * 重写了{@link #equals(Object)}和{@link #hashCode()}，可用于作为Map的key。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-07-20)
 */
class DefaultPage implements Page, Serializable
{
    private static final long serialVersionUID = 0x2c29589ac7ddc6c4L;

    private final Long page;

    private final Long pageSize;

    /**
     * 仅为反序列化使用。
     */
    DefaultPage()
    {
        this.page = null;
        this.pageSize = null;
    }

    DefaultPage(final long page, final long pageSize)
    {
        this.page = page;
        this.pageSize = pageSize;
    }

    @Override
    public long getPage()
    {
        //noinspection ConstantConditions - Let it throw NPE when it is null
        return this.page;
    }

    @Override
    public long getPageSize()
    {
        //noinspection ConstantConditions - Let it throw NPE when it is null
        return this.pageSize;
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
        return Objects.equals(this.page, that.page)
                && Objects.equals(this.pageSize, that.pageSize);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.page, this.pageSize);
    }
}
