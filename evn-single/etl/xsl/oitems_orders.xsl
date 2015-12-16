<?xml version="1.0"?>
<xsl:stylesheet version="2.0" xmlns:ns1="urn:Magento" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions" exclude-result-prefixes="#all">
	
	
  <xsl:output method="text" encoding="UTF-8"/>
	<xsl:template match="/">	
		<xsl:text>order_id|product_id| sku | name | description | qty_ordered | product_type | weight | row_weight | is_virtual | additional_data | applied_rule_ids | price | base_price | discount_amount | base_discount_amount | row_total | base_row_total | created_at</xsl:text>
		<xsl:text>&#x0A;</xsl:text>
		<xsl:apply-templates select="SOAP-ENV:Envelope/SOAP-ENV:Body/ns1:multiCallResponse/multiCallReturn/item/item[key= 'items']/value/item"/>
	</xsl:template>	
 		
 	 	<xsl:template match="SOAP-ENV:Envelope/SOAP-ENV:Body/ns1:multiCallResponse/multiCallReturn/item/item[key= 'items']/value/item">  	
  			<!-- order_id -->
  			<xsl:value-of select="../../../item[key='increment_id']/value"/>
 			<xsl:text>|</xsl:text>
  		
 			<!-- product_id -->	
 			<xsl:value-of select="item[key='product_id']/value"/>
 			<xsl:text>|</xsl:text>
 		 			
 			<!-- sku -->	
 			<xsl:value-of select="item[key='sku']/value"/>
 			<xsl:text>|</xsl:text>
 		 	
  			<!-- name -->
 			<xsl:value-of select="item[key='name']/value"/>
 			<xsl:text>|</xsl:text>
 			
 			<!-- description -->	
 			<xsl:value-of select="item[key='description']/value"/>
 			<xsl:text>|</xsl:text>
 			
 			<!-- qty_ordered -->
 			<xsl:value-of select="format-number(item[key='qty_ordered']/value,'0.00')"/>
 			<xsl:text>|</xsl:text>
 		 						
 			<!-- product_type -->	
 			<xsl:value-of select="item[key='product_type']/value"/>
 			<xsl:text>|</xsl:text>
 			
 			<!-- weight -->	
 			<xsl:value-of select="item[key='weight']/value"/>
 			<xsl:text>|</xsl:text>	
 			
 			<!-- row_weight -->	
 			<xsl:value-of select="item[key='row_weight']/value"/>
 			<xsl:text>|</xsl:text>	
 			
 			<!-- is_virtual -->	
 			<xsl:value-of select="item[key='is_virtual']/value"/>
 			<xsl:text>|</xsl:text>
 			
 			<!-- additional_data -->	
 			<xsl:value-of select="item[key='additional_data']/value"/>
 			<xsl:text>|</xsl:text>
 			
 			<!-- applied_rule_ids -->	
 			<xsl:value-of select="item[key='applied_rule_ids']/value"/>
 			<xsl:text>|</xsl:text>
 			
 			<!-- price -->	
 			<xsl:value-of select="item[key='price']/value"/>
 			<xsl:text>|</xsl:text>
 			
 			<!-- base_price -->	
 			<xsl:value-of select="item[key='base_price']/value"/>
 			<xsl:text>|</xsl:text> 		 		
 		 		
 			<!-- discount_amount -->	
 			<xsl:value-of select="item[key='discount_amount']/value"/>
 			<xsl:text>|</xsl:text>
 			
 			<!-- base_discount_amount -->	
 			<xsl:value-of select="item[key='base_discount_amount']/value"/>
 			<xsl:text>|</xsl:text> 		 		
 		 			
 			<!-- row_total -->	
 			<xsl:value-of select="item[key='row_total']/value"/>
 			<xsl:text>|</xsl:text>
 			
 			<!-- base_row_total -->	
 			<xsl:value-of select="item[key='base_row_total']/value"/>
 			<xsl:text>|</xsl:text>
 		 				
 			<!-- created_at -->	
 			<xsl:value-of select="item[key='created_at']/value"/>
 			<xsl:text>|</xsl:text>
 			
 		<xsl:text>&#x0A;</xsl:text>
  	
   </xsl:template>
</xsl:stylesheet>