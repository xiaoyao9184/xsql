package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.logical.*;

import java.util.Arrays;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface LogicalFunctions {


    static Choose f_choose(
            NumberConstant index,
            Expression... vals){
        Choose f = new Choose();
        f.setIndex(index);
        f.setValList(Arrays.asList(vals));
        return f;
    }

    static IIf f_iif(
            Expression booleanExpression,
            Expression trueValue,
            Expression falseValue){
        IIf f = new IIf();
        f.setBooleanExpression(booleanExpression);
        f.setTrueValue(trueValue);
        f.setFalseValue(falseValue);
        return f;
    }

}
