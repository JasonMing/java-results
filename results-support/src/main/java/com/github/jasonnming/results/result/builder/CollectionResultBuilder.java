package com.github.jasonnming.results.result.builder;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.apiguardian.api.API;

import com.github.jasonnming.results.result.basic.ResultCode;

/**
 * 集合类型构建器，此构建器不直接创建结果对象，目的是将抽象类型的{@link Collection}转换为具体的{@link List}、{@link Set}或{@link Map}。
 *
 * @param <TResultCode> 构建结果的结果码类型。
 * @param <TContainer>  {@link Collection}的具体类型。
 * @param <TElement>    {@link Collection}内数据类型。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-07-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface CollectionResultBuilder<TResultCode extends ResultCode, TContainer extends Collection<TElement>, TElement>
{
    /**
     * 将数据转换为{@link List}。
     *
     * @return {@link ListResultBuilder}。
     */
    ListResultBuilder<TResultCode, TElement> toListResult();

    /**
     * 将数据通过{@code listFactory}转换为{@link List}。
     *
     * @param listFactory 将当前{@link Collection}类型的数据转换为{@link List}的方法（例如：{@code ArrayList::new} ）
     *
     * @return {@link ListResultBuilder}。
     */
    ListResultBuilder<TResultCode, TElement> toListResult(Function<TContainer, List<TElement>> listFactory);

    /**
     * 将数据转换为{@link Set}。
     *
     * @return {@link SetResultBuilder}。
     */
    SetResultBuilder<TResultCode, TElement> toSetResult();

    /**
     * 将数据通过{@code setFactory}转换为{@link Set}。
     *
     * @param setFactory 将当前{@link Collection}类型的数据转换为{@link Set}的方法（例如：{@code HashSet::new} ）。
     *
     * @return {@link SetResultBuilder}。
     */
    SetResultBuilder<TResultCode, TElement> toSetResult(Function<TContainer, Set<TElement>> setFactory);

    /**
     * 将数据转换为{@link Map}。
     *
     * @param keyExtractor key提取器。
     * @param <TKey>       key类型。
     *
     * @return {@link MapResultBuilder}。
     */
    <TKey> MapResultBuilder<TResultCode, TKey, TElement> toMapResult(Function<TElement, TKey> keyExtractor);

    /**
     * 将数据转换为{@link Map}。
     *
     * @param keyExtractor   key提取器。
     * @param valueExtractor value提取器。
     * @param <TKey>         key类型。
     * @param <TValue>       value类型。
     *
     * @return {@link MapResultBuilder}。
     */
    <TKey, TValue> MapResultBuilder<TResultCode, TKey, TValue> toMapResult(Function<TElement, TKey> keyExtractor, Function<TElement, TValue> valueExtractor);
}
