package com.next.jpatis.test.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Orders {
	@Id @Column Integer id;
	@Column String bpCode;
}
