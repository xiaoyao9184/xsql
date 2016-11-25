package com.xy.xsql.orm.data.sql.expression.operator.relational;

import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.expression.BaseBinary;

/**
 * expression >= expression
 *
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class GreaterEqual extends BaseBinary<OperatorEnum> {

    public GreaterEqual(){
        super.t = OperatorEnum.GREATER_EQUAL;
    }

}
