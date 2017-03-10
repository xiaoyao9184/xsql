package com.xy.xsql.orm.data.sql.expression.operator.arithmetic;

import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.expression.BaseBinary;

/**
 * expression / expression
 *
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class Division extends BaseBinary<OperatorEnum> {

    public Division(){
        super.t = OperatorEnum.DIVISION;
    }

}
