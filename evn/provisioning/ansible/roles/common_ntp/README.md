Ansible Role For Common_Ntp playbook
=========================================

This ansible role will install NTP

Tasks performed by this rolebook:

* Install ntp
* Configure the NTP daemon to use UK NTP servers
* (if required) Add IPTables rules for NTP

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
enable_iptables_ntp: true
```
Boolean flag determining whether or not to configure IPTables for NTP.  This is defined in defaults and set to true, if IPTables is not required override this in host_vars.

Global Variables used by Rolebook
-------------------------------
These are global variables used by this rolebook, these are defined in /group_vars/all

