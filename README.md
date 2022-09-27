# 画面構成
MainScreenにBottomBarがあり、BottomBarより、二つのWebView画面に遷移することができます。

<img src="https://user-images.githubusercontent.com/50619510/192420862-81d2e94e-10f3-4c71-8fb1-a3cca8af2f8c.png" width="400px">

# 問題
NavigaitonよりWebView1からWebView2に遷移し、再度WebView1に戻すと、WebViewがreloadされてしまいます。

# 問題調査
WebViewのreload問題は、[こちら](https://github.com/google/accompanist/issues/1178)で議論されてますが、今だに綺麗な解決方法がないという。

ただ、workaroundはあるらしく、
1. 生成されたWebViewを　ViewModelに保持する方法
2. 各Naviationのrouteに webvewのinstanceをcacheする方法

(どちらもMemoryLeakしそうなのでおすすめはしない)
