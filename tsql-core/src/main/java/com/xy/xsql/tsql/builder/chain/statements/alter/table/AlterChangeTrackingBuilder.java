package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.statements.alter.table.AlterChangeTracking;

/**
 * AlterChangeTrackingBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class AlterChangeTrackingBuilder<ParentBuilder>
        extends ParentHoldBuilder<AlterChangeTrackingBuilder<ParentBuilder>,ParentBuilder,AlterChangeTracking> {

    public AlterChangeTrackingBuilder() {
        super(new AlterChangeTracking());
    }

    public AlterChangeTrackingBuilder(AlterChangeTracking target) {
        super(target);
    }

    /**
     * set
     * @param useEnable enable
     * @return THIS
     */
    public AlterChangeTrackingBuilder<ParentBuilder> withUseEnable(boolean useEnable){
        target.setUseEnable(useEnable);
        return this;
    }

    /**
     * set
     * @param useTrackUpdatedOn track updated on
     * @return THIS
     */
    public AlterChangeTrackingBuilder<ParentBuilder> withUseTrackUpdatedOn(Boolean useTrackUpdatedOn){
        target.setUseTrackUpdatedOn(useTrackUpdatedOn);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @return THIS
     */
    public AlterChangeTrackingBuilder<ParentBuilder> $Enable(){
        return withUseEnable(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterChangeTrackingBuilder<ParentBuilder> $Disable(){
        return withUseEnable(false);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterChangeTrackingBuilder<ParentBuilder> $ChangeTracking$With$TrackColumnsUpdated$On(){
        return withUseTrackUpdatedOn(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterChangeTrackingBuilder<ParentBuilder> $ChangeTracking$With$TrackColumnsUpdated$Off(){
        return withUseTrackUpdatedOn(false);
    }

}
