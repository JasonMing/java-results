package com.github.jasonnming.results.result.generic;

import java.io.Serializable;
import java.util.Set;

import org.apiguardian.api.API;

import com.github.jasonnming.results.result.basic.ResultCode;

/**
 * 包含一个{@link Set}类型数据的结果，用于返回全量不重复的数据集。
 *
 * @param <TResultCode> 返回结果码的具体类型
 * @param <TElement>    容器中数据的具体类型，必须为可序列化（{@link Serializable}）对象
 *
 * @apiNote 此类型相对于 {@link com.github.jasonnming.results.result.basic.SetResult} 更进一步细化了 {@link #getResultCode()} 的返回类型到 {@link TResultCode}。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface SetResult<TResultCode extends ResultCode, TElement>
        extends CollectionResult<TResultCode, TElement, Set<? extends TElement>>, com.github.jasonnming.results.result.basic.SetResult<TElement>
{
}
