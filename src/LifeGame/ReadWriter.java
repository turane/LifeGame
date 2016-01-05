package LifeGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReadWriter {
	/*
	 * user 定義データの保存読み込みを行う
	 */

	/*
	 * 保存
	 */
	public static void write(Map<String, int[][]> map) {
		try {
			ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir")+"/userDef.lg"));

			for(String name:map.keySet()){
				os.writeUTF(name);
				os.writeObject(map.get(name));
			}
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 読み込み
	 */
	public static Map<String, int[][]> read(){
		Map<String, int[][]> map = new LinkedHashMap<String, int[][]>();
		try {
			File f = new File(System.getProperty("user.dir")+"/userDef.lg");
			if(!f.exists()) return map;

			ObjectInputStream is=new ObjectInputStream(new FileInputStream(f));

			while(is.available()>0){
				map.put(is.readUTF(), (int[][])is.readObject());
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return map;
	}

}
