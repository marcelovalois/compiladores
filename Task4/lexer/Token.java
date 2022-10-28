package Task4.lexer;

public class Token {

	// Tokenizer delimiters for scanning 
	// \\t -> the tab character
	// \\n -> the newline character
	// \\r -> the carriage-return character
	// \\f -> the form-feed character
	// ' ' -> the space character
	public static final String TOKENIZER_DELIMITER = "\t\n\r\f ";

	public final TokenType type; // token type
	public final String lexeme; // token value

	public Token (TokenType type, String value) {
		this.type = type;
		this.lexeme = value;
	}

	@Override
	public String toString() {
		return "Token [type=" + this.type + ", lexeme=" + this.lexeme + "]";
	}
}
