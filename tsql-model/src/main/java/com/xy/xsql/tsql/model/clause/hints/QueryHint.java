package com.xy.xsql.tsql.model.clause.hints;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Clause;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms181714.aspx
 *
 * <query_hint >
 *
 * Created by xiaoyao9184 on 2016/12/26.
 */
public class QueryHint implements Clause {
    //
    private Type type;

    //FAST number_rows
    private Integer numberRows;

    //MAX_GRANT_PERCENT = percent
    //MIN_GRANT_PERCENT = percent
    private Integer percent;

    //MAXDOP number_of_processors
    private Integer numberOfProcessors;

    //MAXRECURSION number
    private Integer number;

    //OPTIMIZE FOR ( @variable_name { UNKNOWN | = literal_constant } [ , ...n ] )
    private List<OptimizeFor> optimizeFor;

    //USE HINT ( '<hint_name>' [ , ...n ] )
    private List<StringConstant> hintNameList;

    //USE PLAN N'xml_plan'
    private StringConstant xmlPlan;

    //TABLE HINT ( exposed_object_name [ , <table_hint> [ [, ]...n ] ] )
    private String exposedObjectName;
    private List<TableHint> tableHintList;
    private boolean useDelimiter;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getNumberRows() {
        return numberRows;
    }

    public void setNumberRows(Integer numberRows) {
        this.numberRows = numberRows;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Integer getNumberOfProcessors() {
        return numberOfProcessors;
    }

    public void setNumberOfProcessors(Integer numberOfProcessors) {
        this.numberOfProcessors = numberOfProcessors;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<OptimizeFor> getOptimizeFor() {
        return optimizeFor;
    }

    public void setOptimizeFor(List<OptimizeFor> optimizeFor) {
        this.optimizeFor = optimizeFor;
    }

    public List<StringConstant> getHintNameList() {
        return hintNameList;
    }

    public void setHintNameList(List<StringConstant> hintNameList) {
        this.hintNameList = hintNameList;
    }

    public StringConstant getXmlPlan() {
        return xmlPlan;
    }

    public void setXmlPlan(StringConstant xmlPlan) {
        this.xmlPlan = xmlPlan;
    }

    public String getExposedObjectName() {
        return exposedObjectName;
    }

    public void setExposedObjectName(String exposedObjectName) {
        this.exposedObjectName = exposedObjectName;
    }

    public List<TableHint> getTableHintList() {
        return tableHintList;
    }

    public void setTableHintList(List<TableHint> tableHintList) {
        this.tableHintList = tableHintList;
    }

    public boolean isUseDelimiter() {
        return useDelimiter;
    }

    public void setUseDelimiter(boolean useDelimiter) {
        this.useDelimiter = useDelimiter;
    }

    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(type);

        switch (type){
            case FAST:
                b.append(numberRows);
                break;
            case MAX_GRANT_PERCENT:
            case MIN_GRANT_PERCENT:
                b.append(percent);
                break;
            case MAXDOP:
                b.append(numberOfProcessors);
                break;
            case MAXRECURSION:
                b.append(number);
                break;
            case OPTIMIZE_FOR:
                b.append(optimizeFor, Other.DELIMITER);
                break;
            case USE_HINT:
                for(StringConstant unknownString : hintNameList){
                    unknownString.withQuote();
                }
                b.append(hintNameList,Other.DELIMITER);
                break;
            case USE_PLAN:
                b.append(xmlPlan.withNQuote());
                break;
            case TABLE_HINT:
                b.append(Other.GROUP_START)
                            .append(exposedObjectName)
                            .append(tableHintList,useDelimiter ? Other.DELIMITER : null)
                            .append(Other.GROUP_END);
                break;
        }

        return b.build();
    }


    public enum Type implements Block {
        HASH_GROUP("HASH GROUP"),
        ORDER_GROUP("ORDER GROUP"),
        CONCAT_UNION("CONCAT UNION"),
        HASH_UNION("HASH UNION"),
        MERGE_UNION("MERGE UNION"),
        LOOP_JOIN("LOOP JOIN"),
        MERGE_JOIN("MERGE JOIN"),
        HASH_JOIN("HASH JOIN"),
        EXPAND_VIEWS("EXPAND VIEWS"),
        FAST,
        FORCE_ORDER("FORCE ORDER"),
        FORCE_EXTERNALPUSHDOWN("FORCE EXTERNALPUSHDOWN"),
        DISABLE_EXTERNALPUSHDOWN("DISABLE EXTERNALPUSHDOWN"),
        IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX,
        KEEP_PLAN("KEEP PLAN"),
        KEEPFIXED_PLAN("KEEPFIXED PLAN"),
        MAX_GRANT_PERCENT,
        MIN_GRANT_PERCENT,
        MAXDOP,
        MAXRECURSION,
        NO_PERFORMANCE_SPOOL,
        OPTIMIZE_FOR("OPTIMIZE FOR"),
        OPTIMIZE_FOR_UNKNOWN("OPTIMIZE FOR UNKNOWN"),
        PARAMETERIZATION_SIMPLE("PARAMETERIZATION SIMPLE"),
        PARAMETERIZATION_FORCED("PARAMETERIZATION FORCED"),
        RECOMPILE,
        ROBUST_PLAN("ROBUST PLAN"),
        USE_HINT("USE HINT"),
        USE_PLAN("USE PLAN"),
        TABLE_HINT("TABLE HINT");


        private String string;

        Type(){
            this.string = this.name();
        }

        Type(String string){
            this.string = string;
        }

        @Override
        public String toString() {
            return this.name();
        }
    }

    public static class OptimizeFor implements Block {
        private String variableName;
        private boolean useUnknown;
        private String literalConstant;

        public String getVariableName() {
            return variableName;
        }

        public void setVariableName(String variableName) {
            this.variableName = variableName;
        }

        public boolean isUseUnknown() {
            return useUnknown;
        }

        public void setUseUnknown(boolean useUnknown) {
            this.useUnknown = useUnknown;
        }

        public String getLiteralConstant() {
            return literalConstant;
        }

        public void setLiteralConstant(String literalConstant) {
            this.literalConstant = literalConstant;
        }

        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder();
            b.append("@" + variableName);
            if(useUnknown){
                b.append(Keywords.Key.UNKNOWN);
            } else {
                b.append(Operators.EQUAL)
                        .append(literalConstant);
            }
            return b.build();
        }
    }

}
