package models.alphabets;

/**
 * Created by ddewitte on 30.09.15.
 */
public class StringIterator implements CharacterIterator {

    protected int pos = 0;
    protected String text;

    /**
     * Constructor
     * @param text over which to iterate, deep copy taken since string is immutable!
     */
    public StringIterator(String text) {
        this.text = text;
    }

    @Override
    public boolean hasNext() {
        return pos < text.length();
    }

    /**
     * @return current character in iteration + increment iterator
     */
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
    @Override
    public void reset(){
        pos=0;
    }

    /**
     * @return first character in text
     */
    /*@Override
    public char first() {
        return text.charAt(0);
    }*/


    @Override
    public String toString(){
        StringBuilder iteratorRepr = new StringBuilder(text);
        iteratorRepr.insert(pos,"->");
        return iteratorRepr.toString();
    }

}
