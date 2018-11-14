package com.github.jasonnming.results.result.generic;

import java.io.Serializable;
import java.util.List;

import org.apiguardian.api.API;

import com.github.jasonnming.results.page.Page;
import com.github.jasonnming.results.result.basic.ResultCode;

/**
 * 包含一个{@link List}类型数据的结果，包含分页信息。
 *
 * @param <TResultCode> 返回结果码的具体类型。
 * @param <TPage>       分页的具体类型。
 * @param <TElement>    容器中数据的具体类型。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 *
 * @apiNote 此类型相对于 {@link com.github.jasonnming.results.result.basic.PagedListResult} 更进一步细化了 {@link #getResultCode()} 的返回类型到 {@link TResultCode}。
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface PagedListResult<TResultCode extends ResultCode, TPage extends Page, TElement>
        extends ListResult<TResultCode, TElement>, com.github.jasonnming.results.result.basic.PagedListResult<TPage, TElement>
{
}
