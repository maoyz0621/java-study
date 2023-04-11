/**
 * Copyright 2023 Inc.
 **/
package com.myz.opensource.mapstruct.convert;

import com.myz.opensource.mapstruct.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;

/**
 * @author maoyz0621 on 2023/3/21
 * @version v1.0
 */
@Mapper(componentModel = "default", mappingControl = DeepClone.class)
public interface CarCloneConverter {

    Car clone(Car src);
}