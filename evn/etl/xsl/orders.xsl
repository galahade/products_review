<?xml version="1.0"?>
<xsl:stylesheet version="2.0" xmlns:ns1="urn:Magento" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions" exclude-result-prefixes="#all">

	<xsl:output method="text" encoding="UTF-8"/>
	<xsl:template match="/">	
		<xsl:text>order_id|status|store_id|customer_id|base_discount_amount|base_grand_total|base_shipping_amount|base_shipping_discount_amount|base_shipping_tax_amount|base_tax_amount|base_currency_code|discount_amount|grand_total|shipping_amount|shipping_discount_amount|shipping_tax_amount|tax_amount|order_currency_code|customer_is_guest|billing_address_id|shipping_address_id|weight|customer_dob|customer_email|customer_gender|customer_prefix|customer_suffix|customer_firstname|customer_middlename|customer_lastname|store_name|shipping_method|customer_note|created_at|total_item_count|collection_id</xsl:text>
		<xsl:text>&#x0A;</xsl:text>
		<xsl:apply-templates select="SOAP-ENV:Envelope/SOAP-ENV:Body/ns1:multiCallResponse/multiCallReturn/item"/>
	</xsl:template>
	
	<xsl:template match="SOAP-ENV:Envelope/SOAP-ENV:Body/ns1:multiCallResponse/multiCallReturn/item">
			<!-- order_id-->
			<xsl:apply-templates select="item[key= 'increment_id']/value"/>			
			<xsl:text>|</xsl:text>
			
			<!-- status -->
			<xsl:apply-templates select="item[key= 'status']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- store_id -->
			<xsl:apply-templates select="item[key= 'store_id']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- customer_id -->
			<xsl:apply-templates select="item[key= 'customer_id']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- base_discount_amount -->
			<xsl:apply-templates select="item[key= 'base_discount_amount']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- base_grand_total -->
			<xsl:apply-templates select="item[key= 'base_grand_total']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- base_shipping_amount -->
			<xsl:apply-templates select="item[key= 'base_shipping_amount']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- base_shipping_discount_amount -->
			<xsl:apply-templates select="item[key= 'base_shipping_discount_amount']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- base_shipping_tax_amount -->
			<xsl:apply-templates select="item[key= 'base_shipping_tax_amount']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- base_tax_amount -->
			<xsl:apply-templates select="item[key= 'base_tax_amount']/value"/>	
			<xsl:text>|</xsl:text>
									
			<!-- base_currency_code -->
			<xsl:apply-templates select="item[key= 'base_currency_code']/value"/>	
			<xsl:text>|</xsl:text>
						
			<!-- discount_amount -->
			<xsl:apply-templates select="item[key= 'discount_amount']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- grand_total -->
			<xsl:apply-templates select="item[key= 'grand_total']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- shipping_amount -->
			<xsl:apply-templates select="item[key= 'shipping_amount']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- shipping_discount_amount -->
			<xsl:apply-templates select="item[key= 'shipping_discount_amount']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- shipping_tax_amount -->
			<xsl:apply-templates select="item[key= 'shipping_tax_amount']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- tax_amount -->
			<xsl:apply-templates select="item[key= 'tax_amount']/value"/>	
			<xsl:text>|</xsl:text>
						
 			<!-- order_currency_code -->
  			<xsl:value-of select="item[key='order_currency_code']/value"/>
 			<xsl:text>|</xsl:text>
						
			<!-- customer_is_guest -->
			<xsl:apply-templates select="item[key= 'customer_is_guest']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- billing_address_id -->
			<xsl:apply-templates select="item[key= 'billing_address_id']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- shipping_address_id -->
			<xsl:apply-templates select="item[key= 'shipping_address_id']/value"/>	
			<xsl:text>|</xsl:text>
						
			<!-- weight -->
			<xsl:apply-templates select="item[key= 'weight']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- customer_dob -->
			<xsl:apply-templates select="item[key= 'customer_dob']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- customer_email -->
			<xsl:apply-templates select="item[key= 'customer_email']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- customer_gender -->
			<xsl:apply-templates select="item[key= 'customer_gender']/value"/>	
			<xsl:text>|</xsl:text>
						
			<!-- customer_prefix -->
			<xsl:apply-templates select="item[key= 'customer_prefix']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- customer_suffix  -->
			<xsl:apply-templates select="item[key= 'customer_suffix']/value"/>	
			<xsl:text>|</xsl:text>			
				
			<!-- customer_firstname -->
			<xsl:apply-templates select="item[key= 'customer_firstname']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- customer_middlename -->
			<xsl:apply-templates select="item[key= 'customer_middlename']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- customer_lastname -->
			<xsl:apply-templates select="item[key= 'customer_lastname']/value"/>	
			<xsl:text>|</xsl:text>

 
			<!-- store_name -->
			<xsl:value-of select="translate(translate(item[key= 'store_name']/value,'|;','--verticalbar--'),'&#x0A;','--newline--')"/>	
			<xsl:text>|</xsl:text>
			
			<!-- shipping_method -->
			<xsl:apply-templates select="item[key= 'shipping_method']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- customer_note -->
			<xsl:apply-templates select="item[key= 'customer_note']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- created_at -->
			<xsl:apply-templates select="item[key= 'created_at']/value"/>	
			<xsl:text>|</xsl:text>
			
			<!-- total_item_count -->
			<xsl:apply-templates select="item[key= 'total_item_count']/value"/>	
			<xsl:text>|</xsl:text>			
			
			<!-- collection_id store_pickup_code-->
			<xsl:apply-templates select="item[key= 'store_pickup_code']/value"/>	
			<xsl:text>|</xsl:text>
														
			<xsl:text>&#x0A;</xsl:text>
	</xsl:template>

</xsl:stylesheet>