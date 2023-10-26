package com.ms.hdi.flink.business.model

import com.ms.hdi.flink.mockneat.DataGenUtil
import net.andreinc.mockneat.abstraction.MockUnit
import org.scalatest.funsuite.AnyFunSuite

/**
 * Test cases for Customer data generation
 */
class CustomerDataGenTest extends  AnyFunSuite {

  val mockNeat = DataGenUtil.mockNeat(System.currentTimeMillis())
  val paramList:List[Object] = Customer.getParamList(mockNeat)
  val custMockUnit = mockNeat.mockUnit[Customer](paramList)

  test("creates MockUnit object") {
    val v1=custMockUnit.get()
    val v2=custMockUnit.get()
    assert(custMockUnit.isInstanceOf[MockUnit[Customer]])
  }

  test("creates two customer objects") {
    val size= 2
    assert(mockNeat.mockObject[Customer](paramList,size).size()===size)
  }
}
