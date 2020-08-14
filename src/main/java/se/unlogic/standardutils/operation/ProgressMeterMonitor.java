package se.unlogic.standardutils.operation;

import se.unlogic.standardutils.threads.ThreadUtils;

public class ProgressMeterMonitor implements Runnable {

	protected final ProgressLogger monitorOutput;
	protected final ProgressMeter progressMeter;
	protected String logMessage;
	protected final int sleepInterval;

	protected boolean abort;

	public ProgressMeterMonitor(ProgressMeter progressMeter, String logString, int sleepIntervalInMilliseconds, ProgressLogger monitorOutput) {

		super();
		this.progressMeter = progressMeter;
		this.logMessage = logString;
		this.sleepInterval = sleepIntervalInMilliseconds;
		this.monitorOutput = monitorOutput;
	}

	@Override
	public void run() {

		while (!abort) {

			ThreadUtils.sleep(sleepInterval);

			monitorOutput.logProgress(logMessage + String.format("%2d%% complete (" + progressMeter.getCurrentPosition() + "/" + progressMeter.getFinish() + ").", progressMeter.getPercentComplete()));
		}
	}

	public boolean isAbort() {

		return abort;
	}

	public void abort() {

		this.abort = true;
	}

	public String getLogMessage() {

		return logMessage;
	}

	public void setLogMessage(String logMessage) {

		this.logMessage = logMessage;
	}
}
