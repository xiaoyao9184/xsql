package com.xy.xsql.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.statements.alter.table.AlterTrigger;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AlterTriggerBuilder<ParentBuilder>
        extends CodeTreeBuilder<AlterTriggerBuilder<ParentBuilder>,ParentBuilder,AlterTrigger> {

    public AlterTriggerBuilder(AlterTrigger target) {
        super(target);
    }

    public AlterTriggerBuilder<ParentBuilder> withUseEnable(boolean useEnable){
        target.setUseEnable(useEnable);
        return this;
    }

    public AlterTriggerBuilder<ParentBuilder> withTriggerNames(List<String> triggerNames){
        target.setTriggerNames(triggerNames);
        return this;
    }

    public AlterTriggerBuilder<ParentBuilder> withTriggerNames(String... triggerNames){
        target.setTriggerNames(Arrays.asList(triggerNames));
        return this;
    }

    /*
    Quick
     */

    public AlterTriggerBuilder<ParentBuilder> $ENABLE(){
        return withUseEnable(true);
    }

    public AlterTriggerBuilder<ParentBuilder> $DISABLE(){
        return withUseEnable(false);
    }

    public AlterTriggerBuilder<ParentBuilder> $TRIGGER_ALL(){
        return this;
    }

    public AlterTriggerBuilder<ParentBuilder> $CONSTRAINT(String... triggerNames){
        return withTriggerNames(triggerNames);
    }

}
