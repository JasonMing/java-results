package com.github.jasonnming.results.result.basic;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

/**
 * 包含一个{@link Map}类型数据的结果。
 *
 * @param <TKey>   Map中key的类型，必须为可序列化（{@link Serializable}）对象
 * @param <TValue> Map中value的类型，必须为可序列化（{@link Serializable}）对象
 *
 * @apiNote 通过此接口获取结果码（{@link #getResultCode()}）所得到的结果码类型是{@link ResultCode}，
 *          如需指定具体结果码类型的请使用{@link com.github.jasonnming.results.result.generic.MapResult}接口。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface MapResult<TKey, TValue>
        extends CommonResult, DataContainer<Map<TKey, ? extends TValue>>, Iterable<Map.Entry<TKey, TValue>>
{
    /**
     * 返回一个类型为{@link Map}的数据。
     *
     * @return {@link Map}类型的数据容器
     *
     * @apiNote 调用返回的 {@link Map} 中所有的修改方法（如：{@link Map#put}）都将不能通过编译，以避免错误地修改返回结果。
     *          如需修改，请先进行复制后再操作，如：{@code Map<TKey, TValue> map = new HashMap<>(result.getData())}。
     */
    @Override
    Map<TKey, ? extends TValue> getData();

    /**
     * @apiNote 返回的迭代器实例使用默认的 {@link Iterator#remove()}实现，任何尝试调用remove()方法的行为都会导致{@link UnsupportedOperationException} 。
     * @see Iterator#remove()
     */
    @Override
    @SuppressWarnings("unchecked")
    default @NotNull Iterator<Map.Entry<TKey, TValue>> iterator()
    {
        // 这里的泛型转换是安全的，因为Java编译器无法正常处理（?）类型的泛型。
        return (Iterator) this.getData().entrySet().iterator();
    }
}
