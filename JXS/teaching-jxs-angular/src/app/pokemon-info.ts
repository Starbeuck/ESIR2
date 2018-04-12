import { Tuple } from './tuple';

export class PokemonInfo {
 name: string;
 image: string[];
 type: string;
 height: number;
 abilities: string[];
 stats: Tuple[];

 constructor(nameT: string, typeT: string, nT: number, imageT: string[], abilitiesT: string[], statsT: Tuple[]){
  this.name = nameT;
  this.image = imageT;
  this.abilities = abilitiesT;
  this.type = typeT;
  this.height = nT;
  this.stats = statsT;
 }
	
 public setname(n: string){
  this.name = n;
 }
	
 public getname(){
	 return this.name.toUpperCase();
 }
	
 public setimage(i: string[]){
  this.image = i;
 }
	
 public getimageFrontNorm(){
	 return this.image[0];
 }
	
 public getimageBackNorm(){
	 return this.image[1];
 }
	
public getimageFrontShiny(){
	 return this.image[2];
 }
	
public getimageBackShiny(){
	 return this.image[3];
 }
	
public setAbilities(s: string, i: number){
	this.abilities[i] = s;
}
	
public getAbilities(i: number){
	return this.abilities[i];
}
	
public getAbilitiesTab(){
	return this.abilities;
}
	
public setType(s: string){
	this.type =s;
}
	
public getType(){
	return this.type;
}
	
public setHeight(n: number){
	this.height = n;
}

public getHeight(){
	return this.height;
}
	
public setstats(n:Tuple){
	this.stats.push(n);
}
	
public getstatsAll(){
	return this.stats;
}

}

