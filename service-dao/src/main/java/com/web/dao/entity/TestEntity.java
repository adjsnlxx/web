package com.web.dao.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "test", schema = "web_main_db", catalog = "")
public class TestEntity {
	private Long id;
	private Integer p1;
	private String p2;

	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "p1")
	public Integer getP1() {
		return p1;
	}

	public void setP1(Integer p1) {
		this.p1 = p1;
	}

	@Basic
	@Column(name = "p2")
	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TestEntity that = (TestEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(p1, that.p1) && Objects.equals(p2, that.p2);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, p1, p2);
	}
}
