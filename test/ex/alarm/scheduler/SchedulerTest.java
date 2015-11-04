package ex.alarm.scheduler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ex.alarm.driver.AlarmAlertSpy;
import ex.os.service.FakeTimeService;

public class SchedulerTest {
	private static final int MONDAY = 1;
	private Scheduler scheduler;
	private AlarmAlertSpy alarmAlertSpy;
	private FakeTimeService fakeTimeService;
	
	@Before
	public void setUp(){
		scheduler = new Scheduler();
		alarmAlertSpy = new AlarmAlertSpy();
		fakeTimeService = new FakeTimeService();
	}

	@Test
	public void doesNotAlarmWhenNoScheduleAdded() throws Exception {
		scheduler.wakeUp();
		assertFalse(alarmAlertSpy.isAlarmed());
	}
	
	@Test
	public void doesNotAlarmWhenItsNotTheTimeYet() throws Exception {
		scheduler.add(MONDAY, 7*60);
		fakeTimeService.setTime(MONDAY, 6*60);
		scheduler.wakeUp();
		assertFalse(alarmAlertSpy.isAlarmed());
	}
	
	@Test
	public void alarmWhenItsTime() throws Exception {
		scheduler.add(MONDAY, 7*60);
		fakeTimeService.setTime(MONDAY, 7*60);
		scheduler.wakeUp();
		assertTrue(alarmAlertSpy.isAlarmed());
	}
}
