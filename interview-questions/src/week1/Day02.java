package week1;

/**
 * Created by Rick on 10/25/16.
 */
public class Day02 {
    public static void main(String[] args) {

        System.out.println("---- Permutation of Palindrome ----");
        checkPermutation("aabbc");
        checkPermutation("aabb");
        checkPermutation("aabbcc");
        checkPermutation("aabbccdc");
    }

    /* 1.4 Palindrome Permutation: Given a string, see if it is a permutation of a palindrome.  *
     *  Thought: A palindrome is the same forwards as backwards - this means that there is      *
     *  an even amount of each letter in the string, except one odd is allowed. This would be   *
     *  the middle character, i.e abcba, or acbcbca                                             */

    /**
     * Checks if a string is a permutation of a palindrome. It does this by counting the
     * occurrences of characters in the string, and checking to see if they are all
     * even and optionally one odd.
     *
     * O(n + m) = O(m), where n is length of string, and m is 256
     *
     * @param input
     * @return
     */
    static boolean isPermutationOfPalindrome(String input) {
        if (input.length() == 0 || input.length() > 256)
            return false;
        if (input.length() == 1)
            return true;

        int[] counts = new int[256];

        // Count up the characters
        int i = 0;
        for (; i < input.length(); i++) {
            counts[input.charAt(i)]++;
        }

        // Check to see if they are all evens, with one odd allowed.
        boolean foundPivotChar = false;
        for (i = 0; i < counts.length; i++) {
            if (counts[i] % 2 == 1) {
                if (foundPivotChar) {
                    return false;
                }
                foundPivotChar = true;
            }
        }

        return true;
    }

    static void checkPermutation(String arg) {
        System.out.printf("'%s' -> %s\n", arg, isPermutationOfPalindrome(arg) ? "true" : "false");
    }
}
