package com.xy.xsql.orm.core.entity.template;

import com.xy.xsql.orm.data.entity.EntityLink;
import com.xy.xsql.orm.data.entity.EntityTable;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/2.
 */
@Deprecated
public class EntityLinkExpanderTest {

    private EntityTemplate entityTemplate;

    /**
     * EntityTemplate Tree:
     * Byte
     *   Integer
     *     Float
     *     Double
     */
    @Before
    public void init(){
        EntityTemplate template4Float = new EntityTemplate()
                .withClass(Float.class)
                .withTable(new EntityTable().withName("Float"));

        EntityTemplate template4Double = new EntityTemplate()
                .withClass(Double.class)
                .withTable(new EntityTable().withName("Double"));

        List<EntityLink> list4Integer = new ArrayList<>();
        list4Integer.add(new EntityLink().withTemplate(template4Float));
        list4Integer.add(new EntityLink().withTemplate(template4Double));

        EntityTemplate template4Integer = new EntityTemplate()
                .withClass(Integer.class)
                .withTable(new EntityTable().withName("Integer"))
                .withLinks(list4Integer);

        List<EntityLink> linkList4Byte = new ArrayList<>();
        linkList4Byte.add(new EntityLink().withTemplate(template4Integer));

        entityTemplate = new EntityTemplate()
                .withClass(Byte.class)
                .withTable(new EntityTable().withName("Byte"))
                .withLinks(linkList4Byte);
    }

    @Test
    public void testExpandAll(){
        List<EntityLink> listAssert = new EntityLinkExpander()
                .build(entityTemplate);

        Assert.assertEquals(listAssert.size(),3);
    }


    @Test
    public void testExpandDeep(){
        List<EntityLink> listAssert = new EntityLinkExpander()
                .withDeepMax(0)
                .build(entityTemplate);

        Assert.assertEquals(listAssert.size(),1);


        List<EntityLink> listAssert1 = new EntityLinkExpander()
                .withDeepMax(1)
                .build(entityTemplate);

        Assert.assertEquals(listAssert1.size(),3);
    }
}
