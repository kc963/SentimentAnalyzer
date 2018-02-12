package BagOfWords;

import java.io.*;

public class bogTrainer {

	bowArray obj = null;
	
	public void display(){
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
	
	public void train(String name, String type){
		String fname = "C:/Users/Kapil/workspace/SentimentAnalyzer/Resources/" + name +".txt";
		try{
			FileInputStream fileIn = new FileInputStream("C:/Users/Kapil/workspace/SentimentAnalyzer/Resources/bowArrayObj.ser");
			ObjectInputStream oIn = new ObjectInputStream(fileIn);
			obj = (bowArray)oIn.readObject();
			oIn.close();
			fileIn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
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
				int x = obj.words.indexOf(str);
				if(x == -1){
					if(type.equalsIgnoreCase("neg")){
						obj.words.add(str);
						obj.neg.add(1);
						obj.pos.add(0);
					}else{
						obj.words.add(str);
						obj.neg.add(0);
						obj.pos.add(1);
					}
				}else{
					if(type.equalsIgnoreCase("neg")){
						int temp = obj.neg.get(x);
						obj.neg.remove(x);
						obj.neg.add(x,temp + 1);
					}else{
						int temp = obj.pos.get(x);
						obj.pos.remove(x);
						obj.pos.add(x,temp + 1);
					}
				}
			}
		}
		in.close();
		}catch(FileNotFoundException fe){
			fe.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
	
}
