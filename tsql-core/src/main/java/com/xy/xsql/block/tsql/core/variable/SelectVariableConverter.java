package com.xy.xsql.block.tsql.core.variable;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.variable.SelectVariable;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SelectVariableConverter
        implements ModelMetaBlockConverter<SelectVariable> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,SelectVariable>()
                    .overall("SELECT @local_variable")
                    .sub_keyword(Keywords.SELECT)
                    .sub()
                        .description("{ @local_variable { = | += | -= | *= | /= | %= | &= | ^= | |= } expression } [ ,...n ]")
                        .list()
                        .ref(SelectVariableItemConverter.meta)
                        .data(SelectVariable::getItems)
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class SelectVariableItemConverter
            implements ModelMetaBlockConverter<SelectVariable.Item> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,SelectVariable.Item>()
                    .sub("@local_variable")
                        .data(SelectVariable.Item::getLocalVariable)
                        .and()
                    .sub("= | += | -= | *= | /= | %= | &= | ^= | |=")
                        .data(d -> d.getCompound() == null ? Assignment.ASSIGNMENT : d.getCompound())
                        .required()
                        .and()
                    .sub("expression")
                        .data(SelectVariable.Item::getExpression)
                        .and()
                    .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
        return meta;
    }

    }
}
