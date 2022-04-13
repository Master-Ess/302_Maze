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
	}
	
	public void rotateAntiClockwise() {
		//Bottom to top, left to right
		BitSet rotated = new BitSet();
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
		blocks = (mazeHeight + 1) * mazeWidth + mazeWidth * mazeHeight + mazeHeight;
	}
	
}
