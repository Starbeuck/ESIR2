import { Component, OnInit } from '@angular/core';
import { Pokemon } from '../pokemon';
import { PokemonInfo } from '../pokemon-info';
import {PokemonNamePipe} from "../pipes/pokemon-name/pokemon-name.pipe";
import { Observable } from 'rxjs/Observable';
import { catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { SearchApiService } from '../search-api.service';
import { Tuple } from '../tuple';

@Component({
  selector: 'app-interface',
  templateUrl: './interface.component.html',
  styleUrls: ['./interface.component.css']
})


export class InterfaceComponent implements OnInit {

 Search: string = "" ;
 choose: string = "";
 visible: boolean = false;
 invisible: boolean = true;
 apiService: SearchApiService
 public bcp_pokemon;
 api_list_pokemon: Pokemon[];
 pokemonChoosen: PokemonInfo;
	
 constructor(api: SearchApiService) {
	 this.apiService = api;
	 this.api_list_pokemon = [];
	 this.pokemonChoosen = new PokemonInfo("","",0,[],[], []);
 }
	
// get Object Pokemon from JSON	
 ngOnInit() {
	  this.apiService.getJSON(this.apiService.ADRESS).subscribe(data => {
		  	this.bcp_pokemon = data.results; 
		    for (let value in this.bcp_pokemon){
					let temp = new Pokemon(this.bcp_pokemon[value].name, this.bcp_pokemon[value].url)
				    this.api_list_pokemon.push(temp);
			}
        }); 
 }
	
//launch info from a choosen pokemon to the click Go !	
 letsgo(){
	 if(this.choose!=""){
		 this.visible = true;
	 }
	 this.getInfo();
 }
	
//get url from a choosen pokemon	
geturl(tofound:string){
	let temp = this.api_list_pokemon.filter(x => x.name == tofound);
	return temp[0].url;
}
	
//get info from a choosen pokemon
getInfo(){
	this.apiService.getInfo(this.geturl(this.choose)).subscribe(
		data=> {
			//set name
			this.pokemonChoosen.setname(this.choose);
			
			//set abilities
			let temp = data.abilities
			for (let value in temp){
				this.pokemonChoosen.setAbilities(temp[value].ability.name, value);
			}
			
			//set type
			this.pokemonChoosen.setType(data.types[0].type.name)
			
			//set heigt 
			this.pokemonChoosen.setHeight(data.height)
			
			//setStat
			for (let value in data.stats){
				let temp = new Tuple(data.stats[value].stat.name,data.stats[value].base_stat);
				this.pokemonChoosen.setstats(temp);
				console.log(temp.getval)
			}
			
			//set image 
			this.apiService.getJSON(data.forms[0].url).subscribe(data => {
				let temp, temp1, temp2, temp3, temp4;
				if(data.sprites.front_default==null){
					temp1 = "../assets/error.png"
				} else {
					temp1 = data.sprites.front_default;
				}
				if(data.sprites.back_default==null){
					temp2 = "../assets/error.png"
				} else {
					temp2 = data.sprites.back_default;
				}
				if(data.sprites.front_shiny==null){
					temp3 = "../assets/error.png"
				} else {
					temp3 = data.sprites.front_shiny;
				}
				if(data.sprites.back_shiny==null){
					temp4 = "../assets/error.png"
				} else {
					temp4 = data.sprites.back_shiny;
				}
				temp = [temp1,temp2, temp3, temp4];
				this.pokemonChoosen.setimage(temp);
				});			
		}
		
	);
}
		
 
}
