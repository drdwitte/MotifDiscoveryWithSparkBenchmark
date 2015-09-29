package datastructures.search;

import factories.PatternFactory;
import models.alphabets.Alphabet;
import toolbox.NotImplementedException;

/**
 * Created by ddewitte on 25.09.15.
 */
public class AngleRestrictedSearchSpace implements SearchSpace {


    @Override
    public SearchSpaceNavigator getSSNavigator(PatternFactory patternFactory) {
        throw new NotImplementedException();    }

    @Override
    public Alphabet getAlphabet() {
        throw new NotImplementedException();    }
}
