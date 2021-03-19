import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

public class FinalClock {

	private int time;

	public FinalClock() {
		time = 0;
	}

	public void newTimeSimulation(int runTime) {
		time = time + runTime;
	}

	public int getTimeSimulation() {
		return time;
	}

	public void newArrivalTime(ArrayList<FinalProcess> listP, int runTime) {

		for (int i = 0; i < listP.size(); i++) {
			FinalProcess p = listP.get(i);
			p.newArrivalTime(runTime);
		}
	}

	public void newWaitingTime(Queue<FinalProcess> q, int runTime, FinalProcess fP) {
		Iterator<FinalProcess> iteration = q.iterator();

		while (iteration.hasNext()) {
			FinalProcess p = iteration.next();

			if (p != fP) {
				p.setWaitTime(runTime);
			}
		}
	}
}
