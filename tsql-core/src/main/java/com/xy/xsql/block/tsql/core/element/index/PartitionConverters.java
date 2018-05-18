package com.xy.xsql.block.tsql.core.element.index;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.element.index.Partition;

/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class PartitionConverters {

    public static class PartitionColumnFilegroupDefaultKey {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Partition>()
                        .czse(d -> d.getSchemeName() != null)
                            .sub("partition_scheme_name")
                                .scope(d -> d.getSchemeName())
                                .and()
                            .sub_keyword(Other.GROUP_START)
                            .sub("column_name")
                                .scope(d -> d.getColumnName())
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .czse(d -> d.getFilegroupName() != null,"filegroup_name")
                            .scope(d -> d.getFilegroupName())
                            .and()
                        .czse(d -> d.isUseDefault(), "default")
                            .scope(d -> "default")
                            .and()
                        .syntax_sub_line()
                        .syntax_required()
                        .build();
        // @formatter:on
    }

    public static class FilegroupDefaultKey {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Partition>()
                        .czse(d -> d.getFilegroupName() != null,"filegroup_name")
                            .scope(d -> d.getFilegroupName())
                            .and()
                        .czse(d -> d.isUseDefault(), "default")
                            .scope(d -> "default")
                            .and()
                        .syntax_required()
                        .build();
        // @formatter:on
    }


    public static class PartitionColumnFilegroupDefault {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Partition>()
                        .czse(d -> d.getSchemeName() != null)
                            .sub("partition_scheme_name")
                                .scope(d -> d.getSchemeName())
                                .and()
                            .sub_keyword(Other.GROUP_START)
                            .sub("partition_column_name")
                                .scope(d -> d.getColumnName())
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .czse(d -> d.getFilegroupName() != null,"filegroup_name")
                            .scope(d -> d.getFilegroupName())
                            .and()
                        .czse(d -> d.isUseDefault(), "\"default\"")
                            .scope(d -> "\"default\"")
                            .and()
                        .syntax_sub_line()
                        .syntax_required()
                        .build();
        // @formatter:on
    }

    public static class PartitionFilegroupDefault {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Partition>()
                        .czse(d -> d.getSchemeName() != null,"partition_scheme_name")
                            .scope(d -> d.getSchemeName())
                            .and()
                        .czse(d -> d.getFilegroupName() != null,"filegroup_name")
                            .scope(d -> d.getFilegroupName())
                            .and()
                        .czse(d -> d.isUseDefault(), "\"default\"")
                            .scope(d -> "\"default\"")
                            .and()
                        .syntax_sub_line()
                        .syntax_required()
                        .build();
        // @formatter:on
    }

    public static class PartitionFilegroupDefaultNull {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Partition>()
                        .czse(d -> d.getSchemeName() != null,"partition_scheme_name")
                            .scope(d -> d.getSchemeName())
                            .and()
                        .czse(d -> d.getFilegroupName() != null,"filegroup_name")
                            .scope(d -> d.getFilegroupName())
                            .and()
                        .czse(d -> d.isUseDefault(), "\"default\"")
                            .scope(d -> "\"default\"")
                            .and()
                        .czse(d -> d.isUseNull(), "\"NULL\"")
                            .scope(d -> "\"NULL\"")
                            .and()
//                        .syntax_sub_line()
                        .syntax_required()
                        .build();
        // @formatter:on
    }

    public static class FilegroupDefault {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Partition>()
                        .czse(d -> d.getFilegroupName() != null,"filegroup")
                            .scope(d -> d.getFilegroupName())
                            .and()
                        .czse(d -> d.isUseDefault(), "\"default\"")
                            .scope(d -> "\"default\"")
                            .and()
                        .syntax_required()
                        .build();
        // @formatter:on
    }


    public static class FileStreamGroupNull {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Partition>()
                        .czse(d -> d.getFilegroupName() != null,"filestream_filegroup_name")
                            .scope(d -> d.getFilegroupName())
                            .and()
                        .czse(d -> d.getSchemeName() != null,"partition_scheme_name")
                            .scope(d -> d.getSchemeName())
                            .and()
                        .czse(d -> d.isUseDefault(), "\"NULL\"")
                            .scope(d -> "\"NULL\"")
                            .and()
                        .syntax_required()
                        .build();
        // @formatter:on
    }

}
