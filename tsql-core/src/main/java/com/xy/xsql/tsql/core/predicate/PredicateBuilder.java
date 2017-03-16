package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.predicate.*;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class PredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<PredicateBuilder<ParentBuilder>,ParentBuilder,Predicate> {

    public PredicateBuilder() {
        super(null);
    }

    public PredicateBuilder(Predicate predicate) {
        super(predicate);
    }

    public ComparisonPredicateBuilder<ParentBuilder> Operator(){
        Comparison predicate = new Comparison();
        tar = predicate;
        return new ComparisonPredicateBuilder<ParentBuilder>(predicate).in(out());
    }

    public LikePredicateBuilder<ParentBuilder> Like(){
        Like predicate = new Like();
        tar = predicate;
        return new LikePredicateBuilder<ParentBuilder>(predicate).in(out());
    }

    public BetweenPredicateBuilder<ParentBuilder> Between(){
        Between predicate = new Between();
        tar = predicate;
        return new BetweenPredicateBuilder<ParentBuilder>(predicate).in(out());
    }

    public IsNullPredicateBuilder<ParentBuilder> IsNull(){
        IsNull predicate = new IsNull();
        tar = predicate;
        return new IsNullPredicateBuilder<ParentBuilder>(predicate).in(out());
    }

    public ContainsPredicateBuilder<ParentBuilder> Contains(){
        Contains predicate = new Contains();
        tar = predicate;
        return new ContainsPredicateBuilder<ParentBuilder>(predicate).in(out());
    }

    public FreeTextPredicateBuilder<ParentBuilder> FreeText(){
        FreeText predicate = new FreeText();
        tar = predicate;
        return new FreeTextPredicateBuilder<ParentBuilder>(predicate).in(out());
    }

    public InPredicateBuilder<ParentBuilder> In(){
        In predicate = new In();
        tar = predicate;
        return new InPredicateBuilder<ParentBuilder>(predicate).in(out());
    }

    public ASAPredicateBuilder<ParentBuilder> All_Some_Any(){
        ComparisonSubQuery predicate = new ComparisonSubQuery();
        tar = predicate;
        return new ASAPredicateBuilder<ParentBuilder>(predicate).in(out());
    }

    public ExistsPredicateBuilder<ParentBuilder> Exists(){
        Exists predicate = new Exists();
        tar = predicate;
        return new ExistsPredicateBuilder<ParentBuilder>(predicate).in(out());
    }






}
