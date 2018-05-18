package com.xy.xsql.block.tsql.core.element.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.statement.ddl.TruncateTableConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.table.TableOption;
import com.xy.xsql.tsql.model.elements.operators.Assignment;

import static com.xy.xsql.block.tsql.core.element.EnumConverterUtil.getSyntaxString;

/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class TableOptionConverters {

    public static class DiskBased {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableOption>()
                        .overall("table_option")
                        .czse_ref(d -> d instanceof TableOption.DataCompression, DataCompression.meta)
                        .czse_ref(d -> d instanceof TableOption.FileTableDirectory, FileTableDirectory.meta)
                        .czse_ref(d -> d instanceof TableOption.FileTableCollateFilename, FileTableCollateFilename.meta)
                        .czse_ref(d -> d instanceof TableOption.FileTablePrimaryKeyConstraintName, FileTablePrimaryKeyConstraintName.meta)
                        .czse_ref(d -> d instanceof TableOption.FileTableStreamIdUniqueConstraintName, FileTableStreamIdUniqueConstraintName.meta)
                        .czse_ref(d -> d instanceof TableOption.FileTableFullPathUniqueConstraintName, FileTableFullPathUniqueConstraintName.meta)
                        .czse_ref(d -> d instanceof TableOption.SystemVersioning, SystemVersioning.meta)
                        .czse_ref(d -> d instanceof TableOption.RemoteDataArchive, RemoteDataArchive.meta)
                        .syntax_required()
                        .syntax_context_indentation()
                        .syntax_sub_line()
                        .build();
        // @formatter:on
    }

    public static class MemoryOptimized {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableOption>()
                        .overall("table_option")
                        .czse_ref(d -> d instanceof TableOption.MemoryOptimized, MemoryOptimized2.meta)
                        .czse_ref(d -> d instanceof TableOption.Durability, Durability.meta)
                        .czse_ref(d -> d instanceof TableOption.SystemVersioning, SystemVersioning.meta)
                        .syntax_required()
                        .syntax_context_indentation()
                        .syntax_sub_line()
                        .build();
        // @formatter:on
    }


    public static class DataCompression
            implements ModelMetaBlockConverter<TableOption.DataCompression> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableOption.DataCompression>()
                        .sub_keyword(TableOption.KeyWords.DATA_COMPRESSION)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub(getSyntaxString(TableOption.DataCompressionType.class))
                            .scope( TableOption.DataCompression::getType)
                            .syntax_required()
                            .and()
                        .sub()
                            .optional(d -> d.getPartitionsList() == null)
                            .sub_list(TruncateTableConverter.PartitionsConverter.meta)
                                .scope(d -> d.getPartitionsList())
                                .and()
                            .syntax_line()
                            .syntax_indentation_right()
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class FileTableDirectory
            implements ModelMetaBlockConverter<TableOption.FileTableDirectory> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableOption.FileTableDirectory>()
                        .sub_keyword(TableOption.KeyWords.FILETABLE_DIRECTORY)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("<directory_name>")
                            .scope(TableOption.FileTableDirectory::getDirectoryName)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class FileTableCollateFilename
            implements ModelMetaBlockConverter<TableOption.FileTableCollateFilename> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableOption.FileTableCollateFilename>()
                        .sub_keyword(TableOption.KeyWords.FILETABLE_COLLATE_FILENAME)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse(d -> d.getCollationName() != null,"<collation_name>")
                                .scope(d -> d.getCollationName())
                                .and()
                            .czse(d -> d.getCollationName() == null,"database_default")
                                .scope(d -> "database_default")
                                .and()
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class FileTablePrimaryKeyConstraintName
            implements ModelMetaBlockConverter<TableOption.FileTablePrimaryKeyConstraintName> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableOption.FileTablePrimaryKeyConstraintName>()
                        .sub_keyword(TableOption.KeyWords.FILETABLE_PRIMARY_KEY_CONSTRAINT_NAME)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("<collation_name>")
                            .scope(d -> d.getCollationName())
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class FileTableStreamIdUniqueConstraintName
            implements ModelMetaBlockConverter<TableOption.FileTableStreamIdUniqueConstraintName> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableOption.FileTableStreamIdUniqueConstraintName>()
                        .sub_keyword(TableOption.KeyWords.FILETABLE_STREAMID_UNIQUE_CONSTRAINT_NAME)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("<collation_name>")
                            .scope(d -> d.getCollationName())
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class FileTableFullPathUniqueConstraintName
            implements ModelMetaBlockConverter<TableOption.FileTableFullPathUniqueConstraintName> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableOption.FileTableFullPathUniqueConstraintName>()
                        .sub_keyword(TableOption.KeyWords.FILETABLE_FULLPATH_UNIQUE_CONSTRAINT_NAME)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("<collation_name>")
                            .scope(d -> d.getCollationName())
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class SystemVersioning
            implements ModelMetaBlockConverter<TableOption.SystemVersioning> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableOption.SystemVersioning>()
                        .sub_keyword(TableOption.KeyWords.SYSTEM_VERSIONING)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub_keyword(Keywords.ON)
                        .sub()
                            .optional(d -> d.getSchemaName() == null)
                            .sub_keyword(Other.GROUP_START)
                            .sub_keyword(TableOption.KeyWords.HISTORY_TABLE)
                            .sub_keyword(Assignment.ASSIGNMENT)
                            .sub("schema_name")
                                .scope(d -> d.getSchemaName())
                                .and()
                            .sub_keyword(Other.POINT)
                            .sub("history_table_name")
                                .scope(d -> d.getHistoryTableName())
                                .and()
                            .sub()
                                .optional(d -> d.getUseDataConsistencyCheck() == null)
                                .sub_keyword(Other.DELIMITER)
                                .sub_keyword(TableOption.KeyWords.DATA_CONSISTENCY_CHECK)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub()
                                    .czse_keyword(d -> d.getUseDataConsistencyCheck(),Keywords.ON)
                                    .czse_keyword(d -> !d.getUseDataConsistencyCheck(),Keywords.OFF)
                                    .and()
                                .syntax_line()
                                .syntax_indentation_right()
                                .and()
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class RemoteDataArchive
            implements ModelMetaBlockConverter<TableOption.RemoteDataArchive> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableOption.RemoteDataArchive>()
                        .sub_keyword(TableOption.KeyWords.REMOTE_DATA_ARCHIVE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse(d -> d.isUseOff())
                                .sub_keyword(Keywords.ON)
                                .sub()
                                    .optional(d -> d.getTableStretchOptionsList() == null)
                                    .sub_keyword(Other.GROUP_START)
                                    .sub("table_stretch_options")
                                        .list()
                                        .ref(TableStretchOptionsConverter.meta)
                                        .scope(d -> d.getTableStretchOptionsList())
                                        .and()
                                    .sub_keyword(Other.GROUP_END)
                                    .and()
                                .and()
                            .czse(d -> !d.isUseOff())
                                .sub_keyword(Keywords.OFF)
                                .sub_keyword(Other.GROUP_START)
                                .sub_keyword(TableOption.KeyWords.MIGRATION_STATE)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub_keyword(TableOption.KeyWords.PAUSED)
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .syntax_line()
                            .syntax_indentation_right()
                            .syntax_required()
                            .syntax_context_indentation()
                            .syntax_sub_line()
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class MemoryOptimized2
            implements ModelMetaBlockConverter<TableOption.MemoryOptimized> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableOption.MemoryOptimized>()
                        .sub_keyword(TableOption.KeyWords.MEMORY_OPTIMIZED)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub_keyword(Keywords.ON)
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class Durability
            implements ModelMetaBlockConverter<TableOption.Durability> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TableOption.Durability>()
                        .sub_keyword(TableOption.KeyWords.DURABILITY)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .czse_keyword(d -> !d.isSchemaAndData(),TableOption.KeyWords.SCHEMA_ONLY)
                            .czse_keyword(d -> d.isSchemaAndData(),TableOption.KeyWords.SCHEMA_AND_DATA)
                            .syntax_required()
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
