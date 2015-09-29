package factories;

import models.alphabets.Alphabet;

/**
 * Created by ddewitte on 29.09.15.
 */
public interface PatternFactory {
    void createEmptyMotif();
    Alphabet getAlphabet();
}
