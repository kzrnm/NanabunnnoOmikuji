## ビルド方法

### アイコン

`app/src/main/res` 以下にアイコンを置く。

- mipmap-hdpi/ic_launcher_round.png
- mipmap-hdpi/ic_launcher.png
- mipmap-ldpi/ic_launcher_round.png
- mipmap-ldpi/ic_launcher.png
- mipmap-mdpi/ic_launcher_round.png
- mipmap-mdpi/ic_launcher.png
- mipmap-xhdpi/ic_launcher_round.png
- mipmap-xhdpi/ic_launcher.png
- mipmap-xxhdpi/ic_launcher_round.png
- mipmap-xxhdpi/ic_launcher.png
- mipmap-xxxhdpi/ic_launcher_round.png
- mipmap-xxxhdpi/ic_launcher.png

があれば十分。

xml で描画しても良い。

### Twitter API

`local.properties` の

- consumer.key
- consumer.secret

に Twitter API の設定を追加