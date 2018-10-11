package com.github.jasonnming.results.result.builder;

import org.apiguardian.api.API;

import com.github.jasonnming.results.result.generic.CommonResult;

/**
 * 最终阶段的构建器。
 *
 * @param <TResult> 构建结果的类型。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-10-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface FinalStageResultBuilder<TResult extends CommonResult<?>>
{
    /**
     * 构建完整的{@link TResult}。
     *
     * @return 完整的 {@link TResult}。
     */
    TResult build();
}
