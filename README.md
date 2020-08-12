# lawnmower
Algorithm to mower rectangular surfaces.

## Build

Requirements
* Maven 3+
* JDK 11

To compile, run tests and build executable Jar:
````shell script
mvn clean verify
````

## How to run
You first have to clone this repository and then build the tool:
```
mvn clean verify
```
Once built, you can launch it:

```
java -jar target/lawnmower.jar YOUR_INPUT_FILE_CANONICAL_PATH 
```

### input file format
The mowers are programmed using an input file constructed in the following manner:
The first line corresponds to the upper right corner of the lawn. The bottom left corner is
implicitly (0, 0).

The rest of the file describes the multiple mowers that are on the lawn. Each mower is described
on two lines:

The first line contains the mower's starting position and orientation in the format "X Y O". X and
Y are the coordinates and O is the orientation.

The second line contains the instructions for the mower to navigate the lawn. The instructions
are not separated by spaces.

### output
At the end of the simulation, the final position and orientation of each mower is output in the
order that the mower appeared in the input.

When designing and implementing your solution ensure that you keep in mind separation of
concerns, performance, and testing.

*Example*

**Input file**

```
5 5
1 2 N
LFLFLFLFF
3 3 E
FFRFFRFRRF
```

**Result**

```
1 3 N
5 1 E
```

## Architecture overview
### DDD and hexagonal architectures
Main patterns from DDD and hexagonal architectures were applied in this project:
* `interfaces` package contains the application entry points.
** Here only one service is exposed (command line with input file)
*`application` package contains the services corresponding to the main use cases of the application
** They are called from `interfaces` services
** They call domain services
* `domain` contains all the domain model ans its business rules.

### Multithreading
The mower controller handles multiple mowers for a given lawn.

To make them "mow the lawn" at the same time, I choose to use parallel stream. By default, parallel streams use common fork/join thread pool.

It can have many side effects (a thread blocked by another task), so I choose to use a custom thread pool. 