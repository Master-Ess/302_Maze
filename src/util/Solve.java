package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solve{
	private MazeDataStructure data;
	private List<int[]> path = new ArrayList<int[]>();					//contains a list of arrays that indicate that path that was taken
	public Solve(MazeDataStructure data) {
		this.data = data;
		int[][] startingLocs = findGaps();
		if(startingLocs == null) {
			path = null;
		} else {
			int[] startloc = startingLocs[0];
			int[] endloc = startingLocs[1];
			int[] size = {data.getWidth(), data.getHeight()};
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
	
			List<int[]> intersections = new ArrayList<int[]>();			//lists all intersections along the path
			List<Integer> permutations = new ArrayList<Integer>();		//lists the number of splits at each intersection ( 2 or 3)
			List<Integer> testedpaths = new ArrayList<Integer>();		//list the number of splits explored for each intersection - default 1 max 3
			
			int interdepth = -1; //might not be needed
			
			int[] curloc = startloc;
			
			
			
			//recursive because fun
			while (curloc != endloc) { 
				if (findmoves(curloc,size).size() > 1){ 			//if there is multiple directions to go
					
					path.add(curloc);
					intersections.add(curloc);
					interdepth++;
					permutations.add(findmoves(curloc,size).size());
					testedpaths.add(1);
					
					curloc = findmoves(curloc,size).get(0); 
				}
				else if(findmoves(curloc,size).size() == 1){ 		// if there is one direction to go
					
					path.add(curloc);
					curloc = findmoves(curloc,size).get(0); 												//check that this works
				}
				else {												// if there is no direction to go
					
					while (permutations.get(interdepth) == testedpaths.get(interdepth)) { //find last unexplored intersection
						if (interdepth == 0){
							break; //need to return something i guess
						}
						
						intersections.remove(interdepth);
						permutations.remove(interdepth);
						testedpaths.remove(interdepth);
						interdepth--;
					}
					
					curloc = findmoves(intersections.get(interdepth),size).get(testedpaths.get(interdepth) + 1); //finds the next path from the last unexpored intersection
					while (path.get(path.size() - 1) != curloc) { //should trim path until it matches the intersection
						path.remove(path.size() - 1);
					}
					testedpaths.set(interdepth, testedpaths.get(interdepth) + 1); 	//should update the tested path
					
					//no possible moves go back to last intersection and try next route
				}		
			}
			
			if (path.get(path.size() - 1) != endloc){
				path = null;
			}
			
			//print(path)
			
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
    
    //Simple function to find gaps in the walls of the maze
    private int[][] findGaps() {
    	int[][] returnLocs = new int[2][2];
    	boolean loc1Found = false;
    	boolean loc2Found = false;
    	boolean value = false;
    	boolean[] walls = null;
		
    	for(int x = 0; x < data.getWidth(); x++) {
    		for(int y = 0; y < data.getHeight(); y++) {
    			value = true;
    			if(x == 0 || y == 0 || x == data.getWidth() - 1 || y == data.getHeight() - 1) {
    				walls = data.getWalls(x, y);
    				if(x == 0 && !loc2Found) {
    					value = walls[0];
    				}if(x == data.getWidth() - 1 && !loc2Found) {
    					value = walls[2];
    				}if(y == 0 && !loc2Found) {
    					value = walls[1];
    				}if(y == data.getHeight() - 1 && !loc2Found) {
    					value = walls[3];
    				}
    				if(value == false && !loc2Found) {
    					if(!loc1Found) {
    						returnLocs[0][0]=x;
    						returnLocs[0][1]=y;
    						loc1Found = true;
    					}else if(returnLocs[0][0] != x && returnLocs[0][1] != y && !loc2Found){
    						returnLocs[1][0] = x;
    						returnLocs[1][1] = y;
    						loc2Found = true;
    					}
    				}
    			}
    		}
    	}
    	
    	if(!loc2Found) {
    		return null;
    	}
    	
    	return returnLocs;
    }
    
    private int finddist(int[] loc, int[] dest ) {
    	int distance = dest[0] - loc[0] + dest[1] - loc[1];
    	return distance;
    }
    
    
    
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
    
    public List<int[]> getPath() {
    	return path;
    }
	
}


