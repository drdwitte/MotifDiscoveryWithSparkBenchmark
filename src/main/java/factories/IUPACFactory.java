package factories;

import models.alphabets.Alphabet;
import models.alphabets.IUPACAlphabet;
import toolbox.NotImplementedException;

/**
 * Created by ddewitte on 29.09.15.
 */
public class IUPACFactory implements PatternFactory {

    private Alphabet alphabet;

    public IUPACFactory(IUPACAlphabet.IUPACType t){
        throw new NotImplementedException();
    }

    @Override
    public void createEmptyMotif() {
        throw new NotImplementedException();
    }

    @Override
    public Alphabet getAlphabet() {
        return alphabet;
    }
}
