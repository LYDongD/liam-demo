package com.liam.demo.enumType.chainOfResponsibility;

/**
 *  消息处理责任链，迭代所有handler并进行处理
 *  不是用ArrayList或LinkList维护，而是用枚举进行维护所有handler
 */
public class MessageHandlerChain {

    public void handle(Message message){

        for (MessageHandler messageHandler : MessageHandlerEnum.values()){
            messageHandler.handle(message);
        }
    }


    public static void main(String args[]) throws Exception{
        Message message = new Message();
        message.setMessageType(MessageType.JSON);
        new MessageHandlerChain().handle(message);
    }

}
