package es.sdos.android.dependencies;

public class Dependencies {

    public class AndroidTest {
        public static final String archCoreTest = "androidx.arch.core:core-testing:" + Versions.Test.archCore;
        public static final String core = "androidx.test:core:" + Versions.Test.core;
        public static final String coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:" + Versions.Kotlin.coroutines;
        public static final String databinding = "androidx.databinding:databinding-compiler:" + Versions.Test.databinding;
        public static final String espressoContrib = "androidx.test.espresso:espresso-contrib:" + Versions.Test.espressoCore;
        public static final String espressoCore = "androidx.test.espresso:espresso-core:" + Versions.Test.espressoCore;
        public static final String fragmentNav = "androidx.fragment:fragment-testing:" + Versions.Test.fragment;
        public static final String junit = "androidx.test.ext:junit:" + Versions.Test.junit;
        public static final String koin = "org.koin:koin-test:" + Versions.koin;
        public static final String mockk = "io.mockk:mockk:" + Versions.Test.mockk;
        public static final String mockkAndroid = "io.mockk:mockk-android:" + Versions.Test.mockk;
        public static final String mockWebServer = "com.squareup.okhttp:mockwebserver:" + Versions.Test.mockWebServer;
        public static final String runner = "androidx.test:runner:" + Versions.Test.runner;
    }

    public class Androidx {
        public static final String appcompat = "androidx.appcompat:appcompat:" + Versions.Androidx.appcompat;
        public static final String constraintlayout = "androidx.constraintlayout:constraintlayout:" + Versions.Androidx.constraintlayout;
        public static final String lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:" + Versions.Androidx.lifecycle;
        public static final String livedataKtxExtensions = "androidx.lifecycle:lifecycle-livedata-ktx:" + Versions.Androidx.lifecycle;
        public static final String lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:" + Versions.Androidx.lifecycle;
        public static final String material = "com.google.android.material:material:" + Versions.Androidx.material;
        public static final String navigation = "androidx.navigation:navigation-ui-ktx:" + Versions.Androidx.navigation;
        public static final String navigationFragment = "androidx.navigation:navigation-fragment-ktx:" + Versions.Androidx.navigation;
        public static final String recyclerView = "androidx.recyclerview:recyclerview:" + Versions.Androidx.recyclerview;
        public static final String roomCompiler = "androidx.room:room-compiler:" + Versions.Androidx.room;
        public static final String room = "androidx.room:room-runtime:" + Versions.Androidx.room;
        public static final String roomKtx = "androidx.room:room-ktx:" + Versions.Androidx.room;

    }

    public class Dagger {
        public static final String core = "com.google.dagger:dagger:" + Versions.dagger;
        public static final String coreCompiler = "com.google.dagger:dagger-compiler:" + Versions.dagger;
        public static final String androidCompiler = "com.google.dagger:dagger-android-processor:" + Versions.dagger;
        public static final String androidSupport = "com.google.dagger:dagger-android-support:" + Versions.dagger;
    }


    public class Koin {
        public static final String core = "org.koin:koin-android:" + Versions.koin;
        public static final String viewmodel = "org.koin:koin-androidx-viewmodel:" + Versions.koin;
    }

    public class Kotlin {
        public static final String coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:" + Versions.Kotlin.coroutines;
        public static final String coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:" + Versions.Kotlin.coroutines;
        public static final String ktxCore = "androidx.core:core-ktx:" + Versions.Kotlin.ktx;
        public static final String stdlib = "org.jetbrains.kotlin:kotlin-stdlib:" + Versions.Kotlin.kotlin;
        public static final String stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:" + Versions.Kotlin.kotlin;
    }

    public class OkHttp {
        public static final String core = "com.squareup.okhttp3:okhttp:" + Versions.okHttp;
        public static final String httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:" + Versions.okHttp;
        public static final String okhttpUrlConnection = "com.squareup.okhttp3:okhttp-urlconnection:" + Versions.okHttp;
    }

    public class Retrofit {
        public static final String core = "com.squareup.retrofit2:retrofit:" + Versions.retrofit;
        public static final String gsonConverter = "com.squareup.retrofit2:converter-gson:" + Versions.retrofit;
        public static final String scalarsConverter = "com.squareup.retrofit2:converter-scalars:" + Versions.retrofit;
    }

}