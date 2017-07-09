package com.xy.xsql.block.tsql.core.variable;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.variable.SetVariable;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SetVariableConverter
        implements ReferenceBlockConverter<SetVariable> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,SetVariable> builder =
            new ReferenceBlockBuilder<Void,SetVariable>()
                    .overall("SET @local_variable")
                    .sub_keyword(Keywords.SET)
                    .sub("@local_variable")
                        .data(SetVariable::getLocalVariable)
                        .and()
                    .sub("+= | -= | *= | /= | %= | &= | ^= | |= ")
                        .required()
                        .data(d -> d.getCompound() == null ? Assignment.ASSIGNMENT : d.getCompound())
                        .and()
                    .sub("expression")
                        .data(SetVariable::getExpression)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }


    @Override
    public ReferenceBlock convert(SetVariable setVariable) {
        return builder
                .data(setVariable)
                .build();
    }
}
