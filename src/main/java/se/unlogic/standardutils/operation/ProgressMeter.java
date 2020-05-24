/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.operation;

import se.unlogic.standardutils.time.MillisecondTimeUnits;
import se.unlogic.standardutils.time.TimeUtils;

public class ProgressMeter implements ProgressListener {

	private long start;
	private long finish;
	private long currentPosition;
	private long startTime;
	private long endTime;

	public ProgressMeter() {};

	public ProgressMeter(long start, long finish) {
		this.start = start;
		this.finish = finish;
		this.setStartTime();
	}

	public ProgressMeter(int start, int finish, int currentPosition) {
		this.start = start;
		this.finish = finish;
		this.currentPosition = currentPosition;
	}

	public long getCurrentPosition() {

		return currentPosition;
	}

	public synchronized void setCurrentPosition(int currentPosition) {

		this.currentPosition = currentPosition;
	}

	@Override
	public synchronized void incrementCurrentPosition() {

		this.currentPosition++;
	}

	public synchronized void decrementCurrentPosition() {

		this.currentPosition--;
	}

	public long getFinish() {

		return finish;
	}

	public void setFinish(long finish) {

		this.finish = finish;
	}

	public long getStart() {

		return start;
	}

	public void setStart(long start) {

		this.start = start;
	}

	public Float getProgress() {

		if (finish > start) {
			return (float) (currentPosition - start) / (float) (finish - start);

		} else if (finish < start) {
			return (float) (start - currentPosition) / (float) (start - finish);
		}

		return null;
	}

	public int getPercentComplete() {

		Float progress = getProgress();

		if (progress == null) {
			return -1;
		}

		return (int) (progress * 100f);
	}

	public int getPercentRemaining() {

		int percent = getPercentComplete();

		if (percent >= 0) {

			return 100 - percent;
		}

		return percent;
	}

	public long getIntervalSize() {

		if (finish > start) {
			return finish - start;

		} else if (finish < start) {
			return start - finish;

		} else {
			return 0;
		}
	}

	public long getStartTime() {

		return startTime;
	}

	public void setStartTime() {

		this.startTime = System.currentTimeMillis();
	}

	public void setStartTime(long startTime) {

		this.startTime = startTime;
	}

	public long getEndTime() {

		return endTime;
	}

	public void setEndTime() {

		this.endTime = System.currentTimeMillis();
	}

	public void setEndTime(long endTime) {

		this.endTime = endTime;
	}

	public long getTimeSpent() {

		if (startTime != 0) {

			if (endTime == 0) {
				return System.currentTimeMillis() - startTime;

			} else {
				return endTime - startTime;
			}

		} else {
			return 0;
		}
	}

	@Override
	public synchronized void incrementCurrentPosition(long value) {

		this.currentPosition += value;
	}

	public String getTimeSpentString() {

		return TimeUtils.millisecondsToString(getTimeSpent());
	}

	public long getAverageSpeed() {

		if (getTimeSpent() == 0) {
			return 0;
		}

		return Math.abs(MillisecondTimeUnits.SECOND * (currentPosition - start)) / getTimeSpent();
	}
}
