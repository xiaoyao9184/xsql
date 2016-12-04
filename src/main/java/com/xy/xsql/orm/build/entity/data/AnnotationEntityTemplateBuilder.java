package com.xy.xsql.orm.build.entity.data;

import com.xy.xsql.orm.annotation.*;
import com.xy.xsql.orm.build.BaseBuilder;
import com.xy.xsql.orm.data.entity.*;
import com.xy.xsql.orm.dialect.none.AllVarCharTypeMapper;
import com.xy.xsql.orm.mapping.type.TypeMapper;
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
 * build EntityTemplate by class with Annotation
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntityTemplateBuilder implements BaseBuilder<Class<?>,EntityTemplate>, Cloneable {

    private Logger log;

    //config
    private Class<?> annotationClass;

    private TypeMapper<Class<?>, String> typeMapper;
    private String separator;
    private String namePrefix;
    private String nameSuffix;
    private String aliasNamePrefix;
    private String aliasNameSuffix;
    private boolean supportMultipleKey;
    private boolean scanStatus;
    private boolean scanEntity;
    private boolean scanParam;
    private boolean scanOrder;

    //cache
    private List<Field> annotationClassFields;
    private EntityTable table;
    private List<EntityColumn> columns;
    private EntityTemplate data;

    /**
     *
     */
    public AnnotationEntityTemplateBuilder(){
        this.log = LoggerFactory.getLogger(this.getClass());

        this.supportMultipleKey = false;
        this.scanStatus = false;
        this.scanEntity = false;
        this.scanParam = false;
        this.scanOrder = false;
        this.separator = "_";
    }


    //Config
    /**
     * Config TypeMapper
     * @param typeMapper TypeMapper
     * @return This
     */
    public AnnotationEntityTemplateBuilder withTypeMapper(TypeMapper<Class<?>,String> typeMapper){
        this.typeMapper = typeMapper;
        return this;
    }

    /**
     * Config Prefix Suffix Separator
     * @param separator Prefix Suffix Separator
     * @return This
     */
    public AnnotationEntityTemplateBuilder withSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    /**
     * Config Name Prefix
     * @param namePrefix Name Prefix
     * @return This
     */
    public AnnotationEntityTemplateBuilder withNamePrefix(String namePrefix){
        this.namePrefix = namePrefix;
        return this;
    }

    /**
     * Config Name Suffix
     * @param nameSuffix Name Suffix
     * @return This
     */
    public AnnotationEntityTemplateBuilder withNameSuffix(String nameSuffix){
        this.nameSuffix = nameSuffix;
        return this;
    }

    /**
     * Config Alias Name Prefix
     * @param aliasNamePrefix Alias Name Prefix
     * @return This
     */
    public AnnotationEntityTemplateBuilder withAliasNamePrefix(String aliasNamePrefix){
        this.aliasNamePrefix = aliasNamePrefix;
        return this;
    }

//    /**
//     * Config Alias Name Suffix
//     * @param aliasNameSuffix Alias Name Suffix
//     * @return This
//     */
//    public AnnotationEntityTemplateBuilder withAliasNameSuffix(String aliasNameSuffix){
//        this.aliasNameSuffix = aliasNameSuffix;
//        return this;
//    }

    /**
     * Config true if you want turn on Multiple Key support
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuilder withSupportMultipleKey(Boolean yesNo){
        this.supportMultipleKey = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Status
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuilder withScanStatus(Boolean yesNo){
        this.scanStatus = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Entity
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuilder withScanEntity(Boolean yesNo){
        this.scanEntity = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Param
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuilder withScanParam(Boolean yesNo){
        this.scanParam = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Order
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuilder withScanOrder(Boolean yesNo){
        this.scanOrder = yesNo;
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
        if(this.typeMapper == null){
            this.typeMapper = new AllVarCharTypeMapper();
        }

        annotationClassFields = this.initField();
        table = this.initTable();
        columns = this.initColumn();

        this.data = new EntityTemplate()
                .withClass(annotationClass)
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
            this.data.setParams(this.initParam());
        }
        if(scanOrder){
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
            String name = StringUtil.join(this.separator,this.namePrefix,eTable.name(),this.nameSuffix);
            String aliasName = StringUtil.join(this.separator,this.aliasNamePrefix,eTable.aliasName(),this.aliasNameSuffix);
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

                String name = StringUtil.join(this.separator,this.namePrefix,eColumn.name(),this.nameSuffix);
                aliasName = StringUtil.join(this.separator,this.aliasNamePrefix,aliasName,this.aliasNameSuffix);
                String type = this.typeMapper.mapType(field.getType());
                Integer len = -1;
                if(this.typeMapper.isSupportLength(field.getType())){
                    len = eColumn.length();
                    if (len <= 0) {
                        len = this.typeMapper.defaultLength(field.getType());
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
                        .withAliasNamePrefix(entityColumn.getAliasName())
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
                .withNamePrefix(this.namePrefix)
                .withNameSuffix(this.nameSuffix)
                .withAliasNamePrefix(this.aliasNamePrefix)
//                .withAliasNameSuffix(this.aliasNameSuffix)
                .withSupportMultipleKey(this.supportMultipleKey)
                .withScanStatus(this.scanStatus)
                .withScanParam(this.scanParam)
                .withScanOrder(this.scanOrder)
                .withScanEntity(this.scanEntity);

    }
}
