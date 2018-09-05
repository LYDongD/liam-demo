package com.liam.demo.enumType.chainOfResponsibility;


/**
 *  消息模型
 */
public class Message {

    //消息类型
    private MessageType messageType;

    //消息体
    private Object body;

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
