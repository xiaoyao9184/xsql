package com.xy.xsql.orm.build.entity.data;


import com.xy.xsql.orm.data.entity.EntityColumn;
import com.xy.xsql.orm.data.entity.EntityLink;
import com.xy.xsql.orm.data.entity.EntityTable;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/2.
 */
public class EntityColumnExpanderTest {

    @Test
    public void testExpand(){

        List<EntityColumn> listColumn = new ArrayList<>();
        listColumn.add(new EntityColumn());
        listColumn.add(new EntityColumn());

        EntityTemplate entityTemplateLong = new EntityTemplate()
                .withClass(Long.class)
                .withColumns(listColumn);

        EntityTemplate entityTemplateShort = new EntityTemplate()
                .withClass(Short.class)
                .withColumns(listColumn);

        List<EntityLink> listInteger = new ArrayList<>();
        listInteger.add(new EntityLink().withTemplate(entityTemplateLong));
        listInteger.add(new EntityLink().withTemplate(entityTemplateShort));

        EntityTemplate entityTemplateInteger = new EntityTemplate()
                .withClass(Integer.class)
                .withTable(new EntityTable().withName("Integer"))
                .withLinks(listInteger)
                .withColumns(listColumn);

        List<EntityLink> list = new ArrayList<>();
        list.add(new EntityLink().withTemplate(entityTemplateInteger));

        EntityTemplate entityTemplate = new EntityTemplate()
                .withClass(Byte.class)
                .withTable(new EntityTable().withName("Byte"))
                .withLinks(list)
                .withColumns(listColumn);

        List<EntityColumn> listAssert = new EntityColumnExpander()
                .build(entityTemplate);

        Assert.assertEquals(listAssert.size(),8);
    }
}
