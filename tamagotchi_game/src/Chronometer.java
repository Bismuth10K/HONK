import static java.lang.Math.floor;

public final class Chronometer {
	private long begin, end;
	private final int cheat_vit;
	
	public Chronometer(int cheat) {
		begin = System.currentTimeMillis();
		cheat_vit = cheat;
	}
	
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
	
	public long getSeconds() { return (long)(floor((end - begin) * cheat_vit / 1000.0))  % 60; }
	
	public long getMinutes() {
		return (long)(floor((end - begin) * cheat_vit / 60000.0)) % 60;
	}
	
	public long getHours() {
		return (long)floor((end - begin) * cheat_vit / 3600000.0);
	}
	
	public String toString() {
		stop();
		return getHours() + ":" + getMinutes() + ":" + getSeconds();
	}
	
	public long getTimeStats(long lastUpdatedStats) {
		stop();
		return end - lastUpdatedStats;
	}
	
	/**
	 * Retourne le temps au'il sera dans X secondes, X etant le temps passe en parametre
	 * @param seconds int
	 * @return
	 */
	/*public long getTimeFromNow(long seconds) {
		return begin + seconds * 1000;
	} */
}