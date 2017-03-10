package com.xy.xsql.orm.data.sql.clause.hints;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;

import java.util.ArrayList;
import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms181714.aspx
 *
 * <query_hint >
 *
 * Created by xiaoyao9184 on 2016/12/26.
 */
public class QueryHint implements ElementList {
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
    private List<UnknownString> hintNameList;

    //USE PLAN N'xml_plan'
    private UnknownString xmlPlan;

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

    public List<UnknownString> getHintNameList() {
        return hintNameList;
    }

    public void setHintNameList(List<UnknownString> hintNameList) {
        this.hintNameList = hintNameList;
    }

    public UnknownString getXmlPlan() {
        return xmlPlan;
    }

    public void setXmlPlan(UnknownString xmlPlan) {
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
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder()
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
                b.append(optimizeFor,OtherEnum.DELIMITER);
                break;
            case USE_HINT:
                for(UnknownString unknownString : hintNameList){
                    unknownString.withQuote(true);
                }
                b.append(hintNameList,OtherEnum.DELIMITER);
                break;
            case USE_PLAN:
                b.append(xmlPlan.withNQuote(true));
                break;
            case TABLE_HINT:
                b.append(OtherEnum.GROUP_START)
                            .append(exposedObjectName)
                            .append(tableHintList,useDelimiter ? OtherEnum.DELIMITER : null)
                            .append(OtherEnum.GROUP_END);
                break;
        }

        return b.build();
    }


    public enum Type implements Element {
        HASH_GROUP("HASH GROUP"),
        ORDER_GROUP("ORDER GROUP"),
        CONCAT_UNION("CONCAT UNION"),
        HASH_UNION("HASH UNION"),
        MERGE_UNION("MERGE UNION"),
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

    public static class OptimizeFor implements ElementList {
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
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder();
            b.append("@" + variableName);
            if(useUnknown){
                b.append(GrammarEnum.UNKNOWN);
            } else {
                b.append(OperatorEnum.EQUAL)
                        .append(literalConstant);
            }
            return b.build();
        }
    }

}
