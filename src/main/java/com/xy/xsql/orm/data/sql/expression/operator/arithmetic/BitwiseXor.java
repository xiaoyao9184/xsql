package com.xy.xsql.orm.data.sql.expression.operator.arithmetic;

import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.expression.BaseBinary;

/**
 * expression ^ expression
 *
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class BitwiseXor extends BaseBinary<OperatorEnum> {

    public BitwiseXor(){
        super.t = OperatorEnum.BITWISE_XOR;
    }

}
