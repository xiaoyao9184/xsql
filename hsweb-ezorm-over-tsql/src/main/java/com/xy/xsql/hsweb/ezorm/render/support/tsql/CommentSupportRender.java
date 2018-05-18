package com.xy.xsql.hsweb.ezorm.render.support.tsql;

import com.xy.xsql.jdbc.model.JSqlPlaceholderExpression;
import com.xy.xsql.model.param.ListParameterModel;
import com.xy.xsql.model.param.ParameterModel;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.queries.SearchCondition;
import com.xy.xsql.tsql.model.queries.Where;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.elements.expressions.UnknownExpression;
import com.xy.xsql.tsql.model.elements.operators.Operators;
import com.xy.xsql.tsql.model.queries.predicates.*;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.hsweb.commons.StringUtils;
import org.hsweb.ezorm.core.param.Param;
import org.hsweb.ezorm.core.param.SqlTerm;
import org.hsweb.ezorm.core.param.Term;
import org.hsweb.ezorm.core.param.TermType;
import org.hsweb.ezorm.rdb.meta.Correlation;
import org.hsweb.ezorm.rdb.meta.RDBColumnMetaData;
import org.hsweb.ezorm.rdb.meta.RDBTableMetaData;
import org.hsweb.ezorm.rdb.render.SqlRender;
import org.hsweb.ezorm.rdb.render.support.simple.CommonSqlRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.tsql.core.element.TableNameFactory.t;

/**
 * Created by xiaoyao9184 on 2017/9/14.
 */
public abstract class CommentSupportRender<R extends Param> extends CommonSqlRender<R> implements SqlRender<R> {

    PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance().getPropertyUtils();
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected RDBTableMetaData metaData;
    protected R param;


    public TableName getTableName(){
        TableName tableName = new TableName();
        tableName.setTableOrViewName(metaData.getName());
        return tableName;
    }

    protected String getTableAlias(RDBTableMetaData metaData, String field) {
        if (field.contains("."))
            field = field.split("[.]")[0];
        else return metaData.getAlias();
        Correlation correlation = metaData.getCorrelation(field);
        if (correlation != null) return correlation.getAlias();
        return metaData.getAlias();
    }


//    public List<ColumnName> getColumns(){
//
//
//        if (param.getIncludes().isEmpty() && param.getExcludes().isEmpty()) {
//            param.includes("*");
//            metaData.getCorrelations().forEach(correlation -> param.includes(correlation.getName() + ".*"));
//        }
//        //解析要查询的字段
//        this.selectField = parseOperationField(metaData, param);
//        //解析查询条件
//        buildWhere(metaData, "", param.getTerms(), whereSql, needSelectTable);
//        if (!whereSql.isEmpty()) whereSql.removeFirst();
//        //加入要查询的表
//        this.selectField.forEach(field -> needSelectTable.add(field.getTableName()));
//        param.getSorts().forEach(sort -> {
//            RDBColumnMetaData rDBColumnMetaData = metaData.findColumn(sort.getName());
//            if (rDBColumnMetaData.getName() == null) return;
//            String tableName = getTableAlias(metaData, sort.getName());
//            needSelectTable.add(tableName);
//            sort.setName(getDialect().buildColumnName(tableName, rDBColumnMetaData.getName()));
//            sorts.add(sort);
//        });
//    }

