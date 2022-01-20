package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.basaraksanli.photoAlbum.core.presentation.components.ErrorComponent
import com.basaraksanli.photoAlbum.core.presentation.components.FullScreenProgressBar
import com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen.components.UserListWidget
import com.basaraksanli.photoAlbum.feature_album.util.AppSizes
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@Composable
fun AlbumListScreen(navController: NavController, viewModel: AlbumListViewModel = hiltViewModel()) {


    val state = viewModel.state.value

    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        when (state) {
            is AlbumListState.AlbumListScreenLoaded -> AlbumListScreenMainComponent(navController = navController)
            is AlbumListState.AlbumListScreenLoading -> FullScreenProgressBar()
            is AlbumListState.AlbumListScreenNetworkError -> ErrorComponent()
            is AlbumListState.AlbumListScreenUsersLoaded -> FullScreenProgressBar()
        }
    }
}

@ExperimentalPagerApi
@Composable
fun AlbumListScreenMainComponent(navController: NavController) {
    Column {
        Box {
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
            )
            Column {
                Spacer(modifier = Modifier.height(AppSizes.USER_CARD_SIZE.dp / 10))
                UserListWidget(navController = navController)
            }
        }
    }
}







