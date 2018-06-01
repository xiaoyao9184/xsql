package com.xy.xsql.tsql.converter.functions.systemstatistical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.systemstatistical.$$Idle;
import com.xy.xsql.tsql.model.functions.systemstatistical.fn_virtualfilestats;
import com.xy.xsql.tsql.model.functions.text.TextPtr;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class fn_virtualfilestatsConverter
        implements ModelMetaBlockConverter<fn_virtualfilestats> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,fn_virtualfilestats>()
                    .overall("fn_virtualfilestats")
                    .sub_keyword(Function.Keywords.fn_virtualfilestats)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .czse(d -> d.getDatabaseId() != null,"database_id")
                            .scope(d -> d.getDatabaseId())
                            .and()
                        .czse_keyword(d -> d.getDatabaseId() == null, Keywords.NULL)
                        .syntax_required()
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub()
                        .czse(d -> d.getFileId() != null,"file_id")
                            .scope(d -> d.getFileId())
                            .and()
                        .czse_keyword(d -> d.getDatabaseId() == null, Keywords.NULL)
                        .syntax_required()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
