package com.basaraksanli.photoAlbum.ui.photolistscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.basaraksanli.photoAlbum.data.remote.responses.PhotoList
import com.basaraksanli.photoAlbum.data.remote.responses.PhotoListItem
import com.basaraksanli.photoAlbum.util.ApiResult
import com.basaraksanli.photoAlbum.util.AppSizes
import com.basaraksanli.photoAlbum.util.ShimmerAnimation
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun PhotoListScreen(
    navController: NavController,
    albumId: Int,
    albumTitle: String,
    userName: String,
    viewModel: PhotoListViewModel = hiltViewModel()
) {
    val photoList = produceState<ApiResult<PhotoList>>(initialValue = ApiResult.Loading()) {
        value = viewModel.loadPhotoList(albumId)
        value.data?.let { viewModel.setPhotoList(it) }
    }.value

    if (photoList is ApiResult.Success)
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
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
                        AlbumInfoCard(userName, albumTitle)
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
                PhotoList(navController = navController)
            }
        }
    else
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
}

@Composable
fun AlbumInfoCard(userName: String, albumTitle: String) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Album Info",
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column(
                Modifier
                    .width(110.dp)
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("User Name:", maxLines = 1, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text("Album Title:", maxLines = 1, fontWeight = FontWeight.Bold)
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
            ) {
                Text(text = userName, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = albumTitle, overflow = TextOverflow.Ellipsis)
            }
        }
    }

}

@Composable
fun PhotoList(
    navController: NavController,
    viewModel: PhotoListViewModel = hiltViewModel(),
) {
    val photoList by remember { viewModel.photoList }

    val photoItemPairs = arrayListOf<Pair<PhotoListItem, PhotoListItem?>>()

    for (i: Int in photoList.indices) {
        if (i % 2 == 0) {
            val pair = if (i + 1 < photoList.size)
                Pair(photoList[i], photoList[i + 1])
            else
                Pair(photoList[i], null)
            photoItemPairs.add(pair)
        }
    }

    Column {
        Text(
            text = "Albums",
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

@Composable
fun PhotoEntry(
    entry: PhotoListItem,
    navController: NavController,
    viewModel: PhotoListViewModel = hiltViewModel()
) {
    val encodedUrl = URLEncoder.encode(entry.url, StandardCharsets.UTF_8.toString())

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color.Transparent,
            modifier = Modifier
                .size(155.dp)
        ) {
            Box {
                Box (Modifier.clickable{
                    navController.navigate("photo_detail_screen/${viewModel.albumTitle}/${entry.title}/${encodedUrl}")
                }){
                    ShimmerAnimation()
                    Image(
                        painter = rememberImagePainter(
                            data = entry.thumbnailUrl,
                            builder = {
                                crossfade(true)
                            }
                        ),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    tint = Color.White,
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .align(Alignment.CenterEnd),
                )
            }
        }
        Box(
            Modifier
                .width(150.dp)
                .height(40.dp), contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "Title: " + entry.title,
                fontSize = 12.sp,
                softWrap = true,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 2,
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
    }

}