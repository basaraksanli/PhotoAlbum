package com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.basaraksanli.photoAlbum.core.presentation.components.ErrorComponent
import com.basaraksanli.photoAlbum.core.presentation.components.FullScreenProgressBar
import com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen.components.AlbumInfoCard
import com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen.components.PhotoListWidget
import com.basaraksanli.photoAlbum.feature_album.util.AppSizes

@Composable
fun PhotoListScreen(
    navController: NavController,
    albumId: Int,
    albumTitle: String,
    userName: String,
    viewModel: PhotoListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        when (state) {
            is PhotoListScreenState.PhotoListScreenLoaded -> PhotoListScreenMainComponent(
                navController = navController
            )
            is PhotoListScreenState.PhotoListScreenLoading -> FullScreenProgressBar()
            is PhotoListScreenState.PhotoListNetworkError -> ErrorComponent()
        }
    }
}

@Composable
fun PhotoListScreenMainComponent(
    navController: NavController,
    viewModel: PhotoListViewModel = hiltViewModel(),
) {

    Column {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                )
                .background(
                    color = MaterialTheme.colors.secondary
                )
                .height(AppSizes.USER_CARD_SIZE.dp)
                .fillMaxWidth(),
        ) {

            Column {
                Spacer(modifier = Modifier.height(AppSizes.USER_CARD_SIZE.dp / 10))
                AlbumInfoCard(viewModel.userName!!, viewModel.albumTitle!!)
            }
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        navController.popBackStack()
                    }
                    .align(Alignment.TopStart)
            )

        }
        PhotoListWidget(navController = navController)
    }
}





