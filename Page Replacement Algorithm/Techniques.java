import java.util.Scanner;

public class Techniques{
	public static void FIFO(int[] pages, int p, int f) {
		int[] freq = new int[f];
		int[] frames = new int[f];
		int hits = 0;

		for (int i = 0; i < p; i++) {
			boolean flag = false;
			int maxi = -1;
			int ind = -1;

			for (int j = 0; j < f; j++) {
				if (frames[j] == 0) {
					flag = true;
					frames[j] = pages[i];
					break;
				}
				if (pages[i] == frames[j]) {
					hits++;
//					System.out.println(pages[i]);
					flag = true;
					break;
				}
				if (maxi < freq[j]) {
					maxi = freq[j];
					ind = j;
				}
			}

			if (flag == false) {
				frames[ind] = pages[i];
				freq[ind] = 0;
			}

			int j = 0;
			while (j < f && frames[j] != 0) {
				freq[j]++;
				j++;
			}
		}
		System.out.println("No of hits :- " + hits);
		System.out.println("No of Faults :- " + (p - hits));
	}

	public static void LRU(int[] pages, int p, int f) {
		int frames[] = new int[f];
		int hits = 0;

		for (int i = 0; i < p; i++) {
			boolean flag = false;
			for (int j = 0; j < f; j++) {
				if (frames[j] == 0) {
					frames[j] = pages[i];
					flag = true;
					break;
				}
				if (frames[j] == pages[i]) {
					hits++;
					flag = true;
					break;
				}
			}

			if (flag == false) {
				int k = i - 1;
				int ct = 0;
				while (k >= 0 && ct < f) {
					boolean fl = false;
					for (int temp = 0; temp < f; temp++) {
						if (ct == f - 1 && pages[k] == frames[temp]) {
							frames[temp] = pages[i];
							fl = true;
							break;
						}
						if (pages[k] == frames[temp]) {
							ct++;
							break;
						}
					}
					if (fl == true)
						break;
					k--;
				}
			}
		}
		System.out.println("No of Hits are :- " + hits);
		System.out.println("No of Faults are :- " + (p - hits));
	}

	public static void Optimal(int[] pages, int p, int f) {
		int frames[] = new int[f];
		int hits = 0;

		for (int i = 0; i < p; i++) {
			boolean flag = false;
			for (int j = 0; j < f; j++) {
				if (frames[j] == 0) {
					frames[j] = pages[i];
					flag = true;
					break;
				}
				if (frames[j] == pages[i]) {
					hits++;
					// System.out.println("hit");
					flag = true;
					break;
				}
			}

			if (flag == false) {
				int ele = -1;
				int maxi = i;
				int tr = 0;
				boolean done = false;
				for (tr = 0; tr < f; tr++) {
					boolean fl = false;
					for (int pg = i + 1; pg < p; pg++) {
						if (frames[tr] == pages[pg]) {
							fl = true;
							if (maxi < pg) {
								maxi = pg;
								ele = tr;
							}
							break;
						}
					}
					if (fl == false) {
						frames[tr] = pages[i];
						done = true;
						break;
					}

				}

				if (done == false) {
					frames[ele] = pages[i];
				}
			}
		}
		System.out.println("No of Hits are : " + hits);
		System.out.println("No of Faults are : " + (p - hits));
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int p, f;
		System.out.println("Enter the number of Pages: ");
		p = sc.nextInt();

		System.out.println("Enter the number of Frames: ");
		f = sc.nextInt();
		int[] pages = new int[p];

		System.out.println("Enter the Sequence/Reference String: ");
		for (int i = 0; i < p; i++) {
			pages[i] = sc.nextInt();
		}

		int ch;
		do {
			System.out.println("1. FIFO");
			System.out.println("2. LRU");
			System.out.println("3. Optimal");
			System.out.println("4. Exit");

			System.out.println("Enter the operation you want to perform: ");
			ch = sc.nextInt();

			switch (ch) {
				case 1:
					FIFO(pages, p, f);
					break;
				case 2:
					LRU(pages, p, f);
					break;
				case 3:
					Optimal(pages, p, f);
					break;
			}
		} while (ch <= 3);
		sc.close();
	}
}