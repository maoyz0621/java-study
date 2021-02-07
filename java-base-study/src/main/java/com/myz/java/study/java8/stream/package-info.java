/**
 * Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
 * Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。这种风格将要处理的元素集合看作一种流， 流在管道中传输，
 * 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
 *
 * 1、不是数据结构： 它没有内部存储，它只是用操作管道从 source（数据结构、数组、generator function、IO channel）抓取数据。它也绝不修改自己所封装的底层数据结构的数据。例如 Stream 的 filter 操作会产生一个不包含被过滤元素的新 Stream，而不是从 source 删除那些元素。
 * 2、不支持索引访问： 但是很容易生成数组或者 List 。
 * 3、惰性化：很多 Stream 操作是向后延迟的，一直到它弄清楚了最后需要多少数据才会开始。Intermediate 操作永远是惰性化的。
 * 4、并行能力。当一个 Stream 是并行化的，就不需要再写多线程代码，所有对它的操作会自动并行进行的。
 * 5、可以是无限的：集合有固定大小，Stream 则不必。limit(n) 和 findFirst() 这类的 short-circuiting 操作可以对无限的 Stream 进行运算并很快完成。
 * 注意事项：所有 Stream 的操作必须以 lambda 表达式为参数。
 *
 * @author maoyz0621 on 19-3-24
 * @version: v1.0
 */
package com.myz.java.study.java8.stream;