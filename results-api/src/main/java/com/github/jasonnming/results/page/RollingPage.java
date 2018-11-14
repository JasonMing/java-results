package com.github.jasonnming.results.page;

/**
 * 可知道是否存在下一页的分页信息。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-10-22)
 */
public interface RollingPage extends Page
{
    /**
     * 是否还存在下一页。
     *
     * @return {@code true}表示存在下一页，{@code false}表示没有下一页，{@code null}表示不明确。
     */
    Boolean hasNextPage();
}
