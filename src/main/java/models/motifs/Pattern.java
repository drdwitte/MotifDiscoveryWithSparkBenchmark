package models.motifs;

/**
 * Created by ddewitte on 23.09.15.
 */
public interface Pattern extends Comparable<Pattern> {

    Pattern clonePattern();
    int numberOfDegPositions();
    String toString();
    int length();

    void append(Character c);
    Character charAt(int i);
    //Pattern createContent();
    void pop();
    //Pattern getComplement();
    //int hashCode();
    //boolean equals(Object o);
    Character last();
}
