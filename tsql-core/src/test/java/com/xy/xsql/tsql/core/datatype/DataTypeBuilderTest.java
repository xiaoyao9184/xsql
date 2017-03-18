package com.xy.xsql.tsql.core.datatype;

import com.xy.xsql.tsql.model.datatype.DataType;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class DataTypeBuilderTest {

    @Test
    public void testMethodNameSameAsDataTypeName(){
        Method[] methods = DataTypeBuilder.class.getMethods();
        Object[] params = new Object[2];
        params[0] = 1;
        params[1] = new Integer[]{2,3};

        Arrays.stream(methods)
                .filter(method -> method.getReturnType().equals(DataType.class))
                .forEach((method -> {
                    String name = method.getName().replace("_","");
                    try{
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
