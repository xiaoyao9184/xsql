package com.xy.xsql.block.tsql.core.variable;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.variable.SetVariable;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SetVariableConverter
        implements BlockConverter<SetVariable> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,SetVariable> builder =
            new ReferenceBlockBuilder<Void,SetVariable>()
                    .overall("SET @local_variable")
                    .sub_keyword(Keywords.SET)
                    .sub("@local_variable")
                        .data(SetVariable::getLocalVariable)
                        .and()
                    .sub("+= | -= | *= | /= | %= | &= | ^= | |= ")
                        .data(SetVariable::getCompound)
                        .required()
                        .and()
                    .sub("expression")
                        .data(SetVariable::getExpression)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }


    @Override
    public Block convert(SetVariable setVariable) {
        return builder
                .data(setVariable)
                .build();
    }
}
