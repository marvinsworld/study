package com.marvin.demo.marvin.mq.demo.queue;

/**
 * Description:
 *
 * @author Eason
 * @since 15-1-11 下午4:42
 */
public class QueueDemo {

    public static void main(String[] args) {
        QueueCache queueCache = new QueueCache();
        Sender sender = new Sender(queueCache);
        Receiver receiver = new Receiver(queueCache);
        for (int i = 0; i < 10; i++) {
            sender.sender("msg" + i);
        }
        for (int j = 0; j < 10; j++) {
            receiver.receive();
        }
    }
}
