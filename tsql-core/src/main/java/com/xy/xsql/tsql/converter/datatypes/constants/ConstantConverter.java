package com.xy.xsql.tsql.converter.datatypes.constants;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.datatypes.constants.*;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class ConstantConverter
            implements ModelMetaBlockConverter<Constant> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Constant>()
                    .name("constant")
                    .czse(d -> d instanceof BinaryConstant)
                        .ref(BinaryConstantConverter.class)
                        .and()
                    .czse(d -> d instanceof BitConstant)
                        .ref(BitConstantConverter.class)
                        .and()
                    .czse(d -> d instanceof DatetimeConstant)
                        .ref(DatetimeConstantConverter.class)
                        .and()
                    .czse(d -> d instanceof NumberConstant)
                        .ref(NumberConstantConverter.class)
                        .and()
                    .czse(d -> d instanceof MoneyConstant)
                        .ref(MoneyConstantConverter.class)
                        .and()
                    .czse(d -> d instanceof StringConstant)
                        .ref(StringConstantConverter.class)
                        .and()
                    .czse(d -> d instanceof UniqueidentifierConstant)
                        .ref(UniqueidentifierConstantConverter.class)
                        .and()
                    .czse(d -> true)
                        .scope(Object::toString)
                        .and()
                    .sub_format_empty_delimiter()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
