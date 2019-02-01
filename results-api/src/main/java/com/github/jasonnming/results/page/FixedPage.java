package com.github.jasonnming.results.page;

/**
 * 可知道总分页数及数据量的分页信息。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-10-22)
 */
public interface FixedPage extends RollingPage
{
    /**
     * 获取以{@link #getSize()}为分页大小的情况下的总页数。
     *
     * @return 总页数，{@code null}表示不明确。
     */
    Long getTotalPage();

    /**
     * 获取实际记录总数。
     *
     * @return 实际记录总数，{@code null}表示不明确。
     */
    Long getTotalSize();
}
