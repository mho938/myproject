package tv.samim.common.communication;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Auhtor: maJid~ASGARI and Nazemi
 */
@XmlRootElement(name = "result")
public class AuthenticationResult {

	@XmlElement
	String status;
	@XmlElement(required = true)
	String text;
	@XmlElement(required = false)
	String username;
	@XmlElement(required = true)
	List<Groups> groups;

	public List<Groups> getGroups() {
		return groups;
	}

	public String getStatus() {
		return status;
	}

	public String getText() {
		return text;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAthenticate() {
		return status.equals("successful");
	}
}
