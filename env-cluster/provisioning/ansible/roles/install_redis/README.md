Ansible Role for the Install Redis playbook
=========================================

This ansible role will install the Redis caching server. 

Tasks performed by this rolebook:

* Install Redis Packages
* (if required) Ensure rsyslog is available
* Deploy Redis configuration
* (if required) Deploy rsyslog configuration
* Set vm.overcommit kernel parameter
* Start Redis


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

```
redis_bind_address: 127.0.0.1
```
This is the value upon which Redis will listen for connections.  This is not set by default which means Redis will listen on all IP addresses, if a specific bind address is required then set this value in host_vars for the target server.


Other variables used in this rolebook
-------------------------------------
These are the variables in use in this rolebook, along with details on where they should be defined.

```
redis_service:
```
String defining the name of the service created by the RPM installation, this should not be changed unless the RPM changes in future releases. Defined in Defaults.

```
redis_admin_group:
```
The name of the group to create which can administer the Redis daemon.  This is defined in defaults.

```
enable_iptables_redis:
```
Boolean flag determining whether the IPTables configuration will be applied. Defined in Defaults.

```
redis_iptables_whitelist:
  - 192.168.1.0/24
  - 172.17.28.0/24
```
This list object contains the network ranges which should be added to IPTables to be granted access to the target server.  Note that this is obviously very sensitive to typos so take care when adjusting these entries.  These are defined in defaults but should be overridden in host_vars as required to allow other networks access (i.e. customer networks).

```
redis_port:
```
Integer defining which port Redis will listen on. Defined in Defaults.

```
redis_logfile:
```
Path of the log file which Redis will write too. Defined in Defaults.

```
redis_syslog_enabled:
```
Whether to log events via rsyslog or directly. Defined in Defaults and set to use syslog.

```
redis_syslog_facility:
```
Redis syslog level. Defined in Defaults.

```
redis_databases:
```
The number of Redis databases to create. Defined in Defaults.

```
redis_database_save_times:
  - [900, 1]
  - [300, 10]
  - [60, 10000]
```
Defines the frequency at which Redis will snapshot the database to disk. Defined in Defaults.

```
redis_dbfilename:
```
The name of the db file to create when snapshotting. Defined in Defaults.

```
redis_db_dir:
```
The directory where the database snapshot should be stored. Defined in Defaults.

```
redis_max_clients:
```
Performance tuning parameter, how many connections will Redis allow. Defined in Defaults.

```
redis_max_memory:
```
Performance tuning parameter, how much memory can Redis use. Defined in Defaults.

```
redis_maxmemory_policy:
```
Performance tuning parameter, the caching policy (LRU etc) for Redis. Defined in Defaults.

```
redis_appendfsync:
```
Performance tuning parameter, frequency at which Redis will flush writes to disk. Defined in Defaults.

```
redis_overcommit_memory:
```
Value for the vm.overcommit_memory kernel parameter. Defined in Defaults.

```
redis_requirepass:
```
Boolean value controlling whether Redis requires a password to access the service. Defined in Defaults and set to false.

```
redis_pass:
```
Value of the password required to connect to Redis, if required. Defined in Defaults.  If this is being used it should really be overidden in host_vars.

```
redis_role:
```
Whether Redis is a master or slave in a HA configuration. Defined in Defaults and set to "master", override in host_vars if a slave is required.

```
redis_master_ip:
```
If this is a slave Redis instance, the IP address of the "master" Redis instance. Defined in Defaults but should be overridden in host_vars if used.

```
redis_master_port:
```
If this is a slave Redis instance, the TCP/IP port of the "master" Redis instance. Defined in Defaults but should be overridden in host_vars if used.

```
redis_master_auth:
```
If this is a slave Redis instance and if authentication is enabled, the value of the password to connect to the "master" instance. Defined in Defaults but should be overridden in host_vars if used.

```
sudoers_file:
```
File name for custom sudo entries in /etc/sudoers.d.  Defined in Defaults.

```
redis_sudoers_cmnd_aliases:
  service_redis:
    alias: SERVICE_REDIS
    command: "/etc/init.d/{{redis_service}}"
```

A dictionary which defines the sudo command aliases to be created, where:

- {alias} is the CMND_ALIAS name
- {command} is the value of the CMND_ALIAS

This is defined in Defaults.

```
redis_sudoers_groups:
  redisadm_init:
    group: "{{redis_admin_group}}"
    password: ""
    cmnd_alias: SERVICE_REDIS
```    

A dictionary which defines the sudo rights to be assigned specific groups, where:

- {group} is the group to receive the rights
- {password} determines the NOPASSWD: flag for the rights
- {cmnd_alias} is the right to assign

This is defined in Defaults.

Global Variables used by Rolebook
-------------------------------
These are global variables used by this rolebook, these are defined in /group_vars/all

