package com.github.jasonnming.results.result.builder;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apiguardian.api.API;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.basic.WithMessage;
import com.github.jasonnming.results.result.generic.CollectionResult;
import com.github.jasonnming.results.result.generic.CommonResult;
import com.github.jasonnming.results.result.generic.ListResult;
import com.github.jasonnming.results.result.generic.MapResult;
import com.github.jasonnming.results.result.generic.PagedListResult;
import com.github.jasonnming.results.result.generic.SetResult;
import com.github.jasonnming.results.result.generic.SingleResult;

/**
 * 构建{@link CommonResult}的构建器，并可以通过{@code data(...)}方法转换为其它包含结果数据的具体类型。
 *
 * @param <TResultCode> 构建结果的结果码类型。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-07-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface CommonResultBuilder<TResultCode extends ResultCode>
        extends FinalStageResultBuilder<CommonResult<TResultCode>>
{
    /**
     * （可选）设置结果的用户可读消息，如果不设置且{@code resultCode}对象实现了{@link WithMessage}接口，对应的用户消息默认从{@code resultCode}中获取。
     *
     * @param message 用户可读消息。
     *
     * @return 当前 {@link CommonResultBuilder}
     */
    CommonResultBuilder<TResultCode> message(String message);

    /**
     * （可选）设置结果的调试诊断消息，如果不设置且{@code resultCode}对象实现了{@link WithMessage}接口，对应的调试消息默认从{@code resultCode}中获取。
     *
     * @param debugMessage 调试诊断消息。
     *
     * @return 当前 {@link CommonResultBuilder}。
     */
    CommonResultBuilder<TResultCode> debugMessage(String debugMessage);

    /**
     * 提供单份数据，并返回{@link SingleResult}的构建器。
     *
     * @param data    数据对象。
     * @param <TData> 数据类型。
     *
     * @return {@link SingleResult}的构建器。
     */
    <TData> SingleResultBuilder<TResultCode, TData> data(final TData data);

    /**
     * 提供以{@link Collection}类型（或其子类）为容器的的多份数据，并返回{@link CollectionResult}的构建器。
     *
     * @param data         容器对象。
     * @param <TContainer> 容器类型。
     * @param <TElement>   数据类型。
     *
     * @return {@link CollectionResult}的构建器。
     */
    <TContainer extends Collection<TElement>, TElement> CollectionResultBuilder<TResultCode, TContainer, TElement> data(final TContainer data);

    /**
     * 提供多个元素，并返回{@link ListResult}的构建器。
     *
     * @param data       数据对象（多个）。
     * @param <TElement> 数据类型。
     *
     * @return {@link ListResult}的构建器。
     */
    @SuppressWarnings("unchecked")
    <TElement> ListResultBuilder<TResultCode, TElement> data(final TElement... data);

    /**
     * 提供以{@link List}为容器的多份数据，并返回{@link ListResult}的构建器。
     *
     * @param data       List对象。
     * @param <TElement> 数据类型。
     *
     * @return {@link ListResult}的构建器。
     */
    <TElement> ListResultBuilder<TResultCode, TElement> data(final List<TElement> data);

    /**
     * 提供以{@link Set}为容器的多份且不重复数据，并返回{@link SetResult}的构建器。
     *
     * @param data       Set对象。
     * @param <TElement> 数据类型。
     *
     * @return {@link SetResult}的构建器。
     */
    <TElement> SetResultBuilder<TResultCode, TElement> data(final Set<TElement> data);

    /**
     * 提供{@link Map}，并返回{@link MapResult}的构建器。
     *
     * @param data     Map对象。
     * @param <TKey>   Key类型。
     * @param <TValue> Value类型。
     *
     * @return {@link MapResult}的构建器。
     */
    <TKey, TValue> MapResultBuilder<TResultCode, TKey, TValue> data(final Map<TKey, TValue> data);

    // region: Shortcut methods

    /**
     * 构建一个不含数据的{@link SingleResult}，此方法通常用于构建表述失败的结果对象。
     *
     * @param <TData> 结果对象内的数据类型。
     *
     * @return 不含数据的 {@link SingleResult}。
     */
    <TData> SingleResult<TResultCode, TData> buildSingleResult();

    /**
     * 构建一个不含数据的{@link ListResult}，此方法通常用于构建表述失败的结果对象。
     *
     * @param <TElement> 结果对象内的数据类型。
     *
     * @return 不含数据的 {@link ListResult}，{@link ListResult#getData()}将会返回{@code null}。
     */
    <TElement> ListResult<TResultCode, TElement> buildListResult();

    /**
     * 构建一个不含数据的{@link PagedListResult}，此方法通常用于构建表述失败的结果对象。
     *
     * @param <TElement> 结果对象内的数据类型。
     *
     * @return 不含数据的 {@link PagedListResult}，{@link PagedListResult#getData()}将会返回{@code null}。
     */
    <TElement> PagedListResult<TResultCode, TElement> buildPagedListResult();

    /**
     * 构建一个不含数据的{@link SetResult}，此方法通常用于构建表述失败的结果对象。
     *
     * @param <TElement> 结果对象内的数据类型。
     *
     * @return 不含数据的 {@link SetResult}，{@link SetResult#getData()}将会返回{@code null}。
     */
    <TElement> SetResult<TResultCode, TElement> buildSetResult();

    /**
     * 构建一个不含数据的{@link MapResult}，此方法通常用于构建表述失败的结果对象。
     *
     * @param <TKey>   结果对象内Map的key类型。
     * @param <TValue> 结果对象内Map的value类型。
     *
     * @return 不含数据的 {@link MapResult}，{@link MapResult#getData()}将会返回{@code null}。
     */
    <TKey, TValue> MapResult<TResultCode, TKey, TValue> buildMapResult();

    /**
     * 构建一个带有{@code data}数据项的{@link SingleResult}对象。
     * <p>
     * 此操作相当于{@code data(data).build()}的快捷方式。
     *
     * @param data    {@link SingleResult}内的数据项。
     * @param <TData> 结果对象内的数据类型。
     *
     * @return 带有数据的 {@link SingleResult}。
     */
    <TData> SingleResult<TResultCode, TData> buildSingleResult(final TData data);

    /**
     * 构建一个带有{@code data}数据项的{@link ListResult}对象。
     * <p>
     * 此操作相当于{@code data(data).build()}的快捷方式。
     *
     * @param data       {@link ListResult}内的数据项。
     * @param <TElement> 结果对象内的数据类型。
     *
     * @return 带有数据的 {@link ListResult}。
     */
    @SuppressWarnings("unchecked")
    <TElement> ListResult<TResultCode, TElement> buildListResult(final TElement... data);

    /**
     * 构建一个带有{@code data}数据项的{@link ListResult}对象。
     * <p>
     * 此操作相当于{@code data(data).build()}的快捷方式。
     *
     * @param data       {@link ListResult}内的数据项。
     * @param <TElement> 结果对象内的数据类型。
     *
     * @return 带有数据的 {@link ListResult}。
     */
    <TElement> ListResult<TResultCode, TElement> buildListResult(final List<TElement> data);

    /**
     * 构建一个带有{@code data}数据项的{@link ListResult}对象。
     * <p>
     * 此操作相当于{@code data(data).toListResult().build()}的快捷方式。
     *
     * @param data       {@link ListResult}内的数据项。
     * @param <TElement> 结果对象内的数据类型。
     *
     * @return 带有数据的 {@link ListResult}。
     */
    <TElement> ListResult<TResultCode, TElement> buildListResult(final Collection<TElement> data);

    /**
     * 构建一个带有{@code data}数据项的{@link SetResult}对象。
     * <p>
     * 此操作相当于{@code data(data).build()}的快捷方式。
     *
     * @param data       {@link SetResult}内的数据项。
     * @param <TElement> 结果对象内的数据类型。
     *
     * @return 带有数据的 {@link SetResult}。
     */
    <TElement> SetResult<TResultCode, TElement> buildSetResult(final Set<TElement> data);

    /**
     * 构建一个带有{@code data}数据项的{@link MapResult}对象。
     * <p>
     * 此操作相当于{@code data(data).build()}的快捷方式。
     *
     * @param data     {@link SetResult}内的数据项。
     * @param <TKey>   结果对象内Map的key类型。
     * @param <TValue> 结果对象内Map的value类型。
     *
     * @return 带有数据的 {@link MapResult}。
     */
    <TKey, TValue> MapResult<TResultCode, TKey, TValue> buildMapResult(final Map<TKey, TValue> data);

    // endregion: Shortcut methods
}
