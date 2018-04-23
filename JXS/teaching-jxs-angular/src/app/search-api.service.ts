import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class SearchApiService {

	ADRESS: string = "./assets/list_pokemon.json";

	constructor(private http: HttpClient) {
	}

	public getJSON(url :string): Observable<any> {
		return this.http.get(url)
	}

	public getInfo(url: string){
		return this.http.get(url);
	}


}
