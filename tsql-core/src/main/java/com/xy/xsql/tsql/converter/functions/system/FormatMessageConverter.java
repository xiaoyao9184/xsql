package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Binary_Checksum;
import com.xy.xsql.tsql.model.functions.system.FormatMessage;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class FormatMessageConverter
        implements ModelMetaBlockConverter<FormatMessage> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,FormatMessage>()
                    .overall("FORMATMESSAGE")
                    .sub_keyword(Function.Keywords.FORMATMESSAGE)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .czse(d -> d.getMsgMumber() != null, "msg_number")
                            .scope(d -> d.getMsgMumber())
                            .and()
                        .czse(d -> d.getMsgString() != null,"'msg_string'")
                            .scope(d -> d.getMsgString())
                            .and()
                        .syntax_required()
                        .and()
                    .sub()
                        .optional(d -> d.getParamValueList() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("param_value")
                            .list()
                            .scope(d -> d.getParamValueList())
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
