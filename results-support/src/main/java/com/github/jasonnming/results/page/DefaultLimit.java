package com.github.jasonnming.results.page;

import java.util.Objects;

/**
 * 数据库返回限制。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-07-20)
 */
class DefaultLimit implements Limit
{
    private final long offset;

    private final long limit;

    DefaultLimit(final long offset, final long limit)
    {
        this.offset = offset;
        this.limit = limit;
    }

    public long getOffset()
    {
        return this.offset;
    }

    public long getLimit()
    {
        return this.limit;
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
        final DefaultLimit that = (DefaultLimit)o;
        return this.offset == that.offset
                && this.limit == that.limit;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.offset, this.limit);
    }
}
