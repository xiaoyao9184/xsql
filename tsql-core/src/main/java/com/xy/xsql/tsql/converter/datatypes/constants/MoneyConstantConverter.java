package com.xy.xsql.tsql.converter.datatypes.constants;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.datatypes.constants.Keymarks;
import com.xy.xsql.tsql.model.datatypes.constants.MoneyConstant;
import com.xy.xsql.tsql.model.datatypes.constants.NegativePositive;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class MoneyConstantConverter
            implements ModelMetaBlockConverter<MoneyConstant> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,MoneyConstant>()
                    .name("money")
                    .description("money constants")
                    .sub()
                        .optional(c -> !c.isUsePositive() && !c.isUseNegative())
                        .czse(NegativePositive::isUseNegative)
                            .keyword(Keymarks.negative)
                            .and()
                        .czse(NegativePositive::isUsePositive)
                            .keyword(Keymarks.negative)
                            .and()
                        .and()
                    .sub()
                        .scope(MoneyConstant::getSymbol)
                        .and()
                    .sub()
                        .scope(NumberConstant::toUnsignedString)
                        .and()
                    .sub_format_empty_delimiter()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
