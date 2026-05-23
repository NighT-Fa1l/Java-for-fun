
static int sizeX = 40;
static int sizeY = 30;
static int cellSize = 25;
static int getSizeX = sizeX *cellSize;
static int getSizeY = sizeY *cellSize;

public static void main(String[] args) {
        platform play = new platform();
        play.draw(sizeX, sizeY, cellSize,getSizeX,getSizeY);
}
