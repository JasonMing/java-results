package com.github.jasonnming.results.result.builder;

import java.util.List;

import org.apiguardian.api.API;
import org.jetbrains.annotations.Nullable;

import com.github.jasonnming.results.page.Page;
import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.generic.ListResult;

/**
 * 构建{@link ListResult}的构建器。
 *
 * @param <TResultCode> 构建结果的结果码类型。
 * @param <TElement>    存放在{@link List}中的数据类型。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-07-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface ListResultBuilder<TResultCode extends ResultCode, TElement>
        extends FinalStageResultBuilder<ListResult<TResultCode, TElement>>
{
    /**
     * 设置列表结果的分页信息。
     *
     * @param page     当前页码，必须大于0。
     * @param pageSize 当前页大小，必须大于0（非实际数据大小）。
     *
     * @return {@link PagedListResultBuilder}。
     *
     * @throws IllegalArgumentException 当{@code page}或{@code pageSize}小于1。
     */
    PagedListResultBuilder<TResultCode, TElement> page(long page, long pageSize);

    /**
     * 从{@link Page 分页请求}设置列表结果的分页信息，为{@code null}时清空已设置的分页信息。
     *
     * @param pageRequest 分页请求。
     *
     * @return {@link PagedListResultBuilder}。
     *
     * @throws IllegalArgumentException 当{@link Page#getPage()}或{@link Page#getPageSize()}小于1。
     */
    @API(status = API.Status.EXPERIMENTAL, since = "1.0.0")
    PagedListResultBuilder<TResultCode, TElement> page(@Nullable Page pageRequest);

    /**
     * 设置列表结果是否存在下一页，在没有设置过此属性时将会通过假面表达式在{@link #build()}时计算得出：
     * <pre>{@code hasNextPage = page < totalPage}</pre>
     * 如任意一个相关属性（ {@code page} 或 {@code totalPage} ）未被设置，上述过程将不会执行。
     * <p>
     * 注：此属性通常用于总长度未知的流式列表中，以便告知调用方是否还存在下一页。
     *
     * @param hasNextPage 是否存在下一页。
     *
     * @return {@link PagedListResultBuilder}。
     */
    PagedListResultBuilder<TResultCode, TElement> hasNextPage(boolean hasNextPage);

    /**
     * 设置列表结果的总页数，在没有设置过此属性时将会通过下面表达式在{@link #build()}时计算得出：
     * <pre>{@code totalPage = (totalSize / pageSize) + min(0, totalSize % pageSize)}</pre>
     * 如任意一个相关属性（ {@code totalSize} 或 {@code pageSize} ）未被设置，上述过程将不会执行。
     *
     * @param totalPage 总页数，必须大于或等于0（0表示查询的数据集没有任何数据）。
     *
     * @return {@link PagedListResultBuilder}。
     *
     * @throws IllegalArgumentException 当{@code totalPage}小于0。
     */
    PagedListResultBuilder<TResultCode, TElement> totalPage(long totalPage);

    /**
     * 设置列表结果的总数据大小（非当前分页实际数据大小）。
     *
     * @param totalSize 当前页大小，必须大于或等于0（0表示查询的数据集没有任何数据）。
     *
     * @return {@link PagedListResultBuilder}。
     *
     * @throws IllegalArgumentException {@code totalSize}小于0。
     */
    PagedListResultBuilder<TResultCode, TElement> totalSize(long totalSize);
}
