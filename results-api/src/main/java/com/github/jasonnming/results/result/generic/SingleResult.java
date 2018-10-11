package com.github.jasonnming.results.result.generic;

import java.io.Serializable;

import org.apiguardian.api.API;

import com.github.jasonnming.results.result.basic.ResultCode;

/**
 * 包含单一数据项的结果。
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
 * @param <TResultCode> 返回结果码的具体类型
 * @param <TData> 数据的具体类型，必须为可序列化（{@link Serializable}）对象
 *
 * @apiNote 此类型相对于 {@link com.github.jasonnming.results.result.basic.SingleResult} 更进一步细化了 {@link #getResultCode()} 的返回类型到 {@link TResultCode}。
 *
 * @author MiNG
 * @version 1.0
 * @since 1.0 (2016-05-30)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface SingleResult<TResultCode extends ResultCode, TData>
        extends CommonResult<TResultCode>, com.github.jasonnming.results.result.basic.SingleResult<TData>
{
}
