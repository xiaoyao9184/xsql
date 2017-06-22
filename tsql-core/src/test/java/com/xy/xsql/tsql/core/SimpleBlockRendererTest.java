package com.xy.xsql.tsql.core;

import com.xy.xsql.tsql.core.variable.SetVariableBuilder;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.model.variable.SetVariable;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.Expressions.e_string;
import static com.xy.xsql.tsql.core.statement.StatementBuilderFactory.SELECT;

/**
 * Created by xiaoyao9184 on 2017/5/13.
 */
public class SimpleBlockRendererTest {

    /**
     * SELECT
     *  *
     *  FROM test
     *
     */
    @Test
    public void testExample1A(){
        // @formatter:off
        Select select = SELECT()
                .$()
                    .$Select()
                        .$()
                    .$From()
                        .$(t("test"))
                        .and()
                    .and()
                .and()
                .build();

        String sql = new SimpleBlockRenderer().render(select);

        // @formatter:on

        Assert.assertEquals(sql,"SELECT * FROM test");
    }


}
