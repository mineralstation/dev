package com.osgi.example1.schedule;

import java.util.Timer;

public class ExecuteTimer {

	public static void main(String[] args) {
		TimerExample task0 = new TimerExample("Task0");
		TimerExample task1 = new TimerExample("Task1");
		TimerExample task2 = new TimerExample("Task2");

		// param1: task task to be scheduled.
		// param2: delay delay in milliseconds before task is to be executed.
		// param3: period time in milliseconds between successive task executions.
		Timer timer = new Timer();
		timer.schedule(task0, 0);
		timer.scheduleAtFixedRate(task1, 10 * 1000, 10 * 1000); // delay 10 seconds; repeat every 10 seconds
		timer.scheduleAtFixedRate(task2, 11 * 1000, 2 * 1000); // delay 11 seconds; repeat every 2 second

		if (task1 != null) {
			// task1.cancel();
		}
	}

}
