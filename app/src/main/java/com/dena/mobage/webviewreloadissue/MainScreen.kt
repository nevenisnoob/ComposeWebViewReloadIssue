package com.dena.mobage.webviewreloadissue

import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(80.dp),
            ) {
                BottomNavigation(
                    modifier = Modifier
                        .height(72.dp)
                ) {
                    BottomNavigationItem(
                        icon = {
                               Icon(
                                   imageVector = Icons.Filled.Home,
                                   contentDescription = "WebView1"
                               )
                        },
                        selected = true,
                        onClick = {
                            navController.navigate("webView1") {
                                //avoid multiple copies of WebView1
                                launchSingleTop = true
                            }
                        }
                    )
                    BottomNavigationItem(
                        icon = {
                               Icon(
                                   imageVector = Icons.Filled.ShoppingCart,
                                   contentDescription = "WebView2"
                               )
                        },
                        selected = false,
                        onClick = {
                            navController.navigate("webView2") {
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "webView1",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("webView1") { WebView1(viewModel = viewModel) }
            composable("webView2") { WebView2(viewModel = viewModel) }
        }
    }
}

@Composable
fun WebView1(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
){
    GoogleWebView(
        state = rememberWebViewState(url = viewModel.webView1Url),
        onCreated = {
            Log.d("WebView1", "onCreated")
            //bad practice, we shouldn't pass a View to ViewModel(which would lead to a memory leak)
            viewModel.webView1 = it
        },
        onDispose = {
            Log.d("WebView1", "onDispose")
        },
        existingWebView = viewModel.webView1
    )
}


@Composable
fun WebView2(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
) {
    GoogleWebView(
        state = rememberWebViewState(url = viewModel.webView2Url),
        onCreated = {
            Log.d("WebView1", "onCreated")
            it.settings.run {
                javaScriptEnabled = true
                displayZoomControls = false
                builtInZoomControls = true
            }
            viewModel.webView2 = it
        },
        onDispose = {
            Log.d("WebView2", "onDispose")
        },
        existingWebView = viewModel.webView2
    )
}