(* Author: Eric Van Wyk
    Modified by: Horoom *)

(* A function computing the Fibonacci sequence: 1, 1, 2, 3, 5, 8, ... *)

(* There is a bug in teh following program. Caan you fix it? *)


let rec fib x =
    if x = 0 then 0 else
        if x < 3 then 1 else fib (x-1) + fib (x-2)
