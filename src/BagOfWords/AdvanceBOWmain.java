package BagOfWords;

public class AdvanceBOWmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AdvanceBOW obj = new AdvanceBOW();
		obj.display();
		obj.classify("The movie was great. I really enjoyed it.");
		obj.classify("The movie was pathetic. Total waste.");
		obj.classify("bad movie");
		obj.classify("I had fun.");
		
	}

}
