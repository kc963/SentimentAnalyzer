package BagOfWords;

import java.util.ArrayList;

public class bowArray implements java.io.Serializable{

	public ArrayList<String> words = new ArrayList<String>();
	public ArrayList<String> stopwords = new ArrayList<String>();
	public ArrayList<Integer> pos = new ArrayList<Integer>();
	public ArrayList<Integer> neg = new ArrayList<Integer>();
	public ArrayList<Integer> bog = new ArrayList<Integer>();
	public long total_pos;
	public long total_neg;
	public long total_words;
	public float prob_pos;
	public float prob_neg;
	
	bowArray(){
		total_pos = total_neg = total_words = 0;
		prob_pos = prob_neg = 0;
	}
	
}
