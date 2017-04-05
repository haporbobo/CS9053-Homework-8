package edu.nyu.cs9053.homework8;
import java.time.*;

public class LambdaJobWeighted extends LambdaJob {

	private final int value;

	public LambdaJobWeighted(int jobID, LocalDate startTime, LocalDate finishTime, int value) {
		super(jobID, startTime, finishTime);
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}