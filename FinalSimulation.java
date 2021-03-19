import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FinalSimulation {

	private ArrayList<FinalProcess> processArrayList;
	private int timeQuantum;
	private FinalCPU cpu;
	private Queue<FinalProcess> readyQueue;
	private FinalClock clock;
	private ArrayList<FinalProcess> completedProcesses;
	private static int totalProcesses;

	public FinalSimulation(ArrayList<FinalProcess> fP, int tq) {
		processArrayList = fP;
		timeQuantum = tq;
		cpu = new FinalCPU(timeQuantum);
		readyQueue = new LinkedList<FinalProcess>();
		clock = new FinalClock();
		completedProcesses = new ArrayList<FinalProcess>();
		totalProcesses = 4;

		for (int i = 0; i < fP.size(); i++) {
			fP.get(i).setArrivalTimeI();
			fP.get(i).setBurstTimeI();
		}
	}

	public void roundRobinSimulation() {
		processor(null);
		RoundRobin();
	}

	public void processor(FinalProcess process) {

		for (int i = 0; i < processArrayList.size(); i++) {
			FinalProcess p = processArrayList.get(i);

			if (p.toExecute() == true && p.finishedProcess() == 0 && (p != process) && !readyQueue.contains(p)) {
				readyQueue.add(p);
				System.out.println("New process " + p.getPN());
			}
		}
	}

	public void RoundRobin() {
		boolean RR = false;

		while (!RR) {
			FinalProcess process = readyQueue.peek();

			if (process != null) {
				int run = cpu.Run(process);
				clock.newTimeSimulation(run);
				clock.newArrivalTime(processArrayList, run);
				clock.newWaitingTime(readyQueue, run, process);
				processor(process);

				if (process.finishedProcess() == 1) {
					System.out.println("Process completed execution: " + process.getPN());
					processArrayList.remove(process);
					System.out.println("Process removed from ready queue: " + process.getPN());
					completedProcesses.add(readyQueue.remove());

					if (completedProcesses.size() == totalProcesses) {
						this.simulation();
						RR = true;
					}
				}

				else {
					process.newContextSwitch();
					process.getInfo();
					readyQueue.remove();
					readyQueue.add(process);
					processor(process);
				}
			}

			else {
				clock.newTimeSimulation(timeQuantum);
				clock.newArrivalTime(processArrayList, timeQuantum);
				processor(null);
			}
		}
	}
	
	public void simulation() {
		int totalWaitTime = 0;
		int totalTurnaroundTime = 0;
		int totalContextSwitch = 0;
		int wait = 0;

		for (int i = 0; i < completedProcesses.size(); i++) {
			completedProcesses.get(i).setTurnaroundTime();
		}
		int turnaround1 = completedProcesses.get(0).getTurnaroundTime();
		int turnaround2 = completedProcesses.get(1).getTurnaroundTime();
		int turnaround3 = completedProcesses.get(2).getTurnaroundTime();
		int turnaround4 = completedProcesses.get(3).getTurnaroundTime();
		int turnaround = Math.max(turnaround1, Math.max(turnaround2, Math.max(turnaround3, turnaround4)));
		totalTurnaroundTime = turnaround1 + turnaround2 + turnaround3 + turnaround4;

		for (int i = 0; i < completedProcesses.size(); i++) {
			wait = wait + completedProcesses.get(i).getTotalWaitTime();
		}

		for (int i = 0; i < completedProcesses.size(); i++) {
			totalWaitTime = wait;
			totalContextSwitch = totalContextSwitch + completedProcesses.get(i).getContextSwitch();
		}
		
		double finalWaitTime = totalWaitTime / totalProcesses;
		double finalTurnaroundTime = totalTurnaroundTime / totalProcesses;
		
		System.out.println("Average wait time: " + finalWaitTime);
		System.out.println("Average turnaround time: " + finalTurnaroundTime);
		
		double output = ((double) cpu.getCompletedProcesses() / clock.getTimeSimulation());
		
		System.out.println("Output: " + output);
		
		double finalContextSwitch = clock.getTimeSimulation() - totalContextSwitch;
		double utilize = (finalContextSwitch / clock.getTimeSimulation());
		
		System.out.println("CPU utilization: " + utilize);
	}
}
