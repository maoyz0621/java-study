/**
 * 除了名字不一致以外，还有一种特殊情况，那就是类型不一致，如上面的例子中，在PersonDO中用String类型表示用户性别，而在PersonDTO中使用一个Genter的枚举表示用户性别。
 *
 * 这时候类型不一致，就需要涉及到互相转换的问题
 *
 * 其实，MapStruct会对部分类型自动做映射，不需要我们做额外配置，如例子中我们将String类型自动转成了枚举类型。
 *
 * 一般情况下，对于以下情况可以做自动类型转换：
 *
 * 1、基本类型及其他们对应的包装类型。
 * 2、基本类型的包装类型和String类型之间
 * 3、String类型和枚举类型之间
 * @author maoyz0621 on 2020/11/9
 * @version v1.0
 */
package com.myz.opensource.mapstruct;