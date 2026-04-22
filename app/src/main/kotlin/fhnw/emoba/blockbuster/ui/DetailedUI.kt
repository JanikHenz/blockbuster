package fhnw.emoba.blockbuster.ui

import androidx.annotation.NonNull
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import fhnw.emoba.blockbuster.data.Movie
import fhnw.emoba.blockbuster.data.Video

@Composable
fun MovieDetailContent(movie: Movie, onBack: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(32,32,32)),
        verticalArrangement = Arrangement.spacedBy(16.dp)  // Abstände zwischen den Elementen
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color(1,180,228),
                        contentDescription = "Back"
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = movie.name, fontSize = 24.sp, color = Color(1,180,228))
            }
        }

        item {
            // Poster
            Image(
                bitmap = movie.picture,
                contentDescription = "Movie Poster",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
        }

        item {
            // Beschreibung und weitere Details
            Text(
                text = movie.description,
                fontSize = 16.sp,
                color = Color.White
            )
        }

        item {
            // YouTube-Player
            movie.video?.let { YouTubePlayer(it) }
        }

        item {
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun YouTubePlayer(video: Video) {
    val context = LocalContext.current  // Hier den Context holen
    val activityLifecycle = LocalLifecycleOwner.current.lifecycle
    val youtubePlayer = YouTubePlayerView(context).apply {
        activityLifecycle.addObserver(this)
        enableAutomaticInitialization = false
        initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(video.key, 0f)
            }
        })
    }
    AndroidView(
        factory = { youtubePlayer },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        update = {}
    )
}