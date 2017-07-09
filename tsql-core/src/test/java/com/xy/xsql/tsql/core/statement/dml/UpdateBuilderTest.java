package com.xy.xsql.tsql.core.statement.dml;

import com.xy.xsql.tsql.core.clause.OutputBuilder;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.expression.BinaryExpression;
import com.xy.xsql.tsql.model.operator.Compound;
import com.xy.xsql.tsql.model.operator.Operator;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.model.statement.dml.Update;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.WithBuilder.WITH;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.OPTIMIZE_FOR;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.OptimizeForBuilder.OPTIMIZE_FOR_Item;
import static com.xy.xsql.tsql.core.clause.hints.TableHintLimiteds.TABLOCK;
import static com.xy.xsql.tsql.core.clause.subquery.SubQueryBuilder.QUERY;
import static com.xy.xsql.tsql.core.clause.subquery.SubQueryBuilder.QUERYS;
import static com.xy.xsql.tsql.core.clause.subquery.SubQueryBuilder.SUB_QUERY;
import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.BinaryExpressions.*;
import static com.xy.xsql.tsql.core.expression.Expressions.*;
import static com.xy.xsql.tsql.core.predicate.Predicates.*;
import static com.xy.xsql.tsql.core.statement.StatementBuilderFactory.SELECT;
import static com.xy.xsql.tsql.core.statement.dml.UpdateBuilder.SetItemBuilder.*;
import static com.xy.xsql.tsql.core.statement.dml.UpdateBuilder.UPDATE;

/**
 * Created by xiaoyao9184 on 2017/1/7.
 */
public class UpdateBuilderTest {

    /*
    Basic Syntax
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#BasicSyntax
     */

    // @formatter:off
    /**
     * UPDATE Person.Address
    SET ModifiedDate = GETDATE()
     */
    public Update example1A = UPDATE()
                .$(t("Person","Address"))
                .$Set(s(
                        c("ModifiedDate"),
                        e("GETDATE()"))
                )
                .done();
    // @formatter:on

