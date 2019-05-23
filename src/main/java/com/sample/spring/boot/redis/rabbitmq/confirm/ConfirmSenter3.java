package com.sample.spring.boot.redis.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * 异步confirm模式：提供一个回调方法，服务端confirm了一条或者多条消息后Client端会回调这个方法。
 */
public class ConfirmSenter3 {

    private final static String QUEUE_NAME = "test_confirm_queue3";

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();

            // 开始确认
            channel.confirmSelect();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // 线程安全有顺序不重复的set集合
            SortedSet<Long> sortedSet = Collections.synchronizedSortedSet(new TreeSet<>());

            channel.addConfirmListener(new ConfirmListener() {
                /**
                 *
                 * @param deliveryTag （当前Chanel发出的消息序号）
                 * @param multiple 是否是多条数据
                 * @throws IOException
                 */
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("handleAck................");
                    if (multiple) {
                        sortedSet.headSet(deliveryTag + 1).clear();
                    } else {
                        sortedSet.remove(deliveryTag);
                    }
                }

                /**
                 *
                 * @param deliveryTag （当前Chanel发出的消息序号）
                 * @param multiple 是否是多条数据
                 * @throws IOException
                 */
                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("handleNack................");
                    // 处理未确认的应答
                    if (multiple) {
                        sortedSet.headSet(deliveryTag + 1).clear();
                    } else {
                        sortedSet.remove(deliveryTag);
                    }
                }
            });

//            while (true) {
//                long nextSeqNo = channel.getNextPublishSeqNo();
//                String message = "hello, confirm message ";
//                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//                sortedSet.add(nextSeqNo);
//
//                if(nextSeqNo == 3){
//                    break;
//                }
//            }

            for (int i = 0; i < 10; i++) {
                long nextSeqNo = channel.getNextPublishSeqNo();
                String message = "hello, confirm message";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                sortedSet.add(nextSeqNo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
