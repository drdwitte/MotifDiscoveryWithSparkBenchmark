package models.alphabets;

import models.motifs.PatternContent;

/**
 * Created by ddewitte on 02.10.15.
 */
public class CharacterIteratorFactory implements CharacterIteratorProvider {
    private Alphabet alph;


    public CharacterIteratorFactory(Alphabet alph){
        this.alph=alph;
    }

    @Override
    public CharacterIterator getExactCharIterator(){
        return alph.exactCharsIterator();
    }

    @Override
    public CharacterIterator getFullAlphabetIterator(){
        return alph.getAllCharsIterator();
    }

    @Override
    public CharacterIterator getCustomIterator(PatternContent content){
        return new CharacterSetIterator(content.chars());
    }
}
