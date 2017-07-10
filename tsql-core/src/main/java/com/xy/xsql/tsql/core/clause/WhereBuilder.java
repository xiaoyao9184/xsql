package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.Where;
import com.xy.xsql.tsql.model.predicate.Predicate;

import static com.xy.xsql.core.FiledBuilder.initSet;

/**
 * WhereBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class WhereBuilder<ParentBuilder>
        extends CodeTreeBuilder<WhereBuilder<ParentBuilder>,ParentBuilder,Where> {

    public WhereBuilder() {
        super(new Where());
    }

    public WhereBuilder(Where where) {
        super(where);
    }

    /**
     * in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (initSet(SearchCondition::new,
                        target::getSearchCondition,
                        target::setSearchCondition))
                .in(this);
    }


    /*
    Quick
    Same as HavingBuilder
     */

    /**
     * Quick set
     * @param predicate searchCondition.predicate
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $(Predicate predicate){
        return withSearchCondition()
                .$(predicate)
                .and();
    }

    /**
     * Quick set
     * @param predicate searchCondition.predicate
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Not(Predicate predicate){
        return withSearchCondition()
                .$Not(predicate)
                .and();
    }

    /**
     * Quick in
     * for set searchCondition.searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $(){
        SearchCondition searchCondition = new SearchCondition();
        withSearchCondition()
                .withSearchCondition(searchCondition)
                .and();

        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in
     * for set searchCondition.searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $Not(){
        SearchCondition searchCondition = new SearchCondition();
        withSearchCondition()
                .withNot()
                .withSearchCondition(searchCondition)
                .and();

        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick set
     * into SearchConditionBuilder.AndOrNotItemBuilder get-out
     * @param predicate searchCondition.andOrNotItem.predicate
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $And(Predicate predicate) {
        return withSearchCondition()
                .$And(predicate)
                .and();
    }

    /**
     * Quick set
     * into SearchConditionBuilder.AndOrNotItemBuilder get-out
     * @param predicate searchCondition.andOrNotItem.predicate
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Or(Predicate predicate) {
        return withSearchCondition()
                .$Or(predicate)
                .and();
    }

    /**
     * Quick set
     * into SearchConditionBuilder.AndOrNotItemBuilder get-out
     * @param predicate searchCondition.andOrNotItem.predicate
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $And_Not(Predicate predicate) {
        return withSearchCondition()
                .$And_Not(predicate)
                .and();
    }

    /**
     * Quick set
     * into SearchConditionBuilder.AndOrNotItemBuilder get-out
     * @param predicate searchCondition.andOrNotItem.predicate
     * @return THIS
     */
    public WhereBuilder<ParentBuilder> $Or_Not(Predicate predicate) {
        return withSearchCondition()
                .$Or_Not(predicate)
                .and();
    }

    /**
     * Quick in
     * for set searchCondition.andOrNotItem.searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $And() {
        SearchCondition searchCondition = new SearchCondition();
        withSearchCondition()
                .withAndOrNotItem()
                    .withAnd()
                    .withSearchCondition(searchCondition)
                    .and()
                .and();

        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in
     * for set searchCondition.andOrNotItem.searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $Or() {
        SearchCondition searchCondition = new SearchCondition();
        withSearchCondition()
                .withAndOrNotItem()
                    .withOr()
                    .withSearchCondition(searchCondition)
                    .and()
                .and();

        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in
     * for set searchCondition.andOrNotItem.searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $And_Not() {
        SearchCondition searchCondition = new SearchCondition();
        withSearchCondition()
                .withAndOrNotItem()
                    .withNot(true)
                    .withAnd()
                    .withSearchCondition(searchCondition)
                    .and()
                .and();

        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in
     * for set searchCondition.andOrNotItem.searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> $Or_Not() {
        SearchCondition searchCondition = new SearchCondition();
        withSearchCondition()
                .withAndOrNotItem()
                    .withNot(true)
                    .withOr()
                    .withSearchCondition(searchCondition)
                    .and()
                .and();

        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

}
