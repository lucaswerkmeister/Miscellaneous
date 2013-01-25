package de.lucaswerkmeister.code.turing;

public class GbiTm extends TuringMachine {
	private static final char a = 'a';
	private static final char b = 'b';

	public GbiTm(String input) {
		super(input);
	}

	@Override
	protected void step(char input) {
		switch (state) {
		case 0:
			switch (input) {
			case a:
				state = 1;
				memory.write(BLANK);
				break;
			case b:
				state = 5;
				memory.write(a);
				break;
			case BLANK:
				state = -1;
				return;
			}
			memory.stepRight();
			return;
		case 1:
			switch (input) {
			case a:
				state = 1;
				break;
			case b:
				state = 2;
				break;
			case BLANK:
				state = -1;
				return;
			}
			memory.stepRight();
			return;
		case 2:
			switch (input) {
			case a:
				state = 3;
				memory.stepLeft();
				return;
			case b:
				state = 2;
				memory.stepRight();
				return;
			case BLANK:
				state = 3;
				memory.stepLeft();
				return;
			}
		case 3:
			switch (input) {
			case a:
				state = -1;
				return;
			case b:
				state = 4;
				memory.write(a);
				memory.stepLeft();
				return;
			case BLANK:
				state = -1;
				return;
			}
		case 4:
			switch (input) {
			case a:
				state = 4;
				memory.stepLeft();
				return;
			case b:
				state = 4;
				memory.stepLeft();
				return;
			case BLANK:
				state = 0;
				memory.stepRight();
				return;
			}
		case 5:
			switch (input) {
			case a:
				state = 5;
				memory.write(b);
				memory.stepRight();
				return;
			case b:
				state = 5;
				memory.write(a);
				memory.stepRight();
				return;
			case BLANK:
				state = 4;
				memory.stepLeft();
				return;
			}
		}
	}

	public static void main(String[] args) throws IllegalAccessException, InterruptedException {
		GbiTm tm = new GbiTm("aabbbb");
		tm.start();
		tm.join();
		System.out.println(tm.getResult());
	}
}