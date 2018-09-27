/**
 * WordGram objects represent a k-gram of strings/words.
 * 
 * @author Victor Chu
 * vic4
 */

public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;         // cached hash value

	/**
	 * Create WordGram which we do given a myWord array, as well
	 * as a myToString and myhash. We give myToString and 
	 * myHash by instantiating these throughout
	 * the Wordgram class
	 * @param source
	 * @param start
	 * @param size
	 */
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];//holds the size of the wordGram
		// TODO: initialize myWords and ...
		
		//we need to copy our source array to myWords
		for(int i = 0; i < size; i++)
		{
			myWords[i] = source[start+i];//copy each value
		}
		
		myToString = null;//used for toString later on
		myHash = 0;
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * Just return the size of myWords array, which is the same size
	 * as the Wordgram object anyway. 
	 * Returns size of the string 
	 * @return integer of length 
	 */
	public int length(){
		// TODO: change this
		//takes the size of our Wordgram
		return myWords.length;
	}


	//.equals checks to see if WordGrams are equal to each other
	//key thing is turning our wordgram into a string
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}

	    // TODO: complete this method
		WordGram baller = (WordGram) o;//we need to cast this into a WordGram object
		String objectString = baller.toString();//we need to make this a string
		
		if(objectString.equals(this.toString())) //check if the two strings are equal
			//gotta use this.toString() since we are comparing the strings of the wordgrams
		{
			return true;
		}
		
		return false;
	}

	@Override
	/**
	 * Returns the hashcode of our wordgram
	 * Key thing is really to turn it into
	 * a string first. 
	 */
	public int hashCode(){
		// TODO: complete this method

		if(myHash == 0)//we only want to change the myHash once. Condition
		{
			//we use 'this' because we are referring to our object
			myHash = this.toString().hashCode();//make a string first, then hashcode
		}
		return myHash;
	}
	

	/**
	 * 
	 * Create and complete this comment
	 * 
	 * We have to make a holder array, since we can't 
	 * change WordGram since it is immutable
	 * 
	 * @param last is last String of returned WordGram
	 * @return wordgram
	 */
	public WordGram shiftAdd(String last) {
		// TODO: Complete this method
		String[] tempArray = new String[myWords.length];//shift our values first 
		
		for(int i = 0; i < tempArray.length-1;i++)
		{
			tempArray[i] = myWords[i+1];//shift our values
			
		}
		tempArray[myWords.length-1] = last;//we add the value to the end
		
		WordGram wg = new WordGram(tempArray,0,tempArray.length);
		
		return wg;
	}

	
	@Override
	/**
	 * This method will allow us to make our wordgram,
	 * into a string. Important thing is to use .join
	 * but also make sure that we don't use this again
	 * so we check for null
	 * return a string version of our wordgram
	 */
	public String toString(){
		// TODO: Complete this method
		if(myToString == null)
			
		{
			myToString = String.join(" ", myWords);//joins our stuff in our array
		}
		return myToString;
	}
}
