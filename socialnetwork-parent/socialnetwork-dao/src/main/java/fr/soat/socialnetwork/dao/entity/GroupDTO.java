package fr.soat.socialnetwork.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="group")
public class GroupDTO extends AbstractEntity {

	private static final long serialVersionUID = 3740085943023289763L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	private Integer id;
	private String label;
	private String detail;
	
	@Override
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

}
