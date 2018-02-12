package NaiveBayes;

public class mainNB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		NBClassifier obj = new NBClassifier();
		obj.classify("The movie was great. I really enjoyed it.");
		obj.classify("The movie was pathetic. I hated it.");
		obj.classify("bad movie");
		obj.classify("I had fun.");
	}

}
