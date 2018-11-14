package com.github.jasonnming.results.page;

import java.util.List;

/**
 * 带有分页信息的扩展{@link List}对象。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-10-25)
 */
public interface PagedList<TPage extends Page, T>
{
    /**
     * 获取分页信息。
     *
     * @return 分页信息。
     */
    TPage getPage();

    /**
     * 获取列表对象。
     *
     * @return 列表对象。
     */
    List<T> getList();
}
