package cl.usm.lp20162;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.ArrayList;

public class TestTarea2 extends ApplicationAdapter {

    static public final int SCREEN_WIDTH = 500;
    static public final int SCREEN_HEIGHT = 770;
    static public final int CELL_SIDE = 55;
    static public final int CELLS_PER_ROW = 7;
    static public final int MENU_WIDTH = SCREEN_WIDTH - CELL_SIDE * CELLS_PER_ROW;

    static ShapeRenderer renderer;

    SpriteBatch batch;
    OrthographicCamera camera;
    Circle attackButton;
    Circle crosButton;
    Board board;
    ArrayList<Ship> ships;
    ConnectionManager connectorManager;
    Stage stage;
    static Label labelDetails;
    static Label labelMessage;
    Skin skin;
    static TextArea textMessage;
    static TextArea textIPAddress;
    static TextButton button;

    @Override
    public void create() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        Gdx.gl.glLineWidth(1);
        renderer.setProjectionMatrix(camera.combined);
        board = new Board();
        connectorManager = new ConnectionManager();
        ships = Ship.initShips();
        skin = new  Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage();
        // Wire the stage to receive input, as we are using Scene2d in this example
        Gdx.input.setInputProcessor(stage);
        
        setButtons();
    }

    private void setButtons() {
        
        // Vertical group groups contents vertically.  I suppose that was probably pretty obvious
        VerticalGroup vg = new VerticalGroup().space(3).pad(5);//.space(2).pad(5).fill();//.space(3).reverse().fill();
        // Set the bounds of the group to the entire virtual display
        vg.setBounds(0, 0, MENU_WIDTH, SCREEN_HEIGHT);

        // Create our controls
        labelDetails = new Label(connectorManager.ipAddress, skin);
        labelMessage = new Label("Hello world", skin);
        button = new TextButton("Send message", skin);
        textIPAddress = new TextArea("", skin);
        textMessage = new TextArea("", skin);

        // Add them to scene
        vg.addActor(labelDetails);
        vg.addActor(labelMessage);
        vg.addActor(textIPAddress);
        vg.addActor(textMessage);
        vg.addActor(button);
        stage.addActor(vg);
        //stage.setViewport(new ExtendViewport(MENU_WIDTH, SCREEN_HEIGHT));
        //stage.getCamera().position.set(MENU_WIDTH/2,SCREEN_HEIGHT/2,0);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();
        board.drawBoards();
        stage.draw();
        //drawShips();
        //drawEnemySide();
        batch.end();
    }

    private void drawShips() {
        for (Ship ship : ships) {
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
