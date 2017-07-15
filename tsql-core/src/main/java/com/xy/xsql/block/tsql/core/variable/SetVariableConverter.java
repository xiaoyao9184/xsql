package com.xy.xsql.block.tsql.core.variable;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.variable.SetVariable;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SetVariableConverter
        implements ModelMetaBlockConverter<SetVariable> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,SetVariable>()
                    .overall("SET @local_variable")
                    .sub_keyword(Keywords.SET)
                    .sub("@local_variable")
                        .scope(SetVariable::getLocalVariable)
                        .and()
                    .sub("+= | -= | *= | /= | %= | &= | ^= | |= ")
                        .style_required()
                        .scope(d -> d.getCompound() == null ? Assignment.ASSIGNMENT : d.getCompound())
                        .and()
                    .sub("expression")
                        .scope(SetVariable::getExpression)
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
