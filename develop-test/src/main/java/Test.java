import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {

	private static void order(){
		List list = new ArrayList<Integer>();
		for (int i=0;i<10;i++){
			list.add(i);
		}

		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		System.out.println(Arrays.toString(list.toArray()));
		System.out.println("ok2");
	}

	public static void main(String[] args) {
		order();

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		order();
	}

}
