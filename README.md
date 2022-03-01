First of all run zookeeper and kafka services

```$ docker-compose up```

Creating an entity:

With a POST to ```http://localhost:8081/producer/topic/test2/body```

and body

```
{
    "name": "Toyota",
    "model": "Corolla",
    "color": "blue",
    "price": 80000.99
}
```

kafkaPublisher will publish the object to a topic

kafkaConsumer will read messages of topic and will save the object in Mongo Database.