package com.myz.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;


public class App64Test {

    /**
     * 64位
     */
    public static void main(String[] args) {
        System.out.println(VM.current().details());

        Object o = new Object();
        //   OFFSET  SIZE   TYPE DESCRIPTION       VALUE
        //       0     4        (object header)    05 00 00 00 (00000101 00000000 00000000 00000000) (5)
        //       4     4        (object header)    00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //       8     4        (object header)    00 10 00 00 (00000000 00010000 00000000 00000000) (4096)
        //      12     4        (loss due to the next object alignment)
        // Instance size: 16 bytes
        // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            //   OFFSET  SIZE   TYPE DESCRIPTION        VALUE
            //       0     4        (object header)     05 58 c8 c9 (00000101 01011000 11001000 11001001) (-909617147)
            //       4     4        (object header)     d9 01 00 00 (11011001 00000001 00000000 00000000) (473)
            //       8     4        (object header)     00 10 00 00 (00000000 00010000 00000000 00000000) (4096)
            //      12     4        (loss due to the next object alignment)
            // Instance size: 16 bytes
            // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    static class A{

    }
}

