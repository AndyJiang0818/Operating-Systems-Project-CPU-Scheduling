public class FinalProcess {

	private int processNumber;
	private int arrivalTime;
	private int burstTime;
	private int timeQuantum;
	private int contextSwitch;
	private int waitTime;
	private int turnaroundTime;
	private int arrivalTimeInitial;
	private int burstTimeInitial;

	public FinalProcess(int pN, int aT, int bT, int tQ) {
		processNumber = pN;
		arrivalTime = aT;
		burstTime = bT;
		timeQuantum = tQ;
		contextSwitch = 0;
		waitTime = 0;
		turnaroundTime = 0;
		arrivalTimeInitial = 0;
		burstTimeInitial = 0;
	}

	public int getPN() {
		return processNumber;
	}

	public void setArrivalTimeI() {
		arrivalTimeInitial = arrivalTime;
	}

	public int getArrivalTimeI() {
		return arrivalTimeInitial;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void newArrivalTime(int runTime) {
		arrivalTime = arrivalTime - runTime;

		if (arrivalTime < 0) {
			arrivalTime = 0;
		}
	}

	public void setBurstTimeI() {
		burstTimeInitial = burstTime;
	}

	public int getBurstTimeI() {
		return burstTimeInitial;
	}

	public void setWaitTime(int runTime) {
		waitTime = waitTime + runTime;
	}

	public int getTotalWaitTime() {
		return waitTime;
	}

	public void newBurstTime() {
		burstTime = burstTime - timeQuantum;

		if (burstTime < 0) {
			burstTime = 0;
		}
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void newContextSwitch() {
		contextSwitch++;
	}

	public int getContextSwitch() {
		return contextSwitch;
	}

	public void setTurnaroundTime() {
		turnaroundTime = this.getTotalWaitTime() + this.getBurstTimeI();
	}

	public int getTurnaroundTime() {
		return turnaroundTime;
	}

	public int finishedProcess() {

		if (getBurstTime() == 0) {
			return 1;
		}

		else
			return 0;
	}

	public boolean toExecute() {

		if (getArrivalTime() == 0) {
			return true;
		}

		else
			return false;
	}
	
	public void getInfo() {
		System.out.println("Name:" + processNumber);
		System.out.println("Burst time:" + getBurstTime());
		System.out.println("Time to enter ready queue:" + getArrivalTime());
		System.out.println("To enter ready queue:" + toExecute());
		System.out.println("Context switch:" + contextSwitch);
		System.out.println("Total waiting time:" + getTotalWaitTime());
		System.out.println("Total turnaround time:" + getTurnaroundTime());
	}
}
