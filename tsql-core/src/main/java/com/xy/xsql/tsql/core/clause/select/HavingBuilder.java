package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.clause.SearchConditionBuilder;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.select.Having;
import com.xy.xsql.tsql.model.predicate.Predicate;

import static com.xy.xsql.core.FiledBuilder.set;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class HavingBuilder<ParentBuilder>
        extends CodeTreeBuilder<HavingBuilder<ParentBuilder>,ParentBuilder,Having> {

    public HavingBuilder() {
        super(new Having());
    }

    public HavingBuilder(Having having) {
        super(having);
    }

    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<HavingBuilder<ParentBuilder>>
                (set(SearchCondition::new,
                        target::setSearchCondition))
                .in(this);
    }


    /*
        Same as WhereBuilder
     */

    /**
     * Quick inout set SearchConditionBuilder' predicate
     * @param predicate
     * @return
     */
    public HavingBuilder<ParentBuilder> $Predicate(Predicate predicate){
        return withSearchCondition()
                .$Predicate(predicate)
                .and();
    }

    /**
     * Quick inout set SearchConditionBuilder' searchCondition
     * @param searchCondition
     * @return
     */
    public HavingBuilder<ParentBuilder> $SearchCondition(SearchCondition searchCondition){
        return withSearchCondition()
                .$SearchCondition(searchCondition)
                .and();
    }

    /**
     * Quick inout set SearchConditionBuilder' predicate
     * @param predicate
     * @return
     */
    public HavingBuilder<ParentBuilder> $NotPredicate(Predicate predicate){
        return withSearchCondition()
                .$NotPredicate(predicate)
                .and();
    }

    /**
     * Quick inout set SearchConditionBuilder' searchCondition
     * @param searchCondition
     * @return
     */
    public HavingBuilder<ParentBuilder> $NotSearchCondition(SearchCondition searchCondition){
        return withSearchCondition()
                .$NotSearchCondition(searchCondition)
                .and();
    }


    /**
     * Quick inout set SearchConditionBuilder's AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public HavingBuilder<ParentBuilder> $_AndPredicate(Predicate predicate) {
        return withSearchCondition()
                .$_And(predicate)
                .and();
    }

    /**
     * Quick inout set SearchConditionBuilder's AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public HavingBuilder<ParentBuilder> $_OrPredicate(Predicate predicate) {
        return withSearchCondition()
                .$_OrPredicate(predicate)
                .and();
    }

    /**
     * Quick inout set SearchConditionBuilder's AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public HavingBuilder<ParentBuilder> $_AndNotPredicate(Predicate predicate) {
        return withSearchCondition()
                .$_AndNotPredicate(predicate)
                .and();
    }

    /**
     * Quick inout set SearchConditionBuilder's AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public HavingBuilder<ParentBuilder> $_OrNotPredicate(Predicate predicate) {
        return withSearchCondition()
                .$_OrNotPredicate(predicate)
                .and();
    }

    /**
     * Quick inout set SearchConditionBuilder's AndOrNotItem' predicate searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<HavingBuilder<ParentBuilder>>> $__AndSearchCondition() {
        return withSearchCondition()
                .$_AndSearchCondition();
    }

    /**
     * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<HavingBuilder<ParentBuilder>>> $__OrSearchCondition() {
        return withSearchCondition()
                .$_OrSearchCondition();
    }

    /**
     * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<HavingBuilder<ParentBuilder>>> $__AndNotSearchCondition() {
        return withSearchCondition()
                .$_AndNotSearchCondition();
    }

    /**
     * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<HavingBuilder<ParentBuilder>>> $__OrNotSearchCondition() {
        return withSearchCondition()
                .$_OrNotSearchCondition();
    }
}
