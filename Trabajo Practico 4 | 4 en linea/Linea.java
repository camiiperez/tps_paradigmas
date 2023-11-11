import java.util.ArrayList;
import java.util.List;

public class Linea {
	public static final String isTheWinner = " is the winner!";
	public static final String fullColumnMessage = "This column is full!";
	public static final String invalidMoveMessage = "Invalid move!";
	public static final String noWinnerYetMessage = "There's no winner yet!";
	public static final String tieMessage = "Tie!";
	
	static Turn turn;
	TypeOfGame typeOfGame;
	List<List<Token>> board;
	int height;
	int width;
	int lastColumnPlayed;
	int lastRowPlayed;
	int timesPlayed;
	
	public Linea(int width, int height, char variantOfTriumph) {
		this.turn = new Red();
		this.height = height;
		this.width = width;
		this.board = createBoard(width);
		this.typeOfGame = TypeOfGame.typeOfGameFor(variantOfTriumph);
		this.timesPlayed = 0;

	}
	
	public String winner() {
		if (typeOfGame.isThereAnyWin(this)) {
			return getLastToken().getValue() + isTheWinner;
		}
		else if(isBoardFull()) {
			return tieMessage;
		}
		return noWinnerYetMessage;
	}
	

	public boolean redsTurn() {
		return this.turn instanceof Red;
	}

	public boolean bluesTurn() {
		return this.turn instanceof Blue;
	}

	public void playRedAt(int columnToPlay) {
		isItAValidPlay(columnToPlay);
		turn.shouldRedPlay();
		
		placeRedToken(columnToPlay);
		
		lastColumnPlayed = columnToPlay;
		lastRowPlayed = board.get(columnToPlay - 1).size();
		timesPlayed += 1;
		
		turn.changeTurn();
	
	}

	public void playBlueAt(int columnToPlay) {
		isItAValidPlay(columnToPlay);
		turn.shouldBluePlay();
		
		placeBlueToken(columnToPlay);
		
		lastColumnPlayed = columnToPlay;
		lastRowPlayed = board.get(columnToPlay - 1).size();
		timesPlayed += 1;
		
		turn.changeTurn();
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

	

	private void placeRedToken(int columnToPlay) {
		List<Token> column = board.get(columnToPlay - 1);
		int columnSize = column.size();
		board.get(columnToPlay - 1).add(new RedToken(columnToPlay,columnSize + 1,this));
	}
	
	private void placeBlueToken(int columnToPlay) {
		List<Token> column = board.get(columnToPlay - 1);
		int columnSize = column.size();
		board.get(columnToPlay - 1).add(new BlueToken(columnToPlay,columnSize + 1,this));
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
	    List<List<Token>> board = new ArrayList<>();
	
	    for (int i = 0; i < width; i++) {
	        List<Token> row = new ArrayList<>();
	        board.add(row);
	    }
	    
	    return board;
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
	    for (List<Token> column : board) {
	        if (column.size() < height) {
	            return false; 
	        }
	    }
	    return true;
	}
	
	public static void setTurn(Turn turnToChange) {
		turn = turnToChange;
	}
	
	private Token getLastToken() {
		return board.get(lastColumnPlayed - 1).get(lastRowPlayed - 1);
	}

	

	


	

	
}
