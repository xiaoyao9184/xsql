package com.xy.xsql.tsql.core.statement.dml;

import com.xy.xsql.tsql.core.statement.dml.UpdateBuilder;
import com.xy.xsql.tsql.model.expression.BinaryExpression;
import com.xy.xsql.tsql.model.operator.Compound;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.model.statement.dml.Update;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.Expressions.*;
import static com.xy.xsql.tsql.core.expression.BinaryExpressions.*;
import static com.xy.xsql.tsql.core.predicate.Predicates.p_equal;
import static com.xy.xsql.tsql.core.statement.StatementBuilderFactory.SELECT;
import static com.xy.xsql.tsql.core.statement.dml.UpdateBuilder.SetItemBuilder.*;
import static com.xy.xsql.tsql.core.statement.dml.UpdateBuilder.UPDATE;

/**
 * Created by xiaoyao9184 on 2017/1/7.
 */
public class UpdateBuilderTest {

    //Basic Syntax

    // @formatter:off
    public Update example1A = UPDATE()
                .$(t("Person","Address"))
                .$Set(
                        s(c("ModifiedDate"),
                                e("GETDATE()"))
                )
                .done();
    // @formatter:on

    /**
     * UPDATE Person.Address
     SET ModifiedDate = GETDATE();
     */
    @Test
    public void test1A(){
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

    /**
     * UPDATE Sales.SalesPerson
     SET Bonus = 6000, CommissionPct = .10, SalesQuota = NULL;
     */
    @Test
    public void test1B(){
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

    //Limiting the Rows that Are Updated


    // @formatter:off
    public Update example2A = UPDATE()
                .$(t("Production","Product"))
                .$Set(
                        s(
                                c("Color"),
                                e_n_string("Metallic Red")
                        )
                )
                .done();
    // @formatter:on

    /**
     * UPDATE Production.Product
     SET Color = N'Metallic Red'
     */
    @Test
    public void test2A(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Production","Product"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("Color"))
                    .withExpression(e_n_string("Metallic Red"))
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


    // @formatter:off
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
                .done();
    // @formatter:on

    /**
     * UPDATE HumanResources.Employee
     SET VacationHours = VacationHours + 8
     */
    @Test
    public void test2B(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("HumanResources","Employee"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("VacationHours"))
                    .withExpression(e_addition(
                            e("VacationHours"),
                            e_number(8)))
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"HumanResources.Employee");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "VacationHours");
        //TODO
//        Assert.assertEquals(setItem.getExpression().toString(), "VacationHours + 8");
    }


    // @formatter:off
    public Update example2C = UPDATE()
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
                .done();
    // @formatter:on

    /**
     * UPDATE Production.BillOfMaterials
     SET PerAssemblyQty = c.PerAssemblyQty * 2
     */
    @Test
    public void test2C(){
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
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"Production.BillOfMaterials");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "PerAssemblyQty");

