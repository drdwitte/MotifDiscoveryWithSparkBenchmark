package datastructures.search;

import datastructures.indexing.DSNavigator;

/**
 * Created by ddewitte on 23.09.15.
 */
public interface SearchSpaceNavigator {

    boolean hasSibling();
    boolean hasChild();
    boolean hasParent();

    char toSibling();
    char toFirstChild();
    void toParent();

    boolean inSearchSpace();

    /**
     * For a given given search space navigator take a DSNavigator and synchronize
     * the motif trials
     * @param dsNav a discovery structure navigator listening for navigation updates
     *              in the SearchSpaceNavigator
     */
    void attachDSNavigator(DSNavigator dsNav);
}
