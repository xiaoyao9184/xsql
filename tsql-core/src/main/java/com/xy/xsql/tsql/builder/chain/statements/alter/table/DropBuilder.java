package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.core.builder.parent.ParentHoldLazyConfigBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.tsql.model.statements.alter.table.Drop;
import com.xy.xsql.tsql.model.statements.alter.table.DropClusteredConstraintOption;

import java.util.*;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;

/**
 * DropBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused","TypeParameterHidesVisibleType"})
public class DropBuilder<ParentBuilder>
        extends ParentHoldBuilder<DropBuilder<ParentBuilder>,ParentBuilder,Drop> {

    public DropBuilder() {
        super(new Drop());
    }

    public DropBuilder(Drop target) {
        super(target);
    }

    /**
     * set
     * @param items DropItem
     * @return THIS
     */
    public DropBuilder<ParentBuilder> withItems(List<Drop.DropItem> items){
        target.setItems(items);
        return this;
    }

    /**
     * set
     * @param items DropItem
     * @return THIS
     */
    public DropBuilder<ParentBuilder> withItems(Drop.DropItem... items){
        target.setItems(Arrays.asList(items));
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick in
     * @return DropItemBuilder
     */
    public DropItemBuilder<DropBuilder<ParentBuilder>> $(){
        list(target::getItems, target::setItems).init();
        return new DropItemBuilder<DropBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), target.getItems()::add);
    }


    /**
     * Abstract DropItemBuilder
     * @param <ParentBuilder>
     */
    public class DropItemBuilder<ParentBuilder>
            extends ParentHoldLazyConfigBuilder<DropItemBuilder<ParentBuilder>,ParentBuilder,Drop.DropItem> {

        public DropItemBuilder() {}

        /**
         * Confirm type of DropItem
         * @return DropConstraintBuilder
         */
        public DropConstraintBuilder<ParentBuilder> _DropConstraint(){
            return new DropConstraintBuilder<ParentBuilder>
                    (object(Drop.DropConstraint::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of DropItem
         * @return DropColumnBuilder
         */
        public DropColumnBuilder<ParentBuilder> _DropColumn(){
            return new DropColumnBuilder<ParentBuilder>
                    (object(Drop.DropColumn::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of PeriodSystemTime
         * @return PARENT
         */
        public ParentBuilder _PeriodSystemTime(){
            Drop.PeriodSystemTime item = new Drop.PeriodSystemTime();
            this.init(item);
            return this.and();
        }




        /*
        Transform
         */

        /**
         * Transform to DropConstraint
         * @return DropConstraintBuilder
         */
        public DropConstraintBuilder<ParentBuilder> $Constraint(){
            return _DropConstraint();
        }

        /**
         * Transform to DropColumn
         * @return DropColumnBuilder
         */
        public DropColumnBuilder<ParentBuilder> $Column(){
            return _DropColumn();
        }

        /**
         * Transform to PeriodSystemTime
         * @return PARENT
         */
        public ParentBuilder $PeriodForSystemTime(){
            return _PeriodSystemTime();
        }

    }


    /**
     * DropConstraintBuilder
     * @param <ParentBuilder>
     */
    public static class DropConstraintBuilder<ParentBuilder>
            extends ParentHoldBuilder<DropConstraintBuilder<ParentBuilder>,ParentBuilder,Drop.DropConstraint> {

        public DropConstraintBuilder() {
            super(new Drop.DropConstraint());
        }
        
        public DropConstraintBuilder(Drop.DropConstraint target) {
            super(target);
        }

        /**
         * set
         * @param useConstraint constraint
         * @return THIS
         */
        public DropConstraintBuilder<ParentBuilder> withUseConstraint(boolean useConstraint) {
            target.setUseConstraint(useConstraint);
            return this;
        }

        /**
         * set
         * @param useIfExists if exists
         * @return THIS
         */
        public DropConstraintBuilder<ParentBuilder> withUseIfExists(boolean useIfExists) {
            target.setUseIfExists(useIfExists);
            return this;
        }

        /**
         * set
         * @param constraintNameWithOptionList DropClusteredConstraintOption
         * @return THIS
         */
        public DropConstraintBuilder<ParentBuilder> withConstraintNameWithOptionList(List<Map.Entry<String,List<DropClusteredConstraintOption>>> constraintNameWithOptionList) {
            target.setConstraintNameWithOptionList(constraintNameWithOptionList);
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set
         * @return THIS
         */
        public DropConstraintBuilder<ParentBuilder> $Constraint() {
            return withUseConstraint(true);
        }

        /**
         * Quick set
         * @return THIS
         */
        public DropConstraintBuilder<ParentBuilder> $IfExists() {
            return withUseIfExists(true);
        }

        /**
         * Quick in
         * @param constraintName constraint name
         * @return THIS
         */
        public ConstraintNameWithOptionOptionBuilder<DropConstraintBuilder<ParentBuilder>> $(String constraintName){
            Map.Entry<String,List<DropClusteredConstraintOption>> kv =
                    list(target::getConstraintNameWithOptionList, target::setConstraintNameWithOptionList)
                    .add(new AbstractMap.SimpleEntry<>(null, null));
            return new ConstraintNameWithOptionOptionBuilder<DropConstraintBuilder<ParentBuilder>>
                    (kv)
                    .in(this);
        }

    }


    /**
     * DropColumnBuilder
     * @param <ParentBuilder>
     */
    public static class DropColumnBuilder<ParentBuilder>
            extends ParentHoldBuilder<DropColumnBuilder<ParentBuilder>,ParentBuilder,Drop.DropColumn> {
        
        public DropColumnBuilder() {
            super(new Drop.DropColumn());
        }
        
        public DropColumnBuilder(Drop.DropColumn target) {
            super(target);
        }

        /**
         * set
         * @param useIfExists if exists
         * @return THIS
         */
        public DropColumnBuilder<ParentBuilder> withUseIfExists(boolean useIfExists) {
            target.setUseIfExists(useIfExists);
            return this;
        }

        /**
         * set
         * @param names name
         * @return THIS
         */
        public DropColumnBuilder<ParentBuilder> withItems(List<String> names){
            target.setColumnNames(names);
            return this;
        }

        /**
         * set
         * @param names name
         * @return THIS
         */
        public DropColumnBuilder<ParentBuilder> withItems(String... names){
            target.setColumnNames(Arrays.asList(names));
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set
         * @return THIS
         */
        public DropColumnBuilder<ParentBuilder> $IfExists() {
            return withUseIfExists(true);
        }

        /**
         * Quick set
         * @return THIS
         */
        public DropColumnBuilder<ParentBuilder> $(String... names) {
            return withItems(names);
        }

    }


    /**
     * DropConstraintBuilder
     * @param <ParentBuilder>
     */
    public static class ConstraintNameWithOptionOptionBuilder<ParentBuilder>
            extends ParentHoldBuilder<ConstraintNameWithOptionOptionBuilder<ParentBuilder>,ParentBuilder,Map.Entry<String,List<DropClusteredConstraintOption>>> {

        public ConstraintNameWithOptionOptionBuilder(Map.Entry<String,List<DropClusteredConstraintOption>> target) {
            super(target);
        }

        public ConstraintNameWithOptionOptionBuilder(String constraintName) {
            super(new AbstractMap.SimpleEntry<>(constraintName, new LinkedList<>()));
        }

        /**
         * set
         * @param dropClusteredConstraintOptions DropClusteredConstraintOption
         * @return THIS
         */
        public ConstraintNameWithOptionOptionBuilder<ParentBuilder> with(DropClusteredConstraintOption... dropClusteredConstraintOptions) {
            target.setValue(Arrays.asList(dropClusteredConstraintOptions));
            return this;
        }

        /**
         * set
         * @param dropClusteredConstraintOptionList DropClusteredConstraintOption
         * @return THIS
         */
        public ConstraintNameWithOptionOptionBuilder<ParentBuilder> with(List<DropClusteredConstraintOption> dropClusteredConstraintOptionList) {
            target.setValue(dropClusteredConstraintOptionList);
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set
         * @return THIS
         */
        public DropClusteredConstraintOptionBuilder<ConstraintNameWithOptionOptionBuilder<ParentBuilder>> $With() {
            return new DropClusteredConstraintOptionBuilder<ConstraintNameWithOptionOptionBuilder<ParentBuilder>>
                    (list(target::getValue, target::setValue)
                            .addNew(DropClusteredConstraintOption::new))
                    .in(this);
        }

    }

}
