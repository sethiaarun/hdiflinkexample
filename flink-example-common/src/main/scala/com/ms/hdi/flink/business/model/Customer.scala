package com.ms.hdi.flink.business.model

import net.andreinc.mockneat.MockNeat

import java.time.LocalDate


/**
 * Customer Model
 *
 * @param customerId
 * @param customerName     customer name
 * @param firstName        first name
 * @param lastName         last name
 * @param userName         user name
 * @param registrationDate registration date
 */
case class Customer(customerId: Int, customerName: String, firstName: String,
                    lastName: String, userName: String, registrationDate: String)

object Customer {

  private val DateStart = LocalDate.of(2014, 1, 1)
  private val DateEnd = LocalDate.of(2016, 1, 1)
  def getParamList(mockNeat: MockNeat):List[Object] = {
    List(mockNeat.intSeq().start(1).increment(1),
      mockNeat.names().full(),
      mockNeat.names().first(),
      mockNeat.names().last(),
      mockNeat.users(),
      mockNeat.localDates.between(DateStart, DateEnd).mapToString()
    )
  }
}