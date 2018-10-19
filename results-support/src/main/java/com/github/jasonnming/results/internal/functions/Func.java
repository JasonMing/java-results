/*
 * Copied from https://github.com/JasonMing/java-function.
 */

package com.github.jasonnming.results.internal.functions;

import java.util.function.Supplier;

import org.apiguardian.api.API;

/**
 * 提供一个无参数，返回值为{@link R}，无异常抛出声明的函数式接口。
 */
@API(status = API.Status.INTERNAL)
@FunctionalInterface
public interface Func<R>
        extends Action, Supplier<R>
{
    /**
     * 为lambda表达式提供简便的类型声明。
     * <p>
     * 通常，要在参数中使用lambda表达式并且指定类型，必须带上所有的泛型参数，导致代码冗余，例如：
     * <p>
     * {@code map((Func<R>) () -> foo()); }
     * <p>
     * 通过此方法，可简化为：
     * <p>
     * {@code map(Func.of(() -> foo())); }
     *
     * @param f 能适配Func的lambda表达式或任意实例
     *
     * @return {@code f}自身
     */
    static <R> Func<R> of(final Func<R> f)
    {
        return f;
    }

    /**
     * 执行此Func，并返回类型为{@link R}的返回值。
     */
    R invoke();

    @Override
    default void invokeV()
    {
        this.invoke();
    }

    @Override
    default R get()
    {
        return this.invoke();
    }
}
