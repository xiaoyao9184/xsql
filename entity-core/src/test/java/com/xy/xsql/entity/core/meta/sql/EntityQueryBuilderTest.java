package com.xy.xsql.entity.core.meta.sql;

import com.xy.xsql.entity.core.dialect.type.entity.Entity000;
import com.xy.xsql.entity.core.dialect.type.entity.Entity001;
import com.xy.xsql.entity.core.dialect.type.entity.Entity006;
import com.xy.xsql.entity.core.dialect.type.entity.jpa.EntityOne2OneLeft;
import com.xy.xsql.entity.core.dialect.type.entity.jpa.EntityOne2OneRight;
import com.xy.xsql.entity.model.sql.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/10/20.
 */
public class EntityQueryBuilderTest {

    @Test
    public void testBaseCondition(){
        EntityQuery query = EntityQueryBuilder.create(Entity000.class)
                .columnAll()
                .where(Entity000::getId)
                .equalTo("1")
                .build();

//        assertEquals(query.getEntityClass(), Entity000.class);
        assertEquals(query.getEntityConditions().size(), 1);
        assertEquals(query.getEntityConditions().get(0).getClass(), ColumnCondition.class);
        ColumnCondition columnCondition = (ColumnCondition) query.getEntityConditions().get(0);
        assertEquals(columnCondition.getColumnMeta().getName(),"id");
        assertEquals(columnCondition.getPredicateType(), PredicateType.equalTo);
        assertEquals(columnCondition.getValue(),"1");
    }

    @Test
    public void testGroupCondition(){
        EntityQuery query = EntityQueryBuilder.create(Entity000.class)
                .columnAll()
                .where(Entity000::getId)
                .equalTo("1")
                .or()
                    .where(Entity000::getId)
                    .like("1%")
                    .and(Entity000::getFullPath)
                    .like("1%")
                    .back()
                .build();

//        assertEquals(query.getEntityClass(), Entity000.class);
        assertEquals(query.getEntityConditions().size(), 2);
        assertEquals(query.getEntityConditions().get(1).getClass(), GroupCondition.class);
        GroupCondition groupCondition = (GroupCondition) query.getEntityConditions().get(1);
        assertEquals(groupCondition.getInternal().size(),2);
        assertEquals(groupCondition.getUseAnd(), false);
    }

    @Test
    public void testOrder(){
        EntityQuery query = EntityQueryBuilder.create(Entity000.class)
                .columnAll()
                .where(Entity000::getId)
                .equalTo("1")
                .order(Entity000::getId)
                .ase(Entity000::getName)
                .build();

        assertEquals(query.getColumnOrders().size(), 2);
        ColumnOrder columnOrder = query.getColumnOrders().get(0);
        assertEquals(columnOrder.getColumnMeta().getName(),"id");
        assertNull(columnOrder.getAes());
        ColumnOrder columnOrder1 = query.getColumnOrders().get(1);
        assertEquals(columnOrder1.getColumnMeta().getName(),"name");
        assertTrue(columnOrder1.getAes());
    }


    @Test
    public void testJoin(){

//        try {
//            EntityQueryBuilder.create(Entity000.class)
//                    .columnAll()
//                    .where(Entity000::getName)
//                    .like("1%")
//                    .join(Entity006.class)
//                    .on(Entity000::getId)
//                    .column(Entity006::getName,"name_right")
//                    .where(Entity006::getName)
//                    .like("6%")
//                    .build();
//            assert false;
//        } catch (Exception e){
//            assert true;
//        }

        EntityQuery query = EntityQueryBuilder.create(EntityOne2OneLeft.class)
                .columnAll()
                .where(EntityOne2OneLeft::getName)
                .like("1%")
                .join(EntityOne2OneRight.class)
                .on(EntityOne2OneLeft::getId)
                .column(EntityOne2OneRight::getName,"name_right")
                .where(EntityOne2OneRight::getName)
                .like("6%")
                .build();

        assertEquals(query.getTableJoins().size(), 1);
        TableJoin tableJoin = query.getTableJoins().get(0);
        assertEquals(tableJoin.getJoinType(),JoinType.base);
        assertEquals(tableJoin.getTableMeta().getName(),"entity_one_2_one_right");
        assertEquals(tableJoin.getJoinConditions().size(),1);
        JoinCondition joinCondition = tableJoin.getJoinConditions().get(0);
        assertEquals(joinCondition.getLeftColumn().getName(),"id");
        assertEquals(joinCondition.getRightColumn().getName(),"id");
        assertEquals(joinCondition.getLeftColumn().getTableMeta().getName(),"entity_one_2_one_left");
        assertEquals(joinCondition.getRightColumn().getTableMeta().getName(),"entity_one_2_one_right");

    }

    @Test
    public void testTransferJoin(){
        EntityQuery query = EntityQueryBuilder.create(EntityOne2OneLeft.class)
                .columnAll()
                .where(EntityOne2OneLeft::getName)
                .like("1%")
                .join(EntityOne2OneRight.class)
                .on(EntityOne2OneLeft::getId)
                .column(EntityOne2OneRight::getName,"name_right")
                .where(EntityOne2OneRight::getName)
                .like("6%")
                .join(Entity000.class)
                .on(EntityOne2OneRight::getId,EntityOne2OneRight::getName)
                .column(Entity000::getName,"name0")
                .build();

        assertEquals(query.getTableJoins().size(), 2);
        TableJoin tableJoin = query.getTableJoins().get(1);
        assertEquals(tableJoin.getJoinType(),JoinType.base);
        assertEquals(tableJoin.getTableMeta().getName(),"entity000");
        assertEquals(tableJoin.getJoinConditions().size(),2);
        JoinCondition joinCondition = tableJoin.getJoinConditions().get(0);
        assertEquals(joinCondition.getLeftColumn().getName(),"id");
        assertEquals(joinCondition.getRightColumn().getName(),"id");
        assertEquals(joinCondition.getLeftColumn().getTableMeta().getName(),"entity_one_2_one_right");
        assertEquals(joinCondition.getRightColumn().getTableMeta().getName(),"entity000");

        JoinCondition joinCondition1 = tableJoin.getJoinConditions().get(1);
        assertEquals(joinCondition1.getLeftColumn().getName(),"name");
        assertEquals(joinCondition1.getRightColumn().getName(),"name");
        assertEquals(joinCondition1.getLeftColumn().getTableMeta().getName(),"entity_one_2_one_right");
        assertEquals(joinCondition1.getRightColumn().getTableMeta().getName(),"entity000");

    }

}