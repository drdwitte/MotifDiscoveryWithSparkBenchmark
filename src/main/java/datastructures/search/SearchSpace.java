package datastructures.search;

import factories.PatternFactory;
import models.alphabets.Alphabet;
import models.motifs.Pattern;

/**
 * Created by ddewitte on 23.09.15.
 */
public interface SearchSpace {

    SearchSpaceNavigator getSSNavigator(PatternFactory patternFactory);
    Alphabet getAlphabet();
    boolean test(Pattern p);
}
