package com.myz.java.study.opensource.guava;

// import com.google.common.collect.Constraint;
// import com.google.common.collect.Constraints;

import org.junit.Test;

/**
 * Constraint 约束
 * Preconditions 前置条件：让方法调用的前置条件判断更简单。
 * 注： 14版本能用，高版本无法使用
 * checkNotNull(E) 检查非空
 * checkArgument(E)　检验约束条件
 * <p>
 * Constraints.constrainedXxx(Xxx, Constraint)　约束条件
 *
 * @author maoyz on 18-3-3.
 */
public class ConstraintTest {

    @Test
    public void testConstraint() {
        /*

        // 创建约束
        Constraint<String> constraint = new Constraint<String>() {

            @Override
            public String checkElement(String element) {
                //  Preconditons 前置条件：让方法调用的前置条件判断更简单
                // 非空检验
                Preconditions.checkNotNull(element);

                //　检验字符条件
                Preconditions.checkArgument(element.startsWith("demo") && element.length() > 4);
                return element;
            }
        };

        Set<String> set = Sets.newHashSet();

        // Constraints.constrainedSet(set, Constraint)　约束条件
        Set<String> constrainedSet = Constraints.constrainedSet(set, constraint);
        // java.lang.NullPointerException
        constrainedSet.add(null);
        // java.lang.IllegalArgumentException
        constrainedSet.add("assd");
        // 合法数据
        constrainedSet.add("asdsd");

        for (String s : constrainedSet) {
            System.out.println(s);
        }

*/
    }

}
