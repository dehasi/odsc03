package codility;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    public void test1() {
        Assert.assertEquals(3, solution.solution(new int[]{2, 1, 1, 3, 2, 1, 1, 3}));
    }

    @Test
    public void test2() {
        Assert.assertEquals(6, solution.solution(new int[]{7, 5, 2, 7, 2, 7, 4, 7}));
    }

    @Test
    public void test3() {
        Assert.assertEquals(5, solution.solution(new int[]{7, 3, 7, 3, 1, 3, 4, 1}));
    }

    @Test
    public void test4() {
        Assert.assertEquals("004-448-555-583-61", solution.solution("00-44  48 5555 8361"));
    }

    @Test
    public void test5() {
        Assert.assertEquals("022-198-53-24", solution.solution("0 - 22 1985--324"));
    }


}
