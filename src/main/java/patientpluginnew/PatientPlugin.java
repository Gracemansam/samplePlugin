package patientpluginnew;

import coreapplication.plugin.api.Plugin;
import coreapplication.plugin.api.PluginInfo;

import java.util.Collections;
import java.util.List;

/**
 * Patient Management Plugin
 */
public class PatientPlugin implements Plugin {
    private PluginInfo pluginInfo;

    @Override
    public void initialize(PluginInfo info) {
        info.setId("patient-management");
        this.pluginInfo = info;
    }

    @Override
    public void start() {
        // Logic executed when the plugin is activated
        System.out.println("Patient management plugin started successfully");
    }

    @Override
    public void stop() {
        // Logic executed when the plugin is deactivated
        System.out.println("Patient management plugin stopped");
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
}