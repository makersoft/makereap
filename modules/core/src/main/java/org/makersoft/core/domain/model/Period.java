/*
* @(#)Period.java 2012-5-8 下午10:17:04
*
* Copyright (c) 2011-2012 Makersoft.org all rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
*
*/
package org.makersoft.core.domain.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 时间范围(左闭右开)
 */
@Embeddable
public class Period implements Serializable {

	private static final long serialVersionUID = -3374887172570339193L;

	/**
	 * 开始时间
	 */
	@Column(name = "PERIOD_START")
	private Date start;

	/**
	 * 结束时间
	 */
	@Column(name = "PERIOD_END")
	private Date end;

	public Period() {
		super();
	}

	public Period(Date start, Date end) {
		super();
		if (end != null && start != null && end.compareTo(start) < 0) {
			throw new IllegalArgumentException("start:" + start + ", end:" + end + ", 结束时间应该大于起始时间！");
		}
		this.start = start;
		this.end = end;
	}

	/**
	 * 当天 00:00:00 - 23:59:59
	 *
	 * @return
	 */
	public static Period today() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date start = cal.getTime();

		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MILLISECOND, -1);
		Date end = cal.getTime();
		Period period = new Period(start, end);

		return period;
	}

	/**
	 * 交集
	 */
	public Period intersection(final Period other) {
		if (other == null)
			return null;

		Period result = null;
		if (overlap(other)) {
			Date start_ = this.start.after(other.start) ? this.start : other.start;
			Date end_ = this.end.after(other.end) ? other.end : this.end;
			result = new Period(start_, end_);
		}

		return result;
	}

	/**
	 * 并集
	 */
	public Period union(final Period other) {
		if (other == null)
			return this;

		Period result = null;
		if (overlap(other)) {
			Date start_ = this.start.before(other.start) ? this.start : other.start;
			Date end_ = this.end.before(other.end) ? other.end : this.end;
			result = new Period(start_, end_);
		}

		return result;
	}

	/**
	 * 是否包含
	 */
	public boolean isIncluded(final Date date) {
		if (date == null)
			return false;

		if (start == null && end == null)
			return true;
		else if (start == null)
			return (end.after(date) || end.equals(date));
		else if (end == null)
			return (start.before(date) || start.equals(date));
		else
			return (start.before(date) || start.equals(date)) && (end.after(date) || end.equals(date));
	}

	/**
	 * 是否包含
	 */
	public boolean isIncluded(final Period period) {
		return this.isIncluded(period.start) && this.isIncluded(period.end);
	}

	/**
	 * 是否相交
	 */
	public boolean overlap(final Period period) {
		return isIncluded(period.start) || period.isIncluded(this.start);
	}

	/**
	 * 起始时间差
	 */
	public long duration() {
		return this.start.getTime() - this.end.getTime();
	}

	/**
	 * 是否过期
	 */
	public boolean isExpired(Date date) {
		return this.isIncluded(date);
	}

	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "Period[" + df.format(start) + " to " + df.format(end) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Period other = (Period) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

}
