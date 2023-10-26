package com.ms.hdi.flink.mockneat

import net.andreinc.mockneat.MockNeat
import net.andreinc.mockneat.abstraction.MockUnit
import net.andreinc.mockneat.types.enums.RandomType

import scala.reflect.ClassTag


/**
 * Utility object to generate Data for testing
 */
object DataGenUtil {

  /**
   * get mock neat object
   * @param seed
   * @param randomType
   * @return
   */
  def mockNeat(seed: Long, randomType: RandomType=RandomType.THREAD_LOCAL): MockNeat = {
    MockNeat.threadLocal()

  }

  implicit class MockNeatDataGen(mockNeat: MockNeat) {
    /**
     * generate mock unit
     *
     * @param params
     * @return
     */
    def mockUnit[U](params:List[Object])(implicit ct: ClassTag[U]): MockUnit[U] = {
      (mockNeat.constructor(ct.runtimeClass).params(params: _*)).asInstanceOf[MockUnit[U]]
    }

    /**
     * generate mock unit for given size
     *
     * @param params
     * @param size
     * @return
     */
    def mockUnit[U](params:List[Object], size:Int)(implicit ct: ClassTag[U]): MockUnit[java.util.Set[U]] = {
      (mockNeat.constructor(ct.runtimeClass).params(params: _*)).asInstanceOf[MockUnit[U]].set(size)
    }

    /**
     * get list of given object mocked data
     *
     * @param params
     * @param size
     * @return
     */
    def mockObject[U](params:List[Object], size:Int)(implicit ct: ClassTag[U]): java.util.Set[U] = {
      (mockNeat.constructor(ct.runtimeClass).params(params: _*)).asInstanceOf[MockUnit[U]].set(size).get()
    }
  }
}