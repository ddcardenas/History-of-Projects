//	Daniel Cardenas
//	COMP282 Monday/Wednesday class
//	Assignment #4
//	Turn in 12/10/18
//	Contained in this file is the data structure for a Graph
//		as well as a method for outputting Breadth First Search
//		and Depth First Search while displaying:
//		name, distance from root, and name of parent node
package assignment4;
import java.io.*; // for BufferedReader 
import java.util.*; // for StringTokenizer 

class Edge_Node { 
	private Vertex_Node target; 
	private Edge_Node next; 
	
	public Edge_Node(Vertex_Node t, Edge_Node e) {
		target = t; 
		next = e;
	} 
	public Vertex_Node getTarget() { 
		return target;
	}
	public Edge_Node getNext() { 
		return next;
	}	
	// no setters needed
} 

class Vertex_Node { 
	private String name; 
	private Edge_Node edge_head; 
	private int distance; 
	private Vertex_Node parent; 
	private Vertex_Node next; 
	boolean used;
	//returns whether vertex node has been used 
	//	(if has been used then returns true)
	public boolean isUsed() {
		return used;
	}
	//marks vertex node as having been used or not
	public void setUsed(boolean truth_value) {
		used = truth_value;
	}	
	public Vertex_Node(String s, Vertex_Node v) { 
		name = s; 
		next = v; 
		distance = -1; 
	}	
	public String getName() { 
		return name;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int d) { 
		distance = d;
	}
	public Edge_Node getNbrList() {
		return edge_head;
	}
	public void setNbrList(Edge_Node e) {
		edge_head = e;
	}
	public Vertex_Node getNext() {
		return next;
	}
	public Vertex_Node getParent() {
		return parent;
	}
	public void setParent(Vertex_Node n) {
		parent = n;
	}
}

class Graph { 
	private Vertex_Node head; 
	private int size; 
	
	public Graph() { 
		head = null; 
		size = 0; 
	} 	
	// reset all distance values to -1 
	// used to initialize graph
	public void clearDist() { 
		head.setDistance(-1);
		Vertex_Node focusNode = head;
		while (focusNode.getNext() != null) {
			focusNode.getNext().setDistance(-1);
			focusNode = focusNode.getNext();
		}		
	} 	
	// resets all used markers to false
	// used to initialize graph
	public void clearUsed() {
		head.setUsed(false);
		Vertex_Node focusNode = head;
		while (focusNode.getNext() != null) {
			focusNode.getNext().setUsed(false);
			focusNode = focusNode.getNext();
		}
	}
	
	public Vertex_Node findVertex(String s) { 
		Vertex_Node pt = head; 
		while (pt != null && s.compareTo(pt.getName()) != 0) 
			pt = pt.getNext(); 
		return pt; 
	} 	
	public Vertex_Node input(String fileName) throws IOException { 
		String inputLine, sourceName, targetName; 
		Vertex_Node source = null, target; 
		Edge_Node e; 
		StringTokenizer input; 
		BufferedReader inFile = new BufferedReader(new FileReader(fileName)); 
		inputLine = inFile.readLine(); 
		while (inputLine != null) { 
			input = new StringTokenizer(inputLine);
			sourceName = input.nextToken(); 
			source = findVertex(sourceName); 
			if (source == null) { 
				head = new Vertex_Node(sourceName, head); 
				source = head; 
				size++; 
			} 
			if (input.hasMoreTokens()) { 
				targetName = input.nextToken(); 
				target = findVertex(targetName); 
				if (target == null) { 
					head = new Vertex_Node(targetName, head); 
					target = head; 
					size++; 
				} 
				
				// put edge in one direction -- while checking for repeat 
				e = source.getNbrList(); 
				while (e != null) { 
					if (e.getTarget() == target) { 
						System.out.print("Multiple edges from " + source.getName() + " to "); 
						System.out.println(target.getName() + "."); 
						break; 
					} 
					e = e.getNext(); 
				} 
				source.setNbrList(new Edge_Node(target, source.getNbrList())); 
				
				// put edge in the other direction 
				e = target.getNbrList(); 
				while (e != null) { 
					if (e.getTarget() == source) { 
						System.out.print("Multiple edges from " + target.getName() + " to "); 
						System.out.println(source.getName() + "."); 
						break; 
					} 
					e = e.getNext(); 
				} 
				target.setNbrList(new Edge_Node(source, target.getNbrList())); 
			} 
			inputLine = inFile.readLine();
		} 
		inFile.close(); 
		return source;
	} 
	// You might find this helpful when debugging so that you 
	// can see what the graph actually looks like 
	public void output() { 
		Vertex_Node v = head; 
		Edge_Node e; 
		while (v != null) { 
			System.out.print(v.getName() + ":  "); 
			e = v.getNbrList(); 
			while (e != null) { 
				System.out.print(e.getTarget().getName() + "  "); 
				e = e.getNext(); 
			} 
			System.out.println(); 
			v = v.getNext(); 
		}
	} 	
	
