# RecursGet
A java site mirrorer built in my spare time

## How to use
Currently only available in a command-line enviornment.

rget <remote file/page> <local directory>

If you are using the .exe file, simply type the executable name to run it. If not, you may use the built in java command if you have Java installed.

```java -jar rget-cli.jar (arguments here)```

Note that it will mirror the site where you specify where to put it. For example:
Pointing the command towards C:\somefolder will take the URL (eg. cardboardstorage.online/downloads/examplefile.zip) and create directories based on the URL.
C:\somefolder turns into C:\somefolder\downloads\examplefile.zip
