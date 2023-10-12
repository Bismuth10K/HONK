public class Statistiques {
	private int value;
	private int max;
	private final int palierMin;
	private final int palierMax;
	// Timer time;
	// Jauge jauge;
	
	public Statistiques(int max, int palierMin, int palierMax) {
		this.max = max;
		value = max;
		this.palierMin = palierMin;
		this.palierMax = palierMax;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int newValue) {
		value = newValue;
	}
	
	public void setMax(int newMax) {
		max = newMax;
	}
	
	public int toPercent() {
		return value / max * 100;
	}
	
	
}
