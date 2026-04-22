package fhnw.emoba.blockbuster.ui

import BlockbusterModel
import TabType
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.blockbuster.data.Movie
import fhnw.emoba.blockbuster.data.Person

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    selectedTab: TabType,
    movieList: List<Movie>,
    favList: List<Movie>,
    personList: List<Person>,
    model: BlockbusterModel,
    onMovieSelected: (Movie) -> Unit
) {
    when (selectedTab) {
        TabType.FAVS -> FavListContent(movies = favList, onMovieSelected = onMovieSelected, model = model)
        TabType.MOVIES -> MovieListContent(movies = movieList, onMovieSelected = onMovieSelected, model = model)
        TabType.PEOPLE -> PersonListContent(persons = personList)
    }
}


@Composable
fun MovieListContent(
    movies: List<Movie>,
    onMovieSelected: (Movie) -> Unit,
    model: BlockbusterModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(32,32,32))
            .padding(8.dp)
    ) {
        items(movies) { movie ->
            MovieItem(
                movie = movie,
                onMovieSelected = onMovieSelected,
                onFavoriteToggle = { model.toggleFavorite(movie) },
                isFavorite = model.isFavorite(movie)
            )
        }

    }
}


@Composable
fun MovieItem(
    movie: Movie,
    onMovieSelected: (Movie) -> Unit,
    onFavoriteToggle: (Movie) -> Unit,
    isFavorite: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onMovieSelected(movie) },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                bitmap = movie.picture,
                contentDescription = "Movie Poster",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = movie.name,
                    fontSize = 20.sp,
                    color = Color(1,180,228)
                )
                IconButton(onClick = { onFavoriteToggle(movie) }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.Red,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movie.description,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun FavListContent(movies: List<Movie>, onMovieSelected: (Movie) -> Unit, model: BlockbusterModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(32,32,32))
            .padding(8.dp)
    ) {
        items(model.favorites) { movie ->
            MovieItem(
                movie = movie,
                onMovieSelected = onMovieSelected,
                onFavoriteToggle = { model.toggleFavorite(movie) },
                isFavorite = model.isFavorite(movie)
            )
        }
    }
}


@Composable
fun PersonListContent(persons: List<Person>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(32,32,32))
            .padding(8.dp)
    ) {
        items(persons) { person ->
            PersonItem(person)
        }
    }
}

@Composable
fun PersonItem(person: Person) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Placeholder for movie image
            Image(
                bitmap = person.picture,
                contentDescription = "Movie Poster",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            // Column for text content (title and description)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = person.name, // Placeholder for movie title
                    fontSize = 20.sp,
                    color = Color(1,180,228)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Actor", // Placeholder for movie description
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
            }
        }
    }
}