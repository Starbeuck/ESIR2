import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { PokesirComponent } from './pokesir.component';
import {PokemonNamePipe} from "./pipes/pokemon-name/pokemon-name.pipe";
import { InterfaceComponent } from './interface/interface.component';
import { SearchApiService } from './search-api.service';

@NgModule({
  declarations: [
    PokesirComponent,
    PokemonNamePipe,
    InterfaceComponent
  ],
  imports: [
    BrowserModule,
	FormsModule,
	HttpClientModule,
  ],
  providers: [SearchApiService],
  bootstrap: [PokesirComponent]
})

export class PokesirModule { }
