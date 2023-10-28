public class Statistique {
	private int value;
	private int max;
	private final int palierMin;
	private final int palierMax;
	// Timer time;
	// Jauge jauge;
	
	public Statistique(int max, int palierMin, int palierMax) {
		this.max = max;
		value = max;
		this.palierMin = palierMin;
		this.palierMax = palierMax;
	}
	
	public int getValue() {
		return value;
	}
	public int getPalierMin() { return palierMin; }
	public int getPalierMax() { return palierMax; }
	
	public void setValue(int newValue) {
		value = newValue;
		if (value < 0)
			value = 0;
		else if (value > max)
			value = max;
	}
	
	public void add(int toAdd) {
		value += toAdd;
		if (value < 0)
			value = 0;
		else if (value > max)
			value = max;
	}
	
	public int toPercent() {
		return value / max * 100;
	}
}
