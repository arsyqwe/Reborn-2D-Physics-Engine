package cıom.ismail.mygame;

import com.badlogic.gdx.graphics.Texture;

public class Block {
 public  int id;
 public  String name;
 public Texture texture;
 
 public float health;
 public boolean isSolid;
 
 public Block(int id , String name, Texture texture, float health, boolean isSolid) {
	 this.id=id;
	 this.name=name;
	 this.texture=texture;
	 this.health=health;
	 this.isSolid=isSolid;
 }
 
}
