package com.xy.xsql.tsql.builder.chain.functions.trigger;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.GroupExpression;
import com.xy.xsql.tsql.model.functions.metadata.Object_Id;
import com.xy.xsql.tsql.model.functions.trigger.Trigger_NestLevel;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_object_id;
import static com.xy.xsql.tsql.builder.chain.functions.TriggerFunctions.f_trigger_nestlevel;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Query;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.p_equal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class TriggerNestLevelFunctionTest {


    /**
     * TRIGGER_NESTLEVEL( OBJECT_ID('xyz') , 'AFTER' , 'DML' )
     */
    public Trigger_NestLevel exampleA = f_trigger_nestlevel(
            f_object_id(c_string("xyz")),
            c_string("AFTER"),
            c_string("DML")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getObjectId().getClass(), Object_Id.class);
        assertEquals(exampleA.getTriggerType().getClass(), StringConstant.class);
        assertEquals(exampleA.getTriggerEventCategory().getClass(), StringConstant.class);
    }

    /**
     * TRIGGER_NESTLEVEL ( ( SELECT object_id FROM sys.triggers
     WHERE name = 'abc' ), 'AFTER' , 'DDL' )
     */
    public Trigger_NestLevel exampleB = f_trigger_nestlevel(
            e($Query()
                .$(c("object_id"))
                .$From()
                    .$(t("sys","triggers"))
                    .and()
                .$Where()
                    .$(p_equal(
                            c("name"),c_string("abc")
                    ))
                    .and()
                .build()),
            c_string("AFTER"),
            c_string("DDL")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getObjectId().getClass(), GroupExpression.class);
        assertEquals(exampleB.getTriggerType().getClass(), StringConstant.class);
        assertEquals(exampleB.getTriggerEventCategory().getClass(), StringConstant.class);
    }


    /**
     * trigger_nestlevel()
     */
    public Trigger_NestLevel exampleC = f_trigger_nestlevel();

    @Test
    public void testExampleC(){
        assertNull(exampleC.getObjectId());
        assertNull(exampleC.getTriggerType());
        assertNull(exampleC.getTriggerEventCategory());
    }

}