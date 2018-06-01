package com.xy.xsql.tsql.converter.functions.cursor;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.AsymKey_Id;
import com.xy.xsql.tsql.model.functions.cursor.Cursor_Status;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CursorStatusConverter
        implements ModelMetaBlockConverter<Cursor_Status> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Cursor_Status>()
                    .overall("CURSOR_STATUS")
                    .sub_keyword(Function.Keywords.CURSOR_STATUS)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .czse(d -> Cursor_Status.Scope.global.equals(d.getScope()))
                            .sub_keyword(Cursor_Status.Scope.global)
                            .sub_keyword(Other.DELIMITER)
                            .sub("'cursor_name'")
                                .scope(d -> d.getCursorName())
                                .and()
                            .and()
                        .czse(d -> Cursor_Status.Scope.local.equals(d.getScope()))
                            .sub_keyword(Cursor_Status.Scope.local)
                            .sub_keyword(Other.DELIMITER)
                            .sub("'cursor_name'")
                                .scope(d -> d.getCursorName())
                                .and()
                            .and()
                        .czse(d -> Cursor_Status.Scope.variable.equals(d.getScope()))
                            .sub_keyword(Cursor_Status.Scope.variable)
                            .sub_keyword(Other.DELIMITER)
                            .sub("'cursor_variable'")
                                .scope(d -> d.getCursorName())
                                .and()
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
