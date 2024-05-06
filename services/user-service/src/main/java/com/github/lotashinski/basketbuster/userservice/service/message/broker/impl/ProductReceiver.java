package com.github.lotashinski.basketbuster.userservice.service.message.broker.impl;

import com.github.lotashinski.basketbuster.userservice.service.message.broker.ProductListener;
import com.github.lotashinski.basketbuster.userservice.service.message.broker.Receiver;
import com.github.lotashinski.basketbuster.userservice.service.message.broker.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductReceiver implements Receiver<ProductDto> {

    private final ProductListener productListener;


    @KafkaListener(
            groupId = "${spring.application.name}",
            topicPartitions = {
                    @TopicPartition(
                            topic = "products",
                            partitionOffsets = {
                                    @PartitionOffset(partition = "0", initialOffset = "0")
                            }
                    )
            },
            containerFactory = "productKafkaListenerContainerFactory"
    )
    @Override
    public void receive(ProductDto message) {
        log.info("received product message: {}", message);

        switch (message.getEvent()) {
            case CREATE:
                productListener.onCreated(message);
                break;
            case UPDATE:
                productListener.onUpdated(message);
                break;
            case DELETE:
                productListener.onDeleted(message);
                break;
        }
    }

}
