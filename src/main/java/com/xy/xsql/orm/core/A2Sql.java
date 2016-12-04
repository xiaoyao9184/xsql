package com.xy.xsql.orm.core;

import com.xy.xsql.orm.build.entity.sql.BaseDialectEntitySqlBuilder;
import com.xy.xsql.orm.build.entity.sql.DialectMultiEntitySqlBuilder;
import com.xy.xsql.orm.data.entity.EntityLink;
import com.xy.xsql.orm.data.entity.EntityParam;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.data.param.EntitySiteParam;
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
public class A2Sql extends BaseDialectEntitySqlBuilder implements DialectMultiEntitySqlBuilder {


    public A2Sql(Class clazz) {

    }

    /**
     * 创建字段集合
     * @param entitySiteParams 实体参数
     * @return Column List
     */
    private List<Column> createSelectColumn(List<EntitySiteParam> entitySiteParams){
        List<Column> customColumnList = new ArrayList<>();
        customColumnList.addAll(this.data.getColumns());
        int index = 0;
        int count = 0;
        for (EntityLink entityLink : this.data.getLinks()) {
            if((index+1) > entitySiteParams.size() ||
                    CheckUtil.isNull(entitySiteParams.get(index)) ||
                    !entitySiteParams.get(index).isUseLink()){
                //切断实体关系
                //实体没有参数、实体参数为NULL、实体设置为隐藏
                //仅仅在手动设置时才会出现这种情况
                log.info("cut column[" + entityLink.getColumn().getName() + "] entity's columns");
            }else{
                EntityTemplate dataLink = entityLink.getTemplate();
                customColumnList.addAll(dataLink.getColumns());
            }
            count++;
            index++;
        }

        return customColumnList;
    }

    /**
     * 创建左连接表集合
     * @param entitySiteParams EntitySiteParam List
     * @return
     */
    private Map<Table,List<Param>> createLeftJoinParam(List<EntitySiteParam> entitySiteParams){
        Map<Table,List<Param>> customTableParamMap = new HashMap<>();
        int index = 0;
        int count = 0;
        for (EntityLink entityLink : this.data.getLinks()) {
            if((index+1) > entitySiteParams.size() ||
                    CheckUtil.isNull(entitySiteParams.get(index)) ||
                    !entitySiteParams.get(index).isUseLink()){
                //切断实体关系
                //实体没有参数、实体参数为NULL、实体设置为隐藏
                //仅仅在手动设置时才会出现这种情况
                log.info("cut column[" + entityLink.getColumn().getName() + "] entity's table");
            }else{
                EntityTemplate dataLink = entityLink.getTemplate();

                List<Param> onEqualParamList = new ArrayList<>();
                Param param = new Param()
                        .withAnd(true)
                        .withColumn(entityLink.getColumn())
                        .withRelationship(OperatorEnum.EQUAL)
                        .withValue(dataLink.getKeys().get(0));
                onEqualParamList.add(param);

                customTableParamMap.put(
                        dataLink.getTable(),
                        onEqualParamList);
            }
            count++;
            index++;
        }
        return customTableParamMap;
    }

