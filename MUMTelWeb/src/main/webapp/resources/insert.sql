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

USE [mumtel]
GO
/****** Object:  StoredProcedure [dbo].[generate_bill]    Script Date: 12/02/2014 13:55:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER procedure [dbo].[generate_bill]
@phone varchar(20),
@month varchar(5),
@year varchar(5)
as
begin
with bill_input
(id,call_date,call_hour,call_minute,month,year,duration,fromtel,totel,from_country,to_country,serviceCode,FromCountryName,ToCountryName)  
as  
(select cd.callID,convert(date,cd.callDateandTime),DATEPART(hour,cd.callDateandTime) as 'call_hour',DATEPART(minute,cd.callDateandTime) as 'call_minute',
 month(cd.callDateandTime) as 'month',year(cd.callDateandTime) as 'year',cd.duration,fromTel,
   toTel,cfr.callingCode as 'From Country',cTo.callingCode as 'To Country',ser.serviceCode,cfr.countryName,cTo.countryName
   from CallDetail cd,Customer c,Country cTo,Country cfr,ServiceCountry sCountry,Service ser 
    where cd.fromTel=c.telephone  
    and cTo.callingCode=cd.toCallingCode_callingCode
    and cfr.callingCode=cd.fromCallingCode_callingCode  
    and sCountry.serviceCountryID=c.serviceCountry_serviceCountryID  
    and ser.serviceCode=sCountry.service_code  
    and c.telephone=@phone  
    and MONTH(cd.callDateandTime)=@month  
    and YEAR(cd.callDateandTime)=@year)    
    select bi.id, bi.call_date as callDate,cast(bi.call_hour as varchar)+':'+cast(bi.call_minute as varchar) as 'callTime',
		bi.duration/60.0 as callDuration,
		bi.totel as toTelephone,bi.toCountryName,
	(CASE When bi.call_hour between (pT.peakPeriodStart/100) and (pT.offPeakPeriodStart/100)-1 then cRates.peakPeriodRate
		 When bi.call_hour NOT between (pT.peakPeriodStart/100) and (pT.offPeakPeriodStart/100)-1 then cRates.offPeakPeriodRate end) callRate,
    (CASE When bi.call_hour between (pT.peakPeriodStart/100) and (pT.offPeakPeriodStart/100)-1 then (duration/60.0)*cRates.peakPeriodRate
		 When bi.call_hour NOT between (pT.peakPeriodStart/100) and (pT.offPeakPeriodStart/100)-1 then (duration/60.0)*cRates.offPeakPeriodRate end) callCost
    from bill_input bi,ServiceCountry sC,CallRates cRates,PeakTimes pT
    where sC.country_code=bi.from_country
    and sC.service_code=bi.serviceCode
    and sC.serviceCountryID=cRates.serviceCountry_serviceCountryID
    and bi.to_country=cRates.tocallingCode_callingCode
    and sC.serviceCountryID=pT.SC_CODE
   end