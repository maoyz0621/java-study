package com.myz.converter;

import java.util.List;

/**
 * 类型转换接口
 *
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/23 11:19
 */
public interface IPairConverter<T, U> {

    U to(T src);

    List<U> to(List<T> srcList);

    T back(U dest);

    List<T> back(List<U> destList);
}
