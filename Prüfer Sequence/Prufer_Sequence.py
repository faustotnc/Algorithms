class CreatePruferSequence:
    def __init__(self, tree):
        self.tree = dict(tree)
        self.CayleysNumber = len(list(tree)) - 2

    def getSmallestLeaf(self, the_tree):
        """ Gets the leaf with the smallest value in a tree. """
        # We start bt saying that the smallest leaf is a leaf that does not exist
        # in the tree. To do that, we look for the lasgest node, and add 10 to it.
        current_smallest_leaf = sorted(
            [v for v in the_tree.keys()], reverse=True)[0] + 10

        # We loop trough all the nodes in the tree to find the smallest leaf
        for node in the_tree:
            if len(the_tree[node]) == 1 and current_smallest_leaf > node:
                current_smallest_leaf = node

        # and return the smallest leaf
        return current_smallest_leaf

    def sequence(self):
        """ Creates a Prufer Sequence from a tree. """

        seq = []  # We start with an empty sequence
        for index in range(len(list(self.tree))):
            # We only loop over the tree as long as there are
            # more than two nodes in the tree
            if len([v for v in self.tree.keys()]) > 2:
                # gets the current smallest leaf
                smallest_leaf = self.getSmallestLeaf(self.tree)

                # gets the neighbor for the current smallest leaf
                leaf_neighbor = self.tree[smallest_leaf][0]

                # We remove the smallest leaf from self.tree
                self.tree.pop(smallest_leaf)

                # And we also remove it from the neighbor's list
                self.tree[leaf_neighbor].pop(
                    self.tree[leaf_neighbor].index(smallest_leaf)
                )

                # We add the leaf's neighbor's value to the sequence :: var<seq>
                seq.insert(index, leaf_neighbor)

        return seq
