package org.example.questions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution {
  public static void main(String[] args) {
    // 1. 两数之和
//    int[] sum = twoSum(new int[] {2, 7, 11, 15}, 9);
    // 2. 两数之和
//    ListNode listNode =
//        addTwoNumbers(
//            new ListNode(2, new ListNode(4, new ListNode(3))),
//            new ListNode(5, new ListNode(6, new ListNode(4))));
    // 3. 无重复字符的最长子串
    lengthOfLongestSubstring("kwpwwkewa");
  }

  /**
   * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
   *
   * @param nums nums = [2,7,11,15]
   * @param target target = 9
   * @return [0,1]
   */
  public static int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(target - nums[i])) {
        return new int[] {map.get(target - nums[i]), i};
      }
      map.put(nums[i], i);
    }
    return null;
  }

  /**
   * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
   *
   * <p>请你将两个数相加，并以相同形式返回一个表示和的链表
   *
   * @param l1 链表l1 [2,4,3]
   * @param l2 链表l2 [5,6,4]
   * @return 链表结果 [7,0,8]
   */
  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode head = null, tail = null;
    int carry = 0;
    while (l1 != null || l2 != null) {
      int n1 = l1 != null ? l1.val : 0;
      int n2 = l2 != null ? l2.val : 0;
      int sum = n1 + n2 + carry;
      if (head == null) {
        head = tail = new ListNode(sum % 10);
      } else {
        tail.next = new ListNode(sum % 10);
        tail = tail.next;
      }
      carry = sum / 10;
      if (l1 != null) {
        l1 = l1.next;
      }
      if (l2 != null) {
        l2 = l2.next;
      }
    }
    if (carry > 0) {
      tail.next = new ListNode(carry);
    }
    return head;
  }

  /**
   * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
   *
   * @param s 输入: s = "abcabcbb" "bbbbb"  "pwwkew" "kwpwwkewa"
   * @return 3 "abc"    1"b"   3  "wke"
   */
  public static int lengthOfLongestSubstring(String s) {
    // 哈希集合，记录每个字符是否出现过
    Set<Character> occ = new HashSet<Character>();
    int n = s.length();
    // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
    int rk = -1, ans = 0;
    for (int i = 0; i < n; ++i) {
      if (i != 0) {
        // 左指针向右移动一格，移除一个字符
        occ.remove(s.charAt(i - 1));
      }
      while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
        // 不断地移动右指针
        occ.add(s.charAt(rk + 1));
        ++rk;
      }
      // 第 i 到 rk 个字符是一个极长的无重复字符子串
      ans = Math.max(ans, rk - i + 1);
    }
    return ans;
  }

  static class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}
