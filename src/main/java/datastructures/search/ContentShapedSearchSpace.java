package datastructures.search;

import factories.PatternFactory;
import models.alphabets.Alphabet;
import toolbox.NotImplementedException;

/**
 * Created by ddewitte on 25.09.15.
 */
public class ContentShapedSearchSpace implements SearchSpace {

    @Override
    public SearchSpaceNavigator getSSNavigator(PatternFactory mFact) {
        throw new NotImplementedException();    }

    @Override
    public Alphabet getAlphabet() {
        throw new NotImplementedException();    }
}
