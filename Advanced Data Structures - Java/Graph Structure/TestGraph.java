package assignment4;

/*
You need to modify line 17 so that it points to the directory
containing the graphs. If you're running Windows you may need
to put "C:" at the beginning of the string and you'll probably
have to use "\" instead of "/".

For most IDE's I believe you can just make GraphLocation the
empty string if you put the graph files in the same directory
as your prog4.java file.
*/

import java.io.*; // for IOException

public class TestGraph {
	//C:\\Users\\ddc88190\\eclipse-workspace\\Assignment4\\src\\assignment4\\
	final static String GraphLocation = new String(
			"C:\\Users\\CSDDC\\eclipse-workspace\\Assignment4\\src\\assignment4\\");

	public static void main(String[] args) throws IOException {
		Vertex_Node startVertex;
		Graph g;

		for (int i = 1; i <= 10; i++) {
			g = new Graph();
			startVertex = g.input(GraphLocation + "Graph" + i + ".txt");
			System.out.println("Test #" + i + ":  BFS  -- " + Graph.myName());
			System.out.println("=======");
			g.output_bfs(startVertex);
			System.out.println();
			if (i == 3) {
				System.out.println("=======");
				g.output_bfs(startVertex);
				System.out.println();

			}
			if (Graph.implementedDFS()) {
				System.out.println("Test #" + i + ":  DFS  -- "
						+ Graph.myName());
				System.out.println("=======");
				g.output_dfs(startVertex);
				System.out.println();
				if (i == 3) {
					System.out.println("=======");
					g.output_dfs(startVertex);
					System.out.println();

				}
			}
		}
		System.out.println("Done with " + Graph.myName() + "'s test run.");
	}
}