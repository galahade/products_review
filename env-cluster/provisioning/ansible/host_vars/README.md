Configuration for Magento-MVP Ansible Executions
================================================

The configuration files in host_vars are used to define environment specific configuration, such as URLs and credentials.

We ship two sample files OOTB:

* dev-vagrant; contains the sample configuration for the development VM.  If this project has been branched from Magento MVP, then this file should be adjusted to match the projects' configuration.
* singlestack-cloud; contains the sample configuration for a runtime cloud image.  If this project has been branched from Magento MVP, then this file should be adjusted to match the projects' configuration.

It is expected that a configuration file will be created in this folder for each runtime server for a given project, the contents of these files will define the shape and configuration of the runtime servers.

Please see the README.md files for the individual rolebooks to know what configuration variables exist and what can be overridden in these host_var files.
