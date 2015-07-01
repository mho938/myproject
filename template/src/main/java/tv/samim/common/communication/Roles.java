package tv.samim.common.communication;

import javax.xml.bind.annotation.XmlElement;

public class Roles {
	@XmlElement
	long id;
	@XmlElement(required = true)
	String name;

	public long getId() {
		return id;
	}

	public String getName() { return name.toLowerCase(); }
}
