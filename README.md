Remove Prefix From Field Names
====================================

Pipeline Processor Function for Graylog

I created this plugin when I was sending Windows Event Logs in JSON representation into Graylog. There was a lot of nested fields and therefore, Graylog created field names like for example `Event_System_EventID`. Instead of renaming each field separately using `rename_field(oldName, newName)`, I created this plugin to remove the given prefix from all field names which start with that prefix.  

Installation
-------------------------

This project is using Maven and requires Java 17 or higher.

* Clone this repository.
* Run `mvn package` to build a JAR file.
* Copy generated jar file in target directory to your Graylog server plugin directory.
* Restart the Graylog server.

Usage
-------------------------

Example pipeline rule: 
```
rule "Rename nested fields"
when
    (has_field("forensic_artifact") && to_string($message."forensic_artifact") == "windows_event_log")
then
    field_names_remove_prefix("Event_System_");
    field_names_remove_prefix("Event_EventData_");
end
```
