package com.github.jasonnming.results.result.basic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

/**
 * 包含多个{@link TElement}类型数据的结果。
 *
 * @param <TElement>   容器中数据的具体类型，必须为可序列化（{@link Serializable}）对象
 * @param <TContainer> 容器的具体类型，必须为{@link Collection}或其子类型
 *
 * @apiNote 通过此接口获取结果码（{@link #getResultCode()}）所得到的结果码类型是{@link ResultCode}，
 *          如需指定具体结果码类型的请使用{@link com.github.jasonnming.results.result.generic.CollectionResult}接口。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface CollectionResult<TElement, TContainer extends Collection<? extends TElement>>
        extends CommonResult, DataContainer<TContainer>, Iterable<TElement>
{
    /**
     * 返回一个类型为{@link TContainer}的数据。
     *
     * @return 装载 {@link TElement} 类型为 {@link TContainer} 的数据容器
     *
     * @apiNote 调用返回的 {@link TContainer} 中所有的修改方法（如：{@link Collection#add}）都将不能通过编译，以避免错误地修改返回结果。
     *          如需修改，请先进行复制后再操作，如：{@code Collection<TElement> collection = new ArrayList<>(result.getData())}。
     */
    @Override
    TContainer getData();

    /**
     * @apiNote 返回的迭代器实例使用默认的 {@link Iterator#remove()}实现，任何尝试调用remove()方法的行为都会导致{@link UnsupportedOperationException} 。
     * @see Iterator#remove()
     */
    @Override
    @SuppressWarnings("unchecked")
    default @NotNull Iterator<TElement> iterator()
    {
        // 这里的泛型转换是安全的，因为Java编译器无法正常处理（?）类型的泛型。
        return (Iterator) this.getData().iterator();
    }
}
