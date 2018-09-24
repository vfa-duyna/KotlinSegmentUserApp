# 【Android】ユーザー情報を更新してみよう！
*2016/12/7作成*

![画像01](/readme-img/001.png)

## 概要
* [ニフクラ mobile backend](https://mbaas.nifcloud.com/)の『会員管理機能』を利用してAndroidアプリにログイン機能を実装し、ユーザー情報を更新するサンプルプロジェクトです
* 簡単な操作ですぐに [ニフクラ mobile backend](https://mbaas.nifcloud.com/)の機能を体験いただけます★☆

## ニフクラ mobile backendって何？？
スマートフォンアプリのバックエンド機能（プッシュ通知・データストア・会員管理・ファイルストア・SNS連携・位置情報検索・スクリプト）が**開発不要**、しかも基本**無料**(注1)で使えるクラウドサービス！

注1：詳しくは[こちら](https://mbaas.nifcloud.com/price.htm)をご覧ください

![画像02](/readme-img/002.png)

# 準備

* Android Studio
* mBaaSの[アカウント作成](https://mbaas.nifcloud.com/signup.htm)

## 動作環境
* Windows 7 Professional
* Android Studio 3.1
* Android ver 4x以上
* Android SDK v3
  - SDK v2系だと動作しないので注意

※上記内容で動作確認をしています

## 作業の手順
### 1. [ニフクラ mobile backend](https://mbaas.nifcloud.com/)の会員登録とログイン

* 上記リンクから会員登録（無料）をします。登録ができたらログインをすると下図のように「アプリの新規作成」画面が出るのでアプリを作成します

![画像03](/readme-img/003.png)

* アプリ作成されると下図のような画面になります
* この２種類のAPIキー（アプリケーションキーとクライアントキー）はAndroidアプリに[ニフクラ mobile backend](https://mbaas.nifcloud.com/)を紐付けるために使用します

![画像04](/readme-img/004.png)

* 動作確認後に会員情報が保存される場所も確認しておきましょう

![画像05](/readme-img/005.png)

### 2. [GitHub](https://github.com/NIFCloud-mbaas/KotlinSegmentUserApp)からサンプルプロジェクトのダウンロード

* 下記リンクをクリックしてプロジェクトをローカルにダウンロードします

 * __[KotlinSegmentUserApp](https://github.com/NIFCloud-mbaas/KotlinSegmentUserApp/archive/master.zip)__

### 3. AndroidStudioでアプリを起動

* AndroidStudioを開き「Open File or Project」からダウンロードしたプロジェクトのフォルダ(KotlinSegmentUserApp)を選択します

![画像06](/readme-img/006.png)

### 4. APIキーの設定

* `MainActivity.kt`を編集します
* 先程[ニフクラ mobile backend](https://mbaas.nifcloud.com/)のダッシュボード上で確認したAPIキーを貼り付けます

![画像07](/readme-img/007.png)

* それぞれ`YOUR_APPLICATION_KEY`と`YOUR_CLIENT_KEY`の部分を書き換えます
 * このとき、ダブルクォーテーション（`"`）を消さないように注意してください！
* 書き換え終わったら保存してください
 * Windowsの場合、Ctrl + Sで保存できます。
 * Macの場合、command + Sで保存できます。

* AndroidStudioからビルドする。
 * 「プロジェクト場所」\app\build\outputs\apk\ ***.apk ファイルが生成される

### 5.動作確認

* Android Studioから、適当なSimulatorを選択します
* アプリが起動したら、Login画面が表示されます
* 初回は__`No account yet? Create one`__ リンクをクリックして、新規会員登録を行います

![画像13](/readme-img/008.png)

* `User Name`と`Password`を２つ入力して「CREATE ACCOUNT」ボタンをタップします
* 会員登録が成功するとログインされ、下記ユーザー情報の一覧が表示されます
* SignUpに成功するとmBaaS上に会員情報が作成されます！

![画像14](/readme-img/009.png)

* ログインに失敗した場合はアラートでログイン失敗を表示します

#### 新しいフィールドの追加
* 新しいフィールドの追加をしてみましょう。"favorite"というフィールドを作り、中身には"music"と入れてみました。こうすることで、ユーザー情報に新しい属性を付与することができるようになります！
* 編集が完了したら更新ボタンをタップして下さい

![画像15](/readme-img/010.png)

* 更新後、リストビューが自動でリロードされ、追加・更新が行われていることがわかります
* 追加したフィールドは後から編集することが可能です

![画像16](/readme-img/011.png)

* ダッシュボードから、更新ができていることを確認してみましょう！

![画像17](/readme-img/012.png)

* ActionBar右上側メニューから「Logout」を押すとログアウトが出来ます

![画像17](/readme-img/013.png)

## 解説
* 下記３点について解説します
 * 会員登録
 * ログイン
 * 会員情報の取得

### 会員登録
`SignupActivity.kt`

```kotlin
//NCMBUserのインスタンスを作成
val user = NCMBUser()
//ユーザ名を設定
user.userName = name
//パスワードを設定
user.setPassword(password)
//設定したユーザ名とパスワードで会員登録を行う
user.signUpInBackground { e ->
    if (e != null) {
        // 新規登録失敗時の処理
    } else {
        // 新規登録成功時の処理
    }
    }
});
```

### ログイン
`LoginActivity.kt`

```kotlin
NCMBUser.loginInBackground(name, password) { user, e ->
    if (e != null) {
        // ログイン失敗時の処理
    } else {
        // ログイン成功時の処理
    }
}
```

### 会員情報の取得
`MainActivity.kt`

```kotlin
// カレントユーザ情報の取得
val userInfo = NCMBUser.getCurrentUser()

userInfo.fetchInBackground { userObject, e ->
    if (e != null) {
        // ユーザー情報の取得が失敗した場合の処理
    } else {
        // ユーザー情報の取得が成功した場合の処理
    }
}
```

# 参考

サンプルコードをカスタマイズすることで、様々な機能を実装できます！
データ保存・データ検索・会員管理・プッシュ通知などの機能を実装したい場合には、
以下のドキュメントもご参考ください。

* [ドキュメント](https://mbaas.nifcloud.com/doc/current/)
* [ドキュメント・データストア](https://mbaas.nifcloud.com/doc/current/datastore/basic_usage_android.html)
* [ドキュメント・会員管理](https://mbaas.nifcloud.com/doc/current/user/basic_usage_android.html)
* [ドキュメント・プッシュ通知](https://mbaas.nifcloud.com/doc/current/push/basic_usage_android.html)
