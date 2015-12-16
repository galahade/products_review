Group Variables for Magento-MVP Ansible Executions
==================================================

The configuration files in group_vars are used to define rolebook specific configuration.  As a general rule we don't do configuration at this level, chosing to use either rolebook defaults or host_vars.

There is one exception to this, the "all" file which defines global variables used by all rolebooks.  This contains paths that are common across all rolebooks.

Other than "all", we deliver placeholder group_var files for each rolebook, however these contain no content.