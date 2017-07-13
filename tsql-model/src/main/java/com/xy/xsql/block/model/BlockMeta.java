package com.xy.xsql.block.model;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * BlockMeta
 *
 * meta structure like tree,
 * tree contain 'root node', 'branch node' and 'end node'.
 * meta contain 'Overall meta', 'Virtual meta' and 'Data meta'.
 *
 *
 * 'Overall meta' very similar like 'Virtual meta'
 * The nodes under 'Overall meta' share a model scope,
 * Any meta attribute in this model scope is for this model.
 *
 * E.g
 * {@link #optionalPredicate} according to the current scope to determine whether it is optional
 *
 *
 * 'Virtual meta' is a container for aggregated meta,
 * Contains many child meta
 *
 *
 * 'Data meta' can refer to other meta,
 * refer 'Overall meta' is Named references,
 * other is Anonymous references.
 *
 * Reference to reuse, but will also bring about changes in the scope of the model
 * Named references must specify a new model scope, the new scope is generated by the old scope
 * Anonymous references can not be specified.
 *
 *
 *
 *
 * Special meta:
 *
 *
 * 'Collection meta'
 * Usually Is 'Data meta' but may be 'Virtual meta'
 * Exactly the same meta collection, connected by a delimiter.
 * {@link #list} mode is use ',' as delimiter.
 * {@link #repeat} mode is use ' ' as delimiter.
 *
 * Note: You can store different meta as 'Collection meta' child in {@link #sub},
 * this is only meaningful in the list mode,
 * because repeat mode with different child meta is just 'Virtual meta'
 *
 *
 * 'Exclusive meta'
 * Usually Is 'Data meta' but may be 'Virtual meta'
 * Mutual meta collection.
 * The judgment of the mutex is done by {@link #exclusivePredicate}.
 * The mutually exclusive meta is stored sub {@link #sub}.
 *
 *
 * 'Keyword meta'
 * Is 'Data meta' with stationary String data
 * {@link #keyword} mode meta corresponds to the model scope and name is the same string
 *
 * Created by xiaoyao9184 on 2017/3/14.
 */
@SuppressWarnings({"unchecked", "unused", "SameParameterValue"})
public class BlockMeta implements Block {

    /*
    Structure
     */

    //name for Any
    private String name;
    //description for Any
    private String description;
    //optional for Any
    private Predicate<?> optionalPredicate;

    //Overall
    private boolean overall;

    //exclusive for Virtual
    private List<Predicate<?>> exclusivePredicate;
    //exclusive/collection for Virtual
    private List<BlockMeta> sub;
    //collection for Virtual
    private boolean list;
    private boolean repeat;

    //keyword for Data
    private boolean keyword;
    //reference for Data
    private BlockMeta referenceMeta;
    private Class referenceConverter;
    //scope for Data
    private Function scopeGetter;


    /*
    Style
     */
    private boolean required;
    private boolean optional;
    private boolean eachSubTakeLine;
    private boolean headFootTakeLine;
    private boolean startNewLine;
    private boolean endNewLine;

    /*
    Format
     */
    private Format format;

    public BlockMeta(){
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(list || repeat){
            sb.append("▤");
        }
        if(exclusivePredicate == null ||
                exclusivePredicate.isEmpty()){
            sb.append("▥");
        }
        if(referenceConverter != null){
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

    public Predicate<?> getOptionalPredicate() {
        return optionalPredicate;
    }

    public void setOptionalPredicate(Predicate<?> optionalPredicate) {
        this.optionalPredicate = optionalPredicate;
    }

    public boolean isOverall() {
        return overall;
    }

    public void setOverall(boolean overall) {
        this.overall = overall;
    }


    public List<Predicate<?>> getExclusivePredicate() {
        return exclusivePredicate;
    }

    public void setExclusivePredicate(List<Predicate<?>> exclusivePredicate) {
        this.exclusivePredicate = exclusivePredicate;
    }

    public List<BlockMeta> getSub() {
        return sub;
    }

    public void setSub(List<BlockMeta> sub) {
        this.sub = sub;
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

    public boolean isKeyword() {
        return keyword;
    }

    public void setKeyword(boolean keyword) {
        this.keyword = keyword;
    }

    public BlockMeta getReferenceMeta() {
        return referenceMeta;
    }

    public void setReferenceMeta(BlockMeta referenceMeta) {
        this.referenceMeta = referenceMeta;
    }

    public Class getReferenceConverter() {
        return referenceConverter;
    }

    public void setReferenceConverter(Class referenceConverter) {
        this.referenceConverter = referenceConverter;
    }

    public Function getScopeGetter() {
        return scopeGetter;
    }

    public void setScopeGetter(Function scopeGetter) {
        this.scopeGetter = scopeGetter;
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


    /*
    No Getter
     */


    /**
     * Virtual
     * As a container contains subordinate meta
     * @return t/f
     */
    public boolean isVirtual(){
        return this.getSub() != null;
    }

    /**
     * Exclusive
     * @see #getExclusivePredicate()
     * @return t/f
     */
    public boolean isExclusive() {
        return exclusivePredicate != null &&
                !exclusivePredicate.isEmpty();
    }

    /**
     * List or Repeat
     * @see #isList()
     * @see #isRepeat()
     * @return t/f
     */
    public boolean isCollection() {
        return isList() || isRepeat();
    }

    /**
     * Reference
     * Quote other meta instead of this meta
     * @return t/f
     */
    public boolean isReference(){
        return this.referenceConverter != null ||
                this.referenceMeta != null;
    }

    /**
     * Named references
     * Reference By Converter
     * @return t/f
     */
    public boolean isNamedReference(){
        return this.referenceConverter != null;
    }

    /**
     * Anonymous references
     * Reference By meta
     * @return t/f
     */
    public boolean isAnonymousReference(){
        return this.referenceMeta != null;
    }



    /**
     * Get meta scope form current scope
     * if scopeGetter no set, will return this current scope
     * @param scope current scope
     * @return data
     */
    public Object getScope(Object scope){
        if(scopeGetter == null){
            return scope;
        }
        return scopeGetter.apply(scope);
    }

    /**
     * Style for print syntax
     */
    public static class Style {
        private boolean required;
        private boolean optional;
        private boolean eachSubTakeLine;
        private boolean headFootTakeLine;
        private boolean startNewLine;
        private boolean endNewLine;

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
    }

    /**
     * Format for print model
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