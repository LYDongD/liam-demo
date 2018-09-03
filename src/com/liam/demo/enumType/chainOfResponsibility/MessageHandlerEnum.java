package com.liam.demo.enumType.chainOfResponsibility;

/**
 *  管理所有handler实例的枚举类型，且保证所有消息处理器都是单例
 */
public enum MessageHandlerEnum implements MessageHandler{
    TXET_HANDLER(MessageType.TEXT){
        @Override
        void doHandle(Message message) {
            System.out.println("handle text data");
        }
    },
    BIN_HANDLER(MessageType.BIN){
        @Override
        void doHandle(Message message) {
            System.out.println("handle binary data");
        }
    },
    JSON_HANDLER(MessageType.JSON){
        @Override
        void doHandle(Message message) {
            System.out.println("handle json data");
        }
    },
    XML_HANDLER(MessageType.XML){
        @Override
        void doHandle(Message message) {
            System.out.println("handle xml data");
        }
    };



    private MessageType acceptType;


    MessageHandlerEnum(MessageType messageType){
       this.acceptType = messageType;
    }


    abstract void doHandle(Message message);

    @Override
    public void handle(Message message) {

        if (this.acceptType == message.getMessageType()){
            doHandle(message);
        }
    }
}
