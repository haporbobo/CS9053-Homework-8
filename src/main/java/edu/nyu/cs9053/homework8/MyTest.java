package edu.nyu.cs9053.homework8;
import java.util.*;
import java.time.*;

public class MyTest {

	public static void main(String[] args){ 

		System.out.println("Testing LambdaScheduler... ");
		LambdaScheduler test1 = new LambdaScheduler();
		test1.getJobCandiates().add(new LambdaJob(1, LocalDate.of(2017, 3, 1), LocalDate.of(2017, 8, 1)));
		test1.getJobCandiates().add(new LambdaJob(2, LocalDate.of(2017, 1, 1), LocalDate.of(2017, 4, 1)));
		test1.getJobCandiates().add(new LambdaJob(3, LocalDate.of(2016, 12, 1), LocalDate.of(2017, 6, 1)));
		test1.getJobCandiates().add(new LambdaJob(4, LocalDate.of(2017, 3, 1), LocalDate.of(2017, 5, 1)));
		test1.getJobCandiates().add(new LambdaJob(5, LocalDate.of(2017, 5, 1), LocalDate.of(2017, 9, 1)));
		test1.getJobCandiates().add(new LambdaJob(6, LocalDate.of(2017, 6, 1), LocalDate.of(2017, 9, 1)));
		test1.getJobCandiates().add(new LambdaJob(7, LocalDate.of(2017, 4, 1), LocalDate.of(2017, 7, 1)));

		System.out.println("A list of jobs now we have: ");
		test1.printer(test1.getJobCandiates());

		LinkedList<LambdaJob> maxCompatibleSet = test1.computeMaxCompatibleSet();
		System.out.println("\nGet maximal compatible set(accepted jobs): ");
		test1.printer(maxCompatibleSet);

		System.out.println("\n\nTesting LambdaWeightedScheduler... ");
		LambdaWeightedScheduler test2 = new LambdaWeightedScheduler();
		test2.getJobCandiates().add(new LambdaJobWeighted(1, LocalDate.of(2017, 3, 1), LocalDate.of(2017, 8, 1), 120));
		test2.getJobCandiates().add(new LambdaJobWeighted(2, LocalDate.of(2017, 1, 1), LocalDate.of(2017, 4, 1), 100));
		test2.getJobCandiates().add(new LambdaJobWeighted(3, LocalDate.of(2016, 12, 1), LocalDate.of(2017, 6, 1), 30));
		test2.getJobCandiates().add(new LambdaJobWeighted(4, LocalDate.of(2017, 3, 1), LocalDate.of(2017, 5, 1), 50));
		test2.getJobCandiates().add(new LambdaJobWeighted(5, LocalDate.of(2017, 5, 1), LocalDate.of(2017, 9, 1), 60));
		test2.getJobCandiates().add(new LambdaJobWeighted(6, LocalDate.of(2017, 6, 1), LocalDate.of(2017, 9, 1), 80));
		test2.getJobCandiates().add(new LambdaJobWeighted(7, LocalDate.of(2017, 4, 1), LocalDate.of(2017, 7, 1), 70));
		System.out.println("A list of jobs now we have: ");
		test2.printer(test2.getJobCandiates());

		LinkedList<LambdaJobWeighted> optimalValueSet = test2.getOptimalValueSet();
		System.out.println("\nGet optimalValueSet: ");
		test2.printer(optimalValueSet);
		System.out.println("Optimal Total value: " + test2.computeValue(optimalValueSet));

	}
}