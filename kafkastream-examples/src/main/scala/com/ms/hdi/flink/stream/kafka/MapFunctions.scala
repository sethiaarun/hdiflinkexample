package com.ms.hdi.flink.stream.kafka

import com.ms.hdi.flink.business.model.Customer
import org.apache.flink.api.common.functions.MapFunction

/**
 * Mp functions used for Kafka Streaming
 */
object MapFunctions {

  /**
   * Function to convert [[Customer]] to [[String]]
   */
  val customerMapFn: MapFunction[Customer, String] = new MapFunction[Customer, String] {
    override def map(r: Customer): String = r.customerName
  }

}
