package com.github.jasonnming.results.result.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

import com.github.jasonnming.results.internal.InternalUtils;
import com.github.jasonnming.results.page.Page;
import com.github.jasonnming.results.result.basic.Result;
import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.basic.WithMessage;
import com.github.jasonnming.results.result.builder.CollectionResultBuilder;
import com.github.jasonnming.results.result.builder.CommonResultBuilder;
import com.github.jasonnming.results.result.builder.ListResultBuilder;
import com.github.jasonnming.results.result.builder.MapResultBuilder;
import com.github.jasonnming.results.result.builder.PagedListResultBuilder;
import com.github.jasonnming.results.result.builder.SetResultBuilder;
import com.github.jasonnming.results.result.builder.SingleResultBuilder;
import com.github.jasonnming.results.result.generic.CollectionResult;
import com.github.jasonnming.results.result.generic.CommonResult;
import com.github.jasonnming.results.result.generic.ListResult;
import com.github.jasonnming.results.result.generic.MapResult;
import com.github.jasonnming.results.result.generic.PagedListResult;
import com.github.jasonnming.results.result.generic.SetResult;
import com.github.jasonnming.results.result.generic.SingleResult;

/**
 * 创建结果对象的帮助类，主要分为如下方法：
 * <ul>
 * <li>{@code xxxResult(...)}</li>
 * 直接根据结果码及对应数据创建对应的{@link Result}对象。
 * <li>{@code builder(...)}</li>
 * 通过构建器的方式创建完整的{@link Result}对象。
 * </ul>
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2016-05-30)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public final class Results
{
    /**
     * 空的{@link WithMessage}，此消息内部所有方法都将返回{@code null}。
     *
     * @apiNote 此实例不可序列化传输，否则会出现未知问题。
     */
    public static final WithMessage EMPTY_MESSAGE = () -> null;

    private Results() {}

    // region: CommonResult

    /**
     * 创建只包含代码及消息的{@link CommonResult}。
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param <TResultCode> 结果码类型。
     *
     * @return {@link DefaultCommonResult}实例。
     */
    public static <TResultCode extends ResultCode>
    CommonResult<TResultCode> commonResult(final TResultCode resultCode)
    {
        return new DefaultCommonResult<>(resultCode);
    }

    /**
     * 创建只包含代码及消息的{@link CommonResult}。
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param <TResultCode> 结果码类型。
     *
     * @return {@link DefaultCommonResult}实例。
     */
    public static <TResultCode extends ResultCode> CommonResult<TResultCode>
    commonResult(final TResultCode resultCode, final String message)
    {
        return new DefaultCommonResult<>(resultCode, message, null);
    }

    /**
     * 创建只包含代码及消息的{@link CommonResult}。
     *
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param <TResultCode> 结果码类型。
     *
     * @return {@link DefaultCommonResult}实例。
     */
    public static <TResultCode extends ResultCode> CommonResult<TResultCode>
    commonResult(final TResultCode resultCode, final String message, final String debugMessage)
    {
        return new DefaultCommonResult<>(resultCode, message, debugMessage);
    }

    // endregion: CommonResult


    // region: SingleResult

    /**
     * 创建只包含代码及消息的{@link SingleResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param <TResultCode> 结果码类型。
     * @param <TResult>     装载数据的类型。
     *
     * @return {@link DefaultSingleResult}实例。
     */
    public static <TResultCode extends ResultCode, TResult>
    SingleResult<TResultCode, TResult> singleResult(final TResultCode resultCode)
    {
        return new DefaultSingleResult<>(resultCode);
    }

    /**
     * 创建只包含代码及消息的{@link SingleResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param <TResultCode> 结果码类型。
     * @param <TResult>     装载数据的类型。
     *
     * @return {@link DefaultSingleResult}实例。
     */
    public static <TResultCode extends ResultCode, TResult>
    SingleResult<TResultCode, TResult> singleResult(final TResultCode resultCode, final String message)
    {
        return new DefaultSingleResult<>(resultCode, message, null);
    }

    /**
     * 创建只包含代码及消息的{@link SingleResult}。
     *
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param <TResultCode> 结果码类型。
     * @param <TResult>     装载数据的类型。
     *
     * @return {@link DefaultSingleResult}实例。
     */
    public static <TResultCode extends ResultCode, TResult>
    SingleResult<TResultCode, TResult> singleResult(final TResultCode resultCode, final String message, final String debugMessage)
    {
        return new DefaultSingleResult<>(resultCode, message, debugMessage);
    }

    /**
     * 创建包含代码及消息及单个数据的{@link SingleResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     * @param <TResult>     装载数据的类型。
     *
     * @return {@link DefaultSingleResult}实例。
     *
     * @apiNote 创建存放多个对象的结果请使用{@link #listResult(TResultCode, List) listResult}。
     * @see SingleResult
     */
    public static <TResultCode extends ResultCode, TResult>
    SingleResult<TResultCode, TResult> singleResult(final TResultCode resultCode, final TResult data)
    {
        return new DefaultSingleResult<>(resultCode, null, null, data);
    }

    /**
     * 创建包含代码及消息及单个数据的{@link SingleResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     * @param <TResult>     装载数据的类型。
     *
     * @return {@link DefaultSingleResult}实例。
     *
     * @apiNote 创建存放多个对象的结果请使用{@link #listResult(TResultCode, String, List) listResult}。
     * @see SingleResult
     */
    public static <TResultCode extends ResultCode, TResult>
    SingleResult<TResultCode, TResult> singleResult(final TResultCode resultCode, final String message, final TResult data)
    {
        return new DefaultSingleResult<>(resultCode, message, null, data);
    }

    /**
     * 创建包含代码及消息及单个数据的{@link SingleResult}。
     *
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     * @param <TResult>     装载数据的类型。
     *
     * @return {@link DefaultSingleResult}实例。
     *
     * @apiNote 创建存放多个对象的结果请使用{@link #listResult(TResultCode, String, String, List) listResult}。
     * @see SingleResult
     */
    public static <TResultCode extends ResultCode, TResult>
    SingleResult<TResultCode, TResult> singleResult(final TResultCode resultCode, final String message, final String debugMessage, final TResult data)
    {
        return new DefaultSingleResult<>(resultCode, message, debugMessage, data);
    }

    // endregion: SingleResult


    // region: ListResult

    /**
     * 创建只包含代码及消息的{@link ListResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    ListResult<TResultCode, TElement> listResult(final TResultCode resultCode)
    {
        return new DefaultListResult<>(resultCode);
    }

    /**
     * 创建只包含代码及消息的{@link ListResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    ListResult<TResultCode, TElement> listResult(final TResultCode resultCode, final String message)
    {
        return new DefaultListResult<>(resultCode, message, null);
    }

    /**
     * 创建只包含代码及消息的{@link ListResult}。
     *
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    ListResult<TResultCode, TElement> listResult(final TResultCode resultCode, final String message, final String debugMessage)
    {
        return new DefaultListResult<>(resultCode, message, debugMessage);
    }

    /**
     * 创建包含代码及消息及多个数据的{@link ListResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    ListResult<TResultCode, TElement> listResult(final TResultCode resultCode, final List<TElement> data)
    {
        return new DefaultListResult<>(resultCode, null, null, data);
    }

    /**
     * 创建包含代码及消息及多个数据的{@link ListResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    ListResult<TResultCode, TElement> listResult(final TResultCode resultCode, final String message, final List<TElement> data)
    {
        return new DefaultListResult<>(resultCode, message, null, data);
    }

    /**
     * 创建包含代码及消息及多个数据的{@link ListResult}。
     *
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    ListResult<TResultCode, TElement> listResult(final TResultCode resultCode, final String message, final String debugMessage, final List<TElement> data)
    {
        return new DefaultListResult<>(resultCode, message, debugMessage, data);
    }

    // endregion: ListResult


    // region: PagedListResult

    /**
     * 创建只包含代码及消息的{@link PagedListResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultPagedListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    PagedListResult<TResultCode, TElement> pagedListResult(final TResultCode resultCode)
    {
        return new DefaultPagedListResult<>(resultCode);
    }

    /**
     * 创建只包含代码及消息的{@link PagedListResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultPagedListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    PagedListResult<TResultCode, TElement> pagedListResult(final TResultCode resultCode, final String message)
    {
        return new DefaultPagedListResult<>(resultCode, message, null);
    }

    /**
     * 创建只包含代码及消息的{@link PagedListResult}。
     *
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultPagedListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    PagedListResult<TResultCode, TElement> pagedListResult(final TResultCode resultCode, final String message, final String debugMessage)
    {
        return new DefaultPagedListResult<>(resultCode, message, debugMessage);
    }

    /**
     * 创建包含代码及消息及带有分页信息的多个数据的{@link PagedListResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param data          装载的数据。
     * @param page          分页信息，包含{@link Page#getPage() 页码}及{@link Page#getPageSize() 每页数量}（此参数通常从请求处获取）。
     * @param totalSize     符合当前查询的所有记录总数。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultPagedListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    PagedListResult<TResultCode, TElement> pagedListResult(final TResultCode resultCode, final List<TElement> data, final Page page, final long totalSize)
    {
        return builder(resultCode)
                .data(data)
                .page(page)
                .totalSize(totalSize)
                .build();
    }

    /**
     * 创建包含代码及消息及带有分页信息的多个数据的{@link PagedListResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param data          装载的数据。
     * @param page          分页信息，包含{@link Page#getPage() 页码}及{@link Page#getPageSize() 每页数量}（此参数通常从请求处获取）。
     * @param totalSize     符合当前查询的所有记录总数。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultPagedListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    PagedListResult<TResultCode, TElement> pagedListResult(final TResultCode resultCode, final String message, final List<TElement> data, final Page page, final long totalSize)
    {
        return builder(resultCode)
                .message(message)
                .data(data)
                .page(page)
                .totalSize(totalSize)
                .build();
    }

    /**
     * 创建包含代码及消息及带有分页信息的多个数据的{@link PagedListResult}。
     *
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param data          装载的数据。
     * @param page          分页信息，包含{@link Page#getPage() 页码}及{@link Page#getPageSize() 每页数量}（此参数通常从请求处获取）。
     * @param totalSize     符合当前查询的所有记录总数。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultPagedListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    PagedListResult<TResultCode, TElement> pagedListResult(final TResultCode resultCode, final String message, final String debugMessage, final List<TElement> data, final Page page, final long totalSize)
    {
        return builder(resultCode)
                .message(message)
                .debugMessage(debugMessage)
                .data(data)
                .page(page)
                .totalSize(totalSize)
                .build();
    }

    /**
     * 创建包含代码及消息及带有分页信息的多个数据的{@link PagedListResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param data          装载的数据。
     * @param page          分页请求页码（通常从请求中获取）。
     * @param pageSize      分页每页数量（通常从请求中获取）。
     * @param totalSize     符合当前查询的所有记录总数。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultPagedListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    PagedListResult<TResultCode, TElement> pagedListResult(final TResultCode resultCode, final List<TElement> data, final long page, final long pageSize, final long totalSize)
    {
        return builder(resultCode)
                .data(data)
                .page(page, pageSize)
                .totalSize(totalSize)
                .build();
    }

    /**
     * 创建包含代码及消息及带有分页信息的多个数据的{@link PagedListResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param data          装载的数据。
     * @param page          分页请求页码（通常从请求中获取）。
     * @param pageSize      分页每页数量（通常从请求中获取）。
     * @param totalSize     符合当前查询的所有记录总数。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultPagedListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    PagedListResult<TResultCode, TElement> pagedListResult(final TResultCode resultCode, final String message, final List<TElement> data, final long page, final long pageSize, final long totalSize)
    {
        return builder(resultCode)
                .message(message)
                .data(data)
                .page(page, pageSize)
                .totalSize(totalSize)
                .build();
    }

    /**
     * 创建包含代码及消息及带有分页信息的多个数据的{@link PagedListResult}。
     *
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param data          装载的数据。
     * @param page          分页请求页码（通常从请求中获取）。
     * @param pageSize      分页每页数量（通常从请求中获取）。
     * @param totalSize     符合当前查询的所有记录总数。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    列表中装载数据的类型。
     *
     * @return {@link DefaultPagedListResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    PagedListResult<TResultCode, TElement> pagedListResult(final TResultCode resultCode, final String message, final String debugMessage, final List<TElement> data, final long page, final long pageSize, final long totalSize)
    {
        return builder(resultCode)
                .message(message)
                .debugMessage(debugMessage)
                .data(data)
                .page(page, pageSize)
                .totalSize(totalSize)
                .build();
    }

    // endregion: PagedListResult


    // region: SetResult

    /**
     * 创建只包含代码及消息的{@link SetResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    集合中装载数据的类型。
     *
     * @return {@link DefaultSetResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    SetResult<TResultCode, TElement> setResult(final TResultCode resultCode)
    {
        return new DefaultSetResult<>(resultCode);
    }

    /**
     * 创建只包含代码及消息的{@link SetResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    集合中装载数据的类型。
     *
     * @return {@link DefaultSetResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    SetResult<TResultCode, TElement> setResult(final TResultCode resultCode, final String message)
    {
        return new DefaultSetResult<>(resultCode, message, null);
    }

    /**
     * 创建只包含代码及消息的{@link SetResult}。
     *
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    集合中装载数据的类型。
     *
     * @return {@link DefaultSetResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    SetResult<TResultCode, TElement> setResult(final TResultCode resultCode, final String message, final String debugMessage)
    {
        return new DefaultSetResult<>(resultCode, message, debugMessage);
    }

    /**
     * 创建包含代码及消息及无序不重复数据的{@link SetResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    集合中装载数据的类型。
     *
     * @return {@link DefaultSetResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    SetResult<TResultCode, TElement> setResult(final TResultCode resultCode, final Set<TElement> data)
    {
        return new DefaultSetResult<>(resultCode, null, null, data);
    }

    /**
     * 创建包含代码及消息及无序不重复数据的{@link SetResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    集合中装载数据的类型。
     *
     * @return {@link DefaultSetResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    SetResult<TResultCode, TElement> setResult(final TResultCode resultCode, final String message, final Set<TElement> data)
    {
        return new DefaultSetResult<>(resultCode, message, null, data);
    }

    /**
     * 创建包含代码及消息及无序不重复数据的{@link SetResult}。
     *
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     * @param <TElement>    集合中装载数据的类型。
     *
     * @return {@link DefaultSetResult}实例。
     */
    public static <TResultCode extends ResultCode, TElement>
    SetResult<TResultCode, TElement> setResult(final TResultCode resultCode, final String message, final String debugMessage, final Set<TElement> data)
    {
        return new DefaultSetResult<>(resultCode, message, debugMessage, data);
    }

    // endregion: SetResult


    // region: MapResult

    /**
     * 创建只包含代码及消息的{@link MapResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param <TResultCode> 结果码类型。
     * @param <TKey>        key类型。
     * @param <TValue>      value类型。
     *
     * @return {@link DefaultMapResult}实例。
     */
    public static <TResultCode extends ResultCode, TKey, TValue>
    MapResult<TResultCode, TKey, TValue> mapResult(final TResultCode resultCode)
    {
        return new DefaultMapResult<>(resultCode);
    }

    /**
     * 创建只包含代码及消息的{@link MapResult}，
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param <TResultCode> 结果码类型。
     * @param <TKey>        key类型。
     * @param <TValue>      value类型。
     *
     * @return {@link DefaultMapResult}实例。
     */
    public static <TResultCode extends ResultCode, TKey, TValue>
    MapResult<TResultCode, TKey, TValue> mapResult(final TResultCode resultCode, final String message)
    {
        return new DefaultMapResult<>(resultCode, message, null);
    }

    /**
     * 创建包含代码及消息及多个key-value对数据的{@link MapResult}。
     *
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param <TResultCode> 结果码类型。
     * @param <TKey>        key类型。
     * @param <TValue>      value类型。
     *
     * @return {@link DefaultMapResult}实例。
     */
    public static <TResultCode extends ResultCode, TKey, TValue>
    MapResult<TResultCode, TKey, TValue> mapResult(final TResultCode resultCode, final String message, final String debugMessage)
    {
        return new DefaultMapResult<>(resultCode, message, debugMessage);
    }

    /**
     * 创建包含代码及消息及多个key-value对数据的{@link MapResult}，
     * 其中{@code message}和{@code debugMessage}从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     * @param <TKey>        key类型。
     * @param <TValue>      value类型。
     *
     * @return {@link DefaultMapResult}实例。
     */
    public static <TResultCode extends ResultCode, TKey, TValue>
    MapResult<TResultCode, TKey, TValue> mapResult(final TResultCode resultCode, final Map<TKey, TValue> data)
    {
        return new DefaultMapResult<>(resultCode, null, null, data);
    }

    /**
     * 创建包含代码及消息及多个key-value对数据的{@link MapResult}，
     * 其中{@code debugMessage}从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     * @param <TKey>        key类型。
     * @param <TValue>      value类型。
     *
     * @return {@link DefaultMapResult}实例。
     */
    public static <TResultCode extends ResultCode, TKey, TValue>
    MapResult<TResultCode, TKey, TValue> mapResult(final TResultCode resultCode, final String message, final Map<TKey, TValue> data)
    {
        return new DefaultMapResult<>(resultCode, message, null, data);
    }

    /**
     * 创建一个包含多个key-value数据{@code data}且带有自定义{@code message}和{@code debugMessage}的{@link MapResult}。
     *
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     * @param <TKey>        key类型。
     * @param <TValue>      value类型。
     *
     * @return {@link DefaultMapResult}实例。
     */
    public static <TResultCode extends ResultCode, TKey, TValue>
    MapResult<TResultCode, TKey, TValue> mapResult(final TResultCode resultCode, final String message, final String debugMessage, final Map<TKey, TValue> data)
    {
        return new DefaultMapResult<>(resultCode, message, debugMessage, data);
    }

    // endregion: MapResult


    // region: of

    /**
     * 创建只包含代码及消息的结果对象，结果类型根据{@code resultType}决定。
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultType    期望创建的结果对象类型。
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param <TResult>     结果对象类型。
     * @param <TResultCode> 结果码类型。
     *
     * @return {@code resultType}指定的结果对象实例。
     *
     * @throws IllegalArgumentException 当{@code resultType}不在以下类型中：
     *                                  <ul>
     *                                  <li>{@link CommonResult}</li>
     *                                  <li>{@link SingleResult}</li>
     *                                  <li>{@link CollectionResult}</li>
     *                                  <li>{@link ListResult}</li>
     *                                  <li>{@link PagedListResult}</li>
     *                                  <li>{@link SetResult}</li>
     *                                  <li>{@link MapResult}</li>
     *                                  </ul>
     */
    public static <TResult extends com.github.jasonnming.results.result.basic.CommonResult, TResultCode extends ResultCode>
    TResult of(final Class<? extends TResult> resultType, final TResultCode resultCode)
    {
        return of(resultType, resultCode, null, null);
    }

    /**
     * 创建只包含代码及消息的结果对象，结果类型根据{@code resultType}决定；
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultType    期望创建的结果对象类型。
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param <TResult>     结果对象类型。
     * @param <TResultCode> 结果码类型。
     *
     * @return {@code resultType}指定的结果对象实例。
     *
     * @throws IllegalArgumentException 当{@code resultType}不在以下类型中：
     *                                  <ul>
     *                                  <li>{@link CommonResult}</li>
     *                                  <li>{@link SingleResult}</li>
     *                                  <li>{@link CollectionResult}</li>
     *                                  <li>{@link ListResult}</li>
     *                                  <li>{@link PagedListResult}</li>
     *                                  <li>{@link SetResult}</li>
     *                                  <li>{@link MapResult}</li>
     *                                  </ul>
     */
    public static <TResult extends com.github.jasonnming.results.result.basic.CommonResult, TResultCode extends ResultCode>
    TResult of(final Class<? extends TResult> resultType, final TResultCode resultCode, final String message)
    {
        return of(resultType, resultCode, message, null);
    }

    /**
     * 创建只包含代码及消息的结果对象，结果类型根据{@code resultType}决定。
     *
     * @param resultType    期望创建的结果对象类型。
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param <TResult>     结果对象类型。
     * @param <TResultCode> 结果码类型。
     *
     * @return {@code resultType}指定的结果对象实例。
     *
     * @throws IllegalArgumentException 当{@code resultType}不在以下类型中：
     *                                  <ul>
     *                                  <li>{@link CommonResult}</li>
     *                                  <li>{@link SingleResult}</li>
     *                                  <li>{@link CollectionResult}</li>
     *                                  <li>{@link ListResult}</li>
     *                                  <li>{@link PagedListResult}</li>
     *                                  <li>{@link SetResult}</li>
     *                                  <li>{@link MapResult}</li>
     *                                  </ul>
     */
    @SuppressWarnings("unchecked")
    public static <TResult extends com.github.jasonnming.results.result.basic.CommonResult, TResultCode extends ResultCode>
    TResult of(final Class<? extends TResult> resultType, final TResultCode resultCode, final String message, final String debugMessage)
    {
        // SingleResult
        if (resultType == SingleResult.class || resultType == com.github.jasonnming.results.result.basic.SingleResult.class)
        {
            return (TResult)singleResult(resultCode, message, debugMessage);
        }

        // CollectionResult and subtype results
        if (com.github.jasonnming.results.result.basic.CollectionResult.class.isAssignableFrom(resultType))
        {
            if (resultType == PagedListResult.class || resultType == com.github.jasonnming.results.result.basic.PagedListResult.class)
            {
                return (TResult)pagedListResult(resultCode, message, debugMessage);
            }
            if (resultType == ListResult.class || resultType == com.github.jasonnming.results.result.basic.ListResult.class)
            {
                return (TResult)listResult(resultCode, message, debugMessage);
            }
            if (resultType == SetResult.class || resultType == com.github.jasonnming.results.result.basic.SetResult.class)
            {
                return (TResult)setResult(resultCode, message, debugMessage);
            }
            if (resultType == CollectionResult.class || resultType == com.github.jasonnming.results.result.basic.CollectionResult.class)
            {
                // Use ListResult as CollectionResult by default.
                return (TResult)listResult(resultCode, message, debugMessage);
            }
        }

        // MapResult
        if (resultType == MapResult.class || resultType == com.github.jasonnming.results.result.basic.MapResult.class)
        {
            return (TResult)mapResult(resultCode, message, debugMessage);
        }

        // CommonResult
        if (resultType == CommonResult.class || resultType == com.github.jasonnming.results.result.basic.CommonResult.class)
        {
            return (TResult)commonResult(resultCode, message, debugMessage);
        }

        throw resultTypeOutOfRange();
    }

    /**
     * 创建包含代码及消息及数据的结果对象，结果类型根据{@code resultType}决定。
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultType    期望创建的结果对象类型。
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param data          装载的数据。
     * @param <TResult>     结果对象类型。
     * @param <TResultCode> 结果码类型。
     *
     * @return {@code resultType}指定的结果对象实例。
     *
     * @throws IllegalArgumentException 当{@code resultType}不在以下类型中：
     *                                  <ul>
     *                                  <li>{@link CommonResult}</li>
     *                                  <li>{@link SingleResult}</li>
     *                                  <li>{@link CollectionResult}</li>
     *                                  <li>{@link ListResult}</li>
     *                                  <li>{@link PagedListResult}</li>
     *                                  <li>{@link SetResult}</li>
     *                                  <li>{@link MapResult}</li>
     *                                  </ul>
     */
    @API(status = API.Status.EXPERIMENTAL, since = "1.0.0")
    public static <TResult extends com.github.jasonnming.results.result.basic.CommonResult, TResultCode extends ResultCode>
    TResult of(final Class<? extends TResult> resultType, final TResultCode resultCode, final Object data)
    {
        return of(resultType, resultCode, null, null, data);
    }

    /**
     * 创建包含代码及消息及数据的结果对象，结果类型根据{@code resultType}决定；
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultType    期望创建的结果对象类型。
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param data          装载的数据。
     * @param <TResult>     结果对象类型。
     * @param <TResultCode> 结果码类型。
     *
     * @return {@code resultType}指定的结果对象实例。
     *
     * @throws IllegalArgumentException 当{@code resultType}不在以下类型中：
     *                                  <ul>
     *                                  <li>{@link CommonResult}</li>
     *                                  <li>{@link SingleResult}</li>
     *                                  <li>{@link CollectionResult}</li>
     *                                  <li>{@link ListResult}</li>
     *                                  <li>{@link PagedListResult}</li>
     *                                  <li>{@link SetResult}</li>
     *                                  <li>{@link MapResult}</li>
     *                                  </ul>
     */
    @API(status = API.Status.EXPERIMENTAL, since = "1.0.0")
    public static <TResult extends com.github.jasonnming.results.result.basic.CommonResult, TResultCode extends ResultCode>
    TResult of(final Class<? extends TResult> resultType, final TResultCode resultCode, final String message, final Object data)
    {
        return of(resultType, resultCode, message, null, data);
    }

    /**
     * 创建包含代码及消息及数据的结果对象，结果类型根据{@code resultType}决定。
     *
     * @param resultType    期望创建的结果对象类型。
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param data          装载的数据。
     * @param <TResult>     结果对象类型。
     * @param <TResultCode> 结果码类型。
     *
     * @return {@code resultType}指定的结果对象实例。
     *
     * @throws IllegalArgumentException 当{@code resultType}不在以下类型中：
     *                                  <ul>
     *                                  <li>{@link CommonResult}</li>
     *                                  <li>{@link SingleResult}</li>
     *                                  <li>{@link CollectionResult}</li>
     *                                  <li>{@link ListResult}</li>
     *                                  <li>{@link PagedListResult}</li>
     *                                  <li>{@link SetResult}</li>
     *                                  <li>{@link MapResult}</li>
     *                                  </ul>
     */
    @API(status = API.Status.EXPERIMENTAL, since = "1.0.0")
    @SuppressWarnings("unchecked")
    public static <TResult extends com.github.jasonnming.results.result.basic.CommonResult, TResultCode extends ResultCode>
    TResult of(final Class<? extends TResult> resultType, final TResultCode resultCode, final String message, final String debugMessage, final Object data)
    {
        // SingleResult
        if (resultType == SingleResult.class || resultType == com.github.jasonnming.results.result.basic.SingleResult.class)
        {
            return (TResult)singleResult(resultCode, message, debugMessage, data);
        }

        // CollectionResult and subtype results
        if (com.github.jasonnming.results.result.basic.CollectionResult.class.isAssignableFrom(resultType))
        {
            if (resultType == PagedListResult.class || resultType == com.github.jasonnming.results.result.basic.PagedListResult.class)
            {
                // TODO: support PagedListResult
                throw new UnsupportedOperationException("TODO");
            }
            if (resultType == ListResult.class || resultType == com.github.jasonnming.results.result.basic.ListResult.class)
            {
                return (TResult)listResult(resultCode, message, debugMessage, (List<?>)data);
            }
            if (resultType == SetResult.class || resultType == com.github.jasonnming.results.result.basic.SetResult.class)
            {
                return (TResult)setResult(resultCode, message, debugMessage, (Set<?>)data);
            }
            if (resultType == CollectionResult.class || resultType == com.github.jasonnming.results.result.basic.CollectionResult.class)
            {
                // Use ListResult as CollectionResult by default.
                return (TResult)listResult(resultCode, message, debugMessage, (List<?>)data);
            }
        }

        // MapResult
        if (resultType == MapResult.class || resultType == com.github.jasonnming.results.result.basic.MapResult.class)
        {
            return (TResult)mapResult(resultCode, message, debugMessage, (Map<?, ?>)data);
        }

        // CommonResult
        if (resultType == CommonResult.class || resultType == com.github.jasonnming.results.result.basic.CommonResult.class)
        {
            return (TResult)commonResult(resultCode, message, debugMessage);
        }

        throw resultTypeOutOfRange();
    }

    /**
     * 创建包含代码及消息及数据的结果对象，结果类型根据{@code data}决定。
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的用户消息及调试消息将会从{@code resultCode}中获取。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     *
     * @return 根据{@code data}推断最匹配的结果对象实例。
     *
     * @throws IllegalArgumentException 当{@code resultType}不在以下类型中：
     *                                  <ul>
     *                                  <li>{@link CommonResult}</li>
     *                                  <li>{@link SingleResult}</li>
     *                                  <li>{@link CollectionResult}</li>
     *                                  <li>{@link ListResult}</li>
     *                                  <li>{@link PagedListResult}</li>
     *                                  <li>{@link SetResult}</li>
     *                                  <li>{@link MapResult}</li>
     *                                  </ul>
     */
    @API(status = API.Status.EXPERIMENTAL, since = "1.0.0")
    public static <TResultCode extends ResultCode>
    CommonResult<TResultCode> of(final TResultCode resultCode, final Object data)
    {
        return of(resultCode, null, null, data);
    }

    /**
     * 创建包含代码及消息及数据的结果对象，结果类型根据{@code data}决定；
     * 如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     *
     * @param resultCode    结果码对象，如果{@link TResultCode}实现了{@link WithMessage}接口，此结果的调试消息将会从{@code resultCode}中获取。
     * @param message       用户可读消息。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     *
     * @return 根据{@code data}推断最匹配的结果对象实例。
     *
     * @throws IllegalArgumentException 当{@code resultType}不在以下类型中：
     *                                  <ul>
     *                                  <li>{@link CommonResult}</li>
     *                                  <li>{@link SingleResult}</li>
     *                                  <li>{@link CollectionResult}</li>
     *                                  <li>{@link ListResult}</li>
     *                                  <li>{@link PagedListResult}</li>
     *                                  <li>{@link SetResult}</li>
     *                                  <li>{@link MapResult}</li>
     *                                  </ul>
     */
    @API(status = API.Status.EXPERIMENTAL, since = "1.0.0")
    public static <TResultCode extends ResultCode>
    CommonResult<TResultCode> of(final TResultCode resultCode, final String message, final Object data)
    {
        return of(resultCode, message, null, data);
    }

    /**
     * 创建包含代码及消息及数据的结果对象，结果类型根据{@code data}决定。
     *
     * @param resultCode    结果码对象。
     * @param message       用户可读消息。
     * @param debugMessage  调试诊断消息。
     * @param data          装载的数据。
     * @param <TResultCode> 结果码类型。
     *
     * @return 根据{@code data}推断最匹配的结果对象实例。
     *
     * @throws IllegalArgumentException 当{@code resultType}不在以下类型中：
     *                                  <ul>
     *                                  <li>{@link CommonResult}</li>
     *                                  <li>{@link SingleResult}</li>
     *                                  <li>{@link CollectionResult}</li>
     *                                  <li>{@link ListResult}</li>
     *                                  <li>{@link PagedListResult}</li>
     *                                  <li>{@link SetResult}</li>
     *                                  <li>{@link MapResult}</li>
     *                                  </ul>
     */
    @API(status = API.Status.EXPERIMENTAL, since = "1.0.0")
    @SuppressWarnings("unchecked")
    public static <TResultCode extends ResultCode>
    CommonResult<TResultCode> of(final TResultCode resultCode, final String message, final String debugMessage, final Object data)
    {
        if (data == null)
        {
            return Results.commonResult(resultCode, message, debugMessage);
        } else if (data.getClass().isArray())
        {
            return Results.listResult(resultCode, message, debugMessage, Arrays.asList((Object[])data));
        } else if (data instanceof List)
        {
            return Results.listResult(resultCode, message, debugMessage, (List<?>)data);
        } else if (data instanceof Set)
        {
            return Results.setResult(resultCode, message, debugMessage, (Set<?>)data);
        } else if (data instanceof Map)
        {
            return Results.mapResult(resultCode, message, debugMessage, (Map<?, ?>)data);
        } else
        {
            return Results.singleResult(resultCode, message, debugMessage, data);
        }
    }

    @NotNull
    private static IllegalArgumentException resultTypeOutOfRange()
    {
        return new IllegalArgumentException("resultType must be exactly in following types: " + Stream
                .of(
                        CommonResult.class,
                        SingleResult.class,
                        CollectionResult.class,
                        ListResult.class,
                        PagedListResult.class,
                        SetResult.class,
                        MapResult.class
                )
                .map(x -> x.getSimpleName())
                .collect(Collectors.joining(",", "[", "].")));
    }

    // endregion: of


    // region: Builders

    /**
     * 构建{@link CommonResult}、{@link SingleResult}、{@link ListResult}、{@link PagedListResult}、{@link SetResult}或{@link MapResult}。
     * 具体构建类型将由输入参数决定。
     *
     * @param resultCode    结果码
     * @param <TResultCode> 结果码类型
     *
     * @return {@link Result}构建器，构建类型将会根据输入参数而决定。
     */
    public static <TResultCode extends ResultCode> CommonResultBuilder<TResultCode> builder(final TResultCode resultCode)
    {
        return new CommonResultBuilderImpl<>(resultCode);
    }

    // region: Builder Implements

    private static class CommonResultBuilderImpl<TResultCode extends ResultCode>
            implements CommonResultBuilder<TResultCode>/*, DelegatedBuilder<TResultCode>*/
    {
        private final TResultCode resultCode;

        private String message;

        private String debugMessage;

        private CommonResultBuilderImpl(final TResultCode resultCode)
        {
            this.resultCode = resultCode;
        }

        @Override
        public CommonResultBuilder<TResultCode> message(final String message)
        {
            this.message = message;
            return this;
        }

        @Override
        public CommonResultBuilder<TResultCode> debugMessage(final String debugMessage)
        {
            this.debugMessage = debugMessage;
            return this;
        }

        @Override
        public CommonResult<TResultCode> build()
        {
            return new DefaultCommonResult<>(this.resultCode, this.message, this.debugMessage);
        }

        <TConcreteResult extends CommonResult<TResultCode>> TConcreteResult build(final ResultFactory<TResultCode, TConcreteResult> delegate)
        {
            return delegate.create(this.resultCode, this.message, this.debugMessage);
        }

        @Override
        public <TData> SingleResultBuilder<TResultCode, TData> data(final TData data)
        {
            return () -> new DefaultSingleResult<>(this.resultCode, this.message, this.debugMessage, data);
        }

        @Override
        public <TContainer extends Collection<TElement>, TElement> CollectionResultBuilder<TResultCode, TContainer, TElement> data(final TContainer data)
        {
            return new CollectionResultBuilderImpl<>(data, this);
        }

        @Override
        @SuppressWarnings("unchecked")
        public <TElement> ListResultBuilder<TResultCode, TElement> data(final TElement... data)
        {
            return new ListResultBuilderImpl<>(InternalUtils.toList(data), this);
        }

        @Override
        public <TElement> ListResultBuilder<TResultCode, TElement> data(final List<TElement> data)
        {
            return new ListResultBuilderImpl<>(InternalUtils.toList(data), this);
        }

        @Override
        public <TElement> SetResultBuilder<TResultCode, TElement> data(final Set<TElement> data)
        {
            return () -> new DefaultSetResult<>(this.resultCode, this.message, this.debugMessage, data);
        }

        @Override
        public <TKey, TValue> MapResultBuilder<TResultCode, TKey, TValue> data(final Map<TKey, TValue> data)
        {
            return () -> new DefaultMapResult<>(this.resultCode, this.message, this.debugMessage, data);
        }

        // region: Shortcut methods

        @Override
        public <TData> SingleResult<TResultCode, TData> buildSingleResult()
        {
            return new DefaultSingleResult<>(this.resultCode, this.message, this.debugMessage);
        }

        @Override
        public <TElement> ListResult<TResultCode, TElement> buildListResult()
        {
            return new DefaultListResult<>(this.resultCode, this.message, this.debugMessage);
        }

        @Override
        public <TElement> PagedListResult<TResultCode, TElement> buildPagedListResult()
        {
            return new DefaultPagedListResult<>(this.resultCode, this.message, this.debugMessage);
        }

        @Override
        public <TElement> SetResult<TResultCode, TElement> buildSetResult()
        {
            return new DefaultSetResult<>(this.resultCode, this.message, this.debugMessage);
        }

        @Override
        public <TKey, TValue> MapResult<TResultCode, TKey, TValue> buildMapResult()
        {
            return new DefaultMapResult<>(this.resultCode, this.message, this.debugMessage);
        }

        @Override
        public <TData> SingleResult<TResultCode, TData> buildSingleResult(final TData data)
        {
            return this.data(data).build();
        }

        @Override
        @SuppressWarnings("unchecked")
        public <TElement> ListResult<TResultCode, TElement> buildListResult(final TElement... data)
        {
            return this.data(data).build();
        }

        @Override
        public <TElement> ListResult<TResultCode, TElement> buildListResult(final List<TElement> data)
        {
            return this.data(data).build();
        }

        @Override
        public <TElement> ListResult<TResultCode, TElement> buildListResult(final Collection<TElement> data)
        {
            return this.data(data).toListResult().build();
        }

        @Override
        public <TElement> SetResult<TResultCode, TElement> buildSetResult(final Set<TElement> data)
        {
            return this.data(data).build();
        }

        @Override
        public <TKey, TValue> MapResult<TResultCode, TKey, TValue> buildMapResult(final Map<TKey, TValue> data)
        {
            return this.data(data).build();
        }

        // endregion: Shortcut methods
    }

    private static class CollectionResultBuilderImpl<TResultCode extends ResultCode, TElement, TContainer extends Collection<TElement>>
            implements CollectionResultBuilder<TResultCode, TContainer, TElement>
    {
        final TContainer data;

        final CommonResultBuilderImpl<TResultCode> delegatedBuilder;

        private CollectionResultBuilderImpl(final TContainer data, final CommonResultBuilderImpl<TResultCode> delegatedBuilder)
        {
            this.data = data;
            this.delegatedBuilder = delegatedBuilder;
        }

        @Override
        public ListResultBuilder<TResultCode, TElement> toListResult()
        {
            return this.toListResult(ArrayList::new);
        }

        @Override
        @SuppressWarnings("unchecked")
        public ListResultBuilder<TResultCode, TElement> toListResult(final Function<TContainer, List<TElement>> listFactory)
        {
            if (this.data instanceof List)
            {
                return new ListResultBuilderImpl<>((List<TElement>)this.data, this.delegatedBuilder);
            } else
            {
                return new ListResultBuilderImpl<>(listFactory.apply(this.data), this.delegatedBuilder);
            }
        }

        @Override
        public SetResultBuilder<TResultCode, TElement> toSetResult()
        {
            return this.toSetResult(HashSet::new);
        }

        @Override
        @SuppressWarnings("unchecked")
        public SetResultBuilder<TResultCode, TElement> toSetResult(final Function<TContainer, Set<TElement>> setFactory)
        {
            return () -> this.delegatedBuilder.build((resultCode, message, debugMessage) ->
                    new DefaultSetResult<>(resultCode, message, debugMessage, (this.data instanceof Set)
                            ? (Set<TElement>)this.data
                            : setFactory.apply(this.data)));
        }

        @Override
        public <TKey> MapResultBuilder<TResultCode, TKey, TElement> toMapResult(final Function<TElement, TKey> keyExtractor)
        {
            return this.toMapResult(keyExtractor, x -> x);
        }

        @Override
        public <TKey, TValue> MapResultBuilder<TResultCode, TKey, TValue> toMapResult(final Function<TElement, TKey> keyExtractor,
                                                                                      final Function<TElement, TValue> valueExtractor)
        {
            return () -> this.delegatedBuilder.build((resultCode, message, debugMessage) ->
                    new DefaultMapResult<>(resultCode, message, debugMessage, this.data.stream()
                            .collect(Collectors.toMap(keyExtractor, valueExtractor))));
        }
    }

    private static class ListResultBuilderImpl<TResultCode extends ResultCode, TElement>
            implements ListResultBuilder<TResultCode, TElement>
    {
        final List<TElement> data;

        final CommonResultBuilderImpl<TResultCode> delegatedBuilder;

        ListResultBuilderImpl(final List<TElement> data, final CommonResultBuilderImpl<TResultCode> delegatedBuilder)
        {
            this.data = data;
            this.delegatedBuilder = delegatedBuilder;
        }

        private PagedListResultBuilderImpl<TResultCode, TElement> convertAndSet(final Consumer<PagedListResultBuilderImpl> setter)
        {
            final PagedListResultBuilderImpl<TResultCode, TElement> ret = (this instanceof PagedListResultBuilderImpl)
                    ? (PagedListResultBuilderImpl<TResultCode, TElement>)this
                    : new PagedListResultBuilderImpl<>(this.data, this.delegatedBuilder);
            setter.accept(ret);
            return ret;
        }

        @Override
        public PagedListResultBuilder<TResultCode, TElement> page(final long page, final long pageSize)
        {
            if (page < 1 || pageSize < 1)
            {
                throw new IllegalArgumentException("Property [page] and [pageSize] must start from 1.");
            }

            return this.convertAndSet(x ->
            {
                x.page = page;
                x.pageSize = pageSize;
            });
        }

        @Override
        public PagedListResultBuilder<TResultCode, TElement> page(final Page pageRequest)
        {
            if (pageRequest != null)
            {
                return this.page(pageRequest.getPage(), pageRequest.getPageSize());
            } else
            {
                return this.convertAndSet(x ->
                {
                    x.page = null;
                    x.pageSize = null;
                });
            }
        }

        @Override
        public PagedListResultBuilder<TResultCode, TElement> hasNextPage(final boolean hasNextPage)
        {
            return this.convertAndSet(x -> x.hasNextPage = hasNextPage);
        }

        @Override
        public PagedListResultBuilder<TResultCode, TElement> totalPage(final long totalPage)
        {
            if (totalPage < 0)
            {
                throw new IllegalArgumentException("Property [totalPage] must start from 0.");
            }

            return this.convertAndSet(x -> x.totalPage = totalPage);
        }

        @Override
        public PagedListResultBuilder<TResultCode, TElement> totalSize(final long totalSize)
        {
            if (totalSize < 0)
            {
                throw new IllegalArgumentException("Property [totalSize] must start from 0.");
            }

            return this.convertAndSet(x -> x.totalSize = totalSize);
        }

        @Override
        public ListResult<TResultCode, TElement> build()
        {
            return this.delegatedBuilder.build((resultCode, message, debugMessage) ->
                    new DefaultListResult<>(resultCode, message, debugMessage, this.data));
        }
    }

    private static class PagedListResultBuilderImpl<TResultCode extends ResultCode, TElement>
            extends ListResultBuilderImpl<TResultCode, TElement>
            implements PagedListResultBuilder<TResultCode, TElement>
    {
        private boolean restricted;

        private Long page = null;

        private Long pageSize = null;

        private Long totalPage = null;

        private Long totalSize = null;

        private Boolean hasNextPage = null;

        private PagedListResultBuilderImpl(final List<TElement> data, final CommonResultBuilderImpl<TResultCode> delegatedBuilder)
        {
            super(data, delegatedBuilder);
        }

        @Override
        public PagedListResultBuilder<TResultCode, TElement> restricted(final boolean restricted)
        {
            this.restricted = restricted;
            return this;
        }

        private void check()
        {
            // Check unexpected cases
            if ((this.page == null) ^ (this.pageSize == null))
            {
                throw new IllegalArgumentException("Property [page] and [pageSize] must be null/not-null at the same time.");
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
            if (this.page != null && this.totalPage != null)
            {
                if (this.hasNextPage == null)
                {
                    // Calculates hasNextPage via page and totalPage.
                    this.hasNextPage = this.page < this.totalPage;
                } else
                {
                    if (this.restricted)
                    {
                        // Prevent hasNextPage, page and totalPage are both set.
                        throw new IllegalArgumentException(
                                "Property [hasNextPage] can be inferred from [page] and [totalPage], set [restricted] to false to force apply it.");
                    }
                }
            }
        }

        @Override
        public PagedListResult<TResultCode, TElement> build()
        {
            this.check();
            return this.delegatedBuilder.build((resultCode, message, debugMessage) ->
                    new DefaultPagedListResult<>(resultCode, message, debugMessage, this.data,
                            this.page, this.pageSize, this.hasNextPage, this.totalPage, this.totalSize));
        }
    }

    // endregion: Builder Implements

    // endregion: Builders
}
