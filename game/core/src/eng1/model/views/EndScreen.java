package eng1.model.views;

import com.badlogic.gdx.Screen;
import com.eng1.game.ENG1;

public class EndScreen implements Screen {
    private ENG1 parent; // a field to store our orchestrator

    // our constructor with a ENG1 argument
    public EndScreen(ENG1 eng1){
        parent = eng1;     // setting the argument to our field.
    }
    @Override
    public void show() {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        parent.changeScreen(ENG1.MENU);
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }
}