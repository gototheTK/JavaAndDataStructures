
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


/**
African Cities
**/

SELECT c1.name FROM city c1 INNER JOIN country c2 ON c1.countrycode = c2.code WHERE c2.continent = 'Africa';

/**
Average Population of Each Continent

Submissions	Leaderboard	Discussions
Given the CITY and COUNTRY tables, query the names of all the continents (COUNTRY.Continent) and their respective average city populations (CITY.Population) rounded down to the nearest integer.

Note: CITY.CountryCode and COUNTRY.Code are matching key columns.
**/

SELECT C2.CONTINENT, TRUNC(AVG(C1.POPULATION)) FROM CITY C1 INNER JOIN COUNTRY C2 ON C1.COUNTRYCODE = C2.CODE GROUP BY CONTINENT;