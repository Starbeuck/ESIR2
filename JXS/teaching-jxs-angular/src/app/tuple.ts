export class Tuple {
	stat: string;
	val: number;
	
	constructor(s:string, n:number){
		this.stat = s;
		this.val = n;
	}	
	
	public setStat(s: string){
		this.stat = s;
	}
	
	public getStat(){
		return this.stat;
	}
	
	public setval(v: number){
		this.val = v;
	}
	
	public getval(){
		return this.val;
	}
}
