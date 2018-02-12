package BagOfWords;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;

public class BOGClassifier {

	public static bowArray obj = new bowArray();
	
	public static void display(){
		System.out.println("Word\tNeg\tPos\tBOG");
		for(int i=0;i<obj.words.size();i++){
			System.out.println(obj.words.get(i) + "\t" + obj.neg.get(i) + "\t" + obj.pos.get(i) + "\t" + obj.bog.get(i));
		}
		System.out.println("Total Words : " + obj.total_words);
		System.out.println("Total Positive Words : " + obj.total_pos);
		System.out.println("Total Negative Words : " + obj.total_neg);
		System.out.println("Positive Probability : " + obj.prob_pos);
		System.out.println("Negative Probability : " + obj.prob_neg);
	}
	
	public static void main(String args[]){
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:/Users/Kapil/workspace/SentimentAnalyzer/Resources/pythonstopwords.txt"));
			String word = null;
			while((word = br.readLine()) != null){
				obj.stopwords.add(word.toLowerCase());
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader("C:/Users/Kapil/workspace/SentimentAnalyzer/Resources/negative.txt"));
			String line = null;
			while((line = in.readLine()) != null){
				for (String str : line.split(" ")){
					str = str.toLowerCase();
					int z = str.indexOf('.');
					if(z!=-1){
						str = str.substring(0, z);
					}
					if(obj.stopwords.contains(str)){
						continue;
					}
					int x = obj.words.indexOf(str);
					if(x == -1){
						obj.words.add(str);
						obj.neg.add(1);
						obj.pos.add(0);
					}else{
						int temp = obj.neg.get(x);
						obj.neg.remove(x);
						obj.neg.add(x,temp + 1);
					}
				}
			}
			in.close();
			in = new BufferedReader(new FileReader("C:/Users/Kapil/workspace/SentimentAnalyzer/Resources/positive.txt"));
			line = null;
			while((line = in.readLine()) != null){
				for (String str : line.split(" ")){
					str = str.toLowerCase();
					int z = str.indexOf('.');
					if(z!=-1){
						str = str.substring(0, z);
					}
					if(obj.stopwords.contains(str)){
						continue;
					}
					int x = obj.words.indexOf(str);
					if(x == -1){
						obj.words.add(str);
						obj.pos.add(1);
						obj.neg.add(0);
					}else{
						int temp = obj.pos.get(x);
						obj.pos.remove(x);
						obj.pos.add(x,temp + 1);
					}
				}
			}
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			int l = obj.words.size();
			for(int i=0;i<l;i++){
				obj.bog.add(i, obj.pos.get(i)-obj.neg.get(i));
				obj.total_words++;
				if(obj.bog.get(i)>0){
					obj.total_pos++;
				}else if(obj.bog.get(i)<0){
					obj.total_neg++;
				}
			}
			obj.prob_pos = (float) (1.0 * obj.total_pos / obj.total_words);
			obj.prob_neg = (float) (1.0 * obj.total_neg / obj.total_words);
		}
		
		display();
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("C:/Users/Kapil/workspace/SentimentAnalyzer/Resources/bowArrayObj.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(obj);
	         out.close();
	         fileOut.close();
	  	  }catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	
}
