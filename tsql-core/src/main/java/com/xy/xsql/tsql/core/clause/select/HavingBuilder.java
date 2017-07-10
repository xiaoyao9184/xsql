package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.clause.SearchConditionBuilder;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.select.Having;
import com.xy.xsql.tsql.model.predicate.Predicate;

import static com.xy.xsql.core.FiledBuilder.initSet;

/**
 * HavingBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
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
                (initSet(SearchCondition::new,
                        target::getSearchCondition,
                        target::setSearchCondition))
                .in(this);
    }


    /*
    Quick
    Same as WhereBuilder
     */

    /**
     * Quick set
     * @param predicate searchCondition.predicate
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $(Predicate predicate){
        return withSearchCondition()
                .$(predicate)
                .and();
    }

    /**
     * Quick set
     * @param predicate searchCondition.predicate
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $Not(Predicate predicate){
        return withSearchCondition()
                .$Not(predicate)
                .and();
    }

    /**
     * Quick in
     * for set searchCondition.searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> $(){
        SearchCondition searchCondition = new SearchCondition();
        withSearchCondition()
                .withSearchCondition(searchCondition)
                .and();

        return new SearchConditionBuilder<HavingBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in
     * for set searchCondition.searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> $Not(){

        SearchCondition searchCondition = new SearchCondition();
        withSearchCondition()
                .withNot()
                .withSearchCondition(searchCondition)
                .and();

        return new SearchConditionBuilder<HavingBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick set
     * into SearchConditionBuilder.AndOrNotItemBuilder get-out
     * @param predicate searchCondition.andOrNotItem.predicate
     * @return THIS
     */
    public HavingBuilder<ParentBuilder> $And(Predicate predicate) {
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
    public HavingBuilder<ParentBuilder> $Or(Predicate predicate) {
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
    public HavingBuilder<ParentBuilder> $And_Not(Predicate predicate) {
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
    public HavingBuilder<ParentBuilder> $Or_Not(Predicate predicate) {
        return withSearchCondition()
                .$Or_Not(predicate)
                .and();
    }

    /**
     * Quick in
     * for set searchCondition.andOrNotItem.searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> $And() {
        SearchCondition searchCondition = new SearchCondition();
        withSearchCondition()
                .withAndOrNotItem()
                    .withAnd()
                    .withSearchCondition(searchCondition)
                    .and()
                .and();

        return new SearchConditionBuilder<HavingBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in
     * for set searchCondition.andOrNotItem.searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> $Or() {
        SearchCondition searchCondition = new SearchCondition();
        withSearchCondition()
                .withAndOrNotItem()
                    .withOr()
                    .withSearchCondition(searchCondition)
                    .and()
                .and();

        return new SearchConditionBuilder<HavingBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in
     * for set searchCondition.andOrNotItem.searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> $And_Not() {
        SearchCondition searchCondition = new SearchCondition();
        withSearchCondition()
                .withAndOrNotItem()
                    .withNot(true)
                    .withAnd()
                    .withSearchCondition(searchCondition)
                    .and()
                .and();

        return new SearchConditionBuilder<HavingBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in
     * for set searchCondition.andOrNotItem.searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> $Or_Not() {
        SearchCondition searchCondition = new SearchCondition();
        withSearchCondition()
                .withAndOrNotItem()
                    .withNot(true)
                    .withOr()
                    .withSearchCondition(searchCondition)
                    .and()
                .and();

        return new SearchConditionBuilder<HavingBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }
}
