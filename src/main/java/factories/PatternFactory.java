package factories;

import models.alphabets.Alphabet;
import models.motifs.Pattern;

/**
 * Created by ddewitte on 29.09.15.
 */
public interface PatternFactory {
    Pattern createEmptyPattern();
    Alphabet getAlphabet();
}
