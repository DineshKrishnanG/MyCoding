package MyCoding;

public class LongestPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String str = "forgeeksskeegfor";

		 System.out.println("\nstr: "+ str );
		  System.out.println("\nLength is: "+ longestPalSubstr( str ) );
	}

	public static void printSubStr(char[] substr, int low, int high )
	{
	    for( int i = low; i <= high; ++i )
	        System.out.print(substr[i]);
	}
	 
	// This function prints the longest palindrome substring
	// of str[].
	// It also returns the length of the longest palindrome
	public static int longestPalSubstr( String str )
	{
		char[] charStr = str.toCharArray();
	    int n = charStr.length; // get length of input string
	 
	    // table[i][j] will be false if substring str[i..j]
	    // is not palindrome.
	    // Else table[i][j] will be true
	    boolean[][] table = new boolean[n][n];

	    // All substrings of length 1 are palindromes
	    int maxLength = 1;
	    for (int i = 0; i < n; ++i)
	        table[i][i] = true;
	 
	    // check for sub-string of length 2.
	    int start = 0;
	    for (int i = 0; i < n-1; ++i)
	    {
	        if (charStr[i] == charStr[i+1])
	        {
	            table[i][i+1] = true;
	            start = i;
	            maxLength = 2;
	        }
	    }
	 
	    // Check for lengths greater than 2. k is length
	    // of substring
	    for (int k = 3; k <= n; ++k)
	    {
	    	System.out.println("k :"+k);
	        // Fix the starting index
	        for (int i = 0; i < n-k+1 ; ++i)
	        {
	            // Get the ending index of substring from
	            // starting index i and length k
	            int j = i + k - 1;
	 
	            // checking for sub-string from ith index to
	            // jth index iff str[i+1] to str[j-1] is a
	            // palindrome
	            //System.out.println("k["+k+"]	i["+i+"]j["+j+"] table[i+1][j-1] ["+table[i+1][j-1]+"] i_char["+charStr[i]+"] j_char["+charStr[j]+"]");
	            if (table[i+1][j-1] && charStr[i] == charStr[j])
	            {
	            	System.out.println("k["+k+"]	i["+i+"]j["+j+"] table[i+1][j-1] ["+table[i+1][j-1]+"] i_char["+charStr[i]+"] j_char["+charStr[j]+"]");
	            	System.out.println(">> setting to true");
	                table[i][j] = true;
	 
	                if (k > maxLength)
	                {
	                    start = i;
	                    maxLength = k;
	                }
	            }
	        }
	    }
	 
	    System.out.print("Longest palindrome substring is: ");
	    printSubStr( charStr, start, start + maxLength - 1 );
	    System.out.println("");
	    printTable(table);
	    return maxLength; // return length of LPS
	}
	public static void printTable(boolean[][] tableData){
		int len = tableData.length;
		System.out.println("tableData len :"+len);
		for( int i=0; i< len; i++){
			for( int j=0; j< len; j++){
				String val = ""+tableData[i][j];
				if(val.equals("false") ){
					val = "----";
				}
				System.out.print(val+" | ");
			}
			System.out.println("");
		}
		
	}
}

