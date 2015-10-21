package models.alphabets;

import models.motifs.PatternContent;

import java.util.List;

/**
 * Created by ddewitte on 02.10.15.
 */
public interface CharacterIteratorProvider {

    public CharacterIterator getExactCharIterator();
    public CharacterIterator getFullAlphabetIterator();
    public CharacterIterator getCustomIterator(PatternContent content);
}
