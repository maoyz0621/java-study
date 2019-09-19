/*
 * Copyright (C) 2018, All rights Reserved, Designed By www.xiniaoyun.com
 * @author: maoyz
 * @Copyright: 2019-09-19 15:34 www.xiniaoyun.com Inc. All rights reserved.
 * 注意：本内容仅限于南京微欧科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.myz.java.study.base.enum1;

/**
 * 枚举实现接口
 *
 * @author maoyz
 */
public enum EnumImpl implements EnumA, EnumB {
    ;

    @Override
    public void a() {

    }

    @Override
    public void b() {

    }
}

interface EnumA {
    void a();
}

interface EnumB {
    void b();
}