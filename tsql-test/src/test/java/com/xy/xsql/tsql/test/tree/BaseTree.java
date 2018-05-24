package com.xy.xsql.tsql.test.tree;

import com.xy.xsql.tsql.builder.chain.queries.SelectBuilder.QuerySpecificationBuilder;
import com.xy.xsql.tsql.model.statements.Insert;
import com.xy.xsql.tsql.model.queries.Select;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_addition;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_string;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Select;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.p_like;
import static com.xy.xsql.tsql.builder.chain.statements.Statements.$Insert;

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

        Select select = $Select()
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
        Insert insert = $Insert()
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
