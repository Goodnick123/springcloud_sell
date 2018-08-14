package com.imooc.order.message;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding(StreamClient.class)
public class StreamReceiver {

    @StreamListener("input")
    @SendTo("inputOrder")
    public Object process(Object message){
        log.info("input之监听到数据:{0}"+message);
        return "done";
    }

    @StreamListener("inputOrder")
    public void process2(Object message){
        log.info("reveiver:{0}"+message);
    }



}
