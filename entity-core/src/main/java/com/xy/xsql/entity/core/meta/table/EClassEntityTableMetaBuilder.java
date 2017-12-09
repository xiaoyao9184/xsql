//package com.xy.xsql.entity.core.meta;
//
//import com.xy.xsql.entity.api.annotation.*;
//import com.xy.xsql.entity.api.dialect.type.TypeMapper;
//import com.xy.xsql.entity.api.meta.TableMetaBuilder;
//import com.xy.xsql.entity.core.config.AnnotationEntityTemplateConfig;
//import com.xy.xsql.entity.core.util.CheckUtil;
//import com.xy.xsql.entity.core.util.StringUtil;
//import com.xy.xsql.entity.model.lambda.ClassEntityTableMeta;
//import com.xy.xsql.entity.model.template.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * EAnnotationEntityInfoBuilder
// * core EntityInfo by class with Annotation
// * Created by xiaoyao9184 on 2016/10/15.
// */
//public class EClassEntityTableMetaBuilder
//        implements TableMetaBuilder<Class,ClassEntityTableMeta> {
//
//    private Logger log;
//
//    //config
//    private AnnotationEntityTemplateConfig<EClassEntityTableMetaBuilder> config;
//    //cache
//    private Class<?> annotationClass;
//    private List<Field> annotationClassFields;
//    private EntityTable table;
//    private List<EntityColumn> columns;
//    private EntityInfo data;
//
//    /**
//     *
//     */
//    public EClassEntityTableMetaBuilder(){
//        this.log = LoggerFactory.getLogger(this.getClass());
//    }
//
//
//    /**
//     * Go into config
//     * @return AnnotationEntityTemplateConfig
//     */
//    public AnnotationEntityTemplateConfig<EClassEntityTableMetaBuilder> configStart() {
//        if(this.config == null){
//            this.config = new AnnotationEntityTemplateConfig<>();
//        }
//        return this.config.in(this);
//    }
//
////    @Override
////    public EClassEntityTableMetaBuilder config(AnnotationEntityTemplateConfig config) {
////        this.config = config;
////        return this;
////    }
//
////    @Override
////    public EntityInfo build(Class<?> aClass) {
////        this.annotationClass = aClass;
////        this.initData();
////
////        return data;
////    }
//
//
//    //Init
//    /**
//     * 初始化
//     */
//    private void initData() {
//        this.log.info("init elementsSentence form class " + this.annotationClass);
//        if(this.config == null){
//            this.config = new AnnotationEntityTemplateConfig<>();
//        }
//        this.config.initDefault();
//        this.annotationClassFields = this.initField();
//        this.table = this.initTable();
//        this.columns = this.initColumn();
//
//        this.data = new EntityInfo()
//                .withClass(annotationClass)
//                .withTable(table)
//                .withColumns(columns)
//                .withKeys(this.initColumnKey());
//        if(this.config.isScanStatus()){
//            this.data.setStatus(this.initColumnStatus());
//        }
//        if(this.config.isScanLink()){
//            this.data.setLinks(this.initLink());
//        }
//        if(this.config.isScanParam()){
//            this.data.setParams(this.initParam());
//        }
//        if(this.config.isScanOrder()){
//            this.data.setOrders(this.initOrder());
//        }
//
//        this.log.info("init elementsSentence out.");
//    }
//
//
//    /**
//     * 初始化字段
//     * @see EColumn
//     * @return 字段 集合
//     */
//    private List<Field> initField() {
//        List<Field> list = new ArrayList<>();
//
//        Field[] fields = annotationClass.getDeclaredFields();
//        for (Field field : fields) {
//            if(!this.config.getTypeMapper().isSupport(field.getType())){
//                continue;
//            }
//            EColumn eColumn = field.getAnnotation(EColumn.class);
//            if(eColumn == null){
//                continue;
//            }
//            list.add(field);
//        }
//        if(CheckUtil.isNullOrEmpty(list)){
//            throw new UnsupportedOperationException(annotationClass.getName() + " 未有任何字段使用@" + EColumn.class.getSimpleName() + "标注！");
//        }
//        return list;
//    }
//
//
//
//    /**
//     * 初始化表：名称
//     * @see ETable
//     * @return 表名 对象（不允许空表名）
//     */
//    private EntityTable initTable() {
//        ETable eTable = annotationClass.getAnnotation(ETable.class);
//        if(eTable != null){
//            String name = StringUtil.join(this.config.getSeparator(),this.config.getNamePrefix(),eTable.name(),this.config.getNameSuffix());
//            String aliasName = StringUtil.join(this.config.getSeparator(),this.config.getAliasNamePrefix(),eTable.aliasName());
//            return new EntityTable()
//                    .withName(name)
//                    .withAliasName(aliasName);
//        }
//        throw new UnsupportedOperationException(annotationClass.getName() + " 未使用@" + ETable.class.getSimpleName() + "标注或未设置标注属性：name！");
//    }
//
//    /**
//     * 初始化字段：数据库字段
//     * @see EColumn
//     */
//    private List<EntityColumn> initColumn(){
//        List<EntityColumn> list = new ArrayList<>();
//
//        for (Field field : this.annotationClassFields) {
//            if(!this.config.getTypeMapper().isSupport(field.getType())){
//                continue;
//            }
//            EColumn eColumn = field.getAnnotation(EColumn.class);
//            if(eColumn == null){
//                continue;
//            }
//            String aliasName = eColumn.aliasName();
//            if(aliasName.isEmpty()){
//                aliasName = field.getName();
//            }
//
//            String name = StringUtil.join(this.config.getSeparator(),this.config.getNamePrefix(),eColumn.name(),this.config.getNameSuffix());
//            aliasName = StringUtil.join(this.config.getSeparator(),this.config.getAliasNamePrefix(),aliasName);
//            TypeMapper<Class<?>, String> mapper = this.config.getTypeMapper();
//            String type = mapper.mapType(field.getType());
//            Integer len = -1;
//            if(this.config.getTypeMapper().isSupportLength(field.getType())){
//                len = eColumn.length();
//                if (len <= 0) {
//                    len = this.config.getTypeMapper().defaultLength(field.getType());
//                }
//            }
//            EntityColumn column = new EntityColumn()
//                    .withName(name)
//                    .withAliasName(aliasName)
//                    .withType(type)
//                    .withLength(len)
//                    .withTable(this.table);
//            list.add(column);
//        }
//
//        if(CheckUtil.isNullOrEmpty(list)){
//            throw new UnsupportedOperationException(annotationClass.getName() + " 未有任何字段使用@" + EColumn.class.getSimpleName() + "标注！");
//        }
//        return list;
//    }
//
//    /**
//     * 初始化字段：假删除
//     * @see EStatus
//     * @return 注解
//     */
//    private EntityColumn initColumnStatus() {
//        int index = 0;
//        for (Field field : this.annotationClassFields) {
//            EStatus eStatus = field.getAnnotation(EStatus.class);
//            if(eStatus != null){
//                return this.columns.get(index);
//            }
//            index++;
//        }
//
//        throw new UnsupportedOperationException(annotationClass.getName() + " 未有任何字段使用@" + EStatus.class.getSimpleName() + "标注！");
//    }
//
//    /**
//     * 初始化字段：主键
//     * @see EKey
//     * @return 键 集合
//     */
//    private List<EntityColumn> initColumnKey() {
//        List<EntityColumn> list = new ArrayList<>();
//
//        int index = 0;
//        for (Field field : this.annotationClassFields) {
//            EKey eKey = field.getAnnotation(EKey.class);
//            if(eKey != null){
//                list.add(this.columns.get(index));
//            }
//            index++;
//        }
//
//        if(CheckUtil.isNullOrEmpty(list)){
//            throw new UnsupportedOperationException(annotationClass.getName() + " 未有任何字段使用@" + EKey.class.getSimpleName() + "标注！");
//        }
//        return list;
//    }
//
//    /**
//     * 初始化字段：相关实体
//     * @see ELink
//     * @return 相关实体 集合
//     */
//    private List<EntityLink> initLink() {
//        List<EntityLink> list = new ArrayList<>();
//
//        int index = 0;
//        for (Field field : this.annotationClassFields) {
//            ELink eLink = field.getAnnotation(ELink.class);
//            if(eLink != null){
//                EntityColumn entityColumn = this.columns.get(index);
////                EntityInfo entityInfo = this.clone()
////                        .configStart()
////                            .withAliasNamePrefix(entityColumn.getAliasName())
////                            .out()
////                        .build(eLink.value());
////
////                list.add(new EntityLink()
////                                .withColumn(entityColumn)
////                                .withTemplate(entityInfo));
//            }
//            index++;
//        }
//
//        if(CheckUtil.isNullOrEmpty(list)){
//            log.debug(annotationClass.getName() + " 未有任何字段使用@" + ELink.class.getSimpleName() + "标注！");
//        }
//        return list;
//    }
//
//    /**
//     * 初始化字段：参数
//     * @see EParam
//     * @return 查询参数 集合
//     */
//    private List<EntityParam> initParam(){
//        List<EntityParam> list = new ArrayList<>();
//
//        int index = 0;
//        for (Field field : this.annotationClassFields) {
//            EParam eParam = field.getAnnotation(EParam.class);
//            if(eParam != null){
//                EntityColumn entityColumn = this.columns.get(index);
//                Object[] args = null;
//                if(eParam.value()[0].length() != 0){
//                    args = eParam.value();
//                }
//
//                list.add(new EntityParam()
//                                .withColumn(entityColumn)
//                                .withRelationship(eParam.relationship())
//                                .withArgs(args));
//            }
//            index++;
//        }
//
//        if(CheckUtil.isNullOrEmpty(list)){
//            log.debug(annotationClass.getName() + " 未有任何字段使用@" + EParam.class.getSimpleName() + "标注！");
//        }
//        return list;
//    }
//
//    /**
//     * 初始化字段：排序
//     * @see EOrder
//     * @return 查询排序 集合
//     */
//    private List<EntityOrder> initOrder(){
//        List<EntityOrder> list = new ArrayList<>();
//
//        int index = 0;
//        for (Field field : this.annotationClassFields) {
//            EOrder eOrder = field.getAnnotation(EOrder.class);
//            if(eOrder != null){
//                EntityColumn entityColumn = this.columns.get(index);
//
//                list.add(new EntityOrder()
//                        .withColumn(entityColumn)
//                        .withAsc(eOrder.asc()));
//            }
//            index++;
//        }
//
//        if(CheckUtil.isNullOrEmpty(list)){
//            log.debug(annotationClass.getName() + " 未有任何字段使用@" + EOrder.class.getSimpleName() + "标注！");
//        }
//        return list;
//    }
//
//
//    @SuppressWarnings("CloneDoesntCallSuperClone")
//    @Override
//    public EClassEntityTableMetaBuilder clone(){
//        return new EClassEntityTableMetaBuilder()
//                .config(this.config.clone());
//    }
//
//    @Override
//    public ClassEntityTableMeta build(Class aClass) {
//        return null;
//    }
//}
