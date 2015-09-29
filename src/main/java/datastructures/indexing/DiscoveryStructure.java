package datastructures.indexing;

import models.alphabets.Alphabet;

/**
 * Created by ddewitte on 23.09.15.
 */
public interface DiscoveryStructure {
    DSNavigator getDSNavigator(Alphabet alphabet);
}
