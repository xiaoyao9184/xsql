package com.xy.xsql.orm.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/6.
 */
public class ListUtilTest {

    @Test
    public void testLostElementList(){
        List<String> list1 = new ArrayList<>();
        list1.add("111");
        list1.add("222");

        List<String> list2 = new ArrayList<>();
        list2.add("222");
        list2.add("333");


        List<String> list1Lost = ListUtil.lostElementList(list1, list2, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        Assert.assertEquals(list1Lost.size(),1);
        Assert.assertEquals(list1Lost.get(0),"333");
        Assert.assertEquals(list1Lost.get(0),list2.get(1));

        List<String> list2Lost = ListUtil.lostElementList(list2, list1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        Assert.assertEquals(list2Lost.size(),1);
        Assert.assertEquals(list2Lost.get(0),"111");
        Assert.assertEquals(list2Lost.get(0),list1.get(0));
    }
}
