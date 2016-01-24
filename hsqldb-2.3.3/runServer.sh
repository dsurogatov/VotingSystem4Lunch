#!/bin/bash

java -cp lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:data/votsys4lu --dbname.0 votsys4lu
