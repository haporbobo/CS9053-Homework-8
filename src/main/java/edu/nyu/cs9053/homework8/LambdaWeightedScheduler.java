package edu.nyu.cs9053.homework8;
import java.util.*;
import java.time.*;

public class LambdaWeightedScheduler {

	private LinkedList<LambdaJobWeighted> jobCandiates = new LinkedList<>();

	public LinkedList<LambdaJobWeighted> getJobCandiates() { 
		return jobCandiates;
	}

	public void printer(LinkedList<LambdaJobWeighted> jobs) { 
		Iterator<LambdaJobWeighted> iter = jobs.iterator();
		while (iter.hasNext()) {
			LambdaJobWeighted job = iter.next();
			System.out.println("jobID: " + job.getJobID() + ", startTime: " + job.getStartTime() + " , finishTime: " + job.getFinishTime()
								+ ", value: " + job.getValue());
		}
	}

	public LinkedList<LambdaJobWeighted> getOptimalValueSet() { 
		Collections.sort(jobCandiates);
		LinkedList<LambdaJobWeighted> sortedJobCandiates = getJobCandiates();

		int[] compatibleLargestIndexTable = computeCompatibleTable(sortedJobCandiates);

		LinkedList<LambdaJobWeighted> optimalValueSet = computeOptimalValueSet(sortedJobCandiates, compatibleLargestIndexTable);
		return optimalValueSet;
	}

	//largest index i < j such that job i is compatible with j.
	private int[] computeCompatibleTable(LinkedList<LambdaJobWeighted> sortedJobCandiates) { 
		int[] compatibleTable = new int[sortedJobCandiates.size() + 1];
		compatibleTable[0] = 0;
		compatibleTable[1] = 0;
		
		for (int i = 2; i < compatibleTable.length; i++) { 
			ListIterator<LambdaJobWeighted> outIter = sortedJobCandiates.listIterator(i - 1);
			LocalDate startTime = outIter.next().getStartTime();

			ListIterator<LambdaJobWeighted> iter = sortedJobCandiates.listIterator();
			while (iter.hasNext() && iter.nextIndex() <= i - 1) { 
				LocalDate finishTime = iter.next().getFinishTime();
				if (finishTime.compareTo(startTime) <= 0) {
					compatibleTable[i] = iter.previousIndex() + 1;
				} 
			}	
		}
		return compatibleTable; 
	}

	private LinkedList<LambdaJobWeighted> computeOptimalValueSet(LinkedList<LambdaJobWeighted> sortedJobCandiates, int[] compatibleLargestIndexTable) {
		int[] optimalTrackTable = new int[sortedJobCandiates.size() + 1];
		optimalTrackTable[0] = 0;

		int optimalIndex = 0;
		ArrayList<Integer> jobsChosen = new ArrayList<>();
		for (int i = 1; i < optimalTrackTable.length; i++) {
			ListIterator<LambdaJobWeighted> iter = sortedJobCandiates.listIterator(i - 1);
			LambdaJobWeighted job =  iter.next();

			int jobIncluded = job.getValue() + optimalTrackTable[compatibleLargestIndexTable[i]];
			int jobExcluded = optimalTrackTable[i - 1];

			if (jobIncluded > jobExcluded) {
				optimalIndex = i;
				optimalTrackTable[i] = jobIncluded;
				jobsChosen.add(i);
			}
			else {
				optimalTrackTable[i] = jobExcluded;
			}
		}

		LinkedList<LambdaJobWeighted> optimalValueSet = new LinkedList<>();
		while (optimalIndex != 0) {
			ListIterator<LambdaJobWeighted> iter = sortedJobCandiates.listIterator(optimalIndex - 1);
			optimalValueSet.add(iter.next());
			optimalIndex = compatibleLargestIndexTable[optimalIndex];
			for (int i = 0; i < jobsChosen.size(); i++) {
				if (optimalIndex > jobsChosen.get(i)) {
					optimalIndex = jobsChosen.get(i);
					break;
				}
			}
		}
		Collections.reverse(optimalValueSet);
		return optimalValueSet;
	}

	public int computeValue(LinkedList<LambdaJobWeighted> jobs) {
		int totalValue = 0; 
		for(LambdaJobWeighted job : jobs){
			totalValue += job.getValue();
		}
		return totalValue;
	}

}