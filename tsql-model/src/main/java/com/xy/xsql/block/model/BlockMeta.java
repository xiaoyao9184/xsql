package com.xy.xsql.block.model;

import java.util.List;
import java.util.Optional;
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
 * 'Overall meta' is root meta
 * The nodes under 'Overall meta' share a model scope,
 * Any meta attribute in this model scope is for this model.
 * Other angles very similar like 'Virtual meta'
 *
 * E.g
 * {@link #optionalPredicate} according to the current scope to determine whether it is optional
 *
 *
 * 'Virtual meta' is a container for aggregated meta,
 * Contains many child meta, and no use name
 *
 *
 * 'Data meta' is a end node for meta
 * Can refer to other meta, can contains child meta.
 * use name with refer is Named references,
 * no name with refer is Anonymous references.
 *
 * Reference to reuse, but will also bring about changes in the scope of the model.
 * Reference maybe specify a new model scope, the new scope is generated by the old scope.
 *
 * Contains child meta in 'Data meta' is very few cases,
 * Mainly to hide child meta details
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
    Format
     */
    private Format format;
    private Format subFormat;
    private SyntaxFormat syntaxFormat;
    private SyntaxFormat subSyntaxFormat;
    private Style style;

    public BlockMeta(){}


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        if(isOverall()){
            sb.append("⇱");
        }
        if(isKeyword()){
            sb.append("🔑");
        }
        if(isCollection()){
            sb.append("▤");
        }
        if(isExclusive()){
            sb.append("▥");
        }

        if(name != null){
            sb.append(name);
        }
        if(isReference()){
            sb.append("⇲");
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

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Format getSubFormat() {
        return subFormat;
    }

    public void setSubFormat(Format subFormat) {
        this.subFormat = subFormat;
    }

    public SyntaxFormat getSyntaxFormat() {
        return syntaxFormat;
    }

    public void setSyntaxFormat(SyntaxFormat syntaxFormat) {
        this.syntaxFormat = syntaxFormat;
    }

    public SyntaxFormat getSubSyntaxFormat() {
        return subSyntaxFormat;
    }

    public void setSubSyntaxFormat(SyntaxFormat subSyntaxFormat) {
        this.subSyntaxFormat = subSyntaxFormat;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    /*
    No Getter
     */

    public Optional<Style> style(){
        return Optional.ofNullable(this.getStyle());
    }

    public Optional<Format> format(){
        return Optional.ofNullable(this.getFormat());
    }

    public Optional<Format> sub_format(){
        return Optional.ofNullable(this.getSubFormat());
    }

    public Optional<SyntaxFormat> syntax(){
        return Optional.ofNullable(this.getSyntaxFormat());
    }

    public Optional<SyntaxFormat> sub_syntax(){
        return Optional.ofNullable(this.getSubSyntaxFormat());
    }




    public boolean isOptional() {
        return this.optionalPredicate != null;
    }

    /**
     * Virtual
     * @return t/f
     */
    public boolean isVirtual(){
        return this.getSub() != null &&
                this.getName() == null;
    }

    /**
     * Data
     * @return t/f
     */
    public boolean isData(){
        return !this.isOverall() &&
                this.getName() != null;
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
        return this.isReference() &&
                this.getName() != null;
    }

    /**
     * Anonymous references
     * Reference By meta
     * @return t/f
     */
    public boolean isAnonymousReference(){
        return this.isReference() &&
                this.getName() == null;
    }

    /**
     * Reference By Converter
     * @return t/f
     */
    public boolean isReferenceConverter (){
        return this.referenceConverter != null;
    }

    /**
     * Reference By meta
     * @return t/f
     */
    public boolean isReferenceMeta(){
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
        private boolean reference;
        private boolean startNewLine;
        private boolean endNewLine;
        private boolean conventionLineDelimiter;
        private boolean subLineDelimiter;

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

        public boolean isReference() {
            return reference;
        }

        public void setReference(boolean reference) {
            this.reference = reference;
        }

        public boolean isStartNewLine() {
            return startNewLine;
        }

        public void setStartNewLine(boolean startNewLine) {
            this.startNewLine = startNewLine;
        }

        public Boolean isSubNewLine() {
            return subLineDelimiter;
        }

        public boolean isEndNewLine() {
            return endNewLine;
        }

        public void setEndNewLine(boolean endNewLine) {
            this.endNewLine = endNewLine;
        }

        public void setSubLineDelimiter(boolean subLineDelimiter) {
            this.subLineDelimiter = subLineDelimiter;
        }

        public boolean isConventionLineDelimiter() {
            return conventionLineDelimiter;
        }

        public void setConventionLineDelimiter(boolean conventionLineDelimiter) {
            this.conventionLineDelimiter = conventionLineDelimiter;
        }

    }

    public static class SyntaxFormat extends Format {
        private boolean required;
        private boolean optional;
        private boolean reference;
        private boolean indentationContent;

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

        public boolean isReference() {
            return reference;
        }

        public void setReference(boolean reference) {
            this.reference = reference;
        }

        public boolean isIndentationContent() {
            return indentationContent;
        }

        public void setIndentationContent(boolean indentationContent) {
            this.indentationContent = indentationContent;
        }
    }

    /**
     * Format for print model
     * Operates for the interval between the current block and the previous block
     */
    public static class Format {
        private boolean newLine;
        private int indentation;
        private String indentationChar = "\t";
        private boolean useDefaultDelimiter = true;
        private String delimiterChar;

        public boolean isNewLine() {
            return newLine;
        }

        public void setNewLine(boolean newLine) {
            this.newLine = newLine;
        }

        public int getIndentation() {
            return indentation;
        }

        /**
         * < 0 left indentation
         * > 0 right indentation
         * @param indentation indentation level
         */
        public void setIndentation(int indentation) {
            this.indentation = indentation;
        }

        public boolean isUseDefaultDelimiter() {
            return useDefaultDelimiter;
        }

        public void setUseDefaultDelimiter(boolean useDefaultDelimiter) {
            this.useDefaultDelimiter = useDefaultDelimiter;
        }

        public String getIndentationChar() {
            return indentationChar;
        }

        public void setIndentationChar(String indentationChar) {
            this.indentationChar = indentationChar;
        }

        public String getDelimiterChar() {
            return delimiterChar;
        }

        public void setDelimiterChar(String delimiterChar) {
            this.delimiterChar = delimiterChar;
        }
    }



    public enum BlockDelimiterConvention {
        _COMMA_(" , "),
        _ONE_OF_(" | "),
        COMMA_(", "),
        ONE_OF_("| "),


        PREFIX_COMMA(" , "),
        PREFIX_ONE_OF(" | "),

        NO_PREFIX_COMMA(", "),
        NO_PREFIX_ONE_OF("| ");

        private String key;

        BlockDelimiterConvention(String key){
            this.key = key;
        }

        @Override
        public String toString() {
            return key;
        }
    }

    public enum BlockConvention {
        EMPTY(""),
        BLANKS(" "),
        COMMA(","),
        ONE_OF("|"),
        LINE("\n"),
        TERMINATOR(";"),
        REFERENCE_LABEL("::="),

        REPEATED_COMMA("[,...n]"),
        REPEATED_BLANKS("[...n]"),
        REPEATED_COMMA_BLANKS("[ [, ]...n ]"),

        REFERENCE_START("<"),
        REFERENCE_END(">"),
        OPTIONAL_START("["),
        OPTIONAL_END("]"),
        REQUIRED_START("{"),
        REQUIRED_END("}");

        private String key;

        BlockConvention(String key){
            this.key = key;
        }

        @Override
        public String toString() {
            return key;
        }
    }

}