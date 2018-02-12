# SentimentAnalyzer

Implemented 
	- Bag of Words Classifier
		Considers each word as a single unit positive or negative word.
		Counts the total sum of all the numbers with negative words having 
			the number as -1 and positive having +1.
			
	- Advance Bag of Words Classifier
		Assigns the weight to each word using its number of occurrences in 
			positive or negative training data.
		[The word that appears 100 times in positive data and 10 times in 
				negative data will have positive count to be 100/10 = +10]
		Sums the counts of each number and gives the polarity of the 
				sentence as a whole.
			
	- Naive Bayes Classifier
		Uses Bayes theorem of probability to calculate the probability of 
			the word being positive and negative.
		Calculates the total probability of the sentence as a whole using 
			each word and returns the results.