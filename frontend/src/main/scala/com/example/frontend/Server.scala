package com.example.frontend

import cask.main.Main

object Server extends cask.MainRoutes {
  
  // Serve the main HTML page
  @cask.get("/")
  def index() = {
    cask.Response(
      data = getClass.getResourceAsStream("/index.html"),
      headers = Seq("Content-Type" -> "text/html")
    )
  }
  
  // Serve static resources
  @cask.staticResources("/static")
  def staticResourceRoutes() = "/"
  
  override def port: Int = sys.env.getOrElse("PORT", "3000").toInt
  override def host: String = "0.0.0.0"
  
  initialize()
}
