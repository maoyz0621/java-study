package com.myz.converter;

import com.myz.ajax.common.Result;
import com.myz.ajax.common.ResultGenerator;
import com.myz.ajax.model.UserJson;
import com.myz.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author maoyuezhong
 * @version v1.0
 * @date 2021/7/27 20:13
 */
@RestController
public class ConverterController {

    @Autowired
    CarConverter carConverter;

    @PostMapping(value = "/a")
    public Result param(UserJson userJson) {
        carConverter.to(new Car());
        return ResultGenerator.genSuccessResult(userJson);
    }
}
