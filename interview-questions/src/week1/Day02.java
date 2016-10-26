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

        System.out.println("---- One Edit Away ----");
        checkOneEditAway("dog", "dog");
        checkOneEditAway("dog", "dod");
        checkOneEditAway("dog", "god");
        checkOneEditAway("chicken", "thicken");
        checkOneEditAway("thicken", "thicket");
        checkOneEditAway("thikket", "thicket");
        checkOneEditAway("dog", "do");
        checkOneEditAway("do", "dog");
        checkOneEditAway("dog", "og");
        checkOneEditAway("dog", "dg");
        checkOneEditAway("rabbit", "rabit");
        checkOneEditAway("rabit", "racist");

        checkOneEditAway("pale", "ple");
        checkOneEditAway("pales", "pale");
        checkOneEditAway("pale", "bale");
        checkOneEditAway("pale", "bake");
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
     * @param input the string
     * @return T/F is permutation of palindrome
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

    /* 1.5 One away: Given the operations insert, delete, and replace a character in a string,  *
     *               determine if a string is one edit (or zero) away from another.             *
     *                                                                                          *
     *   Thoughts: Insertion and deletion can be treated as the same operation, just by         *
     *   switching the string we're doing the check on.                                         */

    /**
     * Check if one string is one edit away from another string. The edits are insertion,
     *  deletion, and replacement. Note that deletion can be viewed as insertion into the
     *  other string - that simplifies it to two conditions to check.
     *
     * O(n), where n is the length of the longer(eh) string. They'll only differ at most by one though.
     *
     * @param first
     * @param second
     * @return
     */
    static boolean isOneEditAway(String first, String second) {
        // Lengths MUST be within one character of each other (or equal)
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        // Check replacement
        if (first.length() == second.length()) {
            boolean replaced = false;
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    // Allow for one non-matching char in the string, fail otherwise.
                    if (!replaced) {
                        replaced = true;
                    } else {
                        return false;
                    }
                }
            }

            return true;
        }

        // Check Insertion (and deletion)
        //   1) differentiate longer from shorter
        //   2) when we find a different character, eat it and advance the index in the other string
        //   3) it'll make more sense if you look below.
        char[] larger, smaller;
        if (first.length() > second.length()) {
            larger = first.toCharArray();
            smaller = second.toCharArray();
        } else {
            larger = second.toCharArray();
            smaller = first.toCharArray();
        }

        int largerIndex = 0;
        for (int i = 0; i < smaller.length; i++) {
            if (smaller[i] != larger[largerIndex]) {
                if (largerIndex > i) {
                    // we've already allowed an insertion into the string, can't do a second!
                    // ale : abbe -> ale : abbe
                    //  ^     ^       ^      ^   <--- forces this case to fail
                    return false;
                }
                // step back (gets immediately incremented), allows for checking
                // i + 1 in longer string against i in this string:
                // ale : able                      ale : able
                //  ^     ^  -> nope!, back up ->   ^      ^ -> yup!  We're good
                i--;
            }
            largerIndex++;
        }

        return true;
    }

    static void checkOneEditAway(String first, String second) {
        System.out.printf("'%s' & '%s' -> %s\n", first, second, isOneEditAway(first, second) ? "true" : "false");
    }
}
