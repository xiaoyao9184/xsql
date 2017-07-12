package com.xy.xsql.block.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
@SuppressWarnings({"unchecked", "unused", "SameParameterValue"})
public class BlockMeta implements Block {

    private boolean overall;

    //KeywordBlock
    private boolean keyword;
    //MultiBlock
    private boolean exclusive;
    private boolean list;
    private boolean repeat;

    private boolean required;
    private boolean optional;


    private String name;
    private String description;
    private List<BlockMeta> sub;
    private List<Predicate<?>> casePredicate = new ArrayList<>();

    //style
    private boolean eachSubTakeLine;
    private boolean headFootTakeLine;
    private boolean startNewLine;
    private boolean endNewLine;

    //format
    private Format format;

    //reference
    private BlockMeta refMeta;
    private Class refClass;
    private Object data;
    private Function dataGetter;
    private List<Predicate<?>> verifierList;

    //check
    private Predicate<?> optionalFilter;



    public BlockMeta(){
        this.verifierList = new ArrayList<>();
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(list || repeat){
            sb.append("▤");
        }
        if(exclusive){
            sb.append("▥");
        }
        if(refClass != null){
            sb.append("⇲");
        }
        if(overall){
            sb.append("⇱");
        }
        if(keyword){
            sb.append("🔑");
        }

        if(name != null){
            sb.append(name);
        }

        if(description != null){
            sb.append('⚑')
                    .append(description);
        }
        return sb.toString();
    }

    public boolean isOverall() {
        return overall;
    }

    public void setOverall(boolean overall) {
        this.overall = overall;
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

    public boolean isList() {
        return list;
    }

    public void setList(boolean list) {
        this.list = list;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BlockMeta> getSub() {
        return sub;
    }

    public void setSub(List<BlockMeta> sub) {
        this.sub = sub;
    }

    public List<Predicate<?>> getCasePredicate() {
        return casePredicate;
    }

    public void setCasePredicate(List<Predicate<?>> casePredicate) {
        this.casePredicate = casePredicate;
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

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public BlockMeta getRefMeta() {
        return refMeta;
    }

    public void setRefMeta(BlockMeta refMeta) {
        this.refMeta = refMeta;
    }

    public Class getRefClass() {
        return refClass;
    }

    public void setRefClass(Class refClass) {
        this.refClass = refClass;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Function getDataGetter() {
        return dataGetter;
    }

    public void setDataGetter(Function dataGetter) {
        this.dataGetter = dataGetter;
    }

    public List<Predicate<?>> getVerifierList() {
        return verifierList;
    }

    public void setVerifierList(List<Predicate<?>> verifierList) {
        this.verifierList = verifierList;
    }

    public Predicate<?> getOptionalFilter() {
        return optionalFilter;
    }

    public void setOptionalFilter(Predicate<?> optionalFilter) {
        this.optionalFilter = optionalFilter;
    }


    /*

     */

    public Object getContext(Object context){
        if(dataGetter == null){
            return context;
        }
        return dataGetter.apply(context);
    }

    public Object getDataOrGetterData(Object data){
        if(dataGetter == null){
            return null;
        }
        return dataGetter.apply(data);
    }

    public boolean isDataOrReference(){
        return this.data != null ||
                this.dataGetter != null;
    }

    public boolean isReference(){
        return this.refClass != null ||
                this.refMeta != null;
    }

    public boolean isReferenceClass(){
        return this.refClass != null;
    }

    public boolean isReferenceMeta(){
        return this.refMeta != null;
    }

    public void addVerifier(Predicate<?> verifier){
        this.verifierList.add(verifier);
    }


    /**
     * Format for print context
     */
    @SuppressWarnings("SameParameterValue")
    public static class Format {
        private boolean newLine;
        private int indentation;

        public boolean isNewLine() {
            return newLine;
        }

        public void setNewLine(boolean newLine) {
            this.newLine = newLine;
        }

        public int getIndentation() {
            return indentation;
        }

        public void setIndentation(int indentation) {
            this.indentation = indentation;
        }
    }
}
