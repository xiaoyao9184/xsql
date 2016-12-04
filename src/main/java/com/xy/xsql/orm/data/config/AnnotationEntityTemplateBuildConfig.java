package com.xy.xsql.orm.data.config;

import com.xy.xsql.orm.build.Config;
import com.xy.xsql.orm.build.entity.template.AnnotationEntityTemplateBuilder;
import com.xy.xsql.orm.dialect.none.AllVarCharTypeMapper;
import com.xy.xsql.orm.mapping.type.TypeMapper;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntityTemplateBuildConfig implements
        Config<AnnotationEntityTemplateBuildConfig,AnnotationEntityTemplateBuilder>,
        Cloneable {

    protected TypeMapper<Class<?>, String> typeMapper;
    protected String separator;
    protected String namePrefix;
    protected String nameSuffix;
    protected String aliasNamePrefix;
//    protected String aliasNameSuffix;
    protected boolean supportMultipleKey;
    protected boolean scanStatus;
    protected boolean scanLink;
    protected boolean scanParam;
    protected boolean scanOrder;


    public AnnotationEntityTemplateBuildConfig(){
        this.supportMultipleKey = false;
        this.scanStatus = false;
        this.scanLink = false;
        this.scanParam = false;
        this.scanOrder = false;
        this.separator = "_";
    }

    public TypeMapper<Class<?>, String> getTypeMapper() {
        return typeMapper;
    }
    
    public String getSeparator() {
        return separator;
    }
    
    public String getNamePrefix() {
        return namePrefix;
    }
    
    public String getNameSuffix() {
        return nameSuffix;
    }
    
    public String getAliasNamePrefix() {
        return aliasNamePrefix;
    }
    
//    public String getAliasNameSuffix() {
//        return aliasNameSuffix;
//    }
    
    public boolean isSupportMultipleKey() {
        return supportMultipleKey;
    }

    public boolean isScanStatus() {
        return scanStatus;
    }
    
    public boolean isScanLink() {
        return scanLink;
    }
    
    public boolean isScanParam() {
        return scanParam;
    }
    
    public boolean isScanOrder() {
        return scanOrder;
    }



    /**
     * TypeMapper
     * @param typeMapper TypeMapper
     * @return This
     */
    public AnnotationEntityTemplateBuildConfig withTypeMapper(TypeMapper<Class<?>,String> typeMapper){
        this.typeMapper = typeMapper;
        return this;
    }

    /**
     * Config SqlDialectBuilder
     * @param separator Prefix Suffix Separator
     * @return This
     */
    public AnnotationEntityTemplateBuildConfig withSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    /**
     * Config SqlDialectBuilder
     * @param namePrefix Name Prefix
     * @return This
     */
    public AnnotationEntityTemplateBuildConfig withNamePrefix(String namePrefix){
        this.namePrefix = namePrefix;
        return this;
    }

    /**
     * Config SqlDialectBuilder
     * @param nameSuffix Alias Name Suffix
     * @return This
     */
    public AnnotationEntityTemplateBuildConfig withNameSuffix(String nameSuffix){
        this.nameSuffix = nameSuffix;
        return this;
    }

    /**
     * Config SqlDialectBuilder
     * @param aliasNamePrefix Alias Name Prefix
     * @return This
     */
    public AnnotationEntityTemplateBuildConfig withAliasNamePrefix(String aliasNamePrefix){
        this.aliasNamePrefix = aliasNamePrefix;
        return this;
    }

//    /**
//     * Config SqlDialectBuilder
//     * @param aliasNameSuffix Alias Name Suffix
//     * @return This
//     */
//    public AnnotationEntityTemplateBuildConfig withAliasNameSuffix(String aliasNameSuffix){
//        this.aliasNameSuffix = aliasNameSuffix;
//        return this;
//    }

    /**
     * Config true if you want turn on Multiple Key support
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuildConfig withSupportMultipleKey(Boolean yesNo){
        this.supportMultipleKey = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Status
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuildConfig withScanStatus(Boolean yesNo){
        this.scanStatus = yesNo;
        return this;
    }


    /**
     * Config true if you want scan Entity
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuildConfig withScanLink(Boolean yesNo){
        this.scanLink = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Param
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuildConfig withScanParam(Boolean yesNo){
        this.scanParam = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Order
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuildConfig withScanOrder(Boolean yesNo){
        this.scanOrder = yesNo;
        return this;
    }


    public void initDefault() {
        if(this.typeMapper == null){
            this.typeMapper = new AllVarCharTypeMapper();
        }
    }


    private AnnotationEntityTemplateBuilder builder;

    @Override
    public AnnotationEntityTemplateBuildConfig start(AnnotationEntityTemplateBuilder annotationEntityTemplateBuilder) {
        this.builder = annotationEntityTemplateBuilder;
        return this;
    }

    @Override
    public AnnotationEntityTemplateBuilder done() {
        return this.builder;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public AnnotationEntityTemplateBuildConfig clone(){
        return new AnnotationEntityTemplateBuildConfig()
                .withNamePrefix(this.namePrefix)
                .withNameSuffix(this.nameSuffix)
                .withAliasNamePrefix(this.aliasNamePrefix)
//                .withAliasNameSuffix(this.aliasNameSuffix)
                .withSupportMultipleKey(this.supportMultipleKey)
                .withScanStatus(this.scanStatus)
                .withScanParam(this.scanParam)
                .withScanOrder(this.scanOrder)
                .withScanLink(this.scanLink);
    }
}
