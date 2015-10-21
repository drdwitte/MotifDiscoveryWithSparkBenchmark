package datastructures.search;

import factories.IUPACFactory;
import factories.PatternFactory;
import models.alphabets.Alphabet;
import models.alphabets.CharacterIterator;
import models.alphabets.CharacterIteratorProvider;
import models.alphabets.StringIterator;
import models.motifs.Pattern;
import toolbox.NotImplementedException;

import java.util.function.Function;

/**
 * Created by ddewitte on 25.09.15.
 */
public class AngleRestrictedSearchSpace extends DiskShapedSearchSpace {

    private String rootPrefix;

    public AngleRestrictedSearchSpace(int kmin, int kmax, int maxDegPos, Alphabet alph, CharacterIteratorProvider provider, String rootPrefix) {
        super(kmin,kmax,maxDegPos,alph,provider);
        this.rootPrefix=rootPrefix;
        testPrefix();
    }

    private void testPrefix(){
        int numDeg=0;
        for (int i=0; i<rootPrefix.length(); i++){
            if (!alphabet.contains(rootPrefix.charAt(i))){
                maxLength=i;
                return;
            } else {
                if (alphabet.isDegenerate(rootPrefix.charAt(i))){
                    numDeg++;
                    if (numDeg > maxDegeneratePositions){
                        maxLength=i;
                        return;
                    }
                }
            }
        }
    }


    @Override
    public boolean test(Pattern p) {
        int commonLength = p.length()>rootPrefix.length() ? rootPrefix.length() : p.length();
        for (int i=0; i<commonLength; i++){
            if (p.charAt(i)!=rootPrefix.charAt(i))
                return false;
        }
        return super.test(p) ;
    }

    @Override
    public SearchSpaceNavigator getSSNavigator(PatternFactory patternFactory) {

        return new AngleRestrictedNavigator(patternFactory);
    }

    class AngleRestrictedNavigator extends ArrayBasedNavigator {



        public AngleRestrictedNavigator(PatternFactory factory){

            super.siblingNavigators = new CharacterIterator[maxLength+1];
            super.siblingNavigators[0] = new StringIterator(""); //root has no siblings
            super.trail = factory.createEmptyPattern();



        }




        @Override
        public boolean hasChild() {
            return d<siblingNavigators.length-1;
        }

        @Override
        public CharacterIterator getChildIterator() {
            if (d>=rootPrefix.length()){
                if (super.trail.numberOfDegPositions() >= maxDegeneratePositions) {
                    return provider.getExactCharIterator();
                } else {
                    return provider.getFullAlphabetIterator();
                }
            } else {
                char c = rootPrefix.charAt(d);
                if (super.trail.numberOfDegPositions() >= maxDegeneratePositions
                        && alphabet.isDegenerate(c)
                        ) {
                    return new StringIterator("");
                }
                else {
                    return new StringIterator("" + c);


                }


            }
        }

        @Override
        public boolean notInSearchSpaceYet() {
            return d<minLength;
        }
    }
}
