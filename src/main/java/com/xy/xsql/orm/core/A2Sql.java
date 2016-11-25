package com.xy.xsql.orm.core;

import com.xy.xsql.orm.data.cache.ASqlCache;
import com.xy.xsql.orm.data.config.ASqlConfig;
import com.xy.xsql.orm.data.entity.EntityColumn;
import com.xy.xsql.orm.data.entity.EntityLink;
import com.xy.xsql.orm.data.entity.EntityParam;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Order;
import com.xy.xsql.orm.data.sql.element.info.Param;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.util.CheckUtil;

import java.util.*;

/**
 * 标注SQL（支持多表）
 * Created by xiaoyao9184 on 2016/1/13.
 */
@SuppressWarnings({"StringBufferReplaceableByString", "JavaDoc", "unused", "StatementWithEmptyBody", "WeakerAccess"})
public class A2Sql extends ASql {

/*
 * Get SQL
 */

    /**
     *
     * @param clazz 实体类
     */
    public A2Sql(Class<?> clazz){
        super(clazz);
    }

    /**
     *
     * @param config 配置
     */
    public A2Sql(ASqlConfig config){
        super(config);
    }

    /**
     * 获取子级A2Sql
     * @param clazz 子级Class
     * @return A2Sql
     */
    private A2Sql getChildA2Sql(Class clazz){
        ASqlConfig cfg = config.clone(clazz);
        A2Sql a2Sql = null;
        if(config.isUseCache()){
            try {
                a2Sql = ASqlCache.create(cfg,A2Sql.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            a2Sql = new A2Sql(cfg);
        }

        assert a2Sql != null : "无法初始化ASql";
        return a2Sql;
    }

/*
 * Get SQL
 */

    /**
     * 创建字段集合
     * @param entityParams 实体参数
     * @return Column List
     */
    private List<Column> createSelectColumn(List<com.xy.xsql.orm.data.param.EntityParam> entityParams){
        List<Column> customColumnList = new ArrayList<>();
        int index = 0;
        int count = 0;
        for (EntityLink entityLink : this.tableEntity) {
            //实体有参数
            if(entityLink.isCoreBean()){
                customColumnList.addAll(this.tableColumn);
            }else{
                if((index+1) > entityParams.size() ||
                        CheckUtil.isNull(entityParams.get(index)) ||
                        !entityParams.get(index).isUse()){
                    //切断实体关系
                    //实体没有参数、实体参数为NULL、实体设置为隐藏
                    //仅仅在手动设置时才会出现这种情况
                    log.info("cut column[" + entityLink.getEntityColumn().getName() + "] entity's columns");
                }else{
                    A2Sql a2Sql = getChildA2Sql(entityLink.getClazz());
                    Table customTable = a2Sql.tableName.clone();
                    //指定'主实体字段名'为表别名
                    customTable.setAliasName(entityLink.getEntityColumn().getName());
                    for (EntityColumn customEntityColumn : a2Sql.tableColumn) {
                        Column customColumn = customEntityColumn.clone();
                        customColumn.setTable(customTable);
                        //指定'主实体字段名'+'等级连接字符串'+'字段名'为字段别名
                        customColumn.setAliasName(
                                customTable.getAliasName() +
                                this.config.getLevelString() +
                                customColumn.getName());
                        customColumnList.add(customColumn);
                    }
                }
            }
            count++;
            index++;
        }

        return customColumnList;
    }

    /**
     * 创建左连接表集合
     * @param entityParams EntityParam List
     * @return
     */
    private Map<Table,List<Param>> createLeftJoinParam(List<com.xy.xsql.orm.data.param.EntityParam> entityParams){
        Map<Table,List<Param>> customTableParamMap = new HashMap<>();
        int index = 0;
        int count = 0;
        for (EntityLink entityLink : this.tableEntity) {
            if(!entityLink.isCoreBean()){
                if((index+1) > entityParams.size() ||
                        CheckUtil.isNull(entityParams.get(index)) ||
                        !entityParams.get(index).isUse()){
                    //切断实体关系
                    //实体没有参数、实体参数为NULL、实体设置为隐藏
                    //仅仅在手动设置时才会出现这种情况
                    log.info("cut column[" + entityLink.getEntityColumn().getName() + "] entity's table");
                }else{
                    A2Sql a2Sql = getChildA2Sql(entityLink.getClazz());
                    //指定'主实体字段名'为表别名
                    Table table = a2Sql.tableName.clone();
                    table.setAliasName(entityLink.getEntityColumn().getName());
                    Column customColumn = a2Sql.tableKey.get(0).clone();
                    customColumn.setTable(table);

                    List<Param> customParamList = new ArrayList<>();
                    Param param = new Param();
                    param.setAnd(true);
                    param.setColumn(customColumn);
                    param.setRelationship(OperatorEnum.EQUAL);
                    param.setValue(entityLink.getEntityColumn());
                    customParamList.add(param);

                    customTableParamMap.put(table,customParamList);
                }
            }
            count++;
            index++;
        }
        return customTableParamMap;
    }

    /**
     * 创建参数集合
     * @param entityParams 实体参数
     * @return Param List
     */
    private List<Param> createWhereParam(List<com.xy.xsql.orm.data.param.EntityParam> entityParams){
        List<Param> customParamList = new ArrayList<>();
        int index = 0;
        int count = 0;
        for (EntityLink entityLink : this.tableEntity) {
            if((index+1) > entityParams.size() ||
                    CheckUtil.isNull(entityParams.get(index)) ||
                    !entityParams.get(index).isUse()){
                //切断实体关系
                //实体没有参数、实体参数为NULL、实体设置为隐藏
                //仅仅在手动设置时才会出现这种情况
                log.info("cut column[" + entityLink.getEntityColumn().getName() + "] entity's params");
            }else{
                com.xy.xsql.orm.data.param.EntityParam entityParam = entityParams.get(index);
                if(entityLink.isCoreBean()){
                    if(this.tableParam.size() < entityParam.getArgs().length){
                        Object[] notUse = Arrays.copyOfRange(
                                entityParam.getArgs(),
                                this.tableParam.size()-1,
                                entityParam.getArgs().length);
                        log.info("main entity's arguments are out of bounds, ignore " + Arrays.toString(notUse));
                    }
                    int argsIndex = 0;
                    for (EntityParam customEntityParam : this.tableParam) {
                        if(!customEntityParam.isNeedValue()){
                            customParamList.add(customEntityParam);
                        }else if((argsIndex+1) > entityParam.getArgs().length ||
                                CheckUtil.isNull(entityParam.getArgs()[argsIndex])){
                            //切断参数
                            //实际参数 未设置 忽略
                            log.info("cut main entity's column[" + customEntityParam.getColumn().getName() + "] argument, arg is not set");
                            argsIndex++;
                        }else{
                            customParamList.add(customEntityParam);
                            argsIndex++;
                        }
                    }
                }else{
                    A2Sql a2Sql = getChildA2Sql(entityLink.getClazz());
                    if(a2Sql.tableParam.size() < entityParam.getArgs().length){
                        Object[] notUse = Arrays.copyOfRange(
                                entityParam.getArgs(),
                                a2Sql.tableParam.size(),
                                entityParam.getArgs().length);
                        log.info("column[" + entityLink.getEntityColumn().getName() + "] entity's arguments are out of bounds, ignore " + Arrays.toString(notUse));
                    }
                    int argsIndex = 0;
                    for (EntityParam customEntityParam : a2Sql.tableParam) {
                        if(!customEntityParam.isNeedValue()){
                            Table customTable = a2Sql.tableName.clone();
                            //指定'主实体字段名'为表别名
                            customTable.setAliasName(entityLink.getEntityColumn().getName());
                            Param param = customEntityParam.clone();
                            param.getColumn().setTable(customTable);
                            customParamList.add(customEntityParam);
                        }else if((argsIndex+1) > entityParam.getArgs().length ||
                                CheckUtil.isNull(entityParam.getArgs()[argsIndex])){
                            //切断参数
                            //实际参数 未设置 忽略
                            log.info("cut column[" + entityLink.getEntityColumn().getName() + "] entity's column[" + customEntityParam.getColumn().getName() + "] argument, arg is not set");
                            argsIndex++;
                        }else{
                            Table customTable = a2Sql.tableName.clone();
                            //指定'主实体字段名'为表别名
                            customTable.setAliasName(entityLink.getEntityColumn().getName());
                            Param param = customEntityParam.clone();
                            param.getColumn().setTable(customTable);
                            customParamList.add(param);
                            argsIndex++;
                        }
                    }
                }
            }
            count++;
            index++;
        }
        return customParamList;
    }

    /**
     * 创建排序集合
     * @param entityParams 实体参数
     * @return Order List
     */
    private List<Order> createOrder(List<com.xy.xsql.orm.data.param.EntityParam> entityParams){
        List<Order> customOrderList = new ArrayList<>();
        int index = 0;
        int count = 0;
        for (EntityLink entityLink : this.tableEntity) {
            if((index+1) > entityParams.size() ||
                    CheckUtil.isNull(entityParams.get(index)) ||
                    !entityParams.get(index).isUse()){
                //切断实体关系
                //实体没有参数、实体参数为NULL、实体设置为隐藏
                //仅仅在手动设置时才会出现这种情况
                log.info("cut column[" + entityLink.getEntityColumn().getName() + "] entity's orders");
            }else{
                if(entityLink.isCoreBean()){
                    customOrderList.addAll(this.tableOrder);
                }else{
                    A2Sql a2Sql = getChildA2Sql(entityLink.getClazz());
                    customOrderList.addAll(a2Sql.tableOrder);
                }
            }
            count++;
            index++;
        }
        return customOrderList;
    }











    /**
     * 多表条件查询
     * more table more row select with more table args
     * @param entityParams 实体参数
     * @return SQL with (+)? , + is 1 or more , is keys count
     * @throws Exception
     */
    public String createFullSelectSql(List<com.xy.xsql.orm.data.param.EntityParam> entityParams) throws Exception {
        if(entityParams.size() > this.tableEntity.size()){
            throw new UnsupportedOperationException(clazz.getName() + " 实际实体数量大于标注的实体数量，无法生成SQL！");
        }

        XSql xSql = new XSql()
                .select(createSelectColumn(entityParams))
                .from(this.tableName)
                .leftjoin(createLeftJoinParam(entityParams))
                .where(createWhereParam(entityParams))
                .orderBy(createOrder(entityParams));

        return xSql.toSql();
    }

    /**
     * one table select count with more table args
     * 多表条件查询
     * @param entityParams
     * @return 带有args.length个?的SQL
     * @throws Exception
     */
    public String createFullSelectCountSql(List<com.xy.xsql.orm.data.param.EntityParam> entityParams) throws Exception {
        if(entityParams.size() > this.tableEntity.size()){
            throw new UnsupportedOperationException(clazz.getName() + " 实际实体数量大于标注的实体数量，无法生成SQL！");
        }
        for (com.xy.xsql.orm.data.param.EntityParam entityParam : entityParams) {
            entityParam.setOnlyUseForEverParam(true);
        }

        XSql xSql = new XSql()
                .select(createSelectColumn(entityParams))
                .from(this.tableName)
                .leftjoin(createLeftJoinParam(entityParams))
                .where(createWhereParam(entityParams));

        XSql sqlCount = new XSql()
                .select()
                .funCount()
                .from(xSql);
        return sqlCount.toSql();
    }

    /**
     * one table delete with more table args
     * 多表条件删除
     * @param entityParams
     * @return
     * @throws Exception
     */
    public String createFullDeleteSql(List<com.xy.xsql.orm.data.param.EntityParam> entityParams) throws Exception {
        if(entityParams.size() > this.tableEntity.size()){
            throw new UnsupportedOperationException(clazz.getName() + " 实际实体数量大于标注的实体数量，无法生成SQL！");
        }

        XSql xSql = new XSql()
                .delete(this.tableName.getAliasName())
                .from(this.tableName)
                .leftjoin(createLeftJoinParam(entityParams))
                .where(createWhereParam(entityParams));

        return xSql.toSql();
    }

    /**
     * 填充为完整参数
     * @param entityParamList
     * @param defaultEnable 默认是否开启关联表查询
     * @return
     */
    public List<com.xy.xsql.orm.data.param.EntityParam> fillEntityParam(List<com.xy.xsql.orm.data.param.EntityParam> entityParamList, boolean defaultEnable){
        return ASqlUtil.createFullEntityParam(this.tableEntity, entityParamList,defaultEnable);
    }

}
