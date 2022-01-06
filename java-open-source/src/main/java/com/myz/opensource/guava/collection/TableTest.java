package com.myz.opensource.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * 类似三维模型数据
 * Table
 * 双键Map
 * getRowKey() 第一列键
 * getColumnKey()　第二列键
 * getValue()
 * <p>
 * rowKeySet() 第一列键
 * columnKeySet()　第二列键
 * row()　第一列键所对应的值
 * column()　第二列键所对应的值
 *
 * @author maoyz on 18-3-4.
 */
public class TableTest {

    @Test
    public void test() {
        Table<String, String, Integer> table = HashBasedTable.create();

        table.put("aaa", "语文", 90);
        table.put("bbb", "数学", 91);
        table.put("ccc", "英语", 92);
        table.put("ddd", "体育", 93);

        // 所有行列数据
        Set<Table.Cell<String, String, Integer>> cells = table.cellSet();
        for (Table.Cell<String, String, Integer> cell : cells) {
            // getRowKey()
            System.out.println(cell.getRowKey() + "-" + cell.getColumnKey() + "-" + cell.getValue());
        }

        System.out.println("----------------------");

        // rowKeySet() 行
        Set<String> rowKeySet = table.rowKeySet();
        // columnKeySet() 列
        Set<String> columnSet = table.columnKeySet();

        System.out.println("----行数据-----");
        for (String set : rowKeySet) {
            System.out.println(set);
            // 获取行数据
            Map<String, Integer> row = table.row(set);
            for (String s : row.keySet()) {
                System.out.println(s + "->" + row.get(s));
            }
        }

        System.out.println("--------列数据--------");
        for (String column : columnSet) {
            System.out.println(column);
            Map<String, Integer> column1 = table.column(column);
            for (String s : column1.keySet()) {
                System.out.println(s + "->" + column1.get(s));
            }
        }
    }
}
