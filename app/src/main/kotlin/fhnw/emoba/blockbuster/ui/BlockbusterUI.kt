package fhnw.emoba.blockbuster.ui

import BlockbusterModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fhnw.emoba.blockbuster.data.Movie


@Composable
fun AppUI(model: BlockbusterModel) {
    val searchQuery by model.searchQuery.collectAsState()
    val movieList = model.movieList
    val personList = model.personList
    val favList = model.favorites
    var selectedMovie by remember { mutableStateOf<Movie?>(null) }
    val selectedTab by model.selectedTab
    with(model) {
        Scaffold(
            topBar = {
                Column(modifier = Modifier.background(Color.Black).fillMaxWidth(1f)) {
                    TopBar(selectedTab = selectedTab)
                    SearchBar(
                        searchQuery = searchQuery,
                        onSearchQueryChanged = { newQuery ->
                            model.updateSearchQuery(newQuery)
                        },
                        onSearch = {
                            model.search()
                        }
                    )
                }
            },
            bottomBar = {
                BottomNavigtionBar(selectedTab = selectedTab,onTabSelected = { tab -> model.selectTab(tab) })
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .background(Color(32,32,32))
                ) {
                    if (selectedMovie == null) {
                        MainContent(
                            modifier = Modifier.padding(innerPadding).background(Color.DarkGray),
                            selectedTab = selectedTab,
                            movieList = movieList,
                            favList = favList,
                            personList = personList,
                            model = model,
                            onMovieSelected = {movie ->
                                selectedMovie = movie
                                model.loadVideo(movie)
                            }
                        )
                    } else {
                        MovieDetailContent(
                            movie = selectedMovie!!,
                            onBack = { selectedMovie = null }
                        )
                    }
                }
            }
        )
    }
}






