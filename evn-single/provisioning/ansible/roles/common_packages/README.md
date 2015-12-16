Ansible Role For Common_Packages playbook
=========================================

This ansible role will install the common packages. 

Tasks performed by this rolebook:

* Install Common Packages

Required/related rolebooks:

None.

Pre-Requisites
--------------

None.

Specific Variables for Rolebook
-------------------------------
These are the variables in use in this rolebook, along with details on where they should be defined.

Expected entries supplied as --extra-vars for standard usage
------------------------------------------------------------

Expected entries in host_vars for standard usage
------------------------------------------------

Other variables used in this rolebook
-------------------------------------
These are the variables in use in this rolebook, along with details on where they should be defined.

```
common_package_list:
  - man
  - curl
  - ...
```
A list defining the common packages that need to be installed on the target server, this variable is defined in defaults.

Global Variables used by Rolebook
-------------------------------
These are global variables used by this rolebook, these are defined in /group_vars/all

