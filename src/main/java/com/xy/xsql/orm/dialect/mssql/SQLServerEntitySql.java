package com.xy.xsql.orm.dialect.mssql;


import com.xy.xsql.orm.annotation.Relationships;
import com.xy.xsql.orm.core.entity.sql.agreement.*;
import com.xy.xsql.orm.core.entity.template.*;
import com.xy.xsql.orm.data.entity.*;
import com.xy.xsql.orm.data.param.ArgSql;
import com.xy.xsql.orm.data.param.EntityTemplateTreeArg;
import com.xy.xsql.orm.util.CheckUtil;
import com.xy.xsql.orm.util.ListUtil;
import com.xy.xsql.orm.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Sql Server
 * Created by xiaoyao9184 on 2016/11/24.
 */
@SuppressWarnings({"StringBufferReplaceableByString", "Duplicates"})
public class SQLServerEntitySql
        implements
        SqlEntityTableManage,
        SqlEntityCRUD,
        SqlEntityUpdateStatusId,
        SqlEntityDeleteArg,
        SqlEntitySelectArg,
        SqlEntitySearchId,
        SqlEntitySearchArg,
        SqlPage {

    @Override
    public String getCreateTableSql(EntityTemplate entityTemplate) {
        StringBuilder sb = new StringBuilder()
                .append("CREATE TABLE")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n")
                .append("(")
                .append("\n");

        int index = 0;
        for (EntityColumn entityColumn: entityTemplate.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getName())
                    .append(" ")
                    .append(entityColumn.getType());
            if(entityColumn.getLength() != null &&
                    entityColumn.getLength() > 0){
                sb.append("(")
                        .append(entityColumn.getLength().toString())
                        .append(")");
            }
            sb.append("\n");
            index++;
        }

        sb.append(")")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getDropTableSql(EntityTemplate entityTemplate) {
        return new StringBuilder()
                .append("DROP TABLE")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n")
                .toString();
    }

    @Override
    public String getAlterTableSql(EntityTemplate entityTemplateOld, EntityTemplate entityTemplateNew) {
        //Comparison Column
        EntityColumnComparator entityColumnComparator = new EntityColumnComparator();
        List<EntityColumn> addList = ListUtil.lostElementList(
                entityTemplateOld.getColumns(),
                entityTemplateNew.getColumns(),
                entityColumnComparator);
        List<EntityColumn> dropList = ListUtil.lostElementList(
                entityTemplateNew.getColumns(),
                entityTemplateOld.getColumns(),
                entityColumnComparator);
        List<EntityColumn> alterList = ListUtil.bothIncludedElementList(
                entityTemplateNew.getColumns(),
                entityTemplateOld.getColumns(),
                entityColumnComparator);

        StringBuilder sb = new StringBuilder();

//        ALTER TABLE test_sql
//        ADD column_0 int

        int index = 0;
        if(!CheckUtil.isNullOrEmpty(addList)){
            sb.append("ALTER TABLE")
                    .append("\n")
                    .append(entityTemplateOld.getTable().getName())
                    .append("\n")
                    .append("ADD");
            for (EntityColumn column: addList) {
                if(index != 0){
                    sb.append(",");
                }
                sb.append(" ")
                        .append(column.getName())
                        .append(" ")
                        .append(column.getType());
                if(column.getLength() != null &&
                        column.getLength() > 0){
                    sb.append("(")
                            .append(column.getLength().toString())
                            .append(")");
                }
                sb.append("\n");
                index++;
            }
            if(index != 0){
                sb.append("\n");
            }
        }

//        ALTER TABLE test_sql
//        DROP COLUMN column_1
        index = 0;
        if(!CheckUtil.isNullOrEmpty(dropList)){
            sb.append("ALTER TABLE")
                    .append("\n")
                    .append(entityTemplateOld.getTable().getName())
                    .append("\n")
                    .append("DROP COLUMN");
            for (EntityColumn column: dropList) {
                if(index != 0){
                    sb.append(",");
                }
                sb.append(" ")
                        .append(column.getName())
                        .append(" ")
                        .append("\n");
                index++;
            }
            if(index != 0){
                sb.append("\n");
            }
        }

//        ALTER TABLE test_sql
//        ALTER COLUMN column_2 VARCHAR(100)
        if(!CheckUtil.isNullOrEmpty(alterList)){
            for (EntityColumn column: alterList) {
                sb.append("ALTER TABLE")
                        .append("\n")
                        .append(entityTemplateOld.getTable().getName())
                        .append("\n")
                        .append("ALTER COLUMN")
                        .append(" ")
                        .append(column.getName())
                        .append(" ")
                        .append(column.getType());
                if(column.getLength() != null &&
                        column.getLength() > 0){
                    sb.append("(")
                            .append(column.getLength().toString())
                            .append(")");
                }
                sb.append("\n");
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    public String getTableCountSql(EntityTemplate entityTemplate) {
        return new StringBuilder()
                .append("SELECT\n")
                .append("COUNT(name)\n")
                .append("FROM sysobjects\n")
                .append("WHERE name=\n")
                .append("'")
                .append(entityTemplate.getTable().getName())
                .append("'")
                .append("\n")
                .append("AND type='u'")
                .toString();
    }



    @Override
    public String getInsertSql(EntityTemplate entityTemplate) {
        StringBuilder sb = new StringBuilder()
                .append("INSERT INTO")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n")
                .append("(")
                .append("\n");

        int index = 0;
        for (EntityColumn eColumn: entityTemplate.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(eColumn.getName());
            sb.append("\n");
            index++;
        }

        sb.append(")")
                .append("\n");

        sb.append("VALUES")
                .append("\n")
                .append("(")
                .append("\n")
                .append(StringUtil.fillJoin("?", entityTemplate.getColumns().size(), ","))
                .append("\n")
                .append(")")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getInsertByEntityCountSql(EntityTemplate entityTemplate, int entityCount){
        if(entityCount == 1){
            return getInsertSql(entityTemplate);
        }

        StringBuilder sb = new StringBuilder()
                .append("INSERT INTO")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n")
                .append("(")
                .append("\n");

        int index = 0;
        for (EntityColumn eColumn: entityTemplate.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(eColumn.getName());
            sb.append("\n");
            index++;
        }

        sb.append(")")
                .append("\n");

        sb.append("VALUES")
                .append("\n");

        StringBuilder values = new StringBuilder()
                .append("(")
                .append("\n")
                .append(StringUtil.fillJoin("?", entityTemplate.getColumns().size(), ","))
                .append("\n")
                .append(")")
                .append("\n");

        sb.append(StringUtil.fillJoin(values.toString(),entityCount,","));

        return sb.toString();
    }

    @Override
    public String getSelectByIdSql(EntityTemplate entityTemplate){
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;
        for (EntityColumn eColumn: entityTemplate.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(eColumn.getName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(entityTemplate.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getSelectByIdsSql(EntityTemplate entityTemplate, int idCount){
        if(idCount == 1){
            return getSelectByIdSql(entityTemplate);
        }

        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;
        for (EntityColumn eColumn: entityTemplate.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(eColumn.getName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n");

        if(idCount > 0){
            sb.append("WHERE")
                    .append("\n")
                    .append(entityTemplate.getKeys().get(0).getName())
                    .append("\n")
                    .append("IN (")
                    .append(StringUtil.fillJoin("?", idCount, ","))
                    .append(")")
                    .append("\n");
        }

        return sb.toString();
    }

    @Override
    public String getUpdateByIdSql(EntityTemplate entityTemplate) {
        StringBuilder sb = new StringBuilder()
                .append("UPDATE")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n")
                .append("SET")
                .append("\n");

        int index = 0;
        for (EntityColumn eColumn: entityTemplate.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(eColumn.getName())
                .append(" = ?")
                .append("\n");
            index++;
        }

        sb.append("WHERE")
                .append("\n")
                .append(entityTemplate.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getUpdateByIdsSql(EntityTemplate entityTemplate, int idCount) {
        if(idCount == 1){
            return getUpdateByIdSql(entityTemplate);
        }

        StringBuilder sb = new StringBuilder()
                .append("UPDATE")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n")
                .append("SET")
                .append("\n");

        int index = 0;
        for (EntityColumn eColumn: entityTemplate.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(eColumn.getName())
                    .append(" = ")
                    .append("CASE")
                    .append(" ")
                    .append(entityTemplate.getKeys().get(0).getName())
                    .append("\n")
                    .append(StringUtil.fillJoin("WHEN ? THEN ?",idCount,"\n"))
                    .append("\n")
                    .append("END")
                    .append("\n");
            index++;
        }

        sb.append("WHERE")
                .append("\n")
                .append(entityTemplate.getKeys().get(0).getName())
                .append("\n")
                .append("IN (")
                .append(StringUtil.fillJoin("?", idCount, ","))
                .append(")")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getDeleteByIdSql(EntityTemplate entityTemplate) {
        return new StringBuilder()
                .append("DELETE FROM")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(entityTemplate.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n")
                .toString();
    }

    @Override
    public String getDeleteByIdsSql(EntityTemplate entityTemplate, int idCount) {
        if(idCount == 1){
            return getDeleteByIdSql(entityTemplate);
        }

        return new StringBuilder()
                .append("DELETE FROM")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(entityTemplate.getKeys().get(0).getName())
                .append("\n")
                .append("IN (")
                .append(StringUtil.fillJoin("?", idCount, ","))
                .append(")")
                .append("\n")
                .toString();
    }


    @Override
    public String getUpdateStatusByIdSql(EntityTemplate entityTemplate){
        if(entityTemplate.getStatus() == null){
            throw new UnsupportedOperationException("没有Status字段！");
        }
        return new StringBuilder()
                .append("UPDATE")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n")
                .append("SET")
                .append("\n")
                .append(entityTemplate.getStatus().getName())
                .append(" = ")
                .append(" ? ")
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(entityTemplate.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n")
                .toString();
    }

    @Override
    public String getUpdateStatusByIdsSql(EntityTemplate entityTemplate, int idCount){
        if(idCount == 1){
            return getUpdateStatusByIdSql(entityTemplate);
        }
        if(entityTemplate.getStatus() == null){
            throw new UnsupportedOperationException("没有Status字段！");
        }

        StringBuilder sb = new StringBuilder()
                .append("UPDATE")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n")
                .append("SET")
                .append("\n")
                .append(entityTemplate.getStatus().getName())
                .append(" = ?")
                .append("\n");

        if(idCount > 0){
            sb.append("WHERE")
                    .append("\n")
                    .append(entityTemplate.getKeys().get(0).getName())
                    .append("\n")
                    .append("IN (")
                    .append(StringUtil.fillJoin("?", idCount, ","))
                    .append(")")
                    .append("\n");
        }

        return sb.toString();
    }


    @Override
    public ArgSql getDeleteByArgsSql(EntityTemplate entityTemplate, Object... args) {
        if(args.length > entityTemplate.getParams().size()){
            throw new UnsupportedOperationException(entityTemplate.getClazz().getName() + " 实际参数数量大于标注的参数数量，无法生成SQL！");
        }

        List<Object> argList = new ArrayList<>();

        StringBuilder sb = new StringBuilder()
                .append("DELETE FROM")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n");

        List<EntityParam> list = new EntityParamFilter()
                .withArgs(args)
                .build(entityTemplate.getParams());

        int index;
        if(list.size() > 0){
            sb.append("WHERE\n");
            index = 0;
            for (EntityParam param: list) {
                sb.append(CheckUtil.isStart(index) ? "" : "AND")
                        .append(" ")
                        .append(param.getColumn().getName())
                        .append(" ")
                        .append(param.getRelationship().getName())
                        .append(" ");

                if(param.getRelationship().equals(Relationships.IN)){
                    sb.append("(")
                            .append(StringUtil.fillJoin("?",param.getArgsCount(),","))
                            .append(")")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub));
                }else{
                    sb.append("?")
                            .append("\n");
                    argList.add(param.getArg());
                }
                index++;
            }
        }

        return new ArgSql()
                .withSql(sb.toString())
                .withArgs(argList);
    }


    @Override
    public ArgSql getSelectByArgsSql(EntityTemplate entityTemplate, Object... args){
        if(args.length > entityTemplate.getParams().size()){
            throw new UnsupportedOperationException(entityTemplate.getClazz().getName() + " 实际参数数量大于标注的参数数量，无法生成SQL！");
        }

        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;
        for (EntityColumn entityColumn: entityTemplate.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n");


        List<EntityParam> list = new EntityParamFilter()
                .withArgs(args)
                .build(entityTemplate.getParams());

        if(list.size() > 0){
            sb.append("WHERE\n");
            index = 0;
            for (EntityParam param: list) {
                sb.append(CheckUtil.isStart(index) ? "" : "AND")
                        .append(" ")
                        .append(param.getColumn().getName())
                        .append(" ")
                        .append(param.getRelationship().getName())
                        .append(" ");

                if(param.getRelationship().equals(Relationships.IN)){
                    sb.append("(")
                            .append(StringUtil.fillJoin("?",param.getArgsCount(),","))
                            .append(")")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub));
                }else{
                    sb.append("?")
                            .append("\n");
                    argList.add(param.getArg());
                }
                index++;
            }
        }

        if(entityTemplate.getOrders().size() > 0){
            sb.append("ORDER BY\n");
            index = 0;
            for (EntityOrder order: entityTemplate.getOrders()) {
                sb.append(CheckUtil.isStart(index) ? "" : ",")
                        .append(" ")
                        .append(order.getColumn().getName())
                        .append(" ")
                        .append(order.isAsc() ? "ASC" : "DESC")
                        .append("\n");
                index++;
            }
        }

        return new ArgSql()
                .withSql(sb.toString())
                .withArgs(argList);
    }


    @Override
    public String getSelectJoinByIdSql(EntityTemplate entityTemplate) {
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;

        List<EntityColumn> allColumnList = new EntityColumnExpander()
                .build(entityTemplate);

        for (EntityColumn entityColumn: allColumnList) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append(" AS ")
                    .append(entityColumn.getAliasName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append(" AS ")
                .append(entityTemplate.getTable().getAliasName())
                .append("\n");

        List<EntityLink> allEntityLinkList = new EntityLinkExpander()
                .build(entityTemplate);
        for (EntityLink entityLinkEntity: allEntityLinkList) {
            EntityTemplate entityTemplateSub = entityLinkEntity.getTemplate();
            EntityColumn entityColumn = entityLinkEntity.getColumn();
            sb.append("LEFT JOIN")
                    .append("\n")
                    .append(entityTemplateSub.getTable().getName())
                    .append(" AS ")
                    .append(entityTemplateSub.getTable().getAliasName())
                    .append("\n");
            sb.append("ON")
                    .append(" ")
                    .append(entityTemplateSub.getTable().getAliasName())
                    .append(".")
                    .append(entityTemplateSub.getKeys().get(0).getName())
                    .append(" = ")
                    .append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append("\n");
        }

        sb.append("WHERE")
                .append("\n")
                .append(entityTemplate.getTable().getAliasName())
                .append(".")
                .append(entityTemplate.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getSelectJoinByIdsSql(EntityTemplate entityTemplate, int idCount) {

        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;

        List<EntityColumn> allColumnList = new EntityColumnExpander()
                .build(entityTemplate);

        for (EntityColumn entityColumn: allColumnList) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append(" AS ")
                    .append(entityColumn.getAliasName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append(" AS ")
                .append(entityTemplate.getTable().getAliasName())
                .append("\n");

        List<EntityLink> allEntityLinkList = new EntityLinkExpander()
                .build(entityTemplate);
        for (EntityLink entityLinkEntity: allEntityLinkList) {
            EntityTemplate entityTemplateSub = entityLinkEntity.getTemplate();
            EntityColumn entityColumn = entityLinkEntity.getColumn();
            sb.append("LEFT JOIN")
                    .append("\n")
                    .append(entityTemplateSub.getTable().getName())
                    .append(" AS ")
                    .append(entityTemplateSub.getTable().getAliasName())
                    .append("\n");
            sb.append("ON")
                    .append(" ")
                    .append(entityTemplateSub.getTable().getAliasName())
                    .append(".")
                    .append(entityTemplateSub.getKeys().get(0).getName())
                    .append(" = ")
                    .append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append("\n");
        }

        if(idCount > 0){
            sb.append("WHERE")
                    .append("\n")
                    .append(entityTemplate.getTable().getAliasName())
                    .append(".")
                    .append(entityTemplate.getKeys().get(0).getName())
                    .append("\n")
                    .append("IN (")
                    .append(StringUtil.fillJoin("?", idCount, ","))
                    .append(")")
                    .append("\n");
        }

        return sb.toString();
    }


    @Override
    public ArgSql getSelectJoinByArgsSql(EntityTemplate entityTemplate, Object... args) {
        if(args.length > entityTemplate.getParams().size()){
            throw new UnsupportedOperationException(entityTemplate.getClazz().getName() + " 实际参数数量大于标注的参数数量，无法生成SQL！");
        }

        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;

        List<EntityColumn> allColumnList = new EntityColumnExpander()
                .build(entityTemplate);

        for (EntityColumn entityColumn: allColumnList) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append(" AS ")
                    .append(entityColumn.getAliasName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append(" AS ")
                .append(entityTemplate.getTable().getAliasName())
                .append("\n");

        List<EntityLink> allEntityLinkList = new EntityLinkExpander()
                .build(entityTemplate);
        for (EntityLink entityLinkEntity: allEntityLinkList) {
            EntityTemplate entityTemplateSub = entityLinkEntity.getTemplate();
            EntityColumn entityColumn = entityLinkEntity.getColumn();
            sb.append("LEFT JOIN")
                    .append("\n")
                    .append(entityTemplateSub.getTable().getName())
                    .append(" AS ")
                    .append(entityTemplateSub.getTable().getAliasName())
                    .append("\n");
            sb.append("ON")
                    .append(" ")
                    .append(entityTemplateSub.getTable().getAliasName())
                    .append(".")
                    .append(entityTemplateSub.getKeys().get(0).getName())
                    .append(" = ")
                    .append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append("\n");
        }

        List<EntityParam> list = new EntityParamFilter()
                .withArgs(args)
                .build(entityTemplate.getParams());

        if(list.size() > 0){
            sb.append("WHERE\n");
            index = 0;
            for (EntityParam param: list) {
                sb.append(CheckUtil.isStart(index) ? "" : "AND")
                        .append(" ")
                        .append(param.getColumn().getTable().getAliasName())
                        .append(".")
                        .append(param.getColumn().getName())
                        .append(" ")
                        .append(param.getRelationship().getName())
                        .append(" ");

                if(param.getRelationship().equals(Relationships.IN)){
                    sb.append("(")
                            .append(StringUtil.fillJoin("?",param.getArgsCount(),","))
                            .append(")")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub));
                }else{
                    sb.append("?")
                            .append("\n");
                    argList.add(param.getArg());
                }
                index++;
            }
        }

        List<EntityOrder> listOrder = new EntityOrderExpander()
                .build(entityTemplate);

        if(listOrder.size() > 0){
            sb.append("ORDER BY\n");
            index = 0;
            for (EntityOrder order: listOrder) {
                sb.append(CheckUtil.isStart(index) ? "" : ",")
                        .append(" ")
                        .append(order.getColumn().getTable().getAliasName())
                        .append(".")
                        .append(order.getColumn().getName())
                        .append(" ")
                        .append(order.isAsc() ? "ASC" : "DESC")
                        .append("\n");
                index++;
            }
        }

        return new ArgSql()
                .withSql(sb.toString())
                .withArgs(argList);
    }

    @Override
    public ArgSql getSelectJoinByTreeArgSql(EntityTemplate entityTemplate, EntityTemplateTreeArg entityTemplateTreeArg) {
        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;

        List<EntityColumn> allColumnList = new EntityColumnExpander()
                .build(entityTemplate);

        for (EntityColumn entityColumn: allColumnList) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append(" AS ")
                    .append(entityColumn.getAliasName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append(" AS ")
                .append(entityTemplate.getTable().getAliasName())
                .append("\n");

        List<EntityLink> allEntityLinkList = new EntityLinkExpander()
                .build(entityTemplate);
        for (EntityLink entityLinkEntity: allEntityLinkList) {
            EntityTemplate entityTemplateSub = entityLinkEntity.getTemplate();
            EntityColumn entityColumn = entityLinkEntity.getColumn();
            sb.append("LEFT JOIN")
                    .append("\n")
                    .append(entityTemplateSub.getTable().getName())
                    .append(" AS ")
                    .append(entityTemplateSub.getTable().getAliasName())
                    .append("\n");
            sb.append("ON")
                    .append(" ")
                    .append(entityTemplateSub.getTable().getAliasName())
                    .append(".")
                    .append(entityTemplateSub.getKeys().get(0).getName())
                    .append(" = ")
                    .append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append("\n");
        }

        List<EntityParam> list = new EntityParamExpander()
                .withTreeArg(entityTemplateTreeArg)
                .build(entityTemplate);

        if(list.size() > 0){
            sb.append("WHERE\n");
            index = 0;
            for (EntityParam param: list) {
                sb.append(CheckUtil.isStart(index) ? "" : "AND")
                        .append(" ")
                        .append(param.getColumn().getTable().getAliasName())
                        .append(".")
                        .append(param.getColumn().getName())
                        .append(" ")
                        .append(param.getRelationship().getName())
                        .append(" ");

                if(param.getRelationship().equals(Relationships.IN)){
                    sb.append("(")
                            .append(StringUtil.fillJoin("?",param.getArgsCount(),","))
                            .append(")")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub));
                }else{
                    sb.append("?")
                            .append("\n");
                    argList.add(param.getArg());
                }
                index++;
            }
        }

        List<EntityOrder> listOrder = new EntityOrderExpander()
                .build(entityTemplate);

        if(listOrder.size() > 0){
            sb.append("ORDER BY\n");
            index = 0;
            for (EntityOrder order: listOrder) {
                sb.append(CheckUtil.isStart(index) ? "" : ",")
                        .append(" ")
                        .append(order.getColumn().getTable().getAliasName())
                        .append(".")
                        .append(order.getColumn().getName())
                        .append(" ")
                        .append(order.isAsc() ? "ASC" : "DESC")
                        .append("\n");
                index++;
            }
        }

        return new ArgSql()
                .withSql(sb.toString())
                .withArgs(argList);
    }


    @Override
    public String getPageSql(String selectSql, Integer pageStart, Integer pageSize) {
        Integer rowStart = (pageStart-1) * pageSize;
        return new StringBuilder()
                .append("SELECT")
                .append("\n")
                .append("row_number() AS ")
                .append(getPageSqlRowNumberName())
                .append(",*")
                .append("\n")
                .append("FROM")
                .append("\n")
                .append("(")

                    .append("\n")
                    .append("SELECT TOP ")
                    .append(rowStart + pageSize)
                    .append("\n")
                    .append("*")
                    .append("\n")
                    .append("FROM")
                    .append("\n")
                    .append("(")

                        .append("\n")
                        .append(selectSql)
                        .append("\n")

                    .append(") AS top")

                .append(") AS page")
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(getPageSqlRowNumberName())
                .append(" > ")
                .append(rowStart)
                .toString();
    }

    @Override
    public String getPageSqlRowNumberName() {
        return "rowNumber";
    }

    @Override
    public String getCountSql(String selectSql) {
        return new StringBuilder()
                .append("SELECT")
                .append("\n")
                .append("COUNT(*)")
                .append("\n")
                .append("FROM")
                .append("\n")
                .append("(")
                .append("\n")
                .append(selectSql)
                .append("\n")
                .append(") AS main")
                .append("\n")
                .toString();
    }
}