    @Test
    public void testExample1A(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Person","Address"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("ModifiedDate"))
                    .withExpression(e("GETDATE()"))
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"Person.Address");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "ModifiedDate");
        Assert.assertEquals(setItem.getExpression().toString(), "GETDATE()");
    }


    // @formatter:off
    /**
     * UPDATE Sales.SalesPerson
    SET Bonus = 6000, CommissionPct = .10, SalesQuota = NULL
     */
    public Update example1B = UPDATE()
                .$(t("Sales","SalesPerson"))
                .$Set(
                        s(
                                c("Bonus"),
                                e_number(6000)
                        ),
                        s(
                                c("CommissionPct"),
                                e(".10")
                        ),
                        s_null(
                                c("SalesQuota")
                        )
                )
                .done();
    // @formatter:on

    @Test
    public void testExample1B(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Sales","SalesPerson"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("Bonus"))
                    .withExpression(e_number(6000))
                    .and()
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("CommissionPct"))
                    .withExpression(e(".10"))
                    .and()
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("SalesQuota"))
                    .withUseNull(true)
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"Sales.SalesPerson");
        Assert.assertEquals(update.getSets().size(),3);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "Bonus");
        Assert.assertEquals(setItem.getExpression().toString(), "6000");

        Assert.assertEquals(update.getSets().get(1).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem1 = (Update.ColumnAssignmentSet) update.getSets().get(1);
        Assert.assertEquals(setItem1.getColumnName().toString(), "CommissionPct");
        Assert.assertEquals(setItem1.getExpression().toString(), ".10");

        Assert.assertEquals(update.getSets().get(2).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem2 = (Update.ColumnAssignmentSet) update.getSets().get(2);
        Assert.assertEquals(setItem2.getColumnName().toString(), "SalesQuota");
        Assert.assertTrue(setItem2.isUseNull());
    }


    /*
    Limiting the Rows that Are Updated
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#LimitingValues
     */


    // @formatter:off
    /**
     * UPDATE Production.Product
    SET Color = N'Metallic Red'
    WHERE Name LIKE N'Road-250%' AND Color = N'Red'
     */
    public Update example2A = UPDATE()
                .$(t("Production","Product"))
                .$Set(
                        s(
                                c("Color"),
                                e_n_string("Metallic Red")
                        )
                )
                .$Where()
                    .$Predicate(p_like(
                            c("Name"),
                            e_n_string("Road-250%")
                    ))
                    .$_AndPredicate(p_equal(
                            c("Color"),
                            e_n_string("Red")
                    ))
                    .and()
                .done();
    // @formatter:on

    @Test
    public void testExample2A(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Production","Product"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("Color"))
                    .withExpression(e_n_string("Metallic Red"))
                    .and()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Like()
                            .withStringExpression(c("Name"))
                            .withStringExpression(e_n_string("Road-250%"))
                            .and()
                        .withAndOrNotItem()
                            .withAnd()
                            .withPredicate()._Comparison()
                                .withExpression(c("Color"))
                                .withOperator(Operators.EQUAL)
                                .withExpression(e_n_string("Red"))
                                .and()
                            .and()
                        .and()
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"Production.Product");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "Color");
        Assert.assertEquals(setItem.getExpression().toString(), "N'Metallic Red'");
    }


    //2B1 is same as 2A

    // @formatter:off
    private Select select2B = SELECT()
            .$Select()
                .$Top()
                    .$(e_number(10)).and()
                .$(c("BusinessEntityID"))
                .$From()
                    .$(t("HumanResources","Employee"))
                    .and()
                .and()
            .$OrderBy()
                .$(c("HireDate")).$Asc()
                .and()
            .build();

    /**
     * UPDATE HumanResources.Employee
     SET VacationHours = VacationHours + 8
     FROM (SELECT TOP 10 BusinessEntityID FROM HumanResources.Employee
     ORDER BY HireDate ASC) AS th
     WHERE HumanResources.Employee.BusinessEntityID = th.BusinessEntityID
     */
    public Update example2B = UPDATE()
                .$(t("HumanResources","Employee"))
                .$Set(
                        s(
                                c("VacationHours"),
                                e_addition(
                                        e("VacationHours"),
                                        e_number(8))
                        )
                )
                .$From()
                    .$(select2B,"th").$As("th")
                    .and()
                .$Where()
                    .$Predicate(p_equal(
                            c("HumanResources","Employee","BusinessEntityID"),
                            c("th","BusinessEntityID")
                    ))
                    .and()
                .done();
    // @formatter:on

    @Test
    public void testExample2B(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("HumanResources","Employee"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("VacationHours"))
                    .withExpression(e_addition(
                            e("VacationHours"),
                            e_number(8)))
                    .and()
                .withFrom()
                    .withItem()._Derived()
                        .withSubQuery(select2B)
                        .withAs()
                        .withTableAlias("th")
                        .and()
                    .and()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("HumanResources","Employee","BusinessEntityID"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(c("th","BusinessEntityID"))
                            .and()
                        .and()
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"HumanResources.Employee");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "VacationHours");
        Assert.assertEquals(setItem.getExpression().getClass(), BinaryExpression.class);

        Assert.assertNotNull(update.getFrom());
        Assert.assertNotNull(update.getWhere());

    }


    // @formatter:off
    private Select.QueryExpression queryExpression2C = QUERYS()
            .$Select()
                .$(c("b","ProductAssemblyID"))
                .$(c("b","ComponentID"))
                .$(c("b","PerAssemblyQty"))
                .$(c("b","EndDate"))
                .$(e_number(0),"ComponentLevel")
                .$From()
                    .$(t("Production","BillOfMaterials"),"b")
                    .$As("b")
                    .and()
                .$Where()
                    .$Predicate(p_equal(
                            c("b","ProductAssemblyID"),
                            e_number(800)
                    ))
                    .$_AndPredicate(p_is_null(
                            c("b","EndDate")
                    ))
                    .and()
                .and()
            .$Union_All_Select()
                .$(c("bom","ProductAssemblyID"))
                .$(c("bom","ComponentID"))
                .$(c("p","PerAssemblyQty"))
                .$(c("bom","EndDate"))
                .$(e_plus(
                        c("ComponentLevel"),
                        e_number(1)
                ))
                .$From()
                    .$()
                        .$(t("Production","BillOfMaterials"))
                        .$As("bom")
                        .$Inner_Join()
                        .$(t("Parts"))
                        .$As("p")
                        .$On()
                            .$Predicate(p_equal(
                                    c("bom","ProductAssemblyID"),
                                    c("p","ComponentID")
                            ))
                            .$_AndPredicate(p_is_null(
                                    c("bom","EndDate")
                            ))
                            .and()
                        .and()
                    .and()
                .and()
            .build();

    private With with2C = WITH()
            .$("Parts")
                .$("AssemblyID", "ComponentID", "PerAssemblyQty", "EndDate", "ComponentLevel")
                .$As(SUB_QUERY(queryExpression2C))
            .build();
    /**
     * WITH Parts(AssemblyID, ComponentID, PerAssemblyQty, EndDate, ComponentLevel) AS
    (
        SELECT b.ProductAssemblyID, b.ComponentID, b.PerAssemblyQty,
            b.EndDate, 0 AS ComponentLevel
        FROM Production.BillOfMaterials AS b
        WHERE b.ProductAssemblyID = 800
              AND b.EndDate IS NULL
        UNION ALL
        SELECT bom.ProductAssemblyID, bom.ComponentID, p.PerAssemblyQty,
            bom.EndDate, ComponentLevel + 1
        FROM Production.BillOfMaterials AS bom
            INNER JOIN Parts AS p
            ON bom.ProductAssemblyID = p.ComponentID
            AND bom.EndDate IS NULL
    )
    UPDATE Production.BillOfMaterials
    SET PerAssemblyQty = c.PerAssemblyQty * 2
    FROM Production.BillOfMaterials AS c
    JOIN Parts AS d ON c.ProductAssemblyID = d.AssemblyID
    WHERE d.ComponentLevel = 0
     */
    public Update example2C = UPDATE()
                .$With(with2C)
                .$(t("Production","BillOfMaterials"))
                .$Set(
                        s(
                                c("PerAssemblyQty"),
                                e_multiplication(
                                        c("c","PerAssemblyQty"),
                                        e_number(2)
                                )
                        )
                )
                .$From()
                    .$()
                        .$(t("Production","BillOfMaterials")).$As("c")
                        .$Join()
                        .$(t("Parts")).$As("d")
                        .$On()
                            .$Predicate(p_equal(
                                    c("c","ProductAssemblyID"),
                                    c("d","AssemblyID")
                            ))
                            .and()
                        .and()
                    .and()
                .$Where()
                    .$Predicate(p_equal(
                            c("d","ComponentLevel"),
                            e_number(0)
                    ))
                    .and()
                .done();
    // @formatter:on

    @Test
    public void testExample2C(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Production","BillOfMaterials"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("PerAssemblyQty"))
                    .withExpression(e_multiplication(
                            c("c","PerAssemblyQty"),
                            e_number(2)
                    ))
                    .and()
                .withFrom()
                    .withItem()._Joined()
                        .withTableSource()._Base()
                            .withTableName(t("Production","BillOfMaterials"))
                            .withAs()
                            .withTableAlias("c")
                            .and()
                        .withTableSource2()._Base()
                            .withTableName(t("Parts"))
                            .withAs()
                            .withTableAlias("d")
                            .and()
                        .withSearchCondition()
                            .withPredicate()._Comparison()
                                .withExpression(c("c","ProductAssemblyID"))
                                .withOperator(Operators.EQUAL)
                                .withExpression(c("d","AssemblyID"))
                                .and()
                            .and()
                        .and()
                    .and()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("d","ComponentLevel"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(e_number(0))
                            .and()
                        .and()
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"Production.BillOfMaterials");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "PerAssemblyQty");
        Assert.assertEquals(setItem.getExpression().getClass(), BinaryExpression.class);

        Assert.assertNotNull(update.getFrom());
        Assert.assertNotNull(update.getWhere());
    }


    // @formatter:off
    /**
     * UPDATE HumanResources.EmployeePayHistory
    SET PayFrequency = 2
    WHERE CURRENT OF complex_cursor;
    CLOSE complex_cursor;
    DEALLOCATE complex_cursor
     */
    public Update example2D = UPDATE()
                .$(t("HumanResources","EmployeePayHistory"))
                .$Set(
                        s(
                                c("PayFrequency"),
                                e_number(2)
                        )
                )
                //TODO CURRENT OF
                .done();
    // @formatter:on

    @Test
    public void testExample2D(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("HumanResources","EmployeePayHistory"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("PayFrequency"))
                    .withExpression(e_number(2))
                    .and()
                //TODO CURRENT OF
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"HumanResources.EmployeePayHistory");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "PayFrequency");
        Assert.assertEquals(setItem.getExpression().toString(), "2");
    }


    /*
    Setting Column Values
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#ColumnValues
     */


    // @formatter:off
    /**
     * UPDATE Production.Product
    SET ListPrice = ListPrice * 2
     */
    public Update example3A = UPDATE()
                .$(t("Production","Product"))
                .$Set(
                        s(
                                c("ListPrice"),
                                e_multiplication(
                                        c("ListPrice"),
                                        e_number(2)
                                )
                        )
                )
                .done();
    // @formatter:on

    @Test
    public void testExample3A(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Production","Product"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("ListPrice"))
                    .withExpression(
                                e_multiplication(
                                        c("ListPrice"),
                                        e_number(2)
                                ))
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"Production.Product");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "ListPrice");
        Assert.assertEquals(setItem.getExpression().getClass(), BinaryExpression.class);
    }


    // @formatter:off
    /**
     * UPDATE Production.Product
    SET ListPrice += @NewPrice
    WHERE Color = N'Red'
     */
    public Update example3B = UPDATE()
                .$(t("Production","Product"))
                .$Set(
                        s(c("ListPrice"),
                                Compound.ADD_EQUALS,
                                e_variable("NewPrice")
                        )
                )
                .$Where()
                    .$Predicate(p_equal(
                            c("Color"),
                            e_n_string("Red")
                    ))
                    .and()
                .done();
    // @formatter:on

    @Test
    public void testExample3B(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Production","Product"))
                .withSetItem()._ColumnCompound()
                    .withColumnName(c("ListPrice"))
                    .withCompound(Compound.ADD_EQUALS)
                    .withExpression(e_variable("NewPrice"))
                    .and()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("Color"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(e_n_string("Red"))
                            .and()
                        .and()
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"Production.Product");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnCompoundSet.class);
        Update.ColumnCompoundSet setItem = (Update.ColumnCompoundSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "ListPrice");
        Assert.assertEquals(setItem.getCompound(),Compound.ADD_EQUALS);
        Assert.assertEquals(setItem.getExpression().toString(),"@NewPrice");

        Assert.assertNotNull(update.getWhere());
    }


    // @formatter:off
    private Select subQuery3C0 = SELECT()
            .$Select()
                .$(e("MAX(OrderDate)"))
                .$From()
                    .$(t("Sales","SalesOrderHeader"),"so2")
                        .$As("so2")
                    .and()
                .$Where()
                    .$Predicate(p_equal(
                            c("so2","SalesPersonID"),
                            c("so","SalesPersonID")
                    ))
                    .and()
                .and()
            .done();

    private Select subQuery3C = SELECT()
            .$Select()
                .$(e("SUM(so.SubTotal)"))
                .$From()
                    .$(t("Sales","SalesOrderHeader"),"so")
                        .$As("so")
                    .and()
                .$Where()
                    .$Predicate(p_equal(
                            c("so","OrderDate"),
                            e_subquery(subQuery3C0)
                    ))
                    .$_AndPredicate(p_equal(
                            c("Sales","SalesPerson","BusinessEntityID"),
                            c("so","SalesPersonID")
                    ))
                    .and()
                .$GroupBy()
                    .$(c("so","SalesPersonID"))
                    .and()
                .and()
            .done();

    /**
     * UPDATE Sales.SalesPerson
     SET SalesYTD = SalesYTD +
     (SELECT SUM(so.SubTotal)
     FROM Sales.SalesOrderHeader AS so
     WHERE so.OrderDate = (SELECT MAX(OrderDate)
     FROM Sales.SalesOrderHeader AS so2
     WHERE so2.SalesPersonID = so.SalesPersonID)
     AND Sales.SalesPerson.BusinessEntityID = so.SalesPersonID
     GROUP BY so.SalesPersonID)
     */
    public Update example3C = UPDATE()
                .$(t("Sales","SalesPerson"))
                .$Set(
                        s(
                                c("SalesYTD"),
                                e_addition(
                                        c("SalesYTD"),
                                        e_subquery(subQuery3C)
                                )
                        )
                )
                .done();
    // @formatter:on

    @Test
    public void testExample3C(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Sales","SalesPerson"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("SalesYTD"))
                    .withExpression(
                                e_addition(
                                        c("SalesYTD"),
                                        e_subquery(subQuery3C)
                                ))
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"Sales.SalesPerson");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "SalesYTD");
        Assert.assertEquals(setItem.getExpression().getClass(), BinaryExpression.class);
    }


    // @formatter:off
    /**
     * UPDATE Production.Location
    SET CostRate = DEFAULT
    WHERE CostRate > 20.00
     */
    public Update example3D = UPDATE()
                .$(t("Production","Location"))
                .$Set(
                        s_default(c("CostRate"))
                )
                .$Where()
                    .$Predicate(p_greater(
                            c("CostRate"),
                            e_number(20.00)
                    ))
                    .and()
                .done();
    // @formatter:on

    @Test
    public void testExample3D(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Production","Location"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("CostRate"))
                    .withExpression(e_default())
                    .and()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("CostRate"))
                            .withOperator(Operators.GREATER)
                            .withExpression(e_number(20.00))
                            .and()
                        .and()
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"Production.Location");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "CostRate");
        Assert.assertEquals(setItem.getExpression().toString(), "DEFAULT");

        Assert.assertNotNull(update.getWhere());
    }


    /*
    Specifying Target Objects Other than Standard Tables
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#TargetObjects
     */

    // @formatter:off
    /**
     * UPDATE Person.vStateProvinceCountryRegion
    SET CountryRegionName = 'United States of America'
    WHERE CountryRegionName = 'United States'
     */
    public Update example4A = UPDATE()
                .$(t("Person","vStateProvinceCountryRegion"))
                .$Set(
                        s(c("CountryRegionName"),e_string("United States of America"))
                )
                .$Where()
                    .$Predicate(p_equal(
                            c("CountryRegionName"),
                            e_string("United States")
                    ))
                    .and()
                .done();
    // @formatter:on

    @Test
    public void testExample4A(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Person","vStateProvinceCountryRegion"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("CountryRegionName"))
                    .withExpression(e_string("United States of America"))
                    .and()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("CountryRegionName"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(e_string("United States"))
                            .and()
                        .and()
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"Person.vStateProvinceCountryRegion");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "CountryRegionName");
        Assert.assertEquals(setItem.getExpression().toString(), "'United States of America'");

        Assert.assertNotNull(update.getWhere());
    }


    // @formatter:off
    /**
     * UPDATE sr
    SET sr.Name += ' - tool malfunction'
    FROM Production.ScrapReason AS sr
    JOIN Production.WorkOrder AS wo
         ON sr.ScrapReasonID = wo.ScrapReasonID
         AND wo.ScrappedQty > 300
     */
    public Update example4B = UPDATE()
                .$(t("sr"))
                .$Set(
                        s(
                                c("sr","Name"),
                                Compound.ADD_EQUALS,
                                e_string(" - tool malfunction"))
                )
                .$From()
                    .$()
                        .$(t("Production","ScrapReason"))
                        .$As("sr")
                        .$Join()
                        .$(t("Production","WorkOrder"))
                        .$As("wo")
                        .$On()
                            .$Predicate(p_equal(
                                    c("sr","ScrapReasonID"),
                                    c("wo","ScrapReasonID")
                            ))
                            .$_AndPredicate(p_greater(
                                    c("wo","ScrappedQty"),
                                    e_number(300)
                            ))
                            .and()
                        .and()
                    .and()
                .done();
    // @formatter:on

    @Test
    public void testExample4B(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("sr"))
                .withSetItem()._ColumnCompound()
                    .withColumnName(c("sr","CountryRegionName"))
                    .withCompound(Compound.ADD_EQUALS)
                    .withExpression(e_string(" - tool malfunction"))
                    .and()
                .withFrom()
                    .withItem()._Joined()
                        .withTableSource()._Base()
                            .withTableName(t("Production","ScrapReason"))
                            .withAs()
                            .withTableAlias("sr")
                            .and()
                        .withTableSource2()._Base()
                            .withTableName(t("Production","WorkOrder"))
                            .withAs()
                            .withTableAlias("wo")
                            .and()
                        .withSearchCondition()
                            .withPredicate()._Comparison()
                                .withExpression(c("sr","ScrapReasonID"))
                                .withOperator(Operators.EQUAL)
                                .withExpression(c("wo","ScrapReasonID"))
                                .and()
                            .withAndOrNotItem()
                                .withPredicate()._Comparison()
                                    .withExpression(c("wo","ScrappedQty"))
                                    .withOperator(Operators.GREATER)
                                    .withExpression(e_number(300))
                                    .and()
                                .and()
                            .and()
                        .and()
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"sr");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnCompoundSet.class);
        Update.ColumnCompoundSet setItem = (Update.ColumnCompoundSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "sr.CountryRegionName");
        Assert.assertEquals(setItem.getExpression().toString(), "' - tool malfunction'");

        Assert.assertNotNull(update.getFrom());
    }


    // @formatter:off
    /**
     * -- Update columns in the table variable.
    UPDATE @MyTableVar
    SET NewVacationHours = e.VacationHours + 20,
        ModifiedDate = GETDATE()
    FROM HumanResources.Employee AS e
    WHERE e.BusinessEntityID = EmpID
     */
    public Update example4C = UPDATE()
                //TODO support variable
