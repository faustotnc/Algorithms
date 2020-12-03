

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
        # The DFS algorithm visits the current vertex,
        # then proceeds to visit its neighbors one-by-one,
        # then all of its neighbor's neighbors, and so on
        # until a leaf is reached, or until a vertex that
        # has all visited neighbors is reached.
        if (node not in visited):
            visited.append(node)
            callback(node)

            for vertex in graph[node]:
                visit(vertex)

    # The start of the algorithm
    visit(start)


# Represent the graph using an adjacency dictionary (hash-table)
myGraph = {
    1: [2, 4],
    2: [1, 3, 8],
    3: [2],
    4: [1, 8],
    8: [2, 4]
}

# Visit every node using DFS
DFS(myGraph, 3, lambda vertex: print(vertex))
