package datastructures.search;

import datastructures.indexing.DSNavigator;
import models.alphabets.CharacterIterator;
import models.motifs.Pattern;

import java.util.Optional;

/**
 * Created by ddewitte on 30.09.15.
 */
abstract class ArrayBasedNavigator implements SearchSpaceNavigator {

    int d=0;
    CharacterIterator[] siblingNavigators;
    Pattern trail;
    Optional<DSNavigator> dsNav = Optional.ofNullable(null);

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
        char newChar = siblingNavigators[d].next();
        trail.append(newChar);

        if (dsNav.isPresent()){
            dsNav.get().backtrack();
            dsNav.get().jumpTo(newChar);
        }
    }

    public abstract CharacterIterator getChildIterator();

    @Override
    public void toFirstChild() {
        CharacterIterator it = getChildIterator();
        d++;
        siblingNavigators[d] = it;

        char newChar = siblingNavigators[d].next();
        trail.append(newChar);

        if (dsNav.isPresent()){
            dsNav.get().jumpTo(newChar);
        }
    }

    @Override
    public void toParent() {
        d--;
        trail.pop();

        if (dsNav.isPresent()){
            dsNav.get().backtrack();
        }

    }

    @Override
    public abstract boolean notInSearchSpaceYet();

    @Override
    public Pattern trail() {
        return trail;
    }

    @Override
    public void attachDSNavigator(DSNavigator dsNav) {
        //System.err.println(d);
        if (d==0) {
            this.dsNav = Optional.ofNullable(dsNav);
        } else {
            throw new RuntimeException("Not implemented navigator binding for navs not both @root");
        }

    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<d; i++){
            sb.append(siblingNavigators[d].toString());
        }
        return sb.toString();
    }

    @Override
    public void reset(){
        d=0;
        while (trail.length()>0)
            trail.pop();
    }
}
