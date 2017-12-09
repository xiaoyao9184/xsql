package com.xy.xsql.entity.core.meta.table;

import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.core.dialect.none.AllVarcharTypeMapper;
import com.xy.xsql.entity.core.dialect.type.entity.Entity006;
import com.xy.xsql.entity.core.meta.table.JPAClassEntityTableMetaBuilder;
import com.xy.xsql.entity.model.lambda.ClassEntityTableMeta;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/9/25.
 */
public class JPAClassEntityTableMetaBuilderTest {

    @Test
    public void build() throws Exception {
        ClassEntityTableMeta meta = new JPAClassEntityTableMetaBuilder()
                .config(new AllVarcharTypeMapper())
                .build(Entity006.class);

        assertEquals(meta.getJavaType(),Entity006.class);
        assertEquals(meta.getType(),"ClassEntity");
        assertEquals(meta.getName(),"entity_006");
        assertEquals(meta.getColumns().size(),3);

//        ColumnMeta columnMeta = meta.getColumn(Entity006.class.getMethod("getId"));
//        assertEquals(columnMeta.getName(),"e_id");
//
//        assertNotNull(meta.getColumn("full_path"));

    }

}