package com.basaraksanli.photoAlbum.feature_album.presentation.photodetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.basaraksanli.photoAlbum.core.presentation.components.ErrorComponent
import com.basaraksanli.photoAlbum.core.presentation.components.FullScreenProgressBar
import com.basaraksanli.photoAlbum.util.ShimmerAnimation

@ExperimentalCoilApi
@Composable
fun PhotoDetailScreen(
    navController: NavController,
    albumTitle: String,
    photoTitle: String,
    url: String,
    viewModel: PhotoDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        when (state) {
            is PhotoDetailScreenState.PhotoDetailScreenLoaded -> {
                Column {
                    Box {
                        Column {
                            Card(
                                shape = RoundedCornerShape(20.dp), modifier = Modifier
                                    .padding(horizontal = 20.dp)
                                    .padding(top = 50.dp)
                            ) {
                                Box {
                                    val painter = rememberImagePainter(data = state.photoUrl)
                                    if (painter.state !is ImagePainter.State.Success) {
                                        ShimmerAnimation()
                                    }
                                    Image(
                                        painter = painter,
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(1f)
                                    )
                                }
                            }
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
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(Modifier.padding(bottom = 20.dp)) {
                            Text("Album Title:", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(state.albumTitle, softWrap = true)
                        }
                        Row {
                            Text("Photo Title:", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(state.photoTitle, softWrap = true)
                        }
                    }
                }
            }
            is PhotoDetailScreenState.PhotoDetailScreenLoading -> {
                FullScreenProgressBar()
            }
            else -> ErrorComponent()
        }
    }
}