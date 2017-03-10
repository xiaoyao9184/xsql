package com.xy.xsql.orm.data.sql.expression.operator.relational;

import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.expression.BaseBinary;

/**
 * expression = expression
 *
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class Equal extends BaseBinary<OperatorEnum> {

    public Equal(){
        super.t = OperatorEnum.EQUAL;
    }

}
