package com.upwork.screener;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Store the network of nodes and check if there is a connectivity between the nodes.
 * For connectivity check, standard breadth-first-search(BFS) algorithm is used.
 * Hence the time complexity of the code base is O(V+E)
 * 		where V --> the total number of nodes in the network
 * 		  	  E -->	the total number of connections between the nodes
 * As we are using adjacency list representation, the space complexity would also be O(V+E)
 */
public class Network {
	private int V; //node count
	private LinkedList<Integer> adj[]; //adjacency list

	/**
	 * Constructor
	 * @param nodeCount count of nodes in the network
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public Network(int nodeCount) throws Exception {
		if(nodeCount > 0) {
			V = nodeCount;
			adj = new LinkedList[nodeCount];
			for (int i=0; i<nodeCount; i++)
				adj[i] = new LinkedList<Integer>();
		} else {
			throw new Exception("A network should have atleast a node");
		}
	}
		
	/**
	 * Adds direct connection between two nodes in a network
	 * @param nodeA a node
	 * @param nodeB another node
	 * @return true if successful
	 * @throws Exception when connection cannot be established
	 */
	public boolean connect(int nodeA, int nodeB) throws Exception {
		if (isValidNode(nodeA - 1) && isValidNode(nodeB - 1)) {
			if (isEqual(nodeA - 1, nodeB - 1)) {
				throw new Exception("Nodes are same. Please enter two differnt nodes");
			} else {
				connectNodes(nodeA - 1, nodeB - 1);
				return true;
			}
		} else {
			throw new Exception("Nodes should be between 1 and " + V);
		}
	}
	
	/**
	 * checks if two nodes in the network are connected
	 * @param nodeA a node
	 * @param nodeB another node
	 * @return true if they are connected and false if they are not 
	 * @throws Exception when there is an error in input elements
	 */
	public boolean query(int nodeA, int nodeB) throws Exception {
		if (isValidNode(nodeA - 1) && isValidNode(nodeB - 1)) {
			if (isEqual(nodeA - 1, nodeB - 1)) {
				throw new Exception("A node is always connected to itself.");
			} else {
				return isConnected(nodeA - 1, nodeB - 1);
			}
		} else {
			throw new Exception("Nodes should be between 1 and " + V);
		}
	}

	private boolean isValidNode(int node) {
		return ((node>=0) && (node<V));
	}
	
	private boolean isEqual(int nodeA, int nodeB) {
		return (nodeA == nodeB);
	}

	// connect nodes to one another
	private void connectNodes(int nodeA, int nodeB) {
			adj[nodeA].add(nodeB);
			adj[nodeB].add(nodeA);
		}
		
	/**
	 * Apply BFS algorithm to find connectivity between two nodes in a network 
	 * @param sourceNode a node
	 * @param destinationNode another node
	 * @return true if nodes are connected and false if they are not
	 */
	private boolean isConnected(int sourceNode, int destinationNode) {
		//mark all the nodes as not visited(by default set as false)
		boolean visited[] = new boolean[V];

		//create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		//mark the current node as visited and enqueue it
		visited[sourceNode] = true;
		queue.add(sourceNode);

		while (queue.size() != 0) {
			//dequeue a vertex from queue
			sourceNode = queue.poll();

			//'i' will be used to get all adjacent nodes of a node
			Iterator<Integer> adjacentNodeIterator = adj[sourceNode].listIterator();

			//get all adjacent vertices of the dequeued vertex 'source'
			//if a adjacent has not been visited, then mark it visited and enqueue it
			while (adjacentNodeIterator.hasNext()) {
				int n = adjacentNodeIterator.next();

				//if this adjacent node is the destination node return true
				if (n == destinationNode) {
					return true;
				}

				//else, continue to do BFS
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		
		//if BFS is complete without visiting destination
		return false;
	}
}