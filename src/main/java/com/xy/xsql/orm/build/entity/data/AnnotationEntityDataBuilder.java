package com.xy.xsql.orm.build.entity.data;

import com.xy.xsql.orm.annotation.*;
import com.xy.xsql.orm.build.BaseBuilder;
import com.xy.xsql.orm.data.entity.*;
import com.xy.xsql.orm.util.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * AnnotationEntityDataBuilder
 * build SqlEntityData by class with Annotation
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntityDataBuilder implements BaseBuilder<Class<?>,SqlEntityData> {

    private Logger log;

    //config
    private Class<?> annotationClass;
    private String tablePrefix;
    private boolean supportMultipleKey;
    private boolean scanStatus;
    private boolean scanEntity;
    private boolean scanParam;
    private boolean scanOrder;

    //tar
    private SqlEntityData data;

    /**
     *
     */
    public AnnotationEntityDataBuilder(){
        this.log = LoggerFactory.getLogger(this.getClass());

        this.supportMultipleKey = false;
        this.scanStatus = false;
        this.scanEntity = false;
        this.scanParam = false;
        this.scanOrder = false;
    }


    //Config
    /**
     * Config SqlDialectBuilder
     * @param tablePrefix Table Prefix
     * @return This
     */
    public AnnotationEntityDataBuilder tablePrefix(String tablePrefix){
        this.tablePrefix = tablePrefix;
        return this;
    }

    /**
     * Config true if you want turn on Multiple Key support
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityDataBuilder supportMultipleKey(Boolean yesNo){
        this.supportMultipleKey = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Status
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityDataBuilder scanStatus(Boolean yesNo){
        this.scanStatus = yesNo;
        return this;
    }


    /**
     * Config true if you want scan Entity
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityDataBuilder scanEntity(Boolean yesNo){
        this.scanEntity = yesNo;
        return this;
    }


    /**
     * Config true if you want scan Param
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityDataBuilder scanParam(Boolean yesNo){
        this.scanParam = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Order
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityDataBuilder scanOrder(Boolean yesNo){
        this.scanOrder = yesNo;
        return this;
    }



    @Override
    public SqlEntityData build(Class<?> aClass) {
        this.annotationClass = aClass;
        this.initData();

        return data;
    }


    //Init
    /**
     * 初始化
     */
    private void initData() {
        this.log.info("init elementsSentence form class " + this.annotationClass);
        this.data = new SqlEntityData();
        this.data.setTableName(this.initTable());
        this.data.setTableField(this.initField());
        this.data.setTableColumn(this.initColumn(this.data.getTableName()));

        this.data.setTableKey(this.initColumnKey(this.data.getTableName(), this.data.getTableColumn()));
        if(scanStatus){
            this.data.setTableStatus(this.initColumnStatus(this.data.getTableName(), this.data.getTableColumn()));
        }
        if(scanEntity){
            this.data.setTableEntity(this.initBind(this.data.getTableName(), this.data.getTableColumn()));
        }
        if(scanParam){
            this.data.setTableParam(this.initColumnParam(this.data.getTableName(), this.data.getTableColumn()));
        }
        if(scanOrder){
            this.data.setTableOrder(this.initColumnOrder(this.data.getTableName(), this.data.getTableColumn()));
        }

        this.log.info("init elementsSentence done.");
    }

    /**
     * 初始化表：名称
     * @see EntityTable
     * @return 表名 对象（不允许空表名）
     */
    private SqlTable initTable() {
        Annotation[] annotations = annotationClass.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof EntityTable) {
                EntityTable entityTable = (EntityTable) annotation;
                return new SqlTable(entityTable, this.tablePrefix);
            }
        }
        throw new UnsupportedOperationException(annotationClass.getName() + " 未使用@" + EntityTable.class.getSimpleName() + "标注或未设置标注属性：name！");
    }

    /**
     * 初始化字段
     * @see EntityColumn
     * @return 字段 集合
     */
    private List<Field> initField() {
        List<Field> list = new ArrayList<>();

        Field[] fields = annotationClass.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof EntityColumn) {
                    list.add(field);
                }
            }
        }
        if(CheckUtil.isNullOrEmpty(list)){
            throw new UnsupportedOperationException(annotationClass.getName() + " 未有任何字段使用@" + EntityColumn.class.getSimpleName() + "标注！");
        }
        return list;
    }

    /**
     * 初始化字段：数据库字段
     * @see EntityColumn
     */
    private List<SqlColumn> initColumn(SqlTable sqlTable){
        List<SqlColumn> list = new ArrayList<>();

        Field[] fields = annotationClass.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof EntityColumn) {
                    EntityColumn entityColumn = (EntityColumn)annotation;
                    list.add(new SqlColumn(entityColumn,field,sqlTable));
                }
            }
        }
        if(CheckUtil.isNullOrEmpty(list)){
            throw new UnsupportedOperationException(annotationClass.getName() + " 未有任何字段使用@" + EntityColumn.class.getSimpleName() + "标注！");
        }
        return list;
    }

    /**
     * 初始化字段：假删除
     * @see EntityColumnStatus
     * @return 注解
     */
    private SqlStatus initColumnStatus(SqlTable sqlTable, List<SqlColumn> sqlColumnList) {
        SqlStatus sqlStatus;
        for (SqlColumn sqlColumn: sqlColumnList) {
            Annotation[] annotations = sqlColumn.getField().getAnnotations();
            for (Annotation annotation: annotations) {
                if(annotation instanceof EntityColumnStatus) {
                    EntityColumnStatus entityColumnStatus = (EntityColumnStatus)annotation;
                    sqlStatus = new SqlStatus(
                            entityColumnStatus,
                            sqlColumn.getEntityColumn(),
                            sqlColumn.getField(),
                            sqlTable);
                    return sqlStatus;
                }
            }
        }
        return null;
    }

    /**
     * 初始化字段：主键
     * @see EntityColumnKey
     * @return 键 集合
     */
    private List<SqlKey> initColumnKey(SqlTable sqlTable, List<SqlColumn> sqlColumnList) {
        List<SqlKey> list = new ArrayList<>();
        for (SqlColumn sqlColumn: sqlColumnList) {
            Annotation[] annotations = sqlColumn.getField().getAnnotations();
            for (Annotation annotation: annotations) {
                if (annotation instanceof EntityColumnKey) {
                    EntityColumnKey entityColumnKey = (EntityColumnKey)annotation;
                    list.add(new SqlKey(
                            entityColumnKey,
                            sqlColumn.getEntityColumn(),
                            sqlColumn.getField(),
                            sqlTable));

                    if(!supportMultipleKey){
                        return list;
                    }
                }
            }
        }
        if(list.size() == 0){
            throw new UnsupportedOperationException("实体没有主键");
        }
        return list;
    }

    /**
     * 初始化字段：相关实体
     * @see EntityBind
     * @return 相关实体 集合
     */
    private List<SqlEntity> initBind(SqlTable sqlTable, List<SqlColumn> sqlColumnList) {
        List<SqlEntity> list = new ArrayList<>();
        list.add(new SqlEntity(this.annotationClass));
        for (SqlColumn sqlColumn: sqlColumnList) {
            Annotation[] annotations = sqlColumn.getField().getAnnotations();
            for (Annotation annotation: annotations) {
                if(annotation instanceof EntityBind) {
                    EntityBind entityBind = (EntityBind)annotation;
                    SqlEntity sqlEntity = new SqlEntity(
                            entityBind,
                            sqlColumn.getEntityColumn(),
                            sqlColumn.getField(),
                            sqlTable);
                    list.add(sqlEntity);
                }
            }
        }
        return list;
    }

    /**
     * 初始化字段：参数
     * @see EntityColumnParam
     * @return 查询参数 集合
     */
    private List<SqlParam> initColumnParam(SqlTable sqlTable, List<SqlColumn> sqlColumnList){
        List<SqlParam> list = new ArrayList<>();
        for (SqlColumn sqlColumn: sqlColumnList) {
            Annotation[] annotations = sqlColumn.getField().getAnnotations();
            for (Annotation annotation: annotations) {
                if(annotation instanceof EntityColumnParam) {
                    EntityColumnParam entityColumnParam = (EntityColumnParam)annotation;
                    SqlParam sqlParam = new SqlParam(
                            entityColumnParam,
                            sqlColumn.getEntityColumn(),
                            sqlColumn.getField(),
                            sqlTable);
                    list.add(sqlParam);
                }
            }
        }
        return list;
    }

    /**
     * 初始化字段：排序
     * @see EntityColumnOrder
     * @return 查询排序 集合
     */
    private List<SqlOrder> initColumnOrder(SqlTable sqlTable, List<SqlColumn> sqlColumnList){
        List<SqlOrder> list = new ArrayList<>();
        for (SqlColumn sqlColumn: sqlColumnList) {
            Annotation[] annotations = sqlColumn.getField().getAnnotations();
            for (Annotation annotation: annotations) {
                if(annotation instanceof EntityColumnOrder) {
                    EntityColumnOrder entityColumnOrder = (EntityColumnOrder)annotation;
                    SqlOrder sqlOrder = new SqlOrder(
                            entityColumnOrder,
                            sqlColumn.getEntityColumn(),
                            sqlColumn.getField(),
                            sqlTable);
                    list.add(sqlOrder);
                }
            }
        }
        return list;
    }

}
