package com.xy.xsql.tsql.model.statement.ddl.alter.table;

import com.xy.xsql.tsql.model.elements.expressions.Expression;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class Add implements Item {

    private List<AddItem> items;

    private String systemStartTimeColumnName;
    private boolean useHidden4StartTime;
    private boolean useNotNull4StartTime;
    private String constraintName4StartTime;
    private Expression constantExpression4StartTime;
    private boolean useWithValues4StartTime;

    private String systemEndTimeColumnName;
    private boolean useHidden4EndTime;
    private boolean useNotNull4EndTime;
    private String constraintName4EndTime;
    private Expression constantExpression4EndTime;
    private boolean useWithValues4EndTime;

    private String systemStartTimeColumnName4Period;
    private String systemEndTimeColumnName4Period;

    public List<AddItem> getItems() {
        return items;
    }

    public void setItems(List<AddItem> items) {
        this.items = items;
    }

    public String getSystemStartTimeColumnName() {
        return systemStartTimeColumnName;
    }

    public void setSystemStartTimeColumnName(String systemStartTimeColumnName) {
        this.systemStartTimeColumnName = systemStartTimeColumnName;
    }

    public boolean isUseHidden4StartTime() {
        return useHidden4StartTime;
    }

    public void setUseHidden4StartTime(boolean useHidden4StartTime) {
        this.useHidden4StartTime = useHidden4StartTime;
    }

    public boolean isUseNotNull4StartTime() {
        return useNotNull4StartTime;
    }

    public void setUseNotNull4StartTime(boolean useNotNull4StartTime) {
        this.useNotNull4StartTime = useNotNull4StartTime;
    }

    public String getConstraintName4StartTime() {
        return constraintName4StartTime;
    }

    public void setConstraintName4StartTime(String constraintName4StartTime) {
        this.constraintName4StartTime = constraintName4StartTime;
    }

    public Expression getConstantExpression4StartTime() {
        return constantExpression4StartTime;
    }

    public void setConstantExpression4StartTime(Expression constantExpression4StartTime) {
        this.constantExpression4StartTime = constantExpression4StartTime;
    }

    public boolean isUseWithValues4StartTime() {
        return useWithValues4StartTime;
    }

    public void setUseWithValues4StartTime(boolean useWithValues4StartTime) {
        this.useWithValues4StartTime = useWithValues4StartTime;
    }

    public String getSystemEndTimeColumnName() {
        return systemEndTimeColumnName;
    }

    public void setSystemEndTimeColumnName(String systemEndTimeColumnName) {
        this.systemEndTimeColumnName = systemEndTimeColumnName;
    }

    public boolean isUseHidden4EndTime() {
        return useHidden4EndTime;
    }

    public void setUseHidden4EndTime(boolean useHidden4EndTime) {
        this.useHidden4EndTime = useHidden4EndTime;
    }

    public boolean isUseNotNull4EndTime() {
        return useNotNull4EndTime;
    }

    public void setUseNotNull4EndTime(boolean useNotNull4EndTime) {
        this.useNotNull4EndTime = useNotNull4EndTime;
    }

    public String getConstraintName4EndTime() {
        return constraintName4EndTime;
    }

    public void setConstraintName4EndTime(String constraintName4EndTime) {
        this.constraintName4EndTime = constraintName4EndTime;
    }

    public Expression getConstantExpression4EndTime() {
        return constantExpression4EndTime;
    }

    public void setConstantExpression4EndTime(Expression constantExpression4EndTime) {
        this.constantExpression4EndTime = constantExpression4EndTime;
    }

    public boolean isUseWithValues4EndTime() {
        return useWithValues4EndTime;
    }

    public void setUseWithValues4EndTime(boolean useWithValues4EndTime) {
        this.useWithValues4EndTime = useWithValues4EndTime;
    }

    public String getSystemStartTimeColumnName4Period() {
        return systemStartTimeColumnName4Period;
    }

    public void setSystemStartTimeColumnName4Period(String systemStartTimeColumnName4Period) {
        this.systemStartTimeColumnName4Period = systemStartTimeColumnName4Period;
    }

    public String getSystemEndTimeColumnName4Period() {
        return systemEndTimeColumnName4Period;
    }

    public void setSystemEndTimeColumnName4Period(String systemEndTimeColumnName4Period) {
        this.systemEndTimeColumnName4Period = systemEndTimeColumnName4Period;
    }


    public interface AddItem {}
}
