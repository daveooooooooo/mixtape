# Highspot Coding Exercise

## Notes
As stated in the prompt, I didn't spend more than two hours on the exercise. As a result, you will notice that there is no automated testing, and possibly some slight disorganization.

## How to run

Running this project requires [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html). Aside from that, all you need is to run the packaged JAR file included in the repository's root directory, `mixtape.jar`. Example:
```
java -jar mixtape.jar mixtape-data.json changes.json
```

If you have Maven installed, you can also execute it using Maven's `exec` plugin as follows:
```
mvn compile exec:java -Dargs="mixtape-data.json changes.json"
```

## Scaling

This section presumes that the application must run similar to how the assignment suggests. A simple, local application taking in mixtape data as json, and outputting the result as json. When we scale to a very large input size, there are two things we must consider.
1. Mixtapes/changes that cannot fit into RAM
2. Total runtime of the application

### Larger than RAM
To solve the first part, we'd need to come up with an option to store and manipulate data on disk. The code that I've written abstracts the mixtape manipulation into a class called `MixtapeEngine`. We could modify this class to use some kind of persistent storage rather than that storing everything as a `Map` inside the class. We could use a SQLite database for example, and have tables that represent playists, songs, and users. In order to do so, though, we'd need to read the json input as a stream rather than as an entire file. Given the structure, though, it is certainly possible. With this storage system in place, we could read changes in sequetially, and apply them one by one. Then in order to output the final JSON, we'd need to write it out as a stream as well.

### Reducing the total runtime
One of the simplest ways we could improve the runtime is to make changes to the mixtape concurrently. We could spawn some number of threads that would perform operations on the mixtape at the same time, hopefully reducing the overall amount of time it takes to run. Given that the program is likely highly CPU intensive, we may need to rearchitect to see any real gains. One way to do this would be to run a set of servers which perform the operations and returns the result.  Taking this one step further, the application could be server based overall with a shared database, and operations could occur concurrently. This would allow you to do scale horizontally to a huge degree.
