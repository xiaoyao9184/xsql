package com.xy.xsql.hsweb.ezorm.render.support.tsql;

import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitionBuilder;
import com.xy.xsql.tsql.builder.chain.statements.alter.table.AlterColumnBuilder;
import com.xy.xsql.tsql.builder.chain.statements.alter.table.DropBuilder;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.statements.alter.table.AlterColumn;
import com.xy.xsql.tsql.model.statements.alter.table.AlterTable;
import com.xy.xsql.tsql.model.statements.alter.table.Drop;
import org.hsweb.ezorm.rdb.executor.BindSQL;
import org.hsweb.ezorm.rdb.executor.EmptySQL;
import org.hsweb.ezorm.rdb.executor.SQL;
import org.hsweb.ezorm.rdb.meta.RDBColumnMetaData;
import org.hsweb.ezorm.rdb.meta.RDBTableMetaData;
import org.hsweb.ezorm.rdb.render.SqlRender;
import org.hsweb.ezorm.rdb.render.support.simple.SimpleSQL;

import java.util.*;
import java.util.stream.Collectors;

import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._user_defined;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.statements.alter.Alters.$AlterTable;

public class TsqlMetaAlterRender implements SqlRender<Boolean> {

    @Override
    public SQL render(RDBTableMetaData table, Boolean executeRemove) {
        RDBTableMetaData old = table.getDatabaseMetaData().getTableMetaData(table.getName());

        if (old == null) throw new UnsupportedOperationException("旧表不存在!");
        List<RDBColumnMetaData> changedField = new ArrayList<>();
        List<RDBColumnMetaData> addedField = new ArrayList<>();
        List<RDBColumnMetaData> deletedField = new ArrayList<>();

        RDBTableMetaData oldMeta = old;
        if (executeRemove)
            oldMeta.getColumns().forEach(oldField -> {
                RDBColumnMetaData newMeta = table.findColumn(oldField.getName());
                if (newMeta == null) {
                    newMeta = table.getColumns().stream()
                            .filter(columnMetaData -> oldField.getName().equals(columnMetaData.getProperty("old-name").getValue()))
                            .findFirst().orElse(null);
                }
                if (newMeta == null || !newMeta.getName().equals(oldField.getName())) {
                    //删除的字段
                    deletedField.add(oldField);
                }
            });
        table.getColumns().forEach(newField -> {
            String oldName = newField.getProperty("old-name").getValue();
            if (oldName == null) oldName = newField.getName();
            RDBColumnMetaData oldField = oldMeta.findColumn(oldName);
            if (oldField == null) {
                //增加的字段
                addedField.add(newField);
            } else {
                if (!newField.getName().equals(oldField.getName()) ||
                        !newField.getDataType().equals(oldField.getDataType()) ||
//                        !newField.getComment().equals(oldField.getComment()) ||
                        /**
                         * version 1.2-SNAPSHOT SqlServer2012TableMetaParser is incorrect about not-null
                         * @see org.hsweb.ezorm.rdb.meta.parser.SqlServer2012TableMetaParser.TABLE_META_SQL
                         */
                        oldField.isNotNull() == newField.isNotNull()) {
                    changedField.add(newField);
                }
            }
        });
        //TODO comment
//        List<SQL> comments = new ArrayList<>();
//        String newTableComment = table.getComment();
//        String oldTableComment = old.getComment();
//        if (newTableComment == null) newTableComment = "";
//        if (oldTableComment == null) oldTableComment = "";
//        if (!newTableComment.equals(oldTableComment)) {
//            comments.add(buildTableCommentSql(table.getName(), table.getComment()));
//        }
        if (addedField.isEmpty() && changedField.isEmpty() && deletedField.isEmpty()) {
            return new EmptySQL();
        }

        LinkedList<BindSQL> bind = new LinkedList<>();
        buildAdd(table,addedField,bind);
        buildChange(table,oldMeta,changedField,bind);
        buildDrop(table,deletedField,bind);

        //TODO comment
//        LinkedList<BindSQL> commentSql = new LinkedList<>(comments.stream().map(s -> {
//            BindSQL binSql = new BindSQL();
//            binSql.setSql(s);
//            return binSql;
//        }).collect(Collectors.toList()));
//        bind.addAll(commentSql);

        if (bind.isEmpty()) {
            return new EmptySQL();
        }

        SQL sql = null;
        if (!bind.isEmpty()) {
            sql = bind.get(0).getSql();
            bind.removeFirst();
        }
        if (sql != null && !bind.isEmpty())
            ((SimpleSQL) sql).setBindSQLs(bind);
        return sql;
    }


