# KafkaSqlCDC
This is a Project use to consume Kafka emit events in a similar way of CDC (Change Data Capture), while doing CUD to associated DataBase.
Current Support DataBase consist:
  - Mssql
 
 
This Project require three config files to run:
  - Kafka Consumer Config (-k)
  - DataBase Config (-c)
  - Table Schema Config (Table name, and initate query to get table information) (-t)

To Complile code into Graalvm native image:
```
mvn package -e -X
```
OR
```
mvn package
```

To run kafka cluster:
```
docker-compose up
```

To run DataBase:
```
sh run_mssql.sh
```

To run DataBase:
```
sh run_mssql.sh
```

To run native image:
```
roll -t "/location/to/Table Schema Config.yaml" -k "/location/to/Kafka Consumer.yaml" -c "/location/to/Data Base.yaml"
```