    public List<String> getNeedSelectTable(){
         return param.getTerms()
                .stream()
                .map(term -> {
                    boolean nullTerm = StringUtils.isNullOrEmpty(term.getColumn());
                    RDBColumnMetaData column = metaData.findColumn(term.getColumn());
                    if (!(term instanceof SqlTerm)) {
                        //不是空条件 也不是可选字段
                        if (!nullTerm && column == null) return null;
                        //不是空条件，值为空
                        if (!nullTerm && StringUtils.isNullOrEmpty(term.getValue())) return null;
                        //是空条件，但是无嵌套
                        if (nullTerm && term.getTerms().isEmpty()) return null;
                    } else {
                        if (StringUtils.isNullOrEmpty(((SqlTerm) term).getSql())) return null;
                    }
                    String tableAlias = null;
                    if (column != null) {
                        tableAlias = getTableAlias(metaData, term.getColumn());
                    }
                    return tableAlias;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public ListParameterModel<From.TableSource> getTableSource(List<String> needSelectTable){
        //生成join
        return needSelectTable.stream()
                .filter(table -> !table.equals(metaData.getName()) && metaData.getCorrelation(table) != null)
                .map(table -> metaData.getCorrelation(table))
                .sorted()
                .map(correlation -> {
                    From.BaseTable baseTable = new From.BaseTable();
                    baseTable.setTableName(t(correlation.getTargetTable()));
                    baseTable.setUseAs(true);
                    baseTable.setTableAlias(new Alias<>(correlation.getAlias()));

                    From.JoinType joinType = getJoinType(correlation.getJoin());

                    RDBTableMetaData metaTable = metaData.getDatabaseMetaData().getTableMetaData(correlation.getTargetTable());
                    ListParameterModel<SearchCondition> on = buildSearchCondition(metaTable,correlation.getTerms());

                    From.JoinedTable joinedTable = new From.JoinedTable();
                    joinedTable.setJoinType(joinType);
                    joinedTable.setTableSource2(baseTable);
                    joinedTable.setSearchCondition(on.getModel());
                    return new ListParameterModel<>(joinedTable,on.getParam());
                })
                .collect(
                        () -> {
                            From.BaseTable baseTable = new From.BaseTable();
                            baseTable.setTableName(t(metaData.getName()));
                            baseTable.setUseAs(true);
                            baseTable.setTableAlias(new Alias<>(metaData.getAlias()));
                            return new ListParameterModel<>(baseTable,new LinkedList<>());
                        },
                        (tableSource,joinedTable) -> {
                            joinedTable.getModel().setTableSource(tableSource.getModel());
                            tableSource.setModel(joinedTable.getModel());
                            tableSource.getParam().addAll(joinedTable.getParam());
                        },
                        (tableSource,tableSource2) -> {
                            throw new UnsupportedOperationException("TableSource not support merge!");
                        });
    }

    private Map<Correlation.JOIN,From.JoinTypeKeywords> joinMap = new HashMap<Correlation.JOIN,From.JoinTypeKeywords>(){
        {
            put(Correlation.JOIN.LEFT,From.JoinTypeKeywords.LEFT_JOIN);
            put(Correlation.JOIN.RIGHT,From.JoinTypeKeywords.RIGHT_JOIN);
            put(Correlation.JOIN.FULL,From.JoinTypeKeywords.FULL_JOIN);
            put(Correlation.JOIN.INNER,From.JoinTypeKeywords.INNER_JOIN);
        }
    };

    public From.JoinType getJoinType(Correlation.JOIN join){
        return new From.JoinType(joinMap.get(join));
    }

    public ListParameterModel<Where> getWhere(){
        Where where = new Where();
        ListParameterModel<SearchCondition> searchCondition = buildSearchCondition(metaData,param.getTerms());
        if(searchCondition.getModel() == null){
            return new ListParameterModel<>(null, searchCondition.getParam());
        }
        where.setSearchCondition(searchCondition.getModel());
        return new ListParameterModel<>(where, searchCondition.getParam());
    }

    private ListParameterModel<SearchCondition> buildSearchCondition(RDBTableMetaData metaData, List<Term> terms){
        List<ParameterModel<SearchCondition.AndOrNotItem,List<Object>>> list = terms.stream()
                .map(term -> {
                    //添加类型，and 或者 or
                    SearchCondition.AndOrNotItem andOrNotItem = new SearchCondition.AndOrNotItem();
                    List<Object> params = new ArrayList<>();
                    switch (term.getType()){
                        case and:
                            andOrNotItem.setUseAnd(true);
                            break;
                        case or:
                            andOrNotItem.setUseAnd(false);
                            break;
                    }

                    if (!term.getTerms().isEmpty()) {
                        //构建嵌套的条件
                        ListParameterModel<SearchCondition> searchCondition = buildSearchCondition(metaData,term.getTerms());
                        andOrNotItem.setSearchCondition(searchCondition.getModel());
                        params.addAll(searchCondition.getParam());
                    } else {
                        ListParameterModel<Predicate> predicate = buildPredicate(metaData,term);
                        andOrNotItem.setPredicate(predicate.getModel());
                        params.addAll(predicate.getParam());
                    }

                    return new ListParameterModel<>(andOrNotItem,params);
                })
                .filter(andOrNotItem -> andOrNotItem.getModel().getPredicate() != null || andOrNotItem.getModel().getSearchCondition() != null)
                .collect(Collectors.toList());

        List<SearchCondition.AndOrNotItem> items = list.stream()
                .map(ParameterModel::getModel)
                .collect(Collectors.toList());
        List<Object> params = list.stream()
                .flatMap(andOrNotItem -> andOrNotItem.getParam().stream())
                .collect(Collectors.toList());

        Optional<SearchCondition> searchCondition = items.stream()
                .findFirst()
                .map(andOrNotItem -> {
                    SearchCondition sc = new SearchCondition();
                    sc.setPredicate(andOrNotItem.getPredicate());
                    sc.setSearchCondition(andOrNotItem.getSearchCondition());
                    return sc;
                });
        if(searchCondition.isPresent()){
            items.remove(0);
            searchCondition.get().setAndOrList(items);

            //noinspection ConstantConditions
            return new ListParameterModel<>(searchCondition.get(),params);
        }else{
            return new ListParameterModel<>(null,params);
        }
    }

    private ListParameterModel<Predicate> buildPredicate(RDBTableMetaData metaData, Term term) {
        boolean nullTerm = StringUtils.isNullOrEmpty(term.getColumn());
        RDBColumnMetaData column = metaData.findColumn(term.getColumn());
        if (!(term instanceof SqlTerm)) {
            //不是空条件 也不是可选字段
            if (!nullTerm && column == null) return null;
            //不是空条件，值为空
            if (!nullTerm && StringUtils.isNullOrEmpty(term.getValue())) return null;
            //是空条件，但是无嵌套
            if (nullTerm && term.getTerms().isEmpty()) return null;
        } else {
            if (StringUtils.isNullOrEmpty(((SqlTerm) term).getSql())) return null;
        }
        String tableAlias = null;
        if (column != null) {
            tableAlias = getTableAlias(metaData, term.getColumn());
        }

        ColumnName columnName = new ColumnName();
        columnName.setName(column.getName());
        columnName.setTable(t(tableAlias));

        Predicate predicate = null;
        List<Object> params = new ArrayList<>();
        switch (term.getTermType()){
            case TermType.eq:
                Comparison eq = new Comparison();
                predicate = eq;
                eq.setExpression(columnName);
                eq.setOperator(Operators.EQUAL);
                eq.setOperatorExpression(new JSqlPlaceholderExpression());
                params.add(term.getValue());
                break;
            case TermType.not:
                Comparison not = new Comparison();
                predicate = not;
                not.setExpression(columnName);
                not.setOperator(Operators.NOT_EQUAL);
                not.setOperatorExpression(new JSqlPlaceholderExpression());
                params.add(term.getValue());
                break;
            case TermType.like:
                Like like = new Like();
                predicate = like;
                like.setExpression(columnName);
                like.setLikeExpression(new JSqlPlaceholderExpression());
                params.add(term.getValue());
                break;
            case TermType.nlike:
                Like nlike = new Like();
                predicate = nlike;
                nlike.setExpression(columnName);
                nlike.setUseNotOperator(true);
                nlike.setLikeExpression(new JSqlPlaceholderExpression());
                params.add(term.getValue());
                break;
            case TermType.gt:
                Comparison gt = new Comparison();
                predicate = gt;
                gt.setExpression(columnName);
                gt.setOperator(Operators.GREATER);
                gt.setOperatorExpression(new JSqlPlaceholderExpression());
                params.add(term.getValue());
                break;
            case TermType.gte:
                Comparison gte = new Comparison();
                predicate = gte;
                gte.setExpression(columnName);
                gte.setOperator(Operators.GREATER_EQUAL);
                gte.setOperatorExpression(new JSqlPlaceholderExpression());
                params.add(term.getValue());
                break;
            case TermType.lt:
                Comparison lt = new Comparison();
                predicate = lt;
                lt.setExpression(columnName);
                lt.setOperator(Operators.LESS);
                lt.setOperatorExpression(new JSqlPlaceholderExpression());
                params.add(term.getValue());
                break;
            case TermType.lte:
                Comparison lte = new Comparison();
                predicate = lte;
                lte.setExpression(columnName);
                lte.setOperator(Operators.LESS_EQUAL);
                lte.setOperatorExpression(new JSqlPlaceholderExpression());
                params.add(term.getValue());
                break;
            case TermType.in:
                In in = new In();
                predicate = in;
                in.setExpression(columnName);
                //noinspection Duplicates
                if(term.getValue() instanceof Object[]){
                    Object[] ps2 = (Object[]) term.getValue();
                    List<Object> ninParams = Stream.of(ps2)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());
                    in.setExpressionList(ninParams.stream().map(p -> new JSqlPlaceholderExpression()).collect(Collectors.toList()));
                    params.addAll(ninParams);
                }else{
                    in.setExpressionList(Collections.singletonList(new JSqlPlaceholderExpression()));
                    params.add(term.getValue());
                }
                break;
            case TermType.nin:
                In nin = new In();
                predicate = nin;
                nin.setUseNotOperator(true);
                nin.setExpression(columnName);
                //noinspection Duplicates
                if(term.getValue() instanceof Object[]){
                    Object[] ps2 = (Object[]) term.getValue();
                    List<Object> ninParams = Stream.of(ps2)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());
                    nin.setExpressionList(ninParams.stream().map(p -> new JSqlPlaceholderExpression()).collect(Collectors.toList()));
                    params.addAll(ninParams);
                }else{
                    nin.setExpressionList(Collections.singletonList(new JSqlPlaceholderExpression()));
                    params.add(term.getValue());
                }
                break;
            case TermType.empty:
                Comparison empty = new Comparison();
                predicate = empty;
                empty.setExpression(columnName);
                empty.setOperator(Operators.EQUAL);
                empty.setOperatorExpression(new UnknownExpression("''"));
                break;
            case TermType.nempty:
                Comparison nempty = new Comparison();
                predicate = nempty;
                nempty.setExpression(columnName);
                nempty.setOperator(Operators.NOT_EQUAL);
                nempty.setOperatorExpression(new UnknownExpression("''"));
                break;
            case TermType.isnull:
                IsNull isnull = new IsNull();
                predicate = isnull;
                isnull.setExpression(columnName);
                break;
            case TermType.notnull:
                IsNull notnull = new IsNull();
                predicate = notnull;
                notnull.setUseNotOperator(true);
                notnull.setExpression(columnName);
                break;
            case TermType.btw:
                Between btw = new Between();
                predicate = btw;
                btw.setExpression(columnName);
                btw.setStartExpression(new JSqlPlaceholderExpression());
                btw.setEndExpression(new JSqlPlaceholderExpression());
                List btwParams = (List) term.getValue();
                params.add(btwParams.get(0));
                params.add(btwParams.get(1));
                break;
            case TermType.nbtw:
                Between nbtw = new Between();
                predicate = nbtw;
                nbtw.setUseNotOperator(true);
                nbtw.setExpression(columnName);
                nbtw.setStartExpression(new JSqlPlaceholderExpression());
                nbtw.setEndExpression(new JSqlPlaceholderExpression());
                List nbtwParams = (List) term.getValue();
                params.add(nbtwParams.get(0));
                params.add(nbtwParams.get(1));
                break;
        }

        return new ListParameterModel<>(predicate,params);

    }


}
