package com.xy.xsql.block.tsql.core.element.column;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.column.ColumnSetDefinition;

/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class ColumnSetDefinitionConverter
        implements ModelMetaBlockConverter<ColumnSetDefinition> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,ColumnSetDefinition>()
                    .overall("column_set_definition")
                    .sub("column_set_name")
                        .scope(ColumnSetDefinition::getColumnSetName)
                        .and()
                    .sub_keyword(Keywords.Key.XML)
                    .sub_keyword(Keywords.Key.COLUMN_SET)
                    .sub_keyword(Keywords.FOR)
                    .sub_keyword(Keywords.Key.ALL_SPARSE_COLUMNS)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
