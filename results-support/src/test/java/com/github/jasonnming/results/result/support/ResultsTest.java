package com.github.jasonnming.results.result.support;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jasonnming.results.result.generic.CollectionResult;
import com.github.jasonnming.results.result.generic.CommonResult;
import com.github.jasonnming.results.result.generic.ListResult;
import com.github.jasonnming.results.result.generic.MapResult;
import com.github.jasonnming.results.result.generic.PagedListResult;
import com.github.jasonnming.results.result.generic.SetResult;
import com.github.jasonnming.results.result.generic.SingleResult;
import com.github.jasonnming.results.result.test.TestResultCode;

/**
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-11-14)
 */
class ResultsTest
{
    @ParameterizedTest
    @MethodSource("args_of")
    @DisplayName("Results.of(java.lang.Class<? extends TResult>, TResultCode)")
    void test_of(final Class<? extends com.github.jasonnming.results.result.basic.CommonResult> resultType, final Class<?> concreteType)
    {
        final com.github.jasonnming.results.result.basic.CommonResult result = Results.of(resultType, TestResultCode.SUCCESS);
        Assertions.assertTrue(concreteType.isInstance(result));
        Assertions.assertEquals(TestResultCode.SUCCESS, result.getResultCode());
        Assertions.assertEquals(TestResultCode.SUCCESS.getCode(), result.getCode());
        Assertions.assertEquals(TestResultCode.SUCCESS.getMessage(), result.getMessage());
        Assertions.assertEquals(TestResultCode.SUCCESS.getDebugMessage(), result.getDebugMessage());
    }

    @ParameterizedTest
    @MethodSource("args_of")
    @DisplayName("Results.of(java.lang.Class<? extends TResult>, TResultCode, String)")
    void test_of_message(final Class<? extends com.github.jasonnming.results.result.basic.CommonResult> resultType, final Class<?> concreteType)
    {
        final com.github.jasonnming.results.result.basic.CommonResult result = Results.of(resultType, TestResultCode.SUCCESS, "foo");
        Assertions.assertTrue(concreteType.isInstance(result));
        Assertions.assertEquals(TestResultCode.SUCCESS, result.getResultCode());
        Assertions.assertEquals(TestResultCode.SUCCESS.getCode(), result.getCode());
        Assertions.assertEquals("foo", result.getMessage());
        Assertions.assertEquals(TestResultCode.SUCCESS.getDebugMessage(), result.getDebugMessage());
    }

    @ParameterizedTest
    @MethodSource("args_of")
    @DisplayName("Results.of(java.lang.Class<? extends TResult>, TResultCode, String, String)")
    void test_of_message_debugMessage(final Class<? extends com.github.jasonnming.results.result.basic.CommonResult> resultType, final Class<?> concreteType)
    {
        final com.github.jasonnming.results.result.basic.CommonResult result = Results.of(resultType, TestResultCode.SUCCESS, "foo", "bar");
        Assertions.assertTrue(concreteType.isInstance(result));
        Assertions.assertEquals(TestResultCode.SUCCESS, result.getResultCode());
        Assertions.assertEquals(TestResultCode.SUCCESS.getCode(), result.getCode());
        Assertions.assertEquals("foo", result.getMessage());
        Assertions.assertEquals("bar", result.getDebugMessage());
    }

    private static Stream<Arguments> args_of()
    {
        return Stream.of(
                Arguments.of(com.github.jasonnming.results.result.basic.CommonResult.class, DefaultCommonResult.class),
                Arguments.of(com.github.jasonnming.results.result.basic.SingleResult.class, DefaultSingleResult.class),
                Arguments.of(com.github.jasonnming.results.result.basic.CollectionResult.class, DefaultListResult.class),
                Arguments.of(com.github.jasonnming.results.result.basic.ListResult.class, DefaultListResult.class),
                Arguments.of(com.github.jasonnming.results.result.basic.PagedListResult.class, DefaultPagedListResult.class),
                Arguments.of(com.github.jasonnming.results.result.basic.SetResult.class, DefaultSetResult.class),
                Arguments.of(com.github.jasonnming.results.result.basic.MapResult.class, DefaultMapResult.class),
                Arguments.of(CommonResult.class, DefaultCommonResult.class),
                Arguments.of(SingleResult.class, DefaultSingleResult.class),
                Arguments.of(CollectionResult.class, DefaultListResult.class),
                Arguments.of(ListResult.class, DefaultListResult.class),
                Arguments.of(PagedListResult.class, DefaultPagedListResult.class),
                Arguments.of(SetResult.class, DefaultSetResult.class),
                Arguments.of(MapResult.class, DefaultMapResult.class)
        );
    }
}