    private void buildAdd(RDBTableMetaData table, List<RDBColumnMetaData> addedField, LinkedList<BindSQL> bind){
        ColumnDefinition[] cds = addedField.stream()
                .map(column -> {
                    ColumnDefinitionBuilder builder = new ColumnDefinitionBuilder()
                            .withColumnName(c(column.getName()))
                            .withDataType(_user_defined(column.getDataType()))
                            .withUseNotNull(column.isNotNull());

                    if (column.isPrimaryKey()) {
                        builder.$PrimaryKey();
                    }

                    return builder.build();
                })
                .toArray(ColumnDefinition[]::new);

        if(cds.length <= 0){
            return;
        }

        AlterTable alterTable = $AlterTable()
                .$(t(table.getName()))
                .$Add()
                    .withItems(cds)
                    .and()
                .build();

        String sqlString = BlockManager.INSTANCE.print(alterTable);
        BindSQL bindSQL = new BindSQL();
        bindSQL.setSql(new SimpleSQL(sqlString));
        bind.add(bindSQL);
    }

    private void buildDrop(RDBTableMetaData table, List<RDBColumnMetaData> deletedField, LinkedList<BindSQL> bind){
        Drop.DropColumn[] dds = deletedField.stream()
                .map(column -> {
                    DropBuilder.DropColumnBuilder builder = new DropBuilder.DropColumnBuilder()
                            .withItems(column.getName());

                    return builder.build();
                })
                .toArray(Drop.DropColumn[]::new);

        if(dds.length <= 0){
            return;
        }

        AlterTable alterTableDrop = $AlterTable()
                .$(t(table.getName()))
                .$Drop()
                .withItems(dds)
                .and()
                .build();

        String sqlString = BlockManager.INSTANCE.print(alterTableDrop);
        BindSQL bindSQL = new BindSQL();
        bindSQL.setSql(new SimpleSQL(sqlString));
        bind.add(bindSQL);
    }

    private void buildChange(RDBTableMetaData table, RDBTableMetaData oldMeta, List<RDBColumnMetaData> changedField, LinkedList<BindSQL> bind){
        Map<RDBColumnMetaData,RDBColumnMetaData> new2old = changedField.stream()
                .collect(Collectors.toMap(
                        (column) -> column,
                        (column) -> {
                            String oldName = column.getProperty("old-name").getValue();
                            if (oldName == null) oldName = column.getName();
                            return oldMeta.findColumn(oldName);
                        }
                ));

        new2old.entrySet().stream()
                .map(kv -> {
                    RDBColumnMetaData column = kv.getValue();
                    RDBColumnMetaData oldColumn = kv.getKey();

                    if (oldColumn.getDataType().equals(column.getDataType())
                            && oldColumn.isNotNull() == column.isNotNull()) {
                        return null;
                    }

                    //Type change
                    AlterColumnBuilder<AlterColumn> builder = new AlterColumnBuilder<AlterColumn>()
                            .withColumnName(column.getName())
                            .withTypeName(_user_defined(column.getDataType()));

                    if (oldColumn.isNotNull() != column.isNotNull()) {
                        if (column.isNotNull()) {
                            builder.withUseNull(false);
                        } else {
                            builder.withUseNull(true);
                        }
                    }

                    return $AlterTable()
                            .$(t(table.getName()))
                            .withItem(builder.build())
                            .build();
                })
                .filter(Objects::nonNull)
                .map(alterTable -> {
                    String sqlString = BlockManager.INSTANCE.print(alterTable);
                    BindSQL bindSQL = new BindSQL();
                    bindSQL.setSql(new SimpleSQL(sqlString));
                    return bindSQL;
                })
                .forEach(bind::add);

    }

}
