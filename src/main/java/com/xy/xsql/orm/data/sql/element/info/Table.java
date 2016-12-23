package com.xy.xsql.orm.data.sql.element.info;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.Sentence;
import com.xy.xsql.orm.util.ListBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 表
 * 基本元素
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Table
        extends Name<Table>
        implements Element, Expression, Cloneable {


    private String databaseName ;
    private String schemaName;
    private Sentence sentence;


    public Table() {
        super();
    }

    public Table(String name) {
        super(name);
    }

    public Table(String name, String aliasName) {
        super(name,aliasName);
    }

    public Table(Name name) {
        super(name);
    }

    public Table(Sentence sentence) {
        super(null, "temp");
        this.sentence = sentence;
    }

    @Override
    public String getFullName() {
        String[] names = new String[3];
        names[0] = databaseName;
        names[1] = schemaName;
        names[2] = this.getName();
        return StringUtils.join(names,".");
    }

    /**
     * 克隆
     * @return Table
     */
    @SuppressWarnings({"RedundantStringConstructorCall", "CloneDoesntCallSuperClone"})
    @Override
    public Table clone() {
        return new Table(this.getName(),this.getAliasName());
    }

    @Override
    public List<Element> toElementList() {
        return new ListBuilder<Element>()
                .withItem(this)
                .build(null);
    }
}
