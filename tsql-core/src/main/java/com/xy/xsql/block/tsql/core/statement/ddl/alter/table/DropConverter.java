package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.Drop;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.DropClusteredConstraintOption;

import java.util.List;
import java.util.Map;

/**
 * Createe by xiaoyao9184 on 2017/9/17
 */
public class DropConverter
        implements ModelMetaBlockConverter<Drop> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Drop>()
                    .description("alter table drop item")
                    .sub_keyword(Keywords.DROP)
                    .sub()
                        .list()
                        .ref(DropItemConverter.meta)
                        .scope(d -> d.getItems())
                        .syntax_required()
                        .syntax_context_indentation()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }



    public static class DropItemConverter
            implements ModelMetaBlockConverter<Drop.DropItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Drop.DropItem>()
                        .description("drop item")
                        .czse(d -> d instanceof Drop.DropConstraint)
                            .ref(DropConstraintConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d instanceof Drop.DropColumn)
                            .ref(DropColumnConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d instanceof Drop.PeriodSystemTime)
                            .ref(PeriodSystemTimeConverter.meta)
                            .scope(d -> d)
                            .and()
                        .syntax_sub_line()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class DropConstraintConverter
            implements ModelMetaBlockConverter<Drop.DropConstraint> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Drop.DropConstraint>()
                        .description("drop constraint")
                        .sub()
                            .sub()
                                .optional(d -> !d.isUseConstraint())
                                .keyword(Keywords.CONSTRAINT)
                                .and()
                            .sub()
                                .optional(d -> !d.isUseIfExists())
                                .sub_keyword(Keywords.IF)
                                .sub_keyword(Keywords.EXISTS)
                                .and()
                            .and()
                        .sub()
                            .list()
                            .ref(ConstraintNameWithOptionConverter.meta)
                            .scope(d -> d.getConstraintNameWithOptionList())
                            .syntax_required()
                            .syntax_context_indentation()
                            .and()
                        .syntax_sub_line()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class DropColumnConverter
            implements ModelMetaBlockConverter<Drop.DropColumn> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Drop.DropColumn>()
                        .description("drop column")
                        .sub()
                            .sub_keyword(Keywords.COLUMN)
                            .sub()
                                .optional(d -> !d.isUseIfExists())
                                .sub_keyword(Keywords.IF)
                                .sub_keyword(Keywords.EXISTS)
                                .and()
                            .and()
                        .sub("column_name")
                            .list()
                            .ref_scope(d -> d.getColumnNames())
                                .scope(d -> d)
                                .and()
                            .syntax_required()
                            .syntax_context_indentation()
                            .and()
                        .syntax_sub_line()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class PeriodSystemTimeConverter
            implements ModelMetaBlockConverter<Drop.PeriodSystemTime> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Drop.PeriodSystemTime>()
                        .description("drop period for system time")
                        .sub_keyword(Keywords.Key.PERIOD)
                        .sub_keyword(Keywords.FOR)
                        .sub_keyword(Keywords.Key.SYSTEM_TIME)
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class ConstraintNameWithOptionConverter {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Map.Entry<String,List<DropClusteredConstraintOption>>>()
                        .description("drop period for system time")
                        .sub("constraint_name")
                            .scope(d -> d.getKey())
                            .and()
                        .sub()
                            .sub_keyword(Keywords.WITH)
                            .sub_keyword(Other.GROUP_START)
                            .sub("drop_clustered_constraint_option")
                                .list()
                                .ref(DropClusteredConstraintOptionConverter.meta)
                                .scope(d -> d.getValue())
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .syntax_sub_line()
                        .build();
        // @formatter:on

    }

}
