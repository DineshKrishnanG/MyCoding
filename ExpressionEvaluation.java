package MyCoding;
/* A Java program to evaluate a given expression where tokens are separated 
by space.
Test Cases:
  "10 + 2 * 6"            ---> 22
  "100 * 2 + 12"          ---> 212
  "100 * ( 2 + 12 )"      ---> 1400
  "100 * ( 2 + 12 ) / 14" ---> 100    
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExpressionEvaluation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> inputs = new ArrayList<String>();
		inputs.add("10 + 2 * 6");
		inputs.add("100 * 2 + 12");
		inputs.add("100 * ( 2 + 12 )");
		inputs.add("100 * ( 2 + 12 ) / 14");
		
		for( String inStr : inputs){
			System.out.println(" Expression : "+inStr+ "\t ==>"+  ExpressionEvaluation.evaluate(inStr));
		}
		    
	}
	
	public static int evaluate(String expression){
		char[] chArr = expression.toCharArray();
		
		Stack<Integer> values = new Stack<Integer>();
		Stack<Character> ops = new Stack<Character>();
		
		for( int i=0; i<chArr.length;i++){
			//System.out.println("=== i["+i+"] ch["+chArr[i]+"]");
			if( chArr[i] == ' '){
				continue;
			}
			if( chArr[i] >= '0' && chArr[i] <= '9'){
				// Number
				StringBuilder sb = new StringBuilder();
				while(i < chArr.length && chArr[i] >= '0' && chArr[i] <= '9'){
					sb.append(chArr[i++]);
				}
				values.push( Integer.parseInt(sb.toString()) );
			}else if ( chArr[i] == '('){
				ops.push(chArr[i] );
			}else if( chArr[i] == ')'){
				while( ops.peek() != '(' ){
					values.push(applyOps(ops.pop(), values.pop(), values.pop()) );
				}
				ops.pop();
			}
			else if( chArr[i] == '+' ||  chArr[i] == '-' || 
					chArr[i] == '*' ||  chArr[i] == '/'){
				// While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
				while(!ops.empty() && hasPrecedence(chArr[i], ops.peek()) ){				
					values.push( applyOps(ops.pop(), values.pop(), values.pop()) );
				}
  
				ops.push(chArr[i]);
			}else{
				System.out.println("Invalid char :"+chArr[i]);
			}
			
			
		}
		//System.out.println(" Ops stack ==>"+ ops.toString());
		//System.out.println(" Value stack ==>"+ values.toString());
		while(! ops.isEmpty()){
			values.push( applyOps(ops.pop(), values.pop(), values.pop()) );
		}
		return values.pop();
	}
	
	public static Integer applyOps(char op, Integer b, Integer a){
		//System.out.println("Inside applyOps: op["+op+"]b["+b+"]a["+a+"]");
		Integer retVal = 0;
		switch(op){
			case '+':
				retVal =  a + b;
				break;
			case '-':
				retVal = a - b;
				break;
			case '*':
				retVal = a * b;
				break;
			case '/':
				if( b == 0){
					throw new
	                UnsupportedOperationException("Cannot divide by zero");
				}
				retVal = a/b;
				break;
				
		}
		return retVal;
	}
	public static boolean hasPrecedence(char op1, char op2){
		if( op2 == '(' || op2 == ')')
			return false;
		if( (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-') )
			return false;
		else
			return true;
		
	}
}
