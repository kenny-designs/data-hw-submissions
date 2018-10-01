Running and compilation works as follows:

// to compile the program as a whole
javac Assignment2

// to run objective 1 with test input
java Assignment2 1 < obj1/input.txt

// to run objective 2 with test input
java Assignment2 2 < obj2/input.txt

// to run objective 3 with test input
java Assignment2 3 < obj3/input.txt

// to run objective 4 with test input
java Assignment2 4 < obj4/input.txt

To use your own input, replace the text following the '<' symbol with your own
file.

Instructions For Each Objective
-------------------------------
All objectives work best by supplying a file resembling the input.txt files in
each obj directory. For objectives 1 and 4, the program reads each line one at
a time. Objectives 2 and 3 are read two lines at a time. Whenever invalid input
is encountered, such as supplying characters instead of numbers to obj1, then an
exception is thrown alerting the user to which line is incorrect before moving on
to the next line/pair of lines.

Big Note on Objective3
-------------------------------
For Objective3, I read two lines at a time then see if they overlap such as the
following:
  1 2 3 10 15 20
    9 6 10 15 20 // shifted to the right to help show where they overlap

My program then creates two Linked Lists that connect the overlapping parts together
like so:
  [1]->[2]->[3]--+
                 |->[10]->[15]->[20]
       [9]->[6]--+

Each [ ] represents a node and the number inside the data held by said node. Now
that the program has forced these two lines to intersect, I use my method
findIntersectingLink() to retrieve the node where they intersect. If they don't
intersect at all then it returns null.

The main reason I point this all out is that the example input/output on the website
shows the following:
  [1,3,5,7] and [2,4,5,6] —> the lists intersect at 5

I reason that it's impossible for the node holding the data value 5 to be where they
intersect for this implies that a single Link can point to two separate nodes when
each one should only be able to point forward in a singly linked list and forwards and
backwards in a doubly linked list.

On the contrary, any number of nodes can point to a single node with data value 5
and that would be okay. However, a single node can only point towards one other node
in a singly linked list so it's impossible for the node with data value 5 to point to
two other nodes such as the 6 and 7 ones.
