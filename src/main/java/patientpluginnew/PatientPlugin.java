package patientpluginnew;

import coreapplication.logging.AbstractPlugin;
import coreapplication.plugin.api.PluginInfo;
import org.slf4j.Logger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PatientPlugin extends AbstractPlugin {

    private static final String PLUGIN_ID = "patient-management";

    @Override
    public void initialize(PluginInfo info) {

        info.setId(PLUGIN_ID);
        super.initialize(info);

        getLogger().info("Patient management plugin initialized with ID: {}", PLUGIN_ID);
    }

    @Override
    public void start() {
        Logger logger = getLogger();
        logger.info("Starting patient management plugin");

        logger.info("Patient management plugin started successfully");
    }

    @Override
    public void stop() {
        Logger logger = getLogger();
        logger.info("Stopping patient management plugin");

        logger.info("Patient management plugin stopped successfully");
    }

    @Override
    public String getDescription() {
        return "A plugin for managing patient records with full CRUD operations";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String getAuthor() {
        return "Plugin Developer";
    }

    @Override
    public List<String> getDependencies() {
        // This plugin has no dependencies
        return Collections.emptyList();
    }

    @Override
    public List<String> getAuditableActions() {
        return Arrays.asList("USER_LOGIN", "USER_LOGOUT", "DATA_EXPORT", "USER_VIEW", "SETTINGS_CHANGE");
    }

    @Override
    public List<String> getAuditableResourceTypes() {
        return Arrays.asList("USER", "REPORT", "CONFIGURATION", "DATA");
    }
}