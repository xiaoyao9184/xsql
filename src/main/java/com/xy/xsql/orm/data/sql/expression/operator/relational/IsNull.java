package com.xy.xsql.orm.data.sql.expression.operator.relational;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;

import java.util.List;

public class IsNull implements Expression {

	private Expression leftExpression;
	private boolean not = false;

	public IsNull withLeftExpression(Expression leftExpression) {
		this.leftExpression = leftExpression;
		return this;
	}

	public IsNull withNot(boolean b) {
		this.not = b;
		return this;
	}

	@Override
	public List<Element> toElementList() {
		return new ListElementBuilder()
				.append(leftExpression)
				.append(OperatorEnum.IS)
				.append(not ? OperatorEnum.NOT : null)
				.append(OperatorEnum.NULL)
				.build();
	}
}
