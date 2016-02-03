package MyCoding;

public class LongestPalindromicSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String instr = "GEEKS FOR GEEKS";
	    int n = instr.length();
	    System.out.println("instr :"+instr);
	    System.out.println("Length of LPS : "+ lps(instr) );
	    
	    
	}
	
	public static int lps(String inStr){
		char[] chArr = inStr.toCharArray();
		int len = chArr.length;
		int[][] table= new int[len][len];
		 int i,j;
		 
		for (i=0; i<len;i++ ){
			table[i][i] = 1;
		}
		
		int[] ch = new int[3];
		for( int cl=2; cl<= len; cl++){
			System.out.println("### cl ["+cl+"]");
			for( i=0; i < len-cl+1;i++){
				j = i+cl-1;
				
				if( cl==2 && chArr[i] == chArr[j]){
					table[i][j] = 2;
					if( ch[0] == 0){
						System.out.println(" UPDATING to 2");
						System.out.println(" i["+i+"] j["+j+"] iCh["+ chArr[i]+"] jCh["+ chArr[j]+"]");
						printTable(table);
						ch[0] = 1;
					}
					
				}else if(chArr[i] == chArr[j]){
					
					if( ch[1] == 0){
						System.out.println(" UPDATING to 2 + inner table");
						System.out.println(" i["+i+"] j["+j+"] iCh["+ chArr[i]+"] jCh["+ chArr[j]+"]");
						printTable(table);
						table[i][j] = 2 + table[i+1][j-1];
						System.out.println("After");
						printTable(table);
						ch[1] = 1;
					}else{
						table[i][j] = 2 + table[i+1][j-1];
					}
					
				}else{
					
					if( ch[2] == 0){
						System.out.println(" UPDATING to max of inner");
						System.out.println(" i["+i+"] j["+j+"] iCh["+ chArr[i]+"] jCh["+ chArr[j]+"]");
						printTable(table);
						table[i][j] = Math.max( table[i][j-1], table[i+1][j]);
						System.out.println("After");
						printTable(table);
						ch[2] = 1;
					}else{
						table[i][j] = Math.max( table[i][j-1], table[i+1][j]);
					}
				}
			}
		}
		printTable(table);
		return table[0][len-1];
	}

	public static void printTable(int[][] tableData){
		int len = tableData.length;
		System.out.println("tableData len :"+len);
		for( int i=0; i< len; i++){
			for( int j=0; j< len; j++){
				int val = tableData[i][j];
				
				System.out.print(val+" | ");
			}
			System.out.println("");
		}
		
	}
}
