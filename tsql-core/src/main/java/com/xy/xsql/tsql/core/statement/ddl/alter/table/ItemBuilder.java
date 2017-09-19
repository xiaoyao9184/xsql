package com.xy.xsql.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.core.element.index.PartitionBuilder;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.*;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class ItemBuilder<ParentBuilder>
        extends CodeTreeBuilder<ItemBuilder<ParentBuilder>,ParentBuilder,Setter<Item>> {

    public ItemBuilder(Setter<Item> target) {
        super(target);
    }

    public AlterColumnBuilder<ParentBuilder> _AlterColumn(){
        AlterColumn item = new AlterColumn();
        target.set(item);
        return new AlterColumnBuilder<ParentBuilder>
                (item)
                .in(out());
    }

    public AddBuilder<ParentBuilder> _Add(){
        Add item = new Add();
        target.set(item);
        return new AddBuilder<ParentBuilder>
                (item)
                .in(out());
    }

    public DropBuilder<ParentBuilder> _Drop(){
        Drop item = new Drop();
        target.set(item);
        return new DropBuilder<ParentBuilder>
                (item)
                .in(out());
    }

    public AlterConstraintBuilder<ParentBuilder> _Constraint(){
        AlterConstraint item = new AlterConstraint();
        target.set(item);
        return new AlterConstraintBuilder<ParentBuilder>
                (item)
                .in(out());
    }

    public AlterTriggerBuilder<ParentBuilder> _Trigger(){
        AlterTrigger item = new AlterTrigger();
        target.set(item);
        return new AlterTriggerBuilder<ParentBuilder>
                (item)
                .in(out());
    }

    public AlterChangeTrackingBuilder<ParentBuilder> _ChangeTracking(){
        AlterChangeTracking item = new AlterChangeTracking();
        target.set(item);
        return new AlterChangeTrackingBuilder<ParentBuilder>
                (item)
                .in(out());
    }

    public AlterPartitionBuilder<ParentBuilder> _Partition(){
        AlterPartition item = new AlterPartition();
        target.set(item);
        return new AlterPartitionBuilder<ParentBuilder>
                (item)
                .in(out());
    }

    public SetBuilder<ParentBuilder> _Set(){
        Set item = new Set();
        target.set(item);
        return new SetBuilder<ParentBuilder>
                (item)
                .in(out());
    }

    public RebuildBuilder<ParentBuilder> _Rebuild(){
        Rebuild item = new Rebuild();
        target.set(item);
        return new RebuildBuilder<ParentBuilder>
                (item)
                .in(out());
    }

    public AlterTableOptionBuilder<ParentBuilder> _TableOption(){
        AlterTableOption item = new AlterTableOption();
        target.set(item);
        return new AlterTableOptionBuilder<ParentBuilder>
                (item)
                .in(out());
    }

    public AlterFiletableOptionBuilder<ParentBuilder> _FiletableOption(){
        AlterFiletableOption item = new AlterFiletableOption();
        target.set(item);
        return new AlterFiletableOptionBuilder<ParentBuilder>
                (item)
                .in(out());
    }

    public StretchConfigurationBuilder<ParentBuilder> _StretchConfiguration(){
        StretchConfiguration item = new StretchConfiguration();
        target.set(item);
        return new StretchConfigurationBuilder<ParentBuilder>
                (item)
                .in(out());
    }


    /*
    Transform
     */

    /**
     * Transform to AlterColumn
     * @param columnName columnName
     * @return AlterColumnBuilder
     */
    public AlterColumnBuilder<ParentBuilder> $ALTER_COLUMN(String columnName){
        return _AlterColumn()
                .withColumnName(columnName);
    }

    /**
     * Transform to AlterColumn
     * @return AddBuilder
     */
    public AddBuilder<ParentBuilder> $ADD(){
        return _Add();
    }

    /**
     * Transform to AlterColumn
     * @return DropBuilder
     */
    public DropBuilder<ParentBuilder> $DROP(){
        return _Drop();
    }

    private boolean useWith;

    /**
     * Temp
     * @return THIS
     */
    public ItemBuilder<ParentBuilder> $WITH(){
        useWith = true;
        return this;
    }

    /**
     * Transform to AlterColumn
     * @return AlterConstraintBuilder
     */
    public AlterConstraintBuilder<ParentBuilder> $CHECK(){
        return _Constraint()
                .withUseWith(useWith);
    }

    /**
     * Transform to AlterColumn
     * @return AlterConstraintBuilder
     */
    public AlterConstraintBuilder<ParentBuilder> $NOCHECK(){
        return _Constraint();
    }

    /**
     * Transform to AlterPartition
     * @return AlterPartitionBuilder
     */
    public AlterPartitionBuilder<ParentBuilder> $SWITCH(){
        return _Partition();
    }

    /**
     * Transform to Rebuild
     * @return RebuildBuilder
     */
    public RebuildBuilder<ParentBuilder> $REBUILD(){
        return _Rebuild();
    }

    /**
     * Transform to ?
     * @return EnableTransformBuilder
     */
    public EnableTransformBuilder<ParentBuilder> $ENABLE(){
        return new EnableTransformBuilder<ParentBuilder>
                (target)
                .in(out())
                .withUseEnable(true);
    }

    /**
     * Transform to ?
     * @return EnableTransformBuilder
     */
    public EnableTransformBuilder<ParentBuilder> $DISABLE(){
        return new EnableTransformBuilder<ParentBuilder>
                (target)
                .in(out())
                .withUseEnable(false);
    }

    /**
     * Transform to ?
     * @return SetTransformBuilder
     */
    public SetTransformBuilder<ParentBuilder> $SET(){
        return new SetTransformBuilder<ParentBuilder>
                (target)
                .in(out());
    }



    /**
     * EnableTransformBuilder
     * @param <ParentBuilder>
     */
    public static class EnableTransformBuilder<ParentBuilder>
            extends CodeTreeBuilder<EnableTransformBuilder<ParentBuilder>,ParentBuilder,Setter<Item>> {

        private boolean useEnable;

        public EnableTransformBuilder(Setter<Item> setter) {
            super(setter);
        }

        public EnableTransformBuilder<ParentBuilder> withUseEnable(boolean useEnable) {
            this.useEnable = useEnable;
            return this;
        }

        public AlterTriggerBuilder<ParentBuilder> _Trigger(){
            AlterTrigger item = new AlterTrigger();
            target.set(item);
            return new AlterTriggerBuilder<ParentBuilder>
                    (item)
                    .in(out());
        }

        public AlterChangeTrackingBuilder<ParentBuilder> _ChangeTracking(){
            AlterChangeTracking item = new AlterChangeTracking();
            target.set(item);
            return new AlterChangeTrackingBuilder<ParentBuilder>
                    (item)
                    .in(out());
        }

        public AlterFiletableOptionBuilder<ParentBuilder> _AlterFiletableOption(){
            AlterFiletableOption item = new AlterFiletableOption();
            target.set(item);
            return new AlterFiletableOptionBuilder<ParentBuilder>
                    (item)
                    .in(out());
        }

        /*
        Transform
         */

        /**
         * Transform to AlterTrigger
         * @return ParentBuilder
         */
        public ParentBuilder $TRIGGER_$ALL(){
            return _Trigger()
                    .withUseEnable(useEnable)
                    .out();
        }

        /**
         * Transform to AlterTrigger
         * @param triggerNames triggerNames
         * @return AlterTriggerBuilder
         */
        public AlterTriggerBuilder<ParentBuilder> $TRIGGER(String... triggerNames){
            return _Trigger()
                    .withUseEnable(useEnable)
                    .withTriggerNames(triggerNames);
        }

        /**
         * Transform to AlterChangeTracking
         * @return AlterChangeTrackingBuilder
         */
        public AlterChangeTrackingBuilder<ParentBuilder> $CHANGE_TRACKING(){
            return _ChangeTracking()
                    .withUseEnable(useEnable);
        }

        /**
         * Transform to AlterChangeTracking
         * @return AlterChangeTrackingBuilder
         */
        public AlterChangeTrackingBuilder<ParentBuilder> $CHANGE_TRACKING_$WITH_$TRACK_COLUMNS_UPDATED_$ON(){
            return _ChangeTracking()
                    .withUseEnable(useEnable)
                    .withUseTrackUpdatedOn(true);
        }

        /**
         * Transform to AlterChangeTracking
         * @return AlterChangeTrackingBuilder
         */
        public AlterChangeTrackingBuilder<ParentBuilder> $CHANGE_TRACKING_$WITH_$TRACK_COLUMNS_UPDATED_$OFF(){
            return _ChangeTracking()
                    .withUseEnable(useEnable)
                    .withUseTrackUpdatedOn(false);
        }

        /**
         * Transform to AlterFiletableOption
         * @return AlterFiletableOptionBuilder
         */
        public AlterFiletableOptionBuilder<ParentBuilder> $FILETABLE_NAMESPACE(){
            return _AlterFiletableOption()
                    .withUseEnableFileTableNamespace(true);
        }
    }

    /**
     * SetTransformBuilder
     * @param <ParentBuilder>
     */
    public static class SetTransformBuilder<ParentBuilder>
            extends CodeTreeBuilder<SetTransformBuilder<ParentBuilder>,ParentBuilder,Setter<Item>> {


        public SetTransformBuilder(Setter<Item> setter) {
            super(setter);
        }

        public SetBuilder<ParentBuilder> _Set(){
            Set item = new Set();
            target.set(item);
            return new SetBuilder<ParentBuilder>
                    (item)
                    .in(out());
        }

        public AlterTableOptionBuilder<ParentBuilder> _TableOption(){
            AlterTableOption item = new AlterTableOption();
            target.set(item);
            return new AlterTableOptionBuilder<ParentBuilder>
                    (item)
                    .in(out());
        }

        public AlterFiletableOptionBuilder<ParentBuilder> _FiletableOption(){
            AlterFiletableOption item = new AlterFiletableOption();
            target.set(item);
            return new AlterFiletableOptionBuilder<ParentBuilder>
                    (item)
                    .in(out());
        }

        public StretchConfigurationBuilder<ParentBuilder> _StretchConfiguration(){
            StretchConfiguration item = new StretchConfiguration();
            target.set(item);
            return new StretchConfigurationBuilder<ParentBuilder>
                    (item)
                    .in(out());
        }

        /*
        Transform
         */

        /**
         * Transform to Set
         * @return ParentBuilder
         */
        public PartitionBuilder<SetBuilder<ParentBuilder>> $FILESTREAM_ON(){
            return _Set()
                    .$FILESTREAM_ON();
        }

        /**
         * Transform to Set
         * @return SetBuilder
         */
        public SetBuilder<ParentBuilder> $SYSTEM_VERSIONING(){
            return _Set()
                    .$SYSTEM_VERSIONING();
        }

        /**
         * Transform to AlterTableOption
         * @return AlterTableOptionBuilder
         */
        public AlterTableOptionBuilder<ParentBuilder> $LOCK_ESCALATION(){
            return _TableOption();
        }

        /**
         * Transform to AlterFiletableOption
         * @return AlterFiletableOptionBuilder
         */
        public AlterFiletableOptionBuilder<ParentBuilder> $FILETABLE_DIRECTORY(){
            return _FiletableOption();
        }

        /**
         * Transform to StretchConfiguration
         * @return StretchConfigurationBuilder
         */
        public StretchConfigurationBuilder<ParentBuilder> $REMOTE_DATA_ARCHIVE(){
            return _StretchConfiguration();
        }

    }

}
