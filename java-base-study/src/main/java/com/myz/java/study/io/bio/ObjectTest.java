package com.myz.java.study.io.bio;

import org.junit.Test;

import java.io.*;

/**
 * ObjectInputStream  反序列化  readObject()
 * ObjectOutputStream 序列化  　writeObject(Object obj)
 * 先序列化后反序列化
 * transient非序列化关键字
 *
 * @author maoyz on 18-3-17.
 */
public class ObjectTest {

    String pathname = "../first/resources".replace("/", File.separator) + File.separator + "files/demo/7.txt".replace("/", File.separator);

    @Test
    public void testRead() {
        try (
                ObjectInputStream in = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream(pathname)));
        ) {
            Object obj = in.readObject();
            if (obj instanceof Emp) {
                Emp emp = (Emp) obj;
                System.out.println(emp.getName());
                System.out.println(emp.getAge());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * NotSerializableException
     */
    @Test
    public void testWrite() {
        // Emp implements Serializable
        Emp emp = new Emp("abc", 10L);

        try (
                ObjectOutputStream out = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(pathname)));
        ) {
            out.writeObject(emp);

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
