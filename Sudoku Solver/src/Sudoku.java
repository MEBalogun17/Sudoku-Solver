/* Mojola Balogun
 * February 22, 2015
 * "Sudoku Solver"
 * Pseudocode - "How I plan to go about solving a sudoku puzzle"
 * First, loop through each value and place a value between 1 and 9
 * Check to see if the value is valid at the location
 * Move to the next location
 * Check to see if the value is valid
 * If there is no value, place a random value that is not a used value for that spot
 * Do this for every unfilled spot
 * Check to see if the puzzle is solved
 * If not, try solving it again
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Sudoku {
	
	private int[][] puzzle = new int[9][9];
	private int[][] newPuzzle;
	
	public void setSudoku(int[][] p){
		puzzle = p;
	}
	
	public int[][] solve(){
		int[][] cPuzzle = puzzle;
		for(int x = 0;x<9;x++)
			for(int y = 0;y<9;y++)
				for(int i = 1;i<=9;i++){
					if(cPuzzle[x][y]==0&&check(cPuzzle,x,y,i)){
						cPuzzle[x][y]=i;
						break;}
				}
		System.out.println("Puzzle");
		for(int row = 0; row<9;row++)
			System.out.println(Arrays.toString(cPuzzle[row]));
		if(!solved(cPuzzle)){
			newPuzzle = cPuzzle;
			return solve(newPuzzle);
		}
		return cPuzzle;
	}
	
	public int[][] solve(int[][] p){
		int[][] cPuzzle = p;
		for(int x = 0;x<9;x++)
			for(int y = 0;y<9;y++)
				for(int i = 1;i<=9;i++){
					if(cPuzzle[x][y]==0&&check(cPuzzle,x,y,i)){
						cPuzzle[x][y]=i;
						break;
					}
				}
		System.out.println("Puzzle");
		for(int row = 0; row<9;row++)
			System.out.println(Arrays.toString(cPuzzle[row]));
		if(!solved(cPuzzle)){
			newPuzzle = cPuzzle;
			return solve(newPuzzle);
		}
		return cPuzzle;
	}
	
	//Is the value i valid at a given location
	public boolean check(int[][] p, int x, int y, int i){
		//Run through each value in the row
		for(int j = 0;j<9;j++)
			if(p[x][j]==i)
				return false;
		//Run through each value in a column
		for(int k = 0;k<9;k++)
			if(p[k][y]==i)
				return false;
		//Given an x and a y value find what square its in
		//Get how far off x or y if from 3 and subtract it from x or y
		for(int boxX = x-(x%3);boxX<(x-(x%3))+3;boxX++)
			for(int boxY = y-(y%3);boxY<(y-(y%3))+3;boxY++)
				if(p[boxX][boxY]==i)
					return false;
		return true;
	}
	
//	public ArrayList<Integer> getNeededNumbers(int[][] p, int x, int y){
//		ArrayList<Integer> numbers = new ArrayList<Integer>();
//		for(int i = 1;i<10;i++)
//			numbers.add(i);
//		for(int ys = 0;ys<9;ys++)
//			if(p[x][ys]!=0&&numbers.contains(p[x][ys]))
//				numbers.remove(new Integer(p[x][ys]));
//		for(int xs = 0;xs<9;xs++)
//			if(p[xs][y]!=0&&numbers.contains(p[xs][y]))
//				numbers.remove(new Integer(p[xs][y]));
//		for(int boxX = x-(x%3);boxX<(x-(x%3))+3;boxX++)
//			for(int boxY = y-(y%3);boxY<(y-(y%3));boxY++)
//				if(p[boxX][boxY]!=0&&numbers.contains(p[boxX][boxY]))
//					numbers.remove(new Integer(p[boxX][boxY]));
//		return numbers;
//	}
	
	//Check to see if the sum of each row, column and box equals 45
	public boolean solved(int[][] p){
		//Row check
		for(int i = 0;i<9;i++){
			int rowSum = 0;
			for(int j = 0;j<9;j++){
				rowSum += p[i][j];
			}
			if(rowSum!=45)
				return false;
		}
		//Column check
		for(int x = 0;x<9;x++){
			int colSum = 0;
			for(int y = 0;y<9;y++){
				colSum += p[x][y];
			}
			if(colSum!=45)
				return false;
		}
		//A Box check could be added but idk if it's needed
		return true;
	}
}
