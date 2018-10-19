/*
 * Copied from https://github.com/JasonMing/java-function.
 */

package com.github.jasonnming.results.internal.functions;

import org.apiguardian.api.API;

/**
 * 提供一个无参数，无返回值，声明抛出类型为{@link X}的函数式接口。
 */
@API(status = API.Status.INTERNAL)
@FunctionalInterface
public interface ActionX<X extends Throwable>
        extends Action
{
    /**
     * 为lambda表达式提供简便的类型声明。
     * <p>
     * 通常，要在参数中使用lambda表达式并且指定类型，必须带上所有的泛型参数，导致代码冗余，例如：
     * <p>
     * {@code map((ActionX<X>) () -> foo()); }
     * <p>
     * 通过此方法，可简化为：
     * <p>
     * {@code map(ActionX.of(() -> foo())); }
     *
     * @param f 能适配ActionX的lambda表达式或任意实例
     *
     * @return {@code f}自身
     */
    static <X extends Throwable> ActionX<X> of(final ActionX<X> f)
    {
        return f;
    }

    @Override
    @SuppressWarnings({"RedundantCast", "unchecked"})
    default void invokeV()
    {
        ((ActionX<RuntimeException>)this).invokeVX();
    }

    /**
     * 执行此Action，并不返回任何值，期间可能会抛出类型为{@link X}的异常。
     */
    void invokeVX() throws X;

    /**
     * 扩展Action的返回值到{@code <R>}使其转换为对应的Func，并使用{@code ret}作为返回值。
     *
     * @param ret 作为Function的返回值
     * @param <R> Function返回值的类型
     *
     * @return 参数个数相同的Func
     *
     * @apiNote <code><b>void</b> invoke()</code> &#8658; <code><b>R</b> invoke()</code>
     */
    @Override
    default <R> FuncX<R, X> toFunc(final R ret)
    {
        return () ->
        {
            this.invokeV();
            return ret;
        };
    }
}
