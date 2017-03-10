package com.xy.xsql.orm.core.entity.arg;

import com.xy.xsql.orm.data.entity.EntityColumn;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/2.
 */
public class EntityColumnsArgsBuilderTest {

    @Test
    public void testBuild(){
        TestClass testClass = new TestClass();
        testClass.setId("id");
        testClass.setName("name");
        testClass.setCode("code");

        List<EntityColumn> list = new ArrayList<>();
        list.add(new EntityColumn().withName("id").withAliasName("id"));
        list.add(new EntityColumn().withName("name").withAliasName("name"));
        list.add(new EntityColumn().withName("code").withAliasName("code"));

        List<Object> columnsAssert = new EntityColumnsArgsBuilder<TestClass>()
                .withColumns(list)
                .build(testClass);

        Assert.assertEquals(columnsAssert.get(0),testClass.getId());
        Assert.assertEquals(columnsAssert.get(1),testClass.getName());
        Assert.assertEquals(columnsAssert.get(2),testClass.getCode());
    }

    public static class TestClass {
        private String id;
        private String name;
        private String code;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

}
