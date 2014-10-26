Normalize Path Project
========================

-- All single dot components of the path must be removed. For example, "foo/./bar" should be normalized to "foo/bar". 

-- All double dots components of the path must be removed, along with their parent directory. For example, "foo/bar/../baz" should be normalized to "foo/baz”. 

-- DO NO MORE

Setup
=====

project can be downloaded and opened with eclipse

cd to /bin folder and run with command line:

java -cp NormalizePath.jar com.normalizepath.TestRunner 


Assumptions
===========

Solution should only deal with single dot and double dots and do not try to do anything more.

Single dot
----
Single dot with slash will be removed; if there is no slash after dot, only dot will be removed. After above operation, if there are multiple slashes together, do not do any further operation.

For example:
----
./test => test

/./test => /test

bar/. => bar/

bar//. => bar//

.//foo/./bar => /foo/bar

Double dots
-----
Double dots with slash will be removed along with parent directory; if there is no slash after double dots, only double dots along with parent directory will be removed. After above operation, if there are multiple slashes together, do not do any further operation.

For example:
-----
/baz/.. => /

foo/bar/../baz => foo/baz

/foo/bar/..//baz => /foo//baz

More test cases
----------------
[Click here to see the test file] (test/com/normalizepath/PathNormalizerTest.java)




