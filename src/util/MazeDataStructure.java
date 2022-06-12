package util;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Random;

public class MazeDataStructure implements Serializable{
	private static final long serialVersionUID = 1L;
	private BitSet data;
	private int mazeHeight;
	private int mazeWidth;
	private int blockThickness;
	private int blockLength;
	private int blocks;
	
	public MazeDataStructure(int width, int height, int thickness, int length, boolean isRandom){
		Random rand = new Random();
		this.data = new BitSet();
		this.mazeHeight = height;
		this.mazeWidth = width;
		this.blockThickness = thickness;
		this.blockLength = length;
		
		calculateBlocks();
		
		int rowNum = mazeWidth * 2 + 1;
		data.set(0, blocks);
		if(!isRandom){
            for(int y = 0; y < mazeHeight; y++){
                if(y != 0){
                    for(int x = 0; x < mazeWidth; x++){
                        data.clear(y * rowNum + x);
                    }
                }
                for(int x = mazeWidth + 1; x < mazeWidth * 2; x++){
                    data.clear(y * rowNum + x);
                }
            }
		}else{
            for(int y = 0; y < mazeHeight; y++){
                if(y != 0){
                    for(int x = 0; x < mazeWidth; x++){
                        if(rand.nextInt(2) == 1){
                            data.clear(y * rowNum + x);
                        }
                    }
                }
                for(int x = mazeWidth + 1; x < mazeWidth * 2; x++){
                    if(rand.nextInt(2) == 1){
                        data.clear(y * rowNum + x);
                    }
                }
            }
		}
	}
	
	public MazeDataStructure(MazeDataStructure savedData) {
		this.data = savedData.data;
		this.mazeHeight = savedData.mazeHeight;
		this.mazeWidth = savedData.mazeWidth;
		calculateBlocks();
	}
	
	// This method flips the block
	public void flipBlock(int blockNum) {
		data.flip(blockNum);
	}
	
	// This method gets the current state of the block
	public boolean getBlockState(int blockNum) {
		return data.get(blockNum);
	}
	
	// This method rotates the mazes in a clockwise direction
	public void rotateClockwise() {
		//Top to bottom, right to left
		BitSet rotated = new BitSet();
        int blockNum = mazeWidth * 2 + 1;
        int rotHeight = mazeWidth;
        int rotWidth = mazeHeight;
        int newBlockNum = rotWidth * 2 + 1;
        
        for(int x = 0; x < rotHeight; x++){
            for(int i = 0; i < rotWidth; i++){
                rotated.set(x * newBlockNum + i, data.get(blockNum * (rotWidth - i - 1) + rotHeight + x));
            }
            for(int i = rotWidth; i < rotWidth * 2 + 1; i++){
                rotated.set(x * newBlockNum + i, data.get(blockNum * ((rotWidth * 2 + 1) - i - 1) + x));
            }
        }
        for(int i = 0; i < rotWidth; i++){
            int x = rotHeight;
            rotated.set(x * newBlockNum + i, data.get(blockNum * (rotWidth - i - 1) + rotHeight + x));
        }
        
        data = rotated;
        mazeHeight = rotHeight;
        mazeWidth = rotWidth;
	}
	
	// This method rotates the mazes in an anticlockwise direction
	public void rotateAntiClockwise() {
		//Bottom to top, left to right
		rotateClockwise();
		rotateClockwise();
		rotateClockwise();
	}
	
	// This method adds height to the maze
	public void addHeight(int rows) {
		for(int x = 0; x < rows; x++) {
			addVerticalLine();
			mazeHeight++;
		}
		addHorizontalLine();
	}
	
	// This method adds width to the maze
	public void addWidth(int rows) {
		rotateClockwise();
		addHeight(rows);
		rotateAntiClockwise();
	}
	
	// This method reduces the height of the maze
	public void removeHeight(int rows) {
		mazeHeight -= rows;
		addHorizontalLine();
	}
	
	// This method reduces the width of the maze
	public void removeWidth(int rows) {
		rotateClockwise();
		mazeHeight -= rows;
		addHorizontalLine();
		rotateAntiClockwise();
	}
	
	// This method randomly generates a maze
	public void randomise() {
		int rowNum = mazeWidth * 2 + 1;
		Random rand = new Random();
		data.set(0,blocks);
		
		for(int y = 0; y < mazeHeight; y++){
            if(y != 0){
                for(int x = 0; x < mazeWidth; x++){
                    if(rand.nextInt(2) == 1){
                        data.clear(y * rowNum + x);
                    }
                }
            }
            for(int x = mazeWidth + 1; x < mazeWidth * 2; x++){
                if(rand.nextInt(2) == 1){
                    data.clear(y * rowNum + x);
                }
            }
        }
	}
	
	// This method sets the length of a block 
	public void setLength(int blockLength) {
		this.blockLength = blockLength;
	}
	
	// This method sets teh thickness of a block
	public void setThickness(int blockThickness) {
		this.blockThickness = blockThickness;
	}
	
	public int getWidth() {return mazeWidth;}
	public int getHeight() {return mazeHeight;}
	public int getThickness() {return blockThickness;}
	public int getLength() {return blockLength;}
	
	public int getBlocks() {calculateBlocks(); return blocks;}
	
	//WARNING: This test function is meant as a display method to run a simulated test maze.
	//It will destroy the contents of whatever maze data currently exists. You have been warned
	public void runLogicTest() {
		data.clear();
		mazeHeight = 2;
		mazeWidth = 2;
		calculateBlocks();
		data.set(0, 3);
		data.set(4,8);
		data.set(9,12);
		System.out.println(printData());
	    rotateAntiClockwise();
		System.out.println(printData());
		rotateAntiClockwise();
		addHeight(5);
		System.out.println(printData());
		addWidth(10);
		System.out.println(printData());
		addHeight(5);
		System.out.println(printData());
		rotateClockwise();
		System.out.println(printData());
	}
	
	// This method adds a vertical line to the maze
	private void addVerticalLine() {
		calculateBlocks();
		data.set(blocks);
		data.set(blocks + mazeWidth);
	}
	
	// This method adds a horizontal line to the maze
	private void addHorizontalLine() {
		calculateBlocks();
		data.set(blocks - mazeWidth, blocks);
	}

	private void calculateBlocks() {
		blocks = (mazeWidth + 1) * mazeHeight + mazeWidth * mazeHeight + mazeWidth;
	}
	
	private String addChar(boolean isOn){
        if(isOn){
            return "1";
        }else{
            return "0";
        }
    }
	
    
    private String printData(){
        StringBuilder output = new StringBuilder();
        int rowNum = mazeWidth * 2 + 1;
        
        for(int x = 0; x < mazeHeight; x++){
            for(int i = 0; i < mazeWidth; i++){
                output.append(addChar(data.get(x * rowNum + i)));
            }
            output.append("\n");
            for(int i = mazeWidth; i < mazeWidth * 2 + 1; i++){
                output.append(addChar(data.get(x * rowNum + i)));
            }
            output.append("\n");
        }
        for(int i = 0; i < mazeWidth; i++){
            output.append(addChar(data.get(mazeHeight * rowNum + i)));
        }
        output.append("\n");
        
        return output.toString();
    }
    
    public boolean[] getWalls(int x, int y){
        boolean[] walls = new boolean[4];
        int rowNum = mazeWidth * 2 + 1;
            for(int i = 0; i < 2; i++){
                walls[i*2] = data.get(i * rowNum + y * rowNum + x);
                walls[i*2 + 1] = data.get(x + mazeWidth + i + y * rowNum);
            }
        
        return walls;
    }
}
