package str;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
@Ignore
public class SemTest {
    Sem sem = new Sem();

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"2134", "234"}, {"111",""}, {"123", "23"}, {"2341", "234"}
        });
    }

    private String input;

    private String expected;

    public SemTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void test() {
        String actual = sem.stringUtilsNoPreCheck2(input);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        String actual = sem.stringUtilsNoPreCheck(input);
        Assert.assertEquals(expected, actual);
    }
}
