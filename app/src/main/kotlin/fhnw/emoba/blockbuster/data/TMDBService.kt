package fhnw.emoba.blockbuster.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.lifecycle.viewmodel.compose.viewModel
import fhnw.emoba.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection

object TMDBService {

    val apiKey = "e621c29438c257e37d24c31d6f3ab3eb"
    val language = "en"
    val includeAdults = "false"



    fun request(url: String): JSONObject {
        val conn = URL(url).openConnection() as HttpsURLConnection
        conn.connect()
        val reader = BufferedReader(InputStreamReader(conn.inputStream, Charsets.UTF_8))
        val jsonString = reader.readText()
        reader.close()
        conn.disconnect()
        return JSONObject(jsonString)
    }


    fun SearchMovie(query: String): List<Movie> {
        try {
            val json =
                request("https://api.themoviedb.org/3/search/movie?api_key=$apiKey&query=$query&include_adult=$includeAdults&language=$language&page=1")
            val resultsArray = json.getJSONArray("results")
            val movieList = mutableListOf<Movie>()
            for (i in 0 until resultsArray.length()) {
                val movieJson = resultsArray.getJSONObject(i)
                val movie = Movie(movieJson)
                movieList.add(movie)
            }
            return movieList
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList<Movie>()
        }
        return listOf()
    }


    fun getImg(picturePath: String, width: String): ImageBitmap {
        try {
            val url = URL("https://image.tmdb.org/t/p$width$picturePath")
            val conn = url.openConnection() as HttpsURLConnection
            conn.connect()
            val inputStream = conn.inputStream
            val allBytes = inputStream.readBytes()
            inputStream.close()
            return BitmapFactory.decodeByteArray(allBytes, 0, allBytes.size)
                .asImageBitmap()
        } catch (e: Exception) {
            val width = 1
            val height = 1
            val emptyBitmap = Bitmap.createBitmap(
                width,
                height,
                Bitmap.Config.ARGB_8888
            )
            return emptyBitmap.asImageBitmap()
        }
    }

    fun loadVideo(movie:Movie){
        try {
            val movieId = movie.id
            val json =
                request("https://api.themoviedb.org/3/movie/$movieId/videos?api_key=$apiKey&language=$language")
            val resultsArray = json.getJSONArray("results")
            val videoList = mutableListOf<Video>()
            for (i in 0 until resultsArray.length()) {
                val videoJson = resultsArray.getJSONObject(i)
                val video = Video(videoJson)
                videoList.add(video)
            }
            movie.addVideo(videoList.get(0))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun SearchPerson(query: String): List<Person> {
        try {
            val json =
                request("https://api.themoviedb.org/3/search/person?api_key=$apiKey&query=$query&include_adult=$includeAdults&language=$language&page=1")
            val resultsArray = json.getJSONArray("results")
            val personList = mutableListOf<Person>()
            for (i in 0 until resultsArray.length()) {
                val personJson = resultsArray.getJSONObject(i)
                val person = Person(personJson)
                personList.add(person)
            }
            return personList
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList<Movie>()
        }
        return listOf()
    }

}

