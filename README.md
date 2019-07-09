# FileMaster
A simple GUI program that gets input from a user, stores it in a file, and reads it in real-time. Licensed under GPLv2

<i><b> Functionality: </i></b>

In the GUI, there are 3 components.

1). The <b>"middle"</b> component is a text area where the user enters in whatever text he or she wants. This is stored as a string. 

2). The <b>"right"</b> component is  a text area (not editable by the user) that will read in whatever text is stored in the file. Since there's no file by default, it starts off blank.

3). The <b>"top"</b> component is the menu, where various actions can occur. 


In the menu, the user has one of six actions: 

A). <b> Read Me </b> - this will open up a new window and display all of the necessary information to use the program. 

B). <b> Write to File </b> - this will take all text typed in the "middle" component and write it to a text file that it creates. It'll be in the same directory as the jar file and it's called "testfile.txt". 

C). <b> Read From File </b> - this will read in all of the text stored in the text file and display it in the "right" component. This will clear any text typed in the "middle" component

D). <b> Clear All Read </b> - this clears all text stored in the "right" component. 

E). <b> Delete File </b> - this deletes the text file created by the program. 

F). <b> Quit Program </b> - this exits the program when clicked. 

