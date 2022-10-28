package Task4.parser;

import java.util.List;
import java.util.Stack;

import Task4.ast.Expr;
import Task4.lexer.Token;
import Task4.lexer.TokenType;

public class Parser {

	private final List<Token> tokens;
	private Stack<Expr> stack = new Stack<>();
	private int current = 0;

	public Parser(List<Token> tokens) {
		this.tokens = tokens;
	}

	public Expr parse() {
		try {
			return expression();
		} catch (java.util.EmptyStackException error) {
			throw new ParserError("incomplete binop expression");
		}
	}

	private Expr expression() {
		while (!isAtEnd()) {
			if(this.match(TokenType.NUM)) {
				this.stack.push(this.number());
			}

			else if(this.match(TokenType.PLUS, TokenType.MINUS, 
					TokenType.SLASH, TokenType.STAR)) {
				this.stack.push(this.binop());
			}
			else if(this.match(TokenType.ID)) {
				this.stack.push(this.id());
			}
			this.advance();
		}
		return this.stack.pop();
	}

	private Expr id() {
		return new Expr.Id(peek().lexeme);
	}

	private Expr number() {
		return new Expr.Number(peek().lexeme);
	}

	private Expr binop() {
		return new Expr.Binop(this.stack.pop(), this.stack.pop(), this.peek());
	}

	private boolean match(TokenType... types) {
		for (TokenType type : types) {
			if (check(type)) {
				return true;
			}
		}

		return false;
	}

	private boolean check(TokenType type) {
		if (isAtEnd()) return false;
		return peek().type == type;
	}

	private Token advance() {
		if (!isAtEnd()) current++;
		return previous();
	}

	private boolean isAtEnd() {
		return peek().type == TokenType.EOF;
	}

	private Token peek() {
		return tokens.get(current);
	}

	private Token previous() {
		return tokens.get(current - 1);
	}
}
