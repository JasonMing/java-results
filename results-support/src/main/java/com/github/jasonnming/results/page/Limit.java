package com.github.jasonnming.results.page;

import org.apiguardian.api.API;

/**
 * 用于数据库的offset和limit。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-10-27)
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.0.0")
public interface Limit
{
    /**
     * 限制开始的记录行数，从0开始。
     *
     * @return 开始的记录行数，从0开始
     */
    long getOffset();

    /**
     * 限制需要的记录行数，从1开始。
     *
     * @return 需要的记录行数，从1开始
     */
    long getLimit();
}
