import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.blockbuster.data.Movie
import fhnw.emoba.blockbuster.data.Person
import fhnw.emoba.blockbuster.data.TMDBService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object BlockbusterModel {

    private val _searchquery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchquery

    private val _movieSearch = MutableStateFlow("")
    val movieSearch: StateFlow<String> = _movieSearch

    private val _personSearch = MutableStateFlow("")
    val personSearch: StateFlow<String> = _personSearch

    private val _favorites = mutableStateListOf<Movie>()
    val favorites: List<Movie> = _favorites

    val backgroundJob = SupervisorJob()
    val modelScope = CoroutineScope(backgroundJob + Dispatchers.IO)

    var personList: List<Person> by mutableStateOf(emptyList())
    var movieList: List<Movie> by mutableStateOf(emptyList())


    var selectedTab: MutableState<TabType> = mutableStateOf(TabType.MOVIES)


    fun updateSearchQuery(query: String) {
        if (selectedTab.value == TabType.MOVIES) {
            _movieSearch.value = query
            _searchquery.value = movieSearch.value
        } else if (selectedTab.value == TabType.PEOPLE) {
            _personSearch.value = query
            _searchquery.value = personSearch.value
        }
    }


    fun toggleFavorite(movie: Movie) {
        if (_favorites.contains(movie)) {
            _favorites.remove(movie)
        } else {
            _favorites.add(movie)
        }
    }

    fun isFavorite(movie: Movie): Boolean {
        return _favorites.contains(movie)
    }

    fun loadVideo(movie: Movie) {
        modelScope.launch {
            TMDBService.loadVideo(movie)
        }
    }

    fun search() {
        if (selectedTab.value == TabType.MOVIES) {
            searchMovies()
        } else if (selectedTab.value == TabType.PEOPLE) {
            searchPerson()
        }
    }


    fun searchMovies() {
        modelScope.launch {
            movieList = TMDBService.SearchMovie(movieSearch.value)
        }
    }

    fun searchPerson() {
        modelScope.launch {
            personList = TMDBService.SearchPerson(personSearch.value)
        }
    }



    fun selectTab(tab: TabType) {
        selectedTab.value = tab
        if (tab== TabType.MOVIES) {
            _searchquery.value = movieSearch.value
        } else if (tab == TabType.PEOPLE) {
            _searchquery.value = personSearch.value
        }
    }
}

enum class TabType {
    FAVS,
    MOVIES,
    PEOPLE
}
