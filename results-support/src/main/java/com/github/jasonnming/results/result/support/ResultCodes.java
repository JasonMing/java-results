package com.github.jasonnming.results.result.support;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.apiguardian.api.API;

import com.github.jasonnming.results.result.basic.ResultCode;

/**
 * 通用的结果码。
 *
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-07-19)
 */
@API(status = API.Status.STABLE, since = "1.0.0")
public final class ResultCodes
{
    public static final ResultCode SUCCESS = new DefaultResultCode("SUCCESS", "操作成功");

    public static final ResultCode SYSTEM_ERROR = new DefaultResultCode("SYSTEM_ERROR", "系统错误");

    public static final ResultCode REMOTE_ERROR = new DefaultResultCode("REMOTE_ERROR", "远程错误");

    public static final ResultCode ILLEGAL_ARGUMENT = new DefaultResultCode("ILLEGAL_ARGUMENT", "非法参数");

    public static final ResultCode DATA_NOT_EXIST = new DefaultResultCode("DATA_NOT_EXIST", "数据不存在");

    private ResultCodes() { }

    public static ResultCode of(final String code)
    {
        return Holder.PREDEFINED_CODES.containsKey(code)
                ? Holder.PREDEFINED_CODES.get(code)
                : new DefaultResultCode(code);
    }

    private static final class Holder
    {
        static final Map<String, ResultCode> PREDEFINED_CODES;

        static
        {
            PREDEFINED_CODES = Arrays.stream(ResultCodes.class.getFields())
                    .filter(x -> x.getType() == ResultCode.class) // NOTE: Should we use `isAssignableFrom`?
                    .map(x -> {
                        try
                        {
                            return (ResultCode)x.get(null);
                        } catch (IllegalAccessException e)
                        {
                            throw new IllegalStateException(e);
                        }
                    })
                    .collect(Collectors.toMap(x -> x.getCode(), x -> x, (l, r) -> l));
        }
    }
}
