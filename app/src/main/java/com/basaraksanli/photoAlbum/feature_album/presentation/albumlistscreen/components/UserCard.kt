package com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basaraksanli.photoAlbum.feature_album.domain.model.UserListItem

@Composable
fun UserCard(user: UserListItem) {
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
                Text("Name:", fontWeight = FontWeight.Bold)
                Text("Email:", fontWeight = FontWeight.Bold)
                Text("Username:", fontWeight = FontWeight.Bold)
                Text("Website:", fontWeight = FontWeight.Bold)
                Text("Company:", fontWeight = FontWeight.Bold)
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