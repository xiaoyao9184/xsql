package com.xy.xsql.spring.dialect.mssql;

import com.xy.xsql.entity.api.annotation.Relationships;
import com.xy.xsql.entity.api.dialect.render.jsql.*;
import com.xy.xsql.entity.core.template.*;
import com.xy.xsql.entity.core.util.CheckUtil;
import com.xy.xsql.entity.core.util.ListUtil;
import com.xy.xsql.entity.core.util.StringUtil;
import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.entity.model.template.*;
import com.xy.xsql.entity.model.template.param.EntityTemplateTreeArg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Sql Server
 * Created by xiaoyao9184 on 2016/11/24.
 */
@SuppressWarnings({"StringBufferReplaceableByString", "Duplicates"})
public class MSSQLTemplateRenderer
        implements
        TemplateTableManageRenderer,
        TemplateCRUDRenderer,
        TemplateUpdateStatusIdRenderer,
        TemplateDeleteArgRenderer,
        TemplateSelectArgRenderer,
        TemplateSearchIdRenderer,
        TemplateSearchArgRenderer,
        SqlPageRenderer,
        TemplatePageSelectArgRenderer,
        TemplatePageSearchArgRenderer {

    @Override
    public String getCreateTableSql(EntityInfo entityInfo) {
        StringBuilder sb = new StringBuilder()
                .append("CREATE TABLE")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n")
                .append("(")
                .append("\n");

        int index = 0;
        for (EntityColumn entityColumn: entityInfo.getColumns()) {
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
    public String getDropTableSql(EntityInfo entityInfo) {
        return new StringBuilder()
                .append("DROP TABLE")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n")
                .toString();
    }

    @Override
    public String getAlterTableSql(EntityInfo entityInfoOld, EntityInfo entityInfoNew) {
        //Comparison Column
        EntityColumnComparator entityColumnComparator = new EntityColumnComparator();
        List<EntityColumn> addList = ListUtil.lostElementList(
                entityInfoOld.getColumns(),
                entityInfoNew.getColumns(),
                entityColumnComparator);
        List<EntityColumn> dropList = ListUtil.lostElementList(
                entityInfoNew.getColumns(),
                entityInfoOld.getColumns(),
                entityColumnComparator);
        List<EntityColumn> alterList = ListUtil.bothIncludedElementList(
                entityInfoNew.getColumns(),
                entityInfoOld.getColumns(),
                entityColumnComparator);

        StringBuilder sb = new StringBuilder();

//        ALTER TABLE test_sql
//        ADD column_0 int

        int index = 0;
        if(!CheckUtil.isNullOrEmpty(addList)){
            sb.append("ALTER TABLE")
                    .append("\n")
                    .append(entityInfoOld.getTable().getName())
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
                    .append(entityInfoOld.getTable().getName())
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
                        .append(entityInfoOld.getTable().getName())
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
    public String getTableCountSql(EntityInfo entityInfo) {
        return new StringBuilder()
                .append("SELECT\n")
                .append("COUNT(name)\n")
                .append("FROM sysobjects\n")
                .append("WHERE name=\n")
                .append("'")
                .append(entityInfo.getTable().getName())
                .append("'")
                .append("\n")
                .append("AND type='u'")
                .toString();
    }



    @Override
    public String getInsertSql(EntityInfo entityInfo) {
        StringBuilder sb = new StringBuilder()
                .append("INSERT INTO")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n")
                .append("(")
                .append("\n");

        int index = 0;
        for (EntityColumn eColumn: entityInfo.getColumns()) {
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
                .append(StringUtil.fillJoin("?", entityInfo.getColumns().size(), ","))
                .append("\n")
                .append(")")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getInsertByEntityCountSql(EntityInfo entityInfo, int entityCount){
        if(entityCount == 1){
            return getInsertSql(entityInfo);
        }

        StringBuilder sb = new StringBuilder()
                .append("INSERT INTO")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n")
                .append("(")
                .append("\n");

        int index = 0;
        for (EntityColumn eColumn: entityInfo.getColumns()) {
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
                .append(StringUtil.fillJoin("?", entityInfo.getColumns().size(), ","))
                .append("\n")
                .append(")")
                .append("\n");

        sb.append(StringUtil.fillJoin(values.toString(),entityCount,","));

        return sb.toString();
    }

    @Override
    public String getSelectByIdSql(EntityInfo entityInfo){
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;
        for (EntityColumn eColumn: entityInfo.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(eColumn.getName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(entityInfo.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getSelectByIdsSql(EntityInfo entityInfo, int idCount){
        if(idCount == 1){
            return getSelectByIdSql(entityInfo);
        }

        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;
        for (EntityColumn eColumn: entityInfo.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(eColumn.getName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n");

        if(idCount > 0){
            sb.append("WHERE")
                    .append("\n")
                    .append(entityInfo.getKeys().get(0).getName())
                    .append("\n")
                    .append("IN (")
                    .append(StringUtil.fillJoin("?", idCount, ","))
                    .append(")")
                    .append("\n");
        }

        return sb.toString();
    }

    @Override
    public String getUpdateByIdSql(EntityInfo entityInfo) {
        StringBuilder sb = new StringBuilder()
                .append("UPDATE")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n")
                .append("SET")
                .append("\n");

        int index = 0;
        for (EntityColumn eColumn: entityInfo.getColumns()) {
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
                .append(entityInfo.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getUpdateByIdsSql(EntityInfo entityInfo, int idCount) {
        if(idCount == 1){
            return getUpdateByIdSql(entityInfo);
        }

        StringBuilder sb = new StringBuilder()
                .append("UPDATE")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n")
                .append("SET")
                .append("\n");

        int index = 0;
        for (EntityColumn eColumn: entityInfo.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(eColumn.getName())
                    .append(" = ")
                    .append("CASE")
                    .append(" ")
                    .append(entityInfo.getKeys().get(0).getName())
                    .append("\n")
                    .append(StringUtil.fillJoin("WHEN ? THEN ?",idCount,"\n"))
                    .append("\n")
                    .append("END")
                    .append("\n");
            index++;
        }

        sb.append("WHERE")
                .append("\n")
                .append(entityInfo.getKeys().get(0).getName())
                .append("\n")
                .append("IN (")
                .append(StringUtil.fillJoin("?", idCount, ","))
                .append(")")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getDeleteByIdSql(EntityInfo entityInfo) {
        return new StringBuilder()
                .append("DELETE FROM")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(entityInfo.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n")
                .toString();
    }

    @Override
    public String getDeleteByIdsSql(EntityInfo entityInfo, int idCount) {
        if(idCount == 1){
            return getDeleteByIdSql(entityInfo);
        }

        return new StringBuilder()
                .append("DELETE FROM")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(entityInfo.getKeys().get(0).getName())
                .append("\n")
                .append("IN (")
                .append(StringUtil.fillJoin("?", idCount, ","))
                .append(")")
                .append("\n")
                .toString();
    }


    @Override
    public String getUpdateStatusByIdSql(EntityInfo entityInfo){
        if(entityInfo.getStatus() == null){
            throw new UnsupportedOperationException("没有Status字段！");
        }
        return new StringBuilder()
                .append("UPDATE")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n")
                .append("SET")
                .append("\n")
                .append(entityInfo.getStatus().getName())
                .append(" = ")
                .append(" ? ")
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(entityInfo.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n")
                .toString();
    }

    @Override
    public String getUpdateStatusByIdsSql(EntityInfo entityInfo, int idCount){
        if(idCount == 1){
            return getUpdateStatusByIdSql(entityInfo);
        }
        if(entityInfo.getStatus() == null){
            throw new UnsupportedOperationException("没有Status字段！");
        }

        StringBuilder sb = new StringBuilder()
                .append("UPDATE")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n")
                .append("SET")
                .append("\n")
                .append(entityInfo.getStatus().getName())
                .append(" = ?")
                .append("\n");

        if(idCount > 0){
            sb.append("WHERE")
                    .append("\n")
                    .append(entityInfo.getKeys().get(0).getName())
                    .append("\n")
                    .append("IN (")
                    .append(StringUtil.fillJoin("?", idCount, ","))
                    .append(")")
                    .append("\n");
        }

        return sb.toString();
    }


    @Override
    public PlaceholderJSql getDeleteByArgsSql(EntityInfo entityInfo, Object... args) {
        if(args.length > entityInfo.getParams().size()){
            throw new UnsupportedOperationException(entityInfo.getClazz().getName() + " 实际参数数量大于标注的参数数量，无法生成SQL！");
        }

        List<Object> argList = new ArrayList<>();

        StringBuilder sb = new StringBuilder()
                .append("DELETE FROM")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n");

        List<EntityParam> list = new EntityParamFilter()
                .withArgs(args)
                .build(entityInfo.getParams());

        int index;
        if(list.size() > 0){
            sb.append("WHERE\n");
            index = 0;
            for (EntityParam param: list) {
                sb.append(CheckUtil.isStart(index) ? "" : "AND")
                        .append(" ")
                        .append(param.getColumn().getName())
                        .append(" ")
                        .append(param.getRelationship().getKeyword())
                        .append(" ");

                if(param.getRelationship().equals(Relationships.IN)){
                    sb.append("(")
                            .append(StringUtil.fillJoin("?",param.getArgsCount(),","))
                            .append(")")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub));
                }else if(param.getRelationship().equals(Relationships.BETWEEN)){
                    sb.append("? AND ?")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub).subList(0,2));
                }else{
                    sb.append("?")
                            .append("\n");
                    argList.add(param.getArg());
                }
                index++;
            }
        }

        return new PlaceholderJSql()
                .withSql(sb.toString())
                .withArgs(argList);
    }


    @Override
    public PlaceholderJSql getSelectByArgsSql(EntityInfo entityInfo, Object... args){
        if(args.length > entityInfo.getParams().size()){
            throw new UnsupportedOperationException(entityInfo.getClazz().getName() + " 实际参数数量大于标注的参数数量，无法生成SQL！");
        }

        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;
        for (EntityColumn entityColumn: entityInfo.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n");


        List<EntityParam> list = new EntityParamFilter()
                .withArgs(args)
                .build(entityInfo.getParams());

        if(list.size() > 0){
            sb.append("WHERE\n");
            index = 0;
            for (EntityParam param: list) {
                sb.append(CheckUtil.isStart(index) ? "" : "AND")
                        .append(" ")
                        .append(param.getColumn().getName())
                        .append(" ")
                        .append(param.getRelationship().getKeyword())
                        .append(" ");

                if(param.getRelationship().equals(Relationships.IN)){
                    sb.append("(")
                            .append(StringUtil.fillJoin("?",param.getArgsCount(),","))
                            .append(")")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub));
                }else if(param.getRelationship().equals(Relationships.BETWEEN)){
                    sb.append("? AND ?")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub).subList(0,2));
                }else{
                    sb.append("?")
                            .append("\n");
                    argList.add(param.getArg());
                }
                index++;
            }
        }

        if(entityInfo.getOrders().size() > 0){
            sb.append("ORDER BY\n");
            index = 0;
            for (EntityOrder order: entityInfo.getOrders()) {
                sb.append(CheckUtil.isStart(index) ? "" : ",")
                        .append(" ")
                        .append(order.getColumn().getName())
                        .append(" ")
                        .append(order.isAsc() ? "ASC" : "DESC")
                        .append("\n");
                index++;
            }
        }

        return new PlaceholderJSql()
                .withSql(sb.toString())
                .withArgs(argList);
    }


    @Override
    public String getSelectJoinByIdSql(EntityInfo entityInfo) {
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;

        List<EntityColumn> allColumnList = new EntityColumnExpander()
                .build(entityInfo);

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
                .append(entityInfo.getTable().getName())
                .append(" AS ")
                .append(entityInfo.getTable().getAliasName())
                .append("\n");

        List<EntityLink> allEntityLinkList = new EntityLinkExpander()
                .build(entityInfo);
        for (EntityLink entityLinkEntity: allEntityLinkList) {
            EntityInfo entityInfoSub = entityLinkEntity.getTemplate();
            EntityColumn entityColumn = entityLinkEntity.getColumn();
            sb.append("LEFT JOIN")
                    .append("\n")
                    .append(entityInfoSub.getTable().getName())
                    .append(" AS ")
                    .append(entityInfoSub.getTable().getAliasName())
                    .append("\n");
            sb.append("ON")
                    .append(" ")
                    .append(entityInfoSub.getTable().getAliasName())
                    .append(".")
                    .append(entityInfoSub.getKeys().get(0).getName())
                    .append(" = ")
                    .append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append("\n");
        }

        sb.append("WHERE")
                .append("\n")
                .append(entityInfo.getTable().getAliasName())
                .append(".")
                .append(entityInfo.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getSelectJoinByIdsSql(EntityInfo entityInfo, int idCount) {

        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;

        List<EntityColumn> allColumnList = new EntityColumnExpander()
                .build(entityInfo);

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
                .append(entityInfo.getTable().getName())
                .append(" AS ")
                .append(entityInfo.getTable().getAliasName())
                .append("\n");

        List<EntityLink> allEntityLinkList = new EntityLinkExpander()
                .build(entityInfo);
        for (EntityLink entityLinkEntity: allEntityLinkList) {
            EntityInfo entityInfoSub = entityLinkEntity.getTemplate();
            EntityColumn entityColumn = entityLinkEntity.getColumn();
            sb.append("LEFT JOIN")
                    .append("\n")
                    .append(entityInfoSub.getTable().getName())
                    .append(" AS ")
                    .append(entityInfoSub.getTable().getAliasName())
                    .append("\n");
            sb.append("ON")
                    .append(" ")
                    .append(entityInfoSub.getTable().getAliasName())
                    .append(".")
                    .append(entityInfoSub.getKeys().get(0).getName())
                    .append(" = ")
                    .append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append("\n");
        }

        if(idCount > 0){
            sb.append("WHERE")
                    .append("\n")
                    .append(entityInfo.getTable().getAliasName())
                    .append(".")
                    .append(entityInfo.getKeys().get(0).getName())
                    .append("\n")
                    .append("IN (")
                    .append(StringUtil.fillJoin("?", idCount, ","))
                    .append(")")
                    .append("\n");
        }

        return sb.toString();
    }


    @Override
    public PlaceholderJSql getSelectJoinByArgsSql(EntityInfo entityInfo, Object... args) {
        if(args.length > entityInfo.getParams().size()){
            throw new UnsupportedOperationException(entityInfo.getClazz().getName() + " 实际参数数量大于标注的参数数量，无法生成SQL！");
        }

        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;

        List<EntityColumn> allColumnList = new EntityColumnExpander()
                .build(entityInfo);

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
                .append(entityInfo.getTable().getName())
                .append(" AS ")
                .append(entityInfo.getTable().getAliasName())
                .append("\n");

        List<EntityLink> allEntityLinkList = new EntityLinkExpander()
                .build(entityInfo);
        for (EntityLink entityLinkEntity: allEntityLinkList) {
            EntityInfo entityInfoSub = entityLinkEntity.getTemplate();
            EntityColumn entityColumn = entityLinkEntity.getColumn();
            sb.append("LEFT JOIN")
                    .append("\n")
                    .append(entityInfoSub.getTable().getName())
                    .append(" AS ")
                    .append(entityInfoSub.getTable().getAliasName())
                    .append("\n");
            sb.append("ON")
                    .append(" ")
                    .append(entityInfoSub.getTable().getAliasName())
                    .append(".")
                    .append(entityInfoSub.getKeys().get(0).getName())
                    .append(" = ")
                    .append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append("\n");
        }

        List<EntityParam> list = new EntityParamFilter()
                .withArgs(args)
                .build(entityInfo.getParams());

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
                        .append(param.getRelationship().getKeyword())
                        .append(" ");

                if(param.getRelationship().equals(Relationships.IN)){
                    sb.append("(")
                            .append(StringUtil.fillJoin("?",param.getArgsCount(),","))
                            .append(")")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub));
                }else if(param.getRelationship().equals(Relationships.BETWEEN)){
                    sb.append("? AND ?")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub).subList(0,2));
                }else{
                    sb.append("?")
                            .append("\n");
                    argList.add(param.getArg());
                }
                index++;
            }
        }

        List<EntityOrder> listOrder = new EntityOrderExpander()
                .build(entityInfo);

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

        return new PlaceholderJSql()
                .withSql(sb.toString())
                .withArgs(argList);
    }

    @Override
    public PlaceholderJSql getSelectJoinByTreeArgSql(EntityInfo entityInfo, EntityTemplateTreeArg entityTemplateTreeArg) {
        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;

        List<EntityColumn> allColumnList = new EntityColumnExpander()
                .build(entityInfo);

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
                .append(entityInfo.getTable().getName())
                .append(" AS ")
                .append(entityInfo.getTable().getAliasName())
                .append("\n");

        List<EntityLink> allEntityLinkList = new EntityLinkExpander()
                .build(entityInfo);
        for (EntityLink entityLinkEntity: allEntityLinkList) {
            EntityInfo entityInfoSub = entityLinkEntity.getTemplate();
            EntityColumn entityColumn = entityLinkEntity.getColumn();
            sb.append("LEFT JOIN")
                    .append("\n")
                    .append(entityInfoSub.getTable().getName())
                    .append(" AS ")
                    .append(entityInfoSub.getTable().getAliasName())
                    .append("\n");
            sb.append("ON")
                    .append(" ")
                    .append(entityInfoSub.getTable().getAliasName())
                    .append(".")
                    .append(entityInfoSub.getKeys().get(0).getName())
                    .append(" = ")
                    .append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append("\n");
        }

        List<EntityParam> list = new EntityParamExpander()
                .withTreeArg(entityTemplateTreeArg)
                .build(entityInfo);

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
                        .append(param.getRelationship().getKeyword())
                        .append(" ");

                if(param.getRelationship().equals(Relationships.IN)){
                    sb.append("(")
                            .append(StringUtil.fillJoin("?",param.getArgsCount(),","))
                            .append(")")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub));
                }else if(param.getRelationship().equals(Relationships.BETWEEN)){
                    sb.append("? AND ?")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub).subList(0,2));
                }else{
                    sb.append("?")
                            .append("\n");
                    argList.add(param.getArg());
                }
                index++;
            }
        }

        List<EntityOrder> listOrder = new EntityOrderExpander()
                .build(entityInfo);

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

        return new PlaceholderJSql()
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


    @Override
    public PlaceholderJSql getSelectArgsPageSql(EntityInfo entityInfo, Integer pageStart, Integer pageSize, String rowNumberName, Object... args) {
        Integer rowStart = (pageStart-1) * pageSize;
        Integer rowEnd = pageStart * pageSize;

        PlaceholderJSql argSql = getAddRowNumberWithTopSql(entityInfo,rowNumberName,rowEnd,args);

        argSql.setSql(getFilterRowNumberSql(argSql.getSql(),rowNumberName,rowStart));

        return argSql;
    }

    @Override
    public PlaceholderJSql getSelectArgsCountSql(EntityInfo entityInfo, Object... args) {
        if(args.length > entityInfo.getParams().size()){
            throw new UnsupportedOperationException(entityInfo.getClazz().getName() + " 实际参数数量大于标注的参数数量，无法生成SQL！");
        }

        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        sb.append("COUNT(*)")
                .append("\n");

        sb.append("FROM")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n");


        List<EntityParam> list = new EntityParamFilter()
                .withArgs(args)
                .build(entityInfo.getParams());

        int index = 0;
        if(list.size() > 0){
            sb.append("WHERE\n");
            index = 0;
            for (EntityParam param: list) {
                sb.append(CheckUtil.isStart(index) ? "" : "AND")
                        .append(" ")
                        .append(param.getColumn().getName())
                        .append(" ")
                        .append(param.getRelationship().getKeyword())
                        .append(" ");

                if(param.getRelationship().equals(Relationships.IN)){
                    sb.append("(")
                            .append(StringUtil.fillJoin("?",param.getArgsCount(),","))
                            .append(")")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub));
                }else if(param.getRelationship().equals(Relationships.BETWEEN)){
                    sb.append("? AND ?")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub).subList(0,2));
                }else{
                    sb.append("?")
                            .append("\n");
                    argList.add(param.getArg());
                }
                index++;
            }
        }

        return new PlaceholderJSql()
                .withSql(sb.toString())
                .withArgs(argList);
    }


    @Override
    public PlaceholderJSql getSelectJoinByTreeArgPageSql(EntityInfo entityInfo, Integer pageStart, Integer pageSize, String rowNumberName, EntityTemplateTreeArg entityTemplateTreeArg) {
        Integer rowStart = (pageStart-1) * pageSize;
        Integer rowEnd = pageStart * pageSize;

        PlaceholderJSql argSql = getAddRowNumberWithTopSql(entityInfo,rowNumberName,rowEnd,entityTemplateTreeArg);

        argSql.setSql(getFilterRowNumberSql(argSql.getSql(),rowNumberName,rowStart));

        return argSql;
    }

    @Override
    public PlaceholderJSql getSelectJoinByTreeArgCountSql(EntityInfo entityInfo, EntityTemplateTreeArg entityTemplateTreeArg) {
        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        sb.append("COUNT(*)")
                .append("\n");

        sb.append("FROM")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append(" AS ")
                .append(entityInfo.getTable().getAliasName())
                .append("\n");

        List<EntityLink> allEntityLinkList = new EntityLinkExpander()
                .build(entityInfo);
        for (EntityLink entityLinkEntity: allEntityLinkList) {
            EntityInfo entityInfoSub = entityLinkEntity.getTemplate();
            EntityColumn entityColumn = entityLinkEntity.getColumn();
            sb.append("LEFT JOIN")
                    .append("\n")
                    .append(entityInfoSub.getTable().getName())
                    .append(" AS ")
                    .append(entityInfoSub.getTable().getAliasName())
                    .append("\n");
            sb.append("ON")
                    .append(" ")
                    .append(entityInfoSub.getTable().getAliasName())
                    .append(".")
                    .append(entityInfoSub.getKeys().get(0).getName())
                    .append(" = ")
                    .append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append("\n");
        }

        List<EntityParam> list = new EntityParamExpander()
                .withTreeArg(entityTemplateTreeArg)
                .build(entityInfo);

        if(list.size() > 0){
            sb.append("WHERE\n");
            int index = 0;
            for (EntityParam param: list) {
                sb.append(CheckUtil.isStart(index) ? "" : "AND")
                        .append(" ")
                        .append(param.getColumn().getTable().getAliasName())
                        .append(".")
                        .append(param.getColumn().getName())
                        .append(" ")
                        .append(param.getRelationship().getKeyword())
                        .append(" ");

                if(param.getRelationship().equals(Relationships.IN)){
                    sb.append("(")
                            .append(StringUtil.fillJoin("?",param.getArgsCount(),","))
                            .append(")")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub));
                }else if(param.getRelationship().equals(Relationships.BETWEEN)){
                    sb.append("? AND ?")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub).subList(0,2));
                }else{
                    sb.append("?")
                            .append("\n");
                    argList.add(param.getArg());
                }
                index++;
            }
        }

        return new PlaceholderJSql()
                .withSql(sb.toString())
                .withArgs(argList);
    }


    /**
     * Add Row Number with top filter
     * @param entityInfo Entity Template
     * @param rowNumberName Row Number Name
     * @param rowNumberTop Row Number End
     * @param args Arg Array
     * @return ArgSql
     */
    public PlaceholderJSql getAddRowNumberWithTopSql(EntityInfo entityInfo, String rowNumberName, Integer rowNumberTop, Object... args){
        if(args.length > entityInfo.getParams().size()){
            throw new UnsupportedOperationException(entityInfo.getClazz().getName() + " 实际参数数量大于标注的参数数量，无法生成SQL！");
        }

        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT");
        if(rowNumberTop > 0){
            sb.append(" TOP ")
                    .append(rowNumberTop);
        }
        sb.append("\n");

        sb.append("ROW_NUMBER() OVER (\n");
        if(entityInfo.getOrders().size() > 0){
            sb.append("ORDER BY\n");
            int index = 0;
            for (EntityOrder order: entityInfo.getOrders()) {
                sb.append(CheckUtil.isStart(index) ? "" : ",")
                        .append(" ")
                        .append(order.getColumn().getName())
                        .append(" ")
                        .append(order.isAsc() ? "ASC" : "DESC")
                        .append("\n");
                index++;
            }
        } else {
            sb.append("ORDER BY\n")
                    .append(entityInfo.getKeys().get(0).getName())
                    .append("\n");
        }
        sb.append(") AS ")
                .append(rowNumberName)
                .append("\n")
                .append(",");

        int index = 0;
        for (EntityColumn entityColumn: entityInfo.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityInfo.getTable().getName())
                .append("\n");


        List<EntityParam> list = new EntityParamFilter()
                .withArgs(args)
                .build(entityInfo.getParams());

        if(list.size() > 0){
            sb.append("WHERE\n");
            index = 0;
            for (EntityParam param: list) {
                sb.append(CheckUtil.isStart(index) ? "" : "AND")
                        .append(" ")
                        .append(param.getColumn().getName())
                        .append(" ")
                        .append(param.getRelationship().getKeyword())
                        .append(" ");

                if(param.getRelationship().equals(Relationships.IN)){
                    sb.append("(")
                            .append(StringUtil.fillJoin("?",param.getArgsCount(),","))
                            .append(")")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub));
                }else if(param.getRelationship().equals(Relationships.BETWEEN)){
                    sb.append("? AND ?")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub).subList(0,2));
                }else{
                    sb.append("?")
                            .append("\n");
                    argList.add(param.getArg());
                }
                index++;
            }
        }

        if(entityInfo.getOrders().size() > 0){
            sb.append("ORDER BY\n");
            index = 0;
            for (EntityOrder order: entityInfo.getOrders()) {
                sb.append(CheckUtil.isStart(index) ? "" : ",")
                        .append(" ")
                        .append(order.getColumn().getName())
                        .append(" ")
                        .append(order.isAsc() ? "ASC" : "DESC")
                        .append("\n");
                index++;
            }
        }

        return new PlaceholderJSql()
                .withSql(sb.toString())
                .withArgs(argList);
    }

    public PlaceholderJSql getAddRowNumberWithTopSql(EntityInfo entityInfo, String rowNumberName, Integer rowNumberTop, EntityTemplateTreeArg entityTemplateTreeArg){
        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT");
        if(rowNumberTop > 0){
            sb.append(" TOP ")
                    .append(rowNumberTop);
        }
        sb.append("\n");

        sb.append("ROW_NUMBER() OVER (\n");
        if(entityInfo.getOrders().size() > 0){
            sb.append("ORDER BY\n");
            int index = 0;
            for (EntityOrder order: entityInfo.getOrders()) {
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
        } else {
            sb.append("ORDER BY\n")
                    .append(entityInfo.getKeys().get(0).getName())
                    .append("\n");
        }
        sb.append(") AS ")
                .append(rowNumberName)
                .append("\n")
                .append(",");

        int index = 0;

        List<EntityColumn> allColumnList = new EntityColumnExpander()
                .build(entityInfo);

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
                .append(entityInfo.getTable().getName())
                .append(" AS ")
                .append(entityInfo.getTable().getAliasName())
                .append("\n");

        List<EntityLink> allEntityLinkList = new EntityLinkExpander()
                .build(entityInfo);
        for (EntityLink entityLinkEntity: allEntityLinkList) {
            EntityInfo entityInfoSub = entityLinkEntity.getTemplate();
            EntityColumn entityColumn = entityLinkEntity.getColumn();
            sb.append("LEFT JOIN")
                    .append("\n")
                    .append(entityInfoSub.getTable().getName())
                    .append(" AS ")
                    .append(entityInfoSub.getTable().getAliasName())
                    .append("\n");
            sb.append("ON")
                    .append(" ")
                    .append(entityInfoSub.getTable().getAliasName())
                    .append(".")
                    .append(entityInfoSub.getKeys().get(0).getName())
                    .append(" = ")
                    .append(entityColumn.getTable().getAliasName())
                    .append(".")
                    .append(entityColumn.getName())
                    .append("\n");
        }

        List<EntityParam> list = new EntityParamExpander()
                .withTreeArg(entityTemplateTreeArg)
                .build(entityInfo);

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
                        .append(param.getRelationship().getKeyword())
                        .append(" ");

                if(param.getRelationship().equals(Relationships.IN)){
                    sb.append("(")
                            .append(StringUtil.fillJoin("?",param.getArgsCount(),","))
                            .append(")")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub));
                }else if(param.getRelationship().equals(Relationships.BETWEEN)){
                    sb.append("? AND ?")
                            .append("\n");
                    Object[] argSub = param.getArgs();
                    argList.addAll(Arrays.asList(argSub).subList(0,2));
                }else{
                    sb.append("?")
                            .append("\n");
                    argList.add(param.getArg());
                }
                index++;
            }
        }

        List<EntityOrder> listOrder = new EntityOrderExpander()
                .build(entityInfo);

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

        return new PlaceholderJSql()
                .withSql(sb.toString())
                .withArgs(argList);
    }

    /**
     * Sub Select with Row Number filter
     * @param sql Sub Select
     * @param rowNumberName Row Number Name
     * @param rowNumberGreater Row Number Start
     * @return SQL
     */
    public String getFilterRowNumberSql(String sql, String rowNumberName, Integer rowNumberGreater){
        StringBuilder b = new StringBuilder()
                .append("SELECT\n")
                .append(" * \n")
                .append("FROM (\n")
                .append(sql)
                .append(") AS temp\n")
                .append("WHERE\n")
                .append(rowNumberName)
                .append(" > ")
                .append(rowNumberGreater.toString())
                .append("\n");

        return b.toString();
    }


    /**
     * Sub Select Add Row Number with top filter
     * @param selectSql Sub Select
     * @param rowNumberName Row Number Name
     * @param rowNumberTop Row Number End
     * @return
     */
    public String getAddRowNumberWithTopSql(String selectSql, String rowNumberName, Integer rowNumberTop) {
        StringBuilder sb = new StringBuilder()
                .append("SELECT");
        if(rowNumberTop > 0){
            sb.append(" TOP ")
                    .append(rowNumberTop);
        }
        sb.append("\n");

        //get ORDER BY
        String subSelectSql = selectSql;
        String orderSql = selectSql;
        int index = selectSql.indexOf("ORDER BY");
        if(index == -1){
            index = selectSql.indexOf("order by");
        }
        if(index == -1){
            throw new UnsupportedOperationException("cant find necessary 'ORDER BY' clause in :\n" + selectSql);
        }
        subSelectSql = selectSql.substring(0,index);
        orderSql = selectSql.substring(index);

        if(!CheckUtil.isNullOrEmpty(rowNumberName)){
            sb.append("ROW_NUMBER() OVER ( \n")
                    .append(orderSql)
                    .append("\n")
                    .append("\n) AS ")
                    .append(rowNumberName)
                    .append(",\n");
        }
        sb.append("*")
                .append("\n")
                .append("FROM")
                .append("\n")
                .append("(")
                    .append("\n")
                    .append(subSelectSql)
                    .append("\n")
                .append(") AS rowTo\n")
                .append(orderSql)
                .append("\n");
        return sb.toString();
    }

}
