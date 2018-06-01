package com.xy.xsql.tsql.converter.functions.trigger;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.trigger.Columns_Updated;
import com.xy.xsql.tsql.model.functions.trigger.EventData;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;
import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class EventDataConverter
        implements ModelMetaBlockConverter<EventData> {

    public static BlockMeta meta = noParam(Function.Keywords.EVENTDATA);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
