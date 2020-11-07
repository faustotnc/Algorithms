# Convex Hull
> In geometry, the convex hull or convex envelope or convex closure of a shape is the smallest convex set that contains it. The convex hull may be defined either as the intersection of all convex sets containing a given subset of a Euclidean space, or equivalently as the set of all convex combinations of points in the subset. For a bounded subset of the plane, the convex hull may be visualized as the shape enclosed by a rubber band stretched around the subset.
>
> [Convex Hull - Wikipedia.org](https://en.wikipedia.org/wiki/Convex_hull)


<p align="center">
    <img src="/Convex Hull/ConvexHullVisual.png" width="375px">
</p>


In layman's terms, the Convex Hull of a set of points **`S`** is the set of points `h` in **`S`** that form a Convex Polygon **P** enclosing all other points in **`S`** such that the area of **P** is maximized and the perimeter is minimized.

From the figure above, points `1`, `2`, `3`, `4`, and `5` are the points in the set that form the Convex Hull of **`S`**. Adding any other points to the set will increase the perimeter of the polygon and will decrease its area.
