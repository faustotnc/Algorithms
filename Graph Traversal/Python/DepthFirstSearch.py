

def DFS(graph, start, callback):
    """
        Recursive Depth-First-Search Algorithm.

        Parameters
        ----------
        graph : An adjacency-list representation of a graph using dictionaries
        start : The starting vertex
        callback : A callback to execute on every vertex
    """

    visited = []

    def visit(node):
        visited.append(node)
        callback(node)

        for vertex in graph[node]:
            if (vertex not in visited):
                visit(vertex)

    visit(start)


# Represent the graph using a dictionary (hash-table)
myGraph = {
    1: [2, 4],
    2: [1, 3, 8],
    3: [2],
    4: [1],
    8: [2]
}

# Visit every node using DFS
DFS(myGraph, 3, lambda vertex: print(vertex))
