import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static final int	MAX_SAMPLES		= 6;
	public static final int	AVERAGING_RUNS	= 100;

	public static void main(String[] args) throws IOException {
		System.nanoTime(); // init nanoTime
		for (int c = 1; c <= MAX_SAMPLES; c++) {
			long sumOfTime = 0;
			long bestTime = Long.MAX_VALUE;
			for (int r = 0; r < AVERAGING_RUNS; r++) {
				List<Integer> samples = new ArrayList<>();
				for (int i = 0; i < c; i++)
					samples.add(i);
				Collections.shuffle(samples);
				long start = System.nanoTime();
				boolean success = PanicSort.sort(samples);
				long stop = System.nanoTime();
				long time = stop - start;
				sumOfTime += time;
				if (time < bestTime)
					bestTime = time;
				System.out.print(success ? '.' : '!');
			}
			System.out.println(c + (c == 1 ? " sample:  " : " samples: ") + "average: "
					+ format(sumOfTime / AVERAGING_RUNS) + " best: " + format(bestTime));
		}
	}

	public static String format(long time) {
		return time + "ns (" + (time / 1_000_000) + "ms)";
	}
}