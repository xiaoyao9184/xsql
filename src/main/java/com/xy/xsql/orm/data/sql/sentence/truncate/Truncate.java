package com.xy.xsql.orm.data.sql.sentence.truncate;


import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;

/**
 * Created by xiaoyao9184 on 2016/12/19.
 */
public class Truncate extends CustomizeSentence {

	private Table table;

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	@Override
	public BaseElementsSentence toBaseElementsSentence() {
		ListElementBuilder builder = new ListElementBuilder()
				.append(GrammarEnum.TRUNCATE)
				.append(OtherEnum.SPACE)
				.append(GrammarEnum.TABLE)
				.append(OtherEnum.SPACE)
				.append(table);

		return new BaseElementsSentence(builder.build(null));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder()
				.append(GrammarEnum.TRUNCATE)
				.append(OtherEnum.SPACE)
				.append(GrammarEnum.TABLE)
				.append(OtherEnum.SPACE)
				.append(table.getFullName());

		return builder.toString();
	}
}
