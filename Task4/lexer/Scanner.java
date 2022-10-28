package Task4.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Scanner {

	private final String source;
	private final List<Token> tokens = new ArrayList<>();

	public Scanner(String source) {
		this.source = source;
	}

	public List<Token> scan() {
		return this.scan(this.source);
	}
	
	public List<Token> scan(String program) {
		StringTokenizer tokenizer = new StringTokenizer(program, Token.TOKENIZER_DELIMITER); 

		while (tokenizer.hasMoreElements()) {
			String tokenStr = tokenizer.nextToken();
			this.tokens.add(this.getToken(tokenStr));
		}
		this.tokens.add(new Token(TokenType.EOF, "")); // EOF
		
		return this.tokens;
	}

	private Token getToken(String token) {
		Token ret = null;
		if(Regex.isNum(token)) {
			ret = new Token(TokenType.NUM, token);
		}
		else if(Regex.isOP(token)) {
			ret = new Token(Regex.getOPTokenType(token), token);
		}
		else if(Regex.isId(token)){
			ret = new Token(TokenType.ID, token);
		}
		else{
			throw new LexError("Unexpected character: " + token);
		}
		return ret;
	}
}
