package datastructures.indexing;

import models.motifs.Pattern;
import motifconservation.INInfo;

/**
 * Created by ddewitte on 23.09.15.
 */
public interface DSNavigator {

    void jumpTo(char edge);

    void backtrack();

    boolean hasMatches();

    INInfo getInfo();


}
