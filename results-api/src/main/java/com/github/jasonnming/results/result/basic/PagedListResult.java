package com.github.jasonnming.results.result.basic;

import java.io.Serializable;
import java.util.List;

import org.apiguardian.api.API;

import com.github.jasonnming.results.page.Page;

/**
 * 包含一个{@link List}类型数据的结果，包含分页信息。
 *
 * @param <TPage>    分页的具体类型。
 * @param <TElement> 容器中数据的具体类型。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 *
 * @apiNote 通过此接口获取结果码（{@link #getResultCode()}）所得到的结果码类型是{@link ResultCode}，如需指定具体结果码类型的请使用{@link com.github.jasonnming.results.result.generic.PagedListResult}接口。
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.0.0")
public interface PagedListResult<TPage extends Page, TElement>
        extends ListResult<TElement>
{
    /**
     * 获取分页信息。
     *
     * @return 分页信息。
     */
    TPage getPage();
}
