import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bogobogosort {

    public static <T extends Comparable<T>> void sort(List<T> list) {
	if (list.size() <= 1)
	    return;
	while (!isSorted(list))
	    Collections.shuffle(list);
    }

    public static <T extends Comparable<T>> boolean isSorted(List<T> list) {
	List<T> copy = new ArrayList<>(list);
	List<T> subList;
	do {
	    Collections.shuffle(copy);
	    subList = copy.subList(0, copy.size() - 1);
	    sort(subList);
	} while (copy.get(copy.size() - 1).compareTo(
		subList.get(subList.size() - 1)) < 0);
	return copy.equals(list);
    }
}