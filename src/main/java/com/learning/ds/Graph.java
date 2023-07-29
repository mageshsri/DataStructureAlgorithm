package com.learning.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph<T> {

	List<VertexNode<T>> vertexNodes = new ArrayList<>();
	Map<T, Integer> vertexIndexMap = new HashMap<>();

	static class VertexNode<T> {
		T value;
		Neibhour adjacencyList;

		public VertexNode(T value) {
			super();
			this.value = value;
		}

	}

	static class Neibhour {
		int vertexNodeIndex;
		int weight;
		Neibhour next;

		public Neibhour(int vertexNodeIndex) {
			super();
			this.vertexNodeIndex = vertexNodeIndex;
		}

		public Neibhour(int vertexNodeIndex, int weight) {
			super();
			this.vertexNodeIndex = vertexNodeIndex;
			this.weight = weight;
		}
	}

	public void addVertexNode(T value) {
		vertexNodes.add(new VertexNode<>(value));
		vertexIndexMap.put(value, vertexNodes.size() - 1);
	}

	public void addEdge(T srcNode, T tgtNode) {
		addEdge(srcNode, tgtNode, -1);
	}

	public void addEdge(T srcNode, T tgtNode, int weight) {
		int tgtNodeIndx = vertexIndexMap.get(tgtNode);
		VertexNode<T> srcVertexNode = vertexNodes.get(vertexIndexMap.get(srcNode));
		Neibhour neibhour = new Neibhour(tgtNodeIndx, weight);
		if (srcVertexNode.adjacencyList == null) {
			srcVertexNode.adjacencyList = neibhour;
		} else {
			Neibhour lastNeibhour = srcVertexNode.adjacencyList;
			while (lastNeibhour.next != null)
				lastNeibhour = lastNeibhour.next;
			lastNeibhour.next = neibhour;
		}
	}

	public void bfs() {
		System.out.println("Breadth First Search");
		boolean[] visited = new boolean[vertexNodes.size()];
		Arrays.fill(visited, false);
		for (int i = 0; i < vertexNodes.size(); i++) {
			if (!visited[i])
				bfs(vertexNodes.get(i), visited);
		}

	}

	private void bfs(VertexNode<T> vertexNode, boolean[] visited) {
		LinkedList<VertexNode<T>> queue = new LinkedList<>();
		System.out.println("Visted Node " + vertexNode.value);
		visited[vertexIndexMap.get(vertexNode.value)] = true;
		queue.add(vertexNode);
		while (!queue.isEmpty()) {
			VertexNode<T> node = queue.removeFirst();
			Neibhour neibhour = node.adjacencyList;
			while (neibhour != null) {
				if (!visited[neibhour.vertexNodeIndex]) {
					System.out.println("Visted Node " + vertexNodes.get(neibhour.vertexNodeIndex).value);
					visited[neibhour.vertexNodeIndex] = true;
					queue.add(vertexNodes.get(neibhour.vertexNodeIndex));
				}
				neibhour = neibhour.next;
			}
		}
	}

	public void dfs() {
		System.out.println("Depth First Search");
		boolean[] visited = new boolean[vertexNodes.size()];
		Arrays.fill(visited, false);
		for (int i = 0; i < vertexNodes.size(); i++) {
			if (!visited[i])
				dfs(vertexNodes.get(i), visited);
		}

	}

	private void dfs(VertexNode<T> vertexNode, boolean[] visited) {
		System.out.println("Visited Node " + vertexNode.value);
		visited[vertexIndexMap.get(vertexNode.value)] = true;
		Neibhour neibhor = vertexNode.adjacencyList;
		while (neibhor != null) {
			if (!visited[neibhor.vertexNodeIndex]) {
				dfs(vertexNodes.get(neibhor.vertexNodeIndex), visited);
			}
			neibhor = neibhor.next;
		}

	}

	public List<T> findToplogicalOrder() {
		List<T> sortedList = new ArrayList<>();
		LinkedList<VertexNode<T>> queue = new LinkedList<>();
		int[] indegreeArray = new int[vertexNodes.size()];
		Arrays.fill(indegreeArray, 0);
		for (int i = 0; i < vertexNodes.size(); i++) {
			Neibhour neibhour = vertexNodes.get(i).adjacencyList;
			while (neibhour != null) {
				indegreeArray[neibhour.vertexNodeIndex] = indegreeArray[neibhour.vertexNodeIndex] + 1;
				neibhour = neibhour.next;
			}
		}

		for (int i = 0; i < vertexNodes.size(); i++) {
			if (indegreeArray[i] == 0) {
				queue.add(vertexNodes.get(i));
			}
		}

		while (!queue.isEmpty()) {
			VertexNode<T> vertexNode = queue.removeFirst();
			sortedList.add(vertexNode.value);
			Neibhour neibhour = vertexNode.adjacencyList;
			while (neibhour != null) {
				indegreeArray[neibhour.vertexNodeIndex] = indegreeArray[neibhour.vertexNodeIndex] - 1;
				if (indegreeArray[neibhour.vertexNodeIndex] == 0) {
					queue.add(vertexNodes.get(neibhour.vertexNodeIndex));
				}
				neibhour = neibhour.next;
			}
		}
		return sortedList;
	}

	public int[] singleSourceShortestPath(T value) {
		int[] distance = new int[vertexNodes.size()];
		boolean[] visited = new boolean[vertexNodes.size()];
		Arrays.fill(visited, false);
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[vertexIndexMap.get(value)] = 0;
		for (int i = 0; i < vertexNodes.size(); i++) {
			int minimunDistVertNode = getMinimumDistance(distance, visited);
			visited[minimunDistVertNode] = true;
			Neibhour neibhour = vertexNodes.get(minimunDistVertNode).adjacencyList;
			while (neibhour != null) {
				distance[neibhour.vertexNodeIndex] = Math.min(distance[neibhour.vertexNodeIndex],
						distance[minimunDistVertNode] + neibhour.weight);
				neibhour = neibhour.next;
			}
		}
		return distance;
	}

	private int getMinimumDistance(int[] distance, boolean[] visited) {
		int minDist = Integer.MAX_VALUE;
		int minDistIndx = 0;
		for (int i = 0; i < distance.length; i++) {
			if (!visited[i] && distance[i] < minDist) {
				minDist = distance[i];
				minDistIndx = i;
			}
		}
		return minDistIndx;
	}

	public static void main(String[] args) {
		Graph<String> graph = new Graph<>();
		graph.addVertexNode("C");
		graph.addVertexNode("A");
		graph.addVertexNode("B");
		graph.addVertexNode("D");
		graph.addVertexNode("E");
		graph.addEdge("C", "D");
		graph.addEdge("D", "E");
		graph.addEdge("C", "A");
		graph.addEdge("B", "D");
		graph.addEdge("A", "B");
		graph.dfs();
		graph.bfs();
		System.out.println("Traverse Graph in Topolgical Order " + graph.findToplogicalOrder());
		Graph<Integer> spGraph = new Graph<>();
		spGraph.addVertexNode(1);
		spGraph.addVertexNode(2);
		spGraph.addVertexNode(3);
		spGraph.addVertexNode(4);
		spGraph.addVertexNode(5);
		spGraph.addVertexNode(6);
		spGraph.addVertexNode(7);
		spGraph.addEdge(1, 2, 10);
		spGraph.addEdge(1, 3, 80);
		spGraph.addEdge(2, 3, 6);
		spGraph.addEdge(2, 5, 20);
		spGraph.addEdge(3, 4, 70);
		spGraph.addEdge(5, 6, 50);
		spGraph.addEdge(5, 7, 10);
		spGraph.addEdge(7, 6, 5);
		System.out.println(
				" single source shortest path from Node 1 :: " + Arrays.toString(spGraph.singleSourceShortestPath(1)));
	}

}
