package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.tsql.builder.chain.queries.hints.QueryHintBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.operators.Comparison;
import com.xy.xsql.tsql.model.queries.With;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.operators.Compound;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.Update;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Select;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Query;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Querys;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$SubQuery;
import static com.xy.xsql.tsql.builder.chain.queries.UpdateBuilder.SetItemBuilder.*;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Update;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$With;
import static com.xy.xsql.tsql.builder.chain.queries.hints.QueryHintBuilder.$OptimizeFor;
import static com.xy.xsql.tsql.builder.chain.queries.hints.TableHintLimiteds.$Tablock;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.*;
import static org.junit.Assert.*;

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
    public Update example1A = $Update()
                .$(t("Person","Address"))
                .$Set(s(
                        c("ModifiedDate"),
                        e("GETDATE()"))
                )
                .build();
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
                .build();
        // @formatter:on

        assertEquals(update.getTableName().toString(),"Person.Address");
        assertEquals(update.getSets().size(),1);

        assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "ModifiedDate");
        assertEquals(setItem.getExpression().toString(), "GETDATE()");
    }


    // @formatter:off
    /**
     * UPDATE Sales.SalesPerson
    SET Bonus = 6000, CommissionPct = .10, SalesQuota = NULL
     */
    public Update example1B = $Update()
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
                .build();
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
                .build();
        // @formatter:on

        assertEquals(update.getTableName().toString(),"Sales.SalesPerson");
        assertEquals(update.getSets().size(),3);

        assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "Bonus");
        assertTrue(setItem.getExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)setItem.getExpression()).getNumber().toString(), "6000");

        assertEquals(update.getSets().get(1).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem1 = (Update.ColumnAssignmentSet) update.getSets().get(1);
        assertEquals(setItem1.getColumnName().toString(), "CommissionPct");
        assertEquals(setItem1.getExpression().toString(), ".10");

        assertEquals(update.getSets().get(2).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem2 = (Update.ColumnAssignmentSet) update.getSets().get(2);
        assertEquals(setItem2.getColumnName().toString(), "SalesQuota");
        assertTrue(setItem2.isUseNull());
    }


    /*
    Limiting the Rows that Are Updated
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#LimitingValues
     */


    // @formatter:off
    /**
     * UPDATE Production.Product
    SET Color = N'Metallic Red'
    WHERE Name LIKE N'Road-250%' $AND Color = N'Red'
     */
    public Update example2A = $Update()
                .$(t("Production","Product"))
                .$Set(
                        s(
                                c("Color"),
                                e_n_string("Metallic Red")
                        )
                )
                .$Where()
                    .$(p_like(
                            c("Name"),
                            e_n_string("Road-250%")
                    ))
                    .$And(p_equal(
                            c("Color"),
                            e_n_string("Red")
                    ))
                    .and()
                .build();
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
                                .withOperator(Comparison.EQUAL)
                                .withExpression(e_n_string("Red"))
                                .and()
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(update.getTableName().toString(),"Production.Product");
        assertEquals(update.getSets().size(),1);

        assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "Color");
        assertTrue(setItem.getExpression() instanceof StringConstant);
        assertEquals(((StringConstant)setItem.getExpression()).getString(), "Metallic Red");
    }


    //2B1 is same as 2A

    // @formatter:off
    private Select select2B = $Select()
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
    public Update example2B = $Update()
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
                    .$(p_equal(
                            c("HumanResources","Employee","BusinessEntityID"),
                            c("th","BusinessEntityID")
                    ))
                    .and()
                .build();
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
                            .withOperator(Comparison.EQUAL)
                            .withExpression(c("th","BusinessEntityID"))
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(update.getTableName().toString(),"HumanResources.Employee");
        assertEquals(update.getSets().size(),1);

        assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "VacationHours");
        assertEquals(setItem.getExpression().getClass(), BinaryExpression.class);

        assertNotNull(update.getFrom());
        assertNotNull(update.getWhere());

    }


    // @formatter:off
    private Select.QueryExpression queryExpression2C = $Querys()
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
                    .$(p_equal(
                            c("b","ProductAssemblyID"),
                            e_number(800)
                    ))
                    .$And(p_is_null(
                            c("b","EndDate")
                    ))
                    .and()
                .and()
            .$UnionAllSelect()
                .$(c("bom","ProductAssemblyID"))
                .$(c("bom","ComponentID"))
                .$(c("p","PerAssemblyQty"))
                .$(c("bom","EndDate"))
                .$(e_addition(
                        c("ComponentLevel"),
                        e_number(1)
                ))
                .$From()
                    .$()
                        .$(t("Production","BillOfMaterials"))
                        .$As("bom")
                        .$InnerJoin()
                        .$(t("Parts"))
                        .$As("p")
                        .$On()
                            .$(p_equal(
                                    c("bom","ProductAssemblyID"),
                                    c("p","ComponentID")
                            ))
                            .$And(p_is_null(
                                    c("bom","EndDate")
                            ))
                            .and()
                        .and()
                    .and()
                .and()
            .build();

    private With with2C = $With()
            .$("Parts")
                .$("AssemblyID", "ComponentID", "PerAssemblyQty", "EndDate", "ComponentLevel")
                .$As(Queries.$SubQuery(queryExpression2C))
            .build();
    /**
     * WITH Parts(AssemblyID, ComponentID, PerAssemblyQty, EndDate, ComponentLevel) AS
    (
        SELECT b.ProductAssemblyID, b.ComponentID, b.PerAssemblyQty,
            b.EndDate, 0 AS ComponentLevel
        FROM Production.BillOfMaterials AS b
        WHERE b.ProductAssemblyID = 800
              $AND b.EndDate IS NULL
        UNION ALL
        SELECT bom.ProductAssemblyID, bom.ComponentID, p.PerAssemblyQty,
            bom.EndDate, ComponentLevel + 1
        FROM Production.BillOfMaterials AS bom
            INNER JOIN Parts AS p
            ON bom.ProductAssemblyID = p.ComponentID
            $AND bom.EndDate IS NULL
    )
    UPDATE Production.BillOfMaterials
    SET PerAssemblyQty = c.PerAssemblyQty * 2
    FROM Production.BillOfMaterials AS c
    JOIN Parts AS d ON c.ProductAssemblyID = d.AssemblyID
    WHERE d.ComponentLevel = 0
     */
    public Update example2C = $Update()
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
                            .$(p_equal(
                                    c("c","ProductAssemblyID"),
                                    c("d","AssemblyID")
                            ))
                            .and()
                        .and()
                    .and()
                .$Where()
                    .$(p_equal(
                            c("d","ComponentLevel"),
                            e_number(0)
                    ))
                    .and()
                .build();
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
                                .withOperator(Comparison.EQUAL)
                                .withExpression(c("d","AssemblyID"))
                                .and()
                            .and()
                        .and()
                    .and()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("d","ComponentLevel"))
                            .withOperator(Comparison.EQUAL)
                            .withExpression(e_number(0))
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(update.getTableName().toString(),"Production.BillOfMaterials");
        assertEquals(update.getSets().size(),1);

        assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "PerAssemblyQty");
        assertEquals(setItem.getExpression().getClass(), BinaryExpression.class);

        assertNotNull(update.getFrom());
        assertNotNull(update.getWhere());
    }


    // @formatter:off
    /**
     * UPDATE HumanResources.EmployeePayHistory
    SET PayFrequency = 2
    WHERE CURRENT OF complex_cursor;
    CLOSE complex_cursor;
    DEALLOCATE complex_cursor
     */
    public Update example2D = $Update()
                .$(t("HumanResources","EmployeePayHistory"))
                .$Set(
                        s(
                                c("PayFrequency"),
                                e_number(2)
                        )
                )
                //TODO CURRENT OF
                .build();
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
                .build();
        // @formatter:on

        assertEquals(update.getTableName().toString(),"HumanResources.EmployeePayHistory");
        assertEquals(update.getSets().size(),1);

        assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "PayFrequency");
        assertTrue(setItem.getExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)setItem.getExpression()).getNumber().toString(), "2");
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
    public Update example3A = $Update()
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
                .build();
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
                .build();
        // @formatter:on

        assertEquals(update.getTableName().toString(),"Production.Product");
        assertEquals(update.getSets().size(),1);

        assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "ListPrice");
        assertEquals(setItem.getExpression().getClass(), BinaryExpression.class);
    }


    // @formatter:off
    /**
     * UPDATE Production.Product
    SET ListPrice += @NewPrice
    WHERE Color = N'Red'
     */
    public Update example3B = $Update()
                .$(t("Production","Product"))
                .$Set(
                        s(c("ListPrice"),
                                Compound.ADD_ASSIGNMENT,
                                e_variable("NewPrice")
                        )
                )
                .$Where()
                    .$(p_equal(
                            c("Color"),
                            e_n_string("Red")
                    ))
                    .and()
                .build();
    // @formatter:on

    @Test
    public void testExample3B(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Production","Product"))
                .withSetItem()._ColumnCompound()
                    .withColumnName(c("ListPrice"))
                    .withCompound(Compound.ADD_ASSIGNMENT)
                    .withExpression(e_variable("NewPrice"))
                    .and()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("Color"))
                            .withOperator(Comparison.EQUAL)
                            .withExpression(e_n_string("Red"))
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(update.getTableName().toString(),"Production.Product");
        assertEquals(update.getSets().size(),1);

        assertEquals(update.getSets().get(0).getClass(), Update.ColumnCompoundSet.class);
        Update.ColumnCompoundSet setItem = (Update.ColumnCompoundSet) update.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "ListPrice");
        assertEquals(setItem.getCompound(),Compound.ADD_ASSIGNMENT);
        assertEquals(setItem.getExpression().toString(),"@NewPrice");

        assertNotNull(update.getWhere());
    }


    // @formatter:off
    private Select subQuery3C0 = $Select()
            .$Select()
                .$(e("MAX(OrderDate)"))
                .$From()
                    .$(t("Sales","SalesOrderHeader"),"so2")
                        .$As("so2")
                    .and()
                .$Where()
                    .$(p_equal(
                            c("so2","SalesPersonID"),
                            c("so","SalesPersonID")
                    ))
                    .and()
                .and()
            .build();

    private Select subQuery3C = $Select()
            .$Select()
                .$(e("SUM(so.SubTotal)"))
                .$From()
                    .$(t("Sales","SalesOrderHeader"),"so")
                        .$As("so")
                    .and()
                .$Where()
                    .$(p_equal(
                            c("so","OrderDate"),
                            e_subquery(subQuery3C0)
                    ))
                    .$And(p_equal(
                            c("Sales","SalesPerson","BusinessEntityID"),
                            c("so","SalesPersonID")
                    ))
                    .and()
                .$GroupBy()
                    .$(c("so","SalesPersonID"))
                    .and()
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
     $AND Sales.SalesPerson.BusinessEntityID = so.SalesPersonID
     GROUP BY so.SalesPersonID)
     */
    public Update example3C = $Update()
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
                .build();
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
                .build();
        // @formatter:on

        assertEquals(update.getTableName().toString(),"Sales.SalesPerson");
        assertEquals(update.getSets().size(),1);

        assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "SalesYTD");
        assertEquals(setItem.getExpression().getClass(), BinaryExpression.class);
    }


    // @formatter:off
    /**
     * UPDATE Production.Location
    SET CostRate = DEFAULT
    WHERE CostRate > 20.00
     */
    public Update example3D = $Update()
                .$(t("Production","Location"))
                .$Set(
                        s_default(c("CostRate"))
                )
                .$Where()
                    .$(p_greater(
                            c("CostRate"),
                            e_number(20.00)
                    ))
                    .and()
                .build();
    // @formatter:on

    @Test
    public void testExample3D(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Production","Location"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("CostRate"))
                    .withUseDefault(true)
                    .and()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("CostRate"))
                            .withOperator(Comparison.GREATER)
                            .withExpression(e_number(20.00))
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(update.getTableName().toString(),"Production.Location");
        assertEquals(update.getSets().size(),1);

        assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "CostRate");
        assertTrue(setItem.isUseDefault());

        assertNotNull(update.getWhere());
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
    public Update example4A = $Update()
                .$(t("Person","vStateProvinceCountryRegion"))
                .$Set(
                        s(c("CountryRegionName"),e_string("United States of America"))
                )
                .$Where()
                    .$(p_equal(
                            c("CountryRegionName"),
                            e_string("United States")
                    ))
                    .and()
                .build();
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
                            .withOperator(Comparison.EQUAL)
                            .withExpression(e_string("United States"))
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(update.getTableName().toString(),"Person.vStateProvinceCountryRegion");
        assertEquals(update.getSets().size(),1);

        assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "CountryRegionName");
        assertTrue(setItem.getExpression() instanceof StringConstant);
        assertEquals(((StringConstant)setItem.getExpression()).getString(), "United States of America");

        assertNotNull(update.getWhere());
    }


    // @formatter:off
    /**
     * UPDATE sr
    SET sr.Name += ' - tool malfunction'
    FROM Production.ScrapReason AS sr
    JOIN Production.WorkOrder AS wo
         ON sr.ScrapReasonID = wo.ScrapReasonID
         $AND wo.ScrappedQty > 300
     */
    public Update example4B = $Update()
                .$(t("sr"))
                .$Set(
                        s(
                                c("sr","Name"),
                                Compound.ADD_ASSIGNMENT,
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
                            .$(p_equal(
                                    c("sr","ScrapReasonID"),
                                    c("wo","ScrapReasonID")
                            ))
                            .$And(p_greater(
                                    c("wo","ScrappedQty"),
                                    e_number(300)
                            ))
                            .and()
                        .and()
                    .and()
                .build();
    // @formatter:on

    @Test
    public void testExample4B(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("sr"))
                .withSetItem()._ColumnCompound()
                    .withColumnName(c("sr","Name"))
                    .withCompound(Compound.ADD_ASSIGNMENT)
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
                                .withOperator(Comparison.EQUAL)
                                .withExpression(c("wo","ScrapReasonID"))
                                .and()
                            .withAndOrNotItem()
                                .withPredicate()._Comparison()
                                    .withExpression(c("wo","ScrappedQty"))
                                    .withOperator(Comparison.GREATER)
                                    .withExpression(e_number(300))
                                    .and()
                                .and()
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(update.getTableName().toString(),"sr");
        assertEquals(update.getSets().size(),1);

        assertEquals(update.getSets().get(0).getClass(), Update.ColumnCompoundSet.class);
        Update.ColumnCompoundSet setItem = (Update.ColumnCompoundSet) update.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "sr.Name");
        assertTrue(setItem.getExpression() instanceof StringConstant);
        assertEquals(((StringConstant)setItem.getExpression()).getString(), " - tool malfunction");

        assertNotNull(update.getFrom());
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
    public Update example4C = $Update()
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
                .build();
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
                .build();
        // @formatter:on

//        assertEquals(update.getTableName().toString(),"Person.vStateProvinceCountryRegion");
        assertEquals(update.getSets().size(),2);

        assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "NewVacationHours");
        //TODO
