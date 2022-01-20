package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen.AlbumListState
import com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen.AlbumListViewModel
import com.basaraksanli.photoAlbum.feature_album.util.AppSizes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.math.MathUtils
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun UserListWidget(navController: NavController, viewModel: AlbumListViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    if (state is AlbumListState.AlbumListScreenLoaded)
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

                                MathUtils.lerp(
                                    0.65f,
                                    1f,
                                    1f - pageOffset.coerceIn(0f, 1f)
                                ).also { scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }

                                alpha = MathUtils.lerp(
                                    0.5f,
                                    1f,
                                    1f - pageOffset.coerceIn(0f, 1f)
                                )
                            },
                    ) {
                        Column {
                            UserCard(
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
                AlbumListWidget(
                    navController = navController,
                    userId = state.userList[page].id,
                    userName = state.userList[page].name
                )
            }

        }
}