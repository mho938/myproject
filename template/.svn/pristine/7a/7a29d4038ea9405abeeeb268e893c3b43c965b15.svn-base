package tv.samim.template.database;

import org.apache.commons.io.FileUtils;
import org.springframework.util.Assert;
import tv.samim.common.ServerContextListener;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

/**
 * @author mojtaba khallash and AhmadReza Nazemi
 */
public class DatabaseHelper {

    @SuppressWarnings("static-access")
    public static final String dbName = System.getProperty("user.home")
            + "/.iptv/db/" + ServerContextListener.getServerName() + "/database";

    public File getFile() throws IOException {
        File file = new File(dbName);
        System.out.println(file.getAbsolutePath());
        return file;
    }

    public String getPath() {
        return "jdbc:mysql://localhost:3306/ServerDB?useUnicode=true&characterEncoding=UTF-8";
    }

    public DatabaseHelper(ServletContext servletContext) {
        Assert.notNull(servletContext, "DatabaseHelper Error: Cannot resolve ServletContextResource without ServletContext");

        if (!FileUtils.getFile(dbName).exists()) {
            File destFile = new File(dbName);
            destFile.mkdirs();
        }
    }
}
