package edu.nyu.cs9053.homework8;
import java.util.*;
import java.time.*;

public class LambdaScheduler {

	private LinkedList<LambdaJob> jobCandiates = new LinkedList<>();

	public LinkedList<LambdaJob> getJobCandiates() { 
		return jobCandiates;
	}

	public void printer(LinkedList<LambdaJob> jobs) { 
		Iterator<LambdaJob> iter = jobs.iterator();
		while (iter.hasNext()) {
			LambdaJob job = iter.next();
			System.out.println("jobID: " + job.getJobID() + ", startTime: " + job.getStartTime() + " , finishTime: " + job.getFinishTime());
		}
	}

	public LinkedList<LambdaJob> computeMaxCompatibleSet() { 
		Collections.sort(jobCandiates);		
		LinkedList<LambdaJob> sortedJobCandiates = getJobCandiates();

		for(int i = 0 ; i < sortedJobCandiates.size(); i++) {
			ListIterator<LambdaJob> outIter = sortedJobCandiates.listIterator(i); 
			LocalDate finishTime = outIter.next().getFinishTime(); 

			ListIterator<LambdaJob> iter = sortedJobCandiates.listIterator(i + 1);	
			while (iter.hasNext()) {
				LocalDate startTime = iter.next().getStartTime();
				if (startTime.compareTo(finishTime) < 0) { //overlap
					iter.remove();
				} 
			}
		}
		return sortedJobCandiates;
	}
}





