# ItunesApi
Android application for fetching Itunes search api and display some infomation provided.


1. Architectural Pattern - I used MVVM and Singleton pattern for this project, singleton to make api calls asynchronous and mvvm to display live data 
2. Persistence mechanism, i only used shared prefs to store the last search from api, make response a string and store it on prefs for offline use.
3. The only data persisted is the list on mainactivity because it contains all the list and object from the last search. 
4. Third party library used
    * Glide Imageview - To display and cache images from response. I always use glide for showing image because it is fast.
    * Google material design - this app supports system dark/light mode, it automatically adjust when system changes preference. Using this library dark/light theme made easy
    * Retrofit - To make api calls fast and easy
    * Prefs - To store data offline.

