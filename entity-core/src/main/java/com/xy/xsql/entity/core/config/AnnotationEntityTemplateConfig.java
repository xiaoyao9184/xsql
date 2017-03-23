package com.xy.xsql.entity.core.config;

import com.xy.xsql.core.holder.ParentHolder;
import com.xy.xsql.entity.api.dialect.TypeMapper;
import com.xy.xsql.entity.dialect.none.AllVarcharTypeMapper;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntityTemplateConfig<Father> implements
        ParentHolder<AnnotationEntityTemplateConfig,Father>,
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

    //
    private Father father;

    public AnnotationEntityTemplateConfig(){
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
    public AnnotationEntityTemplateConfig<Father> withTypeMapper(TypeMapper<Class<?>,String> typeMapper){
        this.typeMapper = typeMapper;
        return this;
    }

    /**
     * ConfigInOut SqlDialectBuilder
     * @param separator Prefix Suffix Separator
     * @return This
     */
    public AnnotationEntityTemplateConfig<Father> withSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    /**
     * ConfigInOut SqlDialectBuilder
     * @param namePrefix Name Prefix
     * @return This
     */
    public AnnotationEntityTemplateConfig<Father> withNamePrefix(String namePrefix){
        this.namePrefix = namePrefix;
        return this;
    }

    /**
     * ConfigInOut SqlDialectBuilder
     * @param nameSuffix Alias Name Suffix
     * @return This
     */
    public AnnotationEntityTemplateConfig<Father> withNameSuffix(String nameSuffix){
        this.nameSuffix = nameSuffix;
        return this;
    }

    /**
     * ConfigInOut SqlDialectBuilder
     * @param aliasNamePrefix Alias Name Prefix
     * @return This
     */
    public AnnotationEntityTemplateConfig<Father> withAliasNamePrefix(String aliasNamePrefix){
        this.aliasNamePrefix = aliasNamePrefix;
        return this;
    }

//    /**
//     * ConfigInOut SqlDialectBuilder
//     * @param aliasNameSuffix Alias Name Suffix
//     * @return This
//     */
//    public AnnotationEntityTemplateConfig withAliasNameSuffix(String aliasNameSuffix){
//        this.aliasNameSuffix = aliasNameSuffix;
//        return this;
//    }

    /**
     * ConfigInOut true if you want turn on Multiple Key support
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateConfig<Father> withSupportMultipleKey(Boolean yesNo){
        this.supportMultipleKey = yesNo;
        return this;
    }

    /**
     * ConfigInOut true if you want scan Status
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateConfig<Father> withScanStatus(Boolean yesNo){
        this.scanStatus = yesNo;
        return this;
    }


    /**
     * ConfigInOut true if you want scan Entity
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateConfig<Father> withScanLink(Boolean yesNo){
        this.scanLink = yesNo;
        return this;
    }

    /**
     * ConfigInOut true if you want scan Param
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateConfig<Father> withScanParam(Boolean yesNo){
        this.scanParam = yesNo;
        return this;
    }

    /**
     * ConfigInOut true if you want scan Order
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateConfig<Father> withScanOrder(Boolean yesNo){
        this.scanOrder = yesNo;
        return this;
    }

    /**
     * ConfigInOut true if you want scan All
     * @param yesNo Yes/No
     * @return This
     */
    public AnnotationEntityTemplateConfig<Father> withScanAll(Boolean yesNo) {
        this.scanLink = yesNo;
        this.scanStatus = yesNo;
        this.scanParam = yesNo;
        this.scanOrder = yesNo;
        return this;
    }


    public void initDefault() {
        if(this.typeMapper == null){
            this.typeMapper = new AllVarcharTypeMapper();
        }
    }



    @Override
    public AnnotationEntityTemplateConfig<Father> in(Father father) {
        this.father = father;
        return this;
    }

    @Override
    public Father out() {
        return this.father;
    }


    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public AnnotationEntityTemplateConfig<Father> clone(){
        return new AnnotationEntityTemplateConfig<Father>()
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
