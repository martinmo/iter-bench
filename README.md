# ArrayList vs LinkedList iterator benchmark

This is a tiny benchmark to compare the iteration performance of Java's `ArrayList` against
`LinkedList`, using the [Java Microbenchmark Harness (JMH)][jmh].


## Results

Apple M1, OpenJDK Runtime Environment Zulu11.48+21-CA (build 11.0.11+9-LTS):

    Benchmark                         (listType)  (size)  Mode  Cnt   Score    Error  Units
    IterBenchmark.measure_iteration   ARRAY_LIST     200  avgt    5   0.536 ±  0.002  us/op
    IterBenchmark.measure_iteration   ARRAY_LIST   30000  avgt    5  80.863 ±  0.539  us/op
    IterBenchmark.measure_iteration  LINKED_LIST     200  avgt    5   0.602 ±  0.001  us/op
    IterBenchmark.measure_iteration  LINKED_LIST   30000  avgt    5  91.204 ±  3.828  us/op

Intel Xeon Gold 6136 @ 3.00GHz, OpenJDK Runtime Environment (build 11.0.12+7-post-Debian-2deb10u1):

    Benchmark                         (listType)  (size)  Mode  Cnt    Score   Error  Units
    IterBenchmark.measure_iteration   ARRAY_LIST     200  avgt    5    0.814 ± 0.053  us/op
    IterBenchmark.measure_iteration   ARRAY_LIST   30000  avgt    5  155.893 ± 2.928  us/op
    IterBenchmark.measure_iteration  LINKED_LIST     200  avgt    5    0.905 ± 0.027  us/op
    IterBenchmark.measure_iteration  LINKED_LIST   30000  avgt    5  128.268 ± 4.429  us/op

As can be seen, the differences are small on these machines. In nearly all cases, the ArrayList
iteration was slightly faster.


## Caveats / Disclaimer

Be aware: This is by no means a complete benchmark. It is not peer reviewed and I am not a JVM
expert. Although I tried to avoid the usual [Java benchmarking pitfalls][pitfalls], there's still a
chance that I've missed something crucial.

Also note that this benchmark only "investigates" a specific usage of the iterator (completely
iterating over a list of Integer objects). It only measures the average runtime of a complete
iteration and doesn't look at the memory usage, which can also be an important factor.


## How to run

First, build the JAR with Maven:

    mvn clean verify

Then, execute it:

    java -jar target/benchmarks.jar


[jmh]: https://github.com/openjdk/jmh/
[pitfalls]: https://www.oracle.com/technical-resources/articles/java/architect-benchmarking.html
