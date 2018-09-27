import java.io.*;
import java.util.*;

/**
 * 
 * @author Victor
 *vic4
 */
public class WordGramDriver {
	public final static int WSIZE = 10
			;//changes the size of the wordgram

	public static int benchmark(Set<WordGram> set, String filename) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filename));

		ArrayList<String> list = new ArrayList<>();//make an arraylist
		while (scan.hasNext()) {//go through file
			String s = scan.next();//need to keep track of the item
			list.add(s);//add it to our set. 
		}
		scan.close();
		String[] words = list.toArray(new String[0]);//Make an array
		for(int k=0; k < words.length - WSIZE + 1; k += 1) {
			WordGram wg = new WordGram(words,k,WSIZE);//call wordgram
			set.add(wg);//add our wordgram, but 
		}
		return words.length - WSIZE + 1;//return how many unique wordgrams
	}
	public static int benchmarkShift(Set<WordGram> set, String filename) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filename));

		String[] words = new String[WSIZE];//use an array of a set size
		for(int k=0; k < WSIZE; k += 1) {
			words[k] = scan.next();//loop through and 
									//use the scanner to get words
		}
		int count = WSIZE;
		WordGram current = new WordGram(words,0,WSIZE);//have a current wordgram
		set.add(current);//add our wordgram to our hashset
		
		while (scan.hasNext()) {
			String s = scan.next();		
			current = current.shiftAdd(s);//add a word to the wordgram
			set.add(current);//add the rest of our Wordgram
			count += 1;
		}
		scan.close();
		return count - WSIZE + 1;//return it
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		String[] plays = {"allswell.txt",
				"caesar.txt",	"errors.txt",
				"hamlet.txt", "likeit.txt", "macbeth.txt",
				"romeo.txt", "tempest.txt"};

		HashSet<WordGram> hset = new HashSet<>();
		HashSet<WordGram> hset2 = new HashSet<>();

		double start,end,time;

		// calculate stats for running benchmark
		
		int total = 0;
		start = System.nanoTime();
		for(String play : plays) {
			String fname = "data/shakes/" + play;
			total += benchmark(hset,fname);
		}
		end = System.nanoTime();
		time = (end-start)/1e9;

		System.out.printf("benchmark time: %1.3f, size = %d\n",time,hset.size());
		System.out.printf("total # wgs = %d\n",total);
		
		// calculate stats for running benchmarkShift
		
		total = 0;
		start = System.nanoTime();
		for(String play : plays) {
			String fname = "data/shakes/" + play;
			total += benchmarkShift(hset2,fname);
		}
		end = System.nanoTime();
		time = (end-start)/1e9;

		System.out.printf("\nbenchmarkShift time: %1.3f, size = %d\n",time,hset2.size());
		System.out.printf("total # wgs = %d\n",total);
		
		HashSet<WordGram> copy = new HashSet<>(hset);
		HashSet<WordGram> copy2 = new HashSet<>(hset2);
		
		copy.removeAll(hset2);
		copy2.removeAll(hset);
		
		System.out.printf("\nsize of set difference %d\n", copy.size());
		System.out.printf("size of set2 difference %d\n", copy2.size());
	}
}
