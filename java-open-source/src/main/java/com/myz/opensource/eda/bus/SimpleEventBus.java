// /**
//  * Copyright 2022 Inc.
//  **/
// package com.myz.opensource.eda.bus;
//
// import com.myz.opensource.eda.IResult;
// import com.myz.opensource.eda.event.IEvent;
// import com.myz.opensource.eda.sub.IEventSubscriber;
// import lombok.extern.slf4j.Slf4j;
//
// import java.util.Set;
// import java.util.concurrent.Executor;
// import java.util.concurrent.LinkedBlockingQueue;
//
// /**
//  * @author maoyz0621 on 2022/1/13
//  * @version v1.0
//  */
// @Slf4j
// public class SimpleEventBus extends AbstractEventBus {
//
//     private EventLoop[] eventLoop;
//
//     private final Executor executor;
//     private boolean sync = false;
//
//     public SimpleEventBus(Executor executor) {
//         this.executor = executor;
//     }
//
//     /**
//      * 发布
//      *
//      * @param event
//      * @return
//      */
//     @Override
//     public IResult post(IEvent<?> event) {
//         return eventLoop[event.hashCode()].postEvent(event);
//     }
//
//     private void dispatchEvent(IEvent<?> event) {
//         String topic = event.topic();
//         Set<IEventSubscriber<?>> eventSubscribers = getEventSubscriber(topic);
//         eventSubscribers.forEach((eventSubscriber) -> {
//             Runnable runnable = () -> {
//                 IResult result = eventSubscriber.accept(event);
//             };
//
//             if (sync) {
//                 executor.execute(runnable);
//             } else {
//                 runnable.run();
//             }
//         });
//
//
//     }
//
//     class EventLoop {
//         private LinkedBlockingQueue<IEvent> eventQueue = new LinkedBlockingQueue<>();
//
//         public void start() {
//             try {
//                 IEvent event = eventQueue.take();
//                 dispatchEvent(event);
//             } catch (InterruptedException e) {
//             }
//         }
//
//         IResult postEvent(IEvent<?> event) {
//             try {
//                 eventQueue.put(event);
//             } catch (InterruptedException e) {
//             }
//             return null;
//         }
//     }
// }