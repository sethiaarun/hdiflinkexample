package com.ms.hdi.hilo.flink.stream.source

import com.ms.hdi.flink.business.model.Customer
import com.ms.hdi.flink.mockneat.DataGenUtil
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext

import scala.collection.JavaConverters.asScalaSetConverter

/**
 * input parameters for [[MockNeatCustomerSourceFunction]]
 * @param numberOfRecordsInABatch
 * @param sleepTimeBetweenEventsMs
 * @param runningTTimeMs
 */
case class CustomerSourceParam(numberOfRecordsInABatch: Int,
                               sleepTimeBetweenEventsMs: Long,
                               runningTTimeMs: Long)
/**
 * Stream Data Source for Flink, this will generate Customer data for Stream
 *
 * @param customerSourceParam  source parameters
 */
class MockNeatCustomerSourceFunction(customerSourceParam: CustomerSourceParam) extends SourceFunction[Customer] {

  import com.ms.hdi.flink.mockneat.DataGenUtil._

  // flag indicating whether source is still running.
  private var isRunning = true

  private val startTime = System.currentTimeMillis()

  override def run(sourceContext: SourceContext[Customer]): Unit = {
    // mock neat data generation
    val mockNeat = DataGenUtil.mockNeat(System.currentTimeMillis())
    val paramList: List[Object] = Customer.getParamList(mockNeat)
    val customerMockUnit = mockNeat.mockUnit[Customer](paramList)
    // keep running if not cancelled -> cancel function is not called or total time lapsed
    while (isRunning &&
      ((System.currentTimeMillis() - startTime) < customerSourceParam.runningTTimeMs)|| customerSourceParam.runningTTimeMs<0) {
      val data = customerMockUnit.set(customerSourceParam.numberOfRecordsInABatch).get().asScala
      data.foreach(c => sourceContext.collect(c))
      Thread.sleep(customerSourceParam.sleepTimeBetweenEventsMs)
    }
  }

  override def cancel(): Unit = {
    isRunning = false
  }

}