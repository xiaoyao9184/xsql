package com.xy.xsql.orm.dialect.none;


import com.xy.xsql.orm.annotation.Relationships;
import com.xy.xsql.orm.build.entity.data.EntityParamFilter;
import com.xy.xsql.orm.build.entity.sql.agreement.*;
import com.xy.xsql.orm.data.entity.EntityColumn;
import com.xy.xsql.orm.data.entity.EntityParam;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.data.param.ArgSql;
import com.xy.xsql.orm.data.param.EntityTemplateTreeArg;
import com.xy.xsql.orm.util.CheckUtil;
import com.xy.xsql.orm.util.EntityUtil;
import com.xy.xsql.orm.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Base Sql
 * Created by xiaoyao9184 on 2016/11/24.
 */
@SuppressWarnings({"StringBufferReplaceableByString", "Duplicates"})
public class BaseEntitySql 
        implements 
        SqlEntityTableManage,
        SqlEntityCRUD,
        SqlEntityStatusUpdate,
        SqlEntitySearchArg,
        SqlEntityLinkSearch,
        SqlPage {

    @Override
    public String getCreateTableSql(EntityTemplate entityData) {
        StringBuilder sb = new StringBuilder()
                .append("CREATE TABLE")
                .append("\n")
                .append(entityData.getTable().getName())
                .append("\n")
                .append("(")
                .append("\n");

        int index = 0;
        for (EntityColumn entityColumn: entityData.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getName())
                    .append(" ")
                    .append(entityColumn.getType());
            if(entityColumn.getLength() != null &&
                    entityColumn.getLength() > 0){
                sb.append("(");
                sb.append(entityColumn.getLength().toString());
                sb.append(")");
            }
            sb.append("\n");
            index++;
        }

        sb.append(")")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getDropTableSql(EntityTemplate entityData) {
        return new StringBuilder()
                .append("DROP TABLE")
                .append("\n")
                .append(entityData.getTable().getName())
                .append("\n")
                .toString();
    }

    @Override
    public String getAlterTableSql(EntityTemplate entityDataOld, EntityTemplate entityDataNew) {
        //比较字段
        List<EntityColumn> DropList = EntityUtil.lostList(entityDataOld.getColumns(),entityDataNew.getColumns());
        List<EntityColumn> AddList = EntityUtil.lostList(entityDataNew.getColumns(),entityDataOld.getColumns());
        Map<EntityColumn,EntityColumn> AlterMap = EntityUtil.allHaveMap(entityDataOld.getColumns(),entityDataNew.getColumns());


//        ALTER TABLE test_sql
//        ADD column_0 int
//        ALTER TABLE test_sql
//        DROP COLUMN column_1
//        ALTER TABLE test_sql
//        ALTER COLUMN column_2 VARCHAR(100)


        StringBuilder sb = new StringBuilder();
        int index = 0;

        if(!CheckUtil.isNullOrEmpty(AddList)){
            sb.append("ALTER TABLE")
                    .append("\n")
                    .append(entityDataOld.getTable().getName())
                    .append("\n")
                    .append("ADD");
            for (EntityColumn column: AddList) {
                if(index != 0){
                    sb.append(",");
                }
                sb.append(" ")
                        .append(column.getName())
                        .append(" ")
                        .append(column.getType())
                        .append("\n");
                index++;
            }
            if(index != 0){
                sb.append("\n");
                index = 0;
            }
        }


        if(!CheckUtil.isNullOrEmpty(DropList)){
            sb.append("ALTER TABLE")
                    .append("\n")
                    .append(entityDataOld.getTable().getName())
                    .append("\n")
                    .append("DROP COLUMN");
            for (EntityColumn column: DropList) {
                if(index != 0){
                    sb.append(",");
                }
                sb.append(" ")
                        .append(column.getName())
                        .append(" ")
                        .append(column.getType())
                        .append("\n");
                index++;
            }
            if(index != 0){
                sb.append("\n");
            }
        }


        if(!CheckUtil.isNullOrEmpty(AlterMap.keySet())){
            for (EntityColumn column: AlterMap.keySet()) {
                sb.append("ALTER TABLE")
                        .append("\n")
                        .append(entityDataOld.getTable().getName())
                        .append("\n")
                        .append("ALTER COLUMN")
                        .append(" ")
                        .append(column.getName())
                        .append(" ")
                        .append(column.getType());
                if(column.getLength() != null){
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
    public String getTableCountSql(EntityTemplate entityData) {
        throw new RuntimeException("不支持‘检查表存在’语句");
    }



    @Override
    public String getInsertSql(EntityTemplate entityData) {
        StringBuilder sb = new StringBuilder()
                .append("INSERT INTO")
                .append("\n")
                .append(entityData.getTable().getName())
                .append("\n")
                .append("(")
                .append("\n");

        int index = 0;
        for (EntityColumn entityColumn: entityData.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getName());
            sb.append("\n");
            index++;
        }

        sb.append(")")
                .append("\n");

        sb.append("VALUES")
                .append("\n")
                .append("(")
                .append("\n")
                .append(StringUtil.fillJoin("?", entityData.getColumns().size(), ","))
                .append("\n")
                .append(")")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getInsertByEntityCountSql(EntityTemplate entityData, int entityCount){
        if(entityCount == 1){
            return getInsertSql(entityData);
        }

        StringBuilder sb = new StringBuilder()
                .append("INSERT INTO")
                .append("\n")
                .append(entityData.getTable().getName())
                .append("\n")
                .append("(")
                .append("\n");

        int index = 0;
        for (EntityColumn entityColumn: entityData.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getName());
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
                .append(StringUtil.fillJoin("?", entityData.getColumns().size(), ","))
                .append("\n")
                .append(")")
                .append("\n");

        sb.append(StringUtil.fillJoin(values.toString(),entityCount,"\n"));

        return sb.toString();
    }

    @Override
    public String getSelectByIdSql(EntityTemplate entityData){
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;
        for (EntityColumn entityColumn: entityData.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityData.getTable().getName())
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(entityData.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getSelectByIdsSql(EntityTemplate entityData, int idCount){
        if(idCount == 1){
            return getSelectByIdSql(entityData);
        }

        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;
        for (EntityColumn entityColumn: entityData.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityData.getTable().getName())
                .append("\n");

        if(idCount > 0){
            sb.append("WHERE")
                    .append("\n")
                    .append(entityData.getKeys().get(0).getName())
                    .append("\n")
                    .append("IN (")
                    .append(StringUtil.fillJoin("?", idCount, ","))
                    .append(")")
                    .append("\n");
        }

        return sb.toString();
    }

    @Override
    public String getUpdateByIdSql(EntityTemplate entityData) {
        StringBuilder sb = new StringBuilder()
                .append("UPDATE")
                .append("\n")
                .append(entityData.getTable().getName())
                .append("\n")
                .append("SET")
                .append("\n");

        int index = 0;
        for (EntityColumn entityColumn: entityData.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getName())
                .append(" = ?")
                .append("\n");
            index++;
        }

        sb.append("WHERE")
                .append("\n")
                .append(entityData.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getUpdateByIdsSql(EntityTemplate entityData, int idCount) {
        if(idCount == 1){
            return getUpdateByIdSql(entityData);
        }

        StringBuilder sb = new StringBuilder()
                .append("UPDATE")
                .append("\n")
                .append(entityData.getTable().getName())
                .append("\n")
                .append("SET")
                .append("\n");

        int index = 0;
        for (EntityColumn entityColumn: entityData.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getName())
                    .append(" = ?")
                    .append("\n");
            index++;
        }

        sb.append("WHERE")
                .append("\n")
                .append(entityData.getKeys().get(0).getName())
                .append("\n")
                .append("IN (")
                .append(StringUtil.fillJoin("?", idCount, ","))
                .append(")")
                .append("\n");

        return sb.toString();
    }

    @Override
    public String getDeleteByIdSql(EntityTemplate entityData) {
        return new StringBuilder()
                .append("DELETE FROM")
                .append("\n")
                .append(entityData.getTable().getName())
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(entityData.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n")
                .toString();
    }

    @Override
    public String getDeleteByIdsSql(EntityTemplate entityData, int idCount) {
        if(idCount == 1){
            return getDeleteByIdSql(entityData);
        }

        return new StringBuilder()
                .append("DELETE FROM")
                .append("\n")
                .append(entityData.getTable().getName())
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(entityData.getKeys().get(0).getName())
                .append("\n")
                .append("IN (")
                .append(StringUtil.fillJoin("?", idCount, ","))
                .append(")")
                .append("\n")
                .toString();
    }


    @Override
    public String getUpdateStatusByIdSql(EntityTemplate entityData){
        if(entityData.getStatus() == null){
            throw new UnsupportedOperationException("没有Status字段！");
        }
        return new StringBuilder()
                .append("UPDATE")
                .append("\n")
                .append(entityData.getTable().getName())
                .append("\n")
                .append("SET")
                .append("\n")
                .append(entityData.getStatus().getName())
                .append(" = ")
                .append(" ? ")
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(entityData.getKeys().get(0).getName())
                .append(" = ?")
                .append("\n")
                .toString();
    }

    @Override
    public String getUpdateStatusByIdsSql(EntityTemplate entityData, int idCount){
        if(idCount == 1){
            return getUpdateStatusByIdSql(entityData);
        }
        if(entityData.getStatus() == null){
            throw new UnsupportedOperationException("没有Status字段！");
        }

        StringBuilder sb = new StringBuilder()
                .append("UPDATE")
                .append("\n")
                .append(entityData.getTable().getName())
                .append("\n")
                .append("SET")
                .append("\n")
                .append(entityData.getStatus().getName())
                .append(" = ?")
                .append("\n");

        if(idCount > 0){
            sb.append("WHERE")
                    .append("\n")
                    .append(entityData.getKeys().get(0).getName())
                    .append("\n")
                    .append("IN (")
                    .append(StringUtil.fillJoin("?", idCount, ","))
                    .append(")")
                    .append("\n");
        }

        return sb.toString();
    }


    @Override
    public ArgSql getSelectByArgsSql(EntityTemplate entityData, Object... args){
        if(args.length > entityData.getParams().size()){
            throw new UnsupportedOperationException(entityData.getClazz().getName() + " 实际参数数量大于标注的参数数量，无法生成SQL！");
        }

        List<Object> argList = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n");

        int index = 0;
        for (EntityColumn entityColumn: entityData.getColumns()) {
            if(index != 0){
                sb.append(",");
            }
            sb.append(entityColumn.getName())
                    .append("\n");
            index++;
        }

        sb.append("FROM")
                .append("\n")
                .append(entityData.getTable().getName())
                .append("\n");


        List<EntityParam> list = new EntityParamFilter()
                .withArgs(args)
                .build(entityData.getParams());

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
                            .append(")");
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
    public ArgSql getSelectJoinByArgsSql(EntityTemplate entityData, Object... args) {
        throw new RuntimeException("不支持‘多表 参数查询’语句");
    }

    @Override
    public ArgSql getSelectJoinByTreeArgSql(EntityTemplate entityData, EntityTemplateTreeArg entityDataTreeArg) {
        throw new RuntimeException("不支持‘多表 参数查询’语句");
    }


    @Override
    public String getPageSql(String selectSql, Integer pageNo, Integer pageSize) {
        throw new RuntimeException("不支持‘分页查询’语句");
    }

    @Override
    public String getPageSqlRowNumberName() {
        return null;
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
