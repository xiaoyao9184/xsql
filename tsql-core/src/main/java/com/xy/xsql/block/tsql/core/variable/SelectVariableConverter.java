package com.xy.xsql.block.tsql.core.variable;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.variable.SelectVariable;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SelectVariableConverter
        implements MetaContextBlockConverter<SelectVariable> {

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

    public BlockMeta meta() {
        return meta;
    }

    @Override
    public MetaContextBlock convert(SelectVariable context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }


    public static class SelectVariableItemConverter
            implements MetaContextBlockConverter<SelectVariable.Item> {

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

    public BlockMeta meta() {
        return meta;
    }

        @Override
        public MetaContextBlock convert(SelectVariable.Item context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }
}
