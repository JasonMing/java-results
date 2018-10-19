/*
 * Copied from https://github.com/JasonMing/java-function.
 */

package com.github.jasonnming.results.internal.functions;

import org.apiguardian.api.API;

/**
 * 函数式接口常用工具方法。
 *
 * @author JasonMing
 * @version 1.0.0
 * @since 1.0.0 (2018-02-10)
 */
@API(status = API.Status.INTERNAL)
public final class Functions
{
    private Functions() {}

    /**
     * 执行{@code f}中的动作，并返回{@code f}的返回值，执行{@code f}时所抛出的异常将被<strong>原样传播</strong>。
     * <p>
     * 此方法的不声明抛出任何受检异常（checked-exception)，但并不会吞掉（ignore）或对原异常进行任何包装（wrap）。
     * <p>
     * 适用场景：
     * <ul>
     *     <li>高层通过AOP拦截器进行统一的异常处理，原生异常并不会被迫进行无用包装，拦截器亦无需使用复杂的逻辑对截获的异常进行类型判断及解包。</li>
     *     <li>函数式工具方法，不再需要限制传入的lambda函数不能抛出受检异常，同时方法自身无需声明抛出受检异常，方便调用者的使用。</li>
     * </ul>
     *
     * @param f   返回类型为{@link R}的任意动作，并可以抛出任意类型异常，抛出的异常会被正常地向上传播。
     * @param <R> 返回值类型
     *
     * @return {@code f}的返回值
     *
     * @since 1.0.0 (2018-02-10)
     */
    public static <R> R propagate(final FuncX<R, ?> f)
    {
        return f.invoke();
    }

    /**
     * 执行{@code f}中的动作，执行{@code f}时所抛出的异常将被<strong>原样传播</strong>。
     * <p>
     * 此方法的不声明抛出任何受检异常（checked-exception)，但并不会吞掉（ignore）或对原异常进行任何包装（wrap）。
     * <p>
     * 适用场景：
     * <ul>
     *     <li>高层通过AOP拦截器进行统一的异常处理，原生异常并不会被迫进行无用包装，拦截器亦无需使用复杂的逻辑对截获的异常进行类型判断及解包。</li>
     *     <li>函数式工具方法，不再需要限制传入的lambda函数不能抛出受检异常，同时方法自身无需声明抛出受检异常，方便调用者的使用。</li>
     * </ul>
     *
     * @param f 返回类型为{@code void}的任意动作，并可以抛出任意类型异常，抛出的异常会被正常地向上传播。
     *
     * @since 1.0.0 (2018-02-10)
     */
    public static void propagate(final ActionX<?> f)
    {
        f.invokeV();
    }
}
