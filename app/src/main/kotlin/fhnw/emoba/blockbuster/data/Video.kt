package fhnw.emoba.blockbuster.data

import org.json.JSONObject

data class Video(
   val videoJson: JSONObject
){
  val  id = videoJson.getString("id")
   val  name = videoJson.getString("name")
  val  key = videoJson.getString("key")
}