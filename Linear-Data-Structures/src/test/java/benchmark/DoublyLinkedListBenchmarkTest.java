package benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import implementations.DoublyLinkedList;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
// Arguments that specify the memory allocation pool
// Xms -> starting memory pool 2GB and Xmx - the maximum memory pool 4GB
// NOTE: When running those tests you may want to adjust those values as well
// as the param value, otherwise -> java.lang.OutOfMemoryError may occur
@Fork(value = 2, jvmArgs = { "-Xms2G", "-Xmx4G" })
@State(Scope.Benchmark)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 2, time = 1)
public class DoublyLinkedListBenchmarkTest {

	@Param({ "1000", "10000"/* , "100000000" */ })
	private long n;

	private DoublyLinkedList<Integer> linkedList;

	@Setup(Level.Invocation)
	public void setUp() {
		linkedList = new DoublyLinkedList<>();
		for (int i = 0; i < n; i++) {
			linkedList.addLast(i);
		}
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(DoublyLinkedList.class.getSimpleName()).forks(1).build();

		new Runner(opt).run();
	}

	@Benchmark
	public void testAddFirst() {
		DoublyLinkedList<Integer> numbers = new DoublyLinkedList<>();
		for (int i = 0; i < n; i++) {
			numbers.addFirst(i);
		}
	}

	@Benchmark
	public void testAddLast() {
		DoublyLinkedList<Integer> numbers = new DoublyLinkedList<>();
		for (int i = 0; i < n; i++) {
			numbers.addFirst(i);
		}
	}

	@Benchmark
	public void testRemoveFirst() {
		while (!linkedList.isEmpty()) {
			linkedList.removeFirst();
		}
	}

	@Benchmark
	public void testRemoveLast() {
		while (!linkedList.isEmpty()) {
			linkedList.removeLast();
		}
	}
}
