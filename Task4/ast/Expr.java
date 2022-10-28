
package Task4.ast;

import Task4.lexer.Token;

public abstract class Expr {

	public interface Visitor<T> {
		T visitNumberExpr(Number expr);
		T visitBinopExpr(Binop expr);
		T visitIdExpr(Id expr);
	}

	public static class Number extends Expr {
		public Number(String value){
			this.value = value;
		}

		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visitNumberExpr(this);
		}

		public final String value;
	}

	public static class Id extends Expr {
		public Id(String value){
			this.value = value;
		}

		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visitIdExpr(this);
		}

		public final String value;
	}

	public static class Binop extends Expr {
		public Binop(Expr left, Expr right, Token operator) {
			this.left = left;
			this.right = right;
			this.operator = operator;
		}

		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visitBinopExpr(this);
		}

		public final Expr left;
		public final Expr right;
		public final Token operator;
	}

	public abstract <T> T accept(Visitor<T> visitor);
}
