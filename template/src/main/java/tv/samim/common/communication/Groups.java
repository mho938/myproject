package tv.samim.common.communication;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Groups {
	@XmlElement
	long id;
	@XmlElement(required = true)
	String name;
	@XmlElement(required = true)
	List<Roles> roles;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Roles> getRoles() {
		return roles;
	}
}
