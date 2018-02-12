package BagOfWords;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.StringTokenizer;

public class AdvanceBOW {

	bowArray obj = null;
	
	AdvanceBOW(){
		try{
			FileInputStream fileIn = new FileInputStream("C:/Users/Kapil/workspace/SentimentAnalyzer/Resources/bowArrayObj.ser");
			ObjectInputStream oIn = new ObjectInputStream(fileIn);
			obj = (bowArray)oIn.readObject();
			oIn.close();
			fileIn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void display(){
		System.out.println("Total Words : " + obj.total_words);
		System.out.println("Total Positive Words : " + obj.total_pos);
		System.out.println("Total Negative Words : " + obj.total_neg);
		System.out.println("Positive Probability : " + obj.prob_pos);
		System.out.println("Negative Probability : " + obj.prob_neg);
		System.out.println();
	}
	
public void classify(String input){
		
		float count = 0;
		
		StringTokenizer st = new StringTokenizer(input," ,.");
		
		while(st.hasMoreTokens()){
			String token = st.nextToken().toLowerCase();
			if(obj.stopwords.contains(token)){
				continue;
			}
			int x = obj.words.indexOf(token);
			//System.out.println(token + " : " + x + " -- " + ((x>-1)?obj.bog.get(x):"Null"));
			if(x==-1){
			
			}
			else{
				try{
				if (obj.bog.get(x)<0){
					count += (float)(-1.0 * obj.neg.get(x)/obj.pos.get(x));
				}else if(obj.bog.get(x)>0){
					count += (float)(obj.pos.get(x)/obj.neg.get(x));
				}
				}catch(Exception e){
					
				}
			}
		}
		
		System.out.println("Score: " + count);
		
		if(count>0){
			System.out.println("The statement is POSITIVE.");
		}else if(count<0){
			System.out.println("The statement is NEGATIVE.");
		}else{
			System.out.println("The statement is NEUTRAL.");
		}
				
	}
	
}
