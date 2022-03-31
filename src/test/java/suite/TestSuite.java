package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ArticleTest.class,
        MyListsTest.class
})

public class TestSuite {
}
