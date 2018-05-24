package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.PartitionBuilder;
import com.xy.xsql.tsql.model.datatypes.table.index.Partition;
import com.xy.xsql.tsql.model.statements.alter.table.Set;

import static com.xy.xsql.core.FiledBuilder.initSet;

/**
 * SetBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class SetBuilder<ParentBuilder>
        extends CodeTreeBuilder<SetBuilder<ParentBuilder>,ParentBuilder,Set> {

    public SetBuilder(Set target) {
        super(target);
    }

    /**
     * set
     * @param fileStreamOn file stream on
     * @return THIS
     */
    public SetBuilder<ParentBuilder> withFileStreamOn(Partition fileStreamOn){
        target.setFileStreamOn(fileStreamOn);
        return this;
    }

    /**
     * set
     * @param useSystemVersioningOn system versioning on
     * @return THIS
     */
    public SetBuilder<ParentBuilder> withUseSystemVersioningOn(boolean useSystemVersioningOn){
        target.setUseSystemVersioningOn(useSystemVersioningOn);
        return this;
    }

    /**
     * set
     * @param schemaName schema name
     * @return THIS
     */
    public SetBuilder<ParentBuilder> withSchemaName(String schemaName){
        target.setSchemaName(schemaName);
        return this;
    }

    /**
     * set
     * @param historyTableName history table name
     * @return THIS
     */
    public SetBuilder<ParentBuilder> withHistoryTableName(String historyTableName){
        target.setHistoryTableName(historyTableName);
        return this;
    }

    /**
     * set
     * @param useDataConsistencyCheck data consistency check
     * @return THIS
     */
    public SetBuilder<ParentBuilder> withUseDataConsistencyCheck(Boolean useDataConsistencyCheck){
        target.setUseDataConsistencyCheck(useDataConsistencyCheck);
        return this;
    }

    /**
     * set
     * @param useHistoryRetentionPeriod history retention period
     * @return THIS
     */
    public SetBuilder<ParentBuilder> withUseHistoryRetentionPeriod(Boolean useHistoryRetentionPeriod){
        target.setUseHistoryRetentionPeriod(useHistoryRetentionPeriod);
        return this;
    }

    /**
     * set
     * @param historyRetentionPeriodNumber history retention period number
     * @return THIS
     */
    public SetBuilder<ParentBuilder> withHistoryRetentionPeriodNumber(Integer historyRetentionPeriodNumber){
        target.setHistoryRetentionPeriodNumber(historyRetentionPeriodNumber);
        return this;
    }

    /**
     * set
     * @param historyRetentionPeriodUnit HistoryRetentionPeriodUnit
     * @return THIS
     */
    public SetBuilder<ParentBuilder> withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit historyRetentionPeriodUnit){
        target.setHistoryRetentionPeriodUnit(historyRetentionPeriodUnit);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick in
     * @return PartitionBuilder
     */
    public PartitionBuilder<SetBuilder<ParentBuilder>> $FilestreamOn(){
        return new PartitionBuilder<SetBuilder<ParentBuilder>>
                (initSet(Partition::new,
                        target::getFileStreamOn,
                        target::setFileStreamOn))
                .in(this);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $SystemVersioning(){
        target.setFileStreamOn(null);
        return this;
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $On(){
        return withUseSystemVersioningOn(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $Off(){
        return withUseSystemVersioningOn(false);
    }

    /**
     * set
     * @param schemaName schema name
     * @param historyTableName history table name
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $HistoryTable(String schemaName, String historyTableName){
        return withSchemaName(schemaName)
                .withHistoryTableName(historyTableName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $DataConsistencyCheck$On(){
        return withUseDataConsistencyCheck(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $DataConsistencyCheck$Off(){
        return withUseDataConsistencyCheck(false);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $HistoryRetentionPeriod$Infinite(){
        return withUseHistoryRetentionPeriod(true);
    }

    /**
     * set
     * @param historyRetentionPeriodNumber history retention period number
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $HistoryRetentionPeriod(Integer historyRetentionPeriodNumber){
        return withUseHistoryRetentionPeriod(false)
                .withHistoryRetentionPeriodNumber(historyRetentionPeriodNumber);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $Day(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.DAY);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $Days(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.DAYS);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $Week(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.WEEK);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $Weeks(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.WEEKS);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $Month(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.MONTH);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $Months(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.MONTHS);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $Year(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.YEAR);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SetBuilder<ParentBuilder> $Years(){
        return withHistoryRetentionPeriodUnit(Set.HistoryRetentionPeriodUnit.YEARS);
    }

}
