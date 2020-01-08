
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WebPage {
	public String url;
	public String name;
	public WordCounter counter;
	public double score;
	public String time;
	public int timescore;

	public WebPage(String url, String name, String time) {
		this.url = url;
		this.name = name;
		this.time = time;
		this.counter = new WordCounter(url);
	}

	public void timeInt() throws ParseException {
		try {
			Date today = new Date();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");       
		    //today=s.parse(s.format(today));	
		    Date theDate=s.parse(time);
		    System.out.println(today);
		    System.out.println(theDate);
		    long dd=(today.getTime()-theDate.getTime())/(1000*3600*24);
		    System.out.println(dd);
		    if ((dd/10)<1) {
		    	timescore=timescore+200;
		    	if (dd==0) {
		    		timescore=timescore+300;
				}else if (dd==1) {
					timescore=timescore+250;
				}else if (dd==2) {
					timescore=timescore+200;
				}else if (dd==3) {
					timescore=timescore+50;
				}else {
					timescore=timescore+30;
				}
		    }else if ((dd/10)==1) {
		    	timescore=timescore+20;
		    	if (15<=dd&&dd<=10) {
		    		timescore=timescore+20;
				}else if (20>=dd&&dd>15) {
					timescore=timescore+10;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" ");
		}

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
		                prev[i][j] = 0; // 左上方
		               
		            }
		            else
		            {
		                if (length[i-1][j] < length[i][j-1])
		                {
		                    length[i][j] = length[i][j-1];
		                    prev[i][j] = 1; // 左方
		                   
		                }
		                else
		                {
		                    length[i][j] = length[i-1][j];
		                    prev[i][j] = 2; // 上方
		                }
		            }
		 
		    
		}
		       
		
		    }
		    
		    
		    return length[s1][s2] ;//we don't have to use (s1-1) because []is run from 0 so is (s1+1)-1=s1
	}
	
	public void setScore(ArrayList<Keyword> keywords) throws IOException, ParseException {
		try {
			timeInt();
			score = 0;
			for (Keyword k : keywords) {
				
				score += counter.countKeyword(k.name) * k.weight + timescore+200*findLCS(k.toString(),name);
				System.out.println(score);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" ");
		}
	}

}
