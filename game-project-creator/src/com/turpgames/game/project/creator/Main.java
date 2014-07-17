package com.turpgames.game.project.creator;

public class Main {
	public static void main(String[] args) {
		try {
			ProjectCreator creator = new ProjectCreator();
			
			 // path, in which this 'game' project exists. new project will be created next to 'game' project
			 creator.setBaseDir("/Users/mehmet/Documents/code/github/turpgames");
			
			// will be used in package names & project folder names
			creator.setProjectName("testgame"); // doubleup -> doubleup-android | com.turpgames.doubleup
			
			// Name of the game that appears on shortcuts
			creator.setAppName("Test Game"); // Double Up
			
			// GameId used in serverside
			creator.setAppId("TestGame"); // DoubleUp
			
			creator.create();
			
			System.out.println("OK");
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
