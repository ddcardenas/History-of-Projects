package assignment3;

/*  My output

Testing Fearless Leader's program.
QS: book partition, random pivot, cutoff=50: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
QS: 2ptr partition, random pivot, cutoff=50: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
QS: book partition, lf pivot, cutoff=50: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
QS: 2ptr partition, lf pivot, cutoff=50: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
QS: 3 Partition, cutoff=50: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
QS: book partition, random pivot, cutoff=2: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
QS: 2ptr partition, random pivot, cutoff=2: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
QS: book partition, lf pivot, cutoff=2: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
QS: 2ptr partition, lf pivot, cutoff=2: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
QS: 3 Partition, cutoff=2: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
Almost QS book partition, random pivot, cutoff=2: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
Almost QS 2ptr partition, random pivot, cutoff=2: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
Almost QS 3 Partition, random pivot, cutoff=2: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
HeapSortTD: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
HeapSortBU: 
    26.25.24.23.22.21.20.19.18.17.16.15.14.13.12.11.10.9.8.7.6.5.4.3.2.1.0.
Bigger, QS: book partition, random pivot, cutoff=50:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, QS: 2ptr partition, random pivot, cutoff=50:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, QS: book partition, lf pivot, cutoff=50:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, QS: 2ptr partition, lf pivot, cutoff=50:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, QS: 3 Partition, cutoff=50:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, QS: book partition, random pivot, cutoff=2:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, QS: 2ptr partition, random pivot, cutoff=2:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, QS: book partition, lf pivot, cutoff=2:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, QS: 2ptr partition, lf pivot, cutoff=2:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, QS: 3 Partition, cutoff=2:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, Almost QS book partition, random pivot, cutoff=2:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, Almost QS 2ptr partition, random pivot, cutoff=2:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, Almost QS 3 Partition, random pivot, cutoff=2:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, HeapSortTD:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.
Bigger, HeapSortBU:
    100.631.1162.1693.2224.2755.3286.3817.4348.4879.5410.5941.6472.7003.7534.8065.8596.
    9127.9658.10189.10720.11251.11782.12313.12844.13375.13906.14437.14968.15499.16030.
    16561.17092.17623.18154.18685.19216.19747. done.

Testing Fearless Leader's sorts on a huge array.

Java's QuickSort on random data runs in 5.830 seconds.
Java's QuickSort on the test sets runs in 1.575 seconds.


Fearless Leader's QS: book partition, random pivot, cutoff=50
    on all random values runs in 5.658 seconds.      Java Ratio = 0.97
    on the test sets runs in 3.609 seconds.          Java Ratio = 2.29

Fearless Leader's QS: 2ptr partition, random pivot, cutoff=50
    on all random values runs in 5.400 seconds.      Java Ratio = 0.92
    on the test sets runs in 2.746 seconds.          Java Ratio = 1.74

Fearless Leader's QS: book partition, lf pivot, cutoff=50
    on all random values runs in 5.720 seconds.      Java Ratio = 0.98

Fearless Leader's QS: 2ptr partition, lf pivot, cutoff=50
    on all random values runs in 5.391 seconds.      Java Ratio = 0.92

Fearless Leader's QS: 3 Partition, cutoff=50
    on all random values runs in 6.123 seconds.      Java Ratio = 1.05
    on the test sets runs in 4.018 seconds.          Java Ratio = 2.55

Fearless Leader's QS: book partition, random pivot, cutoff=2
    on all random values runs in 7.472 seconds.      Java Ratio = 1.28
    on the test sets runs in 5.170 seconds.          Java Ratio = 3.28

Fearless Leader's QS: 2ptr partition, random pivot, cutoff=2
    on all random values runs in 7.539 seconds.      Java Ratio = 1.29
    on the test sets runs in 4.653 seconds.          Java Ratio = 2.95

Fearless Leader's QS: book partition, lf pivot, cutoff=2
    on all random values runs in 6.289 seconds.      Java Ratio = 1.07

Fearless Leader's QS: 2ptr partition, lf pivot, cutoff=2
    on all random values runs in 6.330 seconds.      Java Ratio = 1.08

Fearless Leader's QS: 3 Partition, cutoff=2
    on all random values runs in 8.011 seconds.      Java Ratio = 1.37
    on the test sets runs in 5.597 seconds.          Java Ratio = 3.55

Fearless Leader's Almost QS book partition, random pivot, cutoff=2
    on all random values runs in 7.338 seconds.      Java Ratio = 1.25
    on the test sets runs in 5.040 seconds.          Java Ratio = 3.20

Fearless Leader's Almost QS 2ptr partition, random pivot, cutoff=2
    on all random values runs in 7.380 seconds.      Java Ratio = 1.26
    on the test sets runs in 4.510 seconds.          Java Ratio = 2.86

Fearless Leader's Almost QS 3 Partition, random pivot, cutoff=2
    on all random values runs in 7.354 seconds.      Java Ratio = 1.26
    on the test sets runs in 5.446 seconds.          Java Ratio = 3.45

Fearless Leader's HeapSortTD
    on all random values runs in 11.652 seconds.      Java Ratio = 1.99
    on the test sets runs in 7.019 seconds.          Java Ratio = 4.45

Fearless Leader's HeapSortBU
    on all random values runs in 14.027 seconds.      Java Ratio = 2.40
    on the test sets runs in 6.503 seconds.          Java Ratio = 4.12

Done testing Fearless Leader methods.

*/

