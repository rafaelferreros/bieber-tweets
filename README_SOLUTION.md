```
*************************************************************
*                                                           *
*       ________  __    __  ________    ____       ______   *
*      /_/_/_/_/ /_/   /_/ /_/_/_/_/  _/_/_/_   __/_/_/_/   *
*     /_/_____  /_/___/_/    /_/    /_/___/_/  /_/          *
*    /_/_/_/_/   /_/_/_/    /_/    /_/_/_/_/  /_/           *
*   ______/_/       /_/    /_/    /_/   /_/  /_/____        *
*  /_/_/_/_/       /_/    /_/    /_/   /_/    /_/_/_/ . io  *
*                                                           *
*************************************************************
```

# Sytac Java Exercise #

To run the application:
`mvn spring-boot:run`

## Configuration ##

In the file `application.properties` will be found the following properties:
+ `consumerKey=RLSrphihyR4G2UxvA0XBkLAdl`
+ `consumerSecret=FTz2KcP1y3pcLw0XXMX5Jy3GTobqUweITIFy4QefullmpPnKm4`
+ `trackValue=bieber`
+ `maxMessages=100`
+ `timeoutSeconds=30`
+ `outputDir=target/`

## Output ##
The output will be saved as a json file in the path assigned to the property `outputDir`
By default, the json file is saved in the project's target folder.
using the name `{currentDateTime}.json`.