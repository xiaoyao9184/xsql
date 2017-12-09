package com.xy.xsql.entity.core.meta.table;

import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.core.dialect.none.AllVarcharTypeMapper;
import com.xy.xsql.entity.core.dialect.type.entity.Entity000;
import com.xy.xsql.entity.core.meta.table.BaseClassEntityTableMetaBuilder;
import com.xy.xsql.entity.model.lambda.ClassEntityTableMeta;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/9/25.
 */
public class BaseClassEntityTableMetaBuilderTest {

    @Test
    public void build() throws NoSuchMethodException {
        ClassEntityTableMeta meta = new BaseClassEntityTableMetaBuilder()
                .config(new AllVarcharTypeMapper())
                .build(Entity000.class);

        assertEquals(meta.getJavaType(),Entity000.class);
        assertEquals(meta.getType(),"ClassEntity");
        assertEquals(meta.getName(),"entity000");
        assertEquals(meta.getColumns().size(),3);
//
//        ColumnMeta columnMeta = meta.getColumn(Entity000.class.getMethod("getId"));
//        assertEquals(columnMeta.getName(),"id");
//
//        assertNotNull(meta.getColumn("full_path"));
    }

}