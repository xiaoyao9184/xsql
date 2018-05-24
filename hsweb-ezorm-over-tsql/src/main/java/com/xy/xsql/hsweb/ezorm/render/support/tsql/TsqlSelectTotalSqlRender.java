package com.xy.xsql.hsweb.ezorm.render.support.tsql;

import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.model.param.ListParameterModel;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.queries.Where;
import com.xy.xsql.tsql.model.elements.expressions.UnknownExpression;
import com.xy.xsql.tsql.model.queries.Select;
import org.hsweb.ezorm.core.param.QueryParam;
import org.hsweb.ezorm.rdb.executor.SQL;
import org.hsweb.ezorm.rdb.meta.RDBTableMetaData;
import org.hsweb.ezorm.rdb.render.support.simple.SimpleSQL;

import java.util.List;

import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Query;

/**
 * Created by zhouhao on 16-5-17.
 */
public class TsqlSelectTotalSqlRender extends CommentSupportRender<QueryParam> {

    @Override
    public SQL render(RDBTableMetaData metaData, QueryParam param) {
        this.metaData = metaData;
        this.param = param;


        //解析要查询的表
        List<String> needSelectTable = getNeedSelectTable();

        ListParameterModel<From.TableSource> tableSourceListParameterModel = getTableSource(needSelectTable);
        List<Object> params = tableSourceListParameterModel.getParam();
        From.TableSource tableSource = tableSourceListParameterModel.getModel();

        ListParameterModel<Where> whereListParameterModel = getWhere();
        params.addAll(whereListParameterModel.getParam());
        Where where = whereListParameterModel.getModel();

        Select.QuerySpecification query = $Query()
                .$(new UnknownExpression("count(0)"),"total")
                .$From()
                    .withItem(tableSource)
                    .and()
                .withWhere(where)
                .build();

        String sqlString = BlockManager.INSTANCE.print(query);
        return new SimpleSQL(sqlString, params);
    }
}
