package com.ms.hdi.flink.stream.job

import org.apache.flink.runtime.testutils.MiniClusterResourceConfiguration
import org.apache.flink.test.util.MiniClusterWithClientResource
import org.scalatest.funsuite.AnyFunSuite

class CustomerStreamingJobTest extends AnyFunSuite {

  //Starts a Flink mini cluster as a resource and registers
  //the respective ExecutionEnvironment and StreamExecutionEnvironment.
  val flinkCluster: MiniClusterWithClientResource =
  new MiniClusterWithClientResource(
    new MiniClusterResourceConfiguration.Builder()
      .setNumberSlotsPerTaskManager(2)
      .setNumberTaskManagers(1)
      .build()
  );
}
