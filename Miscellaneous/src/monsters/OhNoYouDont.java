package monsters;

import java.io.PrintStream;
import java.io.PrintWriter;

public class OhNoYouDont extends Error {
	private static final long	serialVersionUID	= 7545700586789866397L;

	public OhNoYouDont() {
		throw this;
	}

	@Override
	public String getMessage() {
		System.err.println("Oh no you don't!");
		throw this;
	}

	@Override
	public String getLocalizedMessage() {
		throw this;
	}

	@Override
	public synchronized Throwable getCause() {
		throw this;
	}

	@Override
	public synchronized Throwable initCause(Throwable cause) {
		throw this;
	}

	@Override
	public String toString() {
		throw this;
	}

	@Override
	public void printStackTrace() {
		super.fillInStackTrace(); // for debuggers
		super.getStackTrace();
		throw this;
	}

	@Override
	public void printStackTrace(PrintStream s) {
		throw this;
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		throw this;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		// throw this; // if we disable this method, the exception can't even be thrown
		return super.fillInStackTrace();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		throw this;
	}

	@Override
	public void setStackTrace(StackTraceElement[] stackTrace) {
		throw this;
	}

	@Override
	public int hashCode() {
		throw this;
	}

	@Override
	public boolean equals(Object obj) {
		throw this;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw this;
	}

	@Override
	protected void finalize() throws Throwable {
		System.err.println("NOOOPE");
		throw this;
	}
}
