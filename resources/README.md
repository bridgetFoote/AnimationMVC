 **Welcome to the AnimationModel Documentation**
 
 *The Model*
 * **Interface** - AnimationOperations 
 * **Interface Implementation** - AnimationModel
    * `HashMap<String,IShape> shapes`: how the model stores shapes
    * `TreeMap<Integer, List<ShapeDrawParam>>`: parameters needed to draw a shape in 
    the visual view. The key for each `ShapeDrawParam` is the tick where the shape 
    will be drawn
    * Contains the class to build a model
 
 *Shapes*
* **Interface** - IShape
* **Abstract Class** - AbstractShape implements IShape. Each shape has a list of actions.
* **Child Classes** - Ellipse and Rectangle
* **Enum** - ShapeType (can be ellipse or rectangle)

*Transformations (Actions)*
* **Interface** - IAction
* **Abstract Class** - ShapeAction implements IAction
* **Move** - An action where the shape moves, extends ShapeAction
* **Stay** - An action where the shape doesn't move, extends ShapeAction
* **ActionType** - Enum that can be a move or a stay.  
* **RGBColor** - The color for a shape using RGB gradients

*View*
* **Interface** - AnimationView
* **Abstract Textual View** - Abstract class for textual views
* **TextView** - Implementation of text view. Used to write output of animation to a text file
* **SVGView** - Implementation to write animation to SVG file to view in browser. 
* **Abstract Visual View** - Abstract class for visual views. Included for potential additional visual views. 
* **VisualView** - Implementation of visual view. Draws the animation for each tick. 
* **AnimationPanel** - Where the animation is actually drawn. This is included as a field in the VisualView. 
* **ShapeDrawParam** - Class used by VisualView to represent all parameters needed to draw a shape at a given tick. 
This class was created so the view could quickly draw a shape from the model.

*Tests*
* The only tests for this assignment were unit tests for textual views.  

*Design Changes from HW5*
* Following the design discussion, we decided to implement interfaces for our 
Shape and Action classes to add greater flexibility. 
    * NOTE: We meant to implement IAction but ran out of time. 
* Added the class `ShapeDrawParam` so that the view could easily draw a shape at each tick. 
* Removed some methods from `AnimationOperations` that did not need to be client facing. Several of these 
methods didn't need to be public and were re-written to be private helper methods. 
    * NOTE: Due to time constraints, we were not able to do this for all methods.      
    