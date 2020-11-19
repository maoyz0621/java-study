/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.base.enums;

/**
 * @author maoyz0621 on 20-4-17
 * @version v1.0
 */
public class EnumConstructor {
    private EnumStatus enumStatus;

    public enum EnumStatus {
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
        private final int status;

        EnumStatus(int status) {
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

        public int status() {
            return status;
        }
    }

    public boolean isDeliverable(){
        return this.enumStatus.isReady();
    }

    public EnumStatus getEnumStatus() {
        return enumStatus;
    }

    public void setEnumStatus(EnumStatus enumStatus) {
        this.enumStatus = enumStatus;
    }

    public static void main(String[] args) {
        EnumConstructor constructor = new EnumConstructor();
        constructor.setEnumStatus(EnumStatus.DELIVERED);
        System.out.println(constructor.isDeliverable());    // false
        constructor.setEnumStatus(EnumStatus.READY);
        System.out.println(constructor.isDeliverable());    // true
    }
}