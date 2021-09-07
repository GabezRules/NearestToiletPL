# NearestToiletPL

The app purpose is to help people find restrooms in their area, not only the public toilets listed in Google Maps, but also in places like churches or pharmacies. 

Used technologies and approaches:
* MVVM
* LiveData
* Retrofit
* Hilt
* Google Maps with custom styling
* Room
* Android Jetpack Navigation
* Coroutines

TODO:
* resolve accessibility issues
* fix language version bugs
* save restrooms in remote database with Firestore
* save restrooms in local database with Room and update from remote every X hours
* create an exact search algorithm (current one is not perfect)
* add rating and features (perks) for the restrooms, including: has a changing table, is free, has facilities for people with disabilities, is public or in a restaurant/church etc
* show a path to the restroom
* allow users to search toilets only with specific perks
* collect testing data about restrooms from bigger cities in Poland

About architecture: 
* In current architecture there is just MVVM approach with one connection to remote datasource via data_access module. In the near future, there will be two datasources, local and remote Firestore database. Each one will have its own individual module. The data_access module is responsible for all data transactions, so the main app module doesn't have to know, which datasource is currently using. 
