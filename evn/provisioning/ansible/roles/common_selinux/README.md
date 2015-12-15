Ansible Role For Common_Selinux playbook
=========================================

This ansible role will ensure the Python packages required to manage selinux are installed, and configure the target server to have the specified selinux policy applied.

Tasks performed by this rolebook:

* Install Python selinux packages
* Set selinux policies and state

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
selinux_state:
```
The state of selinux on the target server, this can be any of the following values:

* enforcing
* permissive
* disabled

This is defined in defaults (permissive) and can be overidden in host_vars as required.


```
selinux_policy:
```
The selinux policy to apply on the target server, this is required if selinux_state is set to enforcing.

This is defined in defaults (targeted) and can be overidden in host_vars as required.

Global Variables used by Rolebook
-------------------------------
These are global variables used by this rolebook, these are defined in /group_vars/all

