package com.xy.xsql.orm.data.page;

import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2016/11/2.
 */
public class PageQueryTest {

    @Test
    public void testStartWithZero(){
        //来源：从0开始，第0页
        PageQuery page = new PageQuery()
                .withStartWithZero(true)
                .withPageStart(0)
                .withPageSize(10);

        assert page.getPageStart() == 0;
        //目标：从0开始，第0页
        assert page.getPageStartWithZero() == 0;
        //目标：从1开始，第1页
        assert page.getPageStartWithOne() == 1;


        //来源：从0开始，第1页
        page = new PageQuery()
                .withStartWithZero(true)
                .withPageStart(1)
                .withPageSize(10);

        assert page.getPageStart() == 1;
        //目标：从0开始，第1页
        assert page.getPageStartWithZero() == 1;
        //目标：从1开始，第2页
        assert page.getPageStartWithOne() == 2;
    }

    @Test
    public void testStartWithOne(){
        //来源：从1开始，第1页
        PageQuery page = new PageQuery()
                .withStartWithZero(false)
                .withPageStart(1)
                .withPageSize(10);

        assert page.getPageStart() == 1;
        //目标：从0开始，第0页
        assert page.getPageStartWithZero() == 0;
        //目标：从1开始，第1页
        assert page.getPageStartWithOne() == 1;


        //来源：从1开始，第2页
        page = new PageQuery()
                .withStartWithZero(false)
                .withPageStart(2)
                .withPageSize(10);

        assert page.getPageStart() == 2;
        //目标：从0开始，第1页
        assert page.getPageStartWithZero() == 1;
        //目标：从1开始，第2页
        assert page.getPageStartWithOne() == 2;
    }

}
