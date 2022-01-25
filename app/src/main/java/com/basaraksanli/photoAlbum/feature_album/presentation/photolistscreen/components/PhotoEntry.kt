package com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.basaraksanli.photoAlbum.R
import com.basaraksanli.photoAlbum.core.presentation.components.ShimmerAnimation
import com.basaraksanli.photoAlbum.feature_album.domain.model.Photo
import com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen.PhotoListViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun PhotoEntry(
    entry: Photo,
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
                Box(Modifier.clickable {
                    navController.navigate("photo_detail_screen/${viewModel.albumTitle}/${entry.title}/${encodedUrl}")
                }) {
                    val painter = rememberImagePainter(data = entry.thumbnailUrl, builder = {
                        crossfade(true)
                    })
                    if (painter.state !is ImagePainter.State.Success)
                        ShimmerAnimation()
                    Image(
                        painter = painter,
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
                text = stringResource(R.string.title) + entry.title,
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