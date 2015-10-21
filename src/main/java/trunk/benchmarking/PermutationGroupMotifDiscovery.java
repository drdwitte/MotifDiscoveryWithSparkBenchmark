package trunk.benchmarking;

/**
 * Created by ddewitte on 19.10.15.
 */
public class PermutationGroupMotifDiscovery {

    //NOTE it is possible to introduce branch and bound here as well!

    // a certain permutation group will only be investigated if
        //1) it is smaller than its reverse complement => 50% reduction
        //2) all its subgroups also occur (same as with association rule learning: pop all possible chars once and the group should have
        //matches otherwise longer patterns cannot have matches!
}
