package com.ms.hdi.flink.stream.kafka.producer

import com.ms.hdi.flink.model.FlinkJobParam


/**
 * Job Parameters for [[FlinkKafkaCustomerProducer]]
 */
object KafkaCustomerProducerJobParam {

  val TOPIC_NAME = FlinkJobParam("topicName", "Kafka Topic Name")
  val BOOTSTRAP_SERVER = FlinkJobParam("kafkaBootstrapServers", "Kafka Bootstrap Servers")

  val LIST_OF_PARAM: List[FlinkJobParam] = List(TOPIC_NAME, BOOTSTRAP_SERVER)
}
