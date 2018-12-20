# Android Application Development Practices

## Homework-01

Open up a new Android Project (API level 22, Empty Project).

Put 1 TextView view to the center of the screen and 2 floatingActionButton views at the bottom of the screen right next to the other in the project with different images.

Change the code of both of the floatingActionButtons so that,
- When the button to the left is clicked, the font of the TextView is changed to 24dp
- When the button to the right is clicked, the font of the TextView is changed to 12dp

**NOTE:** You need to go to the Android Developers web page and find the method for changing the font size of a TextView.

## Homework-02

Build up an UI consisting of one ImageView and three Switches as seen below

![](https://github.com/taneresme/te.android.practices/blob/master/hw02/docs/Week1_Q2.png)

Make three versions of the same activity, each having the following layouts:
- ConstraintLayout
- LinearLayout
- RelativeLayout

**NOTE:** Use the following Star Trek Poster for the image of the ImageView.

## Homework-03

Open up a new Android Project (API level 22, Empty Project)

Using the UI you've built in Question 2 (ConstraintLayout version), change the functionality of the three switches as explained below:
- Switch 1 - (visibilitySwitch): If checked, the image should be visible; otherwise it should be invisible.
- Switch 2 - (tintSwitch): If checked, there should be a orange tint over the image (either screen or multiply mode); otherwise no tint.
- Switch 3 - (tintModeSwitch): If checked, the tint should be on screen mode; in unchecked it should be multiply mode.

You can find the details of ImageView views from the following link: ImageViews. Check the method setTintList and setTintMode.

## Homework-04

Open up a new Android Project (API level 22, Empty Project).

Build up an app with a single activity which in turn has a single horizontal RecyclerView element. Each item in the RecyclerView element should consist of three items (see Figure 1):
- One ImageView at the top
- Two TextViews below the ImageView, one below the other

![hw-04](https://github.com/taneresme/te.android.practices/blob/master/hw04/docs/Week2_Q1.png)

The RecyclerView element should have the following features:

- There should be AT LEAST 10 items with different images in the RecyclerView.
- The layout of the RecyclerView should be a horizontal linear layout
- There should be a horizontal scroll bar in the RecyclerView

## Homework-05

Open up a new Android Project (API level 22, Empty Project).

Build up an app with a single activity which in turn has a single vertical staggered RecyclerView element. Each item in the RecyclerView element should consist of three items (see Figure 1):
- One ImageView at the top
- Two TextViews below the ImageView, one below the other

The RecyclerView element should have the following features:
- There should be AT LEAST 16 items with different images in the RecyclerView.
- The layout of the RecyclerView should be a vertical staggered grid layout.
- There should be 2 columns consisting of 8 items each.
- The horizontal sizes of each Image should be the same whereas the vertical sizes of each Image MUST be different from each other.

## Homework-06

Start from the project given in the file named "Sunshine22_Part5_END.zip" in Moodle.

Modify the FetchWeatherTask class so that the RecyclerView object should show the pressure and humidity information for each day instead of minimum temperature, maximum temperature and the weather information.

**Example:**
Each item in RecyclerView should say: "Sun Oct 22 Pr: 1018.6, Hum: 0%"
**HINT:** You have to change the part where you get the max, min, main items inside the JSON object.

## Homework-07

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

## Homework-08

Change the design of the MainActivity of the Sunshine project as seen below.

**HINT:** Start from Sunshinev22_Part8_END.zip

![hw-08](https://github.com/taneresme/te.android.practices/blob/master/hw08/docs/Homework-Lab6-Q1.png)

## Homework-09

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

## Homework-10

Create a new Android project which consists of four classes as below. The classes will have the following features:

**1. MainActivity:**

The activity will take some information that should be passed to the BookProvider to be written to the table "Books"
There will be four EditText views, five TextViews, and one FloatingActionButton as seen below.
When the FloatingActionButton is clicked, the activity will get the texts of each EditText view; 
**CHECKS** if such a book with the same name exists in the "Books" table; 
if not insert it to the "Books" table via the ContentResolver methods.

![hw-08](https://github.com/taneresme/te.android.practices/blob/master/hw10/docs/Lab7_Q4.png)

**2. BookProvider**

This class will be based on the ContentProvider class.
It will create the DB with a single table called "Books" as explained below. Note that this is same 
"Books" table you've written in Homework/Lab 6. You can use the DB helper you've written for Homework/Lab 6.
You are supposed to override the correct methods for inserting, and querying the DB similar to what we did 
in lecture notes Part 9.

**3 - 4. Contract Class and DBHelper class**

This class will be based on the contract and DB helper classes we've built in Part 8 of the lectures.
Note that you can use the DB helper you've written for Homework/Lab 6.

**Table "Books":**

ID	Name	Author	Genre	Year
 
* ID is an auto-increment, primary key, integer column
* Name is a NOT NULL, text (or String) column
* Author is a NOT NULL, text (or String) column
* Genre is a text (or String) column
* Year is an integer column

## Homework-11

Rewrite the Hydration app with the same functionality using the JobScheduler API instead of 
the Firebase JobDispatcher as the JobScheduling framework.