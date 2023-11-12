import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {
	public static final String fullColumnMessage = "This column is full!";
	public static final String invalidMoveMessage = "Invalid move!";

	
	static GameState gameState;
	TypeOfGame typeOfGame;
	List<List<Token>> board;
	int height;
	int width;
	int lastColumnPlayed;
	int lastRowPlayed;
	int timesPlayed;
	
	public Linea(int width, int height, char variantOfTriumph) {
		this.gameState = new RedTurn();
		this.height = height;
		this.width = width;
		this.board = createBoard(width);
		this.typeOfGame = TypeOfGame.typeOfGameFor(variantOfTriumph);
		this.timesPlayed = 0;

	}	

	public boolean redsTurn() {
		return this.gameState instanceof RedTurn;
	}

	public boolean bluesTurn() {
		return this.gameState instanceof BlueTurn;
	}

	public void playRedAt(int columnToPlay) {
		gameState.playRedAt(columnToPlay,this);
		
		timesPlayed += 1;
		
		gameState.changeTurn();
	
	}

	public void playBlueAt(int columnToPlay) {
		gameState.playBlueAt(columnToPlay,this);
	
		timesPlayed += 1;
		
		gameState.changeTurn();
	}
	
	void placeRedToken(int columnToPlay) {
		isItAValidPlay(columnToPlay);
		List<Token> column = board.get(columnToPlay - 1);
		int columnSize = column.size();
		board.get(columnToPlay - 1).add(new RedToken(columnToPlay,columnSize + 1,this));
		lastColumnPlayed = columnToPlay;
		lastRowPlayed = board.get(columnToPlay - 1).size();
		updateGame();
	}
	
	void placeBlueToken(int columnToPlay) {
		isItAValidPlay(columnToPlay);
		List<Token> column = board.get(columnToPlay - 1);
		int columnSize = column.size();
		board.get(columnToPlay - 1).add(new BlueToken(columnToPlay,columnSize + 1,this));
		lastColumnPlayed = columnToPlay;
		lastRowPlayed = board.get(columnToPlay - 1).size();
		updateGame();
		
	}
	
	public boolean verticalWin() {
		Token lastToken = getLastToken();
		return lastToken.countInDirection(new Down()) >= 4;
	}
	
	public boolean horizontalWin() {
		Token lastToken = getLastToken();
		return (lastToken.countInDirection(new Right()) + lastToken.countInDirection(new Left()) - 1) >= 4;
	}
	
	public boolean diagonalWin() {
		Token lastToken = getLastToken();
		int firstDiagonal = (lastToken.countInDirection(new UpAndRight()) 
				+ lastToken.countInDirection(new LeftAndDown())) - 1;
		int secondDiagonal = (lastToken.countInDirection(new LeftAndUp()) 
				+ lastToken.countInDirection(new DownAndRight())) - 1;
		
		return firstDiagonal >= 4 || secondDiagonal >= 4;
	}

	 private void updateGame() {
		 if (typeOfGame.isThereAnyWin(this)) {
				gameState = gameState.winner();
				gameState.whosTheWinner();
			}
			else if(isBoardFull()) {
				gameState = gameState.tied();
				gameState.whosTheWinner();
			}	
	}
	
	private void isItAValidPlay(int columnToPlay) {
		if (!(columnToPlay >= 1 && columnToPlay <=width)) {
			throw new RuntimeException(invalidMoveMessage);
		}
		List<Token> column = board.get(columnToPlay - 1);
		if (column.size() >= height) {
			throw new RuntimeException(fullColumnMessage);
		}
		
	}
	
	public boolean isThereARedToken(int column, int row) {
		return board.get(column - 1).get(row - 1).getValue() == 'R';
	}

	public boolean isThereABlueToken(int column, int row) {
		return board.get(column - 1).get(row - 1).getValue() == 'B';
	}
	
	private List<List<Token>> createBoard(int width) {
        return IntStream.range(0, width)
                .mapToObj(i -> new ArrayList<Token>())
                .collect(Collectors.toList());
    }
	
	public String show() {
	    StringBuilder boardString = new StringBuilder();

	    for (int row = height - 1; row >= 0; row--) {
	        for (int column = 0; column <= width - 1; column++) {
	            char cellValue = '.';

	            if (board.get(column).size() > row) {
	                cellValue = board.get(column).get(row).getValue();
	            }

	            boardString.append(cellValue).append(" ");
	        }
	        boardString.append("\n");
	    }

	    return boardString.toString();
	}

	public Token provideToken(int column, int row, Direction direction) {
		int columnToSearch = column + direction.dx;
		int rowToSearch = row + direction.dy;
		
		if (!(columnToSearch >=1 && columnToSearch <= width)) {
			return new GhostToken(columnToSearch,rowToSearch,this);
		}
		
		if (!(rowToSearch >=1 && rowToSearch <= board.get(columnToSearch - 1).size())) {
			return new GhostToken(columnToSearch,rowToSearch,this);
		}
		
		return board.get(columnToSearch - 1).get(rowToSearch - 1);
	}

	public boolean finished() {
		if(timesPlayed == 0 ) {
			return false;
		}
		Token lastToken = getLastToken();
		return typeOfGame.isThereAnyWin(this) || isBoardFull();
	}
	
	public boolean isBoardFull() {
        return board.stream().allMatch(column -> column.size() >= height);
    }
	
	public static void setGameState(GameState newGameState) {
		gameState = newGameState;
	}
	
	private Token getLastToken() {
		return board.get(lastColumnPlayed - 1).get(lastRowPlayed - 1);
	}

	

	

	


	

	
}
