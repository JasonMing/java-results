/**
 * 提供跨系统通讯的(dubbo) api包的通用返回结果对象。<br>
 * <p>
 * 本包内主要提供如下内容:
 * <ul>
 *     <li>basic - 使用通用的{@link com.github.jasonnming.results.result.basic.ResultCode ResultCode}作为结果码的返回通用结果
 *         <ul>
 *             <li>{@link com.github.jasonnming.results.result.basic.ResultCode ResultCode}</li>
 *             结果码通用抽象，提供访问
 *             {@link com.github.jasonnming.results.result.basic.ResultCode#getCode() 结果码}、
 *             {@link com.github.jasonnming.results.result.basic.ResultCode#getMessage() 结果消息}、
 *             {@link com.github.jasonnming.results.result.basic.ResultCode#getDebugMessage() 调试诊断消息}
 *             的通用能力
 *             <li>{@link com.github.jasonnming.results.result.basic.CommonResult CommonResult}</li>
 *             只包含结果码以及结果消息的结果对象
 *             <li>{@link com.github.jasonnming.results.result.basic.SingleResult SingleResult}</li>
 *             包含单一数据内容的结果对象
 *             <li>{@link com.github.jasonnming.results.result.basic.ListResult ListResult}</li>
 *             包含多项数据内容的结果对象
 *             <li>{@link com.github.jasonnming.results.result.basic.PagedListResult PagedListResult}</li>
 *             包含多项数据内容，经过分页的结果对象
 *             <li>{@link com.github.jasonnming.results.result.basic.SetResult SetResult}</li>
 *             包含多项<b>非重复</b>数据内容
 *             <li>{@link com.github.jasonnming.results.result.basic.MapResult MapResult}</li>
 *             包含一个{@link java.util.Map}内容
 *             <li>{@link com.github.jasonnming.results.result.basic.DataContainer DataContainer}</li>
 *             数据容器通用抽象，提供访问所有{@link com.github.jasonnming.results.result.basic.DataContainer#getData() 结果对象内数据}的能力
 *         </ul>
 *     </li>
 *     <li>generic - 使用指定的enum作为结果码的返回专用结果，所有enum必须实现{@link com.github.jasonnming.results.result.basic.ResultCode ResultCode}接口
 *         <ul>
 *             <li>{@link com.github.jasonnming.results.result.generic.CommonResult CommonResult}</li>
 *             只包含结果码以及结果消息的结果对象
 *             <li>{@link com.github.jasonnming.results.result.generic.SingleResult SingleResult}</li>
 *             包含单一数据内容的结果对象
 *             <li>{@link com.github.jasonnming.results.result.generic.ListResult ListResult}</li>
 *             包含多项数据内容的结果对象
 *             <li>{@link com.github.jasonnming.results.result.generic.PagedListResult PagedListResult}</li>
 *             包含多项数据内容，经过分页的的结果对象
 *             <li>{@link com.github.jasonnming.results.result.generic.SetResult SetResult}</li>
 *             包含多项<b>非重复</b>数据内容
 *             <li>{@link com.github.jasonnming.results.result.generic.MapResult MapResult}</li>
 *             包含一个{@link java.util.Map}内容
 *         </ul>
 *     </li>
 * </ul>
 * </p>
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2016-05-30)
 */
package com.github.jasonnming.results.result;