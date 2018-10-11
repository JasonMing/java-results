package com.github.jasonnming.results.result.builder;

import java.util.List;

import org.apiguardian.api.API;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.generic.PagedListResult;

/**
 * 构建{@link PagedListResult}的构建器。
 *
 * @param <TResultCode> 构建结果的结果码类型。
 * @param <TElement>    存放的{@link List}中的数据类型。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-07-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface PagedListResultBuilder<TResultCode extends ResultCode, TElement>
        extends ListResultBuilder<TResultCode, TElement>
{
    /**
     * （可选）指示在已经设置{@link #page pageSize}及{@link #totalSize}的情况下是否允许设置{@link #totalPage}，默认为{@code true}。
     *
     * @param restricted 在已经设置{@link #page pageSize}及{@link #totalSize}的情况下是否允许设置{@link #totalPage}。
     *
     * @return 当前 {@link PagedListResultBuilder}。
     *
     * @apiNote 此标识设置后只会影响最终 {@link #build()}方法中执行的逻辑，不会影响其它的设置方法。
     */
    PagedListResultBuilder<TResultCode, TElement> restricted(boolean restricted);

    /**
     * 构建完整的{@link PagedListResult}。
     *
     * @return 完整的 {@link PagedListResult}。
     *
     * @throws IllegalArgumentException 当 {@link #restricted} 为{@code true}，且同时设置了{@link #page pageSize}、{@link #totalSize}和{@link #totalPage}。
     */
    @Override
    PagedListResult<TResultCode, TElement> build();
}
