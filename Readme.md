Author: Angelo Arriaga

Class: CSC 510

Professor: Dr. Kazunori Okada

Premise:

The n-Queens problem is a problem where you have an n by n board, 
and you're trying to see how you can place n Queens on the board in
a manner in which no queens are capable of capturing another

This program has methods that can either:

1. Brute Force calculate every possible legal board and display it for the user (disabled as outside of assignments scope)
2. Utilize a Monte Carlo Algorithm to predict the number of nodes checked by backtacking.

1 is very slow for large values of n, so we need a way of approximating

2 is the focus of the assignment where we can predict the true value without excessive computation

The code is current set for 300 cycles of the 12-Queen problem and predicts a value close
to the supposed value of 1.01 * 10^7 nodes checked

My code seems to overestimate slightly in all scenarios, even with large cycle counts 
but it does get remarkably close for large values of n