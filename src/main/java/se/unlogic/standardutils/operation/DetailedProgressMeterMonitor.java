package se.unlogic.standardutils.operation;

import java.util.Calendar;
import java.util.Date;

import se.unlogic.standardutils.threads.ThreadUtils;
import se.unlogic.standardutils.time.MillisecondTimeUnits;
import se.unlogic.standardutils.time.TimeUtils;

public class DetailedProgressMeterMonitor extends ProgressMeterMonitor {

	public DetailedProgressMeterMonitor(ProgressMeter progressMeter, String logString, int sleepIntervalInMilliseconds, ProgressLogger monitorOutput) {
		super(progressMeter, logString, sleepIntervalInMilliseconds, monitorOutput);
	}

	@Override
	public void run() {

		long lastRun = System.currentTimeMillis();
		long lastPosition = progressMeter.getCurrentPosition();

		while (!abort) {

			ThreadUtils.sleep(sleepInterval);

			long now = System.currentTimeMillis();

			long timeSpent = progressMeter.getTimeSpent();

			String estimatedCompletion;

			if (timeSpent > 0) {

				// assume linear progress to estimate remaining time
				long totalMillis = (long) (timeSpent / (double) progressMeter.getProgress());
				int remainingSeconds = (int) ((totalMillis - progressMeter.getTimeSpent()) / 1000L);

				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.SECOND, remainingSeconds);

				estimatedCompletion = TimeUtils.TIME_SECONDS_FORMATTER.format(new Date(calendar.getTimeInMillis())) + " in " + TimeUtils.millisecondsToShortString(remainingSeconds * 1000L);

			} else {

				estimatedCompletion = "unknown timeSpent=0";
			}

			String digits;

			if (progressMeter.getStart() == progressMeter.getFinish()) {
				digits = "";

			} else {

				digits = Integer.toString((int) Math.ceil(Math.log10(Math.max(progressMeter.getStart(), progressMeter.getFinish()))));
			}

			double speed = ((10 * MillisecondTimeUnits.SECOND * (progressMeter.getCurrentPosition() - lastPosition)) / (now - lastRun)) / 10.0;

			int speedDecimals = 0;

			if (speed < 5) {
				speedDecimals = 1;
			}

			monitorOutput.logProgress(logMessage + String.format("%2d%% complete. %" + digits + "." + speedDecimals + "f/s, completed %" + digits + "d, %" + digits + "d remaining. Estimated completion at %s", progressMeter.getPercentComplete(), speed, progressMeter.getCurrentPosition(), progressMeter.getFinish() - progressMeter.getCurrentPosition(), estimatedCompletion));

			lastPosition = progressMeter.getCurrentPosition();
			lastRun = now;
		}
	}

}
