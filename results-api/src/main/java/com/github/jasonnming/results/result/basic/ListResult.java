package com.github.jasonnming.results.result.basic;

import java.io.Serializable;
import java.util.List;

import org.apiguardian.api.API;

/**
 * 包含一个{@link List}类型数据的结果，不含分页信息，通常用于返回全量数据集。<br>
 * 当返回非全量数据集时请使用对应的子类型：
 * <ul>
 *     <li>{@link PagedListResult}</li>
 * </ul>
 *
 * @param <TElement> 容器中数据的具体类型，必须为可序列化（{@link Serializable}）对象
 *
 * @apiNote 通过此接口获取结果码（{@link #getResultCode()}）所得到的结果码类型是{@link ResultCode}，
 *          如需指定具体结果码类型的请使用{@link com.github.jasonnming.results.result.generic.ListResult}接口。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface ListResult<TElement>
        extends CollectionResult<TElement, List<TElement>>
{
}