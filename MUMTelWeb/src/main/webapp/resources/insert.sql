
USE [mumtel]
GO
/****** Object:  StoredProcedure [dbo].[generate_bill]    Script Date: 12/02/2014 13:55:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

DROP procedure [dbo].[generate_bill]
CREATE procedure [dbo].[generate_bill]
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
    and bi.call_date>= cRates.dateFrom AND (bi.call_date<= cRates.dateTo OR cRates.dateTo IS NULL)
   end
   
   DROP PROCEDURE  [dbo].[GetMonthlyTrafficReport] 
   CREATE PROCEDURE [dbo].[GetMonthlyTrafficReport] 
	-- Add the parameters for the stored procedure here
	@month int, 
	@year int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT rank() OVER (ORDER BY s.description,sCountry.countryName,dCountry.countryName) as  reportID,s.description as service,sCountry.countryName as fromCountry,dCountry.countryName as toCountry,SUM(c.duration)/60.0 AS totalMinutesOfCall
		FROM CallDetail c
		INNER JOIN Customer cust on
		c.fromTel=cust.telephone
		INNER JOIN ServiceCountry sc on sc.serviceCountryID=cust.serviceCountry_serviceCountryID
		INNER JOIN Service s on sc.service_code=s.serviceCode
		INNER JOIN Country sCountry on c.fromCallingCode_callingCode=sCountry.callingCode
		INNER JOIN Country dCountry on c.toCallingCode_callingCode=dCountry.callingCode
		WHERE Year(c.callDateandTime)=@year
		AND MONTH(c.callDateandTime)=@month
		group by s.description,sCountry.countryName,dCountry.countryName
END