theory main
imports Main
begin

(* Exercice 1 *)
fun member:: "'a => 'a list => bool"
where
"member e []     = False" |
"member e (x#xs) = (if e=x then True else (member e xs))"

value "member 2 [1,2::nat,3]"

(* Exercice 2 *)
fun isSet:: "'a list \<Rightarrow> bool"
where
"isSet []     = True" |
"isSet (x#xs) = (if (member x xs) then False else (isSet xs))"

value "member 1 [1::nat]"
value "isSet [1::nat,1] "

(* Exercice 3 *)
  
fun clean:: "'a list \<Rightarrow> 'a list"
where
"clean []    = []" |
"clean (x#xs) = (if (member x xs) then xs else x#(clean xs))"

value "member 1 [2::nat,3]"
value "clean [1::nat,2,3]"

(* Exercice 4 *)
lemma "\<forall>a. (member a [b]) \<longrightarrow>member a (clean [b])"
nitpick
apply auto
done

(* Exercice 5 *)
lemma "\<forall>a. \<not>(isSet a) \<longrightarrow> isSet(clean[a]) "
nitpick
apply auto
done

(* Exercice 6 *)
fun delete :: "'a => 'a list => 'a list"
where
"delete a [] = []" |
"delete a (x#xs) = (if (x=a) then (delete a xs) else x#(delete a xs))"

value "delete 1 [1]"

(* Exercice 7 *)
lemma "\<forall>a. (member a b) \<longrightarrow> ~(member a (delete a b))"
nitpick
apply auto
oops 

lemma "\<forall>a. \<forall>b. member a (delete b c) \<longrightarrow> (member a c)"
nitpick
apply auto
oops

(* Exercice 8 *)
fun intersection :: "'a list => 'a list => 'a list"
where
"intersection [] b = []" |
"intersection (a#as)  b = (if(member a b) then clean ( a#(intersection as b) )else (intersection as b))"

value "intersection [1::nat,1] [1::nat,1]"

(* Exercice 9 *)
lemma "\<forall>a. (member a b) \<and> (member a c) \<longrightarrow> (member a (intersection b c))"
nitpick
apply auto
oops

(* Exercice 10 *)
lemma "isSet(intersection a b)"
nitpick
oops

(* Exercice 11 *)
fun union :: "'a list => 'a list => 'a list"
where
"union [] b = (clean b)" |
"union (a#as) b = (if\<not>(member a b) then clean(union as (a#(clean b))) else (clean (union as b)))"

value "union [] [1::nat,1,1]"

(* Exercice 12 *)
lemma "\<forall>a. (member a b) \<longrightarrow> (member a (union b c))"
nitpick
apply auto
oops

(* Exercice 13 *)
lemma "isSet(union a b)"
nitpick
oops

(* Exercice 14 *)
fun equal  :: "'a list => 'a list => bool"
where
"equal a b = ((intersection a b = a) \<and> (intersection a b = b))"

value "equal [1::nat,2] [1,2,3]"

(* Exercice 15 *)
lemma "\<forall>a. ((member a b) & (equal b c)  \<longrightarrow> (member a c))"
nitpick
apply auto
done
