package datastructures.search;

import factories.PatternFactory;
import models.alphabets.*;
import models.motifs.IUPACContent;
import models.motifs.IUPACPattern;
import models.motifs.Pattern;
import models.motifs.PatternContent;
import toolbox.NotImplementedException;

/**
 * Created by ddewitte on 25.09.15.
 */
public class ContentShapedSearchSpace extends DiskShapedSearchSpace {

    private PatternContent content;

    public ContentShapedSearchSpace(Alphabet alph, Pattern p, CharacterIteratorProvider provider) {
        super(p.length(),p.length(),p.numberOfDegPositions(),alph,provider);
        this.content=new IUPACContent((IUPACPattern)p);
    }

    @Override
    public boolean test(Pattern p) {
        PatternContent pc = content.cloneContent();
        for (int i=0; i<p.length(); i++) {
            if (pc.chars().contains(p.charAt(i))) {
                pc.pop(p.charAt(i));
            }
            else return false;
        }
        return super.test(p);
    }

    @Override
    public SearchSpaceNavigator getSSNavigator(PatternFactory mFact) {
        return new ContentShapedNavigator(mFact);
    }


    class ContentShapedNavigator extends ArrayBasedNavigator {

        private PatternContent remainingContent = content.cloneContent();

        public ContentShapedNavigator(PatternFactory factory){

            super.siblingNavigators = new CharacterIterator[maxLength+1];
            super.siblingNavigators[0] = new StringIterator(""); //root has no siblings
            super.trail = factory.createEmptyPattern();
        }

        @Override
        public void toFirstChild() {
            super.toFirstChild();
            remainingContent.pop(super.trail.last());
        }

        @Override
        public void toSibling() {
            remainingContent.add(super.trail.last());
            super.toSibling();
            remainingContent.pop(super.trail.last());
        }

        @Override
        public CharacterIterator getChildIterator() {
            return new CharacterSetIterator(remainingContent.chars());
        }

        @Override
        public void toParent() {
            remainingContent.add(super.trail.last());
            super.toParent();

        }

        @Override
        public boolean notInSearchSpaceYet() {
            return d<minLength;
        }
    }
}
