# Android Medior - App  
  
This is source code intended to be used with Skillmea course named Android Medior Programmer (not yet released). It already has pretty neat design for whole authentication flow, so you just need to:
1. implement push notifications
2. implement various Firebase Tools
3. implement sign in and login with Google
4. implement sign in and login with Facebook
5. implement deeplinks
6. and others

In order to test push notifications You will need running and properly configured server. Take a look at my implementation of such [server in Ktor framework](https://github.com/VizGhar/skillmea_medior_android_server).

## Implementations

1. `main` branch is **your entry point** with implemented design of all necessary screens and email/password login against server running on localhost.
2. `onboarding` branch: horizontal pager with images typical for onboarding screens and custom page indication (design feature)
3. `splashscreen` branch - typical splashcreen implementation (design feature)
4. `google` branch - Google Login implementation
5. `facebook` branch - Facebook Login implementation
