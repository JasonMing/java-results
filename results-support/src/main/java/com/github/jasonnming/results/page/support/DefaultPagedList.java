package com.github.jasonnming.results.page.support;

import java.util.Collections;
import java.util.List;

import com.github.jasonnming.results.page.Page;
import com.github.jasonnming.results.page.PagedList;

/**
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-10-24)
 */
class DefaultPagedList<T, TPage extends Page> implements PagedList<TPage, T>
{
    private final List<T> data;

    private final TPage page;

    DefaultPagedList(final List<T> data, final TPage page)
    {
        this.data = data == null ? Collections.emptyList() : data;
        this.page = page;
    }

    @Override
    public TPage getPage()
    {
        return this.page;
    }

    @Override
    public List<T> getList()
    {
        return this.data;
    }
}
