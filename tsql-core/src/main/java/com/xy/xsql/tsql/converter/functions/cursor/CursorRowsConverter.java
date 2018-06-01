package com.xy.xsql.tsql.converter.functions.cursor;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cursor.$$Cursor_Rows;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CursorRowsConverter
        implements ModelMetaBlockConverter<$$Cursor_Rows> {

    public static BlockMeta meta = onlyName(Function.Keywords.$$CURSOR_ROWS);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
