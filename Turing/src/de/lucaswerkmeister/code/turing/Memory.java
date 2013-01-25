package de.lucaswerkmeister.code.turing;

public class Memory {
	private final char blank;
	private Entry currentEntry;

	public Memory(char blank) {
		this.blank = blank;
		currentEntry = new Entry(null, null, blank);
	}

	public Memory(char blank, String initialContent) {
		this.blank = blank;
		Entry firstEntry = currentEntry = new Entry(null, null, blank);
		for (char c : initialContent.toCharArray()) {
			write(c);
			stepRight();
		}
		currentEntry = firstEntry;
	}

	public char read() {
		return currentEntry.content;
	}

	public void write(char content) {
		currentEntry.content = content;
	}

	public void stepRight() {
		if (currentEntry.next == null)
			currentEntry.next = new Entry(currentEntry, null, blank);
		currentEntry = currentEntry.next;
	}

	public void stepLeft() {
		if (currentEntry.previous == null)
			currentEntry.previous = new Entry(null, currentEntry, blank);
		currentEntry = currentEntry.previous;
	}

	public void step(byte direction) {
		if (direction < 0)
			stepLeft();
		else if (direction > 0)
			stepRight();
	}

	public String getContent() {
		Entry firstEntry = currentEntry;
		while (firstEntry.previous != null)
			firstEntry = firstEntry.previous;
		StringBuilder content = new StringBuilder();
		content.append(firstEntry.content);
		while (firstEntry.next != null) {
			firstEntry = firstEntry.next;
			content.append(firstEntry.content);
		}
		return content.toString();
	}

	private class Entry {
		Entry previous;
		Entry next;
		char content;

		Entry(Entry previous, Entry next, char content) {
			this.previous = previous;
			this.next = next;
			this.content = content;
		}
	}
}