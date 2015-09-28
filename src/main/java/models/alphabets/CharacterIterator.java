package models.alphabets;

import java.util.Iterator;

public class CharacterIterator implements Iterator<Character> {

	protected int pos = 0;
	protected String text;

	/**
	 * Constructor
	 * @param text over which to iterate, deep copy taken since string is immutable!
	 */
	public CharacterIterator(String text) {
		this.text = text;
	}

	@Override
	public boolean hasNext() {
		return pos < text.length();
	}

	@Override
	public Character next() {
		return text.charAt(pos++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(
				"Removing alphabet chars not allowed!");
	}

	/**
	 * Reset iterator -> back to position 0 in text
	 */
	public void reset(){
		pos=0;
	}

	/**
	 * @return first character in text
	 */
	public char first() {
		return text.charAt(0);
	}

	/**
	 *
	 * @return current character in iteration
	 */
	public char current(){
		return text.charAt(pos);
	}

}
