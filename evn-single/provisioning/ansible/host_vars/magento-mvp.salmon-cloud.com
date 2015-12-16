## HOST_VARS for a single-stack cloud image

# Many of the options defined below are already the defaults, they are included here
# as these are the ones that will commonly need to be adjusted.

# This is intended as a template file that you should copy and adjust when spinning up
# singlestack environments such as UAT.
---

## The host to build/manage - this must be overridden using --extra-vars
ansible_ssh_host: 127.0.0.1

## The user account to connect too on the remote server.  This assumes the standard 
## "server prep" has been completed (i.e. account created, sudo rights granted and SSH keys)
ansible_ssh_user: ansible

## The project name
project_key: magento-mvp

## Using vagrant or not (Jenkins will hardcode this value to false in --extra-vars anyway)
vagrant: "false"

## Used to determine which configuration files are deployed via the targeted mechanism
env: mvp-cloud

## To be overridden in --extra-vars by Jenkins
software_dest_tmp_dir: /jenkins/jobs/magento_project/workspace



######################
## Repositories Default Configuration
######################

# Only enable IUS for a Vagrant Image, provision.sh handles EPEL earlier in the process
repository_environment_name: PUBLICCLOUD_CENTOS
enable_rhel: false
enable_epel: false
enable_ius: true

######################
## Apache Configuration
######################

## Apache ServerName directive value
mvp_apache_servername: magento-mvp.salmon-cloud.com

## Apache ServerAdmin email value
server_admin_email: rmilnerwatts@salmon.com

## IP restrictions for the backend
magento_admin_ipaddress_whitelist:
  - 172.17.0.0/19
  - 192.168.213.0/24
  - 192.168.214.0/24
  - 192.168.215.0/24
  - 192.168.216.0/22
  - 192.168.130.0/24 
  - 192.168.36.0/24
  - 95.172.74.0/24
  - 212.187.228.0/24

#mvp_apache_ssl_certificates:
#  - mycert.crt
#  - mycertCA.crt
#mvp_apache_ssl_keys:
#  - mycert.key
#mvp_apache_cert_filename: mycert.crt
#mvp_apache_key_filename: mycert.key
#mvp_apache_ca_filename: mycertCA.crt

######################
## HOSTS Configuration
######################

etc_hosts_entries:
  siteurl:
    ipstring: "{{ ansible_eth0.ipv4.address }}"
    hoststring: "{{ mvp_apache_servername }}"

##################
## Solr Details
##################

mvp_solr_cores:
  mvp_catalog_core:
    name: catalog
    instancedir: core-catalog

##################
## MySQL Details
##################

## Enable SSL for network communications
enable_ssl_mysql: false

# Name for updated root user
renamed_mysql_root_user: mydbadmin
# MySQL Root Password
mysql_root_password: 293BE694fCjzE8X

############################
## Magento Database Details
############################

## Urls to configure within the target database
host_name: magento-mvp.salmon-cloud.com

## Name of the MySQL Dump to restore
db_dump_filename: magento-mvp-dump.sql

## Name of the Magento Application Database
magento_database_name: magento

## Credentials for the Application Database
magento_database_username: magento
magento_database_password: RdDzxzv2TMPFV8R

## Determines which "hosts" get access with the application username
# should be overridden in host_vars
magento_db_host_access:
  localhost:
    hoststring: localhost
  ipv4:
    hoststring: 127.0.0.1

############################
## Magento Database Application Configuration
############################

design_package_name: rwd
design_theme_default: enterprise
magento_solr_endpoint: localhost
magento_salmon_admin_password: salmon123

############################
## IPTables Details
############################

enable_iptables_mysql: true
enable_iptables_ntp: true
enable_iptables_memcache: true
enable_iptables_apache: false
enable_iptables_redis: true
enable_iptables_tomcat: false
enable_iptables_solr: true
enable_iptables_mvp_apache: true

common_ssh_whitelist:
  - 172.17.0.0/19
  - 192.168.213.0/24
  - 192.168.214.0/24
  - 192.168.215.0/24
  - 192.168.216.0/22
  - 192.168.130.0/24 
  - 192.168.36.0/24
  - 95.172.74.0/24
  - 212.187.228.0/24

apache_iptables_whitelist:
  - 172.17.0.0/19
  - 192.168.213.0/24
  - 192.168.214.0/24
  - 192.168.215.0/24
  - 192.168.216.0/22
  - 192.168.130.0/24 
  - 192.168.36.0/24
  - 95.172.74.0/24
  - 212.187.228.0/24

memcached_iptables_whitelist:
  - 172.17.0.0/19
  - 192.168.213.0/24
  - 192.168.214.0/24
  - 192.168.215.0/24
  - 192.168.216.0/22
  - 192.168.130.0/24 
  - 192.168.36.0/24
  - 95.172.74.0/24
  - 212.187.228.0/24
  - 127.0.0.1

mysql_iptables_whitelist:
  - 172.17.0.0/19
  - 192.168.213.0/24
  - 192.168.214.0/24
  - 192.168.215.0/24
  - 192.168.216.0/22
  - 192.168.130.0/24 
  - 192.168.36.0/24
  - 95.172.74.0/24
  - 212.187.228.0/24
  - 127.0.0.1

redis_iptables_whitelist:
  - 172.17.0.0/19
  - 192.168.213.0/24
  - 192.168.214.0/24
  - 192.168.215.0/24
  - 192.168.216.0/22
  - 192.168.130.0/24 
  - 192.168.36.0/24
  - 95.172.74.0/24
  - 212.187.228.0/24
  - 127.0.0.1

tomcat_iptables_whitelist:
  - 172.17.0.0/19
  - 192.168.213.0/24
  - 192.168.214.0/24
  - 192.168.215.0/24
  - 192.168.216.0/22
  - 192.168.130.0/24 
  - 192.168.36.0/24
  - 95.172.74.0/24
  - 212.187.228.0/24
  - 127.0.0.1

solr_iptables_whitelist:
  - 172.17.0.0/19
  - 192.168.213.0/24
  - 192.168.214.0/24
  - 192.168.215.0/24
  - 192.168.216.0/22
  - 192.168.130.0/24 
  - 192.168.36.0/24
  - 95.172.74.0/24
  - 212.187.228.0/24
  - 127.0.0.1

magento_iptables_whitelist:
  - 172.17.0.0/19
  - 192.168.213.0/24
  - 192.168.214.0/24
  - 192.168.215.0/24
  - 192.168.216.0/22
  - 192.168.130.0/24 
  - 192.168.36.0/24
  - 95.172.74.0/24
  - 212.187.228.0/24
  - 127.0.0.1
  - 64.41.200.0/24
  - 64.39.96.0/20



############################
## Salmon Users
############################

# Salmon users
salmon_users:
  rmilnerwatts:
    password: $1$salmon$3GsDR4NWxijsRBf40avgN.
    comment: "Richard Milner-Watts"
    groups: "sysadmin,validator,tomcgrp,apacheadm,memcadm,mysqladm"
    state: present
  irogers:
    password: $1$salmon$3GsDR4NWxijsRBf40avgN.
    comment: "Ian Rogers"
    groups: "sysadmin,validator,tomcgrp,apacheadm,memcadm,mysqladm"
    state: present
  mserup:
    password: $1$salmon$3GsDR4NWxijsRBf40avgN.
    comment: "Michael Serup"
    groups: "sysadmin,validator,tomcgrp,apacheadm,memcadm,mysqladm"
    state: present
  mevans:
    password: $1$salmon$EA5qp8sMU5OCqNAB/i2LQ1
    comment: "Michael Evans"
    groups: "sysadmin,validator,tomcgrp,apacheadm,memcadm,mysqladm"
    state: present
  dtrussler:
    password: $1$salmon$3GsDR4NWxijsRBf40avgN.
    comment: "David Trussler"
    groups: "sysadmin,validator,tomcgrp,apacheadm,memcadm,mysqladm"
    state: present
  tbell:
    password: $1$salmon$3GsDR4NWxijsRBf40avgN.
    comment: "Trevor Bell"
    groups: "sysadmin,validator,tomcgrp,apacheadm,memcadm,mysqladm"
    state: present
  kovenden:
    password: $1$salmon$3GsDR4NWxijsRBf40avgN.
    comment: "Kevin Ovenden"
    groups: "mysqladm"
    state: present
  rdyke:
    password: $1$salmon$3GsDR4NWxijsRBf40avgN.
    comment: "Rod Dyke"
    groups: "mysqladm"
    state: present
  schoudhary:
    password: $1$salmon$3GsDR4NWxijsRBf40avgN.
    comment: "Sunil Choudhary"
    groups: "mysqladm"
    state: present
  eboyd:
    password: $1$salmon$3GsDR4NWxijsRBf40avgN.
    comment: "Ed Boyd"
    groups: "sysadmin,validator,tomcgrp,apacheadm,memcadm"
    state: present
  mrowe:
    password: $1$salmon$3GsDR4NWxijsRBf40avgN.
    comment: "Matt Rowe"
    groups: "sysadmin,validator,tomcgrp,apacheadm,memcadm"
    state: present
  crowe:
    password: $1$salmon$3GsDR4NWxijsRBf40avgN.
    comment: "Charles Rowe"
    groups: "sysadmin,validator,tomcgrp,apacheadm,memcadm"
    state: present