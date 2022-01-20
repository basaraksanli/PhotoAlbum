package com.basaraksanli.photoAlbum.feature_album.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.basaraksanli.photoAlbum.feature_album.presentation.albumlistscreen.AlbumListScreen
import com.basaraksanli.photoAlbum.feature_album.presentation.photodetail.PhotoDetailScreen
import com.basaraksanli.photoAlbum.feature_album.presentation.photolistscreen.PhotoListScreen
import com.basaraksanli.photoAlbum.ui.theme.PhotoAlbumTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoAlbumTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "album_list_screen") {
                    composable("album_list_screen") {
                        AlbumListScreen(navController = navController)
                    }
                    composable("photo_list_screen/{albumId}/{albumTitle}/{userName}",
                        arguments = listOf(
                            navArgument("albumId") {
                                type = NavType.IntType
                            },
                            navArgument("albumTitle") {
                                type = NavType.StringType
                            },
                            navArgument("userName") {
                                type = NavType.StringType
                            }
                        )) {
                        val albumId = remember {
                            it.arguments?.getInt("albumId")
                        }
                        val albumTitle = remember {
                            it.arguments?.getString("albumTitle")
                        }
                        val userName = remember {
                            it.arguments?.getString("userName")
                        }
                        if (albumId != null && albumTitle != null && userName != null) {
                            PhotoListScreen(
                                navController = navController,
                                albumId = albumId,
                                albumTitle = albumTitle,
                                userName = userName
                            )
                        }
                    }
                    composable("photo_detail_screen/{albumTitle}/{photoTitle}/{photoUrl}",
                        arguments = listOf(
                            navArgument("albumTitle") {
                                type = NavType.StringType
                            },
                            navArgument("photoTitle") {
                                type = NavType.StringType
                            },
                            navArgument("photoUrl") {
                                type = NavType.StringType
                            }
                        )) {
                        val albumTitle = remember {
                            it.arguments?.getString("albumTitle")
                        }
                        val photoTitle = remember {
                            it.arguments?.getString("photoTitle")
                        }
                        val photoUrl = remember {
                            it.arguments?.getString("photoUrl")
                        }
                        if (albumTitle != null && photoTitle != null && photoUrl != null) {
                            PhotoDetailScreen(
                                navController = navController,
                                albumTitle = albumTitle,
                                photoTitle = photoTitle,
                                url = photoUrl
                            )
                        }
                    }
                }
            }
        }
    }
}

