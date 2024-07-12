MAHMOUDI Mohammadmehdi 322CB Homework : 2

Main.java :
in this class I have written the readFile() method that returns everything contained in the .in file.
I wrote the executeCommand() method that receives all the commands as one
string and run all commands. in the main method I wrote a switch as the basis
name the test calls the executeCommand() method.

Student.java :
in this class I wrote five variables name, studentType, average, references and
hasUnCurs and then I wrote a constructor that receives two values ​​when we make an object
of the type this class. I wrote the methods getName(), getStudentTip(), setAverage(),
getAverage(), setPreferences(), getPreferences(), setAreUnCurs() and getAreUnCurs().

Course.java :
in this class I wrote three variables students Enrollment, name and capacityMaximum
and then I wrote a constructor that receives two values ​​when we create an object
of the type this class. I wrote the getName(), getMaximumCapacity() methods,
getStudentiInscrisi() and addStudent() which adds a new student.

Secretariat.java :
in this class I wrote two variables students and courses and then I wrote one
constructor that initializes variables we have already written when we make a
object of type this class. writeOutFile() which adds a string to the file
.out or creates it, addStudent() which adds a Student object to
students, readNoteFilesInFolder() which reads all files named "note_"*
and returns everything that exists in a single string, sortLinesByGradeAndName() which
sort string notes by grades and student name, checkIfStudentExists()
which checks if a student already exists, updateStudentMedie() which updates
averages, addStudentNote() which sets an average for the student, addPreferences()
which adds preferences for a student, addCurs() which adds an object
of type Course in courses, I wrote the inner class StudentExistsException() which has
a StudentExistsException() method and extend the Exception class, add StudentLaCurs()
which adds a student to a course according to the homework requirement, assignment() which receives
grades and distributes to students in order of grades and using the method
addStudentToCourse(), findStudentByName() and findCourseByName() which find
a course or studnet by name, post() which returns a string containing information
about nameStudent, displaysCours() which returns a string containing information about
a course, showInscrisi() returns students who are assigned to courseName.
