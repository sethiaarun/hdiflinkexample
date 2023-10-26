package com.ms.hdi.hilo.flink.stream.kafka.producer

import com.ms.hdi.flink.model.FlinkJobParam
import com.ms.hdi.flink.stream.kafka.producer.KafkaCustomerProducerJobParam
import com.ms.hdi.flink.stream.kafka.{MapFunctions, SerializationSchema}
import com.ms.hdi.hilo.flink.stream.source.{CustomerSourceParam, MockNeatCustomerSourceFunction}
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer

import java.util.Properties

/**
 * Flink Kafka Producer Example
 * This will create Source Stream from [[MockNeatCustomerSourceFunction]] using MockNeat
 * and Sink data to Kafka
 */
object FlinkKafkaCustomerProducer {

  def main(args: Array[String]) {

    val params: ParameterTool = ParameterTool.fromArgs(args)
    if (KafkaCustomerProducerJobParam.LIST_OF_PARAM.validateParam(params)) {

      val properties = new Properties()
      properties.setProperty("bootstrap.servers", params.get(KafkaCustomerProducerJobParam.BOOTSTRAP_SERVER.paramKey))

      val env = StreamExecutionEnvironment.getExecutionEnvironment

      // read from MockNeatStream Source
      val source = new MockNeatCustomerSourceFunction(CustomerSourceParam(1, 1000, 600000))

      val topicName = params.get(KafkaCustomerProducerJobParam.TOPIC_NAME.paramKey)

      // sink to Kafka
      val kafkaProducer = new FlinkKafkaProducer(
        topicName, // target topic
        SerializationSchema.kafkaStringSerializationSchema(topicName), // serialization schema
        properties, // producer config
        FlinkKafkaProducer.Semantic.NONE); // fault-tolerance

      // build pipeline source ~> transform ~> sink
      env.addSource(source,"Customer data generation")
        .map(MapFunctions.customerMapFn)
        .addSink(kafkaProducer)
      env.execute()

    } else {
      KafkaCustomerProducerJobParam.LIST_OF_PARAM.print
    }
  }

  /**
   * validate Parameters
   *
   * @param params
   * @param list
   */
  private def validateParam(params: ParameterTool, list: List[FlinkJobParam]): Boolean = {
    val properties = params.getProperties()
    list.map(param => properties.containsKey(param.paramKey)).fold(true)(_ && _)
  }
}