//                .$(e_variable("MyTableVar"))
                .$Set(
                        s(
                                c("NewVacationHours"),
                                e_addition(
                                        c("e","VacationHours"),
                                        e_number(20)
                                )
                        ),
                        s(
                                c("ModifiedDate"),
                                e("GETDATE()")
                        )
                )
                .$From()
                    .$(t("HumanResources","Employee"),"e")
                        .and()
                    .and()
                .done();
    // @formatter:on

    /**
     * UPDATE @MyTableVar
     SET NewVacationHours = e.VacationHours + 20,
     ModifiedDate = GETDATE()
     FROM HumanResources.Employee AS e
     */
    @Test
    public void testExample4C(){
        // @formatter:off
        Update update = new UpdateBuilder()
                //TODO support variable
//                .withTableName(e_variable("MyTableVar"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("NewVacationHours"))
                    .withExpression(
                            e_addition(
                                    c("e","VacationHours"),
                                    e_number(20)
                            )
                    )
                    .and()
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("ModifiedDate"))
                    .withExpression(e("GETDATE()"))
                    .and()
                .withFrom()
                    .withItem()._Base()
                        .withTableName(t("HumanResources","Employee"))
                        .withTableAlias("e")
                        .and()
                    .and()
                .done();
        // @formatter:on

