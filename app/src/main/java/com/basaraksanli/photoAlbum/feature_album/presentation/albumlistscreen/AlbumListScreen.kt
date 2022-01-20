package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.basaraksanli.photoAlbum.feature_album.domain.model.AlbumListItem
import com.basaraksanli.photoAlbum.feature_album.domain.model.UserListItem
import com.basaraksanli.photoAlbum.feature_album.util.AppSizes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.math.MathUtils.lerp
import kotlin.math.absoluteValue


@ExperimentalPagerApi
@Composable
fun AlbumListScreen(navController: NavController, viewModel: AlbumListViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        if (!state.isAlbumsLoading && !state.isUsersLoading)
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
                        UserList(navController = navController)
                    }
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
}

@ExperimentalPagerApi
@Composable
fun UserList(navController: NavController, viewModel: AlbumListViewModel = hiltViewModel()) {
    val state = viewModel.state.value

    HorizontalPager(
        count = state.userList.size, Modifier.fillMaxWidth()
    ) { page ->

        Column {
            Row {
                if (page != 0) Icon(
                    Icons.Default.KeyboardArrowLeft, contentDescription = "",
                    Modifier
                        .size(20.dp)
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
                )
                else Spacer(Modifier.width(20.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 5.dp)
                        .height(AppSizes.USER_CARD_SIZE.dp / 10 * 9)
                        .clip(RoundedCornerShape(20.dp))
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                            lerp(
                                0.65f,
                                1f,
                                1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }

                            alpha = lerp(
                                0.5f,
                                1f,
                                1f - pageOffset.coerceIn(0f, 1f)
                            )
                        },
                ) {
                    Column {
                        UserEntry(
                            user = state.userList[page],
                        )
                    }
                }
                if (page != state.userList.size - 1) Icon(
                    Icons.Default.KeyboardArrowRight, contentDescription = "",
                    Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically)
                )
                else Spacer(Modifier.width(20.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))
            AlbumList(
                navController = navController,
                userId = state.userList[page].id,
                userName = state.userList[page].name
            )
        }

    }
}

@Composable
fun UserEntry(user: UserListItem) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "User",
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            textAlign = TextAlign.Center
        )
        Row {
            Column(
                Modifier
                    .width(100.dp)
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Name:", fontWeight = FontWeight.Bold,)
                Text("Email:", fontWeight = FontWeight.Bold,)
                Text("Username:", fontWeight = FontWeight.Bold,)
                Text("Website:", fontWeight = FontWeight.Bold,)
                Text("Company:", fontWeight = FontWeight.Bold,)
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
            ) {
                Text(text = user.name, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = user.email, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = user.username, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = user.website, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = user.company.name, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }

}

@Composable
fun AlbumList(
    navController: NavController,
    userName: String,
    userId: Int,
    viewModel: AlbumListViewModel = hiltViewModel(),
) {
    val state= viewModel.state.value

    val albumItemPairs = arrayListOf<Pair<AlbumListItem, AlbumListItem?>>()


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
            text = "Albums",
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

@Composable
fun AlbumEntry(
    entry: AlbumListItem,
    userName: String,
    navController: NavController,
    imageId: Int
) {
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
            Box(Modifier.clickable {
                navController.navigate("photo_list_screen/${entry.id}/${entry.title}/${userName}")
            }) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxHeight()
                )
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