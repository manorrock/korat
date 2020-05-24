package se.unlogic.standardutils.threads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;


public class PriorityThreadFactory implements ThreadFactory {

    final ThreadGroup group;
    final AtomicInteger threadNumber = new AtomicInteger(1);
    final String prefix;
    final int priority;
    
    public PriorityThreadFactory(int priority, String prefix) {
    	
        SecurityManager s = System.getSecurityManager();
        group = (s != null)? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.prefix = prefix + "-thread-";
        this.priority = priority;
    }

    @Override
	public Thread newThread(Runnable r) {
        
    	Thread thread = new Thread(group, r, prefix + threadNumber.getAndIncrement(), 0);
        
    	if (thread.isDaemon()) {
			thread.setDaemon(false);
		}
        
    	if (thread.getPriority() != priority) {
			thread.setPriority(priority);
		}
    	
        return thread;
    }
}
