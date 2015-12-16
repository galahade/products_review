#!/bin/sh

PHP_BIN=`which php`

if [ $# -lt 1 ]
then
   echo "$0 <filename> "
   exit 1
fi


$PHP_BIN  -f  /var/www/habitat/public/shell/category_import.php -- --rebuild --dump $1 --verbose

