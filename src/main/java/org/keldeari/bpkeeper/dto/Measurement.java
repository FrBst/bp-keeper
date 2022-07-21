package org.keldeari.bpkeeper.dto;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "measurements")
public class Measurement {
	
	@Id
	@Column(name = "user_id")
	private long userId;

	@Column(name = "pressure_sys")
	private short pressureSys;

	@Column(name = "pressure_dia")
	private short pressureDia;

	@Column(name = "pulse")
	private short pulse;

	@Column(name = "datetime")
	private Instant datetime;

	@Column(name = "note")
	private String note;
}
