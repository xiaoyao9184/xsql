package com.xy.xsql.entity.core.meta.sql;

import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.core.dialect.type.entity.Entity000;
import com.xy.xsql.entity.model.sql.ColumnCondition;
import com.xy.xsql.entity.model.sql.PredicateType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ColumnConditionBuilderTest {

    @Test
    public void testBaseCondition(){
        ColumnCondition cc = new ColumnCondition();
        ColumnMeta cm = new ColumnMeta() {
            @Override
            public Class getJavaType() {
                return null;
            }

            @Override
            public TableMeta getTableMeta() {
                return null;
            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public String getPackage() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getType() {
                return null;
            }

            @Override
            public String getParentId() {
                return null;
            }
        };
        ColumnConditionBuilder<Entity000,Void> b = new ColumnConditionBuilder<>(cc);
        b.withColumnMeta(cm)
                .withOr()
                .like("1");
        b.build();

        assertFalse(cc.getUseAnd());
        assertEquals(cc.getColumnMeta(), cm);
        assertEquals(cc.getPredicateType(), PredicateType.like);
        assertEquals(cc.getValue(), "1");
    }
}