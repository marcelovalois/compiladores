package Task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;


import Task4.ast.AstPrinter;
import Task4.ast.Expr;
import Task4.interpreter.Interpreter;
import Task4.lexer.LexError;
import Task4.lexer.Scanner;
import Task4.lexer.Token;
import Task4.parser.Parser;
import Task4.parser.ParserError;

public class Task4 {
    private static final Interpreter interpreter = new Interpreter(new HashMap<String, String>());
	private static boolean hasError = false;
	private static boolean debugging = false;


    public static void main(String[] args) throws IOException {
		args = new String [1];
		args[0] = "./Task4/Calc2.stk";

		debugging = false;
		run(args, debugging);
	}


    private static void run(String[] args, boolean debugging) throws IOException {
        Task4.debugging = debugging;
		if(args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				runFile(args[i]);
			}
		}
		else {
			runPrompt();
		}
    }

    private static void run(String source) {
		try {
			Scanner scanner = new Scanner(source);
			List<Token> tokens = scanner.scan();

			if(debugging) {
				printTokens(tokens);
			}

			Parser parser = new Parser(tokens);
			Expr expr = parser.parse();

			if(debugging) {
				printAST(expr);
			}

			interpreter.env.put("a", "1");
			interpreter.env.put("b", "4");
			interpreter.env.put("c", "9");
			interpreter.env.put("d", "16");
			interpreter.env.put("e", "25");

			System.out.println(interpreter.interp(expr));
		} catch (LexError e) {
			error("Lex", e.getMessage());
			hasError = true;
		}	
		catch (ParserError e) {
			error("Parser", e.getMessage());
			hasError = true;
		}	
	}

    private static void runPrompt() throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);

		for (;;) { 
			System.out.print("> ");
			String line = reader.readLine();
			if (line == null) break;
			run(line);
			hasError = false;
		}
	}


    private static void runFile(String sourceFilePath) throws IOException {
		byte[] bytes = Files.readAllBytes(Paths.get(sourceFilePath));
		String sourceProgram = 
				new String(bytes, Charset.defaultCharset());
		run(sourceProgram);

		if (hasError) System.exit(65);
	}

    private static void printAST(Expr expr) {
		System.out.println("ast: "+new AstPrinter().print(expr));
		System.out.println();
	}

	private static void printTokens(List<Token> tokens) {
		for (Token token : tokens) {
			System.out.println(token);
		}
		System.out.println();
	}

	private static void error(String kind, String message) {
		report(kind, message);
	}

	private static void report(String kind, String message) {
		System.err.println(
				"[" + kind + "] Error: " + message);
		hasError = true;
	}
}
