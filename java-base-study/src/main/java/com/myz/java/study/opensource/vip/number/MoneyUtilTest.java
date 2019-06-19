/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.vip.number;

import com.vip.vjtools.vjkit.number.MoneyUtil;
import org.junit.Test;

import java.text.ParseException;

/**
 * @author maoyz0621 on 19-4-24
 * @version: v1.0
 */
public class MoneyUtilTest {

    @Test
    public void amountConvertTest() {
        System.out.println(MoneyUtil.fen2yuan(100).doubleValue());
        System.out.println(MoneyUtil.yuan2fen("12.5"));
        System.out.println(MoneyUtil.yuan2fen(12.5));
    }

    @Test
    public void format() {
        // 1234.00
        System.out.println(MoneyUtil.format(1234D));
        // 1234.0
        System.out.println(MoneyUtil.format(1234D, "0.0"));
        // 1,234.00
        System.out.println(MoneyUtil.prettyFormat(1234D));
    }

    @Test
    public void parse() throws ParseException {
        System.out.println(MoneyUtil.parsePrettyString("1,234.00"));
        System.out.println(MoneyUtil.parseString("1234.00"));
    }
}
