package fhnw.emoba.blockbuster

import fhnw.emoba.blockbuster.data.TMDBService
import fhnw.emoba.blockbuster.data.TMDBService.apiKey
import org.json.JSONObject
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*;

class APICallTest {
    @Before
    fun initialize(){
        val apiKey= TMDBService.apiKey
    }

    @Test
    fun reachableAPITest(){
        val json = TMDBService.request("https://api.themoviedb.org/3/authentication?api_key=$apiKey")
        assertEquals(json.getBoolean("success"), true)
    }



}