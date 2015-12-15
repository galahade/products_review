Ansible Role For Common_Hosts playbook
=========================================

This ansible role will create entries in /etc/hosts 

Tasks performed by this rolebook:

* Create entries in /etc/hosts

Required/related rolebooks:

None.

Pre-Requisites
--------------

This rolebook is designed to be used with entries defined in hosts_vars for a given target server, by default no HOSTS entries will be created unless explicitly defined.

Specific Variables for Rolebook
-------------------------------
These are the variables in use in this rolebook, along with details on where they should be defined.

Expected entries supplied as --extra-vars for standard usage
------------------------------------------------------------

Expected entries in host_vars for standard usage
------------------------------------------------

```
etc_hosts_entries:
  appserver1:
    ipstring: 172.16.81.4
    hoststring: "656724-app1.vwaudi.local 656724-app1 youraudiservices.audi.co.uk"
```

A dictionary which defines the HOSTS entries to create, where:

- ipstring is the value of the IP address
- hoststring is the value of the string to match to the IP address

This variable needs to be defined in host_vars for the target server.

Other variables used in this rolebook
-------------------------------------
These are the variables in use in this rolebook, along with details on where they should be defined.


Global Variables used by Rolebook
-------------------------------
These are global variables used by this rolebook, these are defined in /group_vars/all

