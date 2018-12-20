# Homework-09

Using the WeatherProvider class we've written in Part 9 of the lectures, 
override the "bulkInsert" method of the WeatherProvider. 
Also, modify the FetchWeatherTask's addWeather method so that if there are MORE THAN 5 
weather entries to be inserted, the method uses the bulkInsert method of 
the Content Resolver (CR) instead of insert method of the CR.

This bulkInsert method handles multiple inserts to the SQLite database in a fast way, 
faster than individual insert method calls inside a loop.

In the bulkInsert method of the WeatherProvider (which is automatically called 
if you use the bulkInsert method of the CR), you MUST add all of these rows 
in a SINGLE DB TRANSACTION. You should use one of the following SQLiteDatabase commands 
given below:

* beginTransaction()
* beginTransactionNonExclusive()