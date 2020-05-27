#!/bin/bash
sudo mvn clean install
sudo mvn exec:java -Dexec.mainClass=com.example.biobot.BioBotApplication
