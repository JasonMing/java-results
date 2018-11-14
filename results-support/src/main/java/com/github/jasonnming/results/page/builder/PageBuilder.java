package com.github.jasonnming.results.page.builder;

import com.github.jasonnming.results.page.FixedPage;
import com.github.jasonnming.results.page.Page;
import com.github.jasonnming.results.page.RollingPage;
import com.github.jasonnming.results.result.builder.PagedListResultBuilder;

/**
 * 分页对象构建器。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-11-08)
 */
public interface PageBuilder<TPage extends Page>
{
    /**
     * 从{@code page}复制相关信息到当前构建器。
     *
     * @param page
     *
     * @return
     */
    PageBuilder<TPage> from(TPage page);

    /**
     * 设置列表结果的分页信息。
     *
     * @param currentPage 当前页码，必须大于0。
     * @param pageSize    当前页大小，必须大于0（非实际数据大小）。
     *
     * @return {@link PagedListResultBuilder}。
     *
     * @throws IllegalArgumentException 当{@code currentPage}或{@code pageSize}小于1。
     */
    PageBuilder<Page> page(long currentPage, long pageSize);

    /**
     * 设置列表结果是否存在下一页，在没有设置过此属性时将会通过假面表达式在{@link #build()}时计算得出：
     * <pre>{@code hasNextPage = page < totalPage}</pre>
     * 如任意一个相关属性（ {@code page} 或 {@code totalPage} ）未被设置，上述过程将不会执行。
     * <p>
     * 注：此属性通常用于总长度未知的流式列表中，以便告知调用方是否还存在下一页。
     *
     * @param hasNextPage 是否存在下一页。
     *
     * @return 当前 {@link PageBuilder}。
     */
    PageBuilder<RollingPage> hasNextPage(boolean hasNextPage);

    /**
     * 设置列表结果的总页数，在没有设置过此属性时将会通过下面表达式在{@link #build()}时计算得出：
     * <pre>{@code totalPage = (totalSize / pageSize) + min(0, totalSize % pageSize)}</pre>
     * 如任意一个相关属性（ {@code totalSize} 或 {@code pageSize} ）未被设置，上述过程将不会执行。
     *
     * @param totalPage 总页数，必须大于或等于0（0表示查询的数据集没有任何数据）。
     *
     * @return 当前 {@link PageBuilder}。
     *
     * @throws IllegalArgumentException 当{@code totalPage}小于0。
     */
    PageBuilder<FixedPage> totalPage(long totalPage);

    /**
     * 设置列表结果的总数据大小（非当前分页实际数据大小）。
     *
     * @param totalSize 当前页大小，必须大于或等于0（0表示查询的数据集没有任何数据）。
     *
     * @return 当前 {@link PageBuilder}。
     *
     * @throws IllegalArgumentException {@code totalSize}小于0。
     */
    PageBuilder<FixedPage> totalSize(long totalSize);

    /**
     * （可选）指示在已经设置{@link #page pageSize}及{@link #totalSize}的情况下是否允许设置{@link #totalPage}，默认为{@code true}。
     *
     * @param restricted 在已经设置{@link #page pageSize}及{@link #totalSize}的情况下是否允许设置{@link #totalPage}。
     *
     * @return 当前 {@link PageBuilder}。
     *
     * @apiNote 此标识设置后只会影响最终 {@link #build()}方法中执行的逻辑，不会影响其它的设置方法。
     */
    PageBuilder<TPage> restricted(boolean restricted);

    /**
     * 构建完整的{@link Page}。
     *
     * @return 完整的 {@link Page}。
     */
    TPage build();
}
