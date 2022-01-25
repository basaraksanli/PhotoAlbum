package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.basaraksanli.photoAlbum.R
import com.basaraksanli.photoAlbum.feature_album.domain.model.Album
import com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen.AlbumListState
import com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen.AlbumListViewModel

@Composable
fun AlbumListWidget(
    navController: NavController,
    userName: String,
    userId: Int,
    viewModel: AlbumListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    if (state is AlbumListState.AlbumListScreenLoaded) {
        val albumItemPairs = arrayListOf<Pair<Album, Album?>>()


        for (i: Int in 0 until state.albumList[userId]?.size!!) {
            if (i % 2 == 0) {
                val pair = if (i + 1 < state.albumList[userId]?.size!!)
                    Pair(state.albumList[userId]?.get(i)!!, state.albumList[userId]?.get(i + 1))
                else
                    Pair(state.albumList[userId]?.get(i)!!, null)
                albumItemPairs.add(pair)
            }
        }

        Column {
            Text(
                text = stringResource(R.string.albums),
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            LazyColumn(contentPadding = PaddingValues(15.dp)) {
                val itemCount = albumItemPairs.size
                items(itemCount) {
                    albumItemPairs[it].let { albumItemPair ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AlbumEntry(
                                entry = albumItemPair.first,
                                userName = userName,
                                navController = navController,
                                imageId = viewModel.getImage(2 * it, state.thumbnailList[userId])
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            albumItemPair.second?.let { secondPair ->
                                AlbumEntry(
                                    entry = secondPair,
                                    userName,
                                    navController = navController,
                                    imageId = viewModel.getImage(
                                        2 * (it + 1) - 1,
                                        state.thumbnailList[userId]
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
