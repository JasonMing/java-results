package com.github.jasonnming.results.result.basic;

import org.apiguardian.api.API;

/**
 * 数据容器，此接口表明对应类型中包含一个具体的数据对象{@link #getData()}。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface DataContainer<TData>
{
    /**
     * 获取类型为{@link TData}的数据。
     *
     * @return 具体的数据对象
     */
    TData getData();
}
