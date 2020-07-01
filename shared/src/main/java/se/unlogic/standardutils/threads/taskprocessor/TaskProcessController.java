package se.unlogic.standardutils.threads.taskprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import se.unlogic.standardutils.operation.ProgressMeter;

public class TaskProcessController<T> {

	private final ProgressMeter progressMeter;
	
	private boolean abort;
	
	private final ListIterator<T> listIterator;
	
	private final List<TaskProcessThread<T>> threads;
	
	private List<Throwable> errors;
	
	public TaskProcessController(String name, List<T> tasks, TaskProcessor<T> processor, int maxThreads) {
		
		progressMeter = new ProgressMeter(0, tasks.size());
		
		this.listIterator = tasks.listIterator();

		if(tasks.size() < maxThreads) {
			
			maxThreads = tasks.size();
		}
		
		threads = new ArrayList<TaskProcessThread<T>>(maxThreads);
		
		for(int i = 0; i < maxThreads; i++) {
			
			TaskProcessThread<T> thread = new TaskProcessThread<T>(name + "-" + i, this, processor, progressMeter);
			
			threads.add(thread);
			
			thread.start();
		}
	}
	
	public void abortProcessing(boolean intrerrupt) {
		
		this.abort = true;
		
		if(intrerrupt) {
			
			for(TaskProcessThread<T> thread : threads) {

				thread.interrupt();
			}
		}
	}
	
	public boolean isAborted() {
		
		return abort;
	}	
	
	protected synchronized T getNextTask() {
		
		if(listIterator.hasNext()) {
			
			return listIterator.next();
		}
		
		return null;
	}
	
	protected synchronized void addError(Throwable e) {
		
		if(errors == null) {
			
			errors = new ArrayList<Throwable>();
		}
		
		errors.add(e);
	}
	
	public List<Throwable> getErrors(){
		
		return errors;
	}
	
	public ProgressMeter getProgressMeter() {
		
		return progressMeter;
	}
	
	public boolean isStopped() {
		
		for(Thread thread : threads) {
			
			if(thread.isAlive()) {
				
				return false;
			}
		}
		
		return true;
	}
}
