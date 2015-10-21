package models.alphabets;

import models.motifs.PatternContent;
import toolbox.NotImplementedException;

/**
 * Created by ddewitte on 02.10.15.
 */
public class CharacterIteratorPool implements CharacterIteratorProvider {


    @Override
    public CharacterIterator getExactCharIterator() {
        throw new NotImplementedException();
    }

    @Override
    public CharacterIterator getFullAlphabetIterator() {
        throw new NotImplementedException();
    }

    @Override
    public CharacterIterator getCustomIterator(PatternContent content) {
        throw new NotImplementedException();
    }
}
