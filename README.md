# Mobile-Development
The MD part is responsible to make the functional android app. Healthy Living app will have 4 main menus but with only three of which is selectable. Those menus are Progress(TBA), Jelajahi, Resep, and Profile.

# User Flow

<img width="281" alt="image" src="https://github.com/helencodejpg/Bangkit-Capstone-Project-Healthy-Living/assets/128057892/48884c42-a6b2-4f2f-836c-5f18a81f4909">

# Resources

Here are all the resources that i use in our app. For assets that I didn't make myself, below i attached the links where i got it.

<img width="319" alt="image" src="https://github.com/helencodejpg/Bangkit-Capstone-Project-Healthy-Living/assets/128057892/850ac560-f3c1-4b5b-8ee1-3859c63e227f">

- https://westmedical.com/bmi-calculator/

# User Experience

To ensure that users get the best experience while using our app, i designed the user flow and the use case.

For more details, click this [figma](https://www.figma.com/file/QoqQIVlUh1JY4CjDBQ0Msy/Healthy-Living-App?type=design&node-id=134%3A163&t=snFyFAohLqxXzVVg-1) link.

# Application Development
In the application development part, i used Android Studio as the IDE and wrote all the codes in kotlin language.

## Depedencies
Here are all the dependencies i use in Healthy Living application:

DataStore
- implementation "androidx.datastore:datastore-preferences:1.0.0"

Room Database
- implementation 'androidx.room:room-runtime:2.5.1'
- implementation 'androidx.room:room-paging:2.6.0-alpha01'
- implementation 'androidx.room:room-common:2.5.1'
- implementation "androidx.room:room-ktx:2.5.1"
- implementation 'androidx.paging:paging-runtime-ktx:3.1.1'
- kapt 'androidx.room:room-compiler:2.5.1'

Coroutine
- implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
- implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"

Networking
- implementation 'com.squareup.retrofit2:retrofit:2.9.0'
- implementation "com.squareup.retrofit2:converter-gson:2.9.0"
- implementation "com.squareup.okhttp3:logging-interceptor:4.9.0"
- implementation 'com.loopj.android:android-async-http:1.4.9'

Image
- implementation 'de.hdodenhof:circleimageview:3.1.0'
- implementation 'com.github.bumptech.glide:glide:4.15.0'

Android Component
- implementation 'androidx.recyclerview:recyclerview:1.3.0'
- implementation 'androidx.cardview:cardview:1.0.0'
- implementation 'androidx.core:core-ktx:1.10.1'
- implementation 'androidx.appcompat:appcompat:1.6.1'
- implementation 'com.google.android.material:material:1.9.0'
- implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
- implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.1'
- implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1'
- implementation 'androidx.navigation:navigation-fragment-ktx:2.6.0'
- implementation 'androidx.navigation:navigation-ui-ktx:2.6.0'
- implementation 'androidx.annotation:annotation:1.6.0'

Testing
- testImplementation 'org.mockito:mockito-core:3.12.4'
- testImplementation 'org.mockito:mockito-inline:3.12.4'
- testImplementation "androidx.arch.core:core-testing:2.2.0"
- testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"
- testImplementation 'junit:junit:4.13.2'
- androidTestImplementation 'androidx.test.ext:junit:1.1.5'
- androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
