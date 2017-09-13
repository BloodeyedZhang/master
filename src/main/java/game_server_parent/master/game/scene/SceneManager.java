package game_server_parent.master.game.scene;

/**
 * <p>Filename:SceneManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class SceneManager {

    private static SceneManager instance = new SceneManager();
    
    public static SceneManager getInstance() {
        return instance;
    }
}
