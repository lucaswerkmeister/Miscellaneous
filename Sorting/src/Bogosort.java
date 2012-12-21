import java.util.Collections;
import java.util.List;

public class Bogosort {

    public static <T extends Comparable<T>> void sort(List<T> list) {
	while (!isSorted(list))
	    Collections.shuffle(list);
    }

    public static <T extends Comparable<T>> boolean isSorted(List<T> list) {
	for (int i = 0; i < list.size() - 1; i++)
	    if (list.get(i).compareTo(list.get(i + 1)) > 0)
		return false;
	return true;
    }
}