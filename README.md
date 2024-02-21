# Elementary Matrix Multiplier
First note that I'm a bad coder and an even worse mathematician. If you read this and actually trust it that's on you.

Sometimes if you have a square* matrix A, and you want to transform it into another square matrix B, you may use a sequence of elementary row operations.

These operations come in three flavors: you can swap two rows, multiply a row by some scalar, or add some row i some number of times to row j.

For example, imagine you have some 2x2 matrix:  
1 2  
3 4  

And you want it to look like:  
3 4   
1 2  

You may notice you can achieve this by swapping rows 1 and 2. 

As it turns out, instead of just typing out what transformation you're doing in a sentence, it can be represented as a matrix that you multiply against your original matrix.

To continue with the example above, you can verify that multiplying  
0 1    
1 0   
by our original matrix gives us the desired result.

This can also be expressed in a fun little unique notation. In this case it would be something like "E[1,2]" where E denotes an elementary matrix, and 1 and 2 tell us we are swapping rows 1 and 2.

Similarly, if instead of swapping two rows we wanted to multiple row 1 by 5, we could write something like "E_5[1]" which in matrix form looks like:  
5 0  
0 1  
(please verify this and see if i'm wrong i kinda just came up with all this like an hour ago).

Lastly, if you wanted to add row 2 to row 1 4 times, we could write something like "E_4[1,2]". In matrix form this looks like:  
1 4  
0 1  

So now you see how all three elementary row transforms can be expressed using our fun little notation or as matrices.

Now, sometimes it takes more than one transformation to achieve the desired matrix. Lucky for us, that is quite alright.

Imagine our first transformation is swapping rows 1 and 2 (E[1,2]). 
Imagine our second transformation is multiplying row 3 by 4 (E_4[3]).

To get to our final elementary matrix necessary to transform our original matrix into the desired matrix, we can just find a series of matrix products.
The formula looks something like [elementary matrix for last transform] * [elementary matrix for second to last transform] * ... * [elementary matrix for first transform]
More simply put: En * En-1 * ... * E2 * E1 

The product of all this craziness is some final matrix E that when left-multiplied by our original matrix, should ideally give us our desired matrix.

You may have guessed that this involves an insane amount of matrix multiplication. With the nice calm examples 
I chose this wasn't a problem since we are all very smart and nice. However, imagine a 20x20 matrix with 50 transformations necessary.
I just started shivering I'm actually so scared of that. 

Anyways, this tool lets you provide a sequence of notations and it will output the final transform matrix E. You could argue
the hard part of all this is finding the sequence of notations and you would probably be right but I haven't figured out how to solve that yet.
Maybe this will help you on your journey. Look at the only test class in this repository to see some more examples.

Also please leave comments or something. 

* A matrix need not be square to perform elementary row operations but this tool is only useful in that case. What are you doing with non-square matrices anyways, brah? You scared? Everything people said about you? Yeah, they were right. Anyways...

