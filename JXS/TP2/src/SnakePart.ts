export class SnakePart {

    private xPos : number;
    private yPos : number;

    constructor(x, y) {
        this.xPos = x;
        this.yPos = y;
        }
        
    getXPos() {
        return this.xPos;
    }

    setXPos(newX : number) {
        this.xPos = newX;
    }

    getYPos() {
        return this.yPos;
    }

    setYPos(newY : number) {
        this.yPos = newY;
    }
    
    draw(canvas : CanvasRenderingContext2D) {
        canvas.beginPath();
        canvas.fillStyle='#000000';
        canvas.fillRect((5 * this.xPos), (5 * this.yPos), 5, 5);
        canvas.stroke();
    }
}