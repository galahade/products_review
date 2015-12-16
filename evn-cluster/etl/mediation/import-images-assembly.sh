#!/bin/sh
#
# Script to import catalogue images into Magento (only images that have changed)
#

sudo -u apache /usr/bin/php -f /data/magento/public/shell/images-assembly-import process recently_updated_only