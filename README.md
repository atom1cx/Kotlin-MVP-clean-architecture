# Kotlin-MVP-clean-architecture
Kotlin MVP(VIPER) clean architecture example/boilerplane using RxJava, Moxy, Jetpack Navigation, retrofit and room

<img src="https://habrastorage.org/getpro/habr/post_images/6ec/b19/1c3/6ecb191c3ea7a1136b1c9ace02a0e73f.png"/>

This application is a kind of boilerplate for VIPER or MVP project

Fully reactive with Room, it uses custom navigation, powered by Jetpack Navigation component.
Based on https://openweathermap.org/ API, this application is a kind of analogue of the platform weather application
The application allows you to add custom city by name and fetch some weather data, like current weather or 3h interval forecast for a three days
Application fetches data from API when network is awailable and displays it when db updated
