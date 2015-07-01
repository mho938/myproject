package tv.samim.common.communication;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Auhtor: maJid~ASGARI
 */
@XmlRootElement(name = "result")
public class GenreListResult {
    @XmlElement
    String status;

    public static class Genre {
        @XmlElement
        int id;
        @XmlElement
        String name;
        @XmlElement(name = "target_id")
        String targetId;
    }

    @XmlElement(name = "genres")
    ArrayList<Genre> genres = new ArrayList<>();
}
