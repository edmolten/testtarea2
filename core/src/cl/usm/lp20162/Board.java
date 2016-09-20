
package cl.usm.lp20162;

import static cl.usm.lp20162.TestTarea2.CELL_SIDE;
import static cl.usm.lp20162.TestTarea2.MENU_WIDTH;
import static cl.usm.lp20162.TestTarea2.SCREEN_HEIGHT;
import static cl.usm.lp20162.TestTarea2.SCREEN_WIDTH;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Board {
    
    private boolean[][] enemySide;
    private boolean[][] playerSide;
    
    public Board(){
        enemySide = new boolean[7][7];
        playerSide = new boolean[7][7];
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                enemySide[i][j] = false;
                playerSide[i][j] = false;
            }
        }
    }
    
    private void drawLine(Vector2 start, Vector2 end){
        TestTarea2.renderer.begin(ShapeRenderer.ShapeType.Line);
        TestTarea2.renderer.line(start, end);
        TestTarea2.renderer.end();
    }

    public void drawBoards(){
        TestTarea2.renderer.setColor(Color.BLACK);
        Vector2 start, end;
        for(int x = MENU_WIDTH; x <= SCREEN_WIDTH; x += CELL_SIDE){
            start = new Vector2(x,0);
            end = new Vector2(x,SCREEN_HEIGHT);
            drawLine(start, end);       
        }
        for(int y = 0; y <= SCREEN_HEIGHT; y += CELL_SIDE){
            if ( y == SCREEN_HEIGHT / 2) TestTarea2.renderer.setColor(Color.RED); //separación del medio
            start = new Vector2(MENU_WIDTH,y);
            end = new Vector2(SCREEN_WIDTH,y);
            drawLine(start, end);
            if ( y == SCREEN_HEIGHT / 2) TestTarea2.renderer.setColor(Color.BLACK);
        }
    }
}
