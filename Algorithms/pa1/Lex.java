/**
 *Ruikang Tao
 *rtao6
 *Programming Assignment 1
 */

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

class Lex{
	
	public static void main(String[] args) throws IOException{
		
		//declare variables
		List L = new List();
		String line = null;
		String[] A = null;
		int count = 0;
		int a = 0;
		int j = 0;
		int k;
		
		// check command line input
		if(args.length < 2){
			System.out.println("Usage: Lex input_file output_file");
			System.exit(1);
		}
		
		//initialize in and out scanners
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		// read input file and count lines
		Scanner lines = new Scanner(new File(args[0]));
		while(lines.hasNextLine()){
			lines.nextLine();
			count++;
		}
		lines.close();
		
		// stores input into String array
		A = new String[count];
		while(in.hasNextLine()){
			A[a] = in.nextLine();
			a++;
		}
		
		// sorting algorithm
		L.append(j); // inserts 0 initially
		for(j = 1; j < A.length; j++){
			L.moveFront();
			while(L.index() >= 0){
				k = L.get();
				// if A[j] comes before current
				if(A[j].compareTo(A[k]) < 0){
					L.insertBefore(j);
					L.moveBack();
				}
				if(A[j].compareTo(A[k]) == 0){
					L.insertAfter(j);
					L.moveBack();
				}
				L.moveNext();
			}
			// if A[j] comes after current
			if(A[j].compareTo(A[L.back()]) > 0 && L.index() == -1){
				L.append(j);
			}
		}
		
		// writing into output file
		L.moveFront();
		for(int n = 0; n < A.length; n++){
			out.println(A[L.get()]);
			L.moveNext();
		}
		
		// close in and out scanners
		in.close();
		out.close();
	}
}
