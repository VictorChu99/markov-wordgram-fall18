/**
 * WordGram objects represent a k-gram of strings/words.
 * 
 * @author Victor Chu
 *
 */

public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;         // cached hash value

	/**
	 * Create WordGram (add comments)
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
		
		myToString = null;
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
	 * Complete this comment
	 * Returns size of the string 
	 * @return
	 */
	public int length(){
		// TODO: change this
		//takes the size of our Wordgram
		return myWords.length;
	}


	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}

	    // TODO: complete this method
		WordGram baller = (WordGram) o;//we need to cast this into a WordGram object
		String objectString = baller.toString();//we need to make this a string
		
		if(objectString.equals(this.toString())) //check if the two strings are equal
		{
			return true;
		}
		
		return false;
	}

	@Override
	public int hashCode(){
		// TODO: complete this method

		if(myHash == 0)//we only want to change the myHash once. Condition
		{
			//we use 'this' because we are referring to our object
			myHash = this.toString().hashCode();
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
	 * @return
	 */
	public WordGram shiftAdd(String last) {
		// TODO: Complete this method
		String[] tempArray = new String[myWords.length];
		
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
	 */
	public String toString(){
		// TODO: Complete this method
		if(myToString == null)
		{
			myToString = String.join(" ", myWords);//joins our stuff in our string
		}
		return myToString;
	}
}
