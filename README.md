Names: Tevin Brown (tmb51) and Robbie Ha (rth19)

ADDING New Quizzes

To add a new PersonalityQuiz, add a name to the string array "quiz_names" under res/values/quiz.xml in the following format.

// Insert Here (Personality Quiz) //

Then, add a new JSONString in the same resource, that shares the same name minus the (Personality Quiz) & replacing the spaces with _.
View the other resources within the file to see the proper formating of the JSON String for the JSONParser to read.

To add a new Linear Quiz (which utilizes drawables), do the following:

Add a name to the string array "quiz_names" under res/values/quiz.xml in the following format.

// Insert Here (Linear Quiz) //

Then, add a new String Array in the same resource, that shares the same name minus the (Linear Quiz) & replacing the spaces with _.
In that String Array, add three items that match the names of the files in the res/drawables folder.

EXTRA CREDIT FEATURES:

1) The app can have multiple users and remembers all the data based on their username.

2) Users can see the score for quizzes in the past at the end of each quiz they take.