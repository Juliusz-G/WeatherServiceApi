# Weather service - API

A brief description of what this project does.

As part of the project, a system is created to collect data from one or preferably two/three weather services (or use files) and provide the average (the result should be a given weather value calculated as the arithmetic average of the results returned by each service) values calculated on the basis of the responses returned.


## Features

- Adding a location
- Display of available locations
- Downloading weather data from https://openweathermap.org/api
- Data storage in H2 database


## Roadmap

- Edit location

  As part of the new menu option, the user should be able to edit the currently added locations. Validation should also be included in the edit.
- Location search

  The user should be able to display information about a specific location by searching for it, e.g. by name.
- Statistic data

  The user should be able to display statistical data on selected weather values ​​from a specific time period, e.g. month, year. Data for this purpose should be downloaded directly from the file.
- Data write/read

  The user should be able to save the currently collected data to a file in any format, and then restore them by saving directly to the file.



## Tech Stack

- JAVA
- Console application
- Unit testing tools
- JSON data serialization/deserialization tool, eg Gson, Jackson
- Hibernate
- H2 DB


## Authors
[@juliuszgorzen](https://github.com/Juliusz-G)

[@pawelbarwinski](https://github.com/PawelB-93)