//        Assert.assertEquals(update.getTableName().toString(),"Person.vStateProvinceCountryRegion");
        Assert.assertEquals(update.getSets().size(),2);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "NewVacationHours");
        //TODO
//        Assert.assertEquals(setItem.getExpression().toString(), "' - tool malfunction'");
    }

    /*
    Updating Data Based on Data From Other Tables
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#OtherTables
     */


    // @formatter:off
    private Select.QuerySpecification query5A1 = QUERY()
            .$(e("MAX(OrderDate)"))
            .$From()
                .$(t("Sales","SalesOrderHeader"))
                .and()
            .$Where()
                .$Predicate(p_equal(
                        c("SalesPersonID"),
                        c("sp","BusinessEntityID")
                ))
                .and()
            .build();
    /**
     * UPDATE Sales.SalesPerson
     SET SalesYTD = SalesYTD + SubTotal
     FROM Sales.SalesPerson AS sp
     JOIN Sales.SalesOrderHeader AS so
     ON sp.BusinessEntityID = so.SalesPersonID
     AND so.OrderDate = (SELECT MAX(OrderDate)
     FROM Sales.SalesOrderHeader
     WHERE SalesPersonID = sp.BusinessEntityID)
     */
    public Update example5A1 = UPDATE()
                .$(t("Sales","SalesPerson"))
                .$Set(s(
                        c("SalesYTD"),
                        e_plus(
                                c("SalesYTD"),
                                c("SubTotal"))
                ))
                .$From()
                    .$()
                        .$(t("Sales","SalesPerson"))
                        .$As("sp")
                        .$Join()
                        .$(t("Sales","SalesOrderHeader"))
                        .$As("so")
                        .$On()
                            .$Predicate(p_equal(
                                    c("sp","BusinessEntityID"),
                                    c("so","SalesPersonID")
                            ))
                            .$_AndPredicate(p_equal(
                                    c("so","OrderDate"),
                                    e_subquery(query5A1)
                            ))
                            .and()
                        .and()
                    .and()
                .done();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification query5A2_0 = QUERY()
            .$(e("MAX(OrderDate)"))
            .$From()
                .$(t("Sales","SalesOrderHeader"),"so2")
                    .$As("so2")
                .and()
            .$Where()
                .$Predicate(p_equal(
                        c("so2","SalesPersonID"),
                        c("so","SalesPersonID")
                ))
                .and()
            .build();
    private Select.QuerySpecification query5A2 = QUERY()
            .$(e("SUM(so.SubTotal)"))
            .$From()
                .$(t("Sales","SalesOrderHeader"),"so")
                    .$As("so")
                .and()
            .$Where()
                .$Predicate(p_equal(
                        c("so","OrderDate"),
                        e_subquery(query5A2_0)
                ))
                .$_AndPredicate(p_equal(
                        c("Sales","SalesPerson","BusinessEntityID"),
                        c("so","SalesPersonID")
                ))
                .and()
            .$GroupBy()
                .$(c("so","SalesPersonID"))
                .and()
            .build();
    /**
     * UPDATE Sales.SalesPerson
     SET SalesYTD = SalesYTD +
     (SELECT SUM(so.SubTotal)
     FROM Sales.SalesOrderHeader AS so
     WHERE so.OrderDate = (SELECT MAX(OrderDate)
     FROM Sales.SalesOrderHeader AS so2
     WHERE so2.SalesPersonID = so.SalesPersonID)
     AND Sales.SalesPerson.BusinessEntityID = so.SalesPersonID
     GROUP BY so.SalesPersonID)
     */
    public Update example5A2 = UPDATE()
                .$(t("Sales","SalesPerson"))
                .$Set(s(
                        c("SalesYTD"),
                        e_plus(
                                c("SalesYTD"),
                                e_subquery(query5A2))
                ))
                .done();
    // @formatter:on


    /*
    Updating Rows in a Remote Table
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#RemoteTables
     */


    // @formatter:off
    /**
     * -- Specify the remote data source using a four-part name
     -- in the form linked_server.catalog.schema.object.

     UPDATE MyLinkServer.AdventureWorks2012.HumanResources.Department
     SET GroupName = N'Public Relations'
     WHERE DepartmentID = 4
     */
    public Update example6A = UPDATE()
                .$(t("MyLinkServer","AdventureWorks2012","HumanResources","Department"))
                .$Set(s(
                        c("GroupName"),
                        e_n_string("Public Relations")
                ))
                .$Where()
                    .$Predicate(p_equal(
                            c("DepartmentID"),
                            e_number(4)
                    ))
                    .and()
                .done();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE OPENQUERY (MyLinkServer, 'SELECT GroupName FROM HumanResources.Department WHERE DepartmentID = 4')
     SET GroupName = 'Sales and Marketing'
     */
    public Update example6B = UPDATE()
            //TODO funtion
