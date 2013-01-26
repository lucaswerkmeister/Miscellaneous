package de.lucaswerkmeister.code.turing;

public class GbiTm extends TuringMachine {
	private static final char	a	= 'a';
	private static final char	b	= 'b';

	public GbiTm(String input) {
		super(input);
	}

	@Override
	protected void simulate(char input) {
		switch (state) {
			case 0:
				switch (input) {
					case a:
						state = 1;
						write(BLANK);
						step(0);
						break;
					case b:
						state = 5;
						write(a);
						step(0);
						break;
					case BLANK:
						state = -1;
						return;
				}
				step(1);
				return;
			case 1:
				switch (input) {
					case a:
						state = 1;
						step(0);
						break;
					case b:
						state = 2;
						step(0);
						break;
					case BLANK:
						state = -1;
						return;
				}
				step(1);
				return;
			case 2:
				switch (input) {
					case a:
						state = 3;
						step(-1);
						return;
					case b:
						state = 2;
						step(1);
						return;
					case BLANK:
						state = 3;
						step(-1);
						return;
				}
			case 3:
				switch (input) {
					case a:
						state = -1;
						return;
					case b:
						state = 4;
						write(a);
						step(-1);
						return;
					case BLANK:
						state = -1;
						return;
				}
			case 4:
				switch (input) {
					case a:
						state = 4;
						step(-1);
						return;
					case b:
						state = 4;
						step(-1);
						return;
					case BLANK:
						state = 0;
						step(1);
						return;
				}
			case 5:
				switch (input) {
					case a:
						state = 5;
						write(b);
						step(1);
						return;
					case b:
						state = 5;
						write(a);
						step(1);
						return;
					case BLANK:
						state = 4;
						step(-1);
						return;
				}
		}
	}

	public static void main(String[] args) throws IllegalAccessException, InterruptedException {
		DEBUG = 0;
		DEBUG |= DEBUG_PRINT_MEMORY;
		// DEBUG |= DEBUG_PRINT_TRANSITIONS;
		GbiTm tm = new GbiTm("aabbbb");
		tm.start();
		tm.join();
		System.out.println(tm.getResult());
	}
}