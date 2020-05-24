package se.unlogic.standardutils.threads.taskprocessor;

import se.unlogic.standardutils.operation.ProgressMeter;

public class TaskProcessThread<T> extends Thread {

	private final TaskProcessController<T> controller;
	private final TaskProcessor<T> processor;
	private final ProgressMeter progressMeter;

	public TaskProcessThread(String name,TaskProcessController<T> controller, TaskProcessor<T> processor, ProgressMeter progressMeter) {

		super(name);
		this.controller = controller;
		this.processor = processor;
		this.progressMeter = progressMeter;
	}

	@Override
	public void run() {

		T task = controller.getNextTask();
		
		while(task != null) {
			
			try {
				processor.processTask(task);
				
			}catch (Throwable e) {
				
				controller.addError(e);
				
			}finally {
			
				progressMeter.incrementCurrentPosition();
			}
			
			if(controller.isAborted()) {
				
				break;
			}
			
			task = controller.getNextTask();
		}
	}
}
