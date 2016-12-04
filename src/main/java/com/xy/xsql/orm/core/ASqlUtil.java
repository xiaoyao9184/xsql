package com.xy.xsql.orm.core;

import com.xy.xsql.orm.annotation.ETable;
import com.xy.xsql.orm.annotation.ESql;
import com.xy.xsql.orm.data.entity.EntityLink;
import com.xy.xsql.orm.data.param.EntitySiteParam;
import com.xy.xsql.orm.util.CheckUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ASql Util
 * Created by xiaoyao9184 on 2016/6/26.
 */
@SuppressWarnings("WeakerAccess")
public class ASqlUtil {

    /**
     * 根据 EntityLink 创建 EntitySiteParam
     * @param entityLinkList EntityLink List
     * @param entitySiteParamList EntitySiteParam List
     * @param defaultEnable Default Enable
     * @return EntitySiteParam List
     */
    @Deprecated
    public static List<EntitySiteParam> createFullEntityParam(List<EntityLink> entityLinkList, List<EntitySiteParam> entitySiteParamList, boolean defaultEnable){
        List<EntitySiteParam> result = new ArrayList<>();
        //类 当前数量
        Map<Class,AtomicInteger> entityClassNowIndexMap = new HashMap<>();
        //类 EntitySiteParam 映射
        Map<Class,List<EntitySiteParam>> entityClassMap = new HashMap<>();
        //字段名 EntitySiteParam 映射
        Map<String,EntitySiteParam> columnNameMap = new HashMap<>();

        //默认
        for (EntityLink entityLink : entityLinkList) {
            EntitySiteParam entitySiteParam = new EntitySiteParam();
            Class clazz = null; //entityLink.getEntityTemplateData().getClazz();
            entitySiteParam.setLinkEntityClass(clazz);
            String name = null;
            if(entityLink.isCoreBean()){
                entitySiteParam.setUseLink(true);
            }else{
                entitySiteParam.setUseLink(defaultEnable);
                name = entityLink.getColumn().getName();
            }
            //in ColumnMap
            entitySiteParam.setLinkColumnName(name);
            columnNameMap.put(name, entitySiteParam);
            //in ClassMap
            if(entityClassMap.containsKey(clazz)){
                entityClassMap.get(clazz).add(entitySiteParam);
            }else{
                List<EntitySiteParam> list = new ArrayList<>();
                list.add(entitySiteParam);
                entityClassMap.put(clazz,list);
            }
            entityClassNowIndexMap.put(clazz,new AtomicInteger());

            result.add(entitySiteParam);
        }

        //自定义修改
        for (EntitySiteParam entitySiteParam : entitySiteParamList) {
            if(entitySiteParam.isUseColumn()){
                if(columnNameMap.containsKey(entitySiteParam.getLinkColumnName())){
                    EntitySiteParam indexEntitySiteParam = columnNameMap.get(entitySiteParam.getLinkColumnName());
                    int index = result.indexOf(indexEntitySiteParam);
                    result.set(index, entitySiteParam);
                }else{
                    throw new UnsupportedOperationException("参数非法，未找到与 " + entitySiteParam.getLinkColumnName() + " 相关的实体，可能名称错误！");
                }
            }else if(entitySiteParam.isUseClass()){
                if(entityClassMap.containsKey(entitySiteParam.getLinkEntityClass())){
                    AtomicInteger classIndex = entityClassNowIndexMap.get(entitySiteParam.getLinkEntityClass());
                    EntitySiteParam indexEntitySiteParam = entityClassMap.get(entitySiteParam.getLinkEntityClass()).get(classIndex.get());
                    int index = result.indexOf(indexEntitySiteParam);
                    result.set(index, entitySiteParam);
                    classIndex.getAndIncrement();
                }else{
                    throw new UnsupportedOperationException("参数非法，未找到与 " + entitySiteParam.getLinkEntityClass() + " 相关的实体，可能名称错误！");
                }
            }else{
                //核心实体
                entitySiteParam.setUseLink(true);
                result.set(0, entitySiteParam);
            }
        }

        return result;
    }


    /**
     * 获取参数
     * @param param EntitySiteParam List
     * @param removeNull 是/否删除NULL参数
     * @return Object Array
     */
    public static Object[] getArgsByParam(List<EntitySiteParam> param, boolean removeNull) {
        List<Object> list = new ArrayList<>();
        for (EntitySiteParam kv: param) {
            if(kv.isUseArgs()){
                if(removeNull){
                    list.addAll(kv.toNoNullArgsList());
                }else{
                    list.addAll(kv.toArgsList());
                }
            }
        }
        return list.toArray();
    }

    /**
     * 通过类生成SQL语句
     * @param clazz
     * @param args
     * @return SQL
     */
    public static String createSqlByClass(Class clazz, Object...args) throws Exception {
        ESql eSql = (ESql) clazz.getAnnotation(ESql.class);
        if(eSql != null){
            return createSqlByVo(eSql,clazz,args);
        }

        ETable eTable = (ETable) clazz.getAnnotation(ETable.class);
        if(eTable != null){
            return createSqlByPo(eTable,clazz,args);
        }

        return null;
    }

    /**
     * 通过VO标注生成SQL语句
     * @param eSql 标注
     * @param clazz SQLVO
     * @param args NULL或空 不使用参数
     * @return SQL
     */
    public static String createSqlByVo(ESql eSql, Class clazz, Object...args) throws Exception {
        String sql;
        if (CheckUtil.isNullOrEmpty(eSql.sql())){
            ETable eTable = (ETable) clazz.getAnnotation(ETable.class);
            if(eTable != null){
                sql = "SELECT * FROM " + eTable.name();
            }else{
                throw new UnsupportedOperationException(clazz.getName() + " 没有表名，无法生成SQL！");
            }
        }
        sql = eSql.sql();

        if(eSql.args().length == 0){
            // TODO: 2016/6/21 空参数，不代表没有参数
        }else if(eSql.args().length >= args.length){
            int index = 0;
            for (Object arg: args) {
                if(CheckUtil.isNull(arg)){
                }else if (arg instanceof String &&
                        CheckUtil.isNullOrEmpty((String)arg)) {
                }else{
                    sql = sql + eSql.args()[index];
                }
                index++;
            }
        }else{
            throw new UnsupportedOperationException(clazz.getName() + " 实际参数数量大于标注参数数量，无法生成SQL！");
        }

        return sql;
    }

    /**
     * 通过PO标注生成SQL语句
     * @param eTable
     * @param clazz
     * @param args
     * @return SQL
     */
    public static String createSqlByPo(ETable eTable, Class clazz, Object...args) throws Exception {
        A2Sql aSql = new A2Sql(clazz);
        List<EntitySiteParam> entitySiteParams = new ArrayList<>();
        entitySiteParams.add(new EntitySiteParam()
                .withArgs(args));
        return aSql.createFullSelectSql(entitySiteParams);
    }

    /**
     * 获取有效参数（NULL或空将被移除）
     * @param args 参数
     * @return 有效参数
     */
    public static Object[] createSqlUseArgs(Object... args){
        List<Object> list = new ArrayList<>();
        for (Object arg: args) {
            if(CheckUtil.isNull(arg)){
            }else if (arg instanceof String &&
                    CheckUtil.isNullOrEmpty((String)arg)) {
            }else{
                list.add(arg);
            }
        }
        return list.toArray();
    }

}
