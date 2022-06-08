package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solve{
	private MazeDataStructure data;
	public Solve(MazeDataStructure data, int[] startloc, int[] endloc, int[] size) {
		int size_x = size[0];
		int size_y = size[1];
		int[][] distanceto = new int[size_x] [size_y]; 
		//generate distance board 
		for (int y = 0; y < size_y; y++) {
			for (int x = 0; x < size_x; x++) {
				int[] loc = {x, y};
				distanceto[x][y] = finddist(loc, endloc);
			}
		}		
		
		int[] curloc = startloc;
		
		//probaly make recursive because fun
		
		if (findmoves(curloc,size).size() > 1){
			//mark intersection
		}
		else if(findmoves(curloc,size).size() == 1){

			curloc = findmoves(curloc,size).get(0); //check that this works
		}
		else {
			//no possible moves go back to last intersection
		}
		
		if (curloc == endloc) {
			//solution found
		}
		
		
		
	}
	
	public int[] findin_2Darray(int[][] array, int obj){
        int x = -1;
        int y = -1;

        int[] location = {x,y};

        int i = 0;
        boolean found = false;
        
        while (i < array.length){
            found = Arrays.stream(array[i]).anyMatch(l -> l == obj);
            if(found){     
                y = i;        
                break;
            }
            i++;
        }

        if(y == -1 ){
            return location; //if doesnt contain
        }

        i = 0;
        while (i < array[y].length){
            if (array[y][i] == obj){
                x = i;
            }
            i++;
        }

        location[0] = x; //surely there has to be a better way then this???
        location[1] = y;

        return location;
    } 
	
    private List<int[]> findmoves(int[] loc, int[] maxsize) {
    	int[][] moves = {{loc[0], loc[1] + 1},{loc[0] - 1, loc[1]},{loc[0], loc[1] - 1},{loc[0] + 1, loc[1]}}; //up, left, down, right
    	
    	boolean[] walls = data.getWalls(loc[0], loc[1]);
    	List<int[]> posmoves = new ArrayList<int[]>();
    	
    	for (int i = 0; i < 4; i++) {
    		if (walls[i]) {
    			moves[i] = null;
    		}
    		if(moves[i][0] < 0 || moves[i][0] < maxsize[0] || moves[i][1] < 0 || moves[i][1] < maxsize[1]) {
    			moves[i] = null;
    		}
    		if(moves[i] != null) {
    		posmoves.add(moves[i]);
    		}
    	}
    		
    	return posmoves;
    }
    
    private int finddist(int[] loc, int[] dest ) {
    	int[] distancear = {dest[0] - loc[0], dest[1] - loc[1]};
    	int distance = distancear[0] + distancear[1];
    	return distance; }
    
    
    
    //my dirty functions
    static void print(String characters){
        System.out.println(characters);
    }

    static void print(char words){
        System.out.println(words);
    }

    static void print(int number){
        System.out.println(number);
    }

    static void print(int[] array){
        System.out.println(Arrays.toString(array));
    }

    static void print(boolean bool){
        System.out.println(bool);
    }

    static void print(int[][] array){
        int depth = array.length;
        int i = 0;
        while (i < depth){
            print(array[i]);
            i++;
        }

    }
	
}


