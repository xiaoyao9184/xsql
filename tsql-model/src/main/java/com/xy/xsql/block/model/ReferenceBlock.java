package com.xy.xsql.block.model;

import com.xy.xsql.tsql.model.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public class ReferenceBlock implements Block {

    private boolean overall;

    //KeywordBlock or MultiBlock
    private boolean keyword;
    //MultiBlock
    private boolean exclusive;
    private boolean list;
    private boolean repeat;
    private boolean more;

    private boolean required;
    private boolean optional;
    //TODO ()是语法是描述
    private boolean group;


    private String name;
    private String description;
    private List<ReferenceBlock> sub;

    //style
    private boolean eachSubTakeLine;
    private boolean headFootTakeLine;
    private boolean startNewLine;
    private boolean endNewLine;

    //reference
    private Class refClass;
    private Object data;
    private Function<?,?> dataGetter;
    private List<Predicate<?>> verifierList;

    //check
    private Predicate<?> optionalFilter;

    private Class<?> type;



    public ReferenceBlock (){
        this.verifierList = new ArrayList<>();
    }


    public String toString(){
        return this.getName() == null ?
                "" :
                this.getName()
                        + (this.getDescription() == null ?
                        "" :
                        this.getDescription());
    }

    @Override
    public boolean hasChild() {
        return sub.size() > 0;
    }

    @Override
    public List<ReferenceBlock> toBlockList() {
        return sub;
    }



    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public Function<?,?> getDataGetter() {
        return dataGetter;
    }

    public void setDataGetter(Function<?,?> dataGetter) {
        this.dataGetter = dataGetter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isDataOrReference(){
        return this.data != null ||
                this.dataGetter != null;
    }

    public boolean isReference(){
        return this.refClass != null;
    }




    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    @Deprecated
    public boolean isGroup() {
        return group;
    }

    @Deprecated
    public void setGroup(boolean group) {
        this.group = group;
    }

    public Predicate<?> getOptionalFilter() {
        return optionalFilter;
    }

    public void setOptionalFilter(Predicate<?> optionalFilter) {
        this.optionalFilter = optionalFilter;
    }

    public List<ReferenceBlock> getSub() {
        return sub;
    }

    public void setSub(List<ReferenceBlock> sub) {
        this.sub = sub;
    }

    public void addVerifier(Predicate<?> verifier){
        this.verifierList.add(verifier);
    }

    public boolean isList() {
        return list;
    }

    public void setList(boolean list) {
        this.list = list;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isKeyword() {
        return keyword;
    }

    public void setKeyword(boolean keyword) {
        this.keyword = keyword;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Class getRefClass() {
        return refClass;
    }

    public void setRefClass(Class refClass) {
        this.refClass = refClass;
    }

    public boolean isEachSubTakeLine() {
        return eachSubTakeLine;
    }

    public void setEachSubTakeLine(boolean eachSubTakeLine) {
        this.eachSubTakeLine = eachSubTakeLine;
    }

    public boolean isHeadFootTakeLine() {
        return headFootTakeLine;
    }

    public void setHeadFootTakeLine(boolean headFootTakeLine) {
        this.headFootTakeLine = headFootTakeLine;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean isOverall() {
        return overall;
    }

    public void setOverall(boolean overall) {
        this.overall = overall;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public boolean isStartNewLine() {
        return startNewLine;
    }

    public void setStartNewLine(boolean startNewLine) {
        this.startNewLine = startNewLine;
    }

    public boolean isEndNewLine() {
        return endNewLine;
    }

    public void setEndNewLine(boolean endNewLine) {
        this.endNewLine = endNewLine;
    }
}
