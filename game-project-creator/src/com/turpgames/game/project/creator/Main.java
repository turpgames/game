package com.turpgames.game.project.creator;

public class Main {
	public static void main(String[] args) {
		try {
			ProjectCreator creator = new ProjectCreator();
			
			creator.setBaseDir("/Users/mehmet/Documents/code/github/turpgames");
			creator.setProjectName("testgame"); // doubleup -> com.turpgames.doubleup
			creator.setAppName("Test Game"); // Double Up
			creator.setAppId("TestGame"); // DoubleUp
			
			creator.create();
			
			System.out.println("OK");
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
