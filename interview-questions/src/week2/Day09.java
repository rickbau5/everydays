package week2;

import common.Node;
import common.SimpleList;

/**
 * Created by Rick on 11/4/16.
 */
public class Day09 {
    public static void main(String[] args) {
        System.out.println("---- Sum Lists ----");
        doSum(SimpleList.of(7, 1, 6), SimpleList.of(5, 9, 2));
        doSum(SimpleList.of(1, 2), SimpleList.of(4));
        doSum(SimpleList.of(4), SimpleList.of(1, 2));
        doSum(SimpleList.of(1, 2), SimpleList.of(9));
    }

    /* 2.5 Sum Lists: Given two numbers represented by linked lists, add them together. For example, *\
     *                   617    +    295    =    912        The numbers are stored in reverse order. *
    \*                (7->1->6) + (5->9->2) = (2->1->9)                                              */

    /**
     * Sum the two lists, returning a new list that represents the sum.
     *
     * O(n + m), where n is length of first list and m is length of second.
     *
     * @param left list one
     * @param right list two
     * @return Node pointing to head of list that represents the sum.
     */
    static Node<Integer> sumLists(Node<Integer> left, Node <Integer> right) {
        if (right == null && left == null) {
            return new Node<>(0);
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        int sum = left.value + right.value;

        Node<Integer> pointer = new Node<>(sum % 10);
        Node<Integer> start = pointer;

        left = left.next;
        right = right.next;

        int carry = sum > 9 ? 1 : 0;
        while (left != null || right != null) {
            int leftV = 0, rightV = 0;

            if (right != null) {
                rightV = right.value;
            }
            if (left != null) {
                leftV = left.value;
            }

            sum = carry + leftV + rightV;
            carry = sum > 9 ? 1 : 0;
            pointer.next = new Node<>(sum % 10);

            pointer = pointer.next;
            if (left != null)
                left = left.next;
            if(right != null)
                right = right.next;
        }

        return start;
    }

    static void doSum(Node<Integer> left, Node<Integer> right) {
        System.out.println(SimpleList.stringify(left) + " + " + SimpleList.stringify(right)
                + " = " + SimpleList.stringify(sumLists(left, right)));
    }
}
