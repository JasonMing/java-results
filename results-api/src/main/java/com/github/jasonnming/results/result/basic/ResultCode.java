package com.github.jasonnming.results.result.basic;

import org.apiguardian.api.API;

/**
 * 包含代码及消息的结果代码。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2016-05-30)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public interface ResultCode
{
    /**
     * 以字符串表现的结果代码。
     *
     * @return 结果代码
     */
    String getCode();

    /**
     * 以字符串表现的调试诊断代码，此代码不可展现给用户。
     *
     * @return 调试诊断代码
     */
    default String getDebugCode()
    {
        return this.getCode();
    }
}
