package br.example.entity.postgres;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lei", schema = "pessoal")
public class Law {

	@Id
	@Column(name = "id_lei")
	private Long id;

	@Column(name = "ds_lei")
	private String dsLaw;


	public String getDsLaw() {
		return dsLaw;
	}

	public void setDsLaw(String dsLaw) {
		this.dsLaw = dsLaw;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Law other = (Law) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
