package lobby;

import java.util.ArrayList;

import serverCommunication.ServerInterface;
import userInterface.LobbyWindow;

public class TableManager extends Thread{

	LobbyWindow lobbyUI;
	ServerInterface serverInterface;
	TableRefresher tableRefresher;
	GameTable table;
	ArrayList<GameTable> tableBuffer = new ArrayList<GameTable>();
//	ArrayList<Integer> localTableList = new ArrayList<Integer>();
//	ArrayList<Thread> refresherList = new ArrayList<Thread>();
	
	public TableManager(LobbyWindow lobbyUI, ServerInterface serverInterface) {
		this.lobbyUI = lobbyUI;
		this.serverInterface = serverInterface;
	}
	
	public void insertTable(int tableID) {
		
	}
	
	public void refreshTables(int[] tableID) {
		tableRefresher = new TableRefresher(tableID, lobbyUI, serverInterface, this);
		tableRefresher.start();
		//tableRefresher.run();
	}
	
	public void updateNextTable(int tableID, String black, String red) {
		if (tableRefresher.isAlive()) {
			synchronized(tableRefresher) {
				table = new GameTable(tableID);
				table.setTableInfo(tableID, black, red);
				tableRefresher.notify();
			}
		} else {
			table = new GameTable(tableID);
			table.setTableInfo(tableID, black, red);
			insertTable(table);
		}
	}
	
//	public void refreshTables(int[] tableID) {
//		for (int i : tableID) {
//			GameTable table = new GameTable(i);
//			SingleTableRefresher refresher = new SingleTableRefresher(table, this);
//			refresherList.add(refresher);
//			refresher.start();
//			serverInterface.getTblStatus("badboy", i);
//		}
//	}
//	
//	public void updateNextTable(int tableID, String black, String red) {
//			table = new GameTable(tableID);
//			table.setTableInfo(tableID, black, red);
//			for (Thread t: refresherList) {
//				t.notify();
//			}
//
//	}
	
	public GameTable getTable() {
		return table;
	}
	
	public void removeTable(int tableID) {
		lobbyUI.removeTable(tableID);
	}
	
	public void insertTable(GameTable table) {
		lobbyUI.insertTable(table);
	}
}
