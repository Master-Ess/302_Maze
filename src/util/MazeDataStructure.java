package util;

import java.util.BitSet;

public class MazeDataStructure {
	private BitSet data;
	private int mazeHeight;
	private int mazeWidth;
	private int blocks;
	
	public MazeDataStructure(int length, int width) {
		this.data = new BitSet();
		this.mazeHeight = length;
		this.mazeWidth = width;
		calculateBlocks();
	}
	
	public MazeDataStructure(MazeDataStructure savedData) {
		this.data = savedData.data;
		this.mazeHeight = savedData.mazeHeight;
		this.mazeWidth = savedData.mazeWidth;
		calculateBlocks();
	}
	
	public void flipBlock(int blockNum) {
		data.flip(blockNum);
	}
	
	public boolean getBlockState(int blockNum) {
		return data.get(blockNum);
	}
	
	public void rotateClockwise() {
		//Top to bottom, right to left
		BitSet rotated = new BitSet();
        int blockNum = mazeWidth * 2 + 1;
        int rotHeight = mazeWidth;
        int rotWidth = mazeHeight;
        
        for(int x = 0; x < rotHeight; x++){
            for(int i = 0; i < rotWidth; i++){
                rotated.set(x * blockNum + i, data.get(blockNum * (rotWidth - i - 1) + rotHeight + x));
            }
            for(int i = rotWidth; i < rotWidth * 2 + 1; i++){
                rotated.set(x * blockNum + i, data.get(blockNum * (blockNum - i - 1) + x));
            }
        }
        for(int i = 0; i < rotWidth; i++){
            int x = rotHeight;
            rotated.set(x * blockNum + i, data.get(blockNum * (rotWidth - i - 1) + rotHeight + x));
        }
        
        data = rotated;
        mazeHeight = rotHeight;
        mazeWidth = rotWidth;
	}
	
	public void rotateAntiClockwise() {
		//Bottom to top, left to right
		rotateClockwise();
		rotateClockwise();
		rotateClockwise();
	}
	
	public void addHeight(int rows) {
		for(int x = 0; x < rows; x++) {
			addVerticalLine();
			mazeHeight++;
		}
		addHorizontalLine();
	}
	
	public void addWidth(int rows) {
		rotateClockwise();
		addHeight(rows);
		rotateAntiClockwise();
	}
	
	private void addVerticalLine() {
		calculateBlocks();
		data.set(blocks);
		data.set(blocks + mazeWidth);
	}
	private void addHorizontalLine() {
		calculateBlocks();
		data.set(blocks - mazeWidth, blocks);
	}
	
	private void calculateBlocks() {
		blocks = (mazeWidth + 1) * mazeHeight + mazeWidth * mazeHeight + mazeWidth;
	}
	
}
