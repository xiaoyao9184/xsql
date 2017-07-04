package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.statement.UpdateBuilderTest;
import com.xy.xsql.tsql.model.statement.dml.Update;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class UpdateConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = UpdateConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<UPDATE> ::=\n" +
                        "[ WITH <common_table_expression> [ ,...n ] ]\n" +
                        "UPDATE\n" +
                        "[ TOP ( expression ) [ PERCENT ] ]\n" +
                        "{\n" +
                        "table_alias\n" +
                        "| <object>\n" +
                        "}\n" +
                        "SET\n" +
                        "{\n" +
                        "column_name = expression | DEFAULT | NULL\n" +
                        "| @variable = expression\n" +
                        "| @variable = column = expression\n" +
                        "| column_name += | -= | *= | /= | %= | &= | ^= | |= expression\n" +
                        "| @variable { += | -= | *= | /= | %= | &= | ^= | |= } expression\n" +
                        "| @variable = column { += | -= | *= | /= | %= | &= | ^= | |= } expression\n" +
                        "} [,...n]\n" +
                        "[ <OUTPUT Clause> ]\n" +
                        "[ FROM table_source [ ,...n ]  ]\n" +
                        "[ WHERE <search_condition> ]\n" +
                        "[ OPTION ( <Query Hint> [ ,...n ] ) ]");
    }


    private Map<Update,String> model2StringMap;

    @Before
    public void init(){
        UpdateBuilderTest builderTest = new UpdateBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1A,
                "UPDATE Person.Address\n" +
                        "     SET ModifiedDate = GETDATE()");

        model2StringMap.put(
                builderTest.example1B,
                "UPDATE Sales.SalesPerson\n" +
                        "     SET Bonus = 6000, CommissionPct = .10, SalesQuota = NULL");

        model2StringMap.put(
                builderTest.example2A,
                "UPDATE Production.Product\n" +
                        "     SET Color = N'Metallic Red'");

        model2StringMap.put(
                builderTest.example2B,
                "UPDATE HumanResources.Employee\n" +
                        "     SET VacationHours = VacationHours + 8");

        model2StringMap.put(
                builderTest.example2C,
                "UPDATE Production.BillOfMaterials\n" +
                        "     SET PerAssemblyQty = c.PerAssemblyQty * 2");

        model2StringMap.put(
                builderTest.example2D,
                "UPDATE HumanResources.EmployeePayHistory\n" +
                        "     SET PayFrequency = 2");

        model2StringMap.put(
                builderTest.example3A,
                "UPDATE Production.Product\n" +
                        "     SET ListPrice = ListPrice * 2");

        model2StringMap.put(
                builderTest.example3B,
                "UPDATE Production.Product\n" +
                        "     SET ListPrice += @NewPrice");

        model2StringMap.put(
                builderTest.example3C,
                "UPDATE Sales.SalesPerson\n" +
                        "     SET SalesYTD = SalesYTD +\n" +
                        "     (SELECT SUM(so.SubTotal)\n" +
                        "     FROM Sales.SalesOrderHeader so\n" +
                        "     WHERE so.OrderDate = (SELECT MAX(OrderDate)\n" +
                        "     FROM Sales.SalesOrderHeader so2\n" +
                        "     WHERE so2.SalesPersonID = so.SalesPersonID)\n" +
                        "     AND Sales.SalesPerson.BusinessEntityID = so.SalesPersonID\n" +
                        "     GROUP BY so.SalesPersonID)");

        model2StringMap.put(
                builderTest.example3D,
                "UPDATE Production.Location\n" +
                        "     SET CostRate = DEFAULT");

        model2StringMap.put(
                builderTest.example4A,
                "UPDATE Person.vStateProvinceCountryRegion\n" +
                        "     SET CountryRegionName = 'United States of America'");

        model2StringMap.put(
                builderTest.example4B,
                "UPDATE sr\n" +
                        "     SET sr.Name += ' - tool malfunction'");

        //TODO support variable
//        model2StringMap.put(
//                builderTest.example4C,
//                "UPDATE @MyTableVar\n" +
//                        "     SET NewVacationHours = e.VacationHours + 20,\n" +
//                        "     ModifiedDate = GETDATE()\n" +
//                        "     FROM HumanResources.Employee AS e");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = ReferenceBlockPrinter.print(key);
            String check = writer.toString()
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }

}