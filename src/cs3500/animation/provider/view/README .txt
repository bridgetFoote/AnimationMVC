HW5:

Our model interface is called ExCELlenceOperations, and our implementation is called ExCELlenceModel. Our implementation allows users
to create shapes with a given name, type, and parameters. The name will be used as the key to the Hashmap<String, IShape> for storing
created shapes.

Our implementation of colors, motions, and shapes are all implementing their respective interfaces so that other implementations can
be utilized in our model without any problems. 

Our Color class handles the verification of valid colors (rgb values from 0-255) and makes sure that colors with the same rgb values
are considered equal

Our Motion class ensures that created motions do not have invalid final positions (We determined that position cannot be negative)
and ensures that final parameters of the shape are positive. Two motions with the same time, and position/sizes within .0001 of
each other are considered equal

Our Posn class was created to have a class describing the position of shapes in our model, as we felt that a position in the format
(X,Y) was most appropriate. Two positions are considered equal if their X and Y values are both within .0001 of each other

Our AShape class is an abstract class containing functionality that all shapes should have (getters, setters, ability to add motions)

Our classes extending AShape were Rectangle and Ellipse, and they handle verification of shape creation (not allowing users to input
invalid parameters). They define equal shapes as being the same type, having the same color, and having positions and sizes within
.0001 of each other.

Within our implementation of the model, we decided that we would have 1 addMotion function with all the parameters so that users would
have an easier time being able to change multiple things at once about a shape, instead of having many subfunctions (move, resize,
changeColor, etc). Our motions only include t1, t2, and the final state of the shape instead of holding the initial state as well.
This made it effectively impossible for shapes to "teleport" at common endpoints, as long as the motions did not overlap times, which
we verified in the method validateMotions. One drawback to this that we did not see until towards the end of the assignment was that
it made writing getGameState, which prints all of the motions for the user, extremely difficult to write, as motions did not hold
the initial state of the shape. We also wrote it with the assumption that users would only call getGameState before executing the 
motions, which makes sense as users would want to visualize all their motions before running it. getGameState and executeMotions also
run sortMotions and validateMotions, which makes it convenient for users as they are unable to execute invalid or overlapping motions,
and it does not matter the order in which they add motions. Finally, our getter method getMotions returns a defensive copy, so that
users will not be able to change the internal model state through calling the method.

__________________________________________________________________________________________________________________________________________
HW6:

Substantial changes to the model include adding methods for removing shapes and removing motions from shapes, as those were necessary
functions for a complete model. We removed our executeMotions() method, as we realized that the shape parameters should not be
changed within the model. The method getShapesAt(int tick) was added for the VisualView to use in drawing shapes onto our panel.
Our implementation of a model itself now has fields topLeft, width, and height so that when the AnimationReader parses the input file,
the visualView can handle changing the window size to the necessary parameters as well as set the top-left corner of the view.
This prevents shapes which shouldn't be seen from being included in the view.All fields in which we failed to list as private
or protected were changed as well.

Minor changes to the model to adhere to how AnimationReader worked included allowing shapes to be created with x or y positions
below 0, and changing the toString() method of our Motion class to allow our textual and svg views to adhere to the assignment
expected outputs.

Our 3 views all implemented our IView interface, which had the following methods:
1. String text(), which produces the corresponding text output of the view
2. void showView(), which made the view visible for the user when running a visual view
3. void refresh(), which refreshes the view
4. void setSpeed(), which determines the rate at which the animation is shown to the user
5. ExCELlenceOperationsReadOnly getModel(), which returns the read-only copy of the model from the view.

If a view did not support one or more of these interface operations, we threw an UnsupportedOperationException to notify the user
____________________________________________________________________________________________________________________________________________
HW7:

The biggest change to the model in this assignment was the addition of the keyframe class. It was basically the same as our motions class having the
end state of a shape except a keyframe only has one time, leading it to be a "snapshot" of a shape. Keyframes are basically motions with less adding
restrictions, as users as able to create keyframes for shapes with little chance of overlap, with the exception being if a user attempts to add a 
keyframe to a shape that already contains a keyframe at the given time.

 Initially, shapes in our model held an ArrayList<Keyframes> but we ran into problems removing keyframes from our animation due to a
 "concurrent modification" error, so we changed the data structure to a CopyOnWriteArrayList<Keyframes> instead.

We also changed our structure holding the shapes in the model from a Hashmap to a LinkedHashmap, as we had a bug where shapes were drawn to our panels
in a different order than the one in which they were put into the map. This led to shapes not being visible in our view because they were covered up 
by larger shapes. This was extremely prominent when rendering the buildings.txt file, as all shapes drawn before the background were covered up by it
even though they existed within the model.

To complete the editor view for HW7, we merely added whatever methods we needed into the IView interface for the new view, and continued 
throwing UnsupportedOperationExceptions to notify the user if the other views did not support the new interface methods.

**** INSTUCTIONS FOR USING EDITOR VIEW

IF THE EDITOR OPTIONS DO NOT SHOW UP ON STARTUP, USER MIGHT HAVE TO MAXIMIZE JFRAME

Our view utilizes mainly JButtons and JCheckboxes for users to interract with, as well as a Scrollable JList in which users can select any shape
or keyframe within the model. There is a control panel at the top for all playback operations, (pause, restart, double speed, halve speed, loop),
and an editting panel at the bottom for users to add/remove/modify shapes and keyframes.

The remove and modify buttons were purposefully disabled if a user did not select an element from the JList, to signify that those operations require 
a selection. To add shapes and keyframes and modify a selected keyframe, users must provide the new or modified parameters in the given JTextfield.
Attempting to hit the add or modify buttons with invalid input will output to user the proper format of the given button. If a user formats the instruction
properly, for example for adding a new rectangle: rectangle r1 10 10 20 20 255 0 255, the JTextfield will notify user of successful shape creation
or unsuccessful shape creation with the accompanying error message (invalid size, shape already with same name, etc.) The same goes for keyframe
addition and modification