import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RoundRobin {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.print("Enter the time quantum: ");

		int timeQuantum = in.nextInt();

		System.out.print("Enter the file name: ");
		ArrayList<FinalProcess> fP = new ArrayList<FinalProcess>();

		try {
			String fName = in.next();
			File fIn = new File(fName);
			Scanner input = new Scanner(fIn);

			while (input.hasNextLine()) {
				String p = input.nextLine();
				int i = 0;
				String[] s = p.split(",");
				int num = Integer.parseInt(s[0]);
				int arrivalTime = Integer.parseInt(s[1]);
				int burstTime = Integer.parseInt(s[2]);
				fP.add(new FinalProcess(num, arrivalTime, burstTime, timeQuantum));
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		FinalSimulation s = new FinalSimulation(fP, timeQuantum);

		s.roundRobinSimulation();
	}
}
