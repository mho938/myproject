package tv.samim.template.model.global;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mojtaba khallash
 */
@XmlRootElement
public class DataSourceResult {

    private long total;

    private List<?> data;

    private Map<String, Object> aggregates;
    private HashMap<String, Error> errors;
    @XmlElement
    public HashMap<String, Error> getErrors() {
        return errors;
    }
    Error err = new Error();

    public void addError(String message) {
        if (this.errors == null)
            this.errors = new HashMap<String, Error>();
        err.addErrors(message);
        this.errors.put("address", err);

    }

    public void setErrors(HashMap<String, Error> errors) {
        this.errors = errors;
    }
    @XmlElement
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
    @XmlElement
    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
    @XmlElement
    public Map<String, Object> getAggregates() {
        return aggregates;
    }

    public void setAggregates(Map<String, Object> aggregates) {
        this.aggregates = aggregates;
    }

    public static class Error {
        public List<String> errors;

        public List<String> getErrors() {
            if (errors == null)
                errors = new ArrayList<String>();
            return errors;
        }

        public void addErrors(String err) {
            getErrors().add(err);
        }

    }
}