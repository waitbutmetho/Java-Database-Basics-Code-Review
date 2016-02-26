#Hair Salon App

####Creates a simple app that allows adding of stylists after that, one can add clients to said stylists, February 26, 2016.

####By Joshua Gustafson

##Description

This was created for practice with databases, as well as to be reviewed by a teacher at Epicodus.

##Setup/Installation Requirements

* Clone this repository.
* Make sure you have Gradle and Java installed.
* Start Psql and Postgres
* in PSQL:
* CREATE DATABASE hair_salon;
* Connect to database by using \c command: \c hair_salon;
* CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);
* CREATE TABLE clients (id serial PRIMARY KEY, stylistid int, name varchar);
* In the top level of the cloned directory, run the following command in your terminal: gradle run

* Open your web browser of choice to localhost:4567

##Technologies Used

Java, Spark, Junit, Velocity, Bootstrap, Postgres, Psql

###License
MIT license.