//                .$(t("MyLinkServer","AdventureWorks2012","HumanResources","Department"))
                .$Set(s(
                        c("GroupName"),
                        e_n_string("Sales and Marketing")
                ))
                .done();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE OPENQUERY (MyLinkServer, 'SELECT GroupName FROM HumanResources.Department WHERE DepartmentID = 4')
    SET GroupName = 'Sales and Marketing'
     */
    public Update example6C = UPDATE()
            //TODO funtion
//                .$(t("MyLinkServer","AdventureWorks2012","HumanResources","Department"))
                .$Set(s(
                        c("GroupName"),
                        e_n_string("Sales and Marketing")
                ))
                .done();
    // @formatter:on

    /*
    Updating Large Object Data Types
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#LOBValues
     */


    // @formatter:off
    /**
     * UPDATE Production.Document
    SET DocumentSummary .WRITE (N'features',28,10)
    OUTPUT deleted.DocumentSummary,
           inserted.DocumentSummary
        INTO @MyTableVar
    WHERE Title = N'Front Reflector Bracket Installation'
     */
    public Update example7A = UPDATE()
                .$(t("Production","Document"))
            //TODO method
//                .$Set(s(
//                        c("DocumentSummary"),
//                        e_n_string("Sales and Marketing")
//                ))
                .$Output()
                    .$Deleted("DocumentSummary")
                    .$Inserted("DocumentSummary")
                    .$Into("MyTableVar")
                    .and()
                .$Where()
                    .$Predicate(p_equal(
                            c("Title"),
                            e_n_string("Front Reflector Bracket Installation")
                    ))
                    .and()
                .done();
    // @formatter:on

    // @formatter:off
    /**
     * -- Replacing NULL value with temporary data.
    UPDATE Production.Document
    SET DocumentSummary = N'Replacing NULL value'
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B1 = UPDATE()
                .$(t("Production","Document"))
                .$Set(s(
                        c("DocumentSummary"),
                        e_n_string("Replacing NULL value")
                ))
                .$Where()
                    .$Predicate(p_equal(
                            c("Title"),
                            e_n_string("Crank Arm and Tire Maintenance")
                    ))
                    .and()
                .done();

    /**
     * -- Replacing temporary data with the correct data. Setting @Length to NULL
    -- truncates all existing data from the @Offset position.
    UPDATE Production.Document
    SET DocumentSummary .WRITE(N'Carefully inspect and maintain the tires and crank arms.',0,NULL)
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B2 = UPDATE()
                .$(t("Production","Document"))
            //TODO method
//                .$Set(s(
//                        c("DocumentSummary"),
//                        e_n_string("Replacing NULL value")
//                ))
                .$Where()
                    .$Predicate(p_equal(
                            c("Title"),
                            e_n_string("Crank Arm and Tire Maintenance")
                    ))
                    .and()
                .done();

    /**
     * -- Appending additional data to the end of the column by setting
    -- @Offset to NULL.
    UPDATE Production.Document
    SET DocumentSummary .WRITE (N' Appending data to the end of the column.', NULL, 0)
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B3 = UPDATE()
                .$(t("Production","Document"))
            //TODO method
//                .$Set(s(
//                        c("DocumentSummary"),
//                        e_n_string("Replacing NULL value")
//                ))
                .$Where()
                    .$Predicate(p_equal(
                            c("Title"),
                            e_n_string("Crank Arm and Tire Maintenance")
                    ))
                    .and()
                .done();

    /**
     * -- Removing all data from @Offset to the end of the existing value by
    -- setting expression to NULL.
    UPDATE Production.Document
    SET DocumentSummary .WRITE (NULL, 56, 0)
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B4 = UPDATE()
                .$(t("Production","Document"))
            //TODO method
//                .$Set(s(
//                        c("DocumentSummary"),
//                        e_n_string("Replacing NULL value")
//                ))
                .$Where()
                    .$Predicate(p_equal(
                            c("Title"),
                            e_n_string("Crank Arm and Tire Maintenance")
                    ))
                    .and()
                .done();

    /**
     * -- Removing partial data beginning at position 9 and ending at
    -- position 21.
    UPDATE Production.Document
    SET DocumentSummary .WRITE ('',9, 12)
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B5 = UPDATE()
                .$(t("Production","Document"))
            //TODO method
//                .$Set(s(
//                        c("DocumentSummary"),
//                        e_n_string("Replacing NULL value")
//                ))
                .$Where()
                    .$Predicate(p_equal(
                            c("Title"),
                            e_n_string("Crank Arm and Tire Maintenance")
                    ))
                    .and()
                .done();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE Production.ProductPhoto
    SET ThumbNailPhoto = (
        SELECT *
        FROM OPENROWSET(BULK 'c:Tires.jpg', SINGLE_BLOB) AS x )
    WHERE ProductPhotoID = 1
     */
    public Update example7C = UPDATE()
                .$(t("Production","ProductPhoto"))
            //TODO OPENROWSET
