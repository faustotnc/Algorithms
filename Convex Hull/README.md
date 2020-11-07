# Convex Hull
> In geometry, the convex hull or convex envelope or convex closure of a shape is the smallest convex set that contains it. The convex hull may be defined either as the intersection of all convex sets containing a given subset of a Euclidean space, or equivalently as the set of all convex combinations of points in the subset. For a bounded subset of the plane, the convex hull may be visualized as the shape enclosed by a rubber band stretched around the subset.
>
> [Convex Hull - Wikipedia.org](https://en.wikipedia.org/wiki/Convex_hull)


<div align="center">
    <img src="/ConvexHullVisual.png" style="width: 375px; height: 375px" />
</div>


In layman's terms, the Convex Hull of a set of points **`S`** is the set of points `h` that enclose all other points in **`S`**. The shape formed when connecting all the points in `h` is a the Convex Polygon with the greatest are and lowest perimeter out of all the points in **`S`**.

From the figure above, points `1`, `2`, `3, 4`, and `5` are the points in the set **`S`** that form a Convex Hull of **`S`**.