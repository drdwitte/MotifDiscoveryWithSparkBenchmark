package models.motifs;

/**
 * Created by ddewitte on 23.09.15.
 */
public interface Pattern {
    Pattern clonePattern();
    int numberOfDegPositions();
}
