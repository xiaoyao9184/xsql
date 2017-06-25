package com.xy.xsql.block.tsql.core.variable;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.variable.SelectVariable;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SelectVariableConverter
        implements BlockConverter<SelectVariable> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,SelectVariable> builder =
            new ReferenceBlockBuilder<Void,SelectVariable>()
                    .overall("SELECT @local_variable")
                    .sub_keyword(Keywords.SELECT)
                    .sub()
                        .description("{ @local_variable { = | += | -= | *= | /= | %= | &= | ^= | |= } expression } [ ,...n ]")
                        .list(SelectVariableItemConverter.meta())
                        .data(SelectVariable::getItems)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(SelectVariable selectVariable) {
        return builder
                .data(selectVariable)
                .build();
    }


    public static class SelectVariableItemConverter
            implements BlockConverter<SelectVariable.Item> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,SelectVariable.Item> builder =
                new ReferenceBlockBuilder<Void,SelectVariable.Item>()
                    .sub("@local_variable")
                        .data(SelectVariable.Item::getLocalVariable)
                        .and()
                    .sub("= | += | -= | *= | /= | %= | &= | ^= | |=")
                        .data(SelectVariable.Item::getCompound)
                        .required()
                        .and()
                    .sub("expression")
                        .data(SelectVariable.Item::getExpression)
                        .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(SelectVariable.Item item) {
            return builder
                    .data(item)
                    .build();
        }
    }
}