import java.io.*; // for IOException
import java.util.Arrays; // to compare your sort against Java's

public class test2 {

	public static void main(String[] args) throws IOException {

		final int largeSize = 10000000;
		int[] number, goodone;
		int n, differ, sort, fill, i;
		boolean[] Sort_OK = { true, true, true, true, true, true, true, true,
				true, true, true, true, true, true, true };
		long before, after, yourtotal, javarandomtotal, javaothertotal;
		number = new int[20000];
		goodone = new int[20000];
		String[] SortType = {
				"QS: book partition, random pivot, cutoff=50",
				"QS: 2ptr partition, random pivot, cutoff=50",
				"QS: book partition, lf pivot, cutoff=50",
				"QS: 2ptr partition, lf pivot, cutoff=50",
				"QS: 3 Partition, cutoff=50",
				"QS: book partition, random pivot, cutoff=2",
				"QS: 2ptr partition, random pivot, cutoff=2",
				"QS: book partition, lf pivot, cutoff=2",
				"QS: 2ptr partition, lf pivot, cutoff=2",
				"QS: 3 Partition, cutoff=2",
				"Almost QS book partition, random pivot, cutoff=2",
				"Almost QS 2ptr partition, random pivot, cutoff=2",
				"Almost QS 3 Partition, random pivot, cutoff=2",
				"HeapSortTD",
				"HeapSortBU" };
		String[] FillType = { "Increasing", "Decreasing", "Random", "Constant",
				"Mids" };

		int numSorts = SortType.length;
		System.out.println("Testing " + ArraySorts.myName() + "'s program.");

		// First check to see if they sort small arrays properly
		for (sort = 0; sort < numSorts; sort++) {
			System.out.print(SortType[sort] + ": \n    ");
			for (n = 26; n >= 0 && Sort_OK[sort]; n--) {
				System.out.print(n + ".");

				for (fill = 0; fill < FillType.length && Sort_OK[sort]; fill++) {
					fillArray(fill, number, 0, n);
					for (i = 0; i < n; i++)
						goodone[i] = number[i];
					callSort(sort + 1, number, n);
					Arrays.sort(goodone, 0, n);
					differ = same(goodone, number, n);
					if (differ != -1) {
						System.out.println();
						System.out.println("*** " + FillType[fill]
								+ " sorting error: " + SortType[sort]
								+ ", position #" + differ);
						System.out.println("*** No more " + SortType[sort]
								+ "s will be tested.  Correct values and "
								+ ArraySorts.myName() + "'s values follow.");
						Sort_OK[sort] = false;
						mydisplay(goodone, n);
						mydisplay(number, n);
					}
				}
			}
			System.out.println();
		}

		// Now check larger arrays
		for (sort = 0; sort < numSorts; sort++) {
			System.out.print("Bigger, " + SortType[sort] + ":\n    ");
			for (n = 100; n <= 20000 && Sort_OK[sort]; n = n + 531) {
				System.out.print(n + ".");
				if (n == 8596 || n == 16030) {
					System.out.println();
					System.out.print("    ");
				}

				for (fill = 0; fill < FillType.length && Sort_OK[sort]; fill++) {
					fillArray(fill, number, 1, n);
					//MY TESTING
					if (n==23) {
						System.out.print("Here is the original array: \n");
						for (int index=0; index<number.length; index++)
						{
							System.out.print(" " + number[index] + ", ");
						} 
						System.out.println("\n");
					}
					//MY TESTING
					for (i = 0; i < n; i++)
						goodone[i] = number[i];
					callSort(sort + 1, number, n);
					Arrays.sort(goodone, 0, n);
					differ = same(goodone, number, n);
					if (differ != -1) {
						System.out.println();
						System.out.println("*** " + FillType[fill]
								+ " sorting error: " + SortType[sort]
								+ ", position #" + differ);
						System.out.println("*** No more " + SortType[sort]
								+ "s will be tested.  Correct values and "
								+ ArraySorts.myName() + "'s values follow.");
						Sort_OK[sort] = false;
					}
				}
			}
			System.out.println(" done.");
		}
		System.out.println();

		// Now see how fast your sorts really are
		System.out.println("Testing " + ArraySorts.myName()
				+ "'s sorts on a huge array.");
		number = new int[largeSize];
		javarandomtotal = 0;
		for (fill = 0; fill < 5; fill++) {
			fillArray(2, number, 1, largeSize);
			before = System.currentTimeMillis();
			Arrays.sort(number);
			after = System.currentTimeMillis();
			javarandomtotal = javarandomtotal + after - before;
		}
		System.out.println("\nJava's QuickSort on random data runs in "
				+ javarandomtotal / 1000 + "." + millis(javarandomtotal % 1000)
				+ " seconds.");

		javaothertotal = 0;
		for (fill = 0; fill < 5; fill++) {
			fillArray(fill, number, 1, largeSize);
			before = System.currentTimeMillis();
			Arrays.sort(number);
			after = System.currentTimeMillis();
			javaothertotal = javaothertotal + after - before;
		}
		System.out.println("Java's QuickSort on the test sets runs in "
				+ javaothertotal / 1000 + "." + millis(javaothertotal % 1000)
				+ " seconds.");
		System.out.println();

		for (int sortNum = 0; sortNum < numSorts; sortNum++) {
			if (Sort_OK[sortNum]) {
				yourtotal = 0;
				for (fill = 0; fill < 5; fill++) {
					fillArray(2, number, 1, largeSize);
					before = System.currentTimeMillis();
					callSort(sortNum + 1, number, largeSize);
					after = System.currentTimeMillis();
					yourtotal = yourtotal + after - before;
				}
				System.out.print("\n" + ArraySorts.myName() + "'s "
						+ SortType[sortNum]
						+ "\n    on all random values runs in " + yourtotal
						/ 1000 + "." + millis(yourtotal % 1000) + " seconds.");
				System.out
						.println("      Java Ratio = "
								+ (yourtotal / javarandomtotal)
								+ "."
								+ hundreds((int) (((float) (yourtotal)
										/ javarandomtotal - (yourtotal / javarandomtotal)) * 100)));
				if ( sortNum != 2 && sortNum != 3 && sortNum != 7 && sortNum != 8 ) { //sortNum == 1 || sortNum == 6) {
					yourtotal = 0;
					for (fill = 0; fill < 5; fill++) {
						fillArray(fill, number, 1, largeSize);
						before = System.currentTimeMillis();
						callSort(sortNum + 1, number, largeSize);
						after = System.currentTimeMillis();
						yourtotal = yourtotal + after - before;
					}
					System.out.print("    on the test sets runs in "
							+ yourtotal / 1000 + "." + millis(yourtotal % 1000)
							+ " seconds.");
					System.out
							.print("          Java Ratio = "
									+ (yourtotal / javaothertotal)
									+ "."
									+ hundreds((int) (((float) (yourtotal)
											/ javaothertotal - (yourtotal / javaothertotal)) * 100)));
					System.out.println();
				}
			} else {
				System.out.println(ArraySorts.myName() + "'s "
						+ SortType[sortNum] + " not tested.");
				System.out.println();
			}
		}
		System.out.println("\nDone testing " + ArraySorts.myName()
				+ " methods.");
	}

