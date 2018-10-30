# Homework-07

Start from the project given in the file named "Sunshine22_Part5_END.zip" in Moodle.

Modify the whole project so that when an item in the RecyclerView is touched, the DetailActivity is opened with the **following information of the touched item** is shown as in the image given below:
* Date
* Day (the big temperature), Min, and Max temperatures
* Description of the weather information (e.g., Sunny, Rainy...)
* The weather icon corresponding to the openweathermap icons

![hw-07](https://github.com/taneresme/te.android.practices/blob/master/hw07/docs/Homework-Lab4_Q2.png)

**NOTE:** You can find all the weather icons from [here](https://openweathermap.org/weather-conditions):
**NOTE:** For some strange reason, Android doesn't allow image files starting with numbers (i.e., if you try to use an image file called 10d.png, Android Studio will give you an error.). Instead you should use the weather icon files with some prefix (e.g., use 10d.png as owm_10d.png)
**NOTE:** If you do not like the openweathermap weather icons, you can use a different weather icon set available on the internet.
**HINT:** You should modify some parts of the the following classes:
* FetchWeatherTask.java
* ForecastViewHolder.java
* GreenAdapter.java
* DetailActivity.java