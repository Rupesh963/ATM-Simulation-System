#!/bin/bash
javac -cp ".:lib/jcalendar-1.4.jar:lib/mysql-connector-j-8.4.0.jar" -d out *.java
java -cp "out:lib/jcalendar-1.4.jar:lib/mysql-connector-j-8.4.0.jar" bank.management.system.Login
