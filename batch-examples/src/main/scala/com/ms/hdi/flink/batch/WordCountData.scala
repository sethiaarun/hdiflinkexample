package com.ms.hdi.flink.batch

import scala.io.Source

/**
 * Data for the WordCount Writer
 */
object WordCountData {

  /**
   * get word lines from resources
   * @return
   */
   def wordData(): List[String] = {
     Source.fromResource("wordcount/data.txt").getLines().toList
   }

}


