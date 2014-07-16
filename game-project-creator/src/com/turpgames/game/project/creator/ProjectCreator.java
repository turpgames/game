package com.turpgames.game.project.creator;

import java.io.File;

public class ProjectCreator {

	private String targetDir;

	public void create() throws Exception {
		targetDir = baseDir + "/" + projectName;

		Utils.deleteDir(targetDir);

		Utils.copyDir(f("%s/game", baseDir), targetDir);

		Utils.deleteDir(f("%s/.metadata", targetDir));
		Utils.deleteDir(f("%s/RemoteSystemsTempFiles", targetDir));
		Utils.deleteDir(f("%s/.git", targetDir));
		Utils.deleteDir(f("%s/game-project-creator", targetDir));

		Utils.deleteDir(f("%s/game/bin", targetDir));
		Utils.deleteDir(f("%s/game-android/bin", targetDir));
		Utils.deleteDir(f("%s/game-desktop/bin", targetDir));
		Utils.deleteDir(f("%s/game-ios/bin", targetDir));

		Utils.deleteFile(f("%s/.gitignore", targetDir));
		Utils.deleteFile(f("%s/README.md", targetDir));

		replaceNameInProjectFile("");
		replaceNameInProjectFile("-android");
		replaceNameInProjectFile("-desktop");
		replaceNameInProjectFile("-ios");

		replaceInProjectFile("-desktop",
				f("<location>%s/game/game-android/assets</location>", baseDir),
				f("<location>%s/%s/%s-android/assets</location>", baseDir, projectName, projectName));

		replaceInClasspathFile("-android", "\"/game\"", f("\"/%s\"", projectName));
		replaceInClasspathFile("-desktop", "\"/game\"", f("\"/%s\"", projectName));
		replaceInClasspathFile("-ios", "\"/game\"", f("\"/%s\"", projectName));

		File[] javaFiles = Utils.getFiles(targetDir, ".java");
		for (File javaFile : javaFiles)
			Utils.replaceInFile(javaFile.getPath(), "com.turpgames.game", f("com.turpgames.%s", projectName));

		String gameJava = f("%s/%s/game/src/com/turpgames/game/view/Game.java", baseDir, projectName);
		Utils.replaceInFile(gameJava, "public class Game extends BaseGame", f("public class %sGame extends BaseGame", appId));
		Utils.rename(gameJava, f("%sGame.java", appId));
		
		String gameXmlPath = f("%s/game-android/assets/game.xml", targetDir);
		Utils.replaceInFile(gameXmlPath, "id=\"Game\"", f("id=\"%s\"", appId));
		Utils.replaceInFile(gameXmlPath, "class=\"com.turpgames.game.view.Game\"", f("class=\"com.turpgames.%s.view.%sGame\"", projectName, appId));
		Utils.replaceInFile(gameXmlPath, "com.turpgames.game", f("com.turpgames.%s", projectName));
		Utils.replaceInFile(gameXmlPath, "value=\"game\"", f("value=\"%s\"", projectName));
		Utils.replaceInFile(gameXmlPath, "value=\"Game\"", f("value=\"%s\"", appName));
		Utils.replaceInFile(gameXmlPath, "http://www.turpgames.com/gameredirect.html", f("http://www.turpgames.com/%sredirect.html", projectName));
		Utils.replaceInFile(gameXmlPath, "http://www.turpgames.com/img/game.png", f("http://www.turpgames.com/img/%s.png", projectName));
		Utils.replaceInFile(gameXmlPath, "market://details?id=com.turpgames.game", f("market://details?id=com.turpgames.%s", projectName));
		Utils.replaceInFile(gameXmlPath, "skin id=\"game\"", f("skin id=\"%s\"", projectName));

		String manifestXmlPath = f("%s/game-android/AndroidManifest.xml", targetDir);
		Utils.replaceInFile(manifestXmlPath, "com.turpgames.game", f("com.turpgames.%s", projectName));

		String stringsXmlPath = f("%s/game-android/res/values/strings.xml", targetDir);
		Utils.replaceInFile(stringsXmlPath, ">Game<", f(">%s<", appName));

		String desktopMainPath = f("%s/game-desktop/src/com/turpgames/game/Main.java", targetDir);
		Utils.replaceInFile(desktopMainPath, "cfg.title = \"Game\"", f("cfg.title = \"%s\"", appName));

		String robovmXmlPath = f("%s/game-ios/robovm.xml", targetDir);
		Utils.replaceInFile(robovmXmlPath, "<directory>../game-android/assets</directory>", f("<directory>../%s-android/assets</directory>", projectName));
		Utils.replaceInFile(robovmXmlPath, "com.turpgames.game.view.*", f("com.turpgames.%s.view.*", projectName));

		String robovmPropsPath = f("%s/game-ios/robovm.properties", targetDir);
		Utils.replaceInFile(robovmPropsPath, "com.turpgames.game", f("com.turpgames.%s", projectName));
		Utils.replaceInFile(robovmPropsPath, "app.executable=Game", f("app.executable=%s", appName));
		Utils.replaceInFile(robovmPropsPath, "app.name=Game", f("app.name=%s", appName));

		String robovmInfoPath = f("%s/game-ios/Info.plist.xml", targetDir);
		Utils.replaceInFile(robovmInfoPath, "<string>Game</string>", f("<string>%s</string>", appName));

		Utils.rename(f("%s/game/src/com/turpgames/game", targetDir), projectName);
		Utils.rename(f("%s/game-android/src/com/turpgames/game", targetDir), projectName);
		Utils.rename(f("%s/game-android/gen/com/turpgames/game", targetDir), projectName);
		Utils.rename(f("%s/game-desktop/src/com/turpgames/game", targetDir), projectName);
		Utils.rename(f("%s/game-ios/src/com/turpgames/game", targetDir), projectName);

		Utils.rename(f("%s/game", targetDir), projectName);
		Utils.rename(f("%s/game-android", targetDir), f("%s-android", projectName));
		Utils.rename(f("%s/game-desktop", targetDir), f("%s-desktop", projectName));
		Utils.rename(f("%s/game-ios", targetDir), f("%s-ios", projectName));
	}

	private void replaceNameInProjectFile(String projectType) throws Exception {
		replaceInProjectFile(projectType,
				f("<name>game%s</name>", projectType),
				f("<name>%s%s</name>", projectName, projectType));
	}

	private void replaceInProjectFile(String projectType, String token, String value) throws Exception {
		Utils.replaceInFile(
				f("%s/game%s/.project", targetDir, projectType),
				token, value);
	}

	private void replaceInClasspathFile(String projectType, String token, String value) throws Exception {
		Utils.replaceInFile(
				f("%s/game%s/.classpath", targetDir, projectType),
				token, value);
	}

	private String f(String format, Object... args) {
		return String.format(format, args);
	}

	private String baseDir;
	private String projectName;
	private String appName;
	private String appId;

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