//                .$Set(s(
//                        c("ThumbNailPhoto"),
//                        e_subquery()
//                ))
                .$Where()
                    .$Predicate(p_equal(
                            c("ProductPhotoID"),
                            e_number(1)
                    ))
                    .and()
                .done();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE Archive.dbo.Records
    SET [Chart] = CAST('Xray 1' as varbinary(max))
    WHERE [SerialNumber] = 2
     */
    public Update example7D = UPDATE()
                .$(t("Archive","dbo","Records"))
                .$Set(s(
                        c("[Chart]"),
                        e("CAST('Xray 1' as varbinary(max)) ")
                ))
                .$Where()
                    .$Predicate(p_equal(
                            c("[SerialNumber]"),
                            e_number(2)
                    ))
                    .and()
                .done();
    // @formatter:on

    /*
    Updating User-defined Types
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#UDTs
     */


    // @formatter:off
    /**
     * UPDATE dbo.Cities
    SET Location = CONVERT(Point, '12.3:46.2')
    WHERE Name = 'Anchorage'
     */
    public Update example8A = UPDATE()
                .$(t("dbo","Cities"))
                .$Set(s(
                        c("Location"),
                        e("CONVERT(Point, '12.3:46.2')")
                ))
                .$Where()
                    .$Predicate(p_equal(
                            c("Name"),
                            e_string("Anchorage")
                    ))
                    .and()
                .done();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE dbo.Cities
    SET Location.SetXY(23.5, 23.5)
    WHERE Name = 'Anchorage'
     */
    public Update example8B = UPDATE()
                .$(t("dbo","Cities"))
            //TODO method
