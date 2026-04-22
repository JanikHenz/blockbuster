package fhnw.emoba.blockbuster.ui

import TabType
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(selectedTab: TabType) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        colors = TopAppBarColors(
            containerColor = Color.Black,
            scrolledContainerColor = Color.Red,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.White,
            actionIconContentColor = Color.Red
        ),
        title = {
            Text(text = if (selectedTab == TabType.FAVS) "Favs" else if (selectedTab == TabType.PEOPLE) "Persons" else "Movies", color = Color(1,180,228))
        },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .padding(10.dp)
            )
        }
    )
}

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    TextField(
        value = searchQuery,
        textStyle = TextStyle(fontSize = 14.sp),
        onValueChange = onSearchQueryChanged,
        placeholder = { Text(text = "Suche nach Filmen oder Schauspielern...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color.Black),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.DarkGray,
            focusedContainerColor = Color.DarkGray,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            unfocusedPlaceholderColor = Color.White
        ),
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = { onSearch() }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    )
}

@Composable
fun BottomNavigationBar(selectedTab: TabType, onTabSelected: (TabType) -> Unit) {
    NavigationBar(
        containerColor = Color.Black
    ) {
        NavigationBarItem(
            selected = selectedTab == TabType.FAVS,
            onClick = { onTabSelected(TabType.FAVS) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.liebe),
                    contentDescription = "FAVS",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("FAVS") },
            alwaysShowLabel = true,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(1,180,228),
                unselectedIconColor = Color.White,
                selectedTextColor = Color(1,180,228),
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            selected = selectedTab == TabType.MOVIES,
            onClick = { onTabSelected(TabType.MOVIES) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.movie),
                    contentDescription = "MOVIE",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("MOVIE") },
            alwaysShowLabel = true,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(1,180,228),
                unselectedIconColor = Color.White,
                selectedTextColor = Color(1,180,228),
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            selected = selectedTab == TabType.PEOPLE,
            onClick = { onTabSelected(TabType.PEOPLE) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "PERSON",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("PERSON") },
            alwaysShowLabel = true,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(1,180,228),
                unselectedIconColor = Color.White,
                selectedTextColor = Color(1,180,228),
                unselectedTextColor = Color.White
            )
        )
    }
}