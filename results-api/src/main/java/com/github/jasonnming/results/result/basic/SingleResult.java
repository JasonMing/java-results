package com.github.jasonnming.results.result.basic;

import java.io.Serializable;

import org.apiguardian.api.API;

/**
 * 包含单个{@link TData}类型数据的结果。
 * <p>
 * 注：不建议使用此接口返回容器类型的数据，如：{@code SingleResult<List<String>>}，这会导致语意不清。<br>
 *     返回多个数据建议使用以下类型：
 *     <ul>
 *         <li>{@link CollectionResult}<ul>
 *             <li>{@link ListResult}<ul>
 *                 <li>{@link PagedListResult}</li>
 *             </ul></li>
 *             <li>{@link SetResult}</li>
 *         </ul></li>
 *         <li>{@link MapResult}</li>
 *     </ul>
 *
 * @param <TData> 数据的具体类型，必须为可序列化（{@link Serializable}）对象
 *
 * @apiNote 通过此接口获取结果码（{@link #getResultCode()}）所得到的结果码类型是{@link ResultCode}，
 *          如需指定具体结果码类型的请使用{@link com.github.jasonnming.results.result.generic.SingleResult}接口。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface SingleResult<TData>
        extends CommonResult, DataContainer<TData>
{
    /**
     * 返回一个类型为{@link TData}的数据。
     *
     * @return 类型为 {@link TData} 的数据
     */
    @Override
    TData getData();
}
