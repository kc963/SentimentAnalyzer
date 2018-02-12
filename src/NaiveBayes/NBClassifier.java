package NaiveBayes;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

import BagOfWords.*;

public class NBClassifier {

	bowArray obj = null;
		
	public void classify(String input){
		
		try{
			//Path of the resource file
			FileInputStream fileIn = new FileInputStream("C:/Users/Kapil/Documents/GitHub/SentimentAnalyzer/Resources/bowArrayObj.ser");
			ObjectInputStream oIn = new ObjectInputStream(fileIn);
			obj = (bowArray)oIn.readObject();
			oIn.close();
			fileIn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		float p_pos = 0,p_neg = 0;
		p_pos = obj.prob_pos;
		p_neg = obj.prob_neg;
		String pp = "" + obj.prob_pos;
		String pn = "" + obj.prob_neg;
		
		StringTokenizer st = new StringTokenizer(input," ,.");
		
		while(st.hasMoreTokens()){
			String token = st.nextToken().toLowerCase();
			if(obj.stopwords.contains(token)){
				continue;
			}
			int x = obj.words.indexOf(token);
			if(x!=-1){
				//System.out.println(token);
				/*if(obj.bog.get(x)>0){
					p_pos *= (float)(1.0 * obj.pos.get(x)/(1.0 * obj.total_pos));
					
				}
				else if(obj.bog.get(x)<0){
					p_neg *= (float)(1.0 * obj.neg.get(x)/(1.0 * obj.total_neg));
				}*/
				int p = obj.pos.get(x);
				int n = obj.neg.get(x);
				if(p!=0){
					p_pos *= (float)(1.0 * p / obj.total_pos);
					pp += " * " + (p) + "/" + (obj.total_pos);
				}
				if(n!=0){
					p_neg *= (float)(1.0 * n / obj.total_neg);
					pn += " * " + (n) + "/" + (obj.total_neg);
				}
			}
		}
		
		DecimalFormat df = new DecimalFormat("#.##########");
		
		System.out.println("Positive Probability : " + pp + " = " + df.format(p_pos));
		System.out.println("Negative Probability : " + pn + " = " + df.format(p_neg));
		
		if(p_pos>p_neg){
			System.out.println("The statement is POSITIVE.\n");
		}
		else if(p_pos<p_neg){
			System.out.println("The statement is NEGATIVE.\n");
		}
		else{
			System.out.println("The statement is NEUTRAL.\n");
		}
		
	}
	
	
	
}
