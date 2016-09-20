package cl.usm.lp20162;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

public class Ship {

    static ArrayList<Ship> initShips() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }
    
    private class BodyPart{
        Rectangle rect;
        boolean isDestroyed;
        
        public BodyPart(Rectangle rect){
            this. rect = rect;
            isDestroyed = false;
        }
    }
    
    ArrayList<BodyPart> bodyParts;
    private int movementLeft = 3;
    
    public Ship(Vector2 position, int cellsHorizontal, int cellsVertical){
        
        Rectangle r;
        bodyParts = new ArrayList<BodyPart>();
        
        for(float x = position.x ; x != cellsHorizontal * TestTarea2.CELL_SIDE; x += TestTarea2.CELL_SIDE){
            for(float y = position.y ; x != cellsVertical * TestTarea2.CELL_SIDE; x += TestTarea2.CELL_SIDE){
                r = new Rectangle(position.x, position.y, TestTarea2.CELL_SIDE, TestTarea2.CELL_SIDE);
                bodyParts.add(new BodyPart(r));
            }
        }
    }
    
    public void move(Vector2 newPosition){
        Vector2 partPosition;
        Vector2 difference;
        for(BodyPart bodyPart : bodyParts){
            partPosition = new Vector2(bodyPart.rect.x, bodyPart.rect.y);
            difference = newPosition.sub(partPosition);
            bodyPart.rect.x = partPosition.add(difference).x;
            bodyPart.rect.y = partPosition.add(difference).y;
            
        }
    }
}
