public class GCD {

    /**
     * Computes the Greatest Common Divisor (GCD) of two integers
     * using Euclid's Algorithm.
     * 
     * @param a The first integer
     * @param b The second integer.
     * @return The greatest common divisor between `a` and `b`.
     */
    public static int EuclidGCD(int a, int b) {

        // Assigns `b` as the largest integer, and `a` as the smallest
        int tempB = Math.max(a, b);
        int tempA = Math.min(a, b);
        a = tempA; // 153972
        b = tempB; // 211848

        // Euclid's algorithm relies on the fact that
        // if there are two integers a and b such that 1 <= a <= b and
        // b = aq + r, where 0 <= r <= a, then gcd⁡(b, a) = gcd⁡(a, r). 
        int r = -1;
        while (r != 0) {
            int q = Math.floorDiv(b, a);

            // If the remainder is equal to zero, we
            // stop the loop, and keep the last
            // non-zero value for r.
            if (b - (a * q) <= 0) break;

            // Computes the new value for r.
            r = b - (a * q);

            // Repeat the process where we now
            // try to find gcd(a, r).
            b = a;
            a = r;
        }

        return r;
    }
}