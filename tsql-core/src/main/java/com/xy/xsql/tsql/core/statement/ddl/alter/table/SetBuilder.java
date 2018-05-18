package com.xy.xsql.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.element.index.PartitionBuilder;
import com.xy.xsql.tsql.model.element.index.Partition;
import com.xy.xsql.tsql.model.statements.alter.table.Set;

import static com.xy.xsql.core.FiledBuilder.initSet;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class SetBuilder<ParentBuilder>
        extends CodeTreeBuilder<SetBuilder<ParentBuilder>,ParentBuilder,Set> {

    public SetBuilder(Set target) {
        super(target);
    }

    public SetBuilder<ParentBuilder> withFileStreamOn(Partition fileStreamOn){
        target.setFileStreamOn(fileStreamOn);
        return this;
    }

    public SetBuilder<ParentBuilder> withUseSystemVersioningOn(boolean useSystemVersioningOn){
        target.setUseSystemVersioningOn(useSystemVersioningOn);
        return this;
    }

    public SetBuilder<ParentBuilder> withSchemaName(String schemaName){
        target.setSchemaName(schemaName);
        return this;
    }

    public SetBuilder<ParentBuilder> withHistoryTableName(String historyTableName){
        target.setHistoryTableName(historyTableName);
        return this;
    }

    public SetBuilder<ParentBuilder> withUseDataConsistencyCheck(Boolean useDataConsistencyCheck){
        target.setUseDataConsistencyCheck(useDataConsistencyCheck);
        return this;
    }

    public SetBuilder<ParentBuilder> withUseHistoryRetentionPeriod(Boolean useHistoryRetentionPeriod){
        target.setUseHistoryRetentionPeriod(useHistoryRetentionPeriod);
        return this;
    }

    public SetBuilder<ParentBuilder> withHistoryRetentionPeriodNumber(Integer historyRetentionPeriodNumber){
        target.setHistoryRetentionPeriodNumber(historyRetentionPeriodNumber);
        return this;
    }

    public SetBuilder<ParentBuilder> withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit historyRetentionPeriodUnit){
        target.setHistoryRetentionPeriodUnit(historyRetentionPeriodUnit);
        return this;
    }

    /*
    Quick
     */

    public PartitionBuilder<SetBuilder<ParentBuilder>> $FILESTREAM_ON(){
        return new PartitionBuilder<SetBuilder<ParentBuilder>>
                (initSet(Partition::new,
                        target::getFileStreamOn,
                        target::setFileStreamOn))
                .in(this);
    }

    public SetBuilder<ParentBuilder> $SYSTEM_VERSIONING(){
        target.setFileStreamOn(null);
        return this;
    }

    public SetBuilder<ParentBuilder> $ON(){
        return withUseSystemVersioningOn(true);
    }

    public SetBuilder<ParentBuilder> $OFF(){
        return withUseSystemVersioningOn(false);
    }

    public SetBuilder<ParentBuilder> $HISTORY_TABLE(String schemaName, String historyTableName){
        return withSchemaName(schemaName)
                .withHistoryTableName(historyTableName);
    }

    public SetBuilder<ParentBuilder> $DATA_CONSISTENCY_CHECK_$ON(){
        return withUseDataConsistencyCheck(true);
    }

    public SetBuilder<ParentBuilder> $DATA_CONSISTENCY_CHECK_$OFF(){
        return withUseDataConsistencyCheck(false);
    }

    public SetBuilder<ParentBuilder> $HISTORY_RETENTION_PERIOD_$INFINITE(){
        return withUseHistoryRetentionPeriod(true);
    }

    public SetBuilder<ParentBuilder> $HISTORY_RETENTION_PERIOD(Integer historyRetentionPeriodNumber){
        return withUseHistoryRetentionPeriod(false)
                .withHistoryRetentionPeriodNumber(historyRetentionPeriodNumber);
    }

    public SetBuilder<ParentBuilder> $DAY(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.DAY);
    }

    public SetBuilder<ParentBuilder> $DAYS(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.DAYS);
    }

    public SetBuilder<ParentBuilder> $WEEK(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.WEEK);
    }

    public SetBuilder<ParentBuilder> $WEEKS(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.WEEKS);
    }

    public SetBuilder<ParentBuilder> $MONTH(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.MONTH);
    }

    public SetBuilder<ParentBuilder> $MONTHS(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.MONTHS);
    }

    public SetBuilder<ParentBuilder> $YEAR(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.YEAR);
    }

    public SetBuilder<ParentBuilder> $YEARS(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.YEARS);
    }

}
