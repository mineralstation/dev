package p07.LC071_simplify_path;

import java.util.ArrayList;
import java.util.List;

/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

click to show corner cases.

Corner Cases:

Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".

 */
public class Solution {

	public static String simplifyPath(String path) {
		List<String> list = new ArrayList<String>();
		String[] segments = path.split("/");
		for (String segment : segments) {
			if (segment.isEmpty() || ".".equals(segment)) {
				continue;
			}
			if ("..".equals(segment)) {
				if (!list.isEmpty()) {
					list.remove(list.size() - 1);
				}
			} else {
				list.add(segment);
			}
		}

		String result = "";
		if (list.isEmpty()) {
			result = "/";
		} else {
			for (String segment : list) {
				result += "/" + segment;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// path = "/../", => "/"
		{
			System.out.println("--- --- --- --- --- --- --- --- --- --- --- ---");
			String path = "/../";
			System.out.println("Input: path = " + path);
			String output = simplifyPath(path);
			System.out.println("Output: path = " + output);
			System.out.println();
		}

		// path = "/home//foo/", => "/home/foo"
		{
			System.out.println("--- --- --- --- --- --- --- --- --- --- --- ---");
			String path = "/home//foo/";
			System.out.println("Input: path = " + path);
			String output = simplifyPath(path);
			System.out.println("Output: path = " + output);
			System.out.println();
		}

		// path = "/home/", => "/home"
		{
			System.out.println("--- --- --- --- --- --- --- --- --- --- --- ---");
			String path = "/home/";
			System.out.println("Input: path = " + path);
			String output = simplifyPath(path);
			System.out.println("Output: path = " + output);
			System.out.println();
		}

		// path = "/a/./b/../../c/", => "/c"
		{
			System.out.println("--- --- --- --- --- --- --- --- --- --- --- ---");
			String path = "/a/./b/../../c/";
			System.out.println("Input: path = " + path);
			String output = simplifyPath(path);
			System.out.println("Output: path = " + output);
			System.out.println();
		}
	}

}
