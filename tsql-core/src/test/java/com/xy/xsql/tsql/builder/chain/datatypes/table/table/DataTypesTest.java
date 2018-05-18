package com.xy.xsql.tsql.builder.chain.datatypes.table.table;

import com.xy.xsql.tsql.builder.chain.datatypes.table.column.DataTypes;
import com.xy.xsql.tsql.model.datatypes.table.column.DataType;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class DataTypesTest {

    @Test
    public void testMethodNameSameAsDataTypeName(){
        Method[] methods = DataTypes.class.getMethods();
        Object[] params = new Object[2];
        params[0] = 1;
        params[1] = new Integer[]{2,3};

        Arrays.stream(methods)
                .filter(method -> method.getReturnType().equals(DataType.class))
                .forEach((method -> {
                    String name = method.getName().replace("_","");
                    try{
                        if(method.getParameterCount() == 1 &&
                                method.getParameterTypes()[0].equals(String.class)){
                            DataType dataType = (DataType) method.invoke(
                                    null,
                                    "TEST");
                            Assert.assertTrue(dataType.toString().equals("TEST"));
                            return;
                        }

                        DataType dataType = (DataType) method.invoke(
                                null,
                                Arrays.copyOf(params,method.getParameterCount()));
                        Assert.assertTrue(dataType.toString().startsWith(name));
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
    }

}
