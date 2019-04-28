package com.doogies.savepups.audio;

public class AudioManager {

    // Audio
    public static AudioPlayer ogre2 = new AudioPlayer();

    public static AudioPlayer orcAttack = new AudioPlayer();

    public static AudioPlayer goldCoinDrop = new AudioPlayer();

    public AudioManager() {

        ogre2.setFile("/soundEffects/rpgSounds/NPC/ogre/ogre2");
        orcAttack.setFile("/soundEffects/rpgSounds/NPC/gutteral beast/mnstr11");
        goldCoinDrop.setFile("/soundEffects/rpgSounds/inventory/coin2");

    }


}
