CREATE PROCEDURE getCallRatesByServiceAndSourceCountry 
  @cname varchar(255),
  @country varchar(255) 
AS 
select cr.tocallingCode_callingCode,cr.peakPeriodRate,cr.offPeakPeriodRate from Country c,ServiceCountry s,CallRates cr,Service ser
where c.callingCode=s.country_code
and cr.serviceCountry_serviceCountryID=s.country_code
and ser.serviceCode=s.service_code
and c.countryName=@country
and ser.description=@cname
GO 