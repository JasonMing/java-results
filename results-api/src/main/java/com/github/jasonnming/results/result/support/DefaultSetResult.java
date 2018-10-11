/*
 * Copyright (c) 1997-2017 Guangdong Yueke Software Engineering Co., Ltd. All Rights Reserved.
 */

package com.github.jasonnming.results.result.support;

import java.util.Set;

import org.jetbrains.annotations.Nullable;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.generic.SetResult;

/**
 * 列表结果。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-01-26)
 */
class DefaultSetResult<TResultCode extends ResultCode, TElement>
        extends DefaultCommonResult<TResultCode>
        implements SetResult<TResultCode, TElement>
{
    private static final long serialVersionUID = 0x5cfcad73a2601187L;

    private final Set<TElement> data;

    /**
     * 仅为反序列化使用。
     */
    DefaultSetResult()
    {
        this.data = null;
    }

    /**
     * 使用{@link ResultCode}构建结果对象
     *
     * @param resultCode 结果编码
     */
    DefaultSetResult(final TResultCode resultCode)
    {
        super(resultCode);
        this.data = null;
    }

    /**
     * 使用{@link ResultCode}和自定义的消息构建结果对象
     *
     * @param resultCode 结果编码
     * @param message    自定义消息，为{@code null}时使用{@link ResultCode#getMessage()}作为消息
     */
    DefaultSetResult(final TResultCode resultCode, final String message, final String debugMessage)
    {
        super(resultCode, message, debugMessage);
        this.data = null;
    }

    /**
     * 使用{@link ResultCode}和自定义的消息和{@code data}构建结果对象
     *
     * @param resultCode 结果编码
     * @param message    自定义消息，为{@code null}时使用{@link ResultCode#getMessage()}作为消息
     * @param data       结果返回的列表
     */
    DefaultSetResult(final TResultCode resultCode, @Nullable final String message, @Nullable final String debugMessage, final Set<TElement> data)
    {
        super(resultCode, message, debugMessage);
        this.data = data;
    }

    @Override
    public Set<TElement> getData()
    {
        return this.data;
    }
}
