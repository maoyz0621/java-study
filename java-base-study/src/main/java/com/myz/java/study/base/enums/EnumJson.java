/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.base.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author maoyz0621 on 20-4-17
 * @version v1.0
 */

public class EnumJson {

    private EnumJsonStatus enumJsonStatus;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum EnumJsonStatus {
        READY(0) {
            @Override
            public boolean isReady() {
                return true;
            }
        },
        ORDERED(1) {
            @Override
            public boolean isOrdered() {
                return super.isOrdered();
            }
        },
        DELIVERED(2) {
            @Override
            public boolean isDelivered() {
                return true;
            }
        };
        @JsonProperty("status")
        private final int status;

        EnumJsonStatus(int status) {
            this.status = status;
        }

        // 枚举中定义了方法
        public boolean isReady() {
            return false;
        }

        public boolean isOrdered() {
            return false;
        }

        public boolean isDelivered() {
            return false;
        }

        public int getStatus() {
            return status;
        }
    }

    public boolean isDeliverable() {
        return this.enumJsonStatus.isReady();
    }

    public static String getJsonString(EnumJson enumJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(enumJson);
    }

    public EnumJsonStatus getEnumJsonStatus() {
        return enumJsonStatus;
    }

    public void setEnumJsonStatus(EnumJsonStatus enumJsonStatus) {
        this.enumJsonStatus = enumJsonStatus;
    }

    public static void main(String[] args) throws JsonProcessingException {
        EnumJson enumJson = new EnumJson();
        enumJson.setEnumJsonStatus(EnumJsonStatus.READY);
        // {"enumJsonStatus":{"ready":true,"delivered":false,"ordered":false,"status":0},"deliverable":true}
        System.out.println(EnumJson.getJsonString(enumJson));
    }
}