package com.xy.xsql.orm.data.config;

import com.xy.xsql.orm.mapping.type.TypeMapper;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntityTemplateBuilderConfig {


    protected TypeMapper<Class<?>, String> typeMapper;
    protected String separator;
    protected String namePrefix;
    protected String nameSuffix;
    protected String aliasNamePrefix;
    protected String aliasNameSuffix;
    protected boolean supportMultipleKey;
    protected boolean scanStatus;
    protected boolean scanEntity;
    protected boolean scanParam;
    protected boolean scanOrder;

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
    
    public String getAliasNameSuffix() {
        return aliasNameSuffix;
    }
    
    public boolean isSupportMultipleKey() {
        return supportMultipleKey;
    }

    public boolean isScanStatus() {
        return scanStatus;
    }
    
    public boolean isScanEntity() {
        return scanEntity;
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
    public AnnotationEntityTemplateBuilderConfig withTypeMapper(TypeMapper<Class<?>,String> typeMapper){
        this.typeMapper = typeMapper;
        return this;
    }

    /**
     * Config SqlDialectBuilder
     * @param separator Prefix Suffix Separator
     * @return This
     */
    public AnnotationEntityTemplateBuilderConfig withSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    /**
     * Config SqlDialectBuilder
     * @param namePrefix Name Prefix
     * @return This
     */
    public AnnotationEntityTemplateBuilderConfig withNamePrefix(String namePrefix){
        this.namePrefix = namePrefix;
        return this;
    }

    /**
     * Config SqlDialectBuilder
     * @param nameSuffix Alias Name Suffix
     * @return This
     */
    public AnnotationEntityTemplateBuilderConfig withNameSuffix(String nameSuffix){
        this.nameSuffix = nameSuffix;
        return this;
    }

    /**
     * Config SqlDialectBuilder
     * @param aliasNamePrefix Alias Name Prefix
     * @return This
     */
    public AnnotationEntityTemplateBuilderConfig withAliasNamePrefix(String aliasNamePrefix){
        this.aliasNamePrefix = aliasNamePrefix;
        return this;
    }

    /**
     * Config SqlDialectBuilder
     * @param aliasNameSuffix Alias Name Suffix
     * @return This
     */
    public AnnotationEntityTemplateBuilderConfig withAliasNameSuffix(String aliasNameSuffix){
        this.aliasNameSuffix = aliasNameSuffix;
        return this;
    }


    /**
     * Config true if you want turn on Multiple Key support
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuilderConfig withSupportMultipleKey(Boolean yesNo){
        this.supportMultipleKey = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Status
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuilderConfig withScanStatus(Boolean yesNo){
        this.scanStatus = yesNo;
        return this;
    }


    /**
     * Config true if you want scan Entity
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuilderConfig withScanEntity(Boolean yesNo){
        this.scanEntity = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Param
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuilderConfig withScanParam(Boolean yesNo){
        this.scanParam = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Order
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateBuilderConfig withScanOrder(Boolean yesNo){
        this.scanOrder = yesNo;
        return this;
    }


}
