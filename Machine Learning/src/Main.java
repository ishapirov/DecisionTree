import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
		//CLASSIFICATION 1
	    classification1();
		//classificationOptimization1();
		
		//CLASSIFICATION 2
		//classification2();
		//classificationOptimization2();
	     
		//CLASSIFICATION 3
		//classification3();
		//classificationOptimization3();
		
		//CLASSIFICATION 4
		//classification4();
		//classificationOptimization4();
		
		//CLASSIFICATION 5
		//classification5(); 
		//classificationOptimization5();
		
		//MissingValue 1
		//missingV1();
		
		 
		//MissingValue 2
		//missingV2();
		 
	}
	public static void classificationOptimization3() {
		 double[][] trainingData = new double[5040][13];
		 double[][] testingData = new double[1260][13];
		 File trainingDataFile;
		 Scanner myReader = null;
		 try {
			 trainingDataFile = new File("TrainData3.txt");
			 myReader = new Scanner(trainingDataFile);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 int trainingCounter = 0;
		 int testingCounter = 0;
		 for(int i=0;i<trainingData.length+testingData.length;i++) { 
			 String data = myReader.nextLine();
			 String[] splited = data.split("\\s+");
			 if(i%5 != 0) { 
				 for(int j = 0;j<trainingData[0].length;j++) {
					trainingData[trainingCounter][j] = Double.parseDouble(splited[j]);
				 } 
				 trainingCounter++;
			 }
			 else {
				 for(int j = 0;j<trainingData[0].length;j++) {
					testingData[testingCounter][j] = Double.parseDouble(splited[j]);
				 } 
				 testingCounter++;
			 }
			
		 }
		 
		 //Predicting missing values
		 trainingData = missingValuePredict(trainingData,5);
		 testingData = missingValuePredict(testingData,5);
		 
		 File trainingLabelFile;
		 Scanner labelReader = null;
		 int[] trainingLabel = new int[5040];
		 int[] trainingLabel2 = new int[1260];
		 int training1Counter = 0;
		 int training2Counter = 0;
		 try {
			 trainingLabelFile = new File("TrainLabel3.txt");
			 labelReader = new Scanner(trainingLabelFile);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingLabel.length + trainingLabel2.length;i++) { 
			 String data = labelReader.nextLine();
			 if(i%5 != 0) { 
				 trainingLabel[training1Counter] = Integer.parseInt(data); 
				 training1Counter++;
			 }
			 else {
				 trainingLabel2[training2Counter] = Integer.parseInt(data); 
				 training2Counter++;
			 }
			
		 } 
		
		 
		 int[][] catTrainingData3 =  categorify(trainingData);
		 int[][] catTestingData3 =  categorify(testingData);
		 
		 //Only 13 features so no feature selection necessary
		
		
		 int testingLabelPossible3 = countDistinct(trainingLabel);
	
		 int[] colsToSelect3 = new int[13];
		 for(int i=0;i<13;i++) {
			 colsToSelect3[i] = i;
		 }
		 
		 //Building decision tree
		 int[] subAtEnd = new int[13];
		 DecisionTreeNode root3 = new DecisionTreeNode(true);
		 DecisionTreeNode.setMaxNrOfChildren(countDistinct(trainingLabel));
		 int[] numValPerCol3 = new int[13];
		 for(int i=0;i<numValPerCol3.length;i++) {
			 int[] colInData = new int[catTrainingData3.length];
			 for(int j=0;j<catTrainingData3.length;j++) {
				 colInData[j] = catTrainingData3[j][i];
			 }
			 int min = min(colInData);
			 numValPerCol3[i] = countDistinct(colInData);
			 
			 subAtEnd[i] = min;
		 }
		 
		 for(int i=0;i<catTrainingData3[0].length;i++) {
			 for(int j=0;j<catTrainingData3.length;j++) {
				 catTrainingData3[j][i] = catTrainingData3[j][i]-subAtEnd[i];
			 }
		 }
	
		 
		 createTree3(root3,catTrainingData3,trainingLabel,colsToSelect3,numValPerCol3,testingLabelPossible3,5);
		
		 
		 for(int i=0;i<catTestingData3[0].length;i++) {
			 for(int j=0;j<catTestingData3.length;j++) {
				 catTestingData3[j][i] = catTestingData3[j][i]-subAtEnd[i];
			 }
		 }
		 
		 int[] predictLabel3 = new int[testingData.length];
		 
		 predictLabel3 = decisionTreePrediction2(catTestingData3,root3);
		 
		 
		 int numCorrect = 0;
		 for(int i=0;i<predictLabel3.length;i++) {
			 if(predictLabel3[i] == trainingLabel2[i])
			 	numCorrect++;
		 }
		 System.out.println(numCorrect);
		 labelReader.close();
	}
	public static void classificationOptimization5() {
		 double[][] trainingData = new double[895][11];
		 double[][] testingData = new double[224][11];
		 File trainingDataFile;
		 Scanner myReader = null;
		 try {
			 trainingDataFile = new File("TrainData5.txt");
			 myReader = new Scanner(trainingDataFile);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 int trainingCounter = 0;
		 int testingCounter = 0;
		 for(int i=0;i<trainingData.length+testingData.length;i++) { 
			 String data = myReader.nextLine();
			 String[] splited = data.split("\\s+");
			 if(i%5 != 0) { 
				 for(int j = 1;j<trainingData[0].length+1;j++) {
					trainingData[trainingCounter][j-1] = Double.parseDouble(splited[j]);
				 } 
				 trainingCounter++;
			 }
			 else {
				 for(int j = 1;j<trainingData[0].length+1;j++) {
					testingData[testingCounter][j-1] = Double.parseDouble(splited[j]);
				 } 
				 testingCounter++;
			 }
			
		 }
		 
		 
		 
		 //Predicting missing values
		 
		 File trainingLabelFile;
		 Scanner labelReader = null;
		 int[] wholeThing = new int[1119];
		 int[] trainingLabel = new int[895];
		 int[] trainingLabel2 = new int[224];
		 int training1Counter = 0;
		 int training2Counter = 0;
		 try {
			 trainingLabelFile = new File("TrainLabel5.txt");
			 labelReader = new Scanner(trainingLabelFile);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingLabel.length + trainingLabel2.length;i++) { 
			 String data = labelReader.nextLine();
			 wholeThing[i] = Integer.parseInt(data);
			 if(i%5 != 0) { 
				 if(wholeThing[i] == 10)
					 trainingLabel[training1Counter] = Integer.parseInt(data)-3; 
				 else
					 trainingLabel[training1Counter] = Integer.parseInt(data)-2; 
				 training1Counter++;
			 }
			 else {
				 if(wholeThing[i] == 10)
					 trainingLabel2[training2Counter] = Integer.parseInt(data)-3; 
				 else
					 trainingLabel2[training2Counter] = Integer.parseInt(data)-2;  
				 training2Counter++;
			 }
			
		 } 
		
		 //making data categorical for decision tree
		 int[][] catTrainingData =  convertToCategorical(trainingData,10);
		 
		 //Selecting 50 most informative features
		 int[] colsToSelect = new int[11];
		 for(int i=0;i<11;i++) {
			 colsToSelect[i] = i;
		 }
		 
		 int testingLabelPossible = countDistinct(trainingLabel);
	
		 
		 //Building decision tree
		 DecisionTreeNode root = new DecisionTreeNode();
		 
		 createTree(root,catTrainingData,trainingLabel,colsToSelect,testingLabelPossible,3);
		 
		 int[][] catTestingData = convertToCategorical(testingData,10);
		 
		 int[] predictLabel = new int[testingData.length];
		 predictLabel = decisionTreePrediction(catTestingData,root);
		 int numCorrect = 0;
		 for(int i=0;i<predictLabel.length;i++) {
			 if(predictLabel[i] == trainingLabel2[i])
				 numCorrect++;
		 }
		 
		 System.out.println(numCorrect);
		 labelReader.close();
	}
	public static void classificationOptimization4() {
		 double[][] trainingData = new double[2037][112];
		 double[][] testingData = new double[510][112];
		 File trainingDataFile;
		 Scanner myReader = null;
		 try {
			 trainingDataFile = new File("TrainData4.txt");
			 myReader = new Scanner(trainingDataFile);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 int trainingCounter = 0;
		 int testingCounter = 0;
		 for(int i=0;i<trainingData.length+testingData.length;i++) { 
			 String data = myReader.nextLine();
			 String[] splited = data.split("\\s+");
			 if(i%5 != 0) { 
				 for(int j = 1;j<trainingData[0].length+1;j++) {
					trainingData[trainingCounter][j-1] = Double.parseDouble(splited[j]);
				 } 
				 trainingCounter++;
			 }
			 else {
				 for(int j = 1;j<trainingData[0].length+1;j++) {
					testingData[testingCounter][j-1] = Double.parseDouble(splited[j]);
				 } 
				 testingCounter++;
			 }
			
		 }
		 
		 //Predicting missing values
		 
		 File trainingLabelFile;
		 Scanner labelReader = null;
		 int[] trainingLabel = new int[2037];
		 int[] trainingLabel2 = new int[510];
		 int training1Counter = 0;
		 int training2Counter = 0;
		 try {
			 trainingLabelFile = new File("TrainLabel4.txt");
			 labelReader = new Scanner(trainingLabelFile);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingLabel.length + trainingLabel2.length;i++) { 
			 String data = labelReader.nextLine();
			 if(i%5 != 0) { 
				 trainingLabel[training1Counter] = Integer.parseInt(data); 
				 training1Counter++;
			 }
			 else {
				 trainingLabel2[training2Counter] = Integer.parseInt(data); 
				 training2Counter++;
			 }
			
		 } 
		
		 //making data categorical for decision tree
		 int[][] catTrainingData =  convertToCategorical(trainingData,7);
		 
		 //Selecting 50 most informative features
		 int numCols = 25;
		 int []colsToSelect = featureSelection(catTrainingData,trainingLabel,numCols,7);
		 
		 int[][] reducedTrainingData = new int[catTrainingData.length][numCols];
		
		 for(int x = 0;x<numCols;x++) {
				int colToIterate = colsToSelect[x];
				for(int y = 0;y < trainingData.length;y++) {
					reducedTrainingData[y][x] = catTrainingData[y][colToIterate];
				}
		}
		 
		 int testingLabelPossible = countDistinct(trainingLabel);
	
		 
		 //Building decision tree
		 DecisionTreeNode root = new DecisionTreeNode();
		 
		 createTree(root,reducedTrainingData,trainingLabel,colsToSelect,testingLabelPossible,5);
		 
		 int[][] catTestingData = convertToCategorical(testingData,7);
		 
		 int[] predictLabel = new int[testingData.length];
		 predictLabel = decisionTreePrediction(catTestingData,root);
		 int numCorrect = 0;
		 for(int i=0;i<predictLabel.length;i++) {
			 if(predictLabel[i] == trainingLabel2[i])
				 numCorrect++;
		 }
		 
		 System.out.println(numCorrect);
		 labelReader.close();
	}
	
	public static void classificationOptimization1() {
		 double[][] trainingData = new double[120][3312];
		 double[][] testingData = new double[30][3312];
		 File trainingDataFile;
		 Scanner myReader = null;
		 try {
			 trainingDataFile = new File("TrainData1.txt");
			 myReader = new Scanner(trainingDataFile);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 int trainingCounter = 0;
		 int testingCounter = 0;
		 for(int i=0;i<trainingData.length+testingData.length;i++) { 
			 String data = myReader.nextLine();
			 String[] splited = data.split("\\s+");
			 if(i%5 != 0) { 
				 for(int j = 0;j<trainingData[0].length;j++) {
					trainingData[trainingCounter][j] = Double.parseDouble(splited[j]);
				 } 
				 trainingCounter++;
			 }
			 else {
				 for(int j = 0;j<trainingData[0].length;j++) {
					testingData[testingCounter][j] = Double.parseDouble(splited[j]);
				 } 
				 testingCounter++;
			 }
			
		 }
		
		 
		 //Predicting missing values
		 trainingData = missingValuePredict(trainingData,5);
		 testingData = missingValuePredict(testingData,5);
		 File trainingLabelFile;
		 Scanner labelReader = null;
		 int[] trainingLabel = new int[120];
		 int[] trainingLabel2 = new int[30];
		 int training1Counter = 0;
		 int training2Counter = 0;
		 try {
			 trainingLabelFile = new File("TrainLabel1.txt");
			 labelReader = new Scanner(trainingLabelFile);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingLabel.length + trainingLabel2.length;i++) { 
			 String data = labelReader.nextLine();
			 if(i%5 != 0) { 
				 trainingLabel[training1Counter] = Integer.parseInt(data); 
				 training1Counter++;
			 }
			 else {
				 trainingLabel2[training2Counter] = Integer.parseInt(data); 
				 training2Counter++;
			 }
			
		 } 
		
		 //making data categorical for decision tree
		 int[][] catTrainingData =  convertToCategorical(trainingData,9);
		 
		 //Selecting 20 most informative features
		 int numCols = 20;
		 int []colsToSelect = featureSelection(catTrainingData,trainingLabel,numCols,9);
		 
		 int[][] reducedTrainingData = new int[catTrainingData.length][numCols];
		
		 for(int x = 0;x<numCols;x++) {
				int colToIterate = colsToSelect[x];
				for(int y = 0;y < trainingData.length;y++) {
					reducedTrainingData[y][x] = catTrainingData[y][colToIterate];
				}
		}
		 
		 int testingLabelPossible = countDistinct(trainingLabel);
	
		 
		 //Building decision tree
		 DecisionTreeNode root = new DecisionTreeNode();
		 
		 createTree(root,reducedTrainingData,trainingLabel,colsToSelect,testingLabelPossible,3);
		 
		 int[][] catTestingData = convertToCategorical(testingData,9);
		 
		 int[] predictLabel = new int[testingData.length];
		 predictLabel = decisionTreePrediction(catTestingData,root);
		 
		 
		 int numCorrect = 0;
		 for(int i=0;i<predictLabel.length;i++) {
			 if(predictLabel[i] == trainingLabel2[i])
			 	numCorrect++;
			 System.out.print(predictLabel[i] + " " + trainingLabel2[i]);
			 System.out.println();
		 }
		 System.out.println(numCorrect);
		 labelReader.close();
	}
	
	public static void classificationOptimization2() {
		 double[][] trainingData = new double[80][9182];
		 double[][] testingData = new double[20][9182];
		 File trainingDataFile;
		 Scanner myReader = null;
		 try {
			 trainingDataFile = new File("TrainData2.txt");
			 myReader = new Scanner(trainingDataFile);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 int trainingCounter = 0;
		 int testingCounter = 0;
		 for(int i=0;i<trainingData.length+testingData.length;i++) { 
			 String data = myReader.nextLine();
			 String[] splited = data.split("\\s+");
			 if(i%5 != 0) { 
				 for(int j = 1;j<trainingData[0].length+1;j++) {
					trainingData[trainingCounter][j-1] = Double.parseDouble(splited[j]);
				 } 
				 trainingCounter++;
			 }
			 else {
				 for(int j = 1;j<trainingData[0].length+1;j++) {
					testingData[testingCounter][j-1] = Double.parseDouble(splited[j]);
				 } 
				 testingCounter++;
			 }
			
		 }
	
		 
		 //Predicting missing values
		 trainingData = missingValuePredict(trainingData,5);
		 testingData = missingValuePredict(testingData,5);
		 File trainingLabelFile;
		 Scanner labelReader = null;
		 int[] trainingLabel = new int[80];
		 int[] trainingLabel2 = new int[20];
		 int training1Counter = 0;
		 int training2Counter = 0;
		 try {
			 trainingLabelFile = new File("TrainLabel2.txt");
			 labelReader = new Scanner(trainingLabelFile);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingLabel.length + trainingLabel2.length;i++) { 
			 String data = labelReader.nextLine();
			 if(i%5 != 0) { 
				 trainingLabel[training1Counter] = Integer.parseInt(data); 
				 training1Counter++;
			 }
			 else {
				 trainingLabel2[training2Counter] = Integer.parseInt(data); 
				 training2Counter++;
			 }
			
		 } 
		
		 //making data categorical for decision tree
		 int[][] catTrainingData =  convertToCategorical(trainingData,4);
		 
		 //Selecting 20 most informative features
		 int numCols = 20;
		 int []colsToSelect = featureSelection(catTrainingData,trainingLabel,numCols,4);
		 
		 int[][] reducedTrainingData = new int[catTrainingData.length][numCols];
		
		 for(int x = 0;x<numCols;x++) {
				int colToIterate = colsToSelect[x];
				for(int y = 0;y < trainingData.length;y++) {
					reducedTrainingData[y][x] = catTrainingData[y][colToIterate];
				}
		}
		 
		 int testingLabelPossible = countDistinct(trainingLabel);
	
		 
		 //Building decision tree
		 DecisionTreeNode root = new DecisionTreeNode();
		 
		 createTree(root,reducedTrainingData,trainingLabel,colsToSelect,testingLabelPossible,4);
		 
		 int[][] catTestingData = convertToCategorical(testingData,4);
		 
		 int[] predictLabel = new int[testingData.length];
		 predictLabel = decisionTreePrediction(catTestingData,root);
		 int numCorrect = 0;
		 for(int i=0;i<predictLabel.length;i++) {
			 if(predictLabel[i] == trainingLabel2[i])
				 numCorrect++;
			 System.out.print(predictLabel[i]);
			 System.out.print(trainingLabel2[i]);
			 System.out.println();
			 	
		 }
		 
		 System.out.println(numCorrect);
		 labelReader.close();
	}
	
	public static void classification1() {
		 double[][] trainingData = new double[150][3312];
		 File trainingDataFile;
		 Scanner myReader = null;
		 try {
			 trainingDataFile = new File("TrainData1.txt");
			 myReader = new Scanner(trainingDataFile);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingData.length;i++) { 
			 String data = myReader.nextLine();
			 String[] splited = data.split("\\s+");
			 for(int j = 0;j<splited.length;j++) {
				trainingData[i][j] = Double.parseDouble(splited[j]); 
			 } 
		 }
		 
		 //Predicting missing values
		 trainingData = missingValuePredict(trainingData,5);
		 
		 File trainingLabelFile;
		 Scanner labelReader = null;
		 int[] trainingLabel = new int[150];
		 try {
			 trainingLabelFile = new File("TrainLabel1.txt");
			 labelReader = new Scanner(trainingLabelFile);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingLabel.length;i++) { 
			 String data = labelReader.nextLine();
			 trainingLabel[i] = Integer.parseInt(data); 
		 } 
		
		 //making data categorical for decision tree
		 int[][] catTrainingData =  convertToCategorical(trainingData,9);
		 
		 //Selecting 20 most informative features
		 int numCols = 20;
		 int []colsToSelect = featureSelection(catTrainingData,trainingLabel,numCols,9);
		 
		 int[][] reducedTrainingData = new int[catTrainingData.length][numCols];
		
		 for(int x = 0;x<numCols;x++) {
				int colToIterate = colsToSelect[x];
				for(int y = 0;y < trainingData.length;y++) {
					reducedTrainingData[y][x] = catTrainingData[y][colToIterate];
				}
		}
		 
		 int testingLabelPossible = countDistinct(trainingLabel);
	
		 
		 //Building decision tree
		 DecisionTreeNode root = new DecisionTreeNode();
		 
		 createTree(root,reducedTrainingData,trainingLabel,colsToSelect,testingLabelPossible,3);
		
		 
		 double[][] testingData = new double[53][3312];
		 File testingDataFile;
		 Scanner testReader = null;
		 try {
			 testingDataFile = new File("TestData1.txt");
			 testReader = new Scanner(testingDataFile);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<testingData.length;i++) { 
			 String data = testReader.nextLine();
			 String[] splited = data.split("\\s+");
			 for(int j = 0;j<splited.length;j++) {
				 testingData[i][j] = Double.parseDouble(splited[j]); 
			 } 
		 }
		 testingData = missingValuePredict(testingData,5);
		 
		 int[][] catTestingData = convertToCategorical(testingData,9);
		 
		 int[] predictLabel = new int[testingData.length];
		 
		 predictLabel = decisionTreePrediction(catTestingData,root);
		 System.out.println("TrainLabel1");
		 for(int i=0;i<predictLabel.length;i++) {
			 System.out.println(predictLabel[i]);
		 }
		 System.out.println();
		 testReader.close();
		 labelReader.close();
	}
	
	public static void classification2() {
		 double[][] trainingData2 = new double[100][9182];
		 File trainingDataFile2;
		 Scanner myReader2 = null;
		 try {
			 trainingDataFile2 = new File("TrainData2.txt");
			 myReader2 = new Scanner(trainingDataFile2);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingData2.length;i++) { 
			 String data = myReader2.nextLine();
			 String[] splited = data.split("\\s+");
			 for(int j = 1;j<trainingData2[0].length+1;j++) {
				trainingData2[i][j-1] = Double.parseDouble(splited[j]); 
			 } 
		 }
		 
		 //Predicting missing values
		 trainingData2 = missingValuePredict(trainingData2,5);
		 
		 File trainingLabelFile2;
		 Scanner labelReader2 = null;
		 int[] trainingLabel2 = new int[100];
		 try {
			 trainingLabelFile2 = new File("TrainLabel2.txt");
			 labelReader2 = new Scanner(trainingLabelFile2);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingLabel2.length;i++) { 
			 String data = labelReader2.nextLine();
			 trainingLabel2[i] = Integer.parseInt(data); 
		 } 
		
		 //making data categorical for decision tree
		 int[][] catTrainingData2 =  convertToCategorical(trainingData2,4);
		 
		 //Selecting 50 most informative features
		 int numCols2 = 20;
		 int []colsToSelect2 = featureSelection(catTrainingData2,trainingLabel2,numCols2,4);
		 
		 int[][] reducedTrainingData2 = new int[catTrainingData2.length][numCols2];
		
		 for(int x = 0;x<numCols2;x++) {
				int colToIterate = colsToSelect2[x];
				for(int y = 0;y < trainingData2.length;y++) {
					reducedTrainingData2[y][x] = catTrainingData2[y][colToIterate];
				}
		}
	
		 
		 //Building decision tree
		 DecisionTreeNode root2 = new DecisionTreeNode();
		 int testingLabelPossible2 = countDistinct(trainingLabel2);
		 
		 createTree(root2,reducedTrainingData2,trainingLabel2,colsToSelect2,testingLabelPossible2,4);
		
		 //TRAINING DATA 2
		 double[][] testingData2 = new double[74][9182];
		 File testingDataFile2;
		 Scanner testReader2 = null;
		 try {
			 testingDataFile2 = new File("TestData2.txt");
			 testReader2 = new Scanner(testingDataFile2);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<testingData2.length;i++) { 
			 String data = testReader2.nextLine();
			 String[] splited = data.split("\\s+");
			 for(int j = 1;j<testingData2[0].length;j++) {
				 testingData2[i][j-1] = Double.parseDouble(splited[j]); 
			 } 
		 }
		 testingData2 = missingValuePredict(testingData2,5);
		 
		 int[][] catTestingData2 = convertToCategorical(testingData2,4);
		 
		 int[] predictLabel2 = new int[testingData2.length];
		 
		 predictLabel2 = decisionTreePrediction(catTestingData2,root2);
		 System.out.println("TrainLabel2");
		 for(int i=0;i<predictLabel2.length;i++) {
			 System.out.println(predictLabel2[i]);
		 }
		 System.out.println();
		 testReader2.close();
		 labelReader2.close();
		 
	}
	
	public static void classification3() {
		 double[][] trainingData3 = new double[6300][13];
		 File trainingDataFile3;
		 Scanner myReader3 = null;
		 try {
			 trainingDataFile3 = new File("TrainData3.txt");
			 myReader3 = new Scanner(trainingDataFile3);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingData3.length;i++) { 
			 String data = myReader3.nextLine();
			 String[] splited = data.split("\\s+");
			 for(int j = 0;j<splited.length;j++) {
				trainingData3[i][j] = Double.parseDouble(splited[j]); 
			 } 
		 }
		 
		 //Predicting missing values
		 trainingData3 = missingValuePredict(trainingData3,5);
		 
		 File trainingLabelFile3;
		 Scanner labelReader3 = null;
		 int[] trainingLabel3 = new int[6300];
		 try {
			 trainingLabelFile3 = new File("TrainLabel3.txt");
			 labelReader3 = new Scanner(trainingLabelFile3);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingLabel3.length;i++) { 
			 String data = labelReader3.nextLine();
			 trainingLabel3[i] = Integer.parseInt(data); 
		 } 
		
		 
		 int[][] catTrainingData3 =  categorify(trainingData3);
		 
		 //Only 13 features so no feature selection necessary
		
		
		 int testingLabelPossible3 = countDistinct(trainingLabel3);
	
		 int[] colsToSelect3 = new int[13];
		 for(int i=0;i<13;i++) {
			 colsToSelect3[i] = i;
		 }
		 
		 //Building decision tree
		 int[] subAtEnd = new int[13];
		 DecisionTreeNode root3 = new DecisionTreeNode(true);
		 DecisionTreeNode.setMaxNrOfChildren(countDistinct(trainingLabel3));
		 int[] numValPerCol3 = new int[13];
		 for(int i=0;i<numValPerCol3.length;i++) {
			 int[] colInData = new int[catTrainingData3.length];
			 for(int j=0;j<catTrainingData3.length;j++) {
				 colInData[j] = catTrainingData3[j][i];
			 }
			 int min = min(colInData);
			 numValPerCol3[i] = countDistinct(colInData);
			 
			 subAtEnd[i] = min;
		 }
		 
		 for(int i=0;i<catTrainingData3[0].length;i++) {
			 for(int j=0;j<catTrainingData3.length;j++) {
				 catTrainingData3[j][i] = catTrainingData3[j][i]-subAtEnd[i];
			 }
		 }
	
		 
		 createTree3(root3,catTrainingData3,trainingLabel3,colsToSelect3,numValPerCol3,testingLabelPossible3,5);
		
		 
		 double[][] testingData3 = new double[2693][13];
		 File testingDataFile3;
		 Scanner testReader3 = null;
		 try {
			 testingDataFile3 = new File("TestData3.txt");
			 testReader3 = new Scanner(testingDataFile3);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<testingData3.length;i++) { 
			 String data = testReader3.nextLine();
			 String[] splited = data.split(",");
			 for(int j = 0;j<splited.length;j++) {
				 testingData3[i][j] = Double.parseDouble(splited[j]); 
			 } 
		 }
		 
		 testingData3 = missingValuePredict2(testingData3,5);
		 
		 int[][] catTestingData3 = categorify(testingData3);
		 
		 for(int i=0;i<catTestingData3[0].length;i++) {
			 for(int j=0;j<catTestingData3.length;j++) {
				 catTestingData3[j][i] = catTestingData3[j][i]-subAtEnd[i];
			 }
		 }
		 
		 int[] predictLabel3 = new int[testingData3.length];
		 
		 predictLabel3 = decisionTreePrediction2(catTestingData3,root3);
		 System.out.println("TrainLabel3");
		 for(int i=0;i<predictLabel3.length;i++) {
			 System.out.println(predictLabel3[i]);
		 }
		 System.out.println();
		 testReader3.close();
		 labelReader3.close();
	}
	
	public static void classification4() {
		 double[][] trainingData4 = new double[2547][112];
		 File trainingDataFile4;
		 Scanner myReader4 = null;
		 try {
			 trainingDataFile4 = new File("TrainData4.txt");
			 myReader4 = new Scanner(trainingDataFile4);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingData4.length;i++) { 
			 String data = myReader4.nextLine();
			 String[] splited = data.split("\\s+");
			 for(int j = 1;j<trainingData4[0].length+1;j++) {
				trainingData4[i][j-1] = Double.parseDouble(splited[j]); 
			 } 
		 }
		 
		 //Predicting missing values
		 trainingData4 = missingValuePredict(trainingData4,5);
		 
		 File trainingLabelFile4;
		 Scanner labelReader4 = null;
		 int[] trainingLabel4 = new int[2547];
		 try {
			 trainingLabelFile4 = new File("TrainLabel4.txt");
			 labelReader4 = new Scanner(trainingLabelFile4);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingLabel4.length;i++) { 
			 String data = labelReader4.nextLine();
			 trainingLabel4[i] = Integer.parseInt(data); 
		 } 
		
		 //making data categorical for decision tree
		 int[][] catTrainingData4 =  convertToCategorical(trainingData4,7);
		 
		 //Selecting 50 most informative features
		 int numCols4 = 25;
		 int []colsToSelect4 = featureSelection(catTrainingData4,trainingLabel4,numCols4,7);
		 
		 int[][] reducedTrainingData4 = new int[catTrainingData4.length][numCols4];
		
		 for(int x = 0;x<numCols4;x++) {
				int colToIterate = colsToSelect4[x];
				for(int y = 0;y < trainingData4.length;y++) {
					reducedTrainingData4[y][x] = catTrainingData4[y][colToIterate];
				}
		}
	
		 
		 //Building decision tree
		 DecisionTreeNode root4 = new DecisionTreeNode();
		 int testingLabelPossible4 = countDistinct(trainingLabel4);
		 
		 createTree(root4,reducedTrainingData4,trainingLabel4,colsToSelect4,testingLabelPossible4,5);
		
		 //TRAINING DATA 4
		 double[][] testingData4 = new double[1092][112];
		 File testingDataFile4;
		 Scanner testReader4 = null;
		 try {
			 testingDataFile4 = new File("TestData4.txt");
			 testReader4 = new Scanner(testingDataFile4);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<testingData4.length;i++) { 
			 String data = testReader4.nextLine();
			 String[] splited = data.split("\\s+");
			 for(int j = 1;j<testingData4[0].length;j++) {
				 testingData4[i][j-1] = Double.parseDouble(splited[j]); 
			 } 
		 }
		 testingData4 = missingValuePredict(testingData4,5);
		 
		 int[][] catTestingData4 = convertToCategorical(testingData4,7);
		 
		 int[] predictLabel4 = new int[testingData4.length];
		 
		 predictLabel4 = decisionTreePrediction(catTestingData4,root4);
		 System.out.println("TrainLabel4");
		 for(int i=0;i<predictLabel4.length;i++) {
			 System.out.println(predictLabel4[i]);
		 }
		 System.out.println();
		 testReader4.close();
		 labelReader4.close();
	}
	
	public static void classification5() {
		double[][] trainingData5 = new double[1119][11];
		 File trainingDataFile5;
		 Scanner myReader5 = null;
		 try {
			 trainingDataFile5 = new File("TrainData5.txt");
			 myReader5 = new Scanner(trainingDataFile5);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingData5.length;i++) { 
			 String data = myReader5.nextLine();
			 String[] splited = data.split("\\s+");
			 for(int j = 1;j<trainingData5[0].length+1;j++) {
				trainingData5[i][j-1] = Double.parseDouble(splited[j]); 
			 } 
		 }
		 
		 //Predicting missing values
		 trainingData5 = missingValuePredict(trainingData5,5);
		 
		 File trainingLabelFile5;
		 Scanner labelReader5 = null;
		 int[] trainingLabel5 = new int[1119];
		 try {
			 trainingLabelFile5 = new File("TrainLabel5.txt");
			 labelReader5 = new Scanner(trainingLabelFile5);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<trainingLabel5.length;i++) { 
			 String data = labelReader5.nextLine();
			 int parse = Integer.parseInt(data);
			 if(parse == 10)
				 trainingLabel5[i] = parse-3; 
			 else
				 trainingLabel5[i] = parse-2; 
		 } 
		
		 //making data categorical for decision tree
		 int[][] catTrainingData5 =  convertToCategorical(trainingData5,10);
		 
		 //Only 11 features so no feature selection
		 int[] colsToSelect5 = new int[11];
		 for(int i=0;i<11;i++) {
			 colsToSelect5[i] = i;
		 }
		 //Building decision tree
		 DecisionTreeNode root5 = new DecisionTreeNode();
		 int testingLabelPossible5 = countDistinct(trainingLabel5);
		 
		 createTree(root5,catTrainingData5,trainingLabel5,colsToSelect5,testingLabelPossible5,3);
		
		 //TRAINING DATA 5
		 double[][] testingData5 = new double[480][11];
		 File testingDataFile5;
		 Scanner testReader5 = null;
		 try {
			 testingDataFile5 = new File("TestData5.txt");
			 testReader5 = new Scanner(testingDataFile5);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<testingData5.length;i++) { 
			 String data = testReader5.nextLine();
			 String[] splited = data.split("\\s+");
			 for(int j = 1;j<testingData5[0].length;j++) {
				 testingData5[i][j-1] = Double.parseDouble(splited[j]); 
			 } 
		 }
		 testingData5 = missingValuePredict(testingData5,5);
		 
		 int[][] catTestingData5 = convertToCategorical(testingData5,10);
		 
		 int[] predictLabel5 = new int[testingData5.length];
		 
		 predictLabel5 = decisionTreePrediction(catTestingData5,root5);
		 System.out.println("TrainLabel5");
		 for(int i=0;i<predictLabel5.length;i++) {
			 if(trainingLabel5[i] == 7)
				 System.out.println(predictLabel5[i]+3); 
			 else
				 System.out.println(predictLabel5[i]+2); 
		 }
		 System.out.println();
		 testReader5.close();
		 labelReader5.close();
		 
	}
	
	public static void missingV1() {
		double[][] missingData1 = new double[242][14];
		 File missingDataFile1;
		 Scanner missingDataReader1 = null;
		 try {
			 missingDataFile1 = new File("MissingData1.txt");
			 missingDataReader1 = new Scanner(missingDataFile1);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<missingData1.length;i++) { 
			 String data = missingDataReader1.nextLine();
			 String[] splited = data.split("\\s+");
			 for(int j = 0;j<missingData1[0].length;j++) {
				 missingData1[i][j] = Double.parseDouble(splited[j]); 
			 } 
		 }
		 
		 //Predicting missing values
		 missingData1 = missingValuePredict(missingData1,5);
		 System.out.println("Missing Data 1");
		 for(int i=0;i<missingData1.length;i++) {
			 for(int j=0;j<missingData1[0].length;j++) {
				 System.out.print(missingData1[i][j] + " ");
			 }
			 System.out.println();
		 }
		 missingDataReader1.close();
	}
	
	public static void missingV2() {
		double[][] missingData2 = new double[758][50];
		 File missingDataFile2;
		 Scanner missingDataReader2 = null;
		 try {
			 missingDataFile2 = new File("MissingData2.txt");
			 missingDataReader2 = new Scanner(missingDataFile2);
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
		 for(int i=0;i<missingData2.length;i++) { 
			 String data = missingDataReader2.nextLine();
			 String[] splited = data.split("\\s+");
			 for(int j = 0;j<missingData2[0].length;j++) {
				 missingData2[i][j] = Double.parseDouble(splited[j]); 
			 } 
		 }
		 
		 //Predicting missing values
		 missingData2 = missingValuePredict(missingData2,5);
		 System.out.println("Missing Data 2");
		 for(int i=0;i<missingData2.length;i++) {
			 for(int j=0;j<missingData2[0].length;j++) {
				 System.out.print(missingData2[i][j] + " ");
			 }
			 System.out.println();
		 }
		 missingDataReader2.close();
	}
	
	public static void createTree3(DecisionTreeNode currentNode,int[][] trainingData,int[] trainingLabel,int[] colsToSelect,int[] numColPossible,int testingLabelPossible,int maxHeight) {
		if(countDistinct(trainingLabel) == 1) {
			currentNode.setLeafNode(true);
			currentNode.setOutput(trainingLabel[0]);
			return;
		}
		if(countDistinct(trainingLabel) == 0) {
			currentNode.setLeafNode(true);
			currentNode.setOutput(currentNode.getParent().getGuess());
			return;
		}
		if(currentNode.heightFromCurrentNode() == maxHeight) {
			currentNode.setLeafNode(true);
			currentNode.setOutput(guess(trainingLabel));
			return;
		}
		
		if(currentNode.heightFromCurrentNode() < maxHeight) {
			int possible = testingLabelPossible;
			double[] infoGainOfCols = new double[trainingData[0].length];
			
			for(int x=0;x<trainingData[0].length;x++) {
				int k = numColPossible[x];
				double baseEntropy = entropy(trainingLabel,possible);
				int[] column = new int[trainingData.length];
				for(int y=0;y<trainingData.length;y++) {
					column[y] = trainingData[y][x];
				}
				
				int[] numValPerBucket = new int[k];

				for(int y=0;y<column.length;y++) {
					int ind = column[y];
					numValPerBucket[ind]++;
				}

				//Find label array for each bucket range
				int[][] trainingValuesPerBucket = new int[k][];
				int[] numFilledIn = new int[k];
				for(int y=0;y<k;y++) {
					trainingValuesPerBucket[y] = new int[numValPerBucket[y]];
					for(int i=0;i<numValPerBucket[y];i++) {
						trainingValuesPerBucket[y][i]=0;
					}
					numFilledIn[y]=0;
				}
				
				
				for(int y=0;y<column.length;y++) {
						trainingValuesPerBucket[column[y]][numFilledIn[column[y]]] = trainingLabel[y];
						numFilledIn[column[y]]++;
				}
				//Doing entropy for each bucket
				for(int y=0;y<numValPerBucket.length;y++) {
					baseEntropy -= ((double)numValPerBucket[y]/trainingData.length)*entropy(trainingValuesPerBucket[y],possible);
				}
				infoGainOfCols[x] = baseEntropy;
			}
			
			double[] colRankCopy = infoGainOfCols.clone();
			Arrays.sort(infoGainOfCols);
			int colIndex = indexOf(colRankCopy,infoGainOfCols[infoGainOfCols.length-1]);	
			
			int numChildren = numColPossible[colIndex];
			
			int[] mostInformation = new int[trainingData.length];
			for(int i=0;i<trainingData.length;i++) {
				mostInformation[i] = trainingData[i][colIndex];
			}
			
			int colInOriginal = colsToSelect[colIndex];
			currentNode.setColumn(colInOriginal);
			
			int[] newColToSelect = new int[colsToSelect.length-1];
			int[] newNumColPossible = new int[numColPossible.length-1];
			boolean past =false;
			for(int i=0;i<colsToSelect.length;i++) {
				if(colsToSelect[i] == colInOriginal)
					past = true;
				else {
				if(!past) {
					newColToSelect[i] = colsToSelect[i];
					newNumColPossible[i] = numColPossible[i];
				}
				else {
					newColToSelect[i-1] = colsToSelect[i];
					newNumColPossible[i-1] = numColPossible[i];
				}
				}
			}
			
			int[] numValPerBucket = new int[numChildren];
			
			for(int y=0;y<mostInformation.length;y++) {
					numValPerBucket[mostInformation[y]]++;
			}
			currentNode.setGuess(guess(trainingLabel));
			currentNode.setChildNodeLen(numChildren);
			for(int y=0;y<numChildren;y++) {
				DecisionTreeNode childNode= new DecisionTreeNode(y,true);
				currentNode.setChildInd(childNode, y);
				childNode.setParent(currentNode);
			}
			
			for(int y=0;y<numChildren;y++) {
			//Need to split training data and label 5x, 1 for each respective node
			
				DecisionTreeNode newNode = currentNode.getChild(y);
				int[][] newTrainingData = new int[numValPerBucket[y]][trainingData[0].length-1];
				int[] newTrainingLabel = new int[numValPerBucket[y]];
				int nodeRangeValue = currentNode.getChild(y).getValue();
				int newIndCounter = 0;
			
				for(int i=0;i<trainingData.length;i++) {
						if(trainingData[i][colIndex] == nodeRangeValue) {
							boolean colPassed = false;
							for(int j =0;j<trainingData[0].length;j++) {
								if(j == colIndex)
									colPassed = true;
								else if(colPassed) {
									newTrainingData[newIndCounter][j-1] = trainingData[i][j];	
								}
								else {
									newTrainingData[newIndCounter][j] = trainingData[i][j];
								}
								
							}
							newTrainingLabel[newIndCounter] = trainingLabel[i]; 
							newIndCounter++;
						}
				}
				
				createTree3(newNode,newTrainingData,newTrainingLabel,newColToSelect,newNumColPossible,testingLabelPossible,maxHeight);
			}
			
			}
			
	}
	
	
	public static int[] decisionTreePrediction2(int[][] testingData,DecisionTreeNode root) {
		int[] finalLabels = new int[testingData.length];
		for(int i=0;i<testingData.length;i++) {
			DecisionTreeNode temp = root;
			while (temp.getLeafNode() != true) {
				int valAtCol = testingData[i][temp.getColumn()];
				temp = temp.getChild(valAtCol);
			}
			finalLabels[i] = temp.getOutput();
		
		}
		return finalLabels;
	}
	
	public static int[] decisionTreePrediction(int[][] testingData,DecisionTreeNode root) {
		int[] finalLabels = new int[testingData.length];
		for(int i=0;i<testingData.length;i++) {
			DecisionTreeNode temp = root;
			while (temp.getLeafNode() != true) {
				int valAtCol = testingData[i][temp.getColumn()];
				temp = temp.getChild(valAtCol);
			}
			finalLabels[i] = temp.getOutput();
		
		}
		return finalLabels;
	}
	
	public static void createTree(DecisionTreeNode currentNode,int[][] trainingData,int[] trainingLabel,int[] colsToSelect,int testingLabelPossible,int maxHeight) {
		if(countDistinct(trainingLabel) == 1) {
			currentNode.setLeafNode(true);
			currentNode.setOutput(trainingLabel[0]);
			return;
		}
		if(countDistinct(trainingLabel) == 0) {
			currentNode.setLeafNode(true);
			currentNode.setOutput(currentNode.getParent().getGuess());
			return;
		}
		if(currentNode.heightFromCurrentNode() == maxHeight) {
			currentNode.setLeafNode(true);
			currentNode.setOutput(guess(trainingLabel));
			return;
		}
		
		if(currentNode.heightFromCurrentNode() < maxHeight) {
			int k = DecisionTreeNode.maxNrOfChildren;
			int possible = testingLabelPossible;
			double[] infoGainOfCols = new double[trainingData[0].length];
			
			for(int x=0;x<trainingData[0].length;x++) {
				double baseEntropy = entropy(trainingLabel,possible);
				int[] column = new int[trainingData.length];
				for(int y=0;y<trainingData.length;y++) {
					column[y] = trainingData[y][x];
				}
				
				int[] numValPerBucket = new int[k];

				for(int y=0;y<column.length;y++) {
					int ind = column[y];
					numValPerBucket[ind]++;
				}

				//Find label array for each bucket range
				int[][] trainingValuesPerBucket = new int[k][];
				int[] numFilledIn = new int[k];
				for(int y=0;y<k;y++) {
					trainingValuesPerBucket[y] = new int[numValPerBucket[y]];
					for(int i=0;i<numValPerBucket[y];i++) {
						trainingValuesPerBucket[y][i]=0;
					}
					numFilledIn[y]=0;
				}
				
				
				for(int y=0;y<column.length;y++) {
						trainingValuesPerBucket[column[y]][numFilledIn[column[y]]] = trainingLabel[y];
						numFilledIn[column[y]]++;
				}
				//Doing entropy for each bucket
				for(int y=0;y<numValPerBucket.length;y++) {
					baseEntropy -= ((double)numValPerBucket[y]/trainingData.length)*entropy(trainingValuesPerBucket[y],possible);
				}
				infoGainOfCols[x] = baseEntropy;
			}
			
			double[] colRankCopy = infoGainOfCols.clone();
			Arrays.sort(infoGainOfCols);
			int colIndex = indexOf(colRankCopy,infoGainOfCols[infoGainOfCols.length-1]);	
			
			int numChildren = k;
			
			int[] mostInformation = new int[trainingData.length];
			for(int i=0;i<trainingData.length;i++) {
				mostInformation[i] = trainingData[i][colIndex];
			}
			
			int colInOriginal = colsToSelect[colIndex];
			currentNode.setColumn(colInOriginal);
			
			int[] newColToSelect = new int[colsToSelect.length-1];
			boolean past =false;
			for(int i=0;i<colsToSelect.length;i++) {
				if(colsToSelect[i] == colInOriginal)
					past = true;
				else {
				if(!past) {
					newColToSelect[i] = colsToSelect[i];
				}
				else {
					newColToSelect[i-1] = colsToSelect[i];
				}
				}
			}
			
			int[] numValPerBucket = new int[numChildren];
			
			for(int y=0;y<mostInformation.length;y++) {
					numValPerBucket[mostInformation[y]]++;
			}
			currentNode.setGuess(guess(trainingLabel));
			
			for(int y=0;y<numChildren;y++) {
				DecisionTreeNode childNode= new DecisionTreeNode(y);
				currentNode.setChildInd(childNode, y);
				childNode.setParent(currentNode);
			}
			
			for(int y=0;y<numChildren;y++) {
			//Need to split training data and label 5x, 1 for each respective node
			
				DecisionTreeNode newNode = currentNode.getChild(y);
				int[][] newTrainingData = new int[numValPerBucket[y]][trainingData[0].length-1];
				int[] newTrainingLabel = new int[numValPerBucket[y]];
				int nodeRangeValue = currentNode.getChild(y).getValue();
				int newIndCounter = 0;
			
				for(int i=0;i<trainingData.length;i++) {
						if(trainingData[i][colIndex] == nodeRangeValue) {
							boolean colPassed = false;
							for(int j =0;j<trainingData[0].length;j++) {
								if(j == colIndex)
									colPassed = true;
								else if(colPassed) {
									newTrainingData[newIndCounter][j-1] = trainingData[i][j];	
								}
								else {
									newTrainingData[newIndCounter][j] = trainingData[i][j];
								}
								
							}
							newTrainingLabel[newIndCounter] = trainingLabel[i]; 
							newIndCounter++;
						}
				}
				
				createTree(newNode,newTrainingData,newTrainingLabel,newColToSelect,testingLabelPossible,maxHeight);
			}
			
			}
			
	}
	
	
	
	
		

	
	
	//Returns list of indexes of which columns to select from original data
	public static int[] featureSelection(int[][] trainingData,int[] trainingLabel,int k,int numFeat){
		
		double[] columnRanks = new double[trainingData[0].length];
		for(int x=0;x<trainingData[0].length;x++) {
			int numDistinct = countDistinct(trainingLabel);
			double baseEntropy = entropy(trainingLabel,numDistinct);
			int[] column = new int[trainingData.length];
			for(int y=0;y<trainingData.length;y++) {
				column[y] = trainingData[y][x];
			}

			//Splitting into buckets
			int[] numValPerBucket = new int[numFeat];

			for(int y=0;y<column.length;y++) {
				int ind = column[y];
				numValPerBucket[ind]++;
			}

			//Find label array for each bucket range
			int[][] trainingValuesPerBucket = new int[numFeat][];
			int[] numFilledIn = new int[numFeat];
			for(int y=0;y<numFeat;y++) {
				trainingValuesPerBucket[y] = new int[numValPerBucket[y]];
				for(int i=0;i<numValPerBucket[y];i++) {
					trainingValuesPerBucket[y][i]=0;
				}
				numFilledIn[y]=0;
			}
			
			
			
			for(int y=0;y<column.length;y++) {
				if((int)column[y] == numFeat) {
					trainingValuesPerBucket[column[y]-1][numFilledIn[column[y]-1]] = trainingLabel[y];
					numFilledIn[column[y]-1]++;
				}
				else {
					trainingValuesPerBucket[column[y]][numFilledIn[column[y]]] = trainingLabel[y];
					numFilledIn[column[y]]++;
				}

			}
			//Doing entropy for each bucket
			for(int y=0;y<numValPerBucket.length;y++) {
				baseEntropy -= ((double)numValPerBucket[y]/trainingData.length)*entropy(trainingValuesPerBucket[y],numDistinct);
			}
			columnRanks[x] = baseEntropy;
		}
		double[] colRankCopy = columnRanks.clone();
		Arrays.sort(columnRanks);
		int[] colIndex = new int[k];

		for(int x = 0;x<k;x++) {
			colIndex[x] = indexOf(colRankCopy,columnRanks[columnRanks.length-1-x]);
		}
		
		return colIndex;
	}
	
	public static double entropy(int[] feature,int numPossible) {
		int numValues = feature.length;
		int[] numCount = new int[numPossible];
		
		for(int i=0;i<numValues;i++) {
			numCount[feature[i]-1]++;
		}
	
		double numEntropy = 0.0;
		double insideLog = 0.0;
		for(int i=0;i<numPossible;i++) {
			insideLog = (double)numCount[i]/numValues;
			if(insideLog != 0 && !(Double.isNaN(insideLog))) {
				numEntropy += (insideLog)*log2(insideLog);
			}
		}
		
		return -numEntropy;
	}
	
	public static double log2(double N) 
    { 
        double result = Math.log(N) / Math.log(2); 
        return result; 
    } 
	
	public static int guess(int[] trainingLabel) {
		 	int[] tLabelCopy = trainingLabel.clone();
			Arrays.sort(tLabelCopy); 
		    int n= tLabelCopy.length;
		    int countofmax = 1;
		    int temp = tLabelCopy[0]; 
		    int count = 1; 
		    for (int i = 1; i < tLabelCopy.length; i++) 
		    { 
		      if (tLabelCopy[i] == tLabelCopy[i - 1]) 
		        count++; 
		      else
		      { 
		        if (count > countofmax) 
		        { 
		          countofmax = count; 
		          temp = tLabelCopy[i - 1]; 
		        } 
		        count = 1; 
		      } 
		    } 
		    if (count > countofmax) 
		    { 
		      countofmax = count; 
		      temp = tLabelCopy[n - 1]; 
		    }
		    return temp;
	}
	
	public static int range(int[] arr) {
		int min = arr[0];
		int max = arr[0];
		for(int i=1;i<arr.length;i++) {
			if(arr[i] > max)
				max = arr[i];
			if(arr[i] < min)
				min = arr[i];
		}
		return max-min;
	}
	
	public static int min(int[] arr) {
		int min = arr[0];
		for(int i=1;i<arr.length;i++) {
			if(arr[i] < min)
				min = arr[i];
		}
		return min;
	}
	
	public static int maxIndex(int[] arr) {
		int max = 0;
		for(int i=1;i<arr.length;i++) {
			if(arr[i] > arr[max])
				max = i;
		}
		return max;
	}
	
	public static double range(double[] arr) {
		double min = arr[0];
		double max = arr[0];
		for(int i=1;i<arr.length;i++) {
			if(arr[i] > max)
				max = arr[i];
			if(arr[i] < min)
				min = arr[i];
		}
		return max-min;
	}
	
	public static double min(double[] arr) {
		double min = arr[0];
		for(int i=1;i<arr.length;i++) {
			if(arr[i] < min)
				min = arr[i];
		}
		return min;
	}
	
	public static int[][] categorify(double[][] trainingData){
		int[][] newData = new int[trainingData.length][trainingData[0].length];
		for(int i =0;i<newData.length;i++) {
			for(int j=0;j<newData[0].length;j++) {
				newData[i][j] = (int)trainingData[i][j];
			}
		}
		return newData;
	}
	
	public static int countDistinct(int arr[]) 
	{ 
		HashSet<Integer> distinct = new HashSet<Integer>();
		
	    for(int i = 0;i<arr.length;i++) {
	    	if(!(distinct.contains(arr[i])))
	    		distinct.add(arr[i]);
	    }
	    return distinct.size();
	} 
	
	public static int[][] convertToCategorical(double[][] trainingData,int k){
		int[][] newTrainingData = new int[trainingData.length][trainingData[0].length];
		for(int i = 0;i<trainingData[0].length;i++) {
			double[] column = new double[trainingData.length];
			for(int j=0;j<trainingData.length;j++) {
				column[j] = trainingData[j][i];
			}
			double range = range(column);
			double min = min(column);
			//Splitting into buckets
			int high = k;
			
			//Normalizing continuous variable into buckets
			for(int y=0;y<column.length;y++) {
				double normalized = ((column[y] - min)/range) *high;
				if(normalized != high)
					newTrainingData[y][i] = (int)normalized;
				else
					newTrainingData[y][i] = (int)normalized - 1;
			}
		}
		return newTrainingData;
	}
	public static double[][] missingValuePredict(double[][] trainingData,int k){
		for(int i=0;i<trainingData.length;i++) {
	        for(int j=0;j < trainingData[0].length;j++) {
	        	if(trainingData[i][j] == 1.0E99) {
	        		trainingData[i][j] = predict(trainingData,i,j,k);
	        	}
	        }
	 }
		
		return trainingData;
	}
	
	public static double[][] missingValuePredict2(double[][] trainingData,int k){
		for(int i=0;i<trainingData.length;i++) {
	        for(int j=0;j < trainingData[0].length;j++) {
	        	if(trainingData[i][j] == 1.0E9) {
	        		trainingData[i][j] = predict2(trainingData,i,j,k);
	        	}
	        }
	 }
		
		return trainingData;
	}
	
	public static double predict(double[][] trainingData,int i,int j,int k){
		double[] thisSampleVals = trainingData[i];
		double[] scoreNeighbours = new double[trainingData.length];
		
		double[] averageForCol = new double[3312];
		for(int x=0;x<trainingData[0].length;x++) {
			double sum = 0.0;
			int numElem = 0;
			for(int y=0;y<trainingData.length;y++) {
				if(!(trainingData[y][x] == 1.0E99)) {
					sum += trainingData[y][x];
					numElem++;
				}
			}
			averageForCol[x] = sum/numElem;
		}
		
		
		for(int x=0;x<trainingData.length;x++) {
			double sum = 0.0;
			if(x == i) {
				scoreNeighbours[x] = 1.0E99; //Trying to predict it	
				continue;
			}
			for(int y=0;y<trainingData[0].length;y++) {
				if(y != j && !(trainingData[x][y] == 1.0E99) && !(thisSampleVals[y] == 1.0E99)) {
					sum += Math.abs(trainingData[x][y]-thisSampleVals[y]);
				}
				else if(trainingData[x][y] == 1.0E99 && !(thisSampleVals[y] == 1.0E99)) {
					sum += Math.abs(averageForCol[y]-thisSampleVals[y]);
				}
				else if(!(trainingData[x][y] == 1.0E99) && thisSampleVals[y] == 1.0E99) {
					sum += Math.abs(trainingData[x][y]-averageForCol[y]);
				}
				else if((trainingData[x][y] == 1.0E99) && thisSampleVals[y] == 1.0E99){
					sum += Math.abs(trainingData[x][y]-averageForCol[y]);
				}
			}
			scoreNeighbours[x] = sum;
		}
		double[] scoreNeighCopy = scoreNeighbours.clone();
		
		
		Arrays.sort(scoreNeighbours);
		
		
		double[] kNeigh = new double[k];
		int neighbourIndex[] = new int[k];
		double sum = 0.0;
		for(int x = 0;x<k;x++) {
			kNeigh[x] = scoreNeighbours[x];
			neighbourIndex[x] = indexOf(scoreNeighCopy,scoreNeighbours[x]);
			sum += (1/scoreNeighbours[x]);
		}
		double prediction = 0.0;
		
		for(int x = 0;x<k;x++) {
			prediction += trainingData[neighbourIndex[x]][j]*((1/scoreNeighbours[x])/sum);
		}
		
		return prediction;
	}
	
	public static double predict2(double[][] trainingData,int i,int j,int k){
		double[] thisSampleVals = trainingData[i];
		double[] scoreNeighbours = new double[trainingData.length];
		
		double[] averageForCol = new double[13];
		for(int x=0;x<trainingData[0].length;x++) {
			double sum = 0.0;
			int numElem = 0;
			for(int y=0;y<trainingData.length;y++) {
				if(!(trainingData[y][x] == 1.0E9)) {
					sum += trainingData[y][x];
					numElem++;
				}
			}
			averageForCol[x] = sum/numElem;
		}
		
		
		for(int x=0;x<trainingData.length;x++) {
			double sum = 0.0;
			if(x == i) {
				scoreNeighbours[x] = 1.0E9; //Trying to predict it	
				continue;
			}
			for(int y=0;y<trainingData[0].length;y++) {
				if(y != j && !(trainingData[x][y] == 1.0E9) && !(thisSampleVals[y] == 1.0E9)) {
					sum += Math.abs(trainingData[x][y]-thisSampleVals[y]);
				}
				else if(trainingData[x][y] == 1.0E9 && !(thisSampleVals[y] == 1.0E9)) {
					sum += Math.abs(averageForCol[y]-thisSampleVals[y]);
				}
				else if(!(trainingData[x][y] == 1.0E9) && thisSampleVals[y] == 1.0E9) {
					sum += Math.abs(trainingData[x][y]-averageForCol[y]);
				}
				else if((trainingData[x][y] == 1.0E9) && thisSampleVals[y] == 1.0E9){
					sum += Math.abs(trainingData[x][y]-averageForCol[y]);
				}
			}
			scoreNeighbours[x] = sum;
		}
		double[] scoreNeighCopy = scoreNeighbours.clone();
		
		
		Arrays.sort(scoreNeighbours);
		
		
		double[] kNeigh = new double[k];
		int neighbourIndex[] = new int[k];
		double sum = 0.0;
		for(int x = 0;x<k;x++) {
			kNeigh[x] = scoreNeighbours[x];
			neighbourIndex[x] = indexOf(scoreNeighCopy,scoreNeighbours[x]);
			sum += (1/scoreNeighbours[x]);
		}
		double prediction = 0.0;
		
		for(int x = 0;x<k;x++) {
			prediction += trainingData[neighbourIndex[x]][j]*((1/scoreNeighbours[x])/sum);
		}
		
		return prediction;
	}
	
	public static int indexOf(double[] a, double target)
	{
		for (int i = 0; i < a.length; i++)
			if (a[i] == target)
				return i;
		return -1;
	}
	
	public static int indexOf(int[] a, int target)
	{
		for (int i = 0; i < a.length; i++)
			if (a[i] == target)
				return i;
		return -1;
	}
	
	public static int[] RandomizeArray(int[] array){
		Random rgen = new Random();  // Random number generator			
 
		for (int i=0; i<array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
 
		return array;
	}

}
