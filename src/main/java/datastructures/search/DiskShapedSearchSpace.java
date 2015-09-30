package datastructures.search;

import datastructures.indexing.DSNavigator;
import factories.PatternFactory;
import models.alphabets.Alphabet;
import models.alphabets.CharacterIterator;
import models.motifs.Pattern;
import toolbox.NotImplementedException;

import java.util.StringJoiner;

/**
 * Created by ddewitte on 24.09.15.
 */
public class DiskShapedSearchSpace implements SearchSpace {

    private int minLength;
    private int maxLength;
    private int maxDegeneratePositions;
    private Alphabet alphabet;
    private DSNavigator dsNav = null;

    /**
     *
     * @param kmin minimum motif length
     * @param kmax maximum motif length
     * @param maxDegPos maximum number of degenerate characters
     * @param alph motif alphabet
     */
    public DiskShapedSearchSpace(int kmin, int kmax, int maxDegPos, Alphabet alph) {
        this.minLength=kmin;
        this.maxLength=kmax;
        this.maxDegeneratePositions=maxDegPos;
        this.alphabet=alph;
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

    public String toString(){

        StringJoiner sj = new StringJoiner(",", "(", ")");
        sj.add(""+minLength)
                .add("" + maxLength)
                .add("" + maxDegeneratePositions);
        return sj.toString();
    }

    /**
     * @return maximum number of basepair strings matching with a motif (for ex.: ANA -> deg=4 -> AAA,ATA,ACA,AGA
     */
    /*public int getMaximumDegeneracy() {
        int maxDeg = 1;
        for (int i=0; i<maxDegeneratePositions; i++){
            maxDeg*=alphabet.getMaxDegPerChar();
        }
        return maxDeg;

    }*/

    @Override
    public SearchSpaceNavigator getSSNavigator(PatternFactory factory) {
        return new IUPACSearchSpaceNavigator(factory);
    }



    class IUPACSearchSpaceNavigator implements SearchSpaceNavigator {

        //d=0 for root node of search space
        private int d;
        private CharacterIterator [] siblingNavigators;
        private Pattern trail;


        /**
         * Constructor
         */
        public IUPACSearchSpaceNavigator(PatternFactory factory){
            this.d=0;
            siblingNavigators = new CharacterIterator[maxLength+1];
            siblingNavigators[0] = new CharacterIterator(""); //root has no siblings
            for (int i=1; i<=maxLength; i++){
                siblingNavigators[i] = alphabet.getAllCharsIterator();
            }
            this.trail = factory.createEmptyPattern();

        }

        @Override
        public boolean hasSibling() {
            return siblingNavigators[d].hasNext();
        }

        @Override
        public boolean hasChild() {
            return d<siblingNavigators.length-1;
        }

        @Override
        public boolean hasParent() {
            return d>0;
        }

        @Override
        public void toSibling() {
            trail.pop();
            trail.append(siblingNavigators[d].next());
        }

        @Override
        public void toFirstChild() {
            d++;
            siblingNavigators[d].reset();
            trail.append(siblingNavigators[d].first());
        }

        @Override
        public void toParent() {
            d--;
            trail.pop();

        }

        @Override
        public boolean largerThanOuterRadius() {
            return trail.numberOfDegPositions()>maxDegeneratePositions || d>maxLength;
        }

        @Override
        public boolean smallerThanInnerRadius() {
            return d<minLength;
        }

        @Override
        public Pattern trail() {
            return trail;
        }

        @Override
        public void attachDSNavigator(DSNavigator dsNav) {
            for (int i=0; i<d; i++){
            }

            throw new NotImplementedException();
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<d; i++){
                sb.append(siblingNavigators[d].current());
            }
            return sb.toString();
        }
    }
}