    /**
     * 创建参数集合
     * @param entitySiteParams 实体参数
     * @return Param List
     */
    private List<Param> createWhereParam(List<EntitySiteParam> entitySiteParams){
        List<Param> customParamList = new ArrayList<>();
        int index = 0;
        int count = 0;
        for (EntityLink entityLink : this.data.getLinks()) {
            if((index+1) > entitySiteParams.size() ||
                    CheckUtil.isNull(entitySiteParams.get(index)) ||
                    !entitySiteParams.get(index).isUseLink()){
                //切断实体关系
                //实体没有参数、实体参数为NULL、实体设置为隐藏
                //仅仅在手动设置时才会出现这种情况
                log.info("cut column[" + entityLink.getColumn().getName() + "] entity's params");
            }else{
                EntitySiteParam entitySiteParam = entitySiteParams.get(index);

                if(entityLink.isCoreBean()){
                    if(this.data.getParams().size() < entitySiteParam.getArgs().length){
                        Object[] notUse = Arrays.copyOfRange(
                                entitySiteParam.getArgs(),
                                this.data.getParams().size()-1,
                                entitySiteParam.getArgs().length);
                        log.info("valueExpression entity's arguments are out of bounds, ignore " + Arrays.toString(notUse));
                    }
                    int argsIndex = 0;
                    for (EntityParam customEntityParam : this.data.getParams()) {
                        if(!customEntityParam.isNeedValue()){
                            customParamList.add(customEntityParam);
                        }else if((argsIndex+1) > entitySiteParam.getArgs().length ||
                                CheckUtil.isNull(entitySiteParam.getArgs()[argsIndex])){
                            //切断参数
                            //实际参数 未设置 忽略
                            log.info("cut valueExpression entity's column[" + customEntityParam.getColumn().getName() + "] argument, EntitySiteParamFiller is not set");
                            argsIndex++;
                        }else{
                            customParamList.add(customEntityParam);
                            argsIndex++;
                        }
                    }
                }else{
                    EntityTemplate dataLink = entityLink.getTemplate();
                    if(dataLink.getParams().size() < entitySiteParam.getArgs().length){
                        Object[] notUse = Arrays.copyOfRange(
                                entitySiteParam.getArgs(),
                                dataLink.getParams().size(),
                                entitySiteParam.getArgs().length);
                        log.info("column[" + entityLink.getColumn().getName() + "] entity's arguments are out of bounds, ignore " + Arrays.toString(notUse));
                    }
                    int argsIndex = 0;
                    for (EntityParam customEntityParam : dataLink.getParams()) {
                        if(!customEntityParam.isNeedValue()){
                            customParamList.add(customEntityParam);
                        }else if((argsIndex+1) > entitySiteParam.getArgs().length ||
                                CheckUtil.isNull(entitySiteParam.getArgs()[argsIndex])){
                            //切断参数
                            //实际参数 未设置 忽略
                            log.info("cut column[" + entityLink.getColumn().getName() + "] entity's column[" + customEntityParam.getColumn().getName() + "] argument, EntitySiteParamFiller is not set");
                            argsIndex++;
                        }else{
                            customParamList.add(customEntityParam);
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
     * @param entitySiteParams 实体参数
     * @return Order List
     */
    private List<Order> createOrder(List<EntitySiteParam> entitySiteParams){
        List<Order> customOrderList = new ArrayList<>();
        customOrderList.addAll(this.data.getOrders());
        int index = 0;
        int count = 0;
        for (EntityLink entityLink : this.data.getLinks()) {
            if((index+1) > entitySiteParams.size() ||
                    CheckUtil.isNull(entitySiteParams.get(index)) ||
                    !entitySiteParams.get(index).isUseLink()){
                //切断实体关系
                //实体没有参数、实体参数为NULL、实体设置为隐藏
                //仅仅在手动设置时才会出现这种情况
                log.info("cut column[" + entityLink.getColumn().getName() + "] entity's orders");
            }else{
                EntityTemplate dataLink = entityLink.getTemplate();
                customOrderList.addAll(dataLink.getOrders());
            }
            count++;
            index++;
        }
        return customOrderList;
    }











    /**
     * 多表条件查询
     * more table more row select with more table args
     * @param entitySiteParams 实体参数
     * @return SQL with (+)? , + is 1 or more , is keys count
     * @throws Exception
     */
    public String createFullSelectSql(List<EntitySiteParam> entitySiteParams) {
        if(entitySiteParams.size() > this.data.getLinks().size()){
            throw new UnsupportedOperationException("实际实体数量大于标注的实体数量，无法生成SQL！");
        }

        XSql xSql = new XSql()
                .select(createSelectColumn(entitySiteParams))
                .from(this.data.getTable().getName())
                .leftjoin(createLeftJoinParam(entitySiteParams))
                .where(createWhereParam(entitySiteParams))
                .orderBy(createOrder(entitySiteParams));

        return xSql.toSql();
    }

    /**
     * one table select count with more table args
     * 多表条件查询
     * @param entitySiteParams
     * @return 带有args.length个?的SQL
     * @throws Exception
     */
    public String createFullSelectCountSql(List<EntitySiteParam> entitySiteParams) {
        if(entitySiteParams.size() > this.data.getLinks().size()){
            throw new UnsupportedOperationException("实际实体数量大于标注的实体数量，无法生成SQL！");
        }
        for (EntitySiteParam entitySiteParam : entitySiteParams) {
//            entitySiteParam.setOnlyUseForEverParam(true);
        }

        XSql xSql = new XSql()
                .select(createSelectColumn(entitySiteParams))
                .from(this.data.getTable().getName())
                .leftjoin(createLeftJoinParam(entitySiteParams))
                .where(createWhereParam(entitySiteParams));

        XSql sqlCount = new XSql()
                .select()
                .funCount()
                .from(xSql);
        return sqlCount.toSql();
    }

    /**
     * one table delete with more table args
     * 多表条件删除
     * @param entitySiteParams
     * @return
     * @throws Exception
     */
    public String createFullDeleteSql(List<EntitySiteParam> entitySiteParams) {
        if(entitySiteParams.size() > this.data.getLinks().size()){
            throw new UnsupportedOperationException("实际实体数量大于标注的实体数量，无法生成SQL！");
        }

        XSql xSql = new XSql()
                .delete(this.data.getTable().getAliasName())
                .from(this.data.getTable().getName())
                .leftjoin(createLeftJoinParam(entitySiteParams))
                .where(createWhereParam(entitySiteParams));

        return xSql.toSql();
    }

    /**
     * 填充为完整参数
     * @param entitySiteParamList
     * @param defaultEnable 默认是否开启关联表查询
     * @return
     */
    @Deprecated
    public List<EntitySiteParam> fillEntityParam(List<EntitySiteParam> entitySiteParamList, boolean defaultEnable){
        return ASqlUtil.createFullEntityParam(this.data.getLinks(), entitySiteParamList,defaultEnable);
    }

}
