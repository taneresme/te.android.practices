# Homework-10

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