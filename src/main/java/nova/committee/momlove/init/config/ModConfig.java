package nova.committee.momlove.init.config;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.UUID;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/2 19:57
 * Description:
 */
public class ModConfig {
    public String getConfigName(){
        return "data";
    }

    @SerializedName(value = "key_words_data")
    private KeyWords keyWordsData = new KeyWords();

    @SerializedName(value = "uuid_data")
    private Data uuidData = new Data();


    public Data getUuidData() {
        return uuidData;
    }

    public KeyWords getKeyWordsData() {
        return keyWordsData;
    }

    public static class Data extends LinkedList<UUID> {
    }

    public static class KeyWords extends LinkedList<String> {
    }


}
