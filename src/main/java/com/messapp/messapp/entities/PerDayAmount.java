package com.messapp.messapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="per_day_amount")
public class PerDayAmount {
	@Id
	@Column(name="per_day_amount_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messTypeId;
	@Column(name="per_day_morning")
	private Double perDayMorning;
	@Column(name="per_day_evening")
	private Double perDayEvening;
	@Column(name="per_day_both")
	private Double perDayBoth;
	
	public Long getMessTypeId() {
		return messTypeId;
	}
	public void setMessTypeId(Long messTypeId) {
		this.messTypeId = messTypeId;
	}
	public Double getPerDayMorning() {
		return perDayMorning;
	}
	public void setPerDayMorning(Double perDayMorning) {
		this.perDayMorning = perDayMorning;
	}
	public Double getPerDayEvening() {
		return perDayEvening;
	}
	public void setPerDayEvening(Double perDayEvening) {
		this.perDayEvening = perDayEvening;
	}
	public Double getPerDayBoth() {
		return perDayBoth;
	}
	public void setPerDayBoth(Double perDayBoth) {
		this.perDayBoth = perDayBoth;
	}
	
}
