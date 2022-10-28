import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Task3
{
	public static void main(String[] args) throws IOException {
        Stack <String> rpn = new Stack <String> ();
        List<String> tokens = new ArrayList<String>();
		FileReader fr = new FileReader("Calc1.stk");
		BufferedReader br = new BufferedReader(fr);
		
        String line;
        Token token;
        boolean error = false;
		try {
            do {
                line = br.readLine();

				if (Regex.isNum(line)) {
					rpn.push(line);
    
					token = new Token(TokenType.NUM, line);
					tokens.add(token.toString());
				} else if (Regex.isOP(line)) {
					if (Regex.isPlus(line)) {
						int a = Integer.parseInt(rpn.pop());
						int b = Integer.parseInt(rpn.pop());
						rpn.push(String.valueOf(a+b));

						token = new Token(TokenType.PLUS, "+");
						tokens.add(token.toString());
					} else if (Regex.isMinus(line)) {
						int a = Integer.parseInt(rpn.pop());
						int b = Integer.parseInt(rpn.pop());
						rpn.push(String.valueOf(a-b));

						token = new Token(TokenType.MINUS, "-");
						tokens.add(token.toString());
					} else if (Regex.isStar(line)) {
						int a = Integer.parseInt(rpn.pop());
						int b = Integer.parseInt(rpn.pop());
						rpn.push(String.valueOf(a*b));
	
						token = new Token(TokenType.STAR, "*");
						tokens.add(token.toString());
					} else if (Regex.isSlash(line)) {
						int a = Integer.parseInt(rpn.pop());
						int b = Integer.parseInt(rpn.pop());
						rpn.push(String.valueOf(a/b));

						token = new Token(TokenType.SLASH, "/");
						tokens.add(token.toString());
					}
				} else {
					error = true;
					System.out.println("Error: Unexpected character: " + line);
				}

            } while (line != null);
		    
		} catch(Exception e) {
		    System.out.println(e);

		} finally {
            br.close();
            fr.close();

            if (!error) {
                for (String i : tokens) {
                    System.out.println(i);
                }
            }
            System.out.println(rpn.pop());
		}
		
	}
}

class Token {

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

enum TokenType {

	// Literals.
	NUM,

	// Single-character tokens for operations.
	MINUS, PLUS, SLASH, STAR,
	
	EOF
}

class Regex {
	
	private static final String NUM_REGEX = "(\\d)+";  // short for [0-9]
	private static final String OP_REGEX = "(\\+|-|\\*|/)";  // short for [0-9]
	private static final String PLUS_REGEX = "(\\+)";  // short for [0-9]
	private static final String MINUS_REGEX = "(\\-)";  // short for [0-9]
	private static final String SLASH_REGEX = "(/)";  // short for [0-9]
	private static final String STAR_REGEX = "(\\*)";  // short for [0-9]

	
	public static boolean isNum(String token) {
		return token.matches(NUM_REGEX);
	}

	public static boolean isOP(String token) {
		return token.matches(OP_REGEX);
	}

	public static boolean isPlus(String token) {
		return token.matches(PLUS_REGEX);
	}

	public static boolean isMinus(String token) {
		return token.matches(MINUS_REGEX);
	}

	public static boolean isSlash(String token) {
		return token.matches(SLASH_REGEX);
	}

	public static boolean isStar(String token) {
		return token.matches(STAR_REGEX);
	}

}