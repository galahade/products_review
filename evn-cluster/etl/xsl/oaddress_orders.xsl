<?xml version="1.0"?>
<xsl:stylesheet version="2.0" xmlns:ns1="urn:Magento" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions" exclude-result-prefixes="#all">

	<xsl:output method="text" encoding="UTF-8"/>
	<xsl:template match="/">	
		<xsl:text>address_id|address_type|order_id|customer_id|customer_address_id|email|prefix|firstname|middlename|lastname|suffix|street|city|postcode|region|region_id|country_id|telephone|fax|company|VAT_Number</xsl:text>
		<xsl:text>&#x0A;</xsl:text>
		<xsl:apply-templates select="SOAP-ENV:Envelope/SOAP-ENV:Body/ns1:multiCallResponse/multiCallReturn/item"/>
	</xsl:template>
	
	<xsl:template match="SOAP-ENV:Envelope/SOAP-ENV:Body/ns1:multiCallResponse/multiCallReturn/item">
			
			<!-- address_id -->			
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'address_id']/value"/>					
			<xsl:text>|</xsl:text> 
		
			<!-- address_type -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'address_type']/value"/>			
			<xsl:text>|</xsl:text> 
					
			<!-- order_id -->
			<xsl:apply-templates select="item[key= 'increment_id']/value"/>			
			<xsl:text>|</xsl:text>
			
			<!-- customer_id -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'customer_id']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- customer_address_id -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'customer_address_id']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- email -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'email']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- prefix -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'prefix']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- firstname -->
			<!-- order_id --><xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'firstname']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- middlename -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'middlename']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- lastname -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'lastname']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- suffix -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'suffix']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- street -->
			<xsl:value-of select="translate(translate(item[key= 'shipping_address']/value/item[key= 'street']/value,'|',' '),'&#x0A;',' ')"/>	
			<xsl:text>|</xsl:text>
			
			
			<!-- city -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'city']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- postcode -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'postcode']/value"/>			
			<xsl:text>|</xsl:text> 
			  
			<!-- region -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'region']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- region_id -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'region_id']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- country_id -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'country_id']/value"/>			
			<xsl:text>|</xsl:text> 
			
			
			<!-- telephone -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'telephone']/value"/>			
			<xsl:text>|</xsl:text> 
			
			
			<!-- fax -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'fax']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- company -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'company']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- vat_id -->
			<xsl:apply-templates select="item[key= 'shipping_address']/value/item[key= 'vat_id']/value"/>			
			<xsl:text>|</xsl:text>
												
			<xsl:text>&#x0A;</xsl:text>
			
			
			<!-- address_id -->			
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'address_id']/value"/>					
			<xsl:text>|</xsl:text>
			
			
			<!-- address_type -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'address_type']/value"/>			
			<xsl:text>|</xsl:text> 
		
			<!-- order_id -->
			<xsl:apply-templates select="item[key= 'increment_id']/value"/>			
			<xsl:text>|</xsl:text>
			
			<!-- customer_id -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'customer_id']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- customer_address_id -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'customer_address_id']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- email -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'email']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- prefix -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'prefix']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- firstname -->
			<!-- order_id --><xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'firstname']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- middlename -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'middlename']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- lastname -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'lastname']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- suffix -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'suffix']/value"/>			
			<xsl:text>|</xsl:text> 
			
			
			<!-- street -->
			<xsl:value-of select="translate(translate(item[key= 'billing_address']/value/item[key= 'street']/value,'|;','--verticalbar--'),'&#x0A;','--newline--')"/>	
			<xsl:text>|</xsl:text>
			
			<!-- city -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'city']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- postcode -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'postcode']/value"/>			
			<xsl:text>|</xsl:text> 
			  
			<!-- region -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'region']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- region_id -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'region_id']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- country_id -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'country_id']/value"/>			
			<xsl:text>|</xsl:text> 
			
			
			<!-- telephone -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'telephone']/value"/>			
			<xsl:text>|</xsl:text> 
			
			
			<!-- fax -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'fax']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- company -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'company']/value"/>			
			<xsl:text>|</xsl:text> 
			
			<!-- VAT Number -->
			<xsl:apply-templates select="item[key= 'billing_address']/value/item[key= 'vat_id']/value"/>			
			<xsl:text>|</xsl:text>
												
			<xsl:text>&#x0A;</xsl:text>
	</xsl:template>

</xsl:stylesheet>