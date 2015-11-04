package ex.alarm.scheduler;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ex.alarm.driver.AlarmAlert;
import ex.os.service.TimeService;

public class SchedulerTest {
	private static final int MONDAY = 1;
	private static final int TUESDAY = 2;
	private Scheduler scheduler;
	private AlarmAlert alarmAlertSpy;
	private TimeService fakeTimeService;
	
	@Before
	public void setUp(){
		alarmAlertSpy = mock(AlarmAlert.class); //new AlarmAlertSpy();
		fakeTimeService = mock(TimeService.class);//new FakeTimeService();
		scheduler = new Scheduler(fakeTimeService, alarmAlertSpy);
	}

	@Test
	public void doesNotAlarmWhenNoScheduleAdded() throws Exception {
		scheduler.wakeUp();
		thenItshoudNotAlarm();
	}
	
	@Test
	public void doesNotAlarmWhenItsNotTheTimeYet() throws Exception {
		givenTheScheduleIsAddedAs(MONDAY, 7*60);
		whenItBecomesTimeAs(MONDAY, 6*60);
		thenItshoudNotAlarm();
	}
	
	@Test
	public void alarmWhenItsTime() throws Exception {
		givenTheScheduleIsAddedAs(MONDAY, 7*60);
		whenItBecomesTimeAs(MONDAY, 7*60);
		thenItShoudAlarm();
	}
	
	@Test
	public void doesNotAlarmWhenItsNotTheDay() throws Exception {
		givenTheScheduleIsAddedAs(MONDAY, 7*60);
		whenItBecomesTimeAs(TUESDAY, 7*60);
		thenItshoudNotAlarm();
	}

	private void whenItBecomesTimeAs(int day, int minute) {
		when(fakeTimeService.getCurrentDay()).thenReturn(day);
		when(fakeTimeService.getCurrentMinute()).thenReturn(minute);
		scheduler.wakeUp();
	}

	private void givenTheScheduleIsAddedAs(int day, int minute) {
		scheduler.add(day, minute);
	}
	
	private void thenItshoudNotAlarm() {
		verify(alarmAlertSpy, never()).startAlarm();
	}

	private void thenItShoudAlarm() {
		verify(alarmAlertSpy).startAlarm();
	}
}
