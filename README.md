# 画面構成
MainScreenにBottomBarがあり、BottomBarより、二つのWebView画面に遷移することができます。

ちなみに、利用するWebViewは [GoogleのWebViewWrapper](https://github.com/google/accompanist/tree/main/web)です。

<img src="https://user-images.githubusercontent.com/50619510/192420862-81d2e94e-10f3-4c71-8fb1-a3cca8af2f8c.png" width="400px">

# 問題
NavigaitonよりWebView1からWebView2に遷移し、再度WebView1に戻すと、WebViewがreloadされてしまいます。

# 問題調査
WebViewのreload問題は、[こちら](https://github.com/google/accompanist/issues/1178)で議論されてますが、今だに綺麗な解決方法がないらしい。

ただ、workaroundはあるらしく（どちらもmemory leak発生しそうで実際お勧めしないが）、
1. 生成されたWebViewを　ViewModelに保持する方法
2. 各Naviationのrouteに webvewのinstanceをcacheする方法

1 についての解決方法はこちら：
https://github.com/nevenisnoob/ComposeWebViewReloadIssue/commit/a03c17928fc746859dfa6be014d9ff169babb91e
ただこの方法だとreload問題回避できたが、webviewのリンクは押せなくなってしまったので、引き続き調査する必要がある。

2　については、原理的に1と似たようなものだと思いますので、一旦　1　の方法で引き続き調査する


