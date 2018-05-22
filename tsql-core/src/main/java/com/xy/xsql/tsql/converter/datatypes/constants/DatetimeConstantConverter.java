package com.xy.xsql.tsql.converter.datatypes.constants;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.datatypes.constants.DatetimeConstant;
import com.xy.xsql.tsql.model.datatypes.constants.Keymarks;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class DatetimeConstantConverter
            implements ModelMetaBlockConverter<DatetimeConstant> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DatetimeConstant>()
                    .name("datetime")
                    .description("datetime constants")
                    .sub_keyword(Keymarks.quotation)
                    .sub()
                        .scope(constant -> constant.getDateTime().format(constant.getFormatter()))
                        .and()
                    .sub_keyword(Keymarks.quotation)
                    .sub_format_empty_delimiter()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
