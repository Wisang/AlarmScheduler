package ex.os.service;

public class FakeTimeService implements TimeService {

	@Override
	public int getCurrentMinute() {
		return 0;
	}

	@Override
	public int getCurrentDay() {
		return 0;
	}

	public void setTime(int day, int minute) {
		
	}

}
