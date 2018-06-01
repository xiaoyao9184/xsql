package com.xy.xsql.tsql.converter.functions.rowset;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.rowset.OpenXml;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class OpenXmlConverter
        implements ModelMetaBlockConverter<OpenXml> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,OpenXml>()
                    .overall("OPENXML")
                    .sub_keyword(Function.Keywords.OPENXML)
                    .sub_keyword(Other.GROUP_START)
                    .sub("idoc")
                        .scope(d -> d.getIdoc())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("rowpattern")
                        .scope(d -> d.getRowPattern())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("flags")
                        .optional(d -> d.getFlags() == null)
                        .scope(d -> d.getFlags())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .sub()
                        .optional(d -> d.getSchemaDeclaration() == null && d.getTableName() == null)
                        .sub_keyword(Keywords.WITH)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .czse(d -> d.getSchemaDeclaration() != null, "SchemaDeclaration")
                                .ref(SchemaDeclarationConverter.meta)
                                .scope(d -> d.getSchemaDeclaration())
                                .and()
                            .czse(d -> d.getTableName() != null, "TableName")
                                .scope(d -> d.getTableName())
                                .and()
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .syntax_indentation_right()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

    public static class SchemaDeclarationConverter
            implements ModelMetaBlockConverter<OpenXml.SchemaDeclaration> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,OpenXml.SchemaDeclaration>()
                        .overall("SchemaDeclaration")
                        .list()
                        .scope(d -> d.getItems())
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class SchemaDeclarationItemConverter
            implements ModelMetaBlockConverter<OpenXml.SchemaDeclarationItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,OpenXml.SchemaDeclarationItem>()
                        .description("SchemaDeclaration item")
                        .sub("ColName")
                            .scope(d -> d.getColName())
                            .and()
                        .sub("ColType")
                            .scope(d -> d.getColType())
                            .and()
                        .sub()
                            .czse(d -> d.getColPattern() != null, "ColPattern")
                                .scope(d -> d.getColPattern())
                                .and()
                            .czse(d -> d.getMetaProperty() != null, "MetaProperty")
                                .scope(d -> d.getMetaProperty())
                                .and()
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
