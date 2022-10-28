import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Task2
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

                if (line.equals("*")) {
                    int a = Integer.parseInt(rpn.pop());
					int b = Integer.parseInt(rpn.pop());
                    rpn.push(String.valueOf(a*b));
                    token = new Token(TokenType.STAR, "*");
                    tokens.add(token.toString());
                }
                else if(line.equals("+")) {
					int a = Integer.parseInt(rpn.pop());
					int b = Integer.parseInt(rpn.pop());
					rpn.push(String.valueOf(a+b));

                    token = new Token(TokenType.PLUS, "+");
                    tokens.add(token.toString());
				}
				else if(line.equals("-")) {
					int a = Integer.parseInt(rpn.pop());
					int b = Integer.parseInt(rpn.pop());
					rpn.push(String.valueOf(a-b));

                    token = new Token(TokenType.MINUS, "-");
                    tokens.add(token.toString());
				}
				else if(line.equals("/")) {
					int a = Integer.parseInt(rpn.pop());
					int b = Integer.parseInt(rpn.pop());
					rpn.push(String.valueOf(a/b));

                    token = new Token(TokenType.SLASH, "/");
                    tokens.add(token.toString());
				}
				else {
                    try {
                        int aux = Integer.parseInt(line);

                        rpn.push(line);
    
                        token = new Token(TokenType.NUM, line);
                        tokens.add(token.toString());

                    } catch (Exception e) {
                        error = true;
                        System.out.println("Error: Unexpected character: " + line);
                    }
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