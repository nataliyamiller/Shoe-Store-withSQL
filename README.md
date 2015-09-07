# Shoe Stores with PostgreSQL database

####_by Nataliya Bareeva-Miller_

#####Object-Oriented Java with SQL, 4th Week Code Review, Epicodus 05 September 2015.


### Description

This application allows user to add new stores and brands to the list. After adding a new store, user can go to that store's page and add brands to that store. And vice versa, after adding a new brand, user can go to that brand's page and add stores to that brand. User can also update each store or brand information, or delete that store or brand if it's no longer needed. 


## Setup

* Please have all Java developer tools ready, including the JDK
* You'll need Gradle or some other way to run and compile Java
* This app uses Spark and Velocity for coordinating its front end
* You may use the database included with this file. Otherwise, create your own using the following information:

* _In PSQL:_
###### ``CREATE DATABASE shoe_stores;``
###### ``CREATE TABLE stores (id serial PRIMARY KEY, name varchar, address varchar, phone_number varchar);``
###### ``CREATE TABLE brands (id serial PRIMARY KEY, brand_name varchar, style varchar, type varchar, color varchar);``
###### ``CREATE TABLE stores_brands (id serial PRIMARY KEY, store_id int, brand_id int);``

* _In command line:_
``psql shoe_stores < stores.sql``

* _Clone this repository and run with Gradle (type ``gradle run`` in your command line). Then open localhost:4567 in your browser._

*Here is the database schema in WWW SQL Designer:

![ScreenShot](https://cloud.githubusercontent.com/assets/10698013/9706092/3b8d295c-548f-11e5-8612-1912c2a5d874.png)


## Tests

_There are both unit and integration tests associated with this application. The test files can be found in the /src/test/java folder._
* _To run the tests:_
 In PSQL -> ``CREATE DATABASE shoe_stores_test WITH TEMPLATE shoe_stores_test;`` then type
``gradle test`` in your command line.


* _Java version 8 update 45_
* _Velocity version 1.7_
* _Spark version 2.1_
* _Bootstrap version 3.2.0_
* _JUnit version 4.+_
* _FluentLenium version 0.10.3_
* _Gradle_
* _PostgreSQL_
* _Sql2o version 1.5.4_

### Legal

Copyright (c) 2015 **Nataliya Bareeva-Miller**

## Copyright (c) 2015 Copyright Holder All Rights Reserved.
[MIT License 2015] https://github.com/nataliyamiller/basic-template-for-java-projects/blob/master/LICENSE.md
