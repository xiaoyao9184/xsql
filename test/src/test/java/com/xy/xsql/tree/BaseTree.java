package com.xy.xsql.tree;

import com.xy.xsql.tsql.core.statement.InsertBuilder;
import com.xy.xsql.tsql.model.statement.dml.Insert;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Test;

import com.xy.xsql.tsql.core.statement.SelectBuilder.*;

import static com.xy.xsql.tsql.core.element.ColumnNameBuilder.c;
import static com.xy.xsql.tsql.core.element.TableNameBuilder.t;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_number;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_string;
import static com.xy.xsql.tsql.core.expression.GroupExpressionBuilder.*;
import static com.xy.xsql.tsql.core.expression.RowValueExpressionBuilder.e_rv;
import static com.xy.xsql.tsql.core.predicate.PredicateBuilder.p_like;
import static com.xy.xsql.tsql.core.statement.SelectBuilder.SELECT;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class BaseTree {

    /**
     *
     SELECT * FROM
     test_use_tree_base,
     (SELECT path + id + '/%' AS p FROM test_use_tree_base
     WHERE id = '1') AS t
     WHERE path LIKE p
     */
    @Test
    public void testSelectOffspring(){
        // @formatter:off

        Select.QuerySpecification select0 = new QuerySpecificationBuilder<Void>()
                .$(e_addition(
                        e_addition(
                                c("path"),
                                c("id")
                        ),
                        e_string("/%")
                ),"p")
                .$From()
                    .$(t("test_use_tree_base"))
                    .and()
                .$Where()
                    .$Predicate(p_like(
                            c("path"),
                            c("p")
                    ))
                    .and()
                .build();

        Select select = SELECT()
                .$Select()
                    .$()
                    .$From()
                        .$(t("test_use_tree_base"))
                        .$(select0,"t")
                            .and()
                        .and()
                    .$Where()
                        .$Predicate(p_like(
                                c("path"),
                                c("p")
                        ))
                        .and()
                    .and()
                .build();
        // @formatter:on

    }

    /**
     *
     INSERT INTO test_use_tree_base
     (id,parent_id,path,level,name)
     --VALUES ('9', '1', '/1/', 2, 'init-9')

     SELECT '11' AS id, id AS parent_id, path + id + '/' AS path, level + 1 AS level, 'init-11' AS name
     FROM test_use_tree_base
     WHERE id = '1'

     SELECT '21' AS id, id AS parent_id, path + id + '/' AS path, level + 1 AS level, 'init-21' AS name
     FROM test_use_tree_base
     WHERE id = '2'
     *
     */
    @Test
    public void testInsert(){
        // @formatter:off
        Insert insert = new InsertBuilder()
                .withInto(true)
                .withTableName("test_use_tree_base")
                .withColumn(c("id"))
                .withColumn(c("parent_id"))
                .withColumn(c("path"))
                .withColumn(c("level"))
                .withColumn(c("name"))
                .withValues()
                    .withItem()
                        .withRowValueExpression((e_rv("9")))
                        .withRowValueExpression(e_rv("1"))
                        .withRowValueExpression(e_rv("/1/"))
                        .withRowValueExpression(e_rv(2))
                        .withRowValueExpression(e_rv("init-9"))
                    .and()
                .and()
                .build();
        // @formatter:on

    }


}
