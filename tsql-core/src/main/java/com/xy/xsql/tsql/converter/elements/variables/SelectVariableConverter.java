package com.xy.xsql.tsql.converter.elements.variables;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.elements.variables.SelectVariable;

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
                        .description("select local_variable's item list")
                        .list()
                        .ref(SelectVariableItemConverter.meta)
                        .scope(SelectVariable::getItems)
                        .sub_format_line()
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
                        .scope(SelectVariable.Item::getLocalVariable)
                        .and()
                    .sub("= | += | -= | *= | /= | %= | &= | ^= | |=")
                        .scope(d -> d.getCompound() == null ? Assignment.ASSIGNMENT : d.getCompound())
                        .syntax_required()
                        .and()
                    .sub("expression")
                        .scope(SelectVariable.Item::getExpression)
                        .and()
                    .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
        return meta;
    }

    }
}
