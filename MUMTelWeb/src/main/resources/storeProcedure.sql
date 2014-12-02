ALTER PROCEDURE [dbo].[GetMonthlyTrafficReport] 
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