//                .$Set(s(
//                        c("Location"),
//                        e("CONVERT(Point, '12.3:46.2')")
//                ))
                .$Where()
                    .$Predicate(p_equal(
                            c("Name"),
                            e_string("Anchorage")
                    ))
                    .and()
                .done();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE dbo.Cities
    SET Location.X = 23.5
    WHERE Name = 'Anchorage'
     */
    public Update example8C = UPDATE()
                .$(t("dbo","Cities"))
                .$Set(s(
                        c("Location","X"),
                        e_number(23.5)
                ))
                .$Where()
                    .$Predicate(p_equal(
                            c("Name"),
                            e_string("Anchorage")
                    ))
                    .and()
                .done();
    // @formatter:on

    /*
    Overriding the Default Behavior of the Query Optimizer by Using Hints
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#TableHints
     */


    // @formatter:off
    /**
     * UPDATE Production.Product
    WITH (TABLOCK)
    SET ListPrice = ListPrice * 1.10
    WHERE ProductNumber LIKE 'BK-%'
     */
    public Update example9A = UPDATE()
                .$(t("Production","Product"))
                .$With(TABLOCK())
                .$Set(s(
                        c("ListPrice"),
                        e_multiplication(
                                c("ListPrice"),
                                e_number(1.10)
                        )
                ))
                .$Where()
                    .$Predicate(p_like(
                            c("ProductNumber"),
                            e_string("BK-%")
                    ))
                    .and()
                .done();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE Production.Product
    SET ListPrice = ListPrice * 1.10
    WHERE ProductNumber LIKE @Product
    OPTION (OPTIMIZE FOR (@Product = 'BK-%') )
     */
    public Update example9B = UPDATE()
                .$(t("Production","Product"))
                .$Set(s(
                        c("ListPrice"),
                        e_multiplication(
                                c("ListPrice"),
                                e_number(1.10)
                        )
                ))
                .$Where()
                    .$Predicate(p_like(
                            c("ProductNumber"),
                            e_variable("Product")
                    ))
                    .and()
                .$Option()
                    .$(OPTIMIZE_FOR("Product","BK-%"))
                    .and()
                .done();
    // @formatter:on

    /*
    Capturing the Results of the UPDATE Statement
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#CaptureResults
     */


    // @formatter:off
    /**
     * UPDATE TOP (10) HumanResources.Employee
    SET VacationHours = VacationHours * 1.25,
        ModifiedDate = GETDATE()
    OUTPUT inserted.BusinessEntityID,
           deleted.VacationHours,
           inserted.VacationHours,
           inserted.ModifiedDate
    INTO @MyTableVar
     */
    public Update example10A = UPDATE()
                .$Top()
                    .$(e_number(10))
                    .and()
                .$(t("HumanResources","Employee"))
                .$Set(s(
                        c("VacationHours"),
                        e_multiplication(
                                c("VacationHours"),
                                e_number(1.25)
                        )
                ),s(
                        c("ModifiedDate"),
                        e("GETDATE()")
                ))
                .$Output()
                    .$Inserted("BusinessEntityID")
                    .$Deleted("VacationHours")
                    .$Inserted("VacationHours")
                    .$Inserted("ModifiedDate")
                    .$Into("MyTableVar")
                    .and()
                .done();
    // @formatter:on

    /*
    Using UPDATE in other statements
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#Other
     */


    // @formatter:off
    /**
     * UPDATE HumanResources.Employee
    SET VacationHours =
        ( CASE
             WHEN SalariedFlag = 0 THEN VacationHours + @NewHours
             ELSE @NewHours
           END
        )
    WHERE CurrentFlag = 1
     */
    public Update example11A = UPDATE()
                .$(t("HumanResources","Employee"))
                .$Set(s(
                        c("VacationHours"),
                        e_case()
                                .$When(p_equal(
                                        c("SalariedFlag"),
                                        e_number(0))
                                ).$Then(e_plus(
                                        c("VacationHours"),
                                        e_variable("NewHours")
                                )).$Else(e_variable("NewHours"))
                                .build()
                        )
                )
                .$Where()
                    .$Predicate(p_equal(
                        c("CurrentFlag"),
                        e_number(1)
                    ))
                    .and()
                .done();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE HumanResources.Department
        SET Name = N'MyNewName'
        WHERE DepartmentID BETWEEN 1 AND 2
     */
    public Update example11B = UPDATE()
                .$(t("HumanResources","Department"))
                .$Set(s(
                        c("Name"),
                        e_n_string("MyNewName")
                ))
                .$Where()
                    .$Predicate(p_between(
                        c("DepartmentID"),
                        e_number(1),
                        e_number(2)
                    ))
                    .and()
                .done();
    // @formatter:on


    /*
    Examples: SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#examples-includesssdwincludessssdw-mdmd-and-includesspdwincludessspdw-mdmd
     */
}
