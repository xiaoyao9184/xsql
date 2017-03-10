package com.xy.xsql.orm.data.sql.expression.operator.relational;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;

import java.util.List;

public class Exists implements Expression {

	private Expression rightExpression;
	private boolean not = false;

	public Exists withRightExpression(Expression expression) {
		this.rightExpression = expression;
		return this;
	}

	public Exists withNot(boolean b) {
		this.not = b;
		return this;
	}

	@Override
	public List<Element> toElementList() {
		return new ListElementBuilder()
				.append(not ? OperatorEnum.NOT : null)
				.append(OperatorEnum.EXISTS)
				.append(rightExpression)
				.build();
	}
}
