package com.github.jasonnming.results.page;

import org.apiguardian.api.API;
import org.jetbrains.annotations.Contract;

/**
 * {@link Page}与{@link Limit}的转换工具。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-07-20)
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.0.0")
public final class Pages
{
    private static final long FIRST_PAGE_INDEX_VALUE = 1L;

    private static final long DEFAULT_PAGE_SIZE_VALUE = 20L;

    /**
     * 首页页码。
     *
     * @implNote 通过 {@code Long.valueOf()} 调用，避免DEFAULT_PAGE_SIZE成为编译期常量。
     */
    @SuppressWarnings("UnnecessaryUnboxing")
    public static final long FIRST_PAGE_INDEX = Long.valueOf(FIRST_PAGE_INDEX_VALUE).longValue();

    /**
     * 默认分页每页页数。
     *
     * @implNote 通过 {@code Long.valueOf()} 调用，避免DEFAULT_PAGE_SIZE成为编译期常量。
     */
    @SuppressWarnings("UnnecessaryUnboxing")
    public static final long DEFAULT_PAGE_SIZE = Long.valueOf(DEFAULT_PAGE_SIZE_VALUE).longValue();

    private Pages() {}

    /**
     * 根据请求页码及每页数据量创建分页请求对象。
     *
     * @param page     请求页码
     * @param pageSize 每页数据量
     *
     * @return {@link Page}
     */
    public static Page page(final long page, final long pageSize)
    {
        return new DefaultPage(page, pageSize);
    }

    /**
     * 返回第一页的分页对象。
     *
     * @param pageSize 每页数据量
     *
     * @return {@link Page}
     */
    public static Page firstPage(final long pageSize)
    {
        return new DefaultPage(FIRST_PAGE_INDEX_VALUE, pageSize);
    }

    /**
     * 返回第一页的分页对象，分页大小为默认的{@value #DEFAULT_PAGE_SIZE_VALUE}。
     *
     * @return {@link Page}
     */
    public static Page firstPage()
    {
        return new DefaultPage(FIRST_PAGE_INDEX_VALUE, DEFAULT_PAGE_SIZE_VALUE);
    }

    /**
     * 根据偏移量及数据量创建Limit对象。
     *
     * @param offset 偏移量
     * @param limit  数据量
     *
     * @return {@link Limit}
     */
    public static Limit limit(final long offset, final long limit)
    {
        return new DefaultLimit(offset, limit);
    }

    /**
     * 将{@link Page}转换为{@link Limit}，如果{@link Limit}内的值不符合规范，将会抛出{@link IllegalArgumentException}，
     * 如需要创建不合规的{@link Limit}对象，请使用{@link #limit(long, long)}。
     * <p>其中：
     * <p>{@code offset = max(0, page.page - 1) * page.pageSize}
     * <p>{@code limit  = page.pageSize}
     *
     * @param page {@link Page 分页}对象
     *
     * @return {@link Limit}对象，当{@code page}为{@code null}时返回{@code null}
     *
     * @throws IllegalArgumentException 当 {@link Page#getPage()} < {@value #FIRST_PAGE_INDEX_VALUE} 或 {@link Page#getPageSize()} < 0 时
     */
    @Contract("null -> null")
    public static Limit toLimit(final Page page)
    {
        if (page == null)
        {
            return null;
        }

        if (page.getPage() < FIRST_PAGE_INDEX_VALUE)
        {
            throw new IllegalArgumentException("page cannot less than " + FIRST_PAGE_INDEX_VALUE + ".");
        }
        if (page.getPageSize() < 0)
        {
            throw new IllegalArgumentException("pageSize cannot less than 0.");
        }

        final long offset = Math.max(0, page.getPage() - FIRST_PAGE_INDEX_VALUE) * page.getPageSize();
        final long limit = page.getPageSize();

        return new DefaultLimit(offset, limit);
    }

    /**
     * 将{@link Limit}转换为{@link Page}，如果{@link Limit}内的值不符合规范，将会抛出{@link IllegalArgumentException}，
     * 如需要创建不合规的{@link Page}对象，请使用{@link #page(long, long)}。
     * <p>
     * 其中：
     * <br>{@code page = limit.offset / limit.limit + 1}
     * <br>{@code pageSize  = limit.limit}
     *
     * @param limit {@link Limit}对象
     *
     * @return {@link Page 分页}对象，当{@code limit}为{@code null}时返回{@code null}
     *
     * @throws IllegalArgumentException 当 {@link Limit#getOffset()} < 0 或 {@link Limit#getLimit()} < 0 时
     */
    @Contract("null -> null")
    public static Page toPage(final Limit limit)
    {
        if (limit == null)
        {
            return null;
        }

        if (limit.getOffset() < 0)
        {
            throw new IllegalArgumentException("offset cannot less than 0.");
        }
        if (limit.getLimit() < 0)
        {
            throw new IllegalArgumentException("limit cannot less than 0.");
        }

        final long page = limit.getOffset() / limit.getLimit() + 1;
        final long pageSize = limit.getLimit();

        return new DefaultPage(page, pageSize);
    }
}
