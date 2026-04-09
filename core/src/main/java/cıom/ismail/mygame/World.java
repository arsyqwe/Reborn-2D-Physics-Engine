package cıom.ismail.mygame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class World {
public final int WIDTH=100;
public final int HEIGHT =60;

public Block [][] map;

public World() {
	map = new Block[WIDTH][HEIGHT];
	createWorld();
}
private void createWorld() {
	for(int i = 0 ; i < WIDTH ; i++) {
		for(int j = 0 ; j< HEIGHT; j++) {
			if (j<20) {
				map[i][j] = Blocks.get(1);		
			}else {
				map[i][j]=null;
			}
		}
	}
}
public void render(SpriteBatch batch) {
	for(int i = 0 ; i<WIDTH ; i++) {
		for (int  j = 0 ; j<HEIGHT ; j++) {
			if (map[i][j] != null && map[i][j].texture != null) {
				batch.draw(map[i][j].texture, i * 32, j * 32);
			}
		}
	}
}
}
