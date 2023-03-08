package com.kaiarcz.repocommitstats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kaiarcz.repocommitstats.app.theme.RepoCommitStatsTheme
import com.kaiarcz.repocommitstats.presentation.repos.ReposScreen
import com.kaiarcz.repocommitstats.presentation.stats.CommitStatsViewModel
import com.kaiarcz.repocommitstats.presentation.stats.CommitStatsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepoCommitStatsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ReposScreen.route
                    ) {
                        composable(
                            route = Screen.ReposScreen.route
                        ) {
                            ReposScreen(navController)
                        }
                        composable(
                            route = Screen.RepoCommitStatsScreen.route + "/{repo}",
                            arguments = listOf(
                                navArgument("repo") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val viewModel = hiltViewModel<CommitStatsViewModel>()
                            CommitStatsScreen(viewModel)
                        }
                    }
                }
            }
        }
    }
}
