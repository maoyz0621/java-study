/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.io.nio.selector;


import java.nio.channels.SelectionKey;
import java.util.Objects;

/**
 * 使用线程池来为准备好数据用于读取的通道提供服务
 * 与在主线程中同步地读取数据不同,这个版本的实现将 SelectionKey 对象传递给为其服务的工作线程
 * <p>
 * 由于执行选择过程的线程将重新循环并几乎立即再次调用 select( ),键的 interest 集合将被修改,并将 interest(感兴趣的操作)从读取就绪(read-rreadiness)状态中移除。
 * 这将防止选择器重复地调用 readDataFromSocket( )(因为通道仍然会准备好读取数据,直到工作线程从它那里读取数据)。当工作线程结束为通道提供的服务时,
 * 它将再次更新键的 ready 集合,来将 interest 重新放到读取就绪集合中。它也会在选择器上显式地调用 wakeup( )。如果主线程在 select( )中被阻塞,这将使它继续执行。
 * 这个选择循环会再次执行一个轮回(可能什么也没做)并带着被更新的键重新进入select( )。
 *
 * @author maoyz on 18-10-22
 * @version: v1.0
 */
public class SelectSocketsThreadPool extends SelectSockets {

    private static final int MAX_THREADS = 5;
    private ThreadPool pool = new ThreadPool(MAX_THREADS);

    public static void main(String[] args) {
        new SelectSocketsThreadPool().service();
    }

    @Override
    public void readDataFromSocket(SelectionKey key) {
        Runnable thread = pool.getWorker();
        if (Objects.isNull(thread)) {
            return;
        }
        ((WorkerThread) thread).serviceChannel(key);
    }

    @Override
    public void writeDataFromSocket(SelectionKey key) {

    }


}
