import {Snake} from "./Snake"
import {SnakeDirection} from "./SnakeDirection"
import {Food} from "./Food";

export class Game {

    canvasContext : CanvasRenderingContext2D;
    gridWidth : number;
    gridHeight : number;
    snake : Snake;
    isGameOver : boolean;
    userScore : number;
    food : Food;
    timeOfFoodApparition : number;
    timeBeforeRelocate : number = 10000;
    constructor(public canvas : HTMLCanvasElement, public speed : number, public gridSize : number = 5) {
        this.gridWidth = canvas.width / gridSize;
        this.gridHeight = canvas.height / gridSize;
        this.canvasContext = canvas.getContext("2d");
        this.isGameOver = false;
         this.userScore = 0;
         this.timeOfFoodApparition = new Date().getTime();

        // TODO : listen to user interaction
        addEventListener("keydown",(evt) => this.listener(evt));
    }

    listener(evt : KeyboardEvent) {
    //Les conditions sont la pour éviter que le serpent soit à "2 têtes"
    // Eviter que si on va vers la gauche, on puisse allez directement à droite en se "marchant" sur le corps
    switch(evt.keyCode) {
      case 37: if (this.snake.getDirection() != SnakeDirection.RIGHT) { //Gauche
        this.snake.setDirection(SnakeDirection.LEFT);
      }
      break;
      case 38: if (this.snake.getDirection() != SnakeDirection.DOWN) { //Haut
        this.snake.setDirection(SnakeDirection.UP);
      } break;
      case 39: if (this.snake.getDirection() != SnakeDirection.LEFT) { //Droite
        this.snake.setDirection(SnakeDirection.RIGHT);
      } break;
      case 40: if (this.snake.getDirection() != SnakeDirection.UP) { //Bas
        this.snake.setDirection(SnakeDirection.DOWN);
      } break;
    }
}
    
    /**
     * Start game
     */
    start() {
        // TODO : initialize game
        this.snake = new Snake(3, this.gridWidth / 2, this.gridHeight / 2, SnakeDirection.RIGHT);
        this.food = new Food(0,0);
        this.food.relocate(this.snake, this.gridWidth, this.gridHeight);
        this.animate(); // Start animation
    }

    animate() {
        let fps = this.speed;
        let now;
        let then = Date.now();
        let interval = 1000/fps;
        let delta;

        let animationLoop = (function () {
            if (!this.isGameOver) {
                requestAnimationFrame(animationLoop);
            }

            now = Date.now();
            delta = now - then;

            if (delta > interval) {
                then = now - (delta % interval);
                this.update();
            }

        }).bind(this);

        animationLoop();
    }
    
    verifyGameRule() {
    //Vérification des bordures de jeu
    if (this.snake.getXPos() < 0 || this.snake.getXPos() > (this.canvas.width / this.gridSize) || this.snake.getYPos() < 0 || this.snake.getYPos() > (this.canvas.height / this.gridSize)) {
      this.isGameOver = true;
        }
        
         //Vérification si le Snake a mangé la nourriture
    if (this.snake.getXPos() == this.food.getXPos() && this.snake.getYPos() == this.food.getYPos()) {
      this.userScore ++;
      this.snake.grow();
      this.food = new Food(0,0);
      this.food.relocate(this.snake, this.gridWidth, this.gridHeight);
      this.timeOfFoodApparition = new Date().getTime();
    }
        
    let t : Date = new Date();
    if ((t.getTime() - this.timeOfFoodApparition) > this.timeBeforeRelocate) {
      this.timeOfFoodApparition = t.getTime();
      this.food.relocate(this.snake, this.gridWidth, this.gridHeight);
}
    }

    /**
     * Update status of game and view
     */
    update() {
        
    //Dessin Canvas
    this.canvasContext.fillStyle = 'grey';
    this.canvasContext.fillRect(0, 0, this.canvas.width, this.canvas.height);

    //Dessin Score
    this.canvasContext.fillStyle = 'black';
    this.canvasContext.fillText("Score : " + this.userScore, 10, 10);

    //Dessin Snake + Nourriture
        this.food.draw(this.canvasContext);
        this.snake.move(); //Mouvement du snake
        this.snake.draw(this.canvasContext);
        this.verifyGameRule(); //Vérification de sa position, de la nourriture, etc
    }

}