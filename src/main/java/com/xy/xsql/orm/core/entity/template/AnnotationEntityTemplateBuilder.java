package com.xy.xsql.orm.core.entity.template;

import com.xy.xsql.orm.annotation.*;
import com.xy.xsql.orm.core.ConfigBuilder;
import com.xy.xsql.orm.data.config.AnnotationEntityTemplateBuildConfig;
import com.xy.xsql.orm.data.entity.*;
import com.xy.xsql.orm.util.CheckUtil;
import com.xy.xsql.orm.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * AnnotationEntityTemplateBuilder
 * core EntityTemplate by class with Annotation
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntityTemplateBuilder implements
        ConfigBuilder<
                AnnotationEntityTemplateBuilder,
                AnnotationEntityTemplateBuildConfig,
                Class<?>,EntityTemplate>, 
        Cloneable {

    private Logger log;

    //config
    private AnnotationEntityTemplateBuildConfig config;
    //cache
    private Class<?> annotationClass;
    private List<Field> annotationClassFields;
    private EntityTable table;
    private List<EntityColumn> columns;
    private EntityTemplate data;

    /**
     *
     */
    public AnnotationEntityTemplateBuilder(){
        this.log = LoggerFactory.getLogger(this.getClass());
    }


    /**
     * Go into config
     * @return AnnotationEntityTemplateBuildConfig
     */
    public AnnotationEntityTemplateBuildConfig configStart() {
        return this.config.start(this);
    }
    
    @Override
    public AnnotationEntityTemplateBuilder config(AnnotationEntityTemplateBuildConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public EntityTemplate build(Class<?> aClass) {
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
        this.config.initDefault();
        this.annotationClassFields = this.initField();
        this.table = this.initTable();
        this.columns = this.initColumn();

        this.data = new EntityTemplate()
                .withClass(annotationClass)
                .withTable(table)
                .withColumns(columns)
                .withKeys(this.initColumnKey());
        if(this.config.isScanStatus()){
            this.data.setStatus(this.initColumnStatus());
        }
        if(this.config.isScanLink()){
            this.data.setLinks(this.initLink());
        }
        if(this.config.isScanParam()){
            this.data.setParams(this.initParam());
        }
        if(this.config.isScanOrder()){
            this.data.setOrders(this.initOrder());
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
            String name = StringUtil.join(this.config.getSeparator(),this.config.getNamePrefix(),eTable.name(),this.config.getNameSuffix());
            String aliasName = StringUtil.join(this.config.getSeparator(),this.config.getAliasNamePrefix(),eTable.aliasName());
            return new EntityTable()
                    .withName(name)
                    .withAliasName(aliasName);
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

                String name = StringUtil.join(this.config.getSeparator(),this.config.getNamePrefix(),eColumn.name(),this.config.getNameSuffix());
                aliasName = StringUtil.join(this.config.getSeparator(),this.config.getAliasNamePrefix(),aliasName);
                String type = this.config.getTypeMapper().mapType(field.getType());
                Integer len = -1;
                if(this.config.getTypeMapper().isSupportLength(field.getType())){
                    len = eColumn.length();
                    if (len <= 0) {
                        len = this.config.getTypeMapper().defaultLength(field.getType());
                    }
                }
                EntityColumn column = new EntityColumn()
                        .withName(name)
                        .withAliasName(aliasName)
                        .withType(type)
                        .withLength(len)
                        .withTable(this.table);
                list.add(column);
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
                EntityTemplate entityTemplate = this.clone()
                        .configStart()
                            .withAliasNamePrefix(entityColumn.getAliasName())
                            .done()
                        .build(eLink.value());

                list.add(new EntityLink()
                                .withColumn(entityColumn)
                                .withTemplate(entityTemplate));
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
    private List<EntityParam> initParam(){
        List<EntityParam> list = new ArrayList<>();

        int index = 0;
        for (Field field : this.annotationClassFields) {
            EParam eParam = field.getAnnotation(EParam.class);
            if(eParam != null){
                EntityColumn entityColumn = this.columns.get(index);

                list.add(new EntityParam()
                                .withColumn(entityColumn)
                                .withRelationship(eParam.relationship())
                                .withArgs((Object[])eParam.value()));
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
    private List<EntityOrder> initOrder(){
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
            index++;
        }

        if(CheckUtil.isNullOrEmpty(list)){
            log.debug(annotationClass.getName() + " 未有任何字段使用@" + EOrder.class.getSimpleName() + "标注！");
        }
        return list;
    }


    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public AnnotationEntityTemplateBuilder clone(){
        return new AnnotationEntityTemplateBuilder()
                .config(this.config.clone());
    }

}
