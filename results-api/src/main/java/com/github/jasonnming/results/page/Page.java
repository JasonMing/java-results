package com.github.jasonnming.results.page;

/**
 * 分页。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-10-22)
 */
public interface Page
{
    /**
     * 获取当前页码，从1开始。
     *
     * @return 当前页码。
     */
    Long getNumber();

    /**
     * 获取分页大小，从0开始。
     * 此值仅表示分页的容量，并不表示实际数据大小。
     *
     * @return 分页大小。
     */
    Long getSize();
}
