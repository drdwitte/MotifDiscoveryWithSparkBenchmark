package models.alphabets;

import java.util.Iterator;
import java.util.Map;

public abstract class Alphabet implements Iterable<Character> {
	protected final String allChars;
	protected final Map<Character, String> matchingChars;
	protected final Map<Character, Character> complementMap;

	/**
	 * Alphabet constructor
	 * @param allChars all characters in alphabet
	 * @param matchingChars mapping alphabet characters to their matching dna characters
	 * @param complementMap mapping characters to their dna complement
	 */
	protected Alphabet(String allChars, Map<Character, String> matchingChars
			, Map<Character, Character> complementMap) {
		this.allChars = allChars;
		this.matchingChars = matchingChars;
		this.complementMap = complementMap;
	}

	/**
	 * @return generate random alphabet character
	 */
	public Character generateRandomChar() {
		double random = Math.random() * allChars.length();
		int charID = (int) random;
		return allChars.charAt(charID);
	}

	/**
	 * Overriding Iterable interface
	 * @return Iterator<Character>
	 */
	@Override
	public Iterator<Character> iterator() {
		return new StringIterator(allChars);
	}

	/**
	 * @return Characteriterator for all characters in alphabet
	 */
	public CharacterIterator getAllCharsIterator(){
		return new StringIterator(allChars);
	}

	/**
	 * @return characteriterator over all basepairs
	 */
	public abstract CharacterIterator exactCharsIterator();

	/**
	 * @return characteriterator over all non-basepairs
	 */
	public abstract CharacterIterator degenerateCharsIterator();

	/**
	 * @return Characteriterator for all dna bases matching with an alphabet character
	 */
	public CharacterIterator getMatchingCharactersIterator(Character c){
		return new StringIterator(matchingChars.get(c));
	}

	/**
	 * @return number of dna bases macthing with an alphabet character
	 */
	public int getNumberOfMatchingCharacters(Character c){
		return matchingChars.get(c).length();
	}

	/**
	 * @return true if character is not a basepair
	 */
	public boolean isDegenerate(Character c){
		return matchingChars.get(c).length()>1;
	}

	/**
	 * @return all character alphabet as a string
	 */
	public String getAllChars() {
		return allChars;
	}

	/**
	 * @return complementary alphabet character (for example A->T, C->G, also degenerates!)
	 */
	public Character getComplement(char c) {
		return complementMap.get(c);
	}

	/**
	 *
	 * @return maximum number of basepairs matching with an alphabet character (for example N=A|C|G|T = 4)
	 */
	public abstract int getMaxDegPerChar();

	public boolean contains(Character character){
		return matchingChars.containsKey(character);
	}

	@Override
	public int hashCode() {
		return allChars.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Alphabet that = (Alphabet) o;

		if (!allChars.equals(that.allChars)) return false;

		return true;
	}
}
