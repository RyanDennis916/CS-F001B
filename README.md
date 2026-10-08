If you get an error like this:
java.lang.SecurityException: class "org.junit.runner.Describable"'s signer information does not match signer information of other classes in the same package

make sure in the .classpath file: the junit-tools.jar and jmockit.jar in the lib folder both listed at the **bottom** of all the libraries (above only the src and bin classpath entries), like this:

<!-- in the .classpath file: -->

`<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER">
		<attributes>
			<attribute name="module" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="con" path="org.eclipse.jdt.junit.JUNIT_CONTAINER/5"/>
	<classpathentry kind="lib" path="lib/jmockit.jar"/>
	<classpathentry kind="lib" path="lib/junit-tools.jar"/>
	<classpathentry kind="src" path="src"/>
	<classpathentry kind="output" path="bin"/>
</classpath>`
More information is at:
https://stackoverflow.com/a/6388117/22054075
