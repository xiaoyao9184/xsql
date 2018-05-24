package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.PartitionBuilder;
import com.xy.xsql.tsql.model.statements.alter.table.*;

import static com.xy.xsql.core.FiledBuilder.initSet;

/**
 * ItemBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ItemBuilder<ParentBuilder>
        extends CodeTreeBuilder<ItemBuilder<ParentBuilder>,ParentBuilder,Setter<Item>> {

    public ItemBuilder(Setter<Item> target) {
        super(target);
    }

    /**
     * Confirm type of Item
     * @return AlterColumnBuilder
     */
    public AlterColumnBuilder<ParentBuilder> _AlterColumn(){
        return new AlterColumnBuilder<ParentBuilder>
                (initSet(AlterColumn::new,
                        Getter.empty(),
                        target::set))
                .in(out());
    }

    /**
     * Confirm type of Item
     * @return AddBuilder
     */
    public AddBuilder<ParentBuilder> _Add(){
        return new AddBuilder<ParentBuilder>
                (initSet(Add::new,
                        Getter.empty(),
                        target::set))
                .in(out());
    }

    /**
     * Confirm type of Item
     * @return DropBuilder
     */
    public DropBuilder<ParentBuilder> _Drop(){
        return new DropBuilder<ParentBuilder>
                (initSet(Drop::new,
                        Getter.empty(),
                        target::set))
                .in(out());
    }

    /**
     * Confirm type of Item
     * @return AlterConstraintBuilder
     */
    public AlterConstraintBuilder<ParentBuilder> _Constraint(){
        return new AlterConstraintBuilder<ParentBuilder>
                (initSet(AlterConstraint::new,
                        Getter.empty(),
                        target::set))
                .in(out());
    }

    /**
     * Confirm type of Item
     * @return AlterTriggerBuilder
     */
    public AlterTriggerBuilder<ParentBuilder> _Trigger(){
        return new AlterTriggerBuilder<ParentBuilder>
                (initSet(AlterTrigger::new,
                        Getter.empty(),
                        target::set))
                .in(out());
    }

    /**
     * Confirm type of Item
     * @return THIS
     */
    public AlterChangeTrackingBuilder<ParentBuilder> _ChangeTracking(){
        return new AlterChangeTrackingBuilder<ParentBuilder>
                (initSet(AlterChangeTracking::new,
                        Getter.empty(),
                        target::set))
                .in(out());
    }

    /**
     * Confirm type of Item
     * @return AlterPartitionBuilder
     */
    public AlterPartitionBuilder<ParentBuilder> _Partition(){
        return new AlterPartitionBuilder<ParentBuilder>
                (initSet(AlterPartition::new,
                        Getter.empty(),
                        target::set))
                .in(out());
    }

    /**
     * Confirm type of Item
     * @return SetBuilder
     */
    public SetBuilder<ParentBuilder> _Set(){
        return new SetBuilder<ParentBuilder>
                (initSet(Set::new,
                        Getter.empty(),
                        target::set))
                .in(out());
    }

    /**
     * Confirm type of Item
     * @return RebuildBuilder
     */
    public RebuildBuilder<ParentBuilder> _Rebuild(){
        return new RebuildBuilder<ParentBuilder>
                (initSet(Rebuild::new,
                        Getter.empty(),
                        target::set))
                .in(out());
    }

    /**
     * Confirm type of Item
     * @return AlterTableOptionBuilder
     */
    public AlterTableOptionBuilder<ParentBuilder> _TableOption(){
        return new AlterTableOptionBuilder<ParentBuilder>
                (initSet(AlterTableOption::new,
                        Getter.empty(),
                        target::set))
                .in(out());
    }

    /**
     * Confirm type of Item
     * @return AlterFiletableOptionBuilder
     */
    public AlterFiletableOptionBuilder<ParentBuilder> _FiletableOption(){
        return new AlterFiletableOptionBuilder<ParentBuilder>
                (initSet(AlterFiletableOption::new,
                        Getter.empty(),
                        target::set))
                .in(out());
    }

    /**
     * Confirm type of Item
     * @return StretchConfigurationBuilder
     */
    public StretchConfigurationBuilder<ParentBuilder> _StretchConfiguration(){
        return new StretchConfigurationBuilder<ParentBuilder>
                (initSet(StretchConfiguration::new,
                        Getter.empty(),
                        target::set))
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
    public AlterColumnBuilder<ParentBuilder> $AlterColumn(String columnName){
        return _AlterColumn()
                .withColumnName(columnName);
    }

    /**
     * Transform to AlterColumn
     * @return AddBuilder
     */
    public AddBuilder<ParentBuilder> $Add(){
        return _Add();
    }

    /**
     * Transform to AlterColumn
     * @return DropBuilder
     */
    public DropBuilder<ParentBuilder> $Drop(){
        return _Drop();
    }

    private boolean useWith;

    /**
     * Temp
     * @return THIS
     */
    public ItemBuilder<ParentBuilder> $With(){
        useWith = true;
        return this;
    }

    /**
     * Transform to AlterColumn
     * @return AlterConstraintBuilder
     */
    public AlterConstraintBuilder<ParentBuilder> $Check(){
        return _Constraint()
                .withUseWith(useWith);
    }

    /**
     * Transform to AlterColumn
     * @return AlterConstraintBuilder
     */
    public AlterConstraintBuilder<ParentBuilder> $Nocheck(){
        return _Constraint();
    }

    /**
     * Transform to AlterPartition
     * @return AlterPartitionBuilder
     */
    public AlterPartitionBuilder<ParentBuilder> $Switch(){
        return _Partition();
    }

    /**
     * Transform to Rebuild
     * @return RebuildBuilder
     */
    public RebuildBuilder<ParentBuilder> $Rebuild(){
        return _Rebuild();
    }

    /**
     * Transform to ?
     * @return EnableTransformBuilder
     */
    public EnableTransformBuilder<ParentBuilder> $Enable(){
        return new EnableTransformBuilder<ParentBuilder>
                (target)
                .in(out())
                .withUseEnable(true);
    }

    /**
     * Transform to ?
     * @return EnableTransformBuilder
     */
    public EnableTransformBuilder<ParentBuilder> $Disable(){
        return new EnableTransformBuilder<ParentBuilder>
                (target)
                .in(out())
                .withUseEnable(false);
    }

    /**
     * Transform to ?
     * @return SetTransformBuilder
     */
    public SetTransformBuilder<ParentBuilder> $Set(){
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

        /**
         * set
         * @param useEnable enable
         * @return THIS
         */
        public EnableTransformBuilder<ParentBuilder> withUseEnable(boolean useEnable) {
            this.useEnable = useEnable;
            return this;
        }

        /**
         * Confirm type of Item
         * @return AlterTriggerBuilder
         */
        public AlterTriggerBuilder<ParentBuilder> _Trigger(){
            return new AlterTriggerBuilder<ParentBuilder>
                    (initSet(AlterTrigger::new,
                            Getter.empty(),
                            target::set))
                    .in(out());
        }

        /**
         * Confirm type of Item
         * @return AlterChangeTrackingBuilder
         */
        public AlterChangeTrackingBuilder<ParentBuilder> _ChangeTracking(){
            return new AlterChangeTrackingBuilder<ParentBuilder>
                    (initSet(AlterChangeTracking::new,
                            Getter.empty(),
                            target::set))
                    .in(out());
        }

        /**
         * Confirm type of Item
         * @return AlterFiletableOptionBuilder
         */
        public AlterFiletableOptionBuilder<ParentBuilder> _AlterFiletableOption(){
            return new AlterFiletableOptionBuilder<ParentBuilder>
                    (initSet(AlterFiletableOption::new,
                            Getter.empty(),
                            target::set))
                    .in(out());
        }




        /*
        Transform
         */

        /**
         * Transform to AlterTrigger
         * @return PARENT
         */
        public ParentBuilder $Trigger$All(){
            return _Trigger()
                    .withUseEnable(useEnable)
                    .out();
        }

        /**
         * Transform to AlterTrigger
         * @param triggerNames triggerNames
         * @return AlterTriggerBuilder
         */
        public AlterTriggerBuilder<ParentBuilder> $Trigger(String... triggerNames){
            return _Trigger()
                    .withUseEnable(useEnable)
                    .withTriggerNames(triggerNames);
        }

        /**
         * Transform to AlterChangeTracking
         * @return AlterChangeTrackingBuilder
         */
        public AlterChangeTrackingBuilder<ParentBuilder> $ChangeTracking(){
            return _ChangeTracking()
                    .withUseEnable(useEnable);
        }

        /**
         * Transform to AlterChangeTracking
         * @return AlterChangeTrackingBuilder
         */
        public AlterChangeTrackingBuilder<ParentBuilder> $ChangeTracking$With$TrackColumnsUpdated$On(){
            return _ChangeTracking()
                    .withUseEnable(useEnable)
                    .withUseTrackUpdatedOn(true);
        }

        /**
         * Transform to AlterChangeTracking
         * @return AlterChangeTrackingBuilder
         */
        public AlterChangeTrackingBuilder<ParentBuilder> $CHANGETRACKING$WITH$TRACKCOLUMNSUPDATED$OFF(){
            return _ChangeTracking()
                    .withUseEnable(useEnable)
                    .withUseTrackUpdatedOn(false);
        }

        /**
         * Transform to AlterFiletableOption
         * @return AlterFiletableOptionBuilder
         */
        public AlterFiletableOptionBuilder<ParentBuilder> $FiletableNamespace(){
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

        /**
         * Confirm type of Item
         * @return AlterTableOptionBuilder
         */
        public AlterTableOptionBuilder<ParentBuilder> _TableOption(){
            return new AlterTableOptionBuilder<ParentBuilder>
                    (initSet(AlterTableOption::new,
                            Getter.empty(),
                            target::set))
                    .in(out());
        }

        /**
         * Confirm type of Item
         * @return AlterFiletableOptionBuilder
         */
        public AlterFiletableOptionBuilder<ParentBuilder> _FiletableOption(){
            return new AlterFiletableOptionBuilder<ParentBuilder>
                    (initSet(AlterFiletableOption::new,
                            Getter.empty(),
                            target::set))
                    .in(out());
        }

        /**
         * Confirm type of Item
         * @return StretchConfigurationBuilder
         */
        public StretchConfigurationBuilder<ParentBuilder> _StretchConfiguration(){
            return new StretchConfigurationBuilder<ParentBuilder>
                    (initSet(StretchConfiguration::new,
                            Getter.empty(),
                            target::set))
                    .in(out());
        }




        /*
        Transform
         */

        /**
         * Transform to Set
         * @return PARENT
         */
        public PartitionBuilder<SetBuilder<ParentBuilder>> $FilestreamOn(){
            return _Set()
                    .$FilestreamOn();
        }

        /**
         * Transform to Set
         * @return SetBuilder
         */
        public SetBuilder<ParentBuilder> $SystemVersioning(){
            return _Set()
                    .$SystemVersioning();
        }

        /**
         * Transform to AlterTableOption
         * @return AlterTableOptionBuilder
         */
        public AlterTableOptionBuilder<ParentBuilder> $LockEscalation(){
            return _TableOption();
        }

        /**
         * Transform to AlterFiletableOption
         * @return AlterFiletableOptionBuilder
         */
        public AlterFiletableOptionBuilder<ParentBuilder> $FiletableDirectory(){
            return _FiletableOption();
        }

        /**
         * Transform to StretchConfiguration
         * @return StretchConfigurationBuilder
         */
        public StretchConfigurationBuilder<ParentBuilder> $RemoteDataArchive(){
            return _StretchConfiguration();
        }

    }

}
