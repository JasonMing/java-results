package com.github.jasonnming.results.result.basic;

import java.io.Serializable;
import java.util.Set;

import org.apiguardian.api.API;

/**
 * 包含一个{@link Set}类型数据的结果，用于返回全量不重复的数据集。
 *
 * @param <TElement> 容器中数据的具体类型，必须为可序列化（{@link Serializable}）对象
 *
 * @apiNote 通过此接口获取结果码（{@link #getResultCode()}）所得到的结果码类型是{@link ResultCode}，
 *          如需指定具体结果码类型的请使用{@link com.github.jasonnming.results.result.generic.SetResult}接口。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface SetResult<TElement>
        extends CollectionResult<TElement, Set<? extends TElement>>
{
}