	private static void callSort(int which, int[] a, int n) {
		if (which == 1)
			ArraySorts.QuickSort1(a, n, 50);
		else if (which == 2)
			ArraySorts.QuickSort2(a, n, 50);
		else if (which == 3)
			ArraySorts.QuickSort3(a, n, 50);
		else if (which == 4)
			ArraySorts.QuickSort4(a, n, 50);
		else if (which == 5)
			ArraySorts.QuickSort5(a, n, 50);
		else if (which == 6)
			ArraySorts.QuickSort1(a, n, 2);
		else if (which == 7)
			ArraySorts.QuickSort2(a, n, 2);
		else if (which == 8)
			ArraySorts.QuickSort3(a, n, 2);
		else if (which == 9)
			ArraySorts.QuickSort4(a, n, 2);
		else if (which == 10)
			ArraySorts.QuickSort5(a, n, 2);
		else if (which == 11)
			ArraySorts.AlmostQS1(a, n, 2);
		else if (which == 12)
			ArraySorts.AlmostQS2(a, n, 2);
		else if (which == 13)
			ArraySorts.AlmostQS5(a, n, 2);
		else if (which == 14)
			ArraySorts.HeapSortTD(a, n);
		else if (which == 15)
			ArraySorts.HeapSortBU(a, n);
		else
			System.out.println("No such sort " + which + ".");
	}

