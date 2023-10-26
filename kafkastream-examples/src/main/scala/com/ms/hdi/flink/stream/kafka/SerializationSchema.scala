package com.ms.hdi.flink.stream.kafka

import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema
import org.apache.kafka.clients.producer.ProducerRecord

import java.lang
import java.nio.charset.StandardCharsets

/**
 * Kafka Serialization Schema Definitions
 */
object SerializationSchema {

  /**
   * Kafka String Serialization Schema
   * @param topicName
   * @return
   */
  def kafkaStringSerializationSchema(topicName: String): KafkaSerializationSchema[String] = {
    new KafkaSerializationSchema[String]() {
      def serialize(element: String, timestamp: lang.Long) = new ProducerRecord[Array[Byte], Array[Byte]](topicName, // target topic
        element.getBytes(StandardCharsets.UTF_8)) // record contents
    }
  }

}
