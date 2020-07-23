# Prüfer Sequence Generator

As defined by Wolfram MathWorld:

> Prüfer's bijection is based on the fact that every tree has at least two nodes of degree 1 (i.e., tree leaves. Therefore, the node ``v`` which is incident to the lowest labeled leaf is uniquely determined, and ``v`` is then taken as the first symbol in the code. This lowest labeled leaf is then deleted and the procedure is repeated until a single edge is left, giving a total of ``n-2`` integers between 1 and ``n`` (Skiena 1990).
>
> Source: https://mathworld.wolfram.com/PrueferCode.html

## Run the Code
Run the following code with the ``python3`` command.
```python
from Prufer_Sequence import CreatePruferSequence

# We represent the tree as an adjacency dictionary:
tree = {
    1: [2, 3, 4],   2: [1, 6],
    3: [1, 5, 7],   4: [1],
    5: [3, 8],      6: [2],
    7: [3],         8: [5]
}

# The class takes in a tree represented as an adjacency dictionary:
s_q1 = CreatePruferSequence(tree)

# We can obtain the sequence by calling CreatePruferSequence.sequence()
sequence = s_q1.sequence()

# Print out the sequence
print('Sequence #1:', sequence)
```