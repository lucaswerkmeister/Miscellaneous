package de.lucaswerkmeister.code.turing;

public abstract class TuringMachine extends Thread {
	private final Memory		memory;
	protected static final char	BLANK					= '☐';
	private boolean				wasStarted				= false;
	protected int				state;
	protected static int		DEBUG					= 0;
	protected static final int	DEBUG_PRINT_TRANSITIONS	= 1;
	protected static final int	DEBUG_PRINT_MEMORY		= 2;
	private char				written;
	private byte				movement				= 0;

	public TuringMachine(String input) {
		memory = new Memory(BLANK, input);
	}

	public void run() {
		wasStarted = true;
		state = 0;
		while (state != -1) {
			char read = memory.read();
			if ((DEBUG & DEBUG_PRINT_TRANSITIONS) != 0) {
				written = read;
				movement = 0;
				System.out.print("(" + state + "," + read + ") -> (");
			}
			simulate(read);
			if ((DEBUG & DEBUG_PRINT_TRANSITIONS) != 0) {
				System.out.println(state + "," + written + "," + movement + ")");
			}
			if ((DEBUG & DEBUG_PRINT_MEMORY) != 0) {
				System.out.println(memory.getContent());
			}
		}
	}

	protected abstract void simulate(char input);

	protected final void write(char c) {
		memory.write(c);
		written = c;
	}

	protected final void step(int direction) {
		movement = (byte) Integer.signum(direction);
		memory.step(movement);
	}

	public String getResult() throws IllegalAccessException {
		if (isAlive() || !wasStarted)
			throw new IllegalAccessException("Not yet finished");
		return memory.getContent().replaceAll("(^" + BLANK + "*)|(" + BLANK + "*$)", "");
	}
}