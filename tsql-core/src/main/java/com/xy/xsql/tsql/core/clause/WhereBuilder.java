package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.Where;
import com.xy.xsql.tsql.model.predicate.Predicate;

import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * WhereBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class WhereBuilder<ParentBuilder>
        extends CodeTreeBuilder<WhereBuilder<ParentBuilder>,ParentBuilder,Where> {

    public WhereBuilder() {
        super(new Where());
    }

    public WhereBuilder(Where where) {
        super(where);
    }

    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (set(SearchCondition::new,
                        target::setSearchCondition))
                .in(this);
    }


    /*
    Quick set
     */

    /**
     * Quick set searchCondition.predicate
     * @param predicate
     * @return
     */
    public WhereBuilder<ParentBuilder> $Predicate(Predicate predicate){
        return withSearchCondition()
                .$Predicate(predicate)
                .and();
    }

    /**
     * Quick set searchCondition.predicate
     * @param predicate
     * @return
     */
    public WhereBuilder<ParentBuilder> $NotPredicate(Predicate predicate){
        return withSearchCondition()
                .$NotPredicate(predicate)
                .and();
    }




    /*
    Quick into
     */

    /**
     * Quick into SearchConditionBuilder
     * for set searchCondition.searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<WhereBuilder<ParentBuilder>>> $SearchCondition(){
        return withSearchCondition()
                .$SearchCondition();
    }

    /**
     * Quick into SearchConditionBuilder
     * for set searchCondition.searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<WhereBuilder<ParentBuilder>>> $NotSearchCondition(){
        return withSearchCondition()
                .$NotSearchCondition();
    }




    /*
    Quick set
     */

    /**
     * Quick set searchCondition.andOrNotItem.predicate
     * into SearchConditionBuilder.AndOrNotItemBuilder get-out
     * @param predicate
     * @return
     */
    public WhereBuilder<ParentBuilder> $_AndPredicate(Predicate predicate) {
        return withSearchCondition()
                .$_AndPredicate(predicate)
                .and();
    }

    /**
     * Quick set searchCondition.andOrNotItem.predicate
     * into SearchConditionBuilder.AndOrNotItemBuilder get-out
     * @param predicate
     * @return
     */
    public WhereBuilder<ParentBuilder> $_OrPredicate(Predicate predicate) {
        return withSearchCondition()
                .$_OrPredicate(predicate)
                .and();
    }

    /**
     * Quick set searchCondition.andOrNotItem.predicate
     * into SearchConditionBuilder.AndOrNotItemBuilder get-out
     * @param predicate
     * @return
     */
    public WhereBuilder<ParentBuilder> $_AndNotPredicate(Predicate predicate) {
        return withSearchCondition()
                .$_AndNotPredicate(predicate)
                .and();
    }

    /**
     * Quick set searchCondition.andOrNotItem.predicate
     * into SearchConditionBuilder.AndOrNotItemBuilder get-out
     * @param predicate
     * @return
     */
    public WhereBuilder<ParentBuilder> $_OrNotPredicate(Predicate predicate) {
        return withSearchCondition()
                .$_OrNotPredicate(predicate)
                .and();
    }




    /*
    Quick into
     */

    /**
     * Quick into SearchConditionBuilder
     * for set searchCondition.andOrNotItem.searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<WhereBuilder<ParentBuilder>>> $__AndSearchCondition() {
        return withSearchCondition()
                .$_AndSearchCondition();
    }

    /**
     * Quick into SearchConditionBuilder
     * for set searchCondition.andOrNotItem.searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<WhereBuilder<ParentBuilder>>> $__OrSearchCondition() {
        return withSearchCondition()
                .$_OrSearchCondition();
    }

    /**
     * Quick into SearchConditionBuilder
     * for set searchCondition.andOrNotItem.searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<WhereBuilder<ParentBuilder>>> $__AndNotSearchCondition() {
        return withSearchCondition()
                .$_AndNotSearchCondition();
    }

    /**
     * Quick into SearchConditionBuilder
     * for set searchCondition.andOrNotItem.searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<WhereBuilder<ParentBuilder>>> $__OrNotSearchCondition() {
        return withSearchCondition()
                .$_OrNotSearchCondition();
    }

}