//        assertEquals(setItem.getExpression().toString(), "' - tool malfunction'");
    }

    /*
    Updating Data Based on Data From Other Tables
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#OtherTables
     */


    // @formatter:off
    private Select.QuerySpecification query5A1 = $Query()
            .$(e("MAX(OrderDate)"))
            .$From()
                .$(t("Sales","SalesOrderHeader"))
                .and()
            .$Where()
                .$(p_equal(
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
     $AND so.OrderDate = (SELECT MAX(OrderDate)
     FROM Sales.SalesOrderHeader
     WHERE SalesPersonID = sp.BusinessEntityID)
     */
    public Update example5A1 = $Update()
                .$(t("Sales","SalesPerson"))
                .$Set(s(
                        c("SalesYTD"),
                        e_addition(
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
                            .$(p_equal(
                                    c("sp","BusinessEntityID"),
                                    c("so","SalesPersonID")
                            ))
                            .$And(p_equal(
                                    c("so","OrderDate"),
                                    e_subquery(query5A1)
                            ))
                            .and()
                        .and()
                    .and()
                .build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification query5A2_0 = $Query()
            .$(e("MAX(OrderDate)"))
            .$From()
                .$(t("Sales","SalesOrderHeader"),"so2")
                    .$As("so2")
                .and()
            .$Where()
                .$(p_equal(
                        c("so2","SalesPersonID"),
                        c("so","SalesPersonID")
                ))
                .and()
            .build();
    private Select.QuerySpecification query5A2 = $Query()
            .$(e("SUM(so.SubTotal)"))
            .$From()
                .$(t("Sales","SalesOrderHeader"),"so")
                    .$As("so")
                .and()
            .$Where()
                .$(p_equal(
                        c("so","OrderDate"),
                        e_subquery(query5A2_0)
                ))
                .$And(p_equal(
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
     $AND Sales.SalesPerson.BusinessEntityID = so.SalesPersonID
     GROUP BY so.SalesPersonID)
     */
    public Update example5A2 = $Update()
                .$(t("Sales","SalesPerson"))
                .$Set(s(
                        c("SalesYTD"),
                        e_addition(
                                c("SalesYTD"),
                                e_subquery(query5A2))
                ))
                .build();
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
    public Update example6A = $Update()
                .$(t("MyLinkServer","AdventureWorks2012","HumanResources","Department"))
                .$Set(s(
                        c("GroupName"),
                        e_n_string("Public Relations")
                ))
                .$Where()
                    .$(p_equal(
                            c("DepartmentID"),
                            e_number(4)
                    ))
                    .and()
                .build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE OPENQUERY (MyLinkServer, 'SELECT GroupName FROM HumanResources.Department WHERE DepartmentID = 4')
     SET GroupName = 'Sales and Marketing'
     */
    public Update example6B = $Update()
            //TODO funtion
//                .$(t("MyLinkServer","AdventureWorks2012","HumanResources","Department"))
                .$Set(s(
                        c("GroupName"),
                        e_n_string("Sales and Marketing")
                ))
                .build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE OPENQUERY (MyLinkServer, 'SELECT GroupName FROM HumanResources.Department WHERE DepartmentID = 4')
    SET GroupName = 'Sales and Marketing'
     */
    public Update example6C = $Update()
            //TODO funtion
//                .$(t("MyLinkServer","AdventureWorks2012","HumanResources","Department"))
                .$Set(s(
                        c("GroupName"),
                        e_n_string("Sales and Marketing")
                ))
                .build();
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
    public Update example7A = $Update()
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
                    .$(p_equal(
                            c("Title"),
                            e_n_string("Front Reflector Bracket Installation")
                    ))
                    .and()
                .build();
    // @formatter:on

    // @formatter:off
    /**
     * -- Replacing NULL value with temporary data.
    UPDATE Production.Document
    SET DocumentSummary = N'Replacing NULL value'
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B1 = $Update()
                .$(t("Production","Document"))
                .$Set(s(
                        c("DocumentSummary"),
                        e_n_string("Replacing NULL value")
                ))
                .$Where()
                    .$(p_equal(
                            c("Title"),
                            e_n_string("Crank Arm and Tire Maintenance")
                    ))
                    .and()
                .build();

    /**
     * -- Replacing temporary data with the correct data. Setting @Length to NULL
    -- truncates all existing data from the @Offset position.
    UPDATE Production.Document
    SET DocumentSummary .WRITE(N'Carefully inspect and maintain the tires and crank arms.',0,NULL)
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B2 = $Update()
                .$(t("Production","Document"))
            //TODO method
//                .$Set(s(
//                        c("DocumentSummary"),
//                        e_n_string("Replacing NULL value")
//                ))
                .$Where()
                    .$(p_equal(
                            c("Title"),
                            e_n_string("Crank Arm and Tire Maintenance")
                    ))
                    .and()
                .build();

    /**
     * -- Appending additional data to the end of the column by setting
    -- @Offset to NULL.
    UPDATE Production.Document
    SET DocumentSummary .WRITE (N' Appending data to the end of the column.', NULL, 0)
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B3 = $Update()
                .$(t("Production","Document"))
            //TODO method
//                .$Set(s(
//                        c("DocumentSummary"),
//                        e_n_string("Replacing NULL value")
//                ))
                .$Where()
                    .$(p_equal(
                            c("Title"),
                            e_n_string("Crank Arm and Tire Maintenance")
                    ))
                    .and()
                .build();

    /**
     * -- Removing all data from @Offset to the end of the existing value by
    -- setting expression to NULL.
    UPDATE Production.Document
    SET DocumentSummary .WRITE (NULL, 56, 0)
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B4 = $Update()
                .$(t("Production","Document"))
            //TODO method
//                .$Set(s(
//                        c("DocumentSummary"),
//                        e_n_string("Replacing NULL value")
//                ))
                .$Where()
                    .$(p_equal(
                            c("Title"),
                            e_n_string("Crank Arm and Tire Maintenance")
                    ))
                    .and()
                .build();

    /**
     * -- Removing partial data beginning at position 9 and ending at
    -- position 21.
    UPDATE Production.Document
    SET DocumentSummary .WRITE ('',9, 12)
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B5 = $Update()
                .$(t("Production","Document"))
            //TODO method
//                .$Set(s(
//                        c("DocumentSummary"),
//                        e_n_string("Replacing NULL value")
//                ))
                .$Where()
                    .$(p_equal(
                            c("Title"),
                            e_n_string("Crank Arm and Tire Maintenance")
                    ))
                    .and()
                .build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE Production.ProductPhoto
    SET ThumbNailPhoto = (
        SELECT *
        FROM OPENROWSET(BULK 'c:Tires.jpg', SINGLE_BLOB) AS x )
    WHERE ProductPhotoID = 1
     */
    public Update example7C = $Update()
                .$(t("Production","ProductPhoto"))
            //TODO OPENROWSET
//                .$Set(s(
//                        c("ThumbNailPhoto"),
//                        e_subquery()
//                ))
                .$Where()
                    .$(p_equal(
                            c("ProductPhotoID"),
                            e_number(1)
                    ))
                    .and()
                .build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE Archive.dbo.Records
    SET [Chart] = CAST('Xray 1' as varbinary(max))
    WHERE [SerialNumber] = 2
     */
    public Update example7D = $Update()
                .$(t("Archive","dbo","Records"))
                .$Set(s(
                        c("[Chart]"),
                        e("CAST('Xray 1' as varbinary(max)) ")
                ))
                .$Where()
                    .$(p_equal(
                            c("[SerialNumber]"),
                            e_number(2)
                    ))
                    .and()
                .build();
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
    public Update example8A = $Update()
                .$(t("dbo","Cities"))
                .$Set(s(
                        c("Location"),
                        e("CONVERT(Point, '12.3:46.2')")
                ))
                .$Where()
                    .$(p_equal(
                            c("Name"),
                            e_string("Anchorage")
                    ))
                    .and()
                .build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE dbo.Cities
    SET Location.SetXY(23.5, 23.5)
    WHERE Name = 'Anchorage'
     */
    public Update example8B = $Update()
                .$(t("dbo","Cities"))
            //TODO method
//                .$Set(s(
//                        c("Location"),
//                        e("CONVERT(Point, '12.3:46.2')")
//                ))
                .$Where()
                    .$(p_equal(
                            c("Name"),
                            e_string("Anchorage")
                    ))
                    .and()
                .build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE dbo.Cities
    SET Location.X = 23.5
    WHERE Name = 'Anchorage'
     */
    public Update example8C = $Update()
                .$(t("dbo","Cities"))
                .$Set(s(
                        c("Location","X"),
                        e_number(23.5)
                ))
                .$Where()
                    .$(p_equal(
                            c("Name"),
                            e_string("Anchorage")
                    ))
                    .and()
                .build();
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
    public Update example9A = $Update()
                .$(t("Production","Product"))
                .$With($Tablock())
                .$Set(s(
                        c("ListPrice"),
                        e_multiplication(
                                c("ListPrice"),
                                e_number(1.10)
                        )
                ))
                .$Where()
                    .$(p_like(
                            c("ProductNumber"),
                            e_string("BK-%")
                    ))
                    .and()
                .build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE Production.Product
    SET ListPrice = ListPrice * 1.10
    WHERE ProductNumber LIKE @Product
    OPTION (OPTIMIZE FOR (@Product = 'BK-%') )
     */
    public Update example9B = $Update()
                .$(t("Production","Product"))
                .$Set(s(
                        c("ListPrice"),
                        e_multiplication(
                                c("ListPrice"),
                                e_number(1.10)
                        )
                ))
                .$Where()
                    .$(p_like(
                            c("ProductNumber"),
                            e_variable("Product")
                    ))
                    .and()
                .$Option()
                    .$(QueryHintBuilder.$OptimizeFor("Product","BK-%"))
                    .and()
                .build();
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
    public Update example10A = $Update()
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
                .build();
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
    public Update example11A = $Update()
                .$(t("HumanResources","Employee"))
                .$Set(s(
                        c("VacationHours"),
                        e_case()
                                .$When(p_equal(
                                        c("SalariedFlag"),
                                        e_number(0))
                                ).$Then(e_addition(
                                        c("VacationHours"),
                                        e_variable("NewHours")
                                )).$Else(e_variable("NewHours"))
                                .build()
                        )
                )
                .$Where()
                    .$(p_equal(
                            c("CurrentFlag"),
                            e_number(1)
                    ))
                    .and()
                .build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE HumanResources.Department
        SET Name = N'MyNewName'
        WHERE DepartmentID $BETWEEN 1 $AND 2
     */
    public Update example11B = $Update()
                .$(t("HumanResources","Department"))
                .$Set(s(
                        c("Name"),
                        e_n_string("MyNewName")
                ))
                .$Where()
                    .$(p_between(
                            c("DepartmentID"),
                            e_number(1),
                            e_number(2)
                    ))
                    .and()
                .build();
    // @formatter:on


    /*
    Examples: SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#examples-includesssdwincludessssdw-mdmd-and-includesspdwincludessspdw-mdmd
     */
}
