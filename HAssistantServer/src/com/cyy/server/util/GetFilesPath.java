package com.cyy.server.util;

import java.io.File;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GetFilesPath {
	
	public static JsonArray getPath(String path) {

		File file = new File(path);
		File[] files = file.listFiles();

		JsonArray jsa = new JsonArray();
		for (File oneFile : files) {

			JsonObject jo = new JsonObject();
			jo.addProperty("picUrl", oneFile.getParentFile().getName() + "/"
					+ oneFile.getName());
			jsa.add(jo);
		}


		return jsa;
	}
}
