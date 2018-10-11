package com.github.jasonnming.results.result.builder;

import java.util.Map;

import org.apiguardian.api.API;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.generic.MapResult;

/**
 * 构建{@link MapResult}的构建器。
 *
 * @param <TResultCode> 构建结果的结果码类型。
 * @param <TKey>        存放在{@link Map}中的key类型。
 * @param <TValue>      存放在{@link Map}中的value类型。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-07-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface MapResultBuilder<TResultCode extends ResultCode, TKey, TValue>
        extends FinalStageResultBuilder<MapResult<TResultCode, TKey, TValue>>
{
    // No special methods.
}
