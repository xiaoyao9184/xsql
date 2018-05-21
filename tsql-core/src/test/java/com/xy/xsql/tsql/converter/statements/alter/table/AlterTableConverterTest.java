package com.xy.xsql.tsql.converter.statements.alter.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/17.
 */
public class AlterTableConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AlterTableConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "ALTER TABLE [ server_name . ] [ database_name . [ schema_name ] . | schema_name . ] table_name\n" +
                        "{\n" +
                        "\tALTER COLUMN column_name\n" +
                        "\t{\n" +
                        "\t\t[ type_schema_name ] type_name\n" +
                        "\t\t\t[ ( \n" +
                        "\t\t\t\t{\n" +
                        "\t\t\t\t\tprecision [ , scale ]\n" +
                        "\t\t\t\t\t| max\n" +
                        "\t\t\t\t\t| xml_schema_collection\n" +
                        "\t\t\t\t}\n" +
                        "\t\t\t ) ]\n" +
                        "\t\t[ COLLATE collation_name ]\n" +
                        "\t\t[ NULL | NULL ] [ SPARSE ]\n" +
                        "\t\t| { ADD | DROP } { ROWGUIDCOL | PERSISTED | [ NOT FOR REPLICATION ] | SPARSE | HIDDEN }\n" +
                        "\t\t| { ADD | DROP } MASKED [ WITH ( FUNCTION = 'mask_function' ) ]\n" +
                        "\t}\n" +
                        "\t[ WITH ( ONLINE = ON | OFF )\n" +
                        "\t| WITH CHECK | NOCHECK ]\n" +
                        "\t\n" +
                        "\t| ADD\n" +
                        "\t{\n" +
                        "\t\t<column_definition>\n" +
                        "\t\t| <computed_column_definition>\n" +
                        "\t\t| <table_constraint>\n" +
                        "\t\t| <column_set_definition>\n" +
                        "\t} [,...n]\n" +
                        "\t| [\n" +
                        "\t\tsystem_start_time_column_name datetime2 GENERATED ALWAYS AS ROW START\n" +
                        "\t\t\t[ HIDDEN ] [ NOT NULL ] [ CONSTRAINT constraint_name ]\n" +
                        "\t\t\tDEFAULT constant_expression [ WITH VALUES ] ,\n" +
                        "\t\tsystem_end_time_column_name datetime2 GENERATED ALWAYS AS ROW END\n" +
                        "\t\t\t[ HIDDEN ] [ NOT NULL ] [ CONSTRAINT constraint_name ]\n" +
                        "\t\t\tDEFAULT constant_expression [ WITH VALUES ]\n" +
                        "\t]\n" +
                        "\tPERIOD FOR SYSTEM_TIME ( system_start_time_column_name , system_end_time_column_name  )\n" +
                        "\t\n" +
                        "\t| DROP {\n" +
                        "\t\t[ CONSTRAINT ] [ IF EXISTS ]\n" +
                        "\t\t{\n" +
                        "\t\t\tconstraint_name\n" +
                        "\t\t\tWITH ( <drop_clustered_constraint_option> [,...n] )\n" +
                        "\t\t} [,...n]\n" +
                        "\t\t| COLUMN [ IF EXISTS ]\n" +
                        "\t\t{\n" +
                        "\t\t\tcolumn_name\n" +
                        "\t\t} [,...n]\n" +
                        "\t\t| PERIOD FOR SYSTEM_TIME\n" +
                        "\t} [,...n]\n" +
                        "\t\n" +
                        "\t| [ WITH ] { CHECK | NOCHECK } CONSTRAINT\n" +
                        "\t\t{ ALL | constraint_name [,...n] }\n" +
                        "\t\n" +
                        "\t| { ENABLE | DISABLE } TRIGGER\n" +
                        "\t\t{ ALL | trigger_name [,...n] }\n" +
                        "\t\n" +
                        "\t| { CHECK | NOCHECK } CHANGE_TRACKING\n" +
                        "\t\t[ WITH ( TRACK_COLUMNS_UPDATED = { ON | OFF } ) ]\n" +
                        "\t\n" +
                        "\t| SWITCH\n" +
                        "\t[ PARTITION source_partition_number_expression ]\n" +
                        "\t\tTO target_table\n" +
                        "\t\t[ PARTITION target_partition_number_expression ]\n" +
                        "\t\t[ WITH ( <low_lock_priority_wait> ) ]\n" +
                        "\t\n" +
                        "\t| SWITCH\n" +
                        "\t[ PARTITION source_partition_number_expression ]\n" +
                        "\t\tTO target_table\n" +
                        "\t\t[ PARTITION target_partition_number_expression ]\n" +
                        "\t\t[ WITH ( <low_lock_priority_wait> ) ]\n" +
                        "\t\n" +
                        "\t| SET\n" +
                        "\t(\n" +
                        "\t\tFILESTREAM_ON =\n" +
                        "\t\t\t{ partition_scheme_name | filegroup_name | \"default\" | \"NULL\" }\n" +
                        "\t\t| SYSTEM_VERSIONING = {\n" +
                        "\t\t\tON | OFF\n" +
                        "\t\t\t[ (\n" +
                        "\t\t\t\tHISTORY_TABLE = schema_name . history_table_name\n" +
                        "\t\t\t\t[ , DATA_CONSISTENCY_CHECK = { ON | OFF } ]\n" +
                        "\t\t\t\t[ , HISTORY_RETENTION_PERIOD = {\n" +
                        "\t\t\t\t\tINFINITE | number { DAY | DAYS | WEEK | WEEKS | MONTH | MONTHS | YEAR | YEARS }\n" +
                        "\t\t\t\t} ]\n" +
                        "\t\t\t) ]\n" +
                        "\t\t}\n" +
                        "\t)\n" +
                        "\t\n" +
                        "\t| REBUILD\n" +
                        "\t[ PARTITION = ALL ] [ WITH ( <rebuild_option> [,...n] ) ]\n" +
                        "\t| [ PARTITION = partition_number ] [ WITH ( <single_partition_rebuild_option> [,...n] ) ]\n" +
                        "\t\n" +
                        "\t| <table_option>\n" +
                        "\t\n" +
                        "\t| <filetable_option>\n" +
                        "\t\n" +
                        "\t| <stretch_configuration>\n" +
                        "}");
    }

}