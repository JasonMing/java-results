package com.github.jasonnming.results.result.basic;

import org.apiguardian.api.API;
import org.jetbrains.annotations.Nullable;

/**
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-09-26)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface WithMessage
{
    /**
     * 代码所对应的用户可读的提示消息。
     *
     * @return 用户可读的提示消息
     */
    @Nullable
    String getMessage();

    /**
     * 代码所对应的调试诊断消息，此消息不可展现给用户。
     *
     * @return 调试诊断消息
     */
    @Nullable
    default String getDebugMessage()
    {
        return this.getMessage();
    }
}
