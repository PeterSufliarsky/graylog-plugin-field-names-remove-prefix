package sk.sufliarsky.peter.graylog;

import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.ServerStatus;
import org.graylog2.plugin.Version;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

/**
 * Implement the PluginMetaData interface here.
 */
public class FieldNamesRemovePrefixFunctionMetadata implements PluginMetaData {
    private static final String PLUGIN_PROPERTIES =
            "sk.sufliarsky.peter.graylog.graylog-plugin-field-names-remove-prefix/graylog2-plugin.properties";

    @Override
    public String getUniqueId() {
        return "sk.sufliarsky.peter.graylog.FieldNamesRemovePrefixFunctionPlugin";
    }

    @Override
    public String getName() {
        return "Remove prefix from field names pipeline function";
    }

    @Override
    public String getAuthor() {
        return "Peter Sufliarsky";
    }

    @Override
    public URI getURL() {
        return URI.create("https://github.com/PeterSufliarsky/graylog-plugin-field-names-remove-prefix");
    }

    @Override
    public Version getVersion() {
        return Version.fromPluginProperties(getClass(), PLUGIN_PROPERTIES, "version", Version.from(1, 0, 0));
    }

    @Override
    public String getDescription() {
        return "Pipeline function that removes the specified prefix from field names.";
    }

    @Override
    public Version getRequiredVersion() {
        return Version.fromPluginProperties(getClass(), PLUGIN_PROPERTIES, "graylog.version", Version.from(5, 0, 0));
    }

    @Override
    public Set<ServerStatus.Capability> getRequiredCapabilities() {
        return Collections.emptySet();
    }
}
