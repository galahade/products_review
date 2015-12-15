<?xml version="1.0"?>
<xsl:stylesheet version="2.0" xmlns:ns1="urn:Magento" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions" exclude-result-prefixes="#all">

  <xsl:output method="text" encoding="UTF-8"/>
		<xsl:template match="/">			
			<xsl:apply-templates select="SOAP-ENV:Envelope/SOAP-ENV:Body/ns1:multiCallResponse/multiCallReturn/item/item[key='isFault']"/>
		</xsl:template>	
 		
 	 	<xsl:template match="SOAP-ENV:Envelope/SOAP-ENV:Body/ns1:multiCallResponse/multiCallReturn/item/item[key='isFault']">  	
  			<!-- order_id -->
  			<xsl:value-of select="../item[key='args']/value/item[key='orderIncrementId']/value"/>
 			<xsl:text>|</xsl:text>
  		
 			<!-- fault_message -->	
 			<xsl:value-of select="../item[key='faultMessage']/value"/>
 		
 		<xsl:text>&#x0A;</xsl:text>
  	
   </xsl:template>
</xsl:stylesheet>