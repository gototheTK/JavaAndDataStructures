
/**
Weather Observation Station 19
**/

SELECT ROUND(SQRT(POWER(MAX(lat_n) - MIN(lat_n),2) + POWER(MAX(long_w) - MIN(long_w),2)),4) FROM STATION;


/**
Weather Observation Station 20
**/

SELECT ROUND(MEDIAN(LAT_N),4) FROM STATION;


/**
Population Census
**/


SELECT SUM(CITY.POPULATION) FROM CITY INNER JOIN COUNTRY ON CITY.COUNTRYCODE = COUNTRY.CODE WHERE COUNTRY.CONTINENT = 'Asia';