# Algorithms Specialization
Welcome to [Rajesh's](http://www.rajeshsurana.com) Algorithm Portfolio! While completing [Algorithms Specialization](https://www.coursera.org/specializations/algorithms) from Stanford I had to solve few problems. Here is the list-

## Course 1 - Divide and Conquer, Sorting and Searching, and Randomized Algorithms - [info](https://www.coursera.org/learn/algorithms-divide-conquer#syllabus)

### week 1
* Multiplication method using Karatsuba Divide and Conquer algorithm. [view code](https://github.com/rajeshsurana/Algorithm/blob/main/divideConquer_sortingSearching_randomizedAlgo/week1/KaratsubaMultiplication.java)
	 * Karatsuba Pseudocode - https://en.wikipedia.org/wiki/Karatsuba_algorithm#Pseudocode
	 * Karatsuba formula = ((a*c)*10^n) + (((a+b)*(c+d) - (a*c) - (b*d)) * 10^(n/2)) + (b*d)
	 * X = 10^(10/2) * a + b and Y = 10^(10/2) * c + d where a, b, c, d are n/2 digit numbers
	 * X*Y = (10^n)*a*c + 10^(n/2) * (a*d+b*d) + b*d
	 * (a + b) * (c + d) = a*c + a*d + b*c + b*d
	 * (a+b)*(c+d) - (a*c) - (b*d) = a*d + b*d
	 * X*Y = ((a*c)*10^n) + (((a+b)*(c+d) - (a*c) - (b*d)) * 10^(n/2)) + (b*d)
	 * Assumptions -
	 * 1. Both numbers are non-negative. This can easily be fixed by counting number of
	 * negative numbers in multiplication whether it is odd or even.
	 * Running time complexity - O(n^(log_2(3))) = O(n^1.59)
### week 2
* Given an array A, compute the number of inversions = number of pairs {i, j} such a that A[i] > A[j] and i < j. [view code](https://github.com/rajeshsurana/Algorithm/blob/main/divideConquer_sortingSearching_randomizedAlgo/week2/CountInversions.java)
* You are given a unimodal array of n distinct elements, meaning that its entries are in increasing order up until its maximum element, after which its elements are in decreasing order. Give an algorithm to compute the maximum element that runs in O(log n) time. [view code](https://github.com/rajeshsurana/Algorithm/blob/main/divideConquer_sortingSearching_randomizedAlgo/week2/MaxFinderInUnimodalArray.java)
* You are given a sorted (from smallest to largest) array A of n distinct integers which can be positive, negative, or zero. You want to decide whether or not there is an index i such that A[i] = i. Design the fastest algorithm that you can for solving this problem. [view code](https://github.com/rajeshsurana/Algorithm/blob/main/divideConquer_sortingSearching_randomizedAlgo/week2/SameIndexValueFinder.java)
* You are given as input an unsorted array of n distinct numbers, where n is a power of 2. Give an algorithm that identifies the second-largest number in the array, and that uses at most n + log_2 n - 2 comparisons. [view code](https://github.com/rajeshsurana/Algorithm/blob/main/divideConquer_sortingSearching_randomizedAlgo/week2/SecondLargestFinderInUnsorted.java)
### week 3
* Count the number of comparisons during quick sort. If m is the length of an array then m-1 comparisons will happen as pivot is compared with rest of the elements. [view code](https://github.com/rajeshsurana/Algorithm/blob/main/divideConquer_sortingSearching_randomizedAlgo/week3/QuickSort.java)
	 * Average case running time complexity - O(n log(n))
	 * Worst case running time complexity - O(n^2)
	 * Running time complexity depends on how good the pivot is selected.
	 * I found 'median of three' pivot selection produced the near merge sort results.
### week 4
* Given a graph, find minimum number of cuts. [view code](https://github.com/rajeshsurana/Algorithm/blob/main/divideConquer_sortingSearching_randomizedAlgo/week4/GraphMinCutFinder.java)
* A QuickSort variant to get nth biggest number in an array. [view code](https://github.com/rajeshsurana/Algorithm/blob/main/divideConquer_sortingSearching_randomizedAlgo/week4/NthLargestFinder.java)
	 * Array elements are unsorted and order could be of max array length
	 * Running time complexity - O(n) (Its contrary to what code looks like O(n^2))

## Course 2 - Graph Search, Shortest Paths, and Data Structures - [info](https://www.coursera.org/learn/algorithms-graphs-data-structures#syllabus)

### week 5
* Given a graph, find all Strongly Connected Components. [view code](https://github.com/rajeshsurana/Algorithm/blob/main/graphSearch_shortestPaths_dataStructures/week5/SCCFinder.java)
	 * A directed graph is strongly connected if there is a path between all pairs of vertices. A strongly connected component (SCC) of a directed graph is a maximal strongly connected subgraph.
* Given a directed acyclic graph, find topologic sort order of nodes. [view code](https://github.com/rajeshsurana/Algorithm/blob/main/graphSearch_shortestPaths_dataStructures/week5/TopologicalSort.java)
	 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u v, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.
### week 6
* Given a weighted graph, find shortest distance path using Dijkstra algorithm from source to other nodes. [view code](https://github.com/rajeshsurana/Algorithm/blob/main/graphSearch_shortestPaths_dataStructures/week6/DijkstraGraphSortestPathFinder.java)
### week 7
* The goal of this problem is to implement the "Median Maintenance" algorithm. [view code](https://github.com/rajeshsurana/Algorithm/blob/main/graphSearch_shortestPaths_dataStructures/week7/MedianMaintainer.java)
	 * The text file contains a list of the integers from 1 to 10000 in unsorted order; you should treat this as a stream of numbers, arriving one by one. Compute the sum of these 10000 medians, modulo 10000 (i.e., only the last 4 digits).
### week 8
* Given list of numbers, count distinct target sum t values in a given range [low, high] for distinct pairs of numbers in a given set. [view code](https://github.com/rajeshsurana/Algorithm/blob/main/graphSearch_shortestPaths_dataStructures/week8/TwoSumPairFinder.java)
	 * This is also called as Two Sum problem.
