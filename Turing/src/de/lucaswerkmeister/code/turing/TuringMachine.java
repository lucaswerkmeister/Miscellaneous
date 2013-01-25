package de.lucaswerkmeister.code.turing;

public abstract class TuringMachine extends Thread {
	private final Memory memory;
	protected static final char BLANK = '☐';
	private boolean wasStarted = false;
	protected int state;
	private static final boolean DEBUG = true;

	public TuringMachine(String input) {
		memory = new Memory(BLANK, input);
	}

	public void run() {
		wasStarted = true;
		state = 0;
		while (state != -1) {
			char read = memory.read();
			System.out.print("(");
			System.out.print(state);
			System.out.print(",");
			System.out.print(read);
			System.out.print(") -> (");
			simulate(read);
		}
	}

	protected abstract void simulate(char input);

	protected final void write(char c) {
		memory.write(c);
	}

	protected final void step(int direction) {
		memory.step((byte) Integer.signum(direction));
	}

	public String getResult() throws IllegalAccessException {
		if (isAlive() || !wasStarted)
			throw new IllegalAccessException("Not yet finished");
		return memory.getContent().replaceAll("(^" + BLANK + "*)|(" + BLANK + "*$)", "");
	}
}