/*
MorraApp.java
@MRoscio
21 3 2020
*/

import javax.swing.JOptionPane;
import java.util.Random;

public class MorraApp{

	public static void main(String args[]){

		//Var
		String playerOddEven=new String();		//Player choice of odd or even
		String cpuOddEven=new String();		//CPU choice of odd or even
		String fingerSumOddEven=new String();		//Is the total finger a odd or even number
		String roundWinner=new String();	//Name of the round winner
		String gameWinner=new String();		//Name of the game winner
		String htmlHeader="<html><body><table \"style=width:100%; border: 1px solid black;\">";		//JOptionPane message are formated with HTML, this lines are the common elements to all messages
		String htmlFirstRowRound="<tr><th>Round</th><th>Player fingers</th><th>CPU fingers</th></tr>";
		String htmlFirstRowSummary="<tr><th>Game</th><th>Player Win/Lose</th><th>Player Extra points</th><th>CPU extra points</th></tr>";
		String htmlClose="</table></body></html>";
		StringBuffer sBuff=new StringBuffer();		//String buffer used to create html string for customised message
		String roundSummaryContent=new String();		//String that will take the above sBuff and be added in end of ROUND message
		String gameSummaryContent=new String();		//String that will take the above sBuff and be added in end of GAME message
		String winLooseSummary[][]=new String[50][2];		//Win/Loose summary 2dArray
		String closestToSum=new String();		//Name of player closest to sum for extra points rule

		int playerInput=0;		//Number chosen by player
		int cpuInput=0;		//Number chosen by CPU
		int fingerSum=0;		//Total of both number
		int playerTotalScore=0;		//Player total score for the round
		int cpuTotalScore=0;		//CPU total score for the round
		int roundNumber=1;		//Round number
		int gameNbr=0;		//Game number
		int playerHist[]=new int[50];		//Player history of finger player
		int cpuHist[]=new int[50];		//CPU history of finger played
		int playerOddCounter=0;		//Number of times the player played odd
		int playerEvenCounter=0;		//Number of times the player played even
		int cpuOddCounter=0;		//Number of times the CPU played odd
		int cpuEvenCounter=0;		//Number of times the CPU played even
		int playerExtraPointTotal[][]=new int[50][2];		//2d array of total Player extra point, first column will be game number, second will be extra point total
		int cpuExtraPointTotal[][]=new int[50][2];		//2d array of total CPU extra point, first column will be game number, second will be extra point total

		boolean endRound=false;		//Boolean to finish round when limit socre is reached
		boolean playAgain=false;		//Boolean to finish game if player doesn't want to play again

		//objects
		Morra mr=new Morra();		//Declaring and creating object to access instantiable class
		Random rand=new Random();		//Declaring and creating object to access Random class for simulating CPU input

		do{			//Begin game loop
			do{			//Begin round loop
				//input
				cpuInput=rand.nextInt(10);		//Simulating CPU input
				cpuInput++;		//rand.nextInt(10);	will create a number between 0 and 9, so we add +1 to the result to get a number between 1 and 10

				Object[] options={"Evens", "Odds"};				//Creating object for following JOptionPane message. Here we create the text that will show up in the buttons

				int n=JOptionPane.showOptionDialog(null,		//In order to catch user choice we need to store the JOptionPane message in a int variable. We use int because yes=0 and no=1
				"Do you want to play evens or odds?",			//Message
				"Round "+roundNumber,							//Window title
				JOptionPane.YES_NO_OPTION,						//What button will show. The text of the button were created above in Object[] options={"Evens", "Odds"};
				JOptionPane.QUESTION_MESSAGE,					//Type of window. QUESTION_MESSAGE is important as it will expect an answer from the player and enable buttons
				null,     										//Do not use a custom Icon
				options,  										//The titles of buttons
				options[0]); 									//Default button title

				if(n == JOptionPane.YES_OPTION){				//If function looking for user choice. Not that whatever the button text you use, the buttons will alway be called YES_OPTION or NO_OPTION
					playerOddEven="even";						//Set player choice to even
					playerEvenCounter++;						//Counter to track number of times player played even
					cpuOddEven="odd";							//CPU choice
					cpuOddCounter++;							//CPU choice counter
				}
				else{
					playerOddEven="odd";
					playerOddCounter++;
					cpuOddEven="even";
					cpuEvenCounter++;
				}

				playerInput=Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your number between 1 and 10: "));	//Get player input from inputDialog. Parse it from string to int

				mr.setPlayerInput(playerInput);			//Set player input in instantiable class
				mr.setPlayerOddEven(playerOddEven);		//Set player choice in instantiable class
				mr.setCpuInput(cpuInput);				//Set CPU input in instantiable class
				mr.setCpuOddEven(cpuOddEven);			//Set CPU choice in instantiable class
				mr.setGameNbr(gameNbr);					//Set game number in instantiable class

				playerHist[roundNumber-1]=playerInput;	//Keep track of player input through the game. roundNumber starts at 1, so array index must be roundNumber-1 to start at 0
				cpuHist[roundNumber-1]=cpuInput;		//Same for CPU

				JOptionPane.showMessageDialog(null,		//Simple showMessageDialog to recap all the above: Player choice, player input, cpu choice, cpu input
				"You are playing "+playerOddEven+"s"+"\n"+
				"Your number is "+playerInput+"\n"+
				"The computer is playing "+cpuOddEven+"s"+"\n"+
				"The computer number is "+cpuInput);

				//compute
				mr.compute();		//Start compute function in instantiable class

				//output
				playerTotalScore=mr.getPlayerTotalScore();		//Get playerTotalScore value from instantiable class
				cpuTotalScore=mr.getCpuTotalScore();		//Get cpuTotalScore value from instantiable class
				roundWinner=mr.getRoundWinner();		//Get roundWinner value from instantiable class
				fingerSum=mr.getFingerSum();		//Get fingerSum value from instantiable class
				fingerSumOddEven=mr.getFingerSumOddEven();		//Get fingerSumOddEven value from instantiable class
				playerExtraPointTotal=mr.getPlayerExtraPointTotal();		//Get playerExtraPointTotal value from instantiable class
				cpuExtraPointTotal=mr.getCpuExtraPointTotal();		//Get cpuExtraPointTotal value from instantiable class
				closestToSum=mr.getClosestToSum();		//Get closestToSum value from instantiable class

				JOptionPane.showMessageDialog(null,			//showMessageDialog to recap what player and CPU chose and who won the round. Also display closest to fingerSum and the current total score
				"You chose number "+playerInput+" and to play "+playerOddEven+"s"+"\n"+
				"CPU chose number "+cpuInput+" and to play "+cpuOddEven+"s"+"\n"+"\n"+
				"Finger sum is: "+fingerSum+" which is a "+fingerSumOddEven+"\n"+"\n"+
				"Round winner is "+roundWinner+"\n"+
				closestToSum+" get 2 extra points for being closest to the sum"+"\n"+
				"Player total points: "+playerTotalScore+"\n"+
				"CPU total points: "+cpuTotalScore+"\n"+"\n"
				);
				roundNumber++;			//Round is over so we increment roundNumber for the next round
				if(playerTotalScore>12||cpuTotalScore>12){		//Did any player reached to limit score ?
					endRound=true;		//Then we break the loop by changing the boolean to true
				}
			}while(endRound==false);	//End of while loop, monitoring status of endRound

			if(playerTotalScore>cpuTotalScore){		//Does the player have more point than CPU
				gameWinner="Player";				//Then player wins the game
				winLooseSummary[gameNbr][0]="Round "+roundNumber;		//2d String array row=gameNbr and column=0, we add the round number
				winLooseSummary[gameNbr][1]="Won";						//row=gameNbr and column=1, we add win if player won...
			}
			else{
				gameWinner="CPU";
				winLooseSummary[gameNbr][0]="Round "+roundNumber;
				winLooseSummary[gameNbr][1]="Lost";						//...or loose if player lost
			}


			//The following loop create a HTML code depending on the number of rounds, that allow us to have a recap table which size adapt to the number of round played
			//We use the StringBuffer.append to add data until we are done, and then transfer everything to an actual string
			//The HTML elements<tr> takes care of returning to line and creating a nice table
			for(int i=0;i<roundNumber-1;i++){
				int n=i+1;
				sBuff.append("<tr>"+"<th>"+n+"</th>"+"<th>"+playerHist[i]+"</th>"+"<th>"+cpuHist[i]+"</th>"+"</tr>");
			}

			roundSummaryContent=sBuff.toString();		//Move content of sBuff to a String
			sBuff.setLength(0);  //We erase the content of sBuff for future use

			int n=JOptionPane.showConfirmDialog(null,	//Another JOptionPane that needs user to click button, we store in a int
			"The winner is: "+gameWinner+"\n"+
			htmlHeader+					//HTML elements declared in variables
			htmlFirstRowRound+			//First row of the table created especially for the round recap
			roundSummaryContent+		//Summary created above with loop
			htmlClose+					//Close html code
			"\n"+
			"Player score: "+playerTotalScore+"\n"+
			"CPU score: "+cpuTotalScore+"\n"+
			"Do you want to play again?",
			"Play again ?",
			JOptionPane.YES_NO_OPTION
			);
			if(n == JOptionPane.YES_OPTION){	//Playe chose to play another game
				gameNbr++;			//Increment gameNbr as we start a new game
				playAgain=true;		//Set playAgain to true as it start false
				endRound=false;		//Set endRound to false for the round loop
				playerTotalScore=0;	//Reset playerTotalScore to 0
				cpuTotalScore=0;	//Reset cpuTotalScore to 0
				roundNumber=1;		//Reset roundNumber to 1
				playerHist=new int[50];	//Reset round history arrays
				cpuHist=new int[50];
				mr.resetVar();		//Call reset function in instantiable class
			}
			else{
				playAgain=false;	//playAgain=false to break game loop
			}
		}while(playAgain==true);	//End of game loop, monitoring playAgain


		//Another loop creating a customised table, this time for the whole game summary. It used the 2dArryas to keep track of what games got what  values
		for(int i=0;i<gameNbr;i++){
			int n=i+1;
				sBuff.append("<tr>"+"<th>"+n+"</th>"+"<th>"+winLooseSummary[i][1]+"</th>"+"<th>"+playerExtraPointTotal[i][1]+"</th>"+"<th>"+cpuExtraPointTotal[i][1]+"</th>"+"</tr>");
		}

		gameSummaryContent=sBuff.toString();	//Moving String buffer content to String
		sBuff.setLength(0);			//Clearing sBuff because it is cleaner

		JOptionPane.showMessageDialog(null,		//End of game showMessageDialog showing and of game table as well as number of time player and CPU played odds or evens
		"You played "+gameNbr+" games"+"\n"+
		htmlHeader+
		htmlFirstRowSummary+
		gameSummaryContent+
		htmlClose+"\n"+
		"You played "+playerOddCounter+" odds and "+playerEvenCounter+" evens"+"\n"+
		"CPU played "+cpuOddCounter+" odds and "+cpuEvenCounter+" evens"+"\n"+
		"Goodbye");	//Goodbye
	}
}