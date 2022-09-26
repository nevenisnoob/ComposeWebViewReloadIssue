package com.dena.mobage.webviewreloadissue

import android.webkit.WebView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel :ViewModel() {
    val webView1Url by mutableStateOf("https://sp.mbga.jp/_t")
    val webView2Url by mutableStateOf("https://github.com/")

    var webView1 by mutableStateOf<WebView?>(null)
    var webView2 by mutableStateOf<WebView?>(null)
}