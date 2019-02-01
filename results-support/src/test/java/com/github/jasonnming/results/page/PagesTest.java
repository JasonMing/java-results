package com.github.jasonnming.results.page;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.jasonnming.results.page.support.Pages;

/**
 * @author MiNG
 * @version 1.0.0
 * @since 1.0.0 (2017-11-28)
 */
class PagesTest
{
    @Test
    void test_page()
    {
        final Page page = Pages.create(1, 20);

        Assertions.assertEquals((Long)Pages.FIRST_PAGE_INDEX, page.getNumber());
        Assertions.assertEquals((Long)Pages.DEFAULT_PAGE_SIZE, page.getSize());
    }

    @Test
    void test_page_withInvalidArgument()
    {
        final Page page = Pages.create(0, -1);

        Assertions.assertEquals((Long)0L, page.getNumber());
        Assertions.assertEquals((Long)(-1L), page.getSize());
    }

    @Test
    void test_firstPage()
    {
        final Page page = Pages.firstDefaultSizePage();

        Assertions.assertEquals((Long)Pages.FIRST_PAGE_INDEX, page.getNumber());
        Assertions.assertEquals((Long)Pages.DEFAULT_PAGE_SIZE, page.getSize());
    }

    @Test
    void test_firstPage_withSize()
    {
        final Page page = Pages.firstPage(10);

        Assertions.assertEquals((Long)Pages.FIRST_PAGE_INDEX, page.getNumber());
        Assertions.assertEquals((Long)10L, page.getSize());
    }

    @Test
    void test_firstPage_withInvalidArgument()
    {
        final Page page = Pages.firstPage(-1);

        Assertions.assertEquals((Long)Pages.FIRST_PAGE_INDEX, page.getNumber());
        Assertions.assertEquals((Long)(-1L), page.getSize());
    }

    // @Test
    // void test_limit()
    // {
    //     final Limit limit = Pages.limit(0, 1);
    //
    //     Assertions.assertEquals(0, limit.getOffset());
    //     Assertions.assertEquals(1, limit.getLimit());
    // }
    //
    // @Test
    // void test_limit_withInvalidArgument()
    // {
    //     final Limit limit = Pages.limit(-1, -1);
    //
    //     Assertions.assertEquals(-1, limit.getOffset());
    //     Assertions.assertEquals(-1, limit.getLimit());
    // }
    //
    // @Test
    // void test_toLimit()
    // {
    //     final Limit limit = Pages.toLimit(Pages.page(2, 11));
    //
    //     Assertions.assertEquals(11, limit.getOffset());
    //     Assertions.assertEquals(11, limit.getLimit());
    // }
    //
    // @Test
    // void test_toLimit_withNull()
    // {
    //     Assertions.assertNull(Pages.toLimit(null));
    // }
    //
    // @Test
    // void test_toLimit_withInvalidArgument()
    // {
    //     Assertions.assertThrows(IllegalArgumentException.class, () -> Pages.toLimit(Pages.page(0, 11)));
    //     Assertions.assertThrows(IllegalArgumentException.class, () -> Pages.toLimit(Pages.page(1, -1)));
    // }
    //
    // @Test
    // void test_toPage()
    // {
    //     final Page page1 = Pages.toPage(Pages.limit(5, 11));
    //
    //     Assertions.assertEquals(1, page1.getNumber());
    //     Assertions.assertEquals(11, page1.getSize());
    //
    //     final Page page2 = Pages.toPage(Pages.limit(11, 11));
    //
    //     Assertions.assertEquals(2, page2.getNumber());
    //     Assertions.assertEquals(11, page2.getSize());
    // }
    //
    // @Test
    // void test_toPage_withNull()
    // {
    //     Assertions.assertNull(Pages.toPage(null));
    // }
    //
    // @Test
    // void test_toPage_withInvalidArgument()
    // {
    //     Assertions.assertThrows(IllegalArgumentException.class, () -> Pages.toPage(Pages.limit(-1, 11)));
    //     Assertions.assertThrows(IllegalArgumentException.class, () -> Pages.toPage(Pages.limit(11, -1)));
    // }
}