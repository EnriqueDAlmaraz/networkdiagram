#Path Analysis

	This program finds relations between the existing notes based on the dependencies that exist between them. 
If node C is dependent on node B, and node B is dependent on node A, and A is a starting node, then the path A, B, C is created.
Each one of these nodes has its own duration. Therefore a path also has its own duration, which is the 
addition of the duration of all the nodes in it. 
 
	The main functionality of the program is to find all the paths between the existing nodes, calculate their duration,
	and sort them in a descending order based on it. 
This process happens every time the user presses the submit button.
The already sorted paths are displayed on the right side of the screen on the “Preview” window. 
They will stay there until the user either decides to close the program or hits the restart button. 
When the restart button is pressed, all the paths found are eliminated and the preview window is left empty.
	
	The program also has its own “Help” button that provides answers to general questions of the program. 

