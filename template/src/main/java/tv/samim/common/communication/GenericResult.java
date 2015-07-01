package tv.samim.common.communication;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Auhtor: maJid~ASGARI
 */
@XmlRootElement(name = "result")
public class GenericResult {
	@XmlElement
	String status;
	@XmlElement
	String text;

	public String getStatus() {
		return status;
	}

	public String getText() {
		return text;
	}

	public void setSuccessful(String text) {
		this.status = "successful";
		this.text = text;
	}

	public void setFail(String text) {
		this.status = "fail";
		this.text = text;
	}
}
