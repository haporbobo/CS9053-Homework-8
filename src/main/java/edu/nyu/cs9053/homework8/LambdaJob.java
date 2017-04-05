package edu.nyu.cs9053.homework8;
import java.time.*;

public class LambdaJob implements Comparable<LambdaJob> {
	private final int jobID;
	private final LocalDate startTime;
	private final LocalDate finishTime;

	public LambdaJob(int jobID, LocalDate startTime, LocalDate finishTime) {
		this.jobID = jobID;
		this.startTime = startTime;
		this.finishTime = finishTime;
	}

	public int getJobID() {
		return jobID;
	}

	public LocalDate getStartTime() {
		return startTime;
	}

	public LocalDate getFinishTime() {
		return finishTime;
	}

	@Override
	public int compareTo(LambdaJob other) {
		int diff = finishTime.compareTo(other.finishTime);
		return diff != 0 ? diff : startTime.compareTo(other.startTime);
	}
}
