package com.xy.xsql.orm.data.sql.expression.operator.arithmetic;

import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.expression.BaseBinary;

/**
 * expression - expression
 *
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class Subtraction extends BaseBinary<OperatorEnum> {

    public Subtraction(){
        super.t = OperatorEnum.SUBTRACTION;
    }

}