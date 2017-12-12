package com.xy.xsql.entity.core.meta.sql;

import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.core.dialect.type.entity.Entity000;
import com.xy.xsql.entity.core.dialect.type.entity.Entity001;
import com.xy.xsql.entity.core.dialect.type.entity.Entity006;
import com.xy.xsql.entity.model.sql.*;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

public class EntityJoinBuilderTest {

    @Test
    public void testBaseCondition(){
        EntityQueryBuilder eqb = new EntityQueryBuilder<Entity000>(Entity000.class);
        eqb.build();

        //id ColumnMeta
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
                return "e_id";
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
        EntityJoinBuilder<Entity000,Entity006> b = new EntityJoinBuilder<Entity000,Entity006>(
                Entity006.class, new Function<Function<Entity000, ?>, ColumnMeta>() {
            @Override
            public ColumnMeta apply(Function<Entity000, ?> entity000Function) {
                //always return id ColumnMeta
                return cm;
            }
        });
        b.in(eqb);
        b.join(JoinType.base)
                .on(Entity000::getFullPath);
        TableJoin tableJoin = b.build();


        assertEquals(tableJoin.getJoinType(),JoinType.base);
        assertEquals(tableJoin.getJoinConditions().size(),1);
        assertEquals(tableJoin.getJoinConditions().get(0).getLeftColumn(),cm);
        assertEquals(tableJoin.getJoinConditions().get(0).getRightColumn().getName(),"e_id");
    }
}