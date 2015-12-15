<?xml version="1.0"?>
<xsl:stylesheet version="2.0" xmlns:ns1="urn:Magento" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions" exclude-result-prefixes="#all">
	<xsl:output method="xml" indent="yes" encoding="UTF-8"/>
	<xsl:template match="/">
		<xsl:text>&#x0A;</xsl:text>
		<ORDERS>
			<xsl:text>&#x0A;</xsl:text>
			<xsl:apply-templates select="SOAP-ENV:Envelope/SOAP-ENV:Body/ns1:multiCallResponse/multiCallReturn/item"/>
			<xsl:text>&#x0A;</xsl:text>
		</ORDERS>
	</xsl:template>
	
	<xsl:template match="SOAP-ENV:Envelope/SOAP-ENV:Body/ns1:multiCallResponse/multiCallReturn/item">
		
		<!-- Order Element -->
		<xsl:element name="ORDER">
			
			<xsl:attribute name="O_ID">
				<xsl:value-of select="item[key = 'increment_id']/value"/>
			</xsl:attribute>
			
		</xsl:element>
		
		
	</xsl:template>
	
	
	
</xsl:stylesheet>