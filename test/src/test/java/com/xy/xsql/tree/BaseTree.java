package com.xy.xsql.tree;

import com.xy.xsql.orm.core.sql.statements.InsertBuilder;
import com.xy.xsql.orm.core.sql.statements.SelectBuilder;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.statements.dml.Insert;
import com.xy.xsql.orm.data.sql.statements.dml.Select;
import org.junit.Test;

import static com.xy.xsql.orm.core.sql.ExpressionBuilder.e;

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
        Select select = new SelectBuilder()
                .withSelectList()
                    .withSelectItem()
                    .withAll()
                    .out()
                .out()
                .withFrom()
                    .withTableSource().withTable("test_use_tree_base").out()
                    .withTableSource().withDerivedTable().select()
                        .withSelectList().withSelectItem()
                            .withColumnName("path").out().out()
                        .withFrom()
                            .withTableSource().withTable("test_use_tree_base").out()
                            .out()
                        .withWhere()
                            .withSearchCondition()
                                .withPredicate()
                                    .withExpression(e("path"))
                                    .withOperatorEnum(OperatorEnum.LIKE)
                                    .withAndExpression(e("p"))
                                    .out()
                                .out()
                            .out()
                        .out()
                    .out().out()
                .out()
                .build(null);
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
                .withColumnList()
                    .withItem().withName("id").out()
                    .withItem().withName("parent_id").out()
                    .withItem().withName("path").out()
                    .withItem().withName("level").out()
                    .withItem().withName("name").out()
                .out()
                .withValues()
                    .withGroupItem()
                        .withItem().withExpression(e("9")).out()
                        .withItem().withExpression(e("1")).out()
                        .withItem().withExpression(e("/1/")).out()
                        .withItem().withExpression(e(2)).out()
                        .withItem().withExpression(e("init-9")).out()
                    .out()
                .out()
                .build(null);
        // @formatter:on

    }


}
