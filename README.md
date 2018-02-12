# ConsoleCalculator
<pre>
This is a cmd string calculator. 
The calculator supports next operations
    - Addition: '+'
    - Subtraction: '-'
    - Multiplication: '*'
    - Division: '-'
    - Exponentiation '^'
    - Brackets: '()'
    
Precedence of operation:
    Brackets >> Degree >> Multiplication/Division >> Addition/Subtraction
    
Accuracy of calculations: 15 decimal places. You can change the parameter in file app.properties
You can use '.' or/and ',' like delimiter of decimal places. You will get result always with '.'

You can put spaces between numbers and operators, but you can't put spaces between digits.

Available commands:
    stop - end the programm
    help - get the help information
    
How to work with the program:
    After launch the program you should input expression and press enter.
    If calculation was successful you wil get result
    Else you will get error message.

Some examples of expressions:
    2+2*2
    (10 + 5)/15
    (10.0 + 5,6)/15
    (10.0^2 + 5,6)/10
    (2^1 + 3^2)^-1
    (2^1 + 3^2)^-(1+1)
</pre>
