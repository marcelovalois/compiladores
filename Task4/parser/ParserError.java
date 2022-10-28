package Task4.parser;

public class ParserError extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ParserError(String msg) {
		super(msg);
	}
}
