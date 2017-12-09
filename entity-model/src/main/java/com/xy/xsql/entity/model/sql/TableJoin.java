package com.xy.xsql.entity.model.sql;

import com.xy.xsql.entity.api.meta.TableMeta;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/10/21
 */
public class TableJoin {

    private JoinType joinType;
    private TableMeta tableMeta;
    private List<JoinCondition> joinConditions;

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public TableMeta getTableMeta() {
        return tableMeta;
    }

    public void setTableMeta(TableMeta tableMeta) {
        this.tableMeta = tableMeta;
    }

    public List<JoinCondition> getJoinConditions() {
        return joinConditions;
    }

    public void setJoinConditions(List<JoinCondition> joinConditions) {
        this.joinConditions = joinConditions;
    }
}
