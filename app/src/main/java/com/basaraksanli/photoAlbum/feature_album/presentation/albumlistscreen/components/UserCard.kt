package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basaraksanli.photoAlbum.R
import com.basaraksanli.photoAlbum.feature_album.domain.model.User

@Composable
fun UserCard(user: User) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.user),
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
                Text(stringResource(R.string.name), fontWeight = FontWeight.Bold)
                Text(stringResource(R.string.email), fontWeight = FontWeight.Bold)
                Text(stringResource(R.string.username), fontWeight = FontWeight.Bold)
                Text(stringResource(R.string.website), fontWeight = FontWeight.Bold)
                Text(stringResource(R.string.company), fontWeight = FontWeight.Bold)
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
                Text(text = user.companyName, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }

}