from Prufer_Sequence import CreatePruferSequence


# A simple tree
tree = {
    1: [2, 3],          2: [1, 4, 5],           3: [1, 6, 7],
    4: [2, 8, 9],       5: [2, 10, 11],         6: [3, 12, 13],
    7: [3, 14, 15],     8: [4],                 9: [4],
    10: [5],            11: [5],                12: [6],
    13: [6],            14: [7],                15: [7]
}
s_q1 = CreatePruferSequence(tree)
print('Sequence #1:', s_q1.sequence(), '\n')


# A Tree with medium complexity
tree = {
    1: [7, 4, 6],       2: [12],
    3: [4],             4: [3, 1],
    5: [10],            6: [1, 8],
    7: [1],             8: [6, 12, 10],
    9: [12],            10: [8, 5, 11],
    11: [10],           12: [8, 2, 9]
}
s_q2 = CreatePruferSequence(tree)
print('Sequence #2:', s_q2.sequence(), '\n')


# Tree with high complexity
tree = {
    1: [9],             2: [3, 18, 21, 7],      3: [6, 2],
    4: [13, 6, 9],      5: [11, 10, 9],         6: [4, 20, 3],
    7: [2, 8],          8: [7],                 9: [4, 12, 5, 1],
    10: [5],            11: [5],                12: [9],
    13: [4, 15, 14],    14: [13],               15: [13],
    16: [18],           17: [18],               18: [2, 17, 19, 16],
    19: [18],           20: [6],                21: [2]
}
s_q3 = CreatePruferSequence(tree)
print('Sequence #3:', s_q3.sequence(), '\n')
