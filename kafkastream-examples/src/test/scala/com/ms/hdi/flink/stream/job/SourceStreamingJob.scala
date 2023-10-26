package com.ms.hdi.flink.stream.job

import com.ms.hdi.flink.stream.source.MockNeatCustomerSourceFunction
import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.datastream.DataStreamSource
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.streaming.api.functions.source.SourceFunction


/**
 * generic streaming Job where source is transform into Sink process
 *
 * @param source
 * @param sink
 * @tparam U
 * @param T
 * @param fn - Transform map function
 */
class SourceStreamingJob[U, T](val source: SourceFunction[U],
                               mapFn: MapFunction[U, T],
                               val sink: SinkFunction[T]) {
  @throws[Exception]
  def execute()(implicit inTypeInformation: TypeInformation[U],
                outTypeInformation: TypeInformation[T]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val sourceStream: DataStreamSource[U] = env.addSource(source)
    sourceStream.map(mapFn).addSink(sink);
    env.execute
  }
}

/**
 * Test Customer Source Streaming Job with Transformation of Customer Name
 */
object CustomerSourceStreamingJob extends App {

  import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction

  val customerMapFn = new MapFunction[Customer, String] {
    override def map(r: Customer): String = r.customerName
  }
  // run every 100 ms , total 10000 ms , each batch will have 10 records
  // total run 100, It will produce 100x10 = 1000 records in 10000 ms
  val job = new SourceStreamingJob[Customer, String](
    new MockNeatCustomerSourceFunction(10, 100, 10000),
    customerMapFn,
    new PrintSinkFunction[String]
  )
  job.execute
}
