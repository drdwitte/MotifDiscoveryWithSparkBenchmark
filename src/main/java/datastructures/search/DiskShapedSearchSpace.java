package datastructures.search;

import datastructures.indexing.DSNavigator;
import factories.PatternFactory;
import models.alphabets.Alphabet;
import models.alphabets.CharacterIterator;
import models.alphabets.CharacterIteratorProvider;
import models.alphabets.StringIterator;
import models.motifs.Pattern;
import toolbox.NotImplementedException;

import java.util.Optional;
import java.util.StringJoiner;

/**
 * Created by ddewitte on 24.09.15.
 */
public class DiskShapedSearchSpace implements SearchSpace {

    protected int minLength;
    protected int maxLength;
    protected int maxDegeneratePositions;
    protected Alphabet alphabet;
    protected CharacterIteratorProvider provider;

    /**
     *
     * @param kmin minimum motif length
     * @param kmax maximum motif length
     * @param maxDegPos maximum number of degenerate characters
     * @param alph motif alphabet
     */
    public DiskShapedSearchSpace(int kmin, int kmax, int maxDegPos, Alphabet alph, CharacterIteratorProvider provider) {
        this.minLength=kmin;
        this.maxLength=kmax;
        this.maxDegeneratePositions=maxDegPos;
        this.alphabet=alph;
        this.provider=provider;
    }

    /**
     * GETTERS
     */
    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getMaxNumberOfDegeneratePositions() {
        return maxDegeneratePositions;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    @Override
    public boolean test(Pattern p) {

        boolean test = p.length() >= minLength
                &&  p.length() <= maxLength
                && p.numberOfDegPositions() <= maxDegeneratePositions
                ;
        for (int i=0; i<p.length(); i++){
            if (!alphabet.contains(p.charAt(i)))
                return false;
        }
        return test;

    }

    public String toString(){

        StringJoiner sj = new StringJoiner(",", "(", ")");
        sj.add(""+minLength)
                .add("" + maxLength)
                .add("" + maxDegeneratePositions);
        return sj.toString();
    }

    @Override
    public SearchSpaceNavigator getSSNavigator(PatternFactory factory) {
         return new DiskShapedSpaceNavigator(factory);
    }

    protected class DiskShapedSpaceNavigator extends ArrayBasedNavigator {

        /**
         * Constructor
         */
        public DiskShapedSpaceNavigator(PatternFactory factory){

            super.siblingNavigators = new CharacterIterator[maxLength+1];
            super.siblingNavigators[0] = new StringIterator(""); //root has no siblings
            super.trail = factory.createEmptyPattern();
        }

        @Override
        public CharacterIterator getChildIterator() {
            if (super.trail.numberOfDegPositions() == maxDegeneratePositions){
                return provider.getExactCharIterator();
            }
            return provider.getFullAlphabetIterator();
        }

        @Override
        public boolean notInSearchSpaceYet() {
            return d<minLength;
        }
    }
}
