package benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class IterBenchmark {
    public enum ListType {
        ARRAY_LIST {
            @Override
            <E> List<E> createList() {
                return new ArrayList<>();
            }
        },
        LINKED_LIST {
            @Override
            <E> List<E> createList() {
                return new LinkedList<>();
            }
        };
        abstract <E> List<E> createList();
    }

    @Param
    ListType listType;

    @Param({"200", "30000"})
    int size;

    List<Integer> list;

    @Setup
    public void setUp() {
        list = listType.createList();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }

    @Benchmark
    public void measure_iteration(Blackhole bh) {
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            bh.consume(iter.next());
        }
    }
}
