package cıom.ismail.mygame;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

public class Blocks {
public static HashMap<Integer, Block> allBlocks = new HashMap<Integer, Block>();
public static Block Dirt;
 
public static void load() {
	Texture dirtTex  = new Texture("dirt.png");
	Dirt = new Block(1, "Dirt", dirtTex, 100f, true);
    register(Dirt);
}
private static void register(Block block) {
	allBlocks.put(block.id, block);
}
public static Block get(int id) {
	return allBlocks.get(id);
}
}
