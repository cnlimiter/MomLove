package nova.committee.momlove.init;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/1 12:08
 * Description:
 */
public class ModConfig extends LinkedList<UUID> {

    public String getConfigName(){
        return "data";
    }
}
