package codility;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static final int N = 100_000;
    int hash[] = new int[N];

    public Solution() {
    }

    public int solution(int[] a) {
        if (a.length == 1) return 1;
        if (a.length == 2) {
            return a[0] == a[1] ? 1 : 2;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : a) {
            set.add(i);
        }

        // write your code in Java SE 8
        int n = a.length;
        int lo = 0;

        int hi = 1;
        ++hash[a[0]];
        int diff = 1;
        int len = Integer.MAX_VALUE;
        while (hi < n || hash[lo] > 1) {
            int r = a[lo];
            if (hash[r] > 1) {
                while (hash[r] > 1) {
                    --hash[r];
                    ++lo;
                    r = a[lo];
                }
            }

            if (diff == set.size()) {
                len = Integer.min(len, hi - lo);
            }
            if (hi < n) {
                int h = a[hi];
                if (hash[h] == 0) {
                    ++diff;
                }
                ++hash[h];
                ++hi;

            }
        }
        return len;
    }

    public String solution(String s) {
        s = s.replace(" ", "").replace("-", "");
        StringBuilder sb = new StringBuilder();
        int n = s.length() / 3;
        int d = s.length() % 3;
        if (d == 1) {
            n = n - 1;
        }
        for (int i = 0; i < 3 * n; i += 3) {
            if (sb.length() > 0) {
                sb.append('-');
            }
            sb.append(s.charAt(i)).append(s.charAt(i + 1)).append(s.charAt(i + 2));
        }
        if (d > 0) {
            sb.append('-');
            String ss = s.substring(n * 3);
            if (ss.length() == 4) {

                sb.append(ss.charAt(0));
                sb.append(ss.charAt(1));
                sb.append('-');
                sb.append(ss.charAt(2));
                sb.append(ss.charAt(3));
            } else {
                sb.append(ss);
            }
        }
        return sb.toString();
    }
}