Ansible Role For Common_Nmon playbook
=========================================

This ansible role will install the nmon binary. 

Tasks performed by this rolebook:

* Install NMON

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
nmon_binary_file:
```
Defines the binary Nmon file to transfer and install onto the target server, this variable needs to be defined in defaults.  This should map to a file stored in the "files" folder within the rolebook.

Global Variables used by Rolebook
-------------------------------
These are global variables used by this rolebook, these are defined in /group_vars/all

