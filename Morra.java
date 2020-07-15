/*
Morra.java
@MRoscio
21 3 2020
*/

public class Morra{

	//Data members - Basically the same variables as the App class, some will be imported from the App class, others will be sent
	private int playerInput; //Number chosen by player
	private int cpuInput;		//Number chosen by CPU
	private int fingerSum;		//Total of both number
	private int playerTotalScore;		//Player total score for the round
	private int cpuTotalScore;		//CPU total score for the round
	private int gameNbr;		//Game number
	private int playerExtraPointTotal[][];		//2d array of total Player extra point, first column will be game number, second will be extra point total
	private int cpuExtraPointTotal[][];		//2d array of total CPU extra point, first column will be game number, second will be extra point total
	private int playerDiffSum;		//Difference between fingerSum and player input to find out if player get the extra 2 points
	private int cpuDiffSum;		//Difference between fingerSum and player input to find out if player get the extra 2 points

	private String playerOddEven;		//Player choice
	private String cpuOddEven;		//CPU choice
	private String fingerSumOddEven;		//Is fingerSum a odd or even number
	private String roundWinner;			//Name of round winner
	private String closestToSum;		//player closest to sum



	//constructor
	public Morra(){		//Constructor initialising variables of instantiable class
		playerInput=0;
		playerOddEven=new String();
		cpuInput=0;
		cpuOddEven=new String();
		fingerSum=0;
		fingerSumOddEven=new String();
		playerTotalScore=0;
		cpuTotalScore=0;
		roundWinner=new String();
		gameNbr=0;
		playerExtraPointTotal=new int[50][2];
		cpuExtraPointTotal=new int[50][2];
		playerDiffSum=0;
		cpuDiffSum=0;
		closestToSum=new String();
	}

	//Input setters	- Getting data from the App class
	public void setPlayerInput(int playerInput){
		this.playerInput=playerInput;
	}
	public void setPlayerOddEven(String playerOddEven){
		this.playerOddEven=playerOddEven;
	}
	public void setCpuInput(int cpuInput){
		this.cpuInput=cpuInput;
	}
	public void setCpuOddEven(String cpuOddEven){
		this.cpuOddEven=cpuOddEven;
	}
	public void setGameNbr(int gameNbr){
		this.gameNbr=gameNbr;
	}

	//compute
	public void compute(){
		fingerSum=playerInput+cpuInput;		//Calculating finger sum by adding the two inputs together

		if(fingerSum%2==0){		//Calculating if the sum is even or odd using the modulo. If result is 0(dividable by 2), then number is even
			fingerSumOddEven="even";
		}
		else{
			fingerSumOddEven="odd";
		}

		if(playerOddEven.equals("odd") && fingerSumOddEven.equals("odd")){		//Did player chose odd and is finger sum odd
			playerTotalScore=playerTotalScore+3;		//Then player win the round and get +3 points to his total score for the round
			roundWinner="Player";
		}
		else if(playerOddEven.equals("even") && fingerSumOddEven.equals("even")){		//Did player chose even and is finger sum even
			playerTotalScore=playerTotalScore+3;		//Then player win the round and get +3 points to his total score for the round
			roundWinner="Player";
		}
		else{											//If none of the above is true, then CPU win the round and get the +3 points
			cpuTotalScore=cpuTotalScore+3;
			roundWinner="CPU";
		}

		playerDiffSum=fingerSum-playerInput;		//Calculating Player input difference to fingerSum
		cpuDiffSum=fingerSum-cpuInput;		//Calculating CPU input difference to fingerSum

		if(playerDiffSum<cpuDiffSum){		//If Player is closer to fingerSum than CPU
			closestToSum="Player";			//Currently closest to Sum
			playerTotalScore=playerTotalScore+2;	//Add +2 extra point to playerTotalScore
			playerExtraPointTotal[gameNbr][0]=gameNbr+1;	//Adding gameNbr+1 to cel gameNbr,0 of 2d array. Note gameNbr starts at 0
			playerExtraPointTotal[gameNbr][1]=playerExtraPointTotal[gameNbr][1]+2;	//Adding +2 to cel gameNbr,1 of 2d array.
			cpuExtraPointTotal[gameNbr][0]=gameNbr+1;		//Adding gameNbr+1 to cel gameNbr,0 of 2d array. Note gameNbr starts at 0
			cpuExtraPointTotal[gameNbr][1]=cpuExtraPointTotal[gameNbr][1];	//Not adding any point as CPU wasn't the closest
		}else if(playerDiffSum>cpuDiffSum){		//If CPU is the closest
			closestToSum="CPU";			//Currently closest to Sum
			cpuTotalScore=cpuTotalScore+2;	//Add +2 extra point to playerTotalScore
			cpuExtraPointTotal[gameNbr][0]=gameNbr+1;	//Adding gameNbr+1 to cel gameNbr,0 of 2d array. Note gameNbr starts at 0
			cpuExtraPointTotal[gameNbr][1]=cpuExtraPointTotal[gameNbr][1]+2;	//Adding +2 to cel gameNbr,1 of 2d array.
			playerExtraPointTotal[gameNbr][0]=gameNbr+1;		//Adding gameNbr+1 to cel gameNbr,0 of 2d array. Note gameNbr starts at 0
			playerExtraPointTotal[gameNbr][1]=playerExtraPointTotal[gameNbr][1];	//Not adding any point as Player wasn't the closest
		}
	}
	public void resetVar(){		//Simple function to reset local variables if user decide to play another game
		playerTotalScore=0;
		cpuTotalScore=0;
	}


	//getter - Used to move data from instantiable class to App class
	public int getPlayerTotalScore(){
		return playerTotalScore;
	}
	public int getCpuTotalScore(){
		return cpuTotalScore;
	}
	public String getRoundWinner(){
		return roundWinner;
	}
	public int getFingerSum(){
		return fingerSum;
	}
	public String getFingerSumOddEven(){
		return fingerSumOddEven;
	}
	public int[][] getPlayerExtraPointTotal(){
		return playerExtraPointTotal;
	}
	public int[][] getCpuExtraPointTotal(){
		return cpuExtraPointTotal;
	}
	public String getClosestToSum(){
		return closestToSum;
	}
}