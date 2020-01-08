import java.util.ArrayList;

public class KeywordList {
	private ArrayList<Keyword> lst;
	private Object[][] l;
	
	public KeywordList(){
		this.lst = new ArrayList<Keyword>();
    }
	
	public void add(Keyword keyword){
		lst.add(keyword);
		System.out.println("Done");
    }
	
	public void find(String s){
		int maxValue = -1;
		int maxIndex = -1;
		for(int i=0; i<lst.size(); i++){
			int lcs = findLCS(lst.get(i).name, s);
			System.out.println(lcs);
			if(lcs > maxValue){
				maxValue = lcs;
				maxIndex = i;
			}
		}
		System.out.println(lst.get(maxIndex).toString());
	}
	
	public int findLCS(String x, String y){
		int s1=x.length();
		int s2=y.length();
		int length[][]=new int[s1+1][s2+1];//because when we build form, we have to build the'0'row,column. So we need (s1+1)*(s2+1) spaces.
		int prev[][]=new int[s1+1][s2+1];//record where the result from

		    for (int i=0; i<=s1; i++) { length[i][0] = 0;}//fill the row or column which has '0' title. 
		    for (int j=0; j<=s2; j++) {length[0][j] = 0;}
		    for (int i=1; i<=s1; i++) {
		        for (int j=1; j<=s2; j++) {
		            if (x.charAt(i-1) == y.charAt(j-1))//cannot use the indexOf
		            {
		                length[i][j] = length[i-1][j-1] + 1;
		                prev[i][j] = 0; // ���W��
		               
		            }
		            else
		            {
		                if (length[i-1][j] < length[i][j-1])
		                {
		                    length[i][j] = length[i][j-1];
		                    prev[i][j] = 1; // ����
		                   
		                }
		                else
		                {
		                    length[i][j] = length[i-1][j];
		                    prev[i][j] = 2; // �W��
		                }
		            }
		 
		    
		}
		       
		
		    }
		    
		    
		    return length[s1][s2] ;//we don't have to use (s1-1) because []is run from 0 so is (s1+1)-1=s1
	}
	
	private void printMatrix(int[][] matrix){
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[0].length; j++){
				System.out.print(matrix[i][j] + " ");
				if(j==matrix[0].length-1)System.out.print("\n");
			}
		}
	}
}