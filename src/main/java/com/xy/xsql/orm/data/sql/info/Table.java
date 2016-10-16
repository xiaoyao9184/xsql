package com.xy.xsql.orm.data.sql.info;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.sentence.Sentence;

/**
 * 表
 * 基本元素
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Table
        extends Name
        implements Element, Cloneable {

    private Sentence sentence;

    public Table(String s) {
        super(s);
    }

    public Table(String realName, String otherName) {
        super(realName,otherName);
    }

    public Table(Name tableName) {
        super(tableName.getRealName(),tableName.getOtherName());
    }

    public Table addSub(Sentence sentence) {
        this.sentence = sentence;
        return this;
    }

    /**
     * 获取表名称
     * @return Name
     */
    public Name toName() {
        return this;
    }


    /**
     * 克隆
     * @return Table
     */
    @SuppressWarnings({"RedundantStringConstructorCall", "CloneDoesntCallSuperClone"})
    @Override
    public Table clone() {
        return new Table(this.getRealName(),this.getOtherName());
    }


}
