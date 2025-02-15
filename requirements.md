- Is grid limited to 3 x 3
- if not above, would it be N x N or M x N
- In case of N x N or M x N grid, how many uniform cells be marked to win? N or min(M, N) or K
  Note:: if it requires 5 cells to be uniform (in a row, col or diag) then it's said to *Gomoku*
- Are characters limited to O|X
- Should we show just winner character (symbol) or name of the player too?

-----------------------
Observations and Decisions:

- game will have status viz NOT_STARTED, IN_PROGRESS, WON, DRAW
- the first player mentioned is the first player to move
- status is returned after player makes a move
- We can maintain number of cells occupied to easily determine completeness of the board in O(1)
