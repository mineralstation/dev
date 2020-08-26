<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

	<xsl:output method="xml" omit-xml-declaration="yes" indent="no" />
	<xsl:strip-space elements="*" />

	<xsl:template match="/root">
		<xsl:element name="getTrialDataResponse" namespace="http://www.royallondon.com/xsd/interface/tt/pensions/simulations">
			<xsl:for-each-group select="d" group-by="a">
				<xsl:element name="TrialData" namespace="http://www.royallondon.com/xsd/interface/tt/pensions/simulations">
					<xsl:if test="r">
						<xsl:element name="Trial" namespace="http://www.royallondon.com/xsd/interface/tt/pensions/simulations">
							<xsl:value-of select="r" />
						</xsl:element>
					</xsl:if>
					<xsl:if test="t">
						<xsl:element name="Timestep" namespace="http://www.royallondon.com/xsd/interface/tt/pensions/simulations">
							<xsl:value-of select="t" />
						</xsl:element>
					</xsl:if>
					<xsl:for-each select="current-group()">
						<xsl:if test="n">
							<xsl:element name="IncomeNet" namespace="http://www.royallondon.com/xsd/interface/tt/pensions/simulations">
								<xsl:value-of select="n" />
							</xsl:element>
						</xsl:if>
					</xsl:for-each>
					<xsl:for-each select="current-group()">
						<xsl:if test="g">
							<xsl:element name="IncomeGross" namespace="http://www.royallondon.com/xsd/interface/tt/pensions/simulations">
								<xsl:value-of select="g" />
							</xsl:element>
						</xsl:if>
					</xsl:for-each>
					<xsl:for-each select="current-group()">
						<xsl:if test="v">
							<xsl:element name="ValueGross" namespace="http://www.royallondon.com/xsd/interface/tt/pensions/simulations">
								<xsl:value-of select="v" />
							</xsl:element>
						</xsl:if>
					</xsl:for-each>
					<xsl:for-each select="current-group()">
						<xsl:if test="d">
							<xsl:element name="Disinvestment" namespace="http://www.royallondon.com/xsd/interface/tt/pensions/simulations">
								<xsl:value-of select="d" />
							</xsl:element>
						</xsl:if>
					</xsl:for-each>
					<xsl:if test="z">
						<xsl:element name="AnnuitisedValueGross" namespace="http://www.royallondon.com/xsd/interface/tt/pensions/simulations">
							<xsl:value-of select="z" />
						</xsl:element>
					</xsl:if>
					<xsl:if test="a">
						<xsl:element name="CustomerAge" namespace="http://www.royallondon.com/xsd/interface/tt/pensions/simulations">
							<xsl:value-of select="a" />
						</xsl:element>
					</xsl:if>
					<xsl:if test="i">
						<xsl:element name="RequiredIncomeTarget" namespace="http://www.royallondon.com/xsd/interface/tt/pensions/simulations">
							<xsl:value-of select="i" />
						</xsl:element>
					</xsl:if>
					<xsl:if test="f">
						<xsl:element name="TargetFundValue" namespace="http://www.royallondon.com/xsd/interface/tt/pensions/simulations">
							<xsl:value-of select="f" />
						</xsl:element>
					</xsl:if>
				</xsl:element>
			</xsl:for-each-group>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>