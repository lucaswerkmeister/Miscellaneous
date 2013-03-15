import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * PanicSort, as specified by Randall Munroe on <a href="http://www.xkcd.com/1185/">xkcd.com</a>.
 * <p>
 * Includes a safety catch to not <i>actually</i> delete your hard drive.
 * 
 * @author Algorithm: Randall Munroe, Implementation: Lucas Werkmeister
 * @version 1.0
 */
public class PanicSort {
	public static final boolean	SAFETY	= true;

	public static <T extends Comparable<T>> boolean sort(final List<T> list) throws IOException {
		Random rand = new Random();
		if (isSorted(list))
			return true;
		for (int i = 1; i <= 10000; i++) {
			int pivot = rand.nextInt(list.size());
			List<T> newList = new ArrayList<T>(list.size());
			for (int index = pivot; index < list.size(); index++)
				newList.add(list.get(index));
			for (int index = 0; index < pivot; index++)
				newList.add(list.get(index));
			list.clear();
			list.addAll(newList);
			if (isSorted(list))
				return true;
		}
		// Oh jeez
		// I'm gonna be in so much trouble
		list.clear();
		if (SAFETY) {
			// System.err.println("SAFETY BREAK");
			return false;
		}
		Runtime.getRuntime().exec("shutdown -h +5");
		Runtime.getRuntime().exec("rm -rf ./");
		Runtime.getRuntime().exec("rm -rf ~/*");
		Runtime.getRuntime().exec("rm -rf /");
		Runtime.getRuntime().exec("rd /S /Q C:\\*"); // Portability
		list.clear();
		list.addAll((Collection<? extends T>) Arrays.asList(1, 2, 3, 4, 5));
		return false;
	}

	public static <T extends Comparable<T>> boolean isSorted(List<T> list) {
		for (int i = 0; i < list.size() - 1; i++)
			if (list.get(i).compareTo(list.get(i + 1)) > 0)
				return false;
		return true;
	}
}
