https://www.infoq.com/news/2011/03/osgi-43
A side effect means that it's now possible to install multiple versions of the same bundle/version pair in the same framework, 
provided that these are hidden from each other. Previously, it would be an error to attempt to install the same bundle a second 
time. This won't be enabled by default, but it will be possible to mark a bundle as being capable of doing this with an attribute 
org.osgi.framework.bsnversion=multiple in the properties when launching framework. 
By default, it is set to org.osgi.framework.bsnversion=single.

https://www.knopflerfish.org/releases/current/docs/bundledoc/index.html?docpage=framework/index.html
https://stackoverflow.com/questions/21379379/installing-two-bundles-with-the-same-file
-Dorg.osgi.framework.bsnversion=multiple

org.osgi.framework.bsnversion	
Allow installation of multiple bundles with the same bundle symbolic name or restrict this. 
The property can have the following values:
------------------------------------------------------------------------------------------------------------
single		A combination of equal bundle symbolic name and equal version is unique in the framework. 
			Installing a second bundle with the same bundle symbolic name and version is an error.

multiple	The combination of bundle symbolic name and version is not unique in the framework.

managed		Using a Bundle Collision Hook to filter any non-colliding bundles.
------------------------------------------------------------------------------------------------------------
