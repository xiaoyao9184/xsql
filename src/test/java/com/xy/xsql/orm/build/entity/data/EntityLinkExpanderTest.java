package com.xy.xsql.orm.build.entity.data;


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
public class EntityLinkExpanderTest {

    @Test
    public void testExpand(){
        EntityTemplate entityTemplateLong = new EntityTemplate()
                .withClass(Long.class)
                .withTable(new EntityTable().withName("Long"));

        EntityTemplate entityTemplateShort = new EntityTemplate()
                .withClass(Short.class)
                .withTable(new EntityTable().withName("Short"));

        List<EntityLink> listInteger = new ArrayList<>();
        listInteger.add(new EntityLink().withTemplate(entityTemplateLong));
        listInteger.add(new EntityLink().withTemplate(entityTemplateShort));

        EntityTemplate entityTemplateInteger = new EntityTemplate()
                .withClass(Integer.class)
                .withTable(new EntityTable().withName("Integer"))
                .withLinks(listInteger);

        List<EntityLink> list = new ArrayList<>();
        list.add(new EntityLink().withTemplate(entityTemplateInteger));

        EntityTemplate entityTemplate = new EntityTemplate()
                .withClass(Byte.class)
                .withTable(new EntityTable().withName("Byte"))
                .withLinks(list);

        List<EntityLink> listAssert = new EntityLinkExpander()
                .build(entityTemplate);

        Assert.assertEquals(listAssert.size(),3);
    }
}
