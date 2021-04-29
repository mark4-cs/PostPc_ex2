I pledge the highest level of ethical principles in support of academic excellence.  
I ensure that all of my work reflects my own abilities and not those of someone else.

Qusetion answer:

======What code do we need to change/add/remove to support this feature?=====

We need to add function "add_multiplication", to the calculator implementation,
also add the button view to the screen and support it being pressed like all other buttons.
The last step is to change the calculation when "Equals" is pressed, because of operations order.
We need to change the calculation function to calculate first the "x" operator before the "-"/"+".

======Which tests can we run on the calculator? On the activity? On the app?=======

We can run tests for the order of the operations of the calculated result. For example we can test
the case "1+1x2", if the calculation done correctly the result would be 3, otherwise 4 or worse.
Also, we need to verify the button "X" calls the function "add_multiplication" in the calculator.