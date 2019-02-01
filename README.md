## Instructions

The purpose of this test is to see how you write and read Java code. 
Test assignment consists of 2 tasks 1 of which is done during live coding session.

After you do remaining task -
 deliver an updated javatest-<yourname>.zip, 
 which consists of the original javatest.zip plus all your modifications and additions. 

When you are done, **please also write down how long time you spent on each question**, 
if there were any unclear points and what assumptions you made in those cases.

Feel free to ask any questions to your tech contact at Minexsystems.

Also you are free to deliver your results via public VCS(GitHub, BitBucket etc). 

### 0: Cache Map

#### Summary: 

You should implement interface CacheMap to make tests green in CacheMapTest.


### 1: Currency Conversion API

#### Summary:

You should implement application that provides currencyName rates via API.

#### What to do:
This application should match the following requirements:
* Currency rates should be loaded from file (find it in the rates_response_sample.json file). File contains only 3 rates: EUR->GBP, EUR->USD, EUR->UAH
* Calculate missing cross-currencyName rates based on data from file:
* We should calculate all other missing rates: GBP->USD, GBP->UAH, UAH->USD
* When we have all possible currencyName pairs, we should calculate reverse rates: GBP->EUR, USD->EUR, UAH->EUR, USD->GBP, UAH->GBP, USD->UAH
* Implement Currency API based on REST Web Service that provides:
* All possible currencyName rates
* Currency rateToBaseCurrency by source currencyName and target currencyName
* The result of output should be in JSON format. There are no other specific requirements on data structure or API specifications.