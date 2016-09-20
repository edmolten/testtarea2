package cl.usm.lp20162;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Circle;

public class TestTarea2 extends ApplicationAdapter {
    
        static public final int screenWidth = 500;
        static public final int screenHeight = 770;
        static public final int cellWidth = 55;
        static public final int cellsPerRow = 7;
        static public final int menuWidth = screenWidth - cellWidth * cellsPerRow;
    
	SpriteBatch batch;
        ShapeRenderer renderer;
	OrthographicCamera camera; 
        Circle attackButton;
        Circle crosButton;
        
        
	@Override
	public void create () {
            renderer = new ShapeRenderer();
            camera = new OrthographicCamera();
            camera.setToOrtho(false, screenWidth,screenHeight);
            Gdx.gl.glLineWidth(1);
            renderer.setProjectionMatrix(camera.combined);
        }

	@Override
	public void render () {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            camera.update();
           // batch.begin();
            drawBoards();
            drawShips();
           // batch.end();
	}
	
        
        private void drawLine(Vector2 start, Vector2 end){
            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.line(start, end);
            renderer.end();
        }
               
        private void drawBoards(){
            renderer.setColor(Color.BLACK);
            Vector2 start, end;
            for(int x = menuWidth; x <= screenWidth; x += cellWidth){
                start = new Vector2(x,0);
                end = new Vector2(x,screenHeight);
                drawLine(start, end);       
            }
            for(int y = 0; y <= screenHeight; y += cellWidth){
                if ( y == screenHeight / 2) renderer.setColor(Color.RED); //separación del medio
                start = new Vector2(menuWidth,y);
                end = new Vector2(screenWidth,y);
                drawLine(start, end);
                if ( y == screenHeight / 2) renderer.setColor(Color.BLACK);
            }
        }
	
        private void drawShips(){
            
        }
        
        @Override
	public void dispose () {
//		batch.dispose();
		
	}
}
