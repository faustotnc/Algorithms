# A* Algorithm ⭐️

> A* (pronounced "A-star") is a graph traversal and path search algorithm, which is often used in many fields of computer science due to its completeness, optimality, and optimal efficiency.
>
> Peter Hart, Nils Nilsson and Bertram Raphael of Stanford Research Institute (now SRI International) first published the algorithm in 1968. It can be seen as an extension of Dijkstra's algorithm. A* achieves better performance by using heuristics to guide its search.
>
>
>[Wikipedia – A* search algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm)

## How to use
1) Run the program
2) Select a starting tile
3) Select an end tile
    - Once an end tile is selected, the program will automatically look for a path between the start and the end tile
4) When the path is found, or the program determines a path cannot be found, select new tiles as the start and end nodes.
    - When a new start tile is selected, the board will be cleared automatically

## Changing the board size
The dimensions of the board and the percentage of tiles blocked can be modified in the `Main.java` file.