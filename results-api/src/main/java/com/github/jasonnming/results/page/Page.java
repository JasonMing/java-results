package com.github.jasonnming.results.page;

import org.apiguardian.api.API;

/**
 * 分页信息。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-07-20)
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.0.0")
public interface Page
{
    /**
     * 获取期望页码，从1开始。
     *
     * @return 期望页码
     */
    long getPage();

    /**
     * 获取每页期望数据量，从0开始。
     *
     * @return 每页期望数据量
     */
    long getPageSize();
}
