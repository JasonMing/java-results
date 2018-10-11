package com.github.jasonnming.results.result.support;

import com.github.jasonnming.results.result.basic.ResultCode;
import com.github.jasonnming.results.result.generic.CommonResult;

/**
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2018-09-26)
 */
interface ResultFactory<TResultCode extends ResultCode, TConcreteResult extends CommonResult<TResultCode>>
{
    TConcreteResult create(TResultCode resultCode, String message, String debugMessage);
}
