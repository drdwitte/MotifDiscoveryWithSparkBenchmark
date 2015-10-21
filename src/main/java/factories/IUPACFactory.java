package factories;

import models.alphabets.Alphabet;
import models.alphabets.IUPACAlphabet;
import models.motifs.IUPACPattern;
import models.motifs.Pattern;
import toolbox.NotImplementedException;

/**
 * Created by ddewitte on 29.09.15.
 */
public class IUPACFactory implements PatternFactory {

    private Alphabet alphabet;

    public IUPACFactory(IUPACAlphabet.IUPACType t){
        alphabet  = new IUPACAlphabet(t);
    }

    @Override
    public Pattern createEmptyPattern() {

        return new IUPACPattern();


    }

    @Override
    public Alphabet getAlphabet() {
        return alphabet;
    }
}
