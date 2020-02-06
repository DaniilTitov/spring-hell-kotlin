package ru.spring.hell.task

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.spring.hell.client.ItemClient
import ru.spring.hell.model.soap.Items
import java.math.BigInteger.TEN

@Component
class ItemTask(private val itemClient: ItemClient) {
    private val log: Logger = getLogger(ItemTask::class.java)

//    @Scheduled(fixedRate = 5000)
//    fun reportCurrentTime() {
//        val items = Items()
//
//        items.item.add(
//                Items.Item().apply {
//                    diameter = TEN
//                    name = "Vasya"
//                    size = "Small"
//                }
//        )
//
//        val person = itemClient.items(items)
//        log.info("Person: {}", person)
//    }

}