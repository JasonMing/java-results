package com.github.jasonnming.results.result.generic;

import java.io.Serializable;
import java.util.List;

import org.apiguardian.api.API;

import com.github.jasonnming.results.result.basic.ResultCode;

/**
 * 包含一个{@link List}类型数据的结果，不含分页信息，通常用于返回全量数据集。<br>
 * 当返回非全量数据集时请使用对应的子类型：
 * <ul>
 *     <li>{@link PagedListResult}</li>
 * </ul>
 *
 * @param <TResultCode> 返回结果码的具体类型
 * @param <TElement>    容器中数据的具体类型，必须为可序列化（{@link Serializable}）对象
 *
 * @apiNote 此类型相对于 {@link com.github.jasonnming.results.result.basic.ListResult} 更进一步细化了 {@link #getResultCode()} 的返回类型到 {@link TResultCode}。

 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface ListResult<TResultCode extends ResultCode, TElement>
        extends CollectionResult<TResultCode, TElement, List<TElement>>, com.github.jasonnming.results.result.basic.ListResult<TElement>
{
}
