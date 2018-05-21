package com.xy.xsql.tsql.converter.statements.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.datatypes.table.table.TableOption;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.statements.alter.table.StretchConfiguration;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class StretchConfigurationConverter
        implements ModelMetaBlockConverter<StretchConfiguration> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,StretchConfiguration>()
                    .overall("stretch_configuration")
                    .sub_keyword(Keywords.SET)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .sub_keyword(TableOption.KeyWords.REMOTE_DATA_ARCHIVE)
                        .sub()
                            .czse(d -> d.getUseOn() && d.getTableStretchOptions() != null)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub_keyword(Keywords.ON)
                                .sub_keyword(Other.GROUP_START)
                                .sub("table_stretch_options")
                                    .scope(d -> d.getTableStretchOptions())
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .czse(d -> !d.getUseOn() && d.getTableStretchOptions() == null)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub_keyword(Keywords.Key.OFF_WITHOUT_DATA_RECOVERY)
                                .sub_keyword(Other.GROUP_START)
                                .sub_keyword(TableOption.KeyWords.MIGRATION_STATE)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub_keyword(Keywords.Key.PAUSED)
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .czse(d -> d.getTableStretchOptionsList() != null)
                                .sub_keyword(Other.GROUP_START)
                                .sub("table_stretch_options")
                                    .list()
                                    .scope(d -> d.getTableStretchOptionsList())
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .syntax_line()
                            .syntax_required()
                            .syntax_context_indentation()
                            .syntax_sub_line()
                            .and()
                        .syntax_context_indentation()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .syntax_required()
                    .syntax_context_indentation()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