        Assert.assertEquals(setItem.getExpression().getClass(), BinaryExpression.class);
        BinaryExpression binaryExpression = (BinaryExpression) setItem.getExpression();
    }


    // @formatter:off
    public Update example2D = UPDATE()
                .$(t("HumanResources","EmployeePayHistory"))
                .$Set(
                        s(
                                c("PayFrequency"),
                                e_number(2)
                        )
                )
                .done();
    // @formatter:on

    /**
     * UPDATE HumanResources.EmployeePayHistory
     SET PayFrequency = 2
     */
    @Test
    public void test2D(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("HumanResources","EmployeePayHistory"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("PayFrequency"))
                    .withExpression(e_number(2))
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"HumanResources.EmployeePayHistory");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "PayFrequency");
        Assert.assertEquals(setItem.getExpression().toString(), "2");
    }

    //Setting Column Values


    // @formatter:off
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

    /**
     * UPDATE Production.Product
     SET ListPrice = ListPrice * 2;
     */
    @Test
    public void test3A(){
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
        //TODO
//        Assert.assertEquals(setItem.getExpression().toString(), "ListPrice * 2");
    }


    // @formatter:off
    public Update example3B = UPDATE()
                .$(t("Production","Product"))
                .$Set(
                        s(c("ListPrice"),
                                Compound.ADD_EQUALS,
                                e_variable("NewPrice")
                        )
                )
                .done();
    // @formatter:on

    /**
     * UPDATE Production.Product
     SET ListPrice += @NewPrice
     */
    @Test
    public void test3B(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Production","Product"))
                .withSetItem()._ColumnCompound()
                    .withColumnName(c("ListPrice"))
                    .withCompound(Compound.ADD_EQUALS)
                    .withExpression(e_variable("NewPrice"))
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
    }


    // @formatter:off
    Select subQuery3C0 = SELECT()
            .$Select()
                .$(e("MAX(OrderDate)"))
                .$From()
                    .$(t("Sales","SalesOrderHeader"),"so2")
                    .and()
                    .and()
                .$Where()
                    .$Predicate(p_equal(
                            c("so2","SalesPersonID"),
                            c("so","SalesPersonID")
                    ))
                    .and()
                .and()
                .done();

    Select subQuery3C = SELECT()
            .$Select()
                .$(e("SUM(so.SubTotal)"))
                .$From()
                    .$(t("Sales","SalesOrderHeader"),"so")
                    .and()
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

    /**
     * UPDATE Sales.SalesPerson
     SET SalesYTD = SalesYTD +
     (SELECT SUM(so.SubTotal)
     FROM Sales.SalesOrderHeader AS so
     WHERE so.OrderDate = (SELECT MAX(OrderDate)
     FROM Sales.SalesOrderHeader AS so2
     WHERE so2.SalesPersonID = so.SalesPersonID)
     AND Sales.SalesPerson.BusinessEntityID = so.SalesPersonID
     GROUP BY so.SalesPersonID);
     */
    @Test
    public void test3C(){
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

        Assert.assertEquals(update.getTableName().toString(),"Production.Product");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "ListPrice");
        Assert.assertEquals(setItem.getExpression().toString(), "ListPrice += @NewPrice");
    }


    // @formatter:off
    public Update example3D = UPDATE()
                .$(t("Production","Location"))
                .$Set(
                        s_default(c("CostRate"))
                )
                .done();
    // @formatter:on

    /**
     * UPDATE Production.Location
     SET CostRate = DEFAULT
     */
    @Test
    public void test3D(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Production","Location"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("CostRate"))
                    .withExpression(e_default())
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"Production.Location");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "CostRate");
        Assert.assertEquals(setItem.getExpression().toString(), "DEFAULT");
    }

    //Specifying Target Objects Other than Standard Tables


    // @formatter:off
    public Update example4A = UPDATE()
                .$(t("Person","vStateProvinceCountryRegion"))
                .$Set(
                        s(c("CountryRegionName"),e_string("United States of America"))
                )
                .done();
    // @formatter:on

    /**
     * UPDATE Person.vStateProvinceCountryRegion
     SET CountryRegionName = 'United States of America'
     */
    @Test
    public void test4A(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("Person","vStateProvinceCountryRegion"))
                .withSetItem()._ColumnAssignment()
                    .withColumnName(c("CountryRegionName"))
                    .withExpression(e_string("United States of America"))
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"Person.vStateProvinceCountryRegion");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "CountryRegionName");
        Assert.assertEquals(setItem.getExpression().toString(), "'United States of America'");
    }


    // @formatter:off
    public Update example4B = UPDATE()
                .$(t("sr"))
                .$Set(
                        s(
                                c("sr","Name"),
                                Compound.ADD_EQUALS,
                                e_string(" - tool malfunction"))
                )
                .done();
    // @formatter:on

    /**
     * UPDATE sr
     SET sr.Name += ' - tool malfunction'
     */
    @Test
    public void test4B(){
        // @formatter:off
        Update update = new UpdateBuilder()
                .withTableName(t("sr"))
                .withSetItem()._ColumnCompound()
                    .withColumnName(c("sr","CountryRegionName"))
                    .withCompound(Compound.ADD_EQUALS)
                    .withExpression(e_string(" - tool malfunction"))
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(update.getTableName().toString(),"sr");
        Assert.assertEquals(update.getSets().size(),1);

        Assert.assertEquals(update.getSets().get(0).getClass(), Update.ColumnCompoundSet.class);
        Update.ColumnCompoundSet setItem = (Update.ColumnCompoundSet) update.getSets().get(0);
        Assert.assertEquals(setItem.getColumnName().toString(), "sr.CountryRegionName");
        Assert.assertEquals(setItem.getExpression().toString(), "' - tool malfunction'");
    }


    // @formatter:off
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
    public void test4C(){
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

}
