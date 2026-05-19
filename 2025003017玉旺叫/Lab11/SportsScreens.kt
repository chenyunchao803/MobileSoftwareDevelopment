package com.example.sports.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sports.model.Sport
import com.example.sports.ui.utils.SportsContentType

@Composable
fun SportsApp(
    windowWidthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val sportsList: List<Sport> = listOf(
        Sport(
            id = 1,
            titleResourceId = 0,
            subtitleResourceId = 0,
            playerCount = 9,
            olympic = true,
            imageResourceId = com.example.sports.R.drawable.ic_baseball_square,
            sportsImageBanner = com.example.sports.R.drawable.ic_baseball_banner,
            sportDetails = 0
        ),
        Sport(
            id = 2,
            titleResourceId = 0,
            subtitleResourceId = 0,
            playerCount = 5,
            olympic = true,
            imageResourceId = com.example.sports.R.drawable.ic_basketball_square,
            sportsImageBanner = com.example.sports.R.drawable.ic_basketball_banner,
            sportDetails = 0
        ),
        Sport(
            id = 3,
            titleResourceId = 0,
            subtitleResourceId = 0,
            playerCount = 2,
            olympic = true,
            imageResourceId = com.example.sports.R.drawable.ic_badminton_square,
            sportsImageBanner = com.example.sports.R.drawable.ic_badminton_banner,
            sportDetails = 0
        ),
        Sport(
            id = 4,
            titleResourceId = 0,
            subtitleResourceId = 0,
            playerCount = 1,
            olympic = false,
            imageResourceId = com.example.sports.R.drawable.ic_bowling_square,
            sportsImageBanner = com.example.sports.R.drawable.ic_bowling_banner,
            sportDetails = 0
        ),
        Sport(
            id = 5,
            titleResourceId = 0,
            subtitleResourceId = 0,
            playerCount = 1,
            olympic = true,
            imageResourceId = com.example.sports.R.drawable.ic_cycling_square,
            sportsImageBanner = com.example.sports.R.drawable.ic_cycling_banner,
            sportDetails = 0
        ),
        Sport(
            id = 6,
            titleResourceId = 0,
            subtitleResourceId = 0,
            playerCount = 1,
            olympic = false,
            imageResourceId = com.example.sports.R.drawable.ic_golf_square,
            sportsImageBanner = com.example.sports.R.drawable.ic_golf_banner,
            sportDetails = 0
        ),
        Sport(
            id = 7,
            titleResourceId = 0,
            subtitleResourceId = 0,
            playerCount = 1,
            olympic = true,
            imageResourceId = com.example.sports.R.drawable.ic_running_square,
            sportsImageBanner = com.example.sports.R.drawable.ic_running_banner,
            sportDetails = 0
        ),
        Sport(
            id = 8,
            titleResourceId = 0,
            subtitleResourceId = 0,
            playerCount = 11,
            olympic = true,
            imageResourceId = com.example.sports.R.drawable.ic_soccer_square,
            sportsImageBanner = com.example.sports.R.drawable.ic_soccer_banner,
            sportDetails = 0
        ),
        Sport(
            id = 9,
            titleResourceId = 0,
            subtitleResourceId = 0,
            playerCount = 1,
            olympic = true,
            imageResourceId = com.example.sports.R.drawable.ic_swimming_square,
            sportsImageBanner = com.example.sports.R.drawable.ic_swimming_banner,
            sportDetails = 0
        ),
        Sport(
            id = 10,
            titleResourceId = 0,
            subtitleResourceId = 0,
            playerCount = 2,
            olympic = true,
            imageResourceId = com.example.sports.R.drawable.ic_table_tennis_square,
            sportsImageBanner = com.example.sports.R.drawable.ic_table_tennis_banner,
            sportDetails = 0
        ),
        Sport(
            id = 11,
            titleResourceId = 0,
            subtitleResourceId = 0,
            playerCount = 2,
            olympic = true,
            imageResourceId = com.example.sports.R.drawable.ic_tennis_square,
            sportsImageBanner = com.example.sports.R.drawable.ic_tennis_banner,
            sportDetails = 0
        )
    )

    var selectedSport: Sport by remember { mutableStateOf(sportsList.first()) }
    var isListPage: Boolean by remember { mutableStateOf(true) }

    val contentType = when (windowWidthSizeClass) {
        WindowWidthSizeClass.Expanded -> SportsContentType.ListAndDetail
        else -> SportsContentType.ListOnly
    }

    if (contentType == SportsContentType.ListOnly) {
        if (isListPage) {
            SportsList(
                sports = sportsList,
                onClick = {
                    selectedSport = it
                    isListPage = false
                },
                modifier = modifier
            )
        } else {
            SportsDetail(
                selectedSport = selectedSport,
                onBackPressed = { isListPage = true },
                modifier = modifier
            )
        }
    } else {
        BackHandler {}
        Row(modifier = modifier.fillMaxSize()) {
            // 左侧列表占40%宽度
            SportsList(
                sports = sportsList,
                onClick = { selectedSport = it },
                modifier = Modifier.fillMaxWidth(0.4f)
            )
            // 右侧详情占60%宽度
            SportsDetail(
                selectedSport = selectedSport,
                onBackPressed = {},
                modifier = Modifier.fillMaxWidth(0.6f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsList(
    sports: List<Sport>,
    onClick: (Sport) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(topBar = { SportsTopAppBar() }, modifier = modifier) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(sports) { sport ->
                SportsListItem(sport = sport, onClick = onClick)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsListItem(
    sport: Sport,
    onClick: (Sport) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(onClick = { onClick(sport) }, modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = sport.imageResourceId),
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = when(sport.id){
                    1->"Baseball"
                    2->"Basketball"
                    3->"Badminton"
                    4->"Bowling"
                    5->"Cycling"
                    6->"Golf"
                    7->"Running"
                    8->"Soccer"
                    9->"Swimming"
                    10->"Table Tennis"
                    else->"Tennis"
                },
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsDetail(
    selectedSport: Sport,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { SportsTopAppBar(false, onBackPressed) },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = selectedSport.sportsImageBanner),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(250.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = when(selectedSport.id){
                    1->"Baseball"
                    2->"Basketball"
                    3->"Badminton"
                    4->"Bowling"
                    5->"Cycling"
                    6->"Golf"
                    7->"Running"
                    8->"Soccer"
                    9->"Swimming"
                    10->"Table Tennis"
                    else->"Tennis"
                },
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "Sport detail information",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsTopAppBar(
    isListPage: Boolean = true,
    onBackPressed: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = if (isListPage) "Sports" else "Details") },
        navigationIcon = {
            if (!isListPage) {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        modifier = modifier
    )
}