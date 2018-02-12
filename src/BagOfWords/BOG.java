package BagOfWords;

import java.io.*;
import java.util.StringTokenizer;

public class BOG {

	bowArray obj = null;
	
	BOG(){
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
	
	public void train_neg(String fname){
		fname = "C:/Users/Kapil/workspace/SentimentAnalyzer/Resources/" + fname;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String line = null;
			try {
				while((line = br.readLine()) != null){
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
							//System.out.println("Pos size : " + obj.pos.size());
							//System.out.println("Neg size : " + obj.neg.size());
						}else{
							int temp = obj.neg.get(x);
							obj.neg.remove(x);
							obj.neg.add(x,temp + 1);
						}
					}
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
	
	public void train_pos(String fname){
		fname = "C:/Users/Kapil/workspace/SentimentAnalyzer/Resources/" + fname;
		try{
		BufferedReader in = new BufferedReader(new FileReader(fname));
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
					obj.pos.add(1);
					obj.neg.add(0);
					//System.out.println("Pos size : " + obj.pos.size());
					//System.out.println("Neg size : " + obj.neg.size());
				}else{
					int temp = obj.pos.get(x);
					obj.pos.remove(x);
					obj.pos.add(x,temp + 1);
				}
			}
		}
		in.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
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
	
	public void classify(String input){
		
		int count = 0;
		
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
				count = count + obj.bog.get(x)/Math.abs(obj.bog.get(x));
				//System.out.println(token + " <-> " + obj.bog.get(x));
				}catch(ArithmeticException e){
					
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
		
		//display();
		
	}
	
}
