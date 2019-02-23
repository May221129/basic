package collection.b_treeset;
//Collection-set-TreeSet之定制排序素材
public class Man{
//	属性：
	private String name;
	private Integer id;
//	构造器：
	public Man(){
		super();
	}
	public Man(String name, Integer id) {
		super();
		this.name = name;
		this.id = id;
	}
//	方法：
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setAge(Integer age) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Man [name=" + name + ", id=" + id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Man other = (Man) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
