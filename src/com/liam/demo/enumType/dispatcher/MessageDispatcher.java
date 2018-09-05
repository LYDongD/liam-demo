package com.liam.demo.enumType.dispatcher;

import com.liam.demo.enumType.chainOfResponsibility.Message;
import com.liam.demo.enumType.chainOfResponsibility.MessageHandler;
import com.liam.demo.enumType.chainOfResponsibility.MessageType;

import java.util.EnumMap;

/**
 *  消息分发器，根据消息类型，路由到指定的handler进行处理
 */
public class MessageDispatcher {

    private final EnumMap<MessageType, MessageHandler> dispatcherMap = new EnumMap<MessageType, MessageHandler>(MessageType.class);

    public MessageDispatcher(){
        dispatcherMap.put(MessageType.JSON, message -> System.out.println("json handle"));
        dispatcherMap.put(MessageType.XML, message -> System.out.println("XML handle"));
        dispatcherMap.put(MessageType.BIN, message -> System.out.println("BIN handle"));
        dispatcherMap.put(MessageType.TEXT, message -> System.out.println("TEXT handle"));
    }

    public void dispatch(Message message){
        MessageHandler messageHandler = this.dispatcherMap.get(message.getMessageType());
        if (message != null){
            messageHandler.handle(message);
        }
    }


    public static void main(String args[]) throws Exception{
        Message message = new Message();
        message.setMessageType(MessageType.XML);
        new MessageDispatcher().dispatch(message);
    }
}
