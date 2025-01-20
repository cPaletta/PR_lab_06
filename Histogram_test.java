import java.util.Scanner;


class Histogram_test {
    
    public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Set image size: n (#rows), m(#kolumns)");
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		Obraz obraz_1 = new Obraz(n, m);

		obraz_1.calculate_histogram();
		obraz_1.print_histogram();

		System.out.println("Set number of threads");
		int num_threads = scanner.nextInt();

		Thread[] NewThr = new Thread[num_threads];
		int range = 94 / num_threads;

		for (int i = 0; i < num_threads; i++) {
			int znakStart = i * range; 
			int znakEnd;
		
			if (i == num_threads - 1) {
				znakEnd = 94;
			} else {
				znakEnd = znakStart + range - 1;
			}
		
			NewThr[i] = new Watek(znakStart, znakEnd, obraz_1); // Tworzenie wątku
			NewThr[i].start();
		}
		
		for (int i = 0; i < num_threads; i++) {
		    try {
			NewThr[i].join();
		    } catch (InterruptedException e) {}
		}

    }

}

