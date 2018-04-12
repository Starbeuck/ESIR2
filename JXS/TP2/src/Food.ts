import {Snake} from "./Snake";

export class Food {
  private xPos : number;
  private yPos : number;

  constructor(x : number, y : number) {
    this.xPos = x;
    this.yPos = y;
  }

  relocate(s : Snake, multiX : number, multiY : number) {
    let x : number = 0;
    let y : number = 0;

    do {
      x = Math.round(Math.random() * multiX);
      y = Math.round(Math.random() * multiY);
    } while (s.isOnSnake(x, y));

    this.xPos = x;
    this.yPos = y;
  }

  getXPos() {
    return this.xPos;
  }

  getYPos() {
    return this.yPos;
}
    draw(canvas : CanvasRenderingContext2D) {
    console.log("Draw Food");

    canvas.fillStyle = 'green';
    canvas.fillRect((5 * this.xPos), (5 * this.yPos), 5, 5);
    canvas.fill();
    canvas.stroke();
}
}