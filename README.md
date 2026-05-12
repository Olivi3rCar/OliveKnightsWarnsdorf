# Olive's Warnsdorf's Knight's Tour

## Choice Types
This implementation of the Warnsdorf method implements 4 choicetypes that are used to determine the choice among 2 squares with the same (minimal) number of Available Adjacent squares.
### Default :
This choicetype returns the first element available in the "Available Adjacent" arrays of the current square (typically based on x/y position). This does not guarantee success of the Knight's Tour on large boards.
### Random :
This choicetype returns a random square among the possible ones.
### Pohl :
This choicetype returns the square for which the sum of the "Available Adjacent" squares for all of its own "Available Adjacent" is minimal. (Basically applying Warnsdorf a second time.)
### Roth :
This choicetype returns the square that is the furthest (Euclidean Distance) from the center of the board.
