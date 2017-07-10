package com.xy.xsql.tsql.test.tree;

import com.xy.xsql.tsql.core.statement.dml.SelectBuilder.QuerySpecificationBuilder;
import com.xy.xsql.tsql.model.statement.dml.Insert;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.Expressions.e_number;
import static com.xy.xsql.tsql.core.expression.Expressions.e_string;
import static com.xy.xsql.tsql.core.expression.BinaryExpressions.e_addition;
import static com.xy.xsql.tsql.core.predicate.Predicates.p_like;
import static com.xy.xsql.tsql.core.statement.dml.InsertBuilder.INSERT;
import static com.xy.xsql.tsql.core.statement.dml.SelectBuilder.SELECT;

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
                    .$(p_like(
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
                        .$(p_like(
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
        Insert insert = INSERT()
                .$Into()
                .$(t("test_use_tree_base"))
                .$(c("id"))
                .$(c("parent_id"))
                .$(c("path"))
                .$(c("level"))
                .$(c("name"))
                .$Values()
                    .$(
                            e_string("9"),
                            e_string("1"),
                            e_string("/1/"),
                            e_number(2),
                            e_string("init-9"))
                    .and()
                .build();
        // @formatter:on

    }


}
