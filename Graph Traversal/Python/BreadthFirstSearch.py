

def BFS(graph, start, callback):
    """
        Recursive Breadth-First-Search Algorithm.

        Parameters
        ----------
        graph : An adjacency-list representation of a graph using dictionaries
        start : The starting vertex
        callback : A callback to execute on every vertex
    """

    discovered = []  # vertices already visited
    queue = []  # vertices to be visited

    def visit(node):
        # The BFS algorithm visits the current vertex and all of
        # its adjacent vertices at once, then proceeds to visit the
        # neighbors of the current vertex's neighbors one by one.
        for vertex in [node, *graph[node]]:
            if vertex not in discovered:
                callback(vertex)

                # If the current vertex is yet yo be visited,
                # we add it to the "discovered" list, and
                # enqueue it so it can be visited later.
                discovered.append(vertex)
                queue.append(vertex)

        # Remove the current node from the
        # queue of vertices to be visited
        if node in queue:
            queue.remove(node)

        # Keeps visiting nodes in the queue
        # until the queue is empty
        if (len(queue) > 0):
            visit(queue[0])

    # The start to the algorithm
    visit(start)


# Represent the graph using an adjacency dictionary (hash-table).
myGraph = {
    1: [2, 4, 5],
    2: [1, 3, 5],
    3: [2, 4, 5],
    4: [1, 3, 5, 6],
    5: [1, 2, 3, 4],
    6: [4]
}

# Visit every node using BFS
BFS(myGraph, 2, lambda vertex: print(vertex))
