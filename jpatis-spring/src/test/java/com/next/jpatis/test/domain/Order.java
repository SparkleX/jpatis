package com.next.jpatis.test.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="[Order]")
public class Order {
	@Id @Column Integer id;
	@Column String bpCode;
}
