package com.iat.momentsqa.messagingrabbitmq;

import com.iat.momentsqa.dto.ChannelDto;
import com.iat.momentsqa.dtomapper.MapperChannel;
import com.iat.momentsqa.service.IChnlsService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RabbitmqConsumer {
    @Autowired
    IChnlsService service;
    @Autowired
    MapperChannel mapperChannel;
    private final Log log = LogFactory.getLog(RabbitmqConsumer.class);

//    https://stackoverflow.com/questions/67197737/spring-boot-unable-to-evaluate-the-expression-method-threw-org-hibernate-lazyin
    @RabbitListener(queues = {"spring-boot"})
    public void consume(String message) throws Exception {
        try{
            if (Objects.isNull(message)) {
                log.info(String.format("No message -> %s"));
            }
//            Channels channel = service.getChannelsById(Long.valueOf(message));
            ChannelDto channelDto = mapperChannel.toChannelId(service.getChannelsById(Long.valueOf(message)));
            log.info(String.format("Received message -> %s", channelDto));
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @RabbitListener(queues = {"spring-boot2"})
    public void consume2(String message) throws Exception {
        try{
            if (Objects.isNull(message)) {
                log.info(String.format("No message -> %s"));
            }
            log.info(String.format("Received message -> %s", message));
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
