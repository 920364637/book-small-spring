package test;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

public class Solution {
    public int countWays(int[][] ranges) {
        int un_ol_cnt = ranges.length;
        Arrays.sort(ranges, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

        int l = ranges[0][0], r = ranges[0][1];
        for (int i = 1; i < ranges.length; i++) {
            int s = ranges[i][0], e = ranges[i][1];
            if (s <= r) {
                r = Math.max(e, r);
                un_ol_cnt -= 1;
            } else {
                l = s;
                r = e;
            }
        }

        return modPow(2, un_ol_cnt, 1000000007);
    }

    private int modPow(int x, int n, int mod) {
        if (n <= 1) {
            return x % mod;
        }
        int res;
        if (n % 2 == 1) {
            res =  modPow(x, n - 1, mod) * x % mod;
        } else {
            res = modPow(x, n / 2, mod) % mod;
            res = res * res % mod;
        }
        System.out.println(res);
        return res;
    }

    @Test
    public void test_sol() {
        System.out.println(modPow(2, 87, 1000000007));
    }
}
