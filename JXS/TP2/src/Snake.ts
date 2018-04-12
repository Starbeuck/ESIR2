import {SnakePart} from "./SnakePart"
import {SnakeDirection} from "./SnakeDirection";

export class Snake {
    
private part : Array<SnakePart>;
private direction : SnakeDirection;

  constructor(initSize : number, xStartPos : number, yStartPos : number, initDir : SnakeDirection) {
    this.part = new Array<SnakePart>();
    this.direction = initDir;

    //Init Snake Part
    for (var i = 0; i < initSize; i++) {
      let snp = new SnakePart(xStartPos - i, yStartPos);
      this.part.push(snp);
    }
  }

  getXPos() {
    return this.part[0].getXPos();
  }

  getYPos() {
    return this.part[0].getYPos();
  }

  setDirection(dir : SnakeDirection) {
    this.direction = dir;
  }

  getDirection() {
    return this.direction;
  }

  draw(canvas : CanvasRenderingContext2D) {
    console.log("Draw Snake");
    this.part.forEach(function(elem) {elem.draw(canvas);});
  }
    
      isOnSnake(x : number, y : number) {
    this.part.forEach(function(elem) {
      if (x == elem.getXPos() && y == elem.getYPos()) {
        return true;
      }
    });
    return false;
}
    
    move() {
    let head : SnakePart;
    let newHead : SnakePart;
    let l : Array<SnakePart>;

    switch (this.direction) {
      case SnakeDirection.UP :
      //On remove l'ancienne queue
      this.part.pop();

      //On récupère la tête et on crée la nouvelle tête
      head = this.part[0];
      newHead = new SnakePart(head.getXPos(), head.getYPos() - 1);

      //Création d'un tableau temporaire
      l = new Array<SnakePart>();
      l.push(newHead);

      //Concaténation entre nouveau tableau et ancien tel que
      // [NouvelleTête]+[Ancien tableau]
      this.part = l.concat(this.part);
      break;

      case SnakeDirection.DOWN :
      this.part.pop();
      head = this.part[0];
      newHead = new SnakePart(head.getXPos(), head.getYPos() + 1);
      l = new Array<SnakePart>();
      l.push(newHead);
      this.part = l.concat(this.part);
      break;

      case SnakeDirection.LEFT :
      this.part.pop();
      head = this.part[0];
      newHead = new SnakePart(head.getXPos() - 1, head.getYPos());
      l = new Array<SnakePart>();
      l.push(newHead);
      this.part = l.concat(this.part);
      break;

      case SnakeDirection.RIGHT :
      this.part.pop();
      head = this.part[0];
      newHead = new SnakePart(head.getXPos() + 1, head.getYPos());
      l = new Array<SnakePart>();
      l.push(newHead);
      this.part = l.concat(this.part);
      break;
    }
}
    
    grow() {
    //On crée une nouvelle part au même endroit que la tail du Snake
    let newSnakePart : SnakePart = new SnakePart(this.part[this.part.length - 1], this.part[this.part.length - 1]);
    this.part.push(newSnakePart);
}
}