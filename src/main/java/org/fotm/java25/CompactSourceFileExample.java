/**
 * From <a href="https://openjdk.java.net/jeps/512>JEP 512</a>
 * <p>
 * This is a compact source file.
 * <p>
 * This is an instance main method.
 * <p>
 * If using the old fashioned "public static void main(String[] args)", the "public" keyword is
 * not needed.
 */
String otter = "Stephen";

void main() {
  System.out.println("""
      cd /training/udemy/pragmatic_code_school/modern-java/src/main/java
      java org/fotm/java25/CompactSourceFileExample.java
      
      or
      
      cd ~/training/udemy/pragmatic_code_school/modern-java/src/main/java/org/fotm/java25
      java CompactSourceFileExample.java
      
      or
      
      cd ~/training/udemy/pragmatic_code_school/modern-java/src/main/java/org/fotm/java25
      javac CompactSourceFileExample.java
      java CompactSourceFileExample
      """);

  // java.lang.IO new with Java 25
  // use this for reading from cmd line rather than System.in
  //
  // java.lang.IO is just for console input/output
  IO.println("something new with Java 25 java.lang.IO");

  IO.println("otter is an instance var!!! " + otter);
  fx();
}

void fx() {
  IO.println("Calling top level function");
}