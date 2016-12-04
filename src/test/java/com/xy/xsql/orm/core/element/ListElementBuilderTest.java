package com.xy.xsql.orm.core.element;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/4.
 */
public class ListElementBuilderTest {

    @Test
    public void testBuild(){
        List<Element> elementList = new ArrayList<>();
        elementList.add(new Column());
        elementList.add(new Column());

        List<Element> elementListAssert = new ListElementBuilder()
                .append(new Table())
                .append(elementList, OtherEnum.DELIMITER)
                .append("string")
                .build();

        Assert.assertTrue(elementListAssert.get(0) instanceof Table);
        Assert.assertTrue(elementListAssert.get(1) instanceof Column);
        Assert.assertTrue(elementListAssert.get(2) instanceof OtherEnum);
        Assert.assertTrue(elementListAssert.get(3) instanceof Column);
        Assert.assertTrue(elementListAssert.get(4) instanceof UnknownString);

    }
}
