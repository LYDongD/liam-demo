package com.liam.demo.enumType.chainOfResponsibility;

/**
 *  消息处理器接口
 */
public interface MessageHandler {


    /**
     * 消息处理
     * @param message 消息模型
     */
    void handle(Message message);
}
