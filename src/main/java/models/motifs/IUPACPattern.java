package models.motifs;

import models.alphabets.Alphabet;
import models.alphabets.CharacterIterator;
import models.alphabets.IUPACAlphabet;
import models.alphabets.StringIterator;

/**
 * Created by ddewitte on 29.09.15.
 */
public class IUPACPattern implements Pattern {

    private static final Alphabet alphabet = new IUPACAlphabet(IUPACAlphabet.IUPACType.FULL);
    private StringBuilder pattern;
    private int numberOfDegeneratePositions=0;

    public IUPACPattern(){
        pattern = new StringBuilder();
    }

    public IUPACPattern(String s){
        this.pattern=new StringBuilder(s);
        this.numberOfDegeneratePositions=calculateNumberOfDegeneratePositions(s);
    }

    private IUPACPattern(String s, int numberOfDegPos){
        this.pattern = new StringBuilder(s);
        this.numberOfDegeneratePositions=numberOfDegPos;
    }

    private int calculateDegeneracy(String s){
        CharacterIterator iterator = new StringIterator(s);
        int degeneracy = 1;
        while (iterator.hasNext()){
            degeneracy*=alphabet.getNumberOfMatchingCharacters(iterator.next());
        }
        return degeneracy;
    }

    /**
     * @return a deep copy of the pattern
     */
    @Override
    public Pattern clonePattern() {
        return new IUPACPattern(this.pattern.toString(),this.numberOfDegeneratePositions);
    }

    /**
     * @return number of character positions which don't contain ACGT
     */
    @Override
    public int numberOfDegPositions() {
        return numberOfDegeneratePositions;
    }


    public static int calculateNumberOfDegeneratePositions(String s){
        CharacterIterator iterator = new StringIterator(s);
        int numberOfDegeneratePositions = 0;
        while (iterator.hasNext()){
            numberOfDegeneratePositions+=addDegPositionContribution(iterator.next());
        }
        return numberOfDegeneratePositions;
    }

    /**
     * @param c character to be evaluated
     * @return 0 if ACGT or 1 if regex character
     */
    private static int addDegPositionContribution(Character c){
        return (alphabet.getNumberOfMatchingCharacters(c)>1)?1:0;
    }

    @Override
    public int length() {
        return pattern.length();
    }

    @Override
    public void append(Character c) {
        pattern.append(c);
        numberOfDegeneratePositions+=addDegPositionContribution(c);

    }

    /*@Override
    public boolean equals(Object o) {
        if (o instanceof IUPACMotif){
            IUPACMotif m =(IUPACMotif) o;
            return m.motif.toString().equals(motif.toString());
        }
        return false;
    }*/

    /*@Override
    public int hashCode() {
        return motif.toString().hashCode();
    }*/

    @Override
    public String toString() {
        return pattern.toString();
    }

    @Override
    public Character charAt(int i) {
        return pattern.charAt(i);
    }

    @Override
    public int compareTo(Pattern o) {
        return this.toString().compareTo(o.toString());
    }


    /*@Override
    public MotifContent createContent() {
        return new IUPACContent(this);
    }*/



    @Override
    public void pop() {
        numberOfDegeneratePositions -= addDegPositionContribution(last());
        pattern.deleteCharAt(lastPos());

    }

    @Override
    public Character last() {
        return pattern.charAt(lastPos());
    }

    private int lastPos(){
        return pattern.length()-1;
    }

    /*@Override
    public Pattern getComplement() {
        StringBuilder s = new StringBuilder();
        for (int i=motif.length()-1; i>=0; i--){
            s.append(alphabet.getComplement(motif.charAt(i)));
        }
        return new IUPACPattern(s.toString(),numberOfDegeneratePositions);
    }*/

}

