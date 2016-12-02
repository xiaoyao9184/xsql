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
 * build EntityTemplateData by class with Annotation
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntityDataBuilder implements BaseBuilder<Class<?>,EntityTemplateData>, Cloneable {

    private Logger log;

    //config
    private Class<?> annotationClass;
    private List<Field> annotationClassFields;

    private EntityTable table;
    private List<EntityColumn> columns;

    private String tablePrefix;
    private String aliasNamePrefix;
    private boolean supportMultipleKey;
    private boolean scanStatus;
    private boolean scanEntity;
    private boolean scanParam;
    private boolean scanOrder;

    //tar
    private EntityTemplateData data;

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
     * Config SqlDialectBuilder
     * @param aliasNamePrefix Alias Name Prefix
     * @return This
     */
    public AnnotationEntityDataBuilder aliasNamePrefix(String aliasNamePrefix){
        this.aliasNamePrefix = aliasNamePrefix;
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
    public EntityTemplateData build(Class<?> aClass) {
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

        if(this.tablePrefix == null){
            this.tablePrefix = "";
        }

        if(this.aliasNamePrefix == null){
            this.aliasNamePrefix = "";
        }

        annotationClassFields = this.initField();
        table = this.initTable();
        columns = this.initColumn();

        this.data = new EntityTemplateData()
                .withTable(table)
                .withColumns(columns)
                .withKeys(this.initColumnKey());
        if(scanStatus){
            this.data.setStatus(this.initColumnStatus());
        }
        if(scanEntity){
            this.data.setLinks(this.initLink());
        }
        if(scanParam){
            this.data.setParams(this.initColumnParam());
        }
        if(scanOrder){
            this.data.setOrders(this.initColumnOrder());
        }

        this.log.info("init elementsSentence done.");
    }


    /**
     * 初始化字段
     * @see EColumn
     * @return 字段 集合
     */
    private List<Field> initField() {
        List<Field> list = new ArrayList<>();

        Field[] fields = annotationClass.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof EColumn) {
                    list.add(field);
                }
            }
        }
        if(CheckUtil.isNullOrEmpty(list)){
            throw new UnsupportedOperationException(annotationClass.getName() + " 未有任何字段使用@" + EColumn.class.getSimpleName() + "标注！");
        }
        return list;
    }



    /**
     * 初始化表：名称
     * @see ETable
     * @return 表名 对象（不允许空表名）
     */
    private EntityTable initTable() {
        ETable eTable = annotationClass.getAnnotation(ETable.class);
        if(eTable != null){
            return new EntityTable()
                    .withName(this.tablePrefix + eTable.name())
                    .withAliasName(this.aliasNamePrefix + eTable.aliasName());
        }
        throw new UnsupportedOperationException(annotationClass.getName() + " 未使用@" + ETable.class.getSimpleName() + "标注或未设置标注属性：name！");
    }

    /**
     * 初始化字段：数据库字段
     * @see EColumn
     */
    private List<EntityColumn> initColumn(){
        List<EntityColumn> list = new ArrayList<>();

        for (Field field : this.annotationClassFields) {
            EColumn eColumn = field.getAnnotation(EColumn.class);
            if(eColumn != null){
                String aliasName = eColumn.aliasName();
                if(aliasName.isEmpty()){
                    aliasName = field.getName();
                }

                list.add(new EntityColumn()
                            .withName(eColumn.name())
                            .withAliasName(this.aliasNamePrefix + aliasName)
                            .withTable(this.table));
            }
        }

        if(CheckUtil.isNullOrEmpty(list)){
            throw new UnsupportedOperationException(annotationClass.getName() + " 未有任何字段使用@" + EColumn.class.getSimpleName() + "标注！");
        }
        return list;
    }

    /**
     * 初始化字段：假删除
     * @see EStatus
     * @return 注解
     */
    private EntityColumn initColumnStatus() {
        int index = 0;
        for (Field field : this.annotationClassFields) {
            EStatus eStatus = field.getAnnotation(EStatus.class);
            if(eStatus != null){
                return this.columns.get(index);
            }
            index++;
        }

        throw new UnsupportedOperationException(annotationClass.getName() + " 未有任何字段使用@" + EStatus.class.getSimpleName() + "标注！");
    }

    /**
     * 初始化字段：主键
     * @see EKey
     * @return 键 集合
     */
    private List<EntityColumn> initColumnKey() {
        List<EntityColumn> list = new ArrayList<>();

        int index = 0;
        for (Field field : this.annotationClassFields) {
            EKey eKey = field.getAnnotation(EKey.class);
            if(eKey != null){
                list.add(this.columns.get(index));
            }
            index++;
        }

        if(CheckUtil.isNullOrEmpty(list)){
            throw new UnsupportedOperationException(annotationClass.getName() + " 未有任何字段使用@" + EKey.class.getSimpleName() + "标注！");
        }
        return list;
    }

    /**
     * 初始化字段：相关实体
     * @see ELink
     * @return 相关实体 集合
     */
    private List<EntityLink> initLink() {
        List<EntityLink> list = new ArrayList<>();

        int index = 0;
        for (Field field : this.annotationClassFields) {
            ELink eLink = field.getAnnotation(ELink.class);
            if(eLink != null){
                EntityColumn entityColumn = this.columns.get(index);
                EntityTemplateData entityTemplateData = this.clone()
                        .aliasNamePrefix(entityColumn.getAliasName())
                        .build(eLink.value());

                list.add(new EntityLink()
                                .withColumn(entityColumn)
                                .withTemplate(entityTemplateData));
            }
            index++;
        }

        if(CheckUtil.isNullOrEmpty(list)){
            log.debug(annotationClass.getName() + " 未有任何字段使用@" + ELink.class.getSimpleName() + "标注！");
        }
        return list;
    }

    /**
     * 初始化字段：参数
     * @see EParam
     * @return 查询参数 集合
     */
    private List<EntityParam> initColumnParam(){
        List<EntityParam> list = new ArrayList<>();

        int index = 0;
        for (Field field : this.annotationClassFields) {
            EParam eParam = field.getAnnotation(EParam.class);
            if(eParam != null){
                EntityColumn entityColumn = this.columns.get(index);

                list.add(new EntityParam()
                                .withColumn(entityColumn)
                                .withRelationship(eParam.relationship())
                                .withArgs(eParam.value()));
            }
        }

        if(CheckUtil.isNullOrEmpty(list)){
            log.debug(annotationClass.getName() + " 未有任何字段使用@" + EParam.class.getSimpleName() + "标注！");
        }
        return list;
    }

    /**
     * 初始化字段：排序
     * @see EOrder
     * @return 查询排序 集合
     */
    private List<EntityOrder> initColumnOrder(){
        List<EntityOrder> list = new ArrayList<>();

        int index = 0;
        for (Field field : this.annotationClassFields) {
            EOrder eOrder = field.getAnnotation(EOrder.class);
            if(eOrder != null){
                EntityColumn entityColumn = this.columns.get(index);

                list.add(new EntityOrder()
                        .withColumn(entityColumn)
                        .withAes(eOrder.aes()));
            }
        }

        if(CheckUtil.isNullOrEmpty(list)){
            log.debug(annotationClass.getName() + " 未有任何字段使用@" + EOrder.class.getSimpleName() + "标注！");
        }
        return list;
    }


    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public AnnotationEntityDataBuilder clone(){
        return new AnnotationEntityDataBuilder()
                .tablePrefix(this.tablePrefix)
                .aliasNamePrefix(this.aliasNamePrefix)
                .supportMultipleKey(this.supportMultipleKey)
                .scanStatus(this.scanStatus)
                .scanParam(this.scanParam)
                .scanOrder(this.scanOrder)
                .scanEntity(this.scanEntity);

    }
}
