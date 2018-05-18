package com.xy.xsql.hsweb.ezorm.render.support.tsql;

import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.model.param.ListParameterModel;
import com.xy.xsql.tsql.model.queries.Where;
import com.xy.xsql.tsql.model.statements.Delete;
import org.hsweb.ezorm.core.param.Param;
import org.hsweb.ezorm.rdb.executor.SQL;
import org.hsweb.ezorm.rdb.meta.RDBTableMetaData;
import org.hsweb.ezorm.rdb.render.support.simple.SimpleSQL;

import java.util.List;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.statements.DeleteBuilder.DELETE;

/**
 * Created by xiaoyao9184 on 2017/9/14.
 */
public class TsqlDeleteSqlRender extends CommentSupportRender<Param> {
    @Override
    public SQL render(RDBTableMetaData metaData, Param param) {
        this.metaData = metaData;
        this.param = param;

        ListParameterModel<Where> whereListParameterModel = getWhere();
        List params = whereListParameterModel.getParam();
        Where where = whereListParameterModel.getModel();

        Delete delete = DELETE()
                .$(t(metaData.getAlias()))
                .$From(getTableName())
                .build();
        delete.setWhere(where);

        String sqlString = BlockManager.INSTANCE.print(delete);
        return new SimpleSQL(sqlString,params.toArray());
    }
}
