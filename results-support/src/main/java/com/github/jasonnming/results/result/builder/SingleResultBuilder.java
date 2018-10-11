package com.github.jasonnming.results.result.builder;

import org.apiguardian.api.API;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.generic.SingleResult;

/**
 * 构建{@link SingleResult}的构建器。
 *
 * @param <TResultCode> 构建结果的结果码类型。
 * @param <TData>       存放数据的类型。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-07-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface SingleResultBuilder<TResultCode extends ResultCode, TData>
        extends FinalStageResultBuilder<SingleResult<TResultCode, TData>>
{
    // No special methods.
}
