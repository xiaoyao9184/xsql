package com.xy.xsql.spring.dialect.h2;

import com.xy.xsql.entity.api.annotation.Relationships;
import com.xy.xsql.entity.api.dialect.jpql.*;
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
public class H2TemplateRenderer
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
                .append("COUNT(TABLE_NAME)\n")
                .append("FROM INFORMATION_SCHEMA.TABLES\n")
                .append("WHERE TABLE_NAME=\n")
                .append("'")
                .append(entityTemplate.getTable().getName().toUpperCase())
                .append("'")
                .append("\n")
                .append("AND TABLE_CLASS='org.h2.mvstore.db.MVTable'")
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
    public PlaceholderJSql getDeleteByArgsSql(EntityTemplate entityTemplate, Object... args) {
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
    public PlaceholderJSql getSelectByArgsSql(EntityTemplate entityTemplate, Object... args){
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

        return new PlaceholderJSql()
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
    public PlaceholderJSql getSelectJoinByArgsSql(EntityTemplate entityTemplate, Object... args) {
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

        return new PlaceholderJSql()
                .withSql(sb.toString())
                .withArgs(argList);
    }

    @Override
    public PlaceholderJSql getSelectJoinByTreeArgSql(EntityTemplate entityTemplate, EntityTemplateTreeArg entityTemplateTreeArg) {
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
    public PlaceholderJSql getSelectArgsPageSql(EntityTemplate entityTemplate, Integer pageStart, Integer pageSize, String rowNumberName, Object... args) {
        Integer rowStart = (pageStart-1) * pageSize;
        Integer rowEnd = pageStart * pageSize;

        PlaceholderJSql argSql = getAddRowNumberWithTopSql(entityTemplate,rowNumberName,rowEnd,args);

        argSql.setSql(getFilterRowNumberSql(argSql.getSql(),rowNumberName,rowStart));

        return argSql;
    }

    @Override
    public PlaceholderJSql getSelectArgsCountSql(EntityTemplate entityTemplate, Object... args) {
        if(args.length > entityTemplate.getParams().size()){
            throw new UnsupportedOperationException(entityTemplate.getClazz().getName() + " 实际参数数量大于标注的参数数量，无法生成SQL！");
        }

        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        sb.append("COUNT(*)")
                .append("\n");

        sb.append("FROM")
                .append("\n")
                .append(entityTemplate.getTable().getName())
                .append("\n");


        List<EntityParam> list = new EntityParamFilter()
                .withArgs(args)
                .build(entityTemplate.getParams());

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
    public PlaceholderJSql getSelectJoinByTreeArgPageSql(EntityTemplate entityTemplate, Integer pageStart, Integer pageSize, String rowNumberName, EntityTemplateTreeArg entityTemplateTreeArg) {
        Integer rowStart = (pageStart-1) * pageSize;
        Integer rowEnd = pageStart * pageSize;

        PlaceholderJSql argSql = getAddRowNumberWithTopSql(entityTemplate,rowNumberName,rowEnd,entityTemplateTreeArg);

        argSql.setSql(getFilterRowNumberSql(argSql.getSql(),rowNumberName,rowStart));

        return argSql;
    }

    @Override
    public PlaceholderJSql getSelectJoinByTreeArgCountSql(EntityTemplate entityTemplate, EntityTemplateTreeArg entityTemplateTreeArg) {
        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        sb.append("COUNT(*)")
                .append("\n");

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
     * @param entityTemplate Entity Template
     * @param rowNumberName Row Number Name
     * @param rowNumberTop Row Number End
     * @param args Arg Array
     * @return ArgSql
     */
    public PlaceholderJSql getAddRowNumberWithTopSql(EntityTemplate entityTemplate, String rowNumberName, Integer rowNumberTop, Object... args){
        if(args.length > entityTemplate.getParams().size()){
            throw new UnsupportedOperationException(entityTemplate.getClazz().getName() + " 实际参数数量大于标注的参数数量，无法生成SQL！");
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
        if(entityTemplate.getOrders().size() > 0){
            sb.append("ORDER BY\n");
            int index = 0;
            for (EntityOrder order: entityTemplate.getOrders()) {
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
                    .append(entityTemplate.getKeys().get(0).getName())
                    .append("\n");
        }
        sb.append(") AS ")
                .append(rowNumberName)
                .append("\n")
                .append(",");

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

        return new PlaceholderJSql()
                .withSql(sb.toString())
                .withArgs(argList);
    }

    public PlaceholderJSql getAddRowNumberWithTopSql(EntityTemplate entityTemplate, String rowNumberName, Integer rowNumberTop, EntityTemplateTreeArg entityTemplateTreeArg){
        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT");
        if(rowNumberTop > 0){
            sb.append(" TOP ")
                    .append(rowNumberTop);
        }
        sb.append("\n");

        sb.append("ROW_NUMBER() OVER (\n");
        if(entityTemplate.getOrders().size() > 0){
            sb.append("ORDER BY\n");
            int index = 0;
            for (EntityOrder order: entityTemplate.getOrders()) {
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
                    .append(entityTemplate.getKeys().get(0).getName())
                    .append("\n");
        }
        sb.append(") AS ")
                .append(rowNumberName)
                .append("\n")
                .append(",");

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
