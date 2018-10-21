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

![hw-02](https://github.com/taneresme/te.android.practices/blob/master/hw02/docs/Week1_Q2.png)

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
