#!/bin/bash

#
# Run as part of Vagrant VM provisioning, this script replicates some of the functionality that 
# is performed by Jenkins when installing a runtime environment.
#
# Author: Richard Milner-Watts/Sergii Khomenko
# Date: 2015-05-26
#
#

# Install EPEL repository (assuming RHEL/Centos 6)
rpm -ivh http://dl.fedoraproject.org/pub/epel/6/x86_64/epel-release-6-8.noarch.rpm

# Update base VM packages
sudo yum --exclude=kernel* --exclude=centos-release* --exclude=redhat-release* update -y

# Install Support packages
#sudo yum install -y dos2unix

# Install Ansible
sudo yum install -y ansible

# Create local Ansible HOSTS file
sudo mkdir -p /etc/ansible
printf '[vagrant]\nlocalhost\n' | sudo tee /etc/ansible/hosts > /dev/null


## Workaround for Vagrant on Windows machines
# /vagrant is mounted as 777 which causes Ansible problems
# therefore we move the Ansible content sideways and chmod it properly
echo -e "Copying Ansible Resources to staging area."
sudo mkdir -p /data/ansible
sudo cp -R /vagrant/provisioning/ansible/* /data/ansible/
sudo find /data/ansible/ -type f -exec chmod 644 {} \;
#sudo dos2unix /data/ansible/dev-vagrant

## Certificates: Simulate the deployment actions by copying certificates to
# /data/deploy/certs
echo -e "Copying SSL Resources to staging area."
sudo mkdir -p /data/deploy/certificates
sudo rm -rf /data/deploy/certificates/*
sudo cp -R /vagrant/certificates/* /data/deploy/certificates/
sudo find /data/deploy/certificates/ -type f -exec chmod 600 {} \;

## ETL: Simulate the deployment actions by copying ETL content to
# /data/deploy/etl
echo -e "Copying ETL Resources to staging area."
sudo mkdir -p /data/deploy/etl
sudo rm -rf /data/deploy/etl/*
sudo cp -R /vagrant/etl/* /data/deploy/etl/

## Execute Ansible
echo Running provisioner: ansible
ansible-playbook -c local -i /data/ansible/redis-cluster1 --sudo /data/ansible/site.yml
