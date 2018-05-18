package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.statements.alter.table.AlterChangeTracking;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AlterChangeTrackingBuilder<ParentBuilder>
        extends CodeTreeBuilder<AlterChangeTrackingBuilder<ParentBuilder>,ParentBuilder,AlterChangeTracking> {

    public AlterChangeTrackingBuilder(AlterChangeTracking target) {
        super(target);
    }

    public AlterChangeTrackingBuilder<ParentBuilder> withUseEnable(boolean useEnable){
        target.setUseEnable(useEnable);
        return this;
    }

    public AlterChangeTrackingBuilder<ParentBuilder> withUseTrackUpdatedOn(Boolean useTrackUpdatedOn){
        target.setUseTrackUpdatedOn(useTrackUpdatedOn);
        return this;
    }

    /*
    Quick
     */

    public AlterChangeTrackingBuilder<ParentBuilder> $ENABLE(){
        return withUseEnable(true);
    }

    public AlterChangeTrackingBuilder<ParentBuilder> $DISABLE(){
        return withUseEnable(false);
    }

    public AlterChangeTrackingBuilder<ParentBuilder> $CHANGE_TRACKING_$WITH_$TRACK_COLUMNS_UPDATED_$ON(){
        return withUseTrackUpdatedOn(true);
    }

    public AlterChangeTrackingBuilder<ParentBuilder> $CHANGE_TRACKING_$WITH_$TRACK_COLUMNS_UPDATED_$OFF(){
        return withUseTrackUpdatedOn(false);
    }

}
