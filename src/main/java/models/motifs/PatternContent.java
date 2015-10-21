package models.motifs;

import java.util.Set;

/**
 * Created by ddewitte on 29.09.15.
 */
public interface PatternContent {
    PatternContent cloneContent();
    void add(Character c);
    void pop(Character c);

    Set<Character> chars();

}
