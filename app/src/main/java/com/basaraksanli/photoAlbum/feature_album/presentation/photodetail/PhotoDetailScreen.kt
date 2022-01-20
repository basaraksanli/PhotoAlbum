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
import coil.compose.rememberImagePainter
import com.basaraksanli.photoAlbum.util.ShimmerAnimation

@Composable
fun PhotoDetailScreen(
    navController: NavController,
    albumTitle: String,
    photoTitle: String,
    url: String,
    viewModel: PhotoDetailViewModel = hiltViewModel()
) {
    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column() {
            Box() {
                Column() {
                    Card(shape = RoundedCornerShape(20.dp), modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 50.dp)) {
                        Box() {
                            ShimmerAnimation(
                                Modifier
                                    .fillMaxWidth()
                            )
                            Image(
                                painter = rememberImagePainter(
                                    data = url,
                                    builder = {
                                        crossfade(true)
                                    }
                                ),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
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
                    Text(viewModel.albumTitle!!, softWrap = true)
                }
                Row() {
                    Text("Photo Title:", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(viewModel.photoTitle!! , softWrap = true)
                }
            }
        }
    }
}