package com.github.jasonnming.results.result.basic;

import java.io.Serializable;
import java.util.List;

import org.apiguardian.api.API;
import org.jetbrains.annotations.Nullable;

/**
 * 包含一个{@link List}类型数据的结果，包含分页信息。
 * <p>
 * 注：如不确定分页信息是否存在，请先使用{@code has*()}方法确定对应信息是否存在。
 *
 * @param <TElement>    容器中数据的具体类型，必须为可序列化（{@link Serializable}）对象
 *
 * @apiNote 通过此接口获取结果码（{@link #getResultCode()}）所得到的结果码类型是{@link ResultCode}，
 *          如需指定具体结果码类型的请使用{@link com.github.jasonnming.results.result.generic.PagedListResult}接口。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.0.0")
public interface PagedListResult<TElement>
        extends ListResult<TElement>
{
    /**
     * 是否存在当前页码。
     *
     * @return {@code true}表示已存在当前页码，{@code false}表示不存在
     */
    boolean hasPage();

    /**
     * 获取当前页码。
     *
     * @return 当前页码
     *
     * @throws IllegalStateException 如果{@link #hasPage()}为{@code false}，则抛出此异常
     */
    long getPage();

    /**
     * 获取当前页码，如果不存在则返回{@code null}。
     *
     * @return 当前页码或 {@code null}
     */
    @Nullable
    Long getPageOrNull();

    /**
     * 是否存在当前页预计数据量。
     *
     * @return {@code true}表示已存在当前页预计数据量，{@code false}表示不存在
     */
    boolean hasPageSize();

    /**
     * 获取当前页预计数据量。
     *
     * @return 当前页预计数据量
     *
     * @throws IllegalStateException 如果{@link #hasPageSize()}为{@code false}，则抛出此异常
     */
    long getPageSize();

    /**
     * 获取当前页预计数据量，如果不存在则返回{@code null}。
     *
     * @return 当前页预计数据量或 {@code null}
     */
    @Nullable
    Long getPageSizeOrNull();

    /**
     * 是否可以预测下一页是否存在。
     *
     * @return {@code true}表示已知下一页的存在性，{@code false}表示未知
     */
    boolean isPredictable();

    /**
     * 是否还存在下一页。
     *
     * @return {@code true}表示存在下一页，{@code false}表示没有下一页
     *
     * @throws IllegalStateException 如果{@link #isPredictable()}为{@code false}，则抛出此异常
     */
    boolean hasNextPage();

    /**
     * 是否存在总页数。
     *
     * @return {@code true}表示已存在总页数，{@code false}表示不存在
     */
    boolean hasTotalPage();

    /**
     * 获取以{@link #getPageSize()}为分页大小的情况下的总页数。
     *
     * @return 总页数。
     *
     * @throws IllegalStateException 如果{@link #hasTotalPage()}为{@code false}，则抛出此异常
     */
    long getTotalPage();

    /**
     * 获取总页数，如果不存在则返回{@code null}。
     *
     * @return 总页数或 {@code null}
     */
    @Nullable
    Long getTotalPageOrNull();

    /**
     * 是否存在实际记录总数。
     *
     * @return {@code true}表示已存在实际记录总数，{@code false}表示不存在
     */
    boolean hasTotalSize();

    /**
     * 获取实际记录总数。
     *
     * @return 实际记录总数
     *
     * @throws IllegalStateException 如果{@link #hasTotalSize()} 为{@code false}，则抛出此异常
     */
    long getTotalSize();

    /**
     * 获取实际记录总数，如果不存在则返回{@code null}。
     *
     * @return 实际记录总数或 {@code null}
     */
    @Nullable
    Long getTotalSizeOrNull();
}
