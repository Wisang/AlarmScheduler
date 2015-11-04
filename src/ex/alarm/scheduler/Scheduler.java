package ex.alarm.scheduler;

import ex.alarm.driver.AlarmAlert;
import ex.os.service.TimeService;

public class Scheduler {

	private TimeService timeService;
	private AlarmAlert alarmAlert;
	private int day;
	private int minute;
	private boolean isScheduled = false;
	
	public Scheduler(TimeService timeService,
			AlarmAlert alarmAlert) {
		this.timeService = timeService;
		this.alarmAlert = alarmAlert;
	}

	public void wakeUp() {
		// if schedule is not added then return;
		if(!isScheduled )
			return;
		
		if(timeService.getCurrentDay() == day &&
				timeService.getCurrentMinute() == minute)
			alarmAlert.startAlarm();
	}

	public void add(int day, int minute) {
		isScheduled = true;
		this.day = day;
		this.minute = minute;
	}

}
