package fhnw.emoba.blockbuster.data

import androidx.compose.ui.graphics.ImageBitmap
import org.json.JSONObject

data class Person(
    val personJson: JSONObject
){
   val id = personJson.getString("id")
   val name = personJson.getString("name")
   val picture = TMDBService.getImg(personJson.getString("profile_path"), "/w200")
}