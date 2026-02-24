package com.example.backend

import cask.main.Main
import cask.Response

object Main extends cask.MainRoutes {
  
  // Helper to add CORS headers
  def withCors(data: String, statusCode: Int = 200) = {
    Response(
      data = data,
      statusCode = statusCode,
      headers = Seq(
        "Access-Control-Allow-Origin" -> "*",
        "Access-Control-Allow-Methods" -> "GET, POST, PUT, DELETE, OPTIONS",
        "Access-Control-Allow-Headers" -> "Content-Type",
        "Content-Type" -> "text/plain"
      )
    )
  }
  
  def withCorsJson(data: ujson.Value) = {
    Response(
      data = data.toString(),
      statusCode = 200,
      headers = Seq(
        "Access-Control-Allow-Origin" -> "*",
        "Access-Control-Allow-Methods" -> "GET, POST, PUT, DELETE, OPTIONS",
        "Access-Control-Allow-Headers" -> "Content-Type",
        "Content-Type" -> "application/json"
      )
    )
  }
  
  @cask.get("/")
  def root() = {
    withCors("Backend is running!")
  }
  
  @cask.get("/api/hello")
  def hello(name: String = "World") = {
    withCorsJson(ujson.Obj(
      "message" -> s"Hello, $name!",
      "timestamp" -> System.currentTimeMillis()
    ))
  }
  
  @cask.get("/api/status")
  def status() = {
    withCorsJson(ujson.Obj(
      "status" -> "ok",
      "service" -> "backend",
      "version" -> "1.0.0"
    ))
  }
  
  override def port: Int = sys.env.getOrElse("PORT", "8080").toInt
  override def host: String = "0.0.0.0"
  
  initialize()
}
