package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.model.statements.alter.table.Drop;
import com.xy.xsql.tsql.model.statements.alter.table.DropClusteredConstraintOption;

import java.util.*;

import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * DropBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused","TypeParameterHidesVisibleType"})
public class DropBuilder<ParentBuilder>
        extends CodeTreeBuilder<DropBuilder<ParentBuilder>,ParentBuilder,Drop> {

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
        return new DropItemBuilder<DropBuilder<ParentBuilder>>
                ((dropItem) -> initAdd(dropItem,
                        target::getItems,
                        target::setItems))
                .in(this);
    }


    /**
     * Abstract DropItemBuilder
     * @param <ParentBuilder>
     */
    public class DropItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<DropItemBuilder<ParentBuilder>,ParentBuilder,Setter<Drop.DropItem>> {

        public DropItemBuilder(Setter<Drop.DropItem> target) {
            super(target);
        }

        /**
         * Confirm type of DropItem
         * @return DropConstraintBuilder
         */
        public DropConstraintBuilder<ParentBuilder> _DropConstraint(){
            Drop.DropConstraint item = new Drop.DropConstraint();
            target.set(item);
            return new DropConstraintBuilder<ParentBuilder>
                    (item)
                    .in(out());
        }

        /**
         * Confirm type of DropItem
         * @return DropColumnBuilder
         */
        public DropColumnBuilder<ParentBuilder> _DropColumn(){
            Drop.DropColumn item = new Drop.DropColumn();
            target.set(item);
            return new DropColumnBuilder<ParentBuilder>
                    (item)
                    .in(out());
        }

        /**
         * Confirm type of PeriodSystemTime
         * @return PARENT
         */
        public ParentBuilder _PeriodSystemTime(){
            Drop.PeriodSystemTime item = new Drop.PeriodSystemTime();
            target.set(item);
            return out();
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
            extends CodeTreeBuilder<DropConstraintBuilder<ParentBuilder>,ParentBuilder,Drop.DropConstraint> {

        public DropConstraintBuilder(Drop.DropConstraint tar) {
            super(tar);
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
            return new ConstraintNameWithOptionOptionBuilder<DropConstraintBuilder<ParentBuilder>>
                    (constraintName)
                    .enter(this,r -> initAdd(r,
                            target::getConstraintNameWithOptionList,
                            target::setConstraintNameWithOptionList));
        }

    }


    /**
     * DropColumnBuilder
     * @param <ParentBuilder>
     */
    public static class DropColumnBuilder<ParentBuilder>
            extends CodeTreeBuilder<DropColumnBuilder<ParentBuilder>,ParentBuilder,Drop.DropColumn> {

        public DropColumnBuilder(Drop.DropColumn tar) {
            super(tar);
        }

        public DropColumnBuilder() {
            super(new Drop.DropColumn());
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
            extends CodeTreeBuilder<ConstraintNameWithOptionOptionBuilder<ParentBuilder>,ParentBuilder,Map.Entry<String,List<DropClusteredConstraintOption>>> {

        public ConstraintNameWithOptionOptionBuilder(Map.Entry<String,List<DropClusteredConstraintOption>> tar) {
            super(tar);
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
                    (initAdd(new DropClusteredConstraintOption(),
                            target::getValue,
                            target::setValue))
                    .in(this);
        }

    }

}
