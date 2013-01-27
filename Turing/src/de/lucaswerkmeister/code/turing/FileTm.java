package de.lucaswerkmeister.code.turing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import de.lucaswerkmeister.code.turing.DynamicTm.State.Transition;

public class FileTm extends DynamicTm {

	public FileTm(String input, String fileName) throws FileNotFoundException, IOException {
		this(input, new File(fileName));
	}

	public FileTm(String input, File file) throws FileNotFoundException, IOException {
		super(input, readStates(file));
	}

	private static State[] readStates(File file) throws FileNotFoundException, IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			List<State> states = new LinkedList<>();
			List<Transition> transitions = new LinkedList<>();
			String line = reader.readLine();
			int currentId = Integer.parseInt(line);
			while (line != null) {
				if (line.startsWith("\t"))
				{
					String[] halves = line.split("->");
					char input = halves[0].trim().charAt(0);
					String[] rightHalf = halves[1].trim().split(",");
					int toState = Integer.parseInt(rightHalf[0]);
					char output = rightHalf[1].charAt(0);
					byte movement = Byte.parseByte(rightHalf[2]);
					transitions.add(new Transition(input, toState, output, movement));
				}
				else {
					states.add(new State(currentId, transitions.toArray(new Transition[transitions.size()])));
					transitions.clear();
					currentId = Integer.parseInt(line);
				}
				line = reader.readLine();
			}
			states.add(new State(currentId, transitions.toArray(new Transition[transitions.size()])));
			return states.toArray(new State[states.size()]);
		}
	}

	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
		FileTm tm;
		if (args.length == 0)
			tm = new FileTm("aabbbb", "GbiTm.jtm");
		else if (args.length == 2)
			tm = new FileTm(args[1], args[0]);
		else
			throw new IllegalArgumentException("Not enough or too many arguments! Usage: FileTm <input> <filename>");
		tm.start();
		tm.join();
		System.out.println(tm.getResult());
	}
}
