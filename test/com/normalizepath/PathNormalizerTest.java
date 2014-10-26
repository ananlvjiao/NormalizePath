package com.normalizepath;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PathNormalizerTest {
	
	@Test
	public void testNullShouldReturnEmpty() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("null path should return empty",
				"", pathNormalizer.normalize(null));
	}
	
	@Test
	public void testEmptyShouldReturnEmpty() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("empty path should return empty",
				"", pathNormalizer.normalize(""));
	}
	
	
	@Test
	public void testOnlySlashesShouldStaySame() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("only slash path should stay the same",
				"/", pathNormalizer.normalize("/"));
		assertEquals("only slashes path should stay the same",
				"///", pathNormalizer.normalize("///"));
	}
	
	@Test
	public void testDoubleSlashesShouldStaySame() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("double slashes of the path should stay the same",
				"foo//bar", pathNormalizer.normalize("foo//bar"));
	}
	
	@Test
	public void testMultipleSlashesShouldStaySame() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("multiple slashes of the path should stay the same",
				"foo////bar", pathNormalizer.normalize("foo////bar"));
	}
	
	@Test
	public void testMultipleDotsShouldStaySame() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("multiple dots of the path should stay the same",
				"foo/.../bar", pathNormalizer.normalize("foo/.../bar"));
	}
	
	@Test
	public void testLastSlashShouldStaySame() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("last slash of the path should stay the same",
				"foo//bar/", pathNormalizer.normalize("foo//bar/"));
	}
	@Test
	public void testLastMultipleSlashesShouldStaySame() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("last multiple slashes of the path should stay the same",
				"foo//bar//", pathNormalizer.normalize("foo//bar//"));
	}
	
	@Test
	public void testSingleDotShouldBeRemovedCornerCases(){
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("only single dot of the path should return empty",
				"", pathNormalizer.normalize("."));
		assertEquals("single dot of the path with slash should return empty",
				"", pathNormalizer.normalize("./"));
		assertEquals("single dot of the path with slash should remove path after them",
				"//", pathNormalizer.normalize("//./"));
	}
	@Test
	public void testSingleDotShouldBeRemoved() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("single dot of the path with slash should return path after them",
				"test", pathNormalizer.normalize("./test"));
		assertEquals("single dot of the path with slash should return path after them",
				"/test", pathNormalizer.normalize("/./test"));
		assertEquals("single dot of the path should be removed",
				"foo/bar", pathNormalizer.normalize("foo/./bar"));
		assertEquals("single dot of the path should be removed",
					"/foo/bar", pathNormalizer.normalize("/foo/./bar"));
		assertEquals("single dot of the path should be removed",
				"foo/bar", pathNormalizer.normalize("./foo/./bar"));
		assertEquals("single dot of the path should be removed",
				"/foo/bar", pathNormalizer.normalize(".//foo/./bar"));
		
		assertEquals("single dot of the path should be removed",
				"//foo///bar", pathNormalizer.normalize("//foo//.//bar"));
	}
	
	@Test
	public void testTrailSingleDotShouldBeRemoved() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("trail single dot of the path should be removed",
				"bar/", pathNormalizer.normalize("bar/."));
		assertEquals("trail single dot of the path should be removed",
				"bar//", pathNormalizer.normalize("bar//."));
		assertEquals("single dot of the path should be removed",
				"/foo/bar/", pathNormalizer.normalize("/foo/./bar/."));
		assertEquals("single dot of the path should be removed",
				"/foo/bar//", pathNormalizer.normalize("/foo/./bar//."));
	}
	
	@Test
	public void testDoubleDotsShouldBeRemovedWithParentDirectoryCornerCases() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("only double dots of the path should return empty",
				"", pathNormalizer.normalize(".."));
		assertEquals("double dots of the path with slash should return empty",
				"", pathNormalizer.normalize("../"));
		assertEquals("only double dots of the path should return slash",
				"/", pathNormalizer.normalize("/.."));
		assertEquals("only double dots of the path with slash should keep slash",
				"/", pathNormalizer.normalize("/../"));
		assertEquals("only double dots of the path with slashes should keep slashes",
				"//", pathNormalizer.normalize("/..//"));
	}
	
	@Test
	public void testDoubleDotsShouldBeRemovedWithParentDirectory() {
		PathNormalizer pathNormalizer = new PathNormalizer();
	
		assertEquals("double dots of the path should be removed with parent directory",
				"foo/baz", pathNormalizer.normalize("foo/bar/../baz"));
		assertEquals("trail double dots of the path should be removed with parent directory",
				"foo/", pathNormalizer.normalize("foo/bar/../baz/.."));
		assertEquals("trail double dots with slash should be removed with parent directory and keep slash",
				"foo/", pathNormalizer.normalize("foo/bar/../baz/../"));
		assertEquals("trail double dots with slash should be removed with parent directory and keep slashes",
				"foo//", pathNormalizer.normalize("foo/bar/../baz/..//"));
		assertEquals("double dots of the path should be removed with parent directory",
				"/foo/baz", pathNormalizer.normalize("/foo/bar/../baz"));
		assertEquals("double dots of the path should be removed with parent directory",
				"/foo//baz", pathNormalizer.normalize("/foo/bar/..//baz"));

	}
	
	@Test
	public void testMultipleDoubleDotsShouldBeRemovedWithParentDirectory() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("multiple trail double dots with slash should be removed with parent directories and keep slash",
				"", pathNormalizer.normalize("foo/bar/../baz/../../../"));
	}
	
	
	@Test
	public void testTrailDoubleDotsShouldBeRemovedWithParentDirectory() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("trail double dots of the path should be removed with parent directories",
				"", pathNormalizer.normalize("foo/bar/../baz/../.."));
		assertEquals("trail double dots of the path should be removed with parent directories",
			"/", pathNormalizer.normalize("/baz/.."));
		assertEquals("multiple trail double dots of the path should be removed with parent directories",
			"", pathNormalizer.normalize("foo/bar/../baz/../../.."));
	
	}
	
	@Test
	public void testSingleDotWithDoubleDotsShouldBeRemoved() {
		PathNormalizer pathNormalizer = new PathNormalizer();
		assertEquals("single dot and double dots of the path should be removed",
				"foo/baz", pathNormalizer.normalize("./foo/bar/../baz"));
		assertEquals("single dot and double dots of the path should be removed",
				"foo/baz", pathNormalizer.normalize("./foo/./bar/../baz"));
		assertEquals("single dot and double dots of the path should be removed",
				"baz", pathNormalizer.normalize("./foo/../bar/../baz"));
		assertEquals("single dot and double dots of the path should be removed",
				"bar/", pathNormalizer.normalize("./foo/../bar/./baz/.."));
	}
}
