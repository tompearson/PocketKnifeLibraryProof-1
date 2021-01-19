# PocketKnifeLibraryProof-1
Proof that the PocketKnife modules work
[![Build status](https://build.appcenter.ms/v0.1/apps/726a0c2c-345b-413a-bc8e-7752da1939ce/branches/AppCenterPrep1/badge)](https://appcenter.ms)

[![](https://jitci.com/gh/tompearson/PocketKnifeModule/svg)](https://jitci.com/gh/tompearson/PocketKnifeModule)

dev: [![Build status](https://build.appcenter.ms/v0.1/apps/6913fed6-37ec-4dca-8c4f-a02ec97b0371/branches/dev/badge)](https://appcenter.ms)
[![Build status](https://build.appcenter.ms/v0.1/apps/6913fed6-37ec-4dca-8c4f-a02ec97b0371/branches/test/badge)](https://appcenter.ms)
master: [![Build status](https://build.appcenter.ms/v0.1/apps/6913fed6-37ec-4dca-8c4f-a02ec97b0371/branches/master/badge)](https://appcenter.ms)

Import this module into a Android Project either an existing or new project

Steps to import the module to a new Project

- First in Android Studio create a new project with an Empty Activity

- Next name your project anything you like say, **PocketKnifeApp**

- Then start adding some configuration details into the project level build.gradle

~~~
allprojects {

    repositories {

        google()

        jcenter()

        maven { url "http://jitpack.io/" }  // <-- THIS MUST BE ADDED
    }
}
~~~

In the module level grade.build file add this dependency:

~~~
dependencies {

...

...

 implementation 'com.github.tompearson:PocketKnifeModule1-Kotlin1:master-SNAPSHOT'

}
~~~

In the resources (res) section in Android Studio

![activity_main.xml](./images/Resources_layout.png)

- Edit the Layout named activity_main.xml file and add this line

~~~
<TextView
        android:id="@+id/message"             <-- THIS MUST BE ADDED
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
~~~

- Now build the project. There should be no errors.

- Finally replace the code in Java/MainActivity.kt with the code [found here](https://github.com/tompearson/PocketKnifeApplication/blob/master/PocketKnifeApp1-Kotlin1/src/main/java/com/example/PocketKnifeApplication/MainActivity.kt)

- Build the project insuring there are no errors.

The app is now ready to run in the emulator or a physical Android device

Test your app with WiFi disabled and enabled




# App with WiFi disabled

![WiFi disabled](./images/Screen2.png)

# App with WiFi enabled
