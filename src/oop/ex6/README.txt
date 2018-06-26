gilgil999,*********

=====================================================
classes:
=====================================================
+++main package+++
Sjavac.java - this is the main class of the project

+++parser package+++
BadLineException.java - this exception is thrown whenever there is an error in the files parsing
CloseLine.java - this class is a representation of a line with a closing bracket
MainParser.java - this class does all of the parsing of the files lines, it then converts it to a Checkable object
OpenCondition.java -this class is a representation of the first line in an if\while block
OpenFuntion.java - this class represents the first line of a function deceleration
RawFuncCall.java - this class is a representation of a function call line
RawLine.java - this interface captures all of the lines after the initial processing
RawReturn.java - this class is rawline representation of  a return line
TypeOneException.java - this Exception is thrown whenever there is a bug in the programs logic
VarDeceleration.java -  this class is a rawline representation of a variable deceleration\assignment line

+++validator package+++
Checkable.java - this interface is the interface of all of the objects in our project that can be checked to see if they are
                 logically valid
CodeSegment.java - this class represents all the codesegments that contain other lines
ConditionSegment.java - this class is a condition segment, a code that begins with a condition
FuncCall.java - this class represents a function call
FunctionObj.java - this class is a representation of a fucntion object
FunctionSegment.java - this class is a representation of a function segment
GlobalSegment.java - this class represents the entire sjava file
ReturnLine.java - this class represents a return line
ScopeObj.java - this class represents a scope
Singleline.java - this abstract class is the ancestor of all the single line checkable
VariableLine.java - this class handles all of the cases where a variable is either declared or assisnged
VarInstance.java - this class represents a variable instance, which is a representation of a variable outside the context of
                        a specific scope
VarObj.java - this class represents a variable in the context of a scope
VarOperation.java - this class represents an assignment or a decelaration of two variables


=====================================================
implementation detatils
=====================================================

=====================================================
questions
=====================================================

--- 6.1  -  error handling ---
in our project we choose to check the validity of the file using exceptions. our validity check method is IsValid().
it is a void function and it throws TypeOneException, this exception, when caught, informs the program that there was
an invalidity.
there are more areas in the code that can prove the code invalid but are not inside an isValid function, these will
throw exceptions that inherit from TypeOneException.
we choose not implement a TypeTwoException because the neccessary input validity checks are done in a confined area in
the code and dont require complex handling.

--- 6.2  -  design choices ---
the different parts of our program are:

- reading the lines and understanding their meaning (MainParser.readlines method)
    - each line is read independently
- going over the lines and validating that they follow the required logic and rules (the Checkable interface)
    - each line sould be validated using a smaller set of considerations then the whole program (ScopeObj)








