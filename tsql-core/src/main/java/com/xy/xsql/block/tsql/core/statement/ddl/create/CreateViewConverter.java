package com.xy.xsql.block.tsql.core.statement.ddl.create;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.statement.ddl.create.CreateView;

/**
 * Created by xiaoyao9184 on 2017/8/3.
 */
public class CreateViewConverter
        implements ModelMetaBlockConverter<CreateView> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,CreateView>()
                    .overall("Create a view")
                    .sub()
                        .sub_keyword(Keywords.CREATE)
                        .sub()
                            .description("or alter")
                            .optional(d -> !d.isUseAlter())
                            .sub_keyword(Keywords.OR)
                            .sub_keyword(Keywords.ALTER)
                            .and()
                        .sub_keyword(Keywords.VIEW)
                        .sub()
                            .description("schema_name view_name")
                            .sub()
                                .description("schema_name")
                                .optional(d -> d.getSchemaName() == null)
                                .sub("schema_name")
                                    .scope(CreateView::getSchemaName)
                                    .and()
                                .sub_keyword(Other.POINT)
                                .sub_format_empty_delimiter()
                                .and()
                            .sub("view_name")
                                .scope(CreateView::getViewName)
                                .and()
                            .sub_format_empty_delimiter()
                            .and()
                        .sub()
                            .description("column")
                            .optional(d -> d.getColumn() == null)
                            .sub_keyword(Other.GROUP_START)
                            .sub("column")
                                .list()
                                .scope(CreateView::getColumn)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .and()
                    .sub()
                        .description("with view_attribute")
                        .optional(d -> d.getViewAttributes() == null)
                        .sub_keyword(Keywords.WITH)
                        .sub("view_attribute")
                            .list()
                            .ref(ViewAttributeConverter.meta)
                            .scope(CreateView::getViewAttributes)
                            .and()
                        .and()
                    .sub()
                        .description("as select_statement")
                        .sub_keyword(Keywords.AS)
                        .sub("select_statement")
                            .scope(CreateView::getSelectStatement)
                            .and()
                        .and()
                    .sub()
                        .description("with check option")
                        .optional(d -> !d.isUseCheckOption())
                        .sub_keyword(Keywords.WITH)
                        .sub_keyword(Keywords.CHECK)
                        .sub_keyword(Keywords.OPTION)
                        .and()
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class ViewAttributeConverter
            implements ModelMetaBlockConverter<CreateView.ViewAttribute> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,CreateView.ViewAttribute>()
                        .overall("view_attribute")
                        .sub("ENCRYPTION")
                            .optional(d -> !d.equals(CreateView.ViewAttribute.ENCRYPTION))
                            .scope(Enum::name)
                            .and()
                        .sub("SCHEMABINDING")
                            .optional(d -> !d.equals(CreateView.ViewAttribute.SCHEMABINDING))
                            .scope(Enum::name)
                            .and()
                        .sub("VIEW_METADATA")
                            .optional(d -> !d.equals(CreateView.ViewAttribute.VIEW_METADATA))
                            .scope(Enum::name)
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

}