	/**********************************************/
	public void output_bfs(Vertex_Node s) { 
		clearDist();
		clearUsed();
		
		perform_bfs(s);
		
		//check each vertices to see if any are unmarked
		Vertex_Node focus = head;		
		do {
			if (!focus.isUsed()) {
				//perform traditional output of bfs using focus
				perform_bfs(focus);
			}
			focus = focus.getNext();
		} while (focus.getNext() != null);	
		
	} //end output_bfs
	
	private void perform_bfs(Vertex_Node s) {
		int dist_counter = 0;
		Queue<Vertex_Node> q = new LinkedList<>(); //create Queue
		s.setUsed(true); //mark vertex as having been used
		q.add(s); //add vertex node to queue
		s.setDistance(dist_counter);
		System.out.println(s.getName() + ", " + s.getDistance() + ", null");
		
		while (q.peek() != null) { //while queue is not empty
			//remove vertex from queue and assign it to a focus node (w)
			Vertex_Node w = q.remove();
			//w is now the parent to the subsequent edge nodes
			//step through each unvisited vertex (u) adjacent to focus node (w)
			for (Edge_Node focusEdge = w.getNbrList(); 
					focusEdge != null; focusEdge = focusEdge.getNext()) 
			{
				//if the target is marked true, don't continue
				if (!focusEdge.getTarget().isUsed()) {
					//mark vertex being targeted as used
					focusEdge.getTarget().setUsed(true);
					//dist is 1+parent's dist
					focusEdge.getTarget().setDistance(w.getDistance()+1);
					System.out.println(focusEdge.getTarget().getName() 
							+ ", " + focusEdge.getTarget().getDistance() 
							+ ", " + w.getName());
					//add vertex being targeted to queue
					q.add(focusEdge.getTarget());
				} //end if conditional
			} //end for loop
		} //end while loop
	} //end perform_bfs

	/*********************************************/
	public void output_dfs(Vertex_Node s) { 
		clearDist();
		clearUsed();
		
		perform_dfs(s);
		
		//check each vertices to see if any are unmarked
		Vertex_Node focus = head;		
		do {
			if (!focus.isUsed()) {
				//perform traditional output of bfs using focus
				perform_dfs(focus);
			}
			focus = focus.getNext();
		} while (focus.getNext() != null);
		
	} //end output_dfs
	
	private void perform_dfs(Vertex_Node s) {
		int dist_counter = 0;
		Stack<Vertex_Node> stack = new Stack<Vertex_Node>();
		
		s.setUsed(true);
		s.setDistance(dist_counter);
		stack.push(s);
		
		Vertex_Node focus_vertex = s;
		System.out.println(s.getName() + ", " + s.getDistance() + ", null");
		//while stack is not empty
		while (!stack.isEmpty()) {
			//initialize found boolean
			boolean umnfound = false;
			//check for an unmarked/unused neighbor but stop when one is found
			Edge_Node focus_edge = focus_vertex.getNbrList();
			while (focus_edge != null && !umnfound) {
				//check if neighbor is not marked/used
				if (!focus_edge.getTarget().isUsed()) {
					//if neighbor/target is not marked, 
					//	then add to stack AND mark used AND output
					// 	AND make that vertex your focus
					stack.push(focus_edge.getTarget());
					focus_edge.getTarget().setUsed(true);
					dist_counter++;
					focus_edge.getTarget().setDistance(dist_counter);
					System.out.println(focus_edge.getTarget().getName() 
							+ ", " + focus_edge.getTarget().getDistance() 
							+ ", " + focus_vertex.getName());
					
					focus_vertex = focus_edge.getTarget();
					umnfound = true;
				}
					focus_edge = focus_edge.getNext();
			} //end while loop for checking unmarked neighbors 
			//if all neighbors are marked/used
			//	then umnfound will be false
			if (!umnfound) {
				//if no unmarked neighbor found,
				//	then remove from stack
				if (!stack.empty())
					stack.pop();
				//make next vertex node in stack the new focus vertex
				if (!stack.empty()) {
					focus_vertex = stack.peek();
					dist_counter = focus_vertex.getDistance();
				}
			}		
		} //end while loop for checking if stack is empty
	} //end perform_dfs method
	
	// If you implemented DFS then leave this method the way it is 
	// If you did not implement DFS then change the “true” to “false” 
	public static boolean implementedDFS() { 
		return true; 
	} 
	public static String myName() { 
		return "Daniel D Cardenas"; 
	}
}