package com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen.components

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
import com.basaraksanli.photoAlbum.feature_album.domain.model.PhotoListItem
import com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen.PhotoListScreenState
import com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen.PhotoListViewModel

@Composable
fun PhotoListWidget(
    navController: NavController,
    viewModel: PhotoListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    if (state is PhotoListScreenState.PhotoListScreenLoaded) {

        val photoItemPairs = arrayListOf<Pair<PhotoListItem, PhotoListItem?>>()

        for (i: Int in state.photoList.indices) {
            if (i % 2 == 0) {
                val pair = if (i + 1 < state.photoList.size)
                    Pair(state.photoList[i], state.photoList[i + 1])
                else
                    Pair(state.photoList[i], null)
                photoItemPairs.add(pair)
            }
        }

        Column {
            Text(
                text = stringResource(R.string.albums_without_punc),
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            LazyColumn(contentPadding = PaddingValues(15.dp)) {
                val itemCount = photoItemPairs.size
                items(itemCount) {
                    photoItemPairs[it].let { photoItemPair ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            PhotoEntry(
                                entry = photoItemPair.first,
                                navController = navController,
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            photoItemPair.second?.let { secondPair ->
                                PhotoEntry(
                                    entry = secondPair,
                                    navController = navController,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}