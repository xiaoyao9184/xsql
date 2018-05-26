package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.statements.alter.table.AlterTrigger;

import java.util.Arrays;
import java.util.List;

/**
 * AlterTriggerBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class AlterTriggerBuilder<ParentBuilder>
        extends ParentHoldBuilder<AlterTriggerBuilder<ParentBuilder>,ParentBuilder,AlterTrigger> {

    public AlterTriggerBuilder() {
        super(new AlterTrigger());
    }

    public AlterTriggerBuilder(AlterTrigger target) {
        super(target);
    }

    /**
     * set
     * @param useEnable enable
     * @return THIS
     */
    public AlterTriggerBuilder<ParentBuilder> withUseEnable(boolean useEnable){
        target.setUseEnable(useEnable);
        return this;
    }

    /**
     * set
     * @param triggerNames trigger name
     * @return THIS
     */
    public AlterTriggerBuilder<ParentBuilder> withTriggerNames(List<String> triggerNames){
        target.setTriggerNames(triggerNames);
        return this;
    }

    /**
     * set
     * @param triggerNames trigger name
     * @return THIS
     */
    public AlterTriggerBuilder<ParentBuilder> withTriggerNames(String... triggerNames){
        target.setTriggerNames(Arrays.asList(triggerNames));
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @return THIS
     */
    public AlterTriggerBuilder<ParentBuilder> $Enable(){
        return withUseEnable(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterTriggerBuilder<ParentBuilder> $Disable(){
        return withUseEnable(false);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterTriggerBuilder<ParentBuilder> $TriggerAll(){
        return this;
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterTriggerBuilder<ParentBuilder> $Constraint(String... triggerNames){
        return withTriggerNames(triggerNames);
    }

}
