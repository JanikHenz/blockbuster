package fhnw.emoba.blockbuster

import BlockbusterModel
import android.graphics.Bitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import fhnw.emoba.blockbuster.data.Movie
import fhnw.emoba.blockbuster.data.Video

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 *  ACHTUNG: Auf Android arbeiten wir (noch) mit JUNIT 4
 */
@RunWith(AndroidJUnit4::class)
class ViewModelTest {

       var  movie = Movie(
            id = "1",
            name = "Test",
            description = "Test",
            picture = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888).asImageBitmap(),
            video = Video(
                id = "1",
                key = "Test",
                name = "Test"
            )
        )


    @Test
    fun toggleFavTest(){
        BlockbusterModel.toggleFavorite(movie = movie)
        assertTrue(BlockbusterModel.isFavorite(movie))
        BlockbusterModel.toggleFavorite(movie = movie)
        assertFalse(BlockbusterModel.isFavorite(movie))
    }


}