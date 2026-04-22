package fhnw.emoba.blockbuster.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.json.JSONObject

data class Movie(
   val  movieJson: JSONObject
) {
    val id = movieJson.getString("id")
    val name = movieJson.getString("title")
    val description = movieJson.getString("overview")
    val picture = TMDBService.getImg(movieJson.getString("poster_path"), "/w500")
    var video: Video? by mutableStateOf(null)

    fun addVideo(video: Video) {
        this.video = video
    }
}