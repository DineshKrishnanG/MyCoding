package practice;

import java.util.Arrays;
import java.util.Stack;

public class Histogram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] hist = {6, 2, 5, 4, 5, 1, 6};
		System.out.println("Entries : "+ Arrays.toString(hist) );
		System.out.println("Max area: "+ getMaxArea(hist) );
	}

	// The main function to find the maximum rectangular area under given
	// histogram with n bars
	public static int getMaxArea(int hist[]){
		int max_area=0;
		int n = hist.length;
		
		Stack<Integer> st = new Stack<Integer>();
		int tp;
		int area_with_top;
		
		int i=0;
		while(i<n){
			//System.out.println("Inside while: i:"+i);
			if( st.empty() || hist[i] >= hist[st.peek()] ){
				//System.out.println("	Pushing: index: "+ i+ " value :"+hist[i]);
				st.push(i++);
				//System.out.println("	>Stack is "+st.toString() );
			}else{
				tp = st.peek();
				st.pop();
				
				int posCnt = st.empty() ? i : i - st.peek() - 1;
				//System.out.println("	<Stack is "+st.toString() );
				//System.out.println("	Calculating: top index "+ tp+ " value :"+hist[tp] + " posCnt:"+posCnt);
				
				area_with_top = hist[tp] * ( posCnt );
				//System.out.println("	Calculating: area_with_top["+area_with_top+"]max_area["+max_area+"] ");
				if( area_with_top > max_area){
					max_area = area_with_top;
				}
			}
		}
		//System.out.println("After processing all elements");
		// Now pop the remaining bars from stack and calculate area with every
	    // popped bar as the smallest bar
		while(! st.empty() ){
			tp = st.peek();
			st.pop();
			//System.out.println("	<Stack is "+st.toString() );
			//System.out.println("	Calculating: top index "+ tp);
			area_with_top = hist[tp] * ( st.empty() ? i : i - st.peek() - 1 );
			//System.out.println("	Calculating: area_with_top["+area_with_top+"]max_area["+max_area+"] ");
			if( area_with_top > max_area){
				max_area = area_with_top;
			}
			
		}
		
		return max_area;
	}
	
}
