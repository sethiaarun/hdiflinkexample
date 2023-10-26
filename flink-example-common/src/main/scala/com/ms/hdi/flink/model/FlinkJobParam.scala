package com.ms.hdi.flink.model

import org.apache.flink.api.java.utils.ParameterTool

/**
 * Flick Job Parameters
 *
 * @param paramKey
 * @param keyDesc
 */
case class FlinkJobParam(paramKey: String, keyDesc: String) {
  override def toString(): String = {
    s"Key:${paramKey}, desc:${keyDesc}"
  }
}

/**
 * Companion Object
 */
object FlinkJobParam {

  implicit class ListFlinkJobParam(list: List[FlinkJobParam]) {
    /**
     * print list of Job Parameter List
     *
     * @param list
     */
    def print() = {
      list.foreach(println)
    }

    /**
     * validate Parameters
     *
     * @param params
     * @param list
     */
    def validateParam(params: ParameterTool): Boolean = {
      val properties = params.getProperties()
      list.map(param => properties.containsKey(param.paramKey)).fold(true)(_ && _)
    }
  }

}
