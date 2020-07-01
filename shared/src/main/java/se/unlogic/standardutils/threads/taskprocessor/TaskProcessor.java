package se.unlogic.standardutils.threads.taskprocessor;


public interface TaskProcessor<T> {

	public void processTask(T task);
}
