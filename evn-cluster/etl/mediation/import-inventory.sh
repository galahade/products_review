#!/bin/sh
#
# Script to import inventory into Magento (for only sku's that are valid)
# Script to Re-index Magento
#

sudo -u apache /usr/bin/php -f /data/magento/public/shell/import.php inventory

sudo -u apache /usr/bin/php -f /data/magento/public/shell/indexer.php -- --reindex "cataloginventory_stock,catalog_product_price"
