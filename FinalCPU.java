public class FinalCPU {

	private int timeQuantum;
	private int completedProcesses;

	public FinalCPU(int tQ) {
		timeQuantum = tQ;
		completedProcesses = 0;
	}

	public int Run(FinalProcess p) {

		if (p.getBurstTime() > timeQuantum) {
			p.newBurstTime();
			return timeQuantum;
		}

		else if (p.getBurstTime() < timeQuantum) {
			int run = p.getBurstTime();
			p.newBurstTime();
			completedProcesses++;
			return run;
		}

		else {
			p.getBurstTime();
			completedProcesses++;
			return timeQuantum;
		}
	}

	public int getCompletedProcesses() {
		return completedProcesses;
	}
}
