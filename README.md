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