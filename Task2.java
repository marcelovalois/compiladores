import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

public class Task2
{
	public static void main(String[] args) throws IOException {
        Stack <String> rpn = new Stack <String> ();
		FileReader fr = new FileReader("Calc1.stk");
		BufferedReader br = new BufferedReader(fr);
		
        String line;
		try {
            do {
                line = br.readLine();

                if (line.equals("*")) {
                    int a = Integer.parseInt(rpn.pop());
					int b = Integer.parseInt(rpn.pop());
                    rpn.push(String.valueOf(a*b));
                }
                else if(line.equals("+")) {
					int a = Integer.parseInt(rpn.pop());
					int b = Integer.parseInt(rpn.pop());
					rpn.push(String.valueOf(a+b));
				}
				else if(line.equals("-")) {
					int a = Integer.parseInt(rpn.pop());
					int b = Integer.parseInt(rpn.pop());
					rpn.push(String.valueOf(a-b));
				}
				else if(line.equals("/")) {
					int a = Integer.parseInt(rpn.pop());
					int b = Integer.parseInt(rpn.pop());
					rpn.push(String.valueOf(a/b));
				}
				else {
					rpn.push(line);
				}

            } while (line != null);
		    
		} catch(Exception e) {
		    System.out.println(e);

		} finally {
            br.close();
            fr.close();
            System.out.println(rpn.pop());
		}
		
	}
}