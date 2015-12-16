#!/bin/sh
# 
# Script to import catalogue options into Magento
# Script to import catalogue into Magento
# Script to import catalogue images into Magento (only images that have changed)
# Script to Re-index Magento
#

sudo -u apache /usr/bin/php -f /data/magento/public/shell/auto_add_options.php log

sudo -u apache /usr/bin/php -f /data/magento/public/shell/import.php catalogue

sudo -u apache /usr/bin/php -f /data/magento/public/shell/images-assembly-import.php process recently_updated_only

sudo -u apache /usr/bin/php -f /data/magento/public/shell/indexer.php -- --reindex "catalog_product_attribute,catalogsearch_fulltext,catalog_category_product,catalog_url_product,catalog_product_price"
