# これは何？

(コンパイル時でなく)起動後にロードするJarを決定して実行するサンプル。

単純なjarファイルをクラスパスに追加するのはSDK提供機能が使えるので[比較的簡単](http://stackoverflow.com/a/21931044/4506703)であるが、Jar-in-Jarをロードする場合には自作が必要になる。

既存実装がいくつかありそれらが利用できると思われるが、このサンプルではEclipseの[jarinjarloader](http://git.eclipse.org/c/jdt/eclipse.jdt.ui.git/plain/org.eclipse.jdt.ui/jar%20in%20jar%20loader/org/eclipse/jdt/internal/jarinjarloader/)を参考にしている。

その他の既存実装は次のリンクを参照:

- [実行可能jarに外部jarを含めるためのライブラリ調査 - iWA’s 雑記@はてな出張所](http://d.hatena.ne.jp/vmi/20100812/p1)

# 説明

`so31611-main`がmainメソッドを含む本体モジュールである。
`Launcher`クラスがEclipseで言うところの[`JarRsrcLoader`](http://git.eclipse.org/c/jdt/eclipse.jdt.ui.git/plain/org.eclipse.jdt.ui/jar%20in%20jar%20loader/org/eclipse/jdt/internal/jarinjarloader/JarRsrcLoader.java)に相当する。

`so31611-module-develop`及び`so31611-module-production`がロード選択可能な`jar`を生成するモジュールである。

# コンパイル & 実行方法

    mvn clean package
    java -DloadJar=[ロードするjar名] -jar so31611-dist/target/so31611-dist-0.1.0-SNAPSHOT-both-included.jar

**[ロードするjar名]は、`so31611-module-develop-0.1.0-SNAPSHOT.jar`または`so31611-module-production-0.1.0-SNAPSHOT.jar`** を指定する。
これらは双方 `so31611-dist-0.1.0-SNAPSHOT-both-included.jar` の中に含まれる`jar`である。

