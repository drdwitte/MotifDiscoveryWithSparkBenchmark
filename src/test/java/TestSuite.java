/**
 * Created by ddewitte on 25.09.15.
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ alphabets.testIUPACAlphabet.class,
        datastructures.TestDiskBasedSearchSpace.class,
        datastructures.TestAngleRestrictedSearchSpace.class
})
public class TestSuite {

}