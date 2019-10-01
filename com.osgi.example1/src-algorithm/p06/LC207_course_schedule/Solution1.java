package p06.LC207_course_schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import common.GraphNode;

/*

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:
Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
             
Example 2:
Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
             
Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
There are several ways to represent a graph. For example, the input prerequisites is a graph represented by a list of edges. Is this graph representation appropriate?
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.

 */
/**
 * DFS solution
 * 
 * @see LC133
 */
public class Solution1 {

	/**
	 * 
	 * @param courses
	 * @param prerequisites
	 * @return
	 */
	public static boolean canFinish(int courses, int[][] prerequisites) {
		List<GraphNode> nodes = new ArrayList<GraphNode>();
		Map<Integer, GraphNode> nodeMap = new HashMap<Integer, GraphNode>();

		for (int course = 0; course < courses; course++) {
			GraphNode node = nodeMap.get(course);
			if (node == null) {
				node = new GraphNode(course);
				nodeMap.put(course, node);
				nodes.add(node);
			}
		}

		for (int[] prerequisite : prerequisites) {
			int course0 = prerequisite[0];
			int course1 = prerequisite[1];

			GraphNode node0 = nodeMap.get(course0);
			if (node0 == null) {
				node0 = new GraphNode(course0);
				nodeMap.put(course0, node0);
				nodes.add(node0);
			}

			GraphNode node1 = nodeMap.get(course1);
			if (node1 == null) {
				node1 = new GraphNode(course1);
				nodeMap.put(course1, node1);
				nodes.add(node1);
			}

			node0.outgoings.add(node1);
		}

		Stack<GraphNode> stack = new Stack<GraphNode>();

		for (int course = 0; course < courses; course++) {
			GraphNode node = nodeMap.get(course);

			stack.push(node);

			boolean canFinish = canFinishDFS(stack, node);
			if (!canFinish) {
				return false;
			}

			stack.pop();
		}

		return true;
	}

	protected static boolean canFinishDFS(Stack<GraphNode> queue, GraphNode node) {
		for (GraphNode outgoingNode : node.outgoings) {
			if (queue.contains(outgoingNode)) {
				return false;
			}
			queue.push(outgoingNode);

			boolean canFinish = canFinishDFS(queue, outgoingNode);
			if (!canFinish) {
				return false;
			}

			queue.pop();
		}

		return true;
	}

	public static void main(String[] args) {
		// Example 1:
		// Input: 2, [[1,0]]
		// Output: true
		{
			int courses = 2; // 2 courses: 0, 1
			int[][] prerequisites = new int[1][];
			prerequisites[0] = new int[] { 1, 0 };
			boolean output = canFinish(courses, prerequisites);
			System.out.println(output);
		}

		// Example 2:
		// Input: 2, [[1,0],[0,1]]
		// Output: false
		{
			int courses = 2; // 2 courses: 0, 1
			int[][] prerequisites = new int[2][];
			prerequisites[0] = new int[] { 1, 0 };
			prerequisites[1] = new int[] { 0, 1 };
			boolean output = canFinish(courses, prerequisites);
			System.out.println(output);
		}
	}

}
