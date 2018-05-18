package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.table.TableOption;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.AlterFiletableOption;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class AlterFiletableOptionConverter
        implements ModelMetaBlockConverter<AlterFiletableOption> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,AlterFiletableOption>()
                    .overall("filetable_option")
                    .sub()
                        .optional(d -> d.getUseEnableFileTableNamespace() == null)
                        .sub()
                            .czse_keyword(d -> d.getUseEnableFileTableNamespace(), Keywords.Key.ENABLE)
                            .czse_keyword(d -> !d.getUseEnableFileTableNamespace(), Keywords.Key.DISABLE)
                            .and()
                        .sub_keyword(Keywords.Key.FILETABLE_NAMESPACE)
                        .and()
                    .sub()
                        .optional(d -> d.getDirectoryName() == null)
                        .sub_keyword(Keywords.SET)
                        .sub_keyword(Other.GROUP_START)
                        .sub_keyword(TableOption.KeyWords.FILETABLE_DIRECTORY)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("directory_name")
                            .scope(d -> d.getDirectoryName())
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .syntax_required()
                    .syntax_context_indentation()
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
