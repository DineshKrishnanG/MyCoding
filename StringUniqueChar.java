package MyCoding;

import java.util.Scanner;

public class StringUniqueChar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a string:");
		String inStr = in.nextLine();
		boolean isUnique = isUniqueChars( inStr);
		if( isUnique){
			System.out.println(inStr+" has unique chars ");
		}else{
			System.out.println(inStr+" has duplicate chars ");
		}
		
	}
	public static boolean isUniqueChars(String str) {
	    if (str.length() > 256) {
	        return false;
	    }
	    int checker = 0;
	    for (int i = 0; i < str.length(); i++) {
	        int val = str.charAt(i) - 'a';
	        System.out.println("i["+i+"] val["+val+"] checker["+checker+"] (checker & (1 << val)) ==>"+(checker & (1 << val)));
	        if ((checker & (1 << val)) > 0) return false;
	        checker |= (1 << val);
	    }
	    return true;
	}
}
