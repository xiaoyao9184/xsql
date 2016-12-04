package com.xy.xsql.orm.build.entity.arg;

import com.xy.xsql.orm.data.entity.EntityColumn;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2016/12/2.
 */
public class MapValueArgsBuilderTest {

    @Test
    public void testBuild(){
        Map<String,Object> map = new HashMap<>();
        map.put("id","id");
        map.put("name","name");
        map.put("code","code");

        List<EntityColumn> list = new ArrayList<>();
        list.add(new EntityColumn().withName("id").withAliasName("id"));
        list.add(new EntityColumn().withName("name").withAliasName("name"));
        list.add(new EntityColumn().withName("code").withAliasName("code"));

        List<Object> columnsAssert = new MapValueArgsBuilder()
                .withColumn(list)
                .build(map);

        Assert.assertEquals(columnsAssert.get(0),map.get("id"));
        Assert.assertEquals(columnsAssert.get(1),map.get("name"));
        Assert.assertEquals(columnsAssert.get(2),map.get("code"));
    }

}
