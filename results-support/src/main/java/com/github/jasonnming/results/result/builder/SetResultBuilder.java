package com.github.jasonnming.results.result.builder;

import java.util.Set;

import org.apiguardian.api.API;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.generic.SetResult;

/**
 * 构建{@link SetResult}的构建器。
 *
 * @param <TResultCode> 构建结果的结果码类型。
 * @param <TElement>    存放在{@link Set}中的数据类型。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-07-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface SetResultBuilder<TResultCode extends ResultCode, TElement>
        extends FinalStageResultBuilder<SetResult<TResultCode, TElement>>
{
    // No special methods.
}
