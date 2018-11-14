package com.github.jasonnming.results.page.support;

import java.util.Collection;
import java.util.List;

import org.apiguardian.api.API;

import com.github.jasonnming.results.internal.InternalUtils;
import com.github.jasonnming.results.page.FixedPage;
import com.github.jasonnming.results.page.Page;
import com.github.jasonnming.results.page.PagedList;
import com.github.jasonnming.results.page.RollingPage;
import com.github.jasonnming.results.page.builder.PageBuilder;

/**
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-10-24)
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
     * 返回第一页的分页对象。
     *
     * @param pageSize 每页数据量
     *
     * @return 作为分页请求的{@link Page}对象。
     */
    public static Page firstPage(final long pageSize)
    {
        return create(FIRST_PAGE_INDEX, pageSize);
    }

    /**
     * 返回第一页的分页对象，分页大小为默认的{@value #DEFAULT_PAGE_SIZE_VALUE}。
     *
     * @return 作为分页请求的{@link Page}对象。
     */
    public static Page firstDefaultSizePage()
    {
        return create(FIRST_PAGE_INDEX, DEFAULT_PAGE_SIZE);
    }

    public static Page create(final long currentPage, final long pageSize)
    {
        return new DefaultPage(currentPage, pageSize, null, null, null);
    }

    public static RollingPage create(final long currentPage, final long pageSize, final boolean hasNextPage)
    {
        return new DefaultPage(currentPage, pageSize, hasNextPage, null, null);
    }

    public static FixedPage create(final long currentPage, final long pageSize, final long totalSize)
    {
        final long totalPage = totalSize / pageSize + (totalSize % pageSize == 0 ? 0 : 1);

        return create(currentPage, pageSize, totalPage, totalSize);
    }

    public static FixedPage create(final long currentPage, final long pageSize, final long totalPage, final long totalSize)
    {
        final boolean hasNextPage = currentPage < totalPage;

        return new DefaultPage(totalPage, totalSize, hasNextPage, currentPage, pageSize);
    }

    public static <TPage extends Page, T> PagedList<TPage, T> pagedList(final T[] data, final TPage pageInfo)
    {
        return new DefaultPagedList<>(InternalUtils.toList(data), pageInfo);
    }

    public static <TPage extends Page, T> PagedList<TPage, T> pagedList(final List<T> data, final TPage pageInfo)
    {
        return new DefaultPagedList<>(data, pageInfo);
    }

    public static <TPage extends Page, T> PagedList<TPage, T> pagedList(final Collection<T> data, final TPage pageInfo)
    {
        return new DefaultPagedList<>(InternalUtils.toList(data), pageInfo);
    }

    public static <TPage extends Page, T> PagedList<TPage, T> pagedList(final Iterable<T> data, final TPage pageInfo)
    {
        return new DefaultPagedList<>(InternalUtils.toList(data), pageInfo);
    }

    public static Page copy(final Page page)
    {
        return new DefaultPage(
                page.getCurrentPage(),
                page.getPageSize(),
                page instanceof RollingPage ? ((RollingPage)page).hasNextPage() : null,
                page instanceof FixedPage ? ((FixedPage)page).getTotalPage() : null,
                page instanceof FixedPage ? ((FixedPage)page).getTotalSize() : null);
    }

    public static RollingPage copy(final Page page, final boolean hasNextPage)
    {
        return new DefaultPage(
                page.getCurrentPage(),
                page.getPageSize(),
                hasNextPage,
                page instanceof FixedPage ? ((FixedPage)page).getTotalPage() : null,
                page instanceof FixedPage ? ((FixedPage)page).getTotalSize() : null);
    }

    public static FixedPage copy(final Page page, final long totalSize)
    {
        ensureRequestPageValid(page);

        final long currentPage = page.getCurrentPage();
        final long pageSize = page.getPageSize();
        final long totalPage = totalSize / pageSize + (totalSize % pageSize == 0 ? 0 : 1);
        final boolean hasNextPage = currentPage < totalPage;

        return new DefaultPage(
                currentPage,
                pageSize,
                hasNextPage,
                totalPage,
                totalSize);
    }

    public static FixedPage copy(final Page page, final long totalPage, final long totalSize)
    {
        ensureRequestPageValid(page);

        return new DefaultPage(
                page.getCurrentPage(),
                page.getPageSize(),
                page.getCurrentPage() < totalPage,
                totalPage,
                totalSize);
    }

    private static void ensureRequestPageValid(final Page requestPage)
    {
        if (requestPage.getCurrentPage() == null)
        {
            throw new IllegalArgumentException("[page] in requestPage cannot be null.");
        }
        if (requestPage.getPageSize() == null)
        {
            throw new IllegalArgumentException("[pageSize] in requestPage cannot be null.");
        }
    }

    // region: Builders

    public static PageBuilder<Page> builder()
    {
        throw new UnsupportedOperationException("Not Implemented");
    }

    // region: Builder Implements

    private static class PageBuilderImpl<TPage extends Page> implements PageBuilder<TPage>
    {
        private Long currentPage = null;

        private Long pageSize = null;

        private Boolean hasNextPage = null;

        private Long totalPage = null;

        private Long totalSize = null;

        private boolean restricted = true;

        @Override
        public PageBuilder<TPage> from(final TPage page)
        {
            if (page == null)
            {
                throw new IllegalArgumentException("Parameter [page] cannot be null.");
            }

            this.currentPage = page.getCurrentPage();
            this.pageSize = page.getPageSize();

            if (page instanceof RollingPage)
            {
                this.hasNextPage = ((RollingPage)page).hasNextPage();
            }
            if (page instanceof FixedPage)
            {
                this.totalPage = ((FixedPage)page).getTotalPage();
                this.totalSize = ((FixedPage)page).getTotalSize();
            }

            return null;
        }

        @SuppressWarnings("unchecked")
        @Override
        public PageBuilder<Page> page(final long currentPage, final long pageSize)
        {
            if (currentPage < 1 || pageSize < 1)
            {
                throw new IllegalArgumentException("Property [currentPage] and [pageSize] must start from 1.");
            }

            this.currentPage = currentPage;
            this.pageSize = pageSize;
            return (PageBuilder<Page>)this;
        }

        @SuppressWarnings("unchecked")
        @Override
        public PageBuilder<RollingPage> hasNextPage(final boolean hasNextPage)
        {
            this.hasNextPage = hasNextPage;
            return (PageBuilder<RollingPage>)this;
        }

        @SuppressWarnings("unchecked")
        @Override
        public PageBuilder<FixedPage> totalPage(final long totalPage)
        {
            if (totalPage < 0)
            {
                throw new IllegalArgumentException("Property [totalPage] must start from 0.");
            }

            this.totalPage = totalPage;
            return (PageBuilder<FixedPage>)this;
        }

        @SuppressWarnings("unchecked")
        @Override
        public PageBuilder<FixedPage> totalSize(final long totalSize)
        {
            if (totalSize < 0)
            {
                throw new IllegalArgumentException("Property [totalSize] must start from 0.");
            }
            this.totalSize = totalSize;
            return (PageBuilder<FixedPage>)this;
        }

        @Override
        public PageBuilder<TPage> restricted(final boolean restricted)
        {
            this.restricted = restricted;
            return this;
        }

        private void check()
        {
            // Check unexpected cases
            if ((this.currentPage == null) ^ (this.pageSize == null))
            {
                throw new IllegalArgumentException("Property [currentPage] and [pageSize] must be null/not-null at the same time.");
            }

            // Try calculate totalPage
            if (this.pageSize != null && this.totalSize != null)
            {
                if (this.totalPage == null)
                {
                    // Calculates totalPage via pageSize and totalSize.
                    this.totalPage = (this.totalSize / this.pageSize) + Math.min(0, this.totalSize % this.pageSize);
                } else
                {
                    if (this.restricted)
                    {
                        // Prevent totalPage, pageSize and totalSize are both set.
                        throw new IllegalArgumentException(
                                "Property [totalPage] can be inferred from [pageSize] and [totalSize], set [restricted] to false to force apply it.");
                    }
                }
            }

            // Try calculate hasNextPage
            if (this.currentPage != null && this.totalPage != null)
            {
                if (this.hasNextPage == null)
                {
                    // Calculates hasNextPage via currentPage and totalPage.
                    this.hasNextPage = this.currentPage < this.totalPage;
                } else
                {
                    if (this.restricted)
                    {
                        // Prevent hasNextPage, currentPage and totalPage are both set.
                        throw new IllegalArgumentException(
                                "Property [hasNextPage] can be inferred from [currentPage] and [totalPage], set [restricted] to false to force apply it.");
                    }
                }
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public TPage build()
        {
            this.check();
            return (TPage)new DefaultPage(this.currentPage, this.pageSize, this.hasNextPage, this.totalPage, this.totalSize);
        }
    }

    // endregion: Builder Implements

    // endregion: Builders
}

