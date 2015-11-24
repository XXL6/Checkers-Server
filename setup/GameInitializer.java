package setup;

import java.awt.EventQueue;

import chatHandler.ChatManager;
import game.Game;
import game.GeneralButtonListener;
import lobby.Lobby;
import lobby.LobbyInterface;
import loginHandler.Login;
import serverCommunication.ServerInterface;
import userInterface.GameWindow;
import userInterface.LoginWindow;

public class GameInitializer extends Thread {

	private ServerInterface serverInterface;
	private GameWindow gameWindow;
	private Game game;
	private GeneralButtonListener listener;
	private String clientUsername;
	ChatManager chatManager;
	private int tableID;
	
	public GameInitializer(ServerInterface serverInterface, int tableID, ChatManager chatManager, String username) {
		this.serverInterface = serverInterface;
		this.tableID = tableID;
		this.chatManager = chatManager;
		this.clientUsername = username;
		
	}
	
	public void run() {
		gameWindow = new GameWindow();
		game = new Game(serverInterface, chatManager, gameWindow, clientUsername);
		listener = new GeneralButtonListener(game);
		gameWindow.setButtonListeners(listener);
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				gameWindow.display(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});
		synchronized(this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stopGame();
		
	}//end startLogin
	
	public void stopGame() {
		gameWindow.display(false);
		gameWindow = null;
		listener = null;
		game = null;
	}
}
