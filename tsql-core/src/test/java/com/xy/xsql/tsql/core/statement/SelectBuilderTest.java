package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.tsql.core.clause.TableValueConstructorBuilder;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameBuilder.c;
import static com.xy.xsql.tsql.core.element.TableNameBuilder.t;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_number;
import static com.xy.xsql.tsql.core.statement.SelectBuilder.SELECT;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SelectBuilderTest {

    /**
     * SELECT * FROM table
     */
    @Test
    public void testBaseBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withSelectItem()
                    .withAll()
                    .and()
                .withFrom()
                    .withItem()._Base()
                        .withTableName(t("table"))
                        .and()
                    .and()
                .build();

        Assert.assertEquals(select.getFrom().getTableSourceList().size(),1);
        Assert.assertEquals(select.getSelectList().size(),1);
    }

    /**
     * SELECT ALL * FROM table
     */
    @Test
    public void testAllBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withAll()
                .build();

        Assert.assertTrue(select.isUseAll());
    }

    /**
     * SELECT DISTINCT * FROM table
     */
    @Test
    public void testDistinctBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withDistinct()
                .build();

        Assert.assertTrue(select.isUseDistinct());
    }

    /**
     * SELECT TOP 50 * FROM table
     * SELECT TOP 50 PERCENT * FROM table
     */
    @Test
    public void testTopBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withTop()
                    .withExpression(e_number(50))
                    .and()
                .build();

        Assert.assertEquals(select.getTop().getExpression().toString(),"50");

        select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withTop()
                    .withExpression(e_number(50))
                    .withPercent()
                    .and()
                .build();

        Assert.assertTrue(select.getTop().isUsePercent());
    }

    /**
     * SELECT * FROM table,table2
     */
    @Test
    public void test2TableBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withSelectItem()
                    .withAll()
                    .and()
                .withFrom()
                    .withItem()._Base()
                        .withTableName(t("table"))
                        .and()
                    .withItem()._Base()
                        .withTableName(t("table2"))
                        .and()
                    .and()
                .build();

        Assert.assertEquals(select.getFrom().getTableSourceList().size(),2);
    }

    /**
     * SELECT * FROM table LEFT JOIN table2
     */
    @Test
    public void testTableJoinBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withSelectItem()
                    .withAll()
                .and()
                .withFrom()
                    .withItem()._Joined()
                        .withTableSource()._Base()
                            .withTableName(t("table"))
                            .and()
                        .withJoinType(From.JoinType.LEFT_JOIN)
                        .withTableSource2()._Base()
                            .withTableName(t("table2"))
                            .and()
                        .and()
                    .and()
                .build();

        Assert.assertEquals(select.getFrom().getTableSourceList().size(),1);
    }


    /**
     * SELECT * FROM (VALUES (1, 2), (3, 4), (5, 6), (7, 8), (9, 10) ) AS MyTable(a, b)
     */
    @Test
    public void testDerivedTable(){
        // @formatter:off
        TableValueConstructor values = new TableValueConstructorBuilder<Void>()
                .$(e_number(1),e_number(2))
                .$(e_number(3),e_number(4))
                .$(e_number(5),e_number(6))
                .$(e_number(7),e_number(8))
                .$(e_number(9),e_number(10))
                .build();

        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withSelectItem()
                    .withAll()
                    .and()
                .withFrom()
                    .withItem()._Derived()
                        .withValues(values)
                        .and()
                        //TODO derived_table [ [ AS ] table_alias ] [ ( column_alias [ ,...n ] ) ]
                    .and()
                .build();
        // @formatter:on
    }

    /**
     * SELECT *
     FROM Production.Product
     ORDER BY Name ASC;
     SELECT p.*
     FROM Production.Product AS p
     ORDER BY Name ASC;
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        Select select = new SelectBuilder()
                .withQuery()
                    .withQuerySpecification()
                        .withSelectItem()
                            .withAll()
                            .and()
                        .withFrom()
                            .withItem()._Base()
                                .withTableName(t("Production","Product"))
                                .and()
                            .and()
                        .and()
                    .and()
                .withOrderBy()
                    .$(c("p","*"))
                    .and()
                .build();

        Select select1 = new SelectBuilder()
                .withQuery()
                    .withQuerySpecification()
                        .withSelectItem()
                            .withTableAll(t("p"))
                            .and()
                        .withFrom()
                            .withItem()._Base()
                                .withTableName(t("Production","Product"))
                                .withTableAlias("p")
                                .and()
                            .and()
                        .and()
                    .and()
                .withOrderBy()
                    .$(c("Name"))
                    .$Asc()
                    .and()
                .build();

        //parent+quick

        Select quick = SELECT()
//                .$()
//                    .$Select()
//                        .$()
//                        .and()
//                    .and()
                .$Select()
                    .$()
                    .$From()
                        .$()
                            .$(t("Production","Product"))
                            .$As("p")
                            .and()
                        .and()
                    .and()
                .$OrderBy()
                    .$(c("p","*"))
                    .and()
                .build();

        Select quick1 = SELECT()
//                .$()
                .$Select()
                    .$(t("p"))
                    .$From()
                        .$()
                            .$(t("Production","Product"))
                            .$As("p")
                            .and()
                        .and()
                    .and()
//                    .and()
                .$OrderBy()
                    .$(c("Name"))
                    .$Asc()
                    .and()
                .build();
        // @formatter:on

        Select.QuerySpecification query = select.getQueryExpression().getQuerySpecification();
        Assert.assertEquals(query.getSelectList().size(),1);
//        Assert.assertTrue(select.getList().get(0).isUseAll());
//
//        Assert.assertEquals(select1.getList().size(),1);
//        Assert.assertEquals(select1.getList().get(0).getTableViewName().toString(),"p");
//        Assert.assertTrue(select1.getList().get(0).isUseTableAll());
    }
}