	private static void fillArray(int fillNumber, int[] number, int BigorSmall,
			int n) {
		if (fillNumber == 0)
			fillIncreasing(number, n);
		else if (fillNumber == 1)
			fillDecreasing(number, n);
		else if (fillNumber == 2) {
			if (BigorSmall == 0)
				fillRandomSmall(number, n);
			else
				fillRandomBig(number, n);
		} else if (fillNumber == 3)
			fillConstant(number, n);
		else if (fillNumber == 4)
			fillMids(number, n);
		else
			System.out.println("No such filling scheme.");
	}

	public static int same(int[] a, int[] b, int n) {
		int result = -1;
		for (int i = 0; (i < n) && (result == -1); i++)
			if (a[i] != b[i])
				result = i;
		return result;
	}

	public static void mydisplay(int[] a, int n) {
		for (int i = 0; i < n; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void fillMids(int[] a, int n) {
		fillMids(a, 0, n - 1, 0);
	}

	private static void fillMids(int[] a, int lf, int rt, int ct) {

		while (lf <= rt) {
			a[lf] = ct;
			ct++;
			lf++;
			a[rt] = ct;
			ct++;
			rt--;
		}
	}

	public static void fillConstant(int a[], int n) {
		int inc = n / Math.max(1, (int) Math.log((double) n));
		for (int i = 0; i < n; i++) {
			a[i] = 52;
		}
		for (int i = 0; i < n; i += inc) {
			if (i % 2 == 0)
				a[i] = 51;
			else
				a[i] = 53;
		}
	}

	public static void fillIncreasing(int[] a, int n) {
		for (int i = 0; i < n; i++)
			a[i] = i;
		if (n > 2) {
			a[n - 2] = 2;
			a[1] = n - 2;
		}
	}

	public static void fillDecreasing(int[] a, int n) {
		for (int i = 0; i < n; i++)
			a[i] = n - i - 1;
		if (n > 2) {
			a[n - 2] = n - 2;
			a[1] = 1;
		}
	}

	public static void fillRandomSmall(int a[], int n) {
		for (int i = 0; i < n; i++)
			a[i] = (int) (Math.random() * n);
	}

	public static void fillRandomBig(int a[], int n) {
		for (int i = 0; i < n; i++)
			a[i] = (int) (Math.random() * n * 10);
	}

	public static String millis(long n) {
		String rtn;
		if (n < 10)
			rtn = "00" + n;
		else if (n < 100)
			rtn = "0" + n;
		else
			rtn = String.valueOf(n);
		return rtn;
	}

	public static String hundreds(long n) {
		String rtn;
		if (n < 10)
			rtn = "0" + n;
		else
			rtn = String.valueOf(n);
		return rtn;
	}

}