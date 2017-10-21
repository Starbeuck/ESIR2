theory tp1
imports Main
begin

lemma "A \<or> B"
nitpick
oops

lemma "A \<and> B \<longrightarrow> B"
apply auto
done

lemma"(A\<and>(B\<or>C)) = ((A\<and>B)\<or>(A\<and>C))"
apply auto
done

lemma "\<not>(A\<and>B) =( \<not>A\<or>\<not>B) "
apply auto
done 

(*exercice 2*)
lemma "\<not>(((\<not>ecossais) \<longrightarrow>chaussettesrouges)\<and>(chaussettesrouges\<longrightarrow>kilt)\<and>(marie\<longrightarrow> \<not>dimanche)\<and>(dimanche=ecossais)\<and>(kilt\<longrightarrow>(ecossais\<and>marie))\<and>(ecossais\<longrightarrow>kilt))" 
nitpick
apply auto 
done 


(*partie 3 logique du 1er ordre*)
(*exercice3*)
lemma "\<forall>(x::nat) y z . x+y>x+z\<longrightarrow>x+x>y+z "
nitpick
oops 
(*satifaisable, found a counterexample, need x>y and x>z and z<y*)

lemma"\<forall>(x::nat) y .x+y \<le>x*y"
nitpick
oops 
(*satisfaisable il faut xet y != 0 et 1*)

lemma"\<forall> (x::nat) y z . x> y\<and>z>0\<longrightarrow>x*z>y*z"
nitpick
apply auto 
done 

lemma"~((\<exists>(x::nat).P(f(x)))\<longrightarrow>( \<forall> (x::nat) .P(f(x))))"
nitpick
sorry
(*satisfaisable fonction constante*)

(*exercice4*)
lemma"(a::nat) + b = b + a"
nitpick
apply auto 
done 

lemma " ((a::nat)+b)+c = (a+(b+c))"
nitpick
apply auto
done 

lemma"(a::nat)+b=a"
nitpick
oops 

(*exercice5*) 
lemma"append [a] [b] = append [b] [a]" 
nitpick
oops

lemma "append [c] (append [a] [b])  = append (append [c]  [a]) [b]"
nitpick
apply auto 
done 

lemma"append [a] [b] = [a]"
nitpick
oops 

(*exercice 6*)
lemma "length ( append  [a] [b]) = length (append  [b]  [a])"
nitpick
apply auto
done 

lemma " map f (append [a] [b]) = append (map f [a]) (map f [b])"
nitpick
apply auto 
done 

lemma" List.member (append [a] [b]) e = List.member  (append [b] [a]) e "
nitpick 
apply auto 
oops


lemma " List.member (append [a] [b]) e \<longrightarrow> List.member [a] e \<or> List.member [b] e"
nitpick
apply auto 
done 



end
