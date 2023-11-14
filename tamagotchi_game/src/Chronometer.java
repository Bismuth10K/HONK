import java.lang.Math;

import static java.lang.Math.round;

public final class Chronometer {
	private long begin, end;
	
	public void start() {
		begin = System.currentTimeMillis();
	}
	
	public void stop() {
		end = System.currentTimeMillis();
	}
	
	public long getTime() {
		return end - begin;
	}
	
	public long getMilliseconds() {
		return end - begin;
	}
	
	public double getSeconds() { return round((end - begin) / 1000.0); }
	
	public double getMinutes() {
		return round((end - begin) / 60000.0);
	}
	
	public double getHours() {
		return round((end - begin) / 3600000.0);
	}
	
	public String toString(){
		stop();
		String res = getHours() + "h:" + getMinutes() + "min:" + getSeconds() + "s";
		return res;
	}
}