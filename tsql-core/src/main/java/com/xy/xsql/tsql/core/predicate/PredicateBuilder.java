package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.predicate.*;
import com.xy.xsql.tsql.model.statement.dml.Select;

/**
 * Abstract Predicate Builder
 *
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class PredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<PredicateBuilder<ParentBuilder>,ParentBuilder,Setter<Predicate>> {

    public PredicateBuilder(Setter<Predicate> setter) {
        super(setter);
    }

    /**
     * Confirm type of Predicate
     * @return
     */
    public ComparisonPredicateBuilder<ParentBuilder> _Comparison(){
        Comparison predicate = new Comparison();
        target.set(predicate);
        return new ComparisonPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return
     */
    public LikePredicateBuilder<ParentBuilder> _Like(){
        Like predicate = new Like();
        target.set(predicate);
        return new LikePredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return
     */
    public BetweenPredicateBuilder<ParentBuilder> _Between(){
        Between predicate = new Between();
        target.set(predicate);
        return new BetweenPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return
     */
    public IsNullPredicateBuilder<ParentBuilder> _IsNull(){
        IsNull predicate = new IsNull();
        target.set(predicate);
        return new IsNullPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return
     */
    public ContainsPredicateBuilder<ParentBuilder> _Contains(){
        Contains predicate = new Contains();
        target.set(predicate);
        return new ContainsPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return
     */
    public FreeTextPredicateBuilder<ParentBuilder> _FreeText(){
        FreeText predicate = new FreeText();
        target.set(predicate);
        return new FreeTextPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return
     */
    public InPredicateBuilder<ParentBuilder> _In(){
        In predicate = new In();
        target.set(predicate);
        return new InPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return
     */
    public ComparisonSubQueryPredicateBuilder<ParentBuilder> _All_Some_Any(){
        ComparisonSubQuery predicate = new ComparisonSubQuery();
        target.set(predicate);
        return new ComparisonSubQueryPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

    /**
     * Confirm type of Predicate
     * @return
     */
    public ExistsPredicateBuilder<ParentBuilder> _Exists(){
        Exists predicate = new Exists();
        target.set(predicate);
        return new ExistsPredicateBuilder<ParentBuilder>
                (predicate)
                .in(out());
    }

}
