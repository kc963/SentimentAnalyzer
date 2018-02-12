package BagOfWords;

public class mainBOG {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BOG obj = new BOG();
		//System.out.println("Classifier's Data");
		//obj.display();
		//obj.train_neg("negative1.txt");
		//System.out.println("NEGATIVE TRAINING END");
		//obj.train_pos("positive1.txt");
		//System.out.println("POSITIVE TRAINING END");
		//System.out.println("Classifier's Data");
		
		obj.display();
		obj.classify("The movie was great. I really enjoyed it.");
		obj.classify("The movie was pathetic. Total waste.");
		obj.classify("bad movie");
		obj.classify("I had fun.");
	}

}
