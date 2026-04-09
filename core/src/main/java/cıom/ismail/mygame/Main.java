package cıom.ismail.mygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
   
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport; 
	private World myWorld;
	private Sprite playerSprite;
	
	private float playerX = 50*32;
	private float playerY=25*32;
	private float speed = 200f;
	private float playerVelocityY=0f;
	private float gravity=-1000f;
	//private float groundLevel = 20*32f;
	private float jumpForce=450f;
	private Vector3 mousePos = new Vector3();

    @Override
    public void create() {
        batch = new SpriteBatch();
        Blocks.load();
        myWorld=new World();
        Texture tex = new Texture("char.png");
        playerSprite= new Sprite(tex);
        playerSprite.setSize(32, 32);
        camera =new OrthographicCamera();
        viewport = new ExtendViewport(400, 225, camera);
        
    }
    public void resize(int width, int height) {
        
        viewport.update(width, height, true); 
    }
    @Override
    public void render() {
        ScreenUtils.clear(0.53f, 0.81f, 0.92f, 1f);
        float deltaTime = Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
        	float nextX = playerX + speed * deltaTime;
        	int nextGridX= (int) ((nextX + 31)/32);;
        	int gridY_alt = (int) ((playerY+2) / 32);
        	int gridY_Ust = (int) ((playerY + 30) / 32);
        	boolean canMove = true;
        	if (nextGridX >= 0 && nextGridX < myWorld.WIDTH && gridY_alt >= 0 && gridY_alt < myWorld.HEIGHT && gridY_Ust >= 0 && gridY_Ust < myWorld.HEIGHT) {
                if (myWorld.map[nextGridX][gridY_alt] != null || myWorld.map[nextGridX][gridY_Ust] != null) {
                    canMove = false;
                }
            }
        	if (canMove) {playerX = nextX;}
            playerSprite.setFlip(false, false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
        	float nextX = playerX - speed * deltaTime;
        	int nextGridX =(int) (nextX /32);
            int gridY_alt= (int) ((playerY+2) / 32);
            int gridY_Ust =(int) ((playerY + 30) / 32);
            boolean canMove = true;
            if (nextGridX >= 0 && nextGridX <myWorld.WIDTH && 
                    gridY_alt >= 0 && gridY_alt <myWorld.HEIGHT && 
                    gridY_Ust >= 0 && gridY_Ust< myWorld.HEIGHT) {
                    
                    
                    if (myWorld.map[nextGridX][gridY_alt]!=null ||myWorld.map[nextGridX][gridY_Ust]!= null) {
                        canMove = false;
                    }
                }
            if (canMove) {
                playerX=nextX;
            }
            playerSprite.setFlip(true, false);
        }
        playerVelocityY+= gravity*deltaTime;
        playerY+= playerVelocityY*deltaTime;
       int gridX = (int)((playerX+16)/32);
       int gridY = (int)(playerY/32);
       if(gridX>=0 && gridX<myWorld.WIDTH && gridY>=0&&gridY<myWorld.HEIGHT) {
    	   if(myWorld.map[gridX][gridY]!= null) {
    		   playerY=(gridY+1)*32f;
    		   playerVelocityY = 0f;
    		   if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
    			   playerVelocityY=jumpForce;
    		   }
    	   }
       }
        
       if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
    	   mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
    	   viewport.unproject(mousePos);
    	   int mGridX = (int)(mousePos.x/32);
    	   int mGridY=(int)(mousePos.y/32);
    	   double mesafe = Math.sqrt(Math.pow(playerX - mousePos.x, 2) + Math.pow(playerY - mousePos.y, 2));
    	   if (mesafe<150) {
    		   if(mGridX >= 0 && mGridX< myWorld.WIDTH&& mGridY>=0 && mGridY<myWorld.HEIGHT) {
        		   if (myWorld.map[mGridX][mGridY]==null) {
        			   myWorld.map[mGridX][mGridY] =Blocks.Dirt;
        			   
        		   }       
    	   }
        	   }
    	   
       }
       if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
    	   mousePos.set(Gdx.input.getX(),Gdx.input.getY() , 0);
    	viewport.unproject(mousePos);
    	double mesafe = Math.sqrt(Math.pow(playerX - mousePos.x, 2) + Math.pow(playerY - mousePos.y, 2));
    	if(mesafe<150) {
    		int mGridX=(int)(mousePos.x /32);
    		int mGridY=(int)(mousePos.y/32);
    		if (mGridX>=0 && mGridX<myWorld.WIDTH &&mGridY>= 0 && mGridY< myWorld.HEIGHT) {
    			if (myWorld.map[mGridX][mGridY]!=null) {
                    myWorld.map[mGridX][mGridY] =null;
    		}
    	}
       }}
 
        
        
        
        viewport.apply();
        camera.position.set(playerX + 16, playerY + 16, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        myWorld.render(batch);
        playerSprite.setPosition(playerX, playerY);
        playerSprite.draw(batch);
        batch.end();
        
    }

    @Override
    public void dispose() {
        batch.dispose();
        playerSprite.getTexture().dispose();
    }
}
