package hostelerp;

import java.util.Scanner;
import java.util.Stack;

import org.apache.commons.codec.binary.Base64;

public class SampleEncodeing
{
	Stack<String> backward = new Stack<String>(); // Backward pages
	Stack<String> forward = new Stack<String>(); // Forward pages

	public SampleEncodeing(Scanner sc)
	{
		while (sc.hasNext())
		{
			String input = sc.nextLine();

			switch (input)
			{
			case "BACKWARD":
				if (!backward.empty())
					forward.push(backward.pop()); // Remove from backward, add
													// to forward
				break;

			case "FORWARD":
				if (!forward.empty())
					backward.push(forward.pop()); // Remove from forward, add to
													// backward
				break;

			default:
				backward.push(input); // New page opened
				while (!forward.empty())
					// Clear forward stack
					forward.pop();
				break;
			}
			if (backward.empty() && !forward.empty()) // Add forward pages to
														// history
				backward.push(forward.pop());
		}

		if (backward.empty() && forward.empty())
			System.out.println("Browsing history is empty.");

		else
		{

			// If there is only 1 page, current page in forward stack else in
			// backward
			String current =
					(backward.empty()) ? forward.peek() : backward.peek();

			System.out.println("Browsing History:");

			while (!forward.empty())
				// In order to print from least to most recent
				backward.push(forward.pop());

			while (!backward.empty())
				forward.push(backward.pop());

			while (!forward.empty())
				System.out.println(forward.pop());

			System.out.println("Current Page:");
			System.out.println(current);
		}
	}

	public static void main(String[] args)
	{
		/*
		 * Scanner sc = new Scanner(System.in); SampleEncodeing bc = new
		 * SampleEncodeing(sc);
		 */

		/*
		 * List list = new ArrayList(); list.add(1); list.add(12); list.add(10);
		 *
		 * Collections.sort(list, new Comparator<Integer>() {
		 *
		 * @Override public int compare(Integer o1, Integer o2) { if (o1 < o2) {
		 * return 1; } else if (o1 == o2) { return 0; } else { return -1; } }
		 *
		 * });
		 */

		// System.out.println(list);
		String str = "P@ssw0rd";
		byte[] bytesEncoded = Base64.encodeBase64(str.getBytes());
		System.out.println("ecncoded value is " + new String(bytesEncoded));

		// Decode data on other side, by processing encoded data
		byte[] valueDecoded = Base64.decodeBase64(bytesEncoded);
		System.out.println("Decoded value is " + new String(valueDecoded));

	}
}
