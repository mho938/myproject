package tv.samim.common.communication;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Auhtor: maJid~ASGARI
 */
@XmlRootElement(name = "result")
public class ClientInfoResult {
    @XmlElement(name = "OS")
    String os;
    @XmlElement(name = "Type")
    String type;
    @XmlElement(name = "Browser")
    String browser;
    @XmlElement(name = "Version")
    String version;
}
