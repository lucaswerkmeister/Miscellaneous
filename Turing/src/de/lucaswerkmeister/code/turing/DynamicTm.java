package de.lucaswerkmeister.code.turing;

import java.util.HashMap;
import java.util.Map;

import de.lucaswerkmeister.code.turing.DynamicTm.State.Transition;

public class DynamicTm extends TuringMachine {
	private final Map<Integer, Map<Character, Integer>>		toState;
	private final Map<Integer, Map<Character, Character>>	output;
	private final Map<Integer, Map<Character, Byte>>		movement;

	public DynamicTm(String input, State... states) {
		super(input);
		toState = new HashMap<>();
		output = new HashMap<>();
		movement = new HashMap<>();
		for (State state : states) {
			Map<Character, Integer> currentToState = new HashMap<>();
			Map<Character, Character> currentOutput = new HashMap<>();
			Map<Character, Byte> currentMovement = new HashMap<>();
			toState.put(state.id, currentToState);
			output.put(state.id, currentOutput);
			movement.put(state.id, currentMovement);
			for (Transition transition : state.transitions) {
				currentToState.put(transition.input, transition.toState);
				currentOutput.put(transition.input, transition.output);
				currentMovement.put(transition.input, transition.movement);
			}
		}
	}

	@Override
	protected void simulate(char input) {
		if (toState.get(state) == null || toState.get(state).get(input) == null) {
			state = -1;
			return;
		}
		int oldState = state;
		state = toState.get(oldState).get(input);
		write(output.get(oldState).get(input));
		step(movement.get(oldState).get(input));
	}

	public static class State {
		private final int			id;
		private final Transition[]	transitions;

		public State(int id, Transition... transitions) {
			this.id = id;
			this.transitions = transitions;
		}

		public static class Transition {
			private final char	input;
			private final int	toState;
			private final char	output;
			private final byte	movement;

			Transition(char input, int toState, char output, int movement) {
				this.input = input;
				this.toState = toState;
				this.output = output;
				this.movement = (byte) movement;
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		DynamicTm tm = new DynamicTm("aabbbb",
				new State(0, new Transition('a', 1, BLANK, 1),
						new Transition('b', 5, 'a', 1)),
				new State(1, new Transition('a', 1, 'a', 1),
						new Transition('b', 2, 'b', 1)),
				new State(2, new Transition('a', 3, 'a', -1),
						new Transition('b', 2, 'b', 1),
						new Transition(BLANK, 3, BLANK, -1)),
				new State(3, new Transition('b', 4, 'a', -1)),
				new State(4, new Transition('a', 4, 'a', -1),
						new Transition('b', 4, 'b', -1),
						new Transition(BLANK, 0, BLANK, 1)),
				new State(5, new Transition('a', 5, 'b', 1),
						new Transition('b', 5, 'a', 1),
						new Transition(BLANK, 4, BLANK, -1)));
		tm.start();
		tm.join();
		System.out.println(tm.getResult());
	}
}
