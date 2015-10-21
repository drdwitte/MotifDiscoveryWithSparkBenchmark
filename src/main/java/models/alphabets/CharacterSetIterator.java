package models.alphabets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by ddewitte on 30.09.15.
 */
public class CharacterSetIterator implements CharacterIterator {

    private Iterator<Character> it;
    private Set<Character> charSet;

    public CharacterSetIterator(Set<Character> s){
        this.charSet = new HashSet<>();
        s.forEach(this.charSet::add);
        this.it = this.charSet.iterator();
    }

    @Override
    public void reset() {
        it=charSet.iterator();

    }

    /*@Override
    public char first() {
        charSet.
    }*/

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public Character next() {
        return it.next();
    }
}
