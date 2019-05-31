function sum(a, b) {
    return a + b;
}

var LinkedList = Java.type("java.util.LinkedList");
var ArrayOfInt = Java.type("int[]");
var printer = java.lang.System.out;

var list = new LinkedList();
list.add(1);
list.add(2);

print("the list is:" + list);

var array = new ArrayOfInt(3);
printer.println("the length of array is : "+ array.length);