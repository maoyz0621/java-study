/**
 * Copyright 2022 Inc.
 **/
package com.myz.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * @author maoyz0621 on 2022/4/28
 * @version v1.0
 */
public class ValidBrackets {

    public static void main(String[] args) {
        boolean valid = isValid("()[]{}");
        System.out.println(valid);

        System.out.println("=====================================");

        valid = isValid("{[]}");
        System.out.println(valid);

        System.out.println("=====================================");

        valid = isValid("([)]");
        System.out.println(valid);

        System.out.println("=====================================");

        valid = isValid("([");
        System.out.println(valid);
    }

    public static boolean isValid(String s) {
        // 奇数返回false
        if (s.length() <= 1 || s.length() % 2 != 0) {
            return false;
        }
        final Map<Character, Character> map = new HashMap<Character, Character>() {{
            put('(', ')');
            put('{', '}');
            put('[', ']');
            put('?', '?');
        }};

        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            // 左括号就入栈
            if (map.containsKey(c)) {
                stack.push(map.get(c));
            } else {
                // 右括号就出栈
                if (stack.isEmpty() || !stack.pop().equals(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}