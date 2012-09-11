package com.example

import cc.spray._

import org.ws4d.coap.client.Client

trait HelloService extends Directives {

  val helloService = {
    path("temperature") {
      get { _.complete(new Client().getTemperature().get().toString()) }
    } ~
    path("humidity") {
      get { _.complete(new Client().getHumidity().get().toString()) }
    }
  }
}
