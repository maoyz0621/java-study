package com.myz.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;


public class App32Test {

    /**
     * 32位
     */
    public static void main(String[] args) {
        System.out.println(VM.current().details());

        Object o = new Object();
        //    OFFSET  SIZE   TYPE DESCRIPTION     VALUE
        //       0     4        (object header)   01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //       4     4        (object header)   e0 10 60 15 (11100000 00010000 01100000 00010101) (358617312)
        // Instance size: 8 bytes
        // Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            //  OFFSET  SIZE   TYPE DESCRIPTION       VALUE
            //       0     4        (object header)   14 f7 05 03 (00010100 11110111 00000101 00000011) (50722580)
            //       4     4        (object header)   e0 10 60 15 (11100000 00010000 01100000 00010101) (358617312)
            // Instance size: 8 bytes
            // Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    static class A{

    }
}

