package tv.samim.common;

import tv.samim.template.utility.PathUtility;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * @author mojtaba khallash
 */
public class ServerContextListener implements ServletContextListener {
    private static String serverName = "";
    public static String getServerName() { return  serverName; }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();

        setupLogger(sc);
    }

    // <editor-fold defaultstate="collapsed" desc="setupLogger(ServletContext)">
    private void setupLogger(ServletContext sc) {
        PathUtility.localAddress = sc.getRealPath("/");
        if (!PathUtility.localAddress.endsWith(File.separator)) {
            PathUtility.localAddress += File.separator;
        }

        // Read pom information
        Properties prop = new Properties();
        try {
            prop.load(sc.getResourceAsStream("/WEB-INF/classes/build.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverName = prop.getProperty("server.name");
        String appVersion = prop.getProperty("release.version");


        // Initialize config
        //Config.init(serverName, appVersion);


        // Setup logger
        /*Logger.setLevel(Config.i().getLogLevel());
        VersionInformation.defineFileLocation(PathUtility.localAddress + "WEB-INF" + File.separator + "classes");
        if (Config.i().isShowLogViewer()) {
            Logger.showLogViewer(serverName);
        }
        Logger.setForceNumber(1);
        Logger.changeCategory("Server Start", Logger.ANSI_RED, true);
        Logger.normal("Start " + serverName + " Server...");
        KhorshidiCalendar releaseDate = new KhorshidiCalendar();
        releaseDate.setDay(VersionInformation.i().getReleaseDay());
        releaseDate.setMonth(VersionInformation.i().getReleaseMonth());
        releaseDate.setYear(VersionInformation.i().getReleaseYear());
        Logger.showApplicationIntro(VersionInformation.i().getServerName(),
                VersionInformation.i().getAuthor(), releaseDate, null,
                appVersion, VersionInformation.i().getReleaseChanges());     */
    }

    // </editor-fold>

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        //Logger.changeCategory("Server Terminate", Logger.ANSI_RED, true);
        //Logger.normal("Terminate " + serverName + " Server");
